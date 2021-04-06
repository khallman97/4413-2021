var loginBtn = document.getElementById("login"); // get the login button
													// element
var error = document.getElementById("error"); // get the error message element
error.style.display = "none"; // do not show error message

// add click event to login button
loginBtn.addEventListener("click", ()=>{
	if (validate()){
		login();
	}
});

// Check if the username or password field is blank if it is throw a error
function validate() {	
	error.style.display = "none";
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	if (username == "" || password == "") {
		error.style.display = "inline";
		error.style.color = "red";
		error.innerHTML = "Username or Password should not be blank";
		console.log("error");
		return false;
	}else {
		error.style.display = "none";
		return true;
	}
}

// send a rest request to login user if username and password in system
function login() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var request = new XMLHttpRequest();
	var data ='';
	
	// create the query string
	data += "username=" + username + "&password=" + password;
	
	request.open("GET", ("rest/user/login" + "?" + data), true);
	request.onreadystatechange = () => {
		if ((request.readyState == 4) && (request.status == 200)){
		console.log(request.responseText)
		// if login information is correct
		if (request.responseText == "1"){
			console.log("login");
			window.location.href = "/Bookstore/payment?username=" + username;
		} 
		// if password is incorrect
		else {
			console.log("Wrong Password");
			error.style.display = "inline";
			error.style.color = "red";
			error.innerHTML = "Wrong Password Please Try Again";
		}
		} 
		// if username does not exist in database
		else if (request.status == 500){
			error.style.display = "inline";
			error.style.color = "red";
			error.innerHTML = "Username does not exist";
		}
	};
	request.send();

}