<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="models.UserModel" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sessions and Cookies</title>
</head>
<body>


<%
    boolean sessionExists = (request.getSession() != null);
    boolean userSignedIn = (session.getAttribute("user") != null);
    if (sessionExists && userSignedIn) {
        UserModel user = (UserModel) session.getAttribute("user");
%>
        <p>Hello, <%= user.getName() %>
        </p>
        <p><a href="LogoutServlet">Logout</a></p>
        <p><a href="ReserveServlet">Cart</a></p>
<%} else {%>
        <p>Please <a href="LogoutServlet">Login/Register</a> to reserve a book</p>
<% }
%>
<br/>


<form action="FetchBookServlet">
    Filter Book Topic:
    <select name="topic">
        <option value="-1">All</option>
        <c:forEach var="topic" items="${list_of_topics}">
            <option value="${topic.getTopic_id()}">${topic.getTopic_name()}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Filter" />
</form>



<br/>
<br/>
<br/>
<br/>


        <table>
            <tr>
                <td><b>Book Name</b></td>
                <td><b>Topic</b></td>
                <td><b>Author</b></td>
                <% if (sessionExists && userSignedIn) { %>
                <td><b>Action</b></td>
                <%}%>
            </tr>
            <c:forEach var="book" items="${list_of_books}">
                <tr>
                    <td>${book.getBook_name()}</td>
                    <td>${book.getTopic_name()}</td>
                    <td>${book.getAuthor_name()}</td>
                    <%-- If not logged in, the user should still see the button to reserve
                        the copy which will take them to the log in page upon click.--%>
                    <% if (sessionExists && userSignedIn) { %>
                    <td><a href="CartServlet?id=${book.getBook_id()}">Reserve</a></td>
                    <%} else{%>
                    <td><a href="LogoutServlet">Reserve</a></td>
                    <%}%>
            </tr>
        </c:forEach>
    </table>



<br/>
<br/>
<br/>
<br/>
<p>${message}</p>


</body>
</html>