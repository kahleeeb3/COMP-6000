package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        int song_id = Integer.parseInt(request.getParameter("id"));

        HttpSession session = request.getSession();
        MySQLdb db = MySQLdb.getInstance();
        if(session != null) {
            if(session.getAttribute("user") != null) {
                try {
                    UserModel user = (UserModel) session.getAttribute("user");
                    boolean result = db.doReserve(user.getUsername(), song_id);



                    List<MusicModel> musicModelList = db.fetchMusic(999);
                    request.setAttribute("list_of_music", musicModelList);

                    List<AlbumModel> albumModelList = db.fetchAlbums();
                    request.setAttribute("list_of_album", albumModelList);






                    if(result) {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("message", "Success.!");
                        requestDispatcher.forward(request, response);
                    } else {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.jsp");
                        request.setAttribute("message", "Something went wrong! Server error.!");
                        requestDispatcher.forward(request, response);
                    }
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
