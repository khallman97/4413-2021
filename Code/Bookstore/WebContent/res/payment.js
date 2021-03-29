var counter = 0;

function confirmOrder() {
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
	else if (CVVString.length < 4) {
		display.innerHTML = "<p>insufficient characters for CVV.</p>";
	}
	else if (isNaN(CVVString)) {
		display.innerHTML = "<p>CVV cannot contain non-numeric values.</p>";
	}
	else if (counter % 3 == 2) {
		display.innerHTML = "<p>Credit Card Authorization Failed.</p>";
	} else {
		display.innerHTML = "<p>Order Successfully Completed.</p>";
	}
}