package com.SApp.Ticket.model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.validation.ValidationException;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.owasp.esapi.ESAPI;

import com.SApp.Ticket.bean.BarcodeBean;
import com.SApp.Ticket.bean.UserBean;
import com.SApp.Ticket.jerseyclient.BookingListClient;
import com.SApp.Ticket.jerseyclient.BookingListPojo;

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
	 * method to check and validate barcode id 
	 * returns home page if validated
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
				bookingListPojo.setBookingList(bookingListClient.postBookingList(userBean.getUsername()));
				LOGGER.trace("From bookinListPojo "+bookingListPojo.getBookingList());
				context.getExternalContext().getSessionMap().put("verifiedid", barcodeBean.getId());
				result="home";
			}
			else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id mismatch"));
			}
		} catch(ValidationException ve) {
			LOGGER.error("Error"+ve.getCause());
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id mismatch"));
		} catch(Exception e) {
			LOGGER.error("Error"+e.getMessage());			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id mismatch"));
		}

		LOGGER.trace("Leaving BarcodeModel getResult method...");
		return result;
	}

}
