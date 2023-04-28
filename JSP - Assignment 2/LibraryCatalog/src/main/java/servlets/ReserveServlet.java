package servlets;

import models.BookModel;
import models.UserModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ReserveServlet", value = "/ReserveServlet")
public class ReserveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(); // get session instance
        boolean sessionExists = (request.getSession() != null); // bool for if session exits
        boolean userSignedIn = (session.getAttribute("user") != null); // bool for if user is signed in

        // if user is signed in and session exists
        if (sessionExists && userSignedIn) {

            UserModel user = (UserModel) session.getAttribute("user"); // get user
            int user_id = user.getUserid(); // get user id

            // use user_id to get a list of books
            MySQLdb db = MySQLdb.getInstance(); // define db
            try {
                List<BookModel> list = db.getReservedBooks(user_id); // get reserved books from user_id
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
                request.setAttribute("list_of_reserve", list);
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // if user is not signed in or session expires
        else{
            // ask user to sign in
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "You must be logged in to reserve a book.");
            requestDispatcher.forward(request, response);
        }
    }
}