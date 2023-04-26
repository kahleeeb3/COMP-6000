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
<p> <a href="ReserveServlet">Cart</a> </p>
<br/>
<br/>


<form action="FetchMusicServlet">
    Filter Songs:
    <select name="album">
        <option value="999">All</option>
        <c:forEach var="each_album" items="${list_of_album}">
            <option value="${each_album.getAlbum_id()}">${each_album.getAlbum_name()}</option>
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
                <th>Song id</th>
                <th>Song name</th>
                <th>Album name</th>
                <th>Action</th>
            </tr>
            <c:forEach var="each_music" items="${list_of_music}">
                <tr>
                    <td>${each_music.getSong_id()}</td>
                    <td>${each_music.getSong_name()}</td>
                    <td>${each_music.getAlbum_name()}</td>
                    <td><a href="CartServlet?id=${each_music.getSong_id()}">Reserve</a></td>
                </tr>
            </c:forEach>
        </table>



<br/>
<br/>
<br/>
<br/>
<p>${message}</p>


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