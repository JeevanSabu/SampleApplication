package com.SampleApplication.SampleApplication2.model;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BarcodeBean;

@ManagedBean(name = "barcodeModel" , eager = true)
@SessionScoped
public class BarcodeModel {
	private static final Logger LOGGER = LogManager.getLogger(BarcodeModel.class);
	
	private String result = "authentication";

	FacesContext context = FacesContext.getCurrentInstance();
	BarcodeBean barcodeBean = (BarcodeBean) context.getApplication().getExpressionFactory()
            .createValueExpression(context.getELContext(), "#{barcodeBean}", BarcodeBean.class)
              .getValue(context.getELContext());
	
	public String getResult() {
		try {
			String barcodeId = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("barcodeId");
			LOGGER.trace("BarcodeId from session "+barcodeId);
			if(barcodeBean.getId().equals(barcodeId)) {
				result="home";
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Authentication failed","Id mismatch"));
			}
		} catch(Exception e) {
			LOGGER.error("Error in barcode session"+e.getMessage());
		}
		
		return result;
	}

}
