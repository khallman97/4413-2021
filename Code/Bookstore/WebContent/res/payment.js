var counter = 0;

function confirmOrder(addr, username) {
	console.log(addr);
	console.log(username);
	var request2 = new XMLHttpRequest();
	var request = new XMLHttpRequest();
	
	var data ='';
	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var display = document.getElementById("mainDisplay");
	var cardString = document.getElementById("cardNum").value;
	var CVVString = document.getElementById("CVV").value;
	var expDate =  document.getElementById("expiration").value;

	if (cardString.length < 16) {
		display.innerHTML = "<p>insufficient characters for credit card number.</p>";
	}
	else if (isNaN(cardString)) {
		display.innerHTML = "<p>credit card cannot contain non-numeric values.</p>";
	}
	//else if (CVVString.length < 4) {
	//	display.innerHTML = "<p>insufficient characters for CVV.</p>";
	//}
	else if (isNaN(CVVString)) {
		display.innerHTML = "<p>CVV cannot contain non-numeric values.</p>";
	}
	else if (counter % 3 == 2) {
		display.innerHTML = "<p>Credit Card Authorization Failed.</p>";
	} else {
		
		if(document.getElementById("sameAsBil").checked == false) {
			console.log("here");
			var addrData = '';
			addrData += "street=" + document.getElementById("shipName").value
			 + "&province=" + document.getElementById("province").value
			 + "&country=" +  document.getElementById("Country").value
		     + "&zip=" + document.getElementById("zipcode").value;
			var newAddr = 0;
			request2.open("GET", ("rest/purchase/newAddr" + "?" + addrData), true);
			request2.onreadystatechange = () => {
				if ((request2.readyState == 4) && (request2.status == 200)){
					console.log("okay!");
					console.log(request2.responseText);
					data += "username=" + username +
					"&status=" + "ORDERED" +
					"&addr=" + request2.responseText;
					console.log(data);
					request.open("GET", ("./payment" + "?" + data), true);
					request.onreadystatechange = () => {
						if ((request.readyState == 4) && (request.status == 200)){
							//console.log("order comeplete");
						}
					};
					request.send();
					
				}
			};
			
			//newAddr = request2.responseText;
			request2.send();
			
				
			
		}  else {
			data += "username=" + username +
			"&status=" + "ORDERED" +
			"&addr=" + addr;
			console.log(data);
			request.open("GET", ("./payment" + "?" + data), true);
					request.onreadystatechange = () => {
						if ((request.readyState == 4) && (request.status == 200)){
							//console.log("order comeplete");
						}
			};
			request.send();
		}
		
		
		
		display.innerHTML = "<p>Order Successfully Completed.</p>";
	}
	
}



function disableShipFields() {
	if(document.getElementById('shipName').disabled == false) {
		console.log(document.getElementById("sameAsBil").checked);
		document.getElementById("shipName").disabled = true;
		document.getElementById("phone").disabled = true;
		document.getElementById("shipAddr").disabled = true;
		document.getElementById("province").disabled = true;
		document.getElementById("Country").disabled = true;
		document.getElementById("zipcode").disabled = true;
	} else {
		document.getElementById("shipName").disabled = false;
		document.getElementById("phone").disabled = false;
		document.getElementById("shipAddr").disabled = false;
		document.getElementById("province").disabled = false;
		document.getElementById("Country").disabled = false;
		document.getElementById("zipcode").disabled = false;
	}
	
}