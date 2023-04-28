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
            <p> <a href="ReserveServlet">Cart</a> </p>
<br/>


<form action="FetchMusicServlet">
    Filter Book Topic:
    <select name="topic">
        <option value="999">All</option>
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
                <th>Book Name</th>
                <th>Topic</th>
                <th>Author</th>
                <th>Action</th>
            </tr>
            <c:forEach var="book" items="${list_of_books}">
                <tr>
                    <td>${book.getBook_name()}</td>
                    <td>${book.getTopic_name()}</td>
                    <td>${book.getAuthor_name()}</td>
                    <td>Temp</td>
                <%--<td><a href="CartServlet?id=${each_music.getSong_id()}">Reserve</a></td>--%>
            </tr>
        </c:forEach>
    </table>



<br/>
<br/>
<br/>
<br/>
<p>${message}</p>


<%
        }
        else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "Please login to continue..!!!");
            requestDispatcher.forward(request, response);
        }
    }

    else {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        request.setAttribute("error", "Please login to continue..!!!");
        requestDispatcher.forward(request, response);
    }
%>


</body>
</html>