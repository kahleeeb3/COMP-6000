package servlets;

import models.MusicModel;
import models.UserModel;
import services.MySQLdb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String submitType = request.getParameter("submitButton");

        if (submitType.equals("Login")) {
            submitLoginForm(request,response);

        }
        else if (submitType.equals("Register")) {
            submitRegisterForm(request, response);
        }
    }
    protected void submitLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the username and password elements
        String username = request.getParameter("login_username");
        String password = request.getParameter("login_password");

        // create an SQL Instance
        MySQLdb db = MySQLdb.getInstance();
        UserModel userModel = null;
        try {
            // try to log in with the provided username and password
            userModel = db.doLogin(username, password);
        }
        catch(SQLException e) {
            // print any errors
            e.printStackTrace();
        }

        // if log in succeeded
        if (userModel != null) {
            // set the user attribute
            HttpSession session = request.getSession();
            session.setAttribute("user", userModel);

            // launch the Fetch Music Servlet
            RequestDispatcher indexPageDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "Login Successful.");
            indexPageDispatcher.forward(request,response);
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/FetchMusicServlet");
            //requestDispatcher.forward(request, response);
        }
        // if log in failed
        else {
            // report the error to the user
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("error", "Incorrect username or password provided.");
            requestDispatcher.forward(request, response);
        }
    }

    protected void submitRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first_name = request.getParameter("register_first_name");
        String last_name = request.getParameter("register_last_name");
        String username = request.getParameter("register_username");
        String password = request.getParameter("register_password");

    }
}