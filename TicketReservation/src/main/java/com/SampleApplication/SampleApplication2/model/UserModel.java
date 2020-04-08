package com.SampleApplication.SampleApplication2.model;

import java.util.Random;

import javax.faces.application.FacesMessage;
//import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BarcodeBean;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.User;
import com.SampleApplication.SampleApplication2.jerseyclient.UserPojo;
//import com.SampleApplication.SampleApplication2.tools.SessionUtils;
import com.SampleApplication.SampleApplication2.tools.SessionUtils;

@ManagedBean( name = "userModel" , eager = true)
@SessionScoped
public class UserModel {
    private static final Logger LOGGER = LogManager.getLogger(UserModel.class);
	private String result = "login";
//	@ManagedProperty(value="#{userBean}")
//	private UserBean userBean;
//	public void setUserBean(UserBean userBean) {
//		this.userBean = userBean;
//	}
	FacesContext context = FacesContext.getCurrentInstance();
	UserBean userBean = (UserBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{userBean}", UserBean.class)
	              .getValue(context.getELContext());

	BarcodeBean barcodeBean = (BarcodeBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{barcodeBean}", BarcodeBean.class)
	              .getValue(context.getELContext());
	private User user = new User();
	private UserPojo userPojo = new UserPojo();
	public String getResult() {
		LOGGER.trace("Inside Usermodel");
		LOGGER.trace("from userbean "+userBean.getUsername());
//		String uname = null;
		try {
//		uname = user.getUser(userBean.getUsername(),userBean.getPassword());
		userPojo = user.getUser(userBean.getUsername(),userBean.getPassword());
		LOGGER.trace("UserName "+userPojo.getUsername());
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", userBean.getUsername());
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", userBean.getUsername());
		}catch(Exception e) {
			LOGGER.error("Exception "+e.getMessage());
		}
		if((null!=userPojo.getUsername()&&userPojo.getUsername().equals(userBean.getUsername()))&&(null!=userPojo.getPassword()&&userPojo.getPassword().equalsIgnoreCase(userBean.getPassword()))) {
//			HttpSession session = SessionUtils.getSession();
//			session.setAttribute("username", userBean.getUsername());
//			LOGGER.trace("Session user "+FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
			
			Random random = new Random();
			int generatedId = random.nextInt(900000) + 100000;
			
			barcodeBean.setGenId(Integer.toString(generatedId));
			try {
//				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("barcodeId", Integer.toString(generatedId));

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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Credentials","Username and Password incorrect"));
//				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Invalid Credentials","Please enter correct username and Password"));
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				
			}catch(Exception ex) {
				LOGGER.error("Invalidate session error:"+ex.getMessage());
			}
			result="login";
		}
		return result;
	}
	public String logout() {
		LOGGER.trace("Inside logout");
		try {
	//		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			HttpSession session = SessionUtils.getSession();
			session.invalidate();
		} catch(Exception inv) {
			LOGGER.error("Invalidating Error "+inv.getMessage());
		}
		return "login";
	}
}
