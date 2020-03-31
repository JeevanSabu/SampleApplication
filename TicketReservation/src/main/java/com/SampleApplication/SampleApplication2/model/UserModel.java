package com.SampleApplication.SampleApplication2.model;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.User;
import com.SampleApplication.SampleApplication2.jerseyclient.UserPojo;

@ManagedBean( name = "userModel" , eager = true)
@SessionScoped
public class UserModel {
    private static final Logger LOGGER = LogManager.getLogger(UserModel.class);
	private String result = "login";
	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
	UserBean userBean
	    = (UserBean) FacesContext.getCurrentInstance().getApplication()
	    .getELResolver().getValue(elContext, null, "userBean");
//	private UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getContext();
//	private User user = new User();
	public String getResult() {
		LOGGER.trace("Inside Usermodel");
//		UserPojo userPojo = user.getUser(userBean.getUsername(),userBean.getPassword());
//		LOGGER.trace("UserName form userPojo "+userPojo.getUsername());
		result = "home";
		return result;
	}

}
