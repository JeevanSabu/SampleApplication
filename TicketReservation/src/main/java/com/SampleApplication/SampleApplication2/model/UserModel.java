package com.SampleApplication.SampleApplication2.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
//import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SampleApplication.SampleApplication2.bean.BarcodeBean;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.LastLoginClient;
import com.SampleApplication.SampleApplication2.jerseyclient.User;
import com.SampleApplication.SampleApplication2.jerseyclient.UserPojo;
import com.SampleApplication.SampleApplication2.tools.SessionUtils;

@ManagedBean( name = "userModel" , eager = true)
@SessionScoped
public class UserModel {
    private static final Logger LOGGER = LogManager.getLogger(UserModel.class);
	private String result = "login";
	FacesContext context = FacesContext.getCurrentInstance();
	UserBean userBean = (UserBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{userBean}", UserBean.class)
	              .getValue(context.getELContext());

	BarcodeBean barcodeBean = (BarcodeBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{barcodeBean}", BarcodeBean.class)
	              .getValue(context.getELContext());
	private User user = new User();
	private UserPojo userPojo = new UserPojo();
	/**
	 * 
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside Usermodel getResult method");
		if(null==userBean) {
			LOGGER.error("userBean null");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login Failed","Fields can't be empty"));
		}
		try {
			String validusername = ESAPI.encoder().canonicalize(userBean.getUsername());
			String validpassword = ESAPI.encoder().canonicalize(userBean.getPassword());
			boolean isvaliduser = ESAPI.validator().isValidInput("username", userBean.getUsername(), "username", 30, false);
			boolean isvalidpassword = ESAPI.validator().isValidInput("password", userBean.getPassword(), "password", 30, false);
			LOGGER.trace("user "+validusername+" password "+validpassword);
			LOGGER.trace("is valid "+isvaliduser+" "+isvalidpassword);
			if(isvaliduser==false||isvalidpassword==false) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Credentials","Username and Password possess values that are not allowed"));
				return "login";
			}
			
			userPojo = user.getUser(userBean.getUsername(),userBean.getPassword());
			LOGGER.trace("UserName "+userPojo.getUsername());
//			context).getExternalContext().getSessionMap().put("username", userBean.getUsername());
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", userBean.getUsername());
		} catch(ValidationException ve) {
			LOGGER.error("Error"+ve.getCause());
		} catch(Exception e) {
			LOGGER.error("Exception "+e.getMessage());
		}
		
		if((null!=userPojo.getUsername()&&StringUtils.equals(userPojo.getUsername(), userBean.getUsername()))
				&&(null!=userPojo.getPassword()&&StringUtils.equals(userPojo.getPassword(), userBean.getPassword()))) {

			userBean.setLastLogin(userPojo.getLastlogin());
			Random random = new Random();
			int generatedId = random.nextInt(900000) + 100000;
			
			userBean.setGenId(Integer.toString(generatedId));
			LOGGER.trace("BarcodeId "+generatedId);
			try {
//				context.getExternalContext().getSessionMap().put("barcodeId", Integer.toString(generatedId));
				HttpSession session = SessionUtils.getSession();
				session.setAttribute("barcodeId", Integer.toString(generatedId));
			}catch(Exception ex) {
				LOGGER.error("BarcodeId setting in session error:"+ex.getMessage());
			}
			result = "authentication";
		}
		else {
			LOGGER.trace("No user Found");
			try {
				context.getExternalContext().invalidateSession();
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid Credentials","Username and Password incorrect"));
				result="login";
			}catch(Exception ex) {
				LOGGER.error("Invalidate session error:"+ex.getMessage());
			}
		}
		LOGGER.trace("Leaving Usermodel getResult method...");
		return result;
	}
	/**
	 * 
	 * @return
	 */
	public String logout() {
		LOGGER.trace("Inside logout method");
		try {
			Date date = new Date();
		    SimpleDateFormat ft = new SimpleDateFormat ("d MMM y HH:mm:ss");
		    String formatteddate = ft.format(date).toString();
		    LOGGER.trace("Logout at "+formatteddate);
		    LastLoginClient lastLoginClient = new LastLoginClient();
		    int status = lastLoginClient.logout(userBean.getUsername(),userBean.getPassword(),formatteddate);
		    if(status==200) {
		     	LOGGER.trace("lastlogin saved");
		    }
//			context.getExternalContext().invalidateSession();	
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", null);
			session.setAttribute("barcodeId", null);
			session.invalidate();
		} catch(Exception inv) {
			LOGGER.error("Invalidating Error "+inv.getMessage());
		}
		LOGGER.trace("Leaving Usermodel logout method");
        return "/login.xhtml?faces-redirect=true";
	}
}
