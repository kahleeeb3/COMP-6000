package servlets;

import models.BookModel;
import models.TopicModel;
import models.UserModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
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
            try {
                MySQLdb db = MySQLdb.getInstance(); // define database
                UserModel user = (UserModel) session.getAttribute("user"); // define user
                boolean result = false;

                // check if user is reserving a book
                String addId = request.getParameter("id");
                if(addId != null){
                    int book_id = Integer.parseInt(addId);
                    result = db.doReserve(user.getUserid(), book_id); // perform reservation

                    // update available book list
                    List<BookModel> bookModelList = db.fetchBook(-1);
                    request.setAttribute("list_of_books", bookModelList);

                    // update available of topics
                    List<TopicModel> topicModelList = db.fetchTopics();
                    request.setAttribute("list_of_topics", topicModelList);

                    // if reserve success
                    if(result) {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("message", "Successfully reserved book.");
                        requestDispatcher.forward(request, response);
                    }
                    // if reserve failure
                    else {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("message", "You have already reserved this book.");
                        requestDispatcher.forward(request, response);
                    }
                }

                // check if user is removing book
                String removeId = request.getParameter("remove_id");
                if (removeId != null) {
                    int remove_book_id = Integer.parseInt(removeId); // get book id
                    result = db.doRemoveReserve(user.getUserid(), remove_book_id); // perform reservation

                    // update available book list
                    List<BookModel> bookModelList = db.fetchBook(-1);
                    request.setAttribute("list_of_books", bookModelList);

                    // update available of topics
                    List<TopicModel> topicModelList = db.fetchTopics();
                    request.setAttribute("list_of_topics", topicModelList);

                    // if reserve success
                    if(result) {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("message", "Successfully removed reserved book.");
                        requestDispatcher.forward(request, response);
                    }
                    // if reserve failure
                    else {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("message", "Couldn't remove reserved this book.");
                        requestDispatcher.forward(request, response);
                    }
                }



            }
            catch(SQLException e){
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
