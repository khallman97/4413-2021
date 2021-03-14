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

function handler(request){
	console.log(request);
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("result");
		target.innerHTML = request.responseText;
	}
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
