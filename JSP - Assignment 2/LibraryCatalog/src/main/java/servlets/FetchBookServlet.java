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

        boolean topic_defined = (request.getParameter("topic") != null);
        int topic_id = -1; // default to all topics (default for first page load)

        // if the topic is defined (page is already loaded)
        if (topic_defined) {
            topic_id = Integer.parseInt(request.getParameter("topic"));
        }

        HttpSession session = request.getSession(); // get session instance
        MySQLdb db = MySQLdb.getInstance(); // get database instance

        try {
            // Get List of Books
            List<BookModel> bookModelList = db.fetchBook(topic_id);
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
