package com.SApp.Ticket.model;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.faces.application.FacesMessage;
//import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.crypto.CipherText;
import org.owasp.esapi.crypto.PlainText;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.reference.crypto.JavaEncryptor;

import com.SApp.Ticket.bean.BarcodeBean;
import com.SApp.Ticket.bean.UserBean;
import com.SApp.Ticket.jerseyclient.LastLoginClient;
import com.SApp.Ticket.jerseyclient.User;
import com.SApp.Ticket.jerseyclient.UserPojo;
import com.SApp.Ticket.tools.SessionUtils;

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
		HttpSession session = SessionUtils.getSession();
		try {
//			CipherText cText = ESAPI.encryptor().encrypt(new PlainText(userBean.getPassword()));
			CipherText cText = JavaEncryptor.getInstance().encrypt(new PlainText(userBean.getPassword()));
//			String encryptedPassword = ESAPI.encryptor().hash(userBean.getPassword(), userBean.getUsername());
			LOGGER.trace("Encrypted password "+cText);
			boolean isvaliduser = ESAPI.validator().isValidInput("username", userBean.getUsername(), "username", 30, false);
			boolean isvalidpassword = ESAPI.validator().isValidInput("password", userBean.getPassword(), "password", 30, false);
			LOGGER.trace("is valid "+isvaliduser+" "+isvalidpassword);
			if(isvaliduser==false||isvalidpassword==false) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Credentials","Username and Password possess values that are not allowed"));
				return "login";
			}
			
			session.setAttribute("username", userBean.getUsername());
			session.setMaxInactiveInterval(300);
			LOGGER.trace(session.getId());
			userPojo = user.postUser(userBean.getUsername(),userBean.getPassword());
			LOGGER.trace("UserName "+userPojo.getUsername());
//			context).getExternalContext().getSessionMap().put("username", userBean.getUsername());
		} catch(EncryptionException ee) {
			LOGGER.error("Encryption Exception "+ee.getMessage()+" cause "+ee.getCause());
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
