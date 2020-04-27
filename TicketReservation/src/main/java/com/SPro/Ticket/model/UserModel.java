package com.SPro.Ticket.model;

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

import com.SPro.Ticket.bean.BarcodeBean;
import com.SPro.Ticket.bean.UserBean;
import com.SPro.Ticket.jerseyclient.LastLoginClient;
import com.SPro.Ticket.jerseyclient.User;
import com.SPro.Ticket.jerseyclient.UserPojo;
import com.SPro.Ticket.tools.SessionUtils;

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
	 * method to validate username and password
	 * returns barcode page on successfull validation
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
			
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", userBean.getUsername());
			session.setMaxInactiveInterval(300);
			LOGGER.trace(session.getId());
			userPojo = user.postUser(userBean.getUsername(),userBean.getPassword());
			LOGGER.trace("UserName "+userPojo.getUsername());
//			context).getExternalContext().getSessionMap().put("username", userBean.getUsername());
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
				session.setAttribute("token", userPojo.getAccessToken());
				session.setAttribute("barcodeId", Integer.toString(generatedId));
				LOGGER.trace(session.getId());
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
	 * method to logout
	 * invalidates session 
	 * and returns login page
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
		    if(null!=userBean) {
//		    	int status = lastLoginClient.logout(userBean.getUsername(),userBean.getPassword(),formatteddate);
		    	int status = lastLoginClient.logout(userBean.getUsername(),userBean.getPassword());
		    	if(status==200) {
		    		LOGGER.trace("lastlogin saved");
		    	}
		    }
		    HttpSession session = SessionUtils.getSession();
			LOGGER.trace(session.getId());
			session.setAttribute("username", null);
			session.setAttribute("barcodeId", null);
			session.setAttribute("verifiedid", null);
			session.invalidate();
//			context.getExternalContext().invalidateSession();
//			LOGGER.trace(session.getId());
		} catch( UnsupportedOperationException ue) {
			LOGGER.error("UnsupportedOperationException "+ue.getMessage());
		} catch(IllegalStateException ie) {
			LOGGER.error("IllegalStateException "+ie.getMessage());
		} catch(Exception inv) {
			LOGGER.error("Invalidating Error "+inv.getMessage());
		}
		LOGGER.trace("Leaving Usermodel logout method");
        return "/login.xhtml?faces-redirect=true";
	}
}
