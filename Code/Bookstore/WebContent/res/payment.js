var counter = 0;

function confirmOrder() {
	var display = document.getElementById("mainDisplay")
	if (counter % 3 == 2) {
		display.innerHTML = "<p>Credit Card Authorization Failed.</p>"
	} else {
		display.innerHTML = "<p>Order Successfully Completed.</p>"

	}
}