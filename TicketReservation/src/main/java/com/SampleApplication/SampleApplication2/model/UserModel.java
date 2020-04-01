package com.SampleApplication.SampleApplication2.model;

import java.util.Random;

//import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BarcodeBean;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.User;
import com.SampleApplication.SampleApplication2.jerseyclient.UserPojo;
//import com.SampleApplication.SampleApplication2.tools.SessionUtils;

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
//	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//	UserBean userBean
//	    = (UserBean) FacesContext.getCurrentInstance().getApplication()
//	    .getELResolver().getValue(elContext, null, "userBean");
//	private UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getContext();
	private User user = new User();
	public String getResult() {
		LOGGER.trace("Inside Usermodel");
		LOGGER.trace("from userbean "+userBean.getUsername());
		String uname= user.getUser(userBean.getUsername(),userBean.getPassword());
		LOGGER.trace("UserName "+uname);
		try {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", userBean.getUsername());
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
		if(null==uname) {
			LOGGER.trace("No user Found");
			try {
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			}catch(Exception ex) {
				LOGGER.error("Invalidate session error:"+ex.getMessage());
			}
			result="login";
		}
		else {
//			HttpSession session = SessionUtils.getSession();
//			session.setAttribute("username", userBean.getUsername());
//			LOGGER.trace("Session user "+FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
			// initialize a Random object somewhere; you should only need one
			
			Random random = new Random();
			int generatedId = random.nextInt(900000) + 100000;
			result = "home";
			barcodeBean.setGenId(generatedId);
		}
		return result;
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
}
