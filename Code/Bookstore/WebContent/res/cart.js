/**
 * 
 */

function removeFromCart(bid) {
	console.log(bid);
	
	var request = new XMLHttpRequest();
	var data ="bid=" + bid;
	request.open("GET", ("/Bookstore/rest/cart/remove" + "?" + data), true);
	request.onreadystatechange = () => {
				console.log("Item Removed to Cart");
				console.log(request.responseText);
	};
	request.send();
	window.location.href = "/Bookstore/cart" ;
	
}