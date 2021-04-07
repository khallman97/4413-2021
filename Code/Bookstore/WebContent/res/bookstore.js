var category = document.getElementsByName("category"); // get all radio buttons with the name category
console.log(category.length);

// add an change event listener to each radio button
for (var i = 0; i < category.length; i++) {
	category[i].addEventListener('change', ()=> {
        displayBooks();
    });
}
displayBooks()

// GET the books with the specific category
function displayBooks() {
		
		var request = new XMLHttpRequest();
		var data ='field=category&';
		var category = getCategory();
		
		// create the query string
		if (category == null){
			data += "value=";
		} else {
			data += "value=" + category;
		}
		
		request.open("GET", ("rest/book/read" + "?" + data), true);
		request.onreadystatechange = () => {
			handler(request);
		};
		request.send();
}

// GET the books that match the title name
function displaySearchResults() {

		var request = new XMLHttpRequest();
		var data ='field=title&';
		var search = document.getElementById("search").value;
		// create the query string
		if (search == null) {
			data += "value=";
		} else {
			data += "value=" + search;
		}
	
		request.open("GET", ("rest/book/read" + "?" + data), true);
		request.onreadystatechange = () => {
			handler(request);
		};
		request.send();
}	

// Update the DOM to display the books
function handler(request){
	console.log(request);
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("result");
		console.log(request.responseText);
		var books = JSON.parse(request.responseText);
		var html = "<div class=\"books row\">";
		
		for (var i = 0; i < books.length; i++){
			console.log(books[i]);
			html+="<div class=\"card col-4\">" +
			"<img class=\"card-img-top hover-shadow\" src=\"" + books[i].picture_link + "\">" +
			"<span class=\"bid\" style=\"display:none;\">" + books[i].bid + "</span>" +
			"<div class=\"card-body\">" +
			"<h5 class=\"card-title\">" + books[i].title + "</h5>" +
			"<h5 class\"card-text\">By: " + books[i].author + "</h5>" +
			"<h5 class=\"price card-text\">Price: $" + books[i].price + "</h5>" +
			"<h5 class=\"card-text\">Category: " + books[i].category + "</h5>" +
			 "<button class=\"btn btn-primary addToCart\" type=\"button\">Add To Shopping Cart</button>" +
			"</div>" + 
			"</div>";

		}

		html += "</div>";
		
		target.innerHTML = html;
	}
	addClickEvent();
	addToCart();
}

// get the category from the radio button that was checked
function getCategory(){
	var category = document.getElementsByName("category");
    for( i = 0; i < category.length; i++ ) {
        if( category[i].checked ) {
            return category[i].value;
        }
    }
    return null;
}

// add click event to each book card, sends the user to the page of the book item
function addClickEvent(){
	var books = document.getElementsByClassName("card");
	for (var i = 0; i < books.length; i++) {
		books[i].addEventListener("click", (e)=> {
			//console.log(e.target.parentNode);
			var bid = e.target.parentNode.querySelector('span').innerHTML;
			console.log("clicked " + bid);
			window.location.href = "./Book/" + bid;
		})
	}
}

// add the book to the cart if button pressed
function addToCart(){
	var addBtn = document.getElementsByClassName("addToCart");
	var counter = document.getElementById("counter");
	var request = new XMLHttpRequest();
	var data;
	for (var i = 0; i < addBtn.length; i++) {
		addBtn[i].addEventListener("click", (e)=> {
			var bid = e.target.parentNode.parentNode.querySelector("span").innerHTML;
			var price = e.target.parentNode.getElementsByClassName("price")[0].innerHTML;
			data = "cart=" +bid;
			//need to refresh cart counter
			request.open("GET", ("./main" + "?" + data), true);
			request.onreadystatechange = () => {
				if ((request.readyState == 4) && (request.status == 200)){
					console.log("added to cart");
					counter.innerHTML = parseInt(counter.innerHTML) + 1;
					//console.log(request.responseText);
				}
			};
			request.send();
		});
	};


	
}


