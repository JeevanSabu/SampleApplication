package com.SampleApplication.SampleApplication2.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.HomeBean;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.tools.Bus;
import com.SampleApplication.SampleApplication2.tools.BusView;

@ManagedBean(name="homeModel" , eager=true)
@SessionScoped
public class HomeModel {
	private static final Logger LOGGER = LogManager.getLogger(HomeModel.class);
	private String result = "home";
	FacesContext context = FacesContext.getCurrentInstance();
	UserBean userBean = (UserBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{userBean}", UserBean.class)
	              .getValue(context.getELContext());
	HomeBean homeBean = (HomeBean) context.getApplication().getExpressionFactory()
	            .createValueExpression(context.getELContext(), "#{homeBean}", HomeBean.class)
	              .getValue(context.getELContext());
	private HomeClient homeClient = new HomeClient();
//	BusView busview = (BusView) context.getApplication().getExpressionFactory()
//            .createValueExpression(context.getELContext(), "#{busview}", BusView.class)
//            .getValue(context.getELContext());
	BusView busview = new BusView();
	public String getResult() {
		LOGGER.trace("Date "+homeBean.getDate());
		List<Bus> buses = homeClient.search(homeBean.getSource(),homeBean.getDestination(),homeBean.getDate());
		busview.setBuses(buses);
		result = "booking";
		return result;
	}
}
