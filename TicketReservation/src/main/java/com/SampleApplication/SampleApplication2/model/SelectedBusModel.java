package com.SampleApplication.SampleApplication2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.SampleApplication.SampleApplication2.bean.BusSeatsView;
import com.SampleApplication.SampleApplication2.bean.SelectedBusBean;
import com.SampleApplication.SampleApplication2.jerseyclient.SelectedBusClient;

@ManagedBean(name="selectedBusModel", eager=true)
@SessionScoped
public class SelectedBusModel {
	private static final Logger LOGGER = LogManager.getLogger(SelectedBusModel.class);
	private String result;

	FacesContext context = FacesContext.getCurrentInstance();
	SelectedBusBean selectedBusBean = (SelectedBusBean) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{selectedBusBean}", SelectedBusBean.class)
			.getValue(context.getELContext());
	BusSeatsView busSeatsView = (BusSeatsView) context.getApplication().getExpressionFactory()
			.createValueExpression(context.getELContext(), "#{busSeatsView}", BusSeatsView.class)
			.getValue(context.getELContext());      
	private SelectedBusClient selectedBusClient = new SelectedBusClient();
	/**
	 * 
	 * @return
	 */
	public String getResult() {
		LOGGER.trace("Inside SelectedBusModel.getResult");
		if(null==selectedBusBean) {
			return null;
		}
		try {			
//			Map<String,String> params = context.getExternalContext().getRequestParameterMap();
//			LOGGER.trace("Booked Bus id "+params.get("busid"));
//			LOGGER.trace("Booked Bus id "+busView.getSelectedBus().getId());
			LOGGER.trace("Booked Bus id "+selectedBusBean.getSelectedBus().getId());
			busSeatsView.setBusId(Integer.parseInt(selectedBusBean.getSelectedBus().getId()));
			busSeatsView.setBusName(selectedBusBean.getSelectedBus().getName());
			busSeatsView.setPrice(selectedBusBean.getSelectedBus().getPrice());
			busSeatsView.setBusSeats(selectedBusClient.getSelectedBus(selectedBusBean.getSelectedBus().getId(),selectedBusBean.getSelectedBus().getName()));
//			busSeatsView.setBusId(Integer.parseInt(selectedBusBean.getId()));
//			busSeatsView.setBusName(selectedBusBean.getName());
//			busSeatsView.setPrice(Integer.parseInt(selectedBusBean.getPrice()));
//			busSeatsView.setBusSeats(selectedBusClient.getSelectedBus(selectedBusBean.getId(),selectedBusBean.getName()));
			LOGGER.trace("busSeatsView "+busSeatsView.getBusSeats().get(0).getSeatNo());
			result="bus1";
		} catch(Exception e) {
			LOGGER.error(e.getMessage());
			result="booking";
		}
		LOGGER.trace("leaving SelectedBusModel.getResult");
		return result;
	}

}
