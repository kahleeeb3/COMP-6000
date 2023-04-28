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
    if (request.getSession() != null) {
        if(session.getAttribute("user") != null) {
            UserModel user = (UserModel) session.getAttribute("user");
%>
<p>Hello, <%= user.getName() %></p>
<p> <a href="LogoutServlet">Logout</a> </p>


<br/>
<br/>
<br/>
<br/>

<p>Your Reservations</p>
<table>
    <tr>
        <td><b>Index</b></td>
        <td><b>Book Name</b></td>
        <td><b>Topic</b></td>
        <td><b>Author</b></td>
        <td><b>Action</b></td>
    </tr>
    <c:forEach var="book" items="${list_of_reserve}" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${book.getBook_name()}</td>
            <td>${book.getTopic_name()}</td>
            <td>${book.getAuthor_name()}</td>
            <td><a href="CartServlet?remove_id=${book.getBook_id()}">Remove Reserve</a></td>
        </tr>
    </c:forEach>
</table>




<%
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "Please login to continue..!!!");
            requestDispatcher.forward(request, response);
        }
    } else {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        request.setAttribute("error", "Please login to continue..!!!");
        requestDispatcher.forward(request, response);
    }
%>


</body>
</html>