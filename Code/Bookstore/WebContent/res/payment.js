var counter = 0;

function confirmOrder(addr, username) {
	console.log(addr);
	console.log(username);
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
		data += "username=" + username +
			"&status=" + "ORDERED" +
			"&addr=" + addr;
			console.log(data);
			request.open("GET", ("/Bookstore/rest/purchase/addOrder" + "?" + data), true);
			request.onreadystatechange = () => {
				if ((request.readyState == 4) && (request.status == 200)){
					console.log("order comeplete");
				}
				
			};
			request.send();
		
		display.innerHTML = "<p>Order Successfully Completed.</p>";
	}
	
	
	
	
	
}