var loginBtn = document.getElementById("login");
var error = document.getElementById("error");
error.style.display = "none";

loginBtn.addEventListener("click", ()=>{
	if (validate()){
		login();
	}
});

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
		if (request.responseText == "1"){
			console.log("login");
			window.location.href = "/Bookstore/payment?username=" + username;
		} else {
			console.log("Wrong Password");
			error.style.display = "inline";
			error.style.color = "red";
			error.innerHTML = "Wrong Password Please Try Again";
		}
		}
	};
	request.send();

}