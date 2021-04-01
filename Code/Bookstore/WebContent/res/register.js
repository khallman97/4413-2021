var registerBtn = document.getElementById("register"); // get the register button element
var error = document.getElementById("error"); // get the error message element
error.style.display = "none"; // do not show error message

//add click event to register button
registerBtn.addEventListener("click", ()=>{
	if (validate()){
		register();
	}
});

// check if any field is blank or password and confirmation password match. Throw relevant error message
function validate() {	
	error.style.display = "none";
	var name = document.getElementById("name").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var confirmPassword = document.getElementById("confirmPassword").value;
	var address = document.getElementById("address").value;
	console.log(username);

	if (username == "" || password == "" || name == "" || confirmPassword == "" || address == "") {
		error.style.display = "inline";
		error.style.color = "red";
		error.innerHTML = "One or more field is missing";
		return false;
	}
	else if (password != confirmPassword){
		error.style.display = "inline";
		error.style.color = "red";
		error.innerHTML = "Passwords do not match";
		return false;
	}
	else {
		error.style.display = "none";
		return true;
	}
}

// Create the user account
function register() {
	var name = document.getElementById("name").value;
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var address = document.getElementById("address").value;
	var request = new XMLHttpRequest();
	var data ='';
	
	// create the query string
	data += "name=" + name +
			"&username=" + username + 
			"&password=" + password +
			"&addr=" + address;
	
	request.open("GET", ("rest/user/add" + "?" + data), true);
	request.onreadystatechange = () => {
		if ((request.readyState == 4) && (request.status == 200)){
			console.log("Account Created");
		}
		
	};
	request.send();
	window.location.href = "/Bookstore/payment?username=" + username;

}