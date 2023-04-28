package servlets;

import models.BookModel;
import models.TopicModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FetchBookServlet", value = "/FetchBookServlet")
public class FetchBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //int album_id = 999;
        HttpSession session = request.getSession(); // get session instance
        MySQLdb db = MySQLdb.getInstance(); // get database instance


        // if the album is defined
                /*
                if(request.getParameter("album") != null) {
                    album_id = Integer.parseInt(request.getParameter("album"));
                }
                */


        try {
            // Get List of Books
            List<BookModel> bookModelList = db.fetchBook(100);
            request.setAttribute("list_of_books", bookModelList);

            // Get List of topics
            List<TopicModel> topicModelList = db.fetchTopics();
            request.setAttribute("list_of_topics", topicModelList);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
        requestDispatcher.forward(request, response);
    }
}
