package com.SampleApplication.SampleApplication2.model;

//import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.User;
import com.SampleApplication.SampleApplication2.jerseyclient.UserPojo;
//import com.SampleApplication.SampleApplication2.tools.SessionUtils;

@ManagedBean( name = "userModel" , eager = true)
@SessionScoped
public class UserModel {
    private static final Logger LOGGER = LogManager.getLogger(UserModel.class);
	private String result = "login";
	@ManagedProperty(value="userBean")
	private UserBean userBean;
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
//	ELContext elContext = FacesContext.getCurrentInstance().getELContext();
//	UserBean userBean
//	    = (UserBean) FacesContext.getCurrentInstance().getApplication()
//	    .getELResolver().getValue(elContext, null, "userBean");
//	private UserBean userBean = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getContext();
	private User user = new User();
	public String getResult() {
		LOGGER.trace("Inside Usermodel");
		String uname= user.getUser(userBean.getUsername(),userBean.getPassword());
		LOGGER.trace("UserName "+uname);
		if(null!=uname||!uname.equals("")) {
//			HttpSession session = SessionUtils.getSession();
//			session.setAttribute("username", user);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", userBean.getUsername());
			LOGGER.trace("Session user "+FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
		}
		else {
			result="login";
		}
		result = "home";
		return result;
	}
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login";
	}
}
