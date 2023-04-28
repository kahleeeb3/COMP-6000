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
        /*
        int album_id = 999;
        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if (session != null) {
            if (session.getAttribute("user") != null) {
                try {
                    UserModel user = (UserModel) session.getAttribute("user");
                    List<MusicModel> list = db.getReservedMusic(user.getUsername());

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
                    request.setAttribute("list_of_reserve", list);
                    requestDispatcher.forward(request, response);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
        */
    }
}