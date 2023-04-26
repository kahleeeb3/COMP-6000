<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sessions and Cookies</title>
</head>
<body>


<form action="LoginServlet" method="post" onsubmit="return validate()">
    Username: <input id="username" name="username" type="text" /> <br/>
    Password: <input id="password" name="password" type="password" /> <br/>
    <input type="submit" name="Login" /> <br/> </br>
    <span id="error_msg"></span>
</form>

<p>${error}</p>


</body>
<script>
function validate() {
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

</html>