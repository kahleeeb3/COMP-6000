<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sessions and Cookies</title>
</head>
<body>

<p>Login</p>
<form action="LoginServlet" method="post" onsubmit="return validate_login()">
    Username: <input id="username" name="username" type="text" /> <br/>
    Password: <input id="password" name="password" type="password" /> <br/>
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
    Confirm Password: <input id="confirm_register_password" name="confirm_register_password" type="password" /> <br/>
    <input type="submit" name="submitButton" value="Register" /> <br/> </br>
    <span id="register_error_msg"></span>
</form>
<p>${register_error}</p>

</body>
<script>
function validate_login() {
    let result = true; // store if username and password were passed correctly.
    const username = document.getElementById("username").value
    const password = document.getElementById("password").value

    if(username === "") {
        document.getElementById("error_msg").innerHTML = "Please enter a username."
        result = false;
    } else if(password === "") {
        document.getElementById("error_msg").innerHTML = "Please enter a password."
        result = false;
    }
    return result; // return if result is true or false
}
</script>

<script>
    function validate_register() {
        let result = true;
        return result;
    }
</script>

</html>