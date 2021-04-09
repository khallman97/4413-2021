displayBook();

// add a review for the book
function submitReview(bid) {
	var request = new XMLHttpRequest();
	console.log("Here");
	var data ='';
	var reviewareaData = document.getElementById("reviewarea").value;
	var ratingareaData = document.getElementById("ratingarea").value;
	
	data += "bid=" +bid + "&review=" +reviewareaData + "&rating=" +ratingareaData;
	request.open("GET", ("../rest/review/create" + "?" + data), true);
		request.onreadystatechange = () => {
			//console.log(request);
			location.reload(); 
			//handler(request);
		};
		request.send();
}

// GET the book from database
function displayBook() {
		var request = new XMLHttpRequest();
		var data ='field=bid&';
		// create the query string
		var bid = window.location.href.substring(window.location.href.lastIndexOf("/") + 1);
		bid = bid.replace(/\?/g,'');
		data += "value=" + bid;
		console.log(bid);
	
		request.open("GET", ("../rest/book/read" + "?" + data), true);
		request.onreadystatechange = () => {
			handler(request);
		};
		request.send();
}	

// update the DOM to display the book
function handler(request){
	console.log(request);
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("result");
		//console.log(request.responseText);
		var books = JSON.parse(request.responseText);
		var html = "<div class=\"books\">";
		
		for (var i = 0; i < books.length; i++){
			//console.log(books[i]);
			html+="<div class=\"card mb-3\">" +
				"<div class=\"row no-gutters\">" + 
				"<div class=\"col-md-4\">" +
				"<img width=\"20px\" class=\"card-img\" src=\"" + books[i].picture_link + "\">" +
				"<span class=\"bid\" style=\"display:none;\">" + books[i].bid + "</span>" +
				"</div><div class=\"col-md-8\"><div class=\"card-body\">" +
					"<h5 class=\"card-title\">" + books[i].title + "</h5>" +
					"<h5 class\"card-text\">By: " + books[i].author + "</h5>" +
					"<h5 class=\"price card-text\">Price: $" + books[i].price + "</h5>" +
					"<h5 class=\"card-text\">Category: " + books[i].category + "</h5>" +
				"</div></div></div>" + 
			"</div>";

		}

		html += "</div>";
		
		target.innerHTML = html;
	}
}