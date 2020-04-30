package com.SApp.Ticket.model;

import java.text.SimpleDateFormat;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SApp.Ticket.bean.BusView;
import com.SApp.Ticket.bean.HomeBean;
import com.SApp.Ticket.bean.UserBean;
import com.SApp.Ticket.jerseyclient.BusViewPojo;
import com.SApp.Ticket.jerseyclient.HomeClient;

@ManagedBean(name = "homeModel" , eager = true)
@ViewScoped
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
	/**
	 * Method to find bus on specified date
	 * to and from specified locations
	 * returns the list of booking page
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside HomeBean getResult method");
		if(null==homeBean) {
			LOGGER.error("homeBean is null");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data error","Fileds can't be null"));
			return "home";
		}
		try {
			boolean isvalidsource = ESAPI.validator().isValidInput("placename", homeBean.getSource(), "placename", 30, false);
			boolean isvaliddestination = ESAPI.validator().isValidInput("placename", homeBean.getDestination(), "placename", 30, false);
			LOGGER.trace("is valid "+isvalidsource+" "+isvaliddestination);
			if(isvalidsource==false||isvaliddestination==false) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data error","Invalid data"));
				return "home";
			}
		
			LOGGER.trace("unformatted date "+homeBean.getDate());
		    SimpleDateFormat ft = new SimpleDateFormat ("d MMMM y");
		    String date = ft.format(homeBean.getDate()).toString();
		    LOGGER.trace("Formatted Date "+date);
		    
			if(homeBean.getSource().equals(homeBean.getDestination())) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Data error","Source and Destination can't be same"));
				return "home";
			}
			BusViewPojo busViewPojo = homeClient.postBuses(homeBean.getSource(),homeBean.getDestination(),date);
			if(null!=busViewPojo) {				
				busView.setBuses(busViewPojo.getBuses());
			}
			LOGGER.trace(busView.getBuses());
			if(null==busView.getBuses()||busView.getBuses().size()<=0) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No buses Found","Try another search"));
				return "home";
			}
			result="booking";
		} catch(ValidationException ve) {
			LOGGER.error("Error"+ve.getCause());
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No buses Found","Try another search"));
			result="home";
		} catch(Exception e) {
			LOGGER.trace("Error"+e.getMessage());
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"No buses Found","Try another search"));
			result="home";
		}
		LOGGER.trace("Leaving HomeBean getResult method...");
		return result;
	}

}
