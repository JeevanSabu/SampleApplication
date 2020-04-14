function selection(element,seatno){
//	document.getElementById(id).classList.toggle("select");
	let paybutton = "bus_seats:selected_bus:pay_button";
	element.classList.toggle("select");
	document.getElementById("bus_seats:selected_bus:pay_button").style.visibility = "visible";
	let selectedseats = document.getElementById("bus_seats:selected_bus:selected_seats").value;
	if(selectedseats.search(seatno)==-1){
		if((null!=selectedseats)&&(selectedseats!="")){
			selectedseats = selectedseats+",";
			let price = parseInt(document.getElementById("bus_seats:selected_bus:pay_button").value)+1200;
			document.getElementById("bus_seats:selected_bus:pay_button").value = price;
			document.getElementById("bus_seats:selected_bus:pay_button").innerHTML = "<span class='ui-button-icon-left ui-icon ui-c fa fa-inr'></span>" +
					"<span class='ui-button-text ui-c'\>"+price+"</span>";
		}
		else{
			document.getElementById("bus_seats:selected_bus:pay_button").value = 1200;
			document.getElementById("bus_seats:selected_bus:pay_button").innerHTML = "<span class='ui-button-icon-left ui-icon ui-c fa fa-inr'></span>" +
			"<span class='ui-button-text ui-c'\>"+1200+"</span>";
		}
		document.getElementById("bus_seats:selected_bus:selected_seats").value = selectedseats+seatno;
	}
	else{
		selectedseats = selectedseats.replace(seatno+",","");
		selectedseats = selectedseats.replace(seatno,"");
		document.getElementById("bus_seats:selected_bus:selected_seats").value = selectedseats;
		let price = parseInt(document.getElementById("bus_seats:selected_bus:pay_button").value)-1200;
		document.getElementById("bus_seats:selected_bus:pay_button").value = price;
		document.getElementById("bus_seats:selected_bus:pay_button").innerHTML = "<span class='ui-button-icon-left ui-icon ui-c fa fa-inr'></span>" +
				"<span class='ui-button-text ui-c'\>"+price+"</span>";
		if((null==selectedseats)||(selectedseats=="")){			
			document.getElementById("bus_seats:selected_bus:pay_button").style.visibility = "hidden";
		}
	}
}
function showDebitOtp(){
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_header").style.visibility="visible";
//	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_header").value="123456";
//	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_header").innerHTML="123456";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_label").style.visibility="visible";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_field").style.visibility="visible";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_regenerate_button").style.visibility="visible";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_pay_button").style.visibility="visible";
}
function showCreditOtp(){
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_header").style.visibility="visible";
//	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_header").value="123456";
//	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_header").innerHTML="123456";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_label").style.visibility="visible";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_field").style.visibility="visible";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_regenerate_button").style.visibility="visible";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_pay_button").style.visibility="visible";
}
function selectedBusFunction(id){
	alert("selected bus"+id);
//	document.getElementById("booking_form:ooking_table:selected_bus_field").value=id;
}