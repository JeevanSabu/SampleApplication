function selection(element,seatno,status,baseprice){
//	document.getElementById(id).classList.toggle("select");
	if(status=="selected"){
		return false;
	}
	let paybutton = "bus_seats:selected_bus:pay_button";
	element.classList.toggle("select");
	document.getElementById("bus_seats:selected_bus:pay_button").style.visibility = "visible";
	let selectedseats = document.getElementById("bus_seats:selected_bus:selected_seats").value;
	if(selectedseats.search(seatno)==-1){
		if((null!=selectedseats)&&(selectedseats!="")){
			selectedseats = selectedseats+",";
			let price = parseInt(document.getElementById("bus_seats:selected_bus:pay_button").value)+baseprice;
			document.getElementById("bus_seats:selected_bus:pay_button").value = price;
			document.getElementById("bus_seats:selected_bus:pay_button").innerHTML = "<span class='ui-button-icon-left ui-icon ui-c fa fa-inr'></span>" +
					"<span class='ui-button-text ui-c'\>"+price+"</span>";
		}
		else{
			document.getElementById("bus_seats:selected_bus:pay_button").value = baseprice;
			document.getElementById("bus_seats:selected_bus:pay_button").innerHTML = "<span class='ui-button-icon-left ui-icon ui-c fa fa-inr'></span>" +
			"<span class='ui-button-text ui-c'\>"+baseprice+"</span>";
		}
		document.getElementById("bus_seats:selected_bus:selected_seats").value = selectedseats+seatno;
	}
	else{
		selectedseats = selectedseats.replace(seatno+",","");
		selectedseats = selectedseats.replace(","+seatno,"");
		selectedseats = selectedseats.replace(seatno,"");
		document.getElementById("bus_seats:selected_bus:selected_seats").value = selectedseats;
		let price = parseInt(document.getElementById("bus_seats:selected_bus:pay_button").value)-baseprice;
		document.getElementById("bus_seats:selected_bus:pay_button").value = price;
		document.getElementById("bus_seats:selected_bus:pay_button").innerHTML = "<span class='ui-button-icon-left ui-icon ui-c fa fa-inr'></span>" +
				"<span class='ui-button-text ui-c'\>"+price+"</span>";
		if((null==selectedseats)||(selectedseats=="")){			
			document.getElementById("bus_seats:selected_bus:pay_button").style.visibility = "hidden";
		}
	}
}
function showDebitOtp(){
//	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_header").style.visibility="visible";
//	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_header").value="123456";
//	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_header").innerHTML="123456";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_label").style.visibility="visible";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_otp_field").style.visibility="visible";
//	document.getElementById("payment_tabview:payment_debit_otp_form:debit_regenerate_button").style.visibility="visible";
	document.getElementById("payment_tabview:payment_debit_otp_form:debit_pay_button").style.visibility="visible";
}
function showCreditOtp(){
//	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_header").style.visibility="visible";
//	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_header").value="123456";
//	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_header").innerHTML="123456";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_label").style.visibility="visible";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_otp_field").style.visibility="visible";
//	document.getElementById("payment_tabview:payment_credit_otp_form:credit_regenerate_button").style.visibility="visible";
	document.getElementById("payment_tabview:payment_credit_otp_form:credit_pay_button").style.visibility="visible";
}
function selectedBusFunction(id){
	alert("selected bus"+id);
//	document.getElementById("booking_form:ooking_table:selected_bus_field").value=id;
}
function validateuser(){
	if(document.getElementById("login_form:username").value==""||document.getElementById("login_form:password").value==""){
		alert("All fields are Mandatory");
	}
}
function validatebarcode(){
	if(document.getElementById("authentication_form:barcode_id").value==""){
		alert("All fields are Mandatory");
	}
}
function validatehome(){
	if(document.getElementById("home_tabview:booking_home_form:from_field").value==""||
			document.getElementById("home_tabview:booking_home_form:to_field").value==""||
			document.getElementById("home_tabview:booking_home_form:date_field").value==""){
		alert("All fields are Mandatory");
	}
}
function onChange(element,id){
	let selectedValue = element.value;
	let select = document.getElementById(id);
	let options = document.getElementsByTagName("LI");
	let i;
	for(i = 0; i < options.length; i++){
		if(options[i].innerHTML == selectedValue){
			options[i].style.display="none";
		}
		else{
			options[i].style.display="block";
		}
	}
}
function updateDialog(id,name,time,price,avlseats){
	alert("Selected bus "+name);
	document.getElementById("booking_form:hiddenbusid").value = id;
	document.getElementById("booking_form:hiddenbusname").value = name;
	document.getElementById("booking_form:hiddenbustime").value = time;
	document.getElementById("booking_form:hiddenbusprice").value = price;
	document.getElementById("booking_form:hiddenbusavlseats").value = avlseats;
	document.getElementById("booking_form:hiddenbutton").click();
//	document.getElementById("booking_form:hiddenbusid").innerHTML = id;
//	document.getElementById("booking_form:hiddenbusname").innerHTML = name;
//	document.getElementById("booking_form:hiddenbustime").innerHTML = time;
//	document.getElementById("booking_form:hiddenbusprice").innerHTML = price;
//	document.getElementById("booking_form:hiddenbusavlseats").innerHTML = avlseats;
}
function clearHistory()
{
     var len = history.length;
     history.go(-len);
     window.location.href = "http://localhost:8080/TicketReservation/faces/login.xhtml";
}