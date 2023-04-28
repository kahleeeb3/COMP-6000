<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sessions and Cookies</title>
</head>
<body>

<p>Login</p>
<form action="LoginServlet" method="post" onsubmit="return validate_login()">
    Username: <input id="login_username" name="login_username" type="text" /> <br/>
    Password: <input id="login_password" name="login_password" type="password" /> <br/>
    <input type="submit" name="submitButton" value="Login"/> <br/> </br>
    <span id="error_msg"></span>
</form>
<p>${error}</p>


<p>Register</p>
<form action="LoginServlet" method="post" onsubmit="return validate_register()">
    First Name: <input id="register_first_name" name="register_first_name" type="text" /> <br/>
    Last Name: <input id="register_last_name" name="register_last_name" type="text" /> <br/>
    Username: <input id="register_username" name="register_username" type="text" /> <br/>
    Password: <input id="register_password" name="register_password" type="password" /> <br/>
    Confirm Password: <input id="register_confirm_password" name="confirm_register_password" type="password" /> <br/>
    <input type="submit" name="submitButton" value="Register" /> <br/> </br>
    <span id="register_error_msg"></span>
</form>
<p>${register_error}</p>

<p><a href="FetchBookServlet">Browse Catalog Without Sign In</a></p>

</body>
<script>
    function validate_login() {
        let result = true; // store if username and password were passed correctly.
        const username = document.getElementById("login_username").value
        const password = document.getElementById("login_password").value

        if(username === "") {
            document.getElementById("error_msg").innerHTML = "Please enter a username.";
            result = false;
        } else if(password === "") {
            document.getElementById("error_msg").innerHTML = "Please enter a password.";
            result = false;
        }
        return result; // return if result is true or false
    }

    function validate_register() {
        let result = true;

        const first_name = document.getElementById("register_first_name").value
        const last_name = document.getElementById("register_last_name").value
        const username = document.getElementById("register_username").value
        const password = document.getElementById("register_password").value
        const confirm_password = document.getElementById("register_confirm_password").value

        let error_elm = document.getElementById("register_error_msg");

        // check valid input
        if(first_name === "") {
            error_elm.innerHTML = "Please enter a first name.";
            result = false;
        }
        else if(last_name === "") {
            error_elm.innerHTML = "Please enter a last name.";
            result = false;
        }
        else if(username === "") {
            error_elm.innerHTML = "Please enter a username.";
            result = false;
        }
        else if(password === "") {
            error_elm.innerHTML = "Please enter a password."
            result = false;
        }
        else if(password !== confirm_password) {
            error_elm.innerHTML = "Passwords do not match."
            result = false;
        }
        return result;
    }
</script>

</html>