var category = document.getElementsByName("category");
console.log(category.length);
for (var i = 0; i < category.length; i++) {
	category[i].addEventListener('change', ()=> {
        displayBooks();
    });
}
displayBooks()

function displayBooks() {
		
		var request = new XMLHttpRequest();
		var data ='';
		var category = getCategory();
		
		// create the query string
		if (category == null){
			data += "category=";
		} else {
			data += "category=" + category;
		}
		
		request.open("GET", ("main" + "?" + data), true);
		request.onreadystatechange = () => {
			handler(request);
		};
		request.send();
}

function displaySearchResults() {

		var request = new XMLHttpRequest();
		var data = '';
		var search = document.getElementById("search").value;
		// create the query string
		if (search == null) {
			data += "category=";
		} else {
			data += "category=" + search;
		}
	
		request.open("GET", ("main" + "?" + data), true);
		request.onreadystatechange = () => {
			handler(request);
		};
		request.send();
}	

function handler(request){
	console.log(request);
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("result");
		target.innerHTML = request.responseText;
	}
	addClickEvent();
	addToCart();
}

function getCategory(){
	var category = document.getElementsByName("category");
    for( i = 0; i < category.length; i++ ) {
        if( category[i].checked ) {
            return category[i].value;
        }
    }
    return null;
}

function addClickEvent(){
	var books = document.getElementsByClassName("card");
	for (var i = 0; i < books.length; i++) {
		books[i].addEventListener("click", (e)=> {
			//console.log(e.target.parentNode);
			var bid = e.target.parentNode.querySelector('span').innerHTML;
			console.log("clicked " + bid);
			window.location.href = "/Bookstore/Book/" + bid;
		})
	}
}

function addToCart(){
	var addBtn = document.getElementsByClassName("addToCart");
	var request = new XMLHttpRequest();
	var data;
	for (var i = 0; i < addBtn.length; i++) {
		addBtn[i].addEventListener("click", (e)=> {
			var bid = e.target.parentNode.parentNode.querySelector("span").innerHTML;
			var price = e.target.parentNode.getElementsByClassName("price")[0].innerHTML;
			data ="bid=" + bid;
			//need to refresh cart counter
			request.open("GET", ("rest/cart/add" + "?" + data), true);
			request.onreadystatechange = () => {
				console.log("added to cart");
				console.log(request.responseText);
			};
			request.send();
		});
	};


	
}


