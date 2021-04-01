displayBook();

// GET the book from database
function displayBook() {
		var request = new XMLHttpRequest();
		var data ='field=bid&';
		// create the query string
		data += "value=" + window.location.href.substring(window.location.href.lastIndexOf("/") + 1);
		
	
		request.open("GET", ("/Bookstore/rest/book/read" + "?" + data), true);
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
		console.log(request.responseText);
		var books = JSON.parse(request.responseText);
		var html = "<div class=\"books\">";
		
		for (var i = 0; i < books.length; i++){
			console.log(books[i]);
			html+="<div class=\"card\">" +
				"<img class=\"card-img-top\" src=\"" + books[i].picture_link + "\">" +
				"<span class=\"bid\" style=\"display:none;\">" + books[i].bid + "</span>" +
				"<div class=\"card-body\">" +
					"<h5 class=\"card-title\">" + books[i].title + "</h5>" +
					"<h5 class\"card-text\">" + books[i].author + "</h5>" +
					"<h5 class=\"price card-text\">" + books[i].price + "</h5>" +
					"<h5 class=\"card-text\">" + books[i].category + "</h5>" +
				"</div>" + 
			"</div>";

		}

		html += "</div>";
		
		target.innerHTML = html;
	}
}