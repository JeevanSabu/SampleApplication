package com.SampleApplication.SampleApplication2.model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.ValidationException;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SampleApplication.SampleApplication2.bean.BarcodeBean;
import com.SampleApplication.SampleApplication2.bean.UserBean;
import com.SampleApplication.SampleApplication2.jerseyclient.BookingListClient;
import com.SampleApplication.SampleApplication2.jerseyclient.BookingListPojo;

@ManagedBean(name = "barcodeModel" , eager = true)
@ViewScoped
public class BarcodeModel {
	private static final Logger LOGGER = LogManager.getLogger(BarcodeModel.class);
	
	private String result = "authentication";

	FacesContext context = FacesContext.getCurrentInstance();
	UserBean userBean = (UserBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{userBean}", UserBean.class)
              .getValue(context.getELContext());
	BarcodeBean barcodeBean = (BarcodeBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{barcodeBean}", BarcodeBean.class)
              .getValue(context.getELContext());
	BookingListPojo bookingListPojo = (BookingListPojo) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{bookingListPojo}", BookingListPojo.class)
              .getValue(context.getELContext());
	private BookingListClient bookingListClient = new BookingListClient();
//	private BookingListPojo bookingListPojo = new BookingListPojo();
	/**
	 * 
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside BarcodeModel getResult method");
		if(null==barcodeBean) {
			LOGGER.error("BarcodeBean null");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id field can't be null"));
		}
		try {
			boolean isvalidid = ESAPI.validator().isValidInput("barcodeid", barcodeBean.getId(), "barcodeid", 6, false);
			LOGGER.trace("is valid "+isvalidid);
			
			if(isvalidid==false) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id field possess invalid data"));
				return "authentication";
			}
			
			String barcodeId = (String) context.getExternalContext().getSessionMap().get("barcodeId");
			LOGGER.trace("BarcodeId from session "+barcodeId);
			if(null==bookingListClient) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id mismatch"));
				return "authentication";
			}
			else if(StringUtils.equals(barcodeBean.getId(), barcodeId)) {
				bookingListPojo.setBookingList(bookingListClient.getBookingList(userBean.getUsername()));
				LOGGER.trace("From bookinListPojo "+bookingListPojo.getBookingList());
				result="home";
			}
			else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id mismatch"));
			}
		} catch(ValidationException ve) {
			LOGGER.error("Error"+ve.getCause());
		} catch(Exception e) {
			LOGGER.error("Error"+e.getMessage());
		}

		LOGGER.trace("Leaving BarcodeModel getResult method...");
		return result;
	}

}
