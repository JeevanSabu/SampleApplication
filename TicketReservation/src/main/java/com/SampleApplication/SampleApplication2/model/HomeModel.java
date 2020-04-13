package com.SampleApplication.SampleApplication2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BusView;
import com.SampleApplication.SampleApplication2.bean.HomeBean;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.BusViewPojo;
import com.SampleApplication.SampleApplication2.jerseyclient.HomeClient;

@ManagedBean(name = "homeModel" , eager = true)
@SessionScoped
public class HomeModel {
	private static final Logger LOGGER = LogManager.getLogger(HomeModel.class);
	private String result;
	FacesContext context = FacesContext.getCurrentInstance();
	UserBean userBean = (UserBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{userBean}", UserBean.class)
              .getValue(context.getELContext());
	HomeBean homeBean = (HomeBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{homeBean}", HomeBean.class)
              .getValue(context.getELContext());
	BusView busView = (BusView) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{busView}", BusView.class)
              .getValue(context.getELContext());

	private HomeClient homeClient = new HomeClient();
	public String getResult() {
		LOGGER.trace("HomeBean "+homeBean.getDate());
		try {
			BusViewPojo busViewPojo = homeClient.getBuses(homeBean.getSource(),homeBean.getDestination());
			busView.setBuses(busViewPojo.getBuses());
			LOGGER.trace(busView.getBuses());
		} catch(Exception e) {
			LOGGER.trace(e.getMessage());
		}
		result="booking";
		return result;
	}

}
