var login = new Login(), logout = new Logout(), user = new User(), send = new SendReceiveService();

function Login() {
	"use strict";
	console.log("Login()" + this);
	this.sendLogin = function(button) {
		if (document.getElementById("loginButton") === button)
			;
		else {
			console.log("function invoked in an unintended way");
			return null;
		}
		try {
			var password = document.getElementById("password").value;
			// var encPassword = Base64.encode(passwordInClearArr);

			var messageToSend = JSON.stringify({
				"modelId" : 100,
				"userName" : document.getElementById("userid").value,
				"password" : password,
				"status" : [ {
					"id" : 1,
					"isActive" : document.getElementById("active").checked
				}, {
					"id" : 2,
					"isActive" : document.getElementById("deactive").checked
				} ],
				"channel" : document.getElementById("channel").value,
				"transactionId" : Math.random(Date) * 100000000000000000
			});
			send.send(messageToSend);
		} catch (e) {
			alert(e);
			console
					.log("login function: Exception while sending request: "
							+ e);
		}
	}
} // Login
function User() {
	"use strict";
	console.log("Login()" + this);
	this.addUser = function(button) {
		console.log("addUser()" + button.id);
		if (document.getElementById("addUser") === button)
			;
		else {
			console.log("function invoked in an unintended way");
			return null;
		}
		var messageToSend = JSON
				.stringify({
					"modelId" : 101,
					"channel" : document.getElementById("channel").value,
					"transactionId" : Math.random(Date) * 100000000000000000,
					"userName" : document.getElementById("userid").value,
					"userDetails" : {
						"firstName" : document
								.getElementById("addUserFirstName").value,
						"lastName" : document.getElementById("addUserLastName").value,
						"userName" : document.getElementById("addUserUserName").value,
						"primaryEmail" : document
								.getElementById("addUserPrimaryEmail").value,
						"secondaryEmail" : document
								.getElementById("addUserSecondaryEmail").value,
						"mobileNumber" : document
								.getElementById("addUserMobile").value,
						"Designation" : document
								.getElementById("addUserDesignation").value
					}
				});
		send.send(messageToSend);
	}
	this.getAllUsersDetails = function(button) {
		console.log("getAllUsersDetails()" + button.id);
		if (document.getElementById("getUserDetailsList") === button)
			;
		else {
			console.log("function invoked in an unintended way");
			return null;
		}
		var messageToSend = JSON.stringify({
			"modelId" : 102,
			"channel" : document.getElementById("channel").value,
			"transactionId" : Math.random(Date) * 100000000000000000,
			"userName" : document.getElementById("userid").value
		});
		send.send(messageToSend);
	}

	this.updateUsers = function(button) {
		console.log("updateUsers()" + button.id);
		if (document.getElementById("updateUser") === button)
			;
		else {
			console.log("function invoked in an unintended way");
			return null;
		}
		var messageToSend = JSON
				.stringify({
					"modelId" : 103,
					"channel" : document.getElementById("channel").value,
					"transactionId" : Math.random(Date) * 100000000000000000,
					"userName" : document.getElementById("userid").value,
					"updateUsersList" : {
						"userId" : document.getElementById("updateUserId").value,
						"firstName" : document
								.getElementById("updateUserFirstName").value,
						"lastName" : document
								.getElementById("updateUserLastName").value,
						"userName" : document
								.getElementById("updateUserUserName").value,
						"primaryEmail" : document
								.getElementById("updateUserPrimaryEmail").value,
						"secondaryEmail" : document
								.getElementById("updateUserSecondaryEmail").value,
						"mobileNumber" : document
								.getElementById("updateUserMobile").value,
						"Designation" : document
								.getElementById("updateUserDesignation").value
					}

				});
		send.send(messageToSend);
	}
	this.getUserDetailsById = function(button) {
		console.log("getUserDetailsById()" + button.id);
		if (document.getElementById("getUserDetailsButton") === button)
			;
		else {
			console.log("function invoked in an unintended way");
			return null;
		}
		var messageToSend = JSON.stringify({
			"modelId" : 104,
			"channel" : document.getElementById("channel").value,
			"transactionId" : Math.random(Date) * 100000000000000000,
			"userName" : document.getElementById("userid").value,
			"userId" : document.getElementById("getUserDetailsId").value
		});
		send.send(messageToSend);
	}
	this.getUsersByFilterId = function(button) {
		console.log("getUsersByFilterId()" + button.id);
		if (document.getElementById("getUsersByFilterId") === button)
			;
		else {
			console.log("function invoked in an unintended way");
			return null;
		}
		var messageToSend = JSON.stringify({
			"modelId" : 105,
			"channel" : document.getElementById("channel").value,
			"transactionId" : Math.random(Date) * 100000000000000000,
			"userName" : document.getElementById("userid").value,
			"filterIdList" : document.getElementById("getUsersFilterId").value
					.split(",")
		});
		send.send(messageToSend);
	}

} // User()
function Logout() {
	"use strict";
	console.log("Logout()" + this);
	try {
		this.logout = function(button) {
			console.log("logout()" + button.id);
			if (document.getElementById("logoutButton") === button)
				;
			else {
				console.log("function invoked in an unintended way");
				return null;
			}
			var messageToSend = JSON.stringify({
				"modelId" : "300",
				"channel" : document.getElementById("channel").value,
				"transactionId" : Math.random(Date) * 100000000000000000
			});
			send.send(messageToSend);
		}
	} catch (e) {
		alert(e);
		console.log(e);
	}
} // LogOut()
function SendReceiveService() {
	"use strict";
	console.log("SendReceiveService()" + this);
	var xmlhttp = new XMLHttpRequest;
	this.send = function(messageToSend) {
		console.log("send()" + this);
		xmlhttp.open("POST",
				"http://localhost:8888/LoginUpdateGetAddDetails/login");
		xmlhttp.setRequestHeader("Content-Type",
				"application/json;charset=UTF-8");
		console.log("message being sent is: " + messageToSend);
		xmlhttp.onreadystatechange = function() {
			send.receive();
		} // xmlhttp.onready
		xmlhttp.send(messageToSend);
	} // this.send
	// sr.send(messageToSend);
	this.receive = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var obj = JSON.parse(xmlhttp.responseText);
			console.log("Response: " + xmlhttp.responseText);
			alert(xmlhttp.responseText);
		} else if (xmlhttp.readyState == 4 && xmlhttp.status != 200) {
			alert("Somehing went wrong. xmlhttp.status: " + xmlhttp.status);
			console.log("Some went wrong while retrieving http response: "
					+ xmlhttp.status);
		} else {
			console.log("xmlhttp state change. xmlhttp.status: "
					+ xmlhttp.status + " xmlhttp.readyState: "
					+ xmlhttp.readyState);
		}
	} // this.receive
} // sendReceive()
