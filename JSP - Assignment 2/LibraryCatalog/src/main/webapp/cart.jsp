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


<table>
    <tr>
        <th>Index</th>
        <th>Song name</th>
        <th>Album name</th>
    </tr>
    <c:forEach var="each_reserve" items="${list_of_reserve}" varStatus="i">
        <tr>
            <td>${i.index + 1}</td>
            <td>${each_reserve.getSong_name()}</td>
            <td>${each_reserve.getAlbum_name()}</td>
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