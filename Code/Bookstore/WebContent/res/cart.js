/**
 * 
 */

function removeFromCart(bid) {
	console.log(bid);
	
	var request = new XMLHttpRequest();
	var data ="remove=" + bid;
	request.open("GET", ("./cart" + "?" + data), true);
	request.onreadystatechange = () => {
				console.log("Item Removed to Cart");
				console.log(request.responseText);
	};
	request.send();
	window.location.href = "./cart" ;
	
}