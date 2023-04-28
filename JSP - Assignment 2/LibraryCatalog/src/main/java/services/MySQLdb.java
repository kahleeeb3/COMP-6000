package services;

import models.TopicModel;
import models.BookModel;
import models.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLdb {
    String url = "jdbc:mysql://localhost:3306/library_catalog";
    String username = "root";
    String password = "mysupersecurepassword";
    Connection connection = null;
    static MySQLdb instance = null;


    public MySQLdb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static synchronized MySQLdb getInstance() {
        if(instance == null) {
            instance = new MySQLdb();
        }
        return instance;
    }

    public UserModel doLogin(String username, String password) throws SQLException {

        // Statement
        String qLogin = "SELECT fname, user_id FROM users WHERE username = '"+ username +"' AND password = '"+ password +"'";

        // query statement
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);

        // get the user model
        UserModel userModel = null;
        if(resultSet.next()) {
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("fname");
            userModel = new UserModel(user_id, name);
        }
        resultSet.close();
        statement.close();
        return userModel;
    }

    public int doRegister(int user_id, String first_name, String last_name, String username, String password) throws SQLException{

        // check if the user exists first
        String qCheck = "SELECT COUNT(*) AS count FROM users WHERE username = '"+ username +"'";
        Statement checkStatement = connection.createStatement();
        ResultSet checkResult = checkStatement.executeQuery(qCheck);
        checkResult.next();
        int count = checkResult.getInt("count");
        if (count > 0) {
            System.err.println("Username already exists");
            checkStatement.close();
            return 1;
        }

        // Statement
        String qRegister = "INSERT INTO users (user_id, fname, lname, username, password) VALUES ("+user_id+", '"+ first_name +"', '"+ last_name +"', '"+ username +"', '"+ password +"')";
        Statement statement = connection.createStatement();

        // execute statement
        try {
            statement.executeUpdate(qRegister);
            return 0;
        } catch (SQLException e) {
            System.err.println("Error executing SQL statement: " + e.getMessage());
            System.err.println("SQL Statement: " + qRegister);
            return -1;
        }

    }

    public List<BookModel> fetchBook(int book_id) throws SQLException {

        // if the id is -1, select all books
        boolean select_all_books = (book_id == -1);

        // Define base SQL Query
        String qGetBook = "SELECT B.book_name, T.topic_name, A.author_name, B.is_available, B.book_id " +
                "FROM library_catalog.books as B, library_catalog.authors as A, library_catalog.topics as T "+
                "WHERE A.author_id = B.author_id AND T.topic_id = B.topic_id";

        if(!select_all_books){
            // SQL Statement to data for all books
            qGetBook += " AND T.topic_id = " + book_id;
        }

        // create prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(qGetBook);
        // get results from query
        ResultSet resultSet = preparedStatement.executeQuery();

        // define book lists
        List<BookModel> list = new ArrayList<>();

        // iterate over SQL results
        while(resultSet.next()) {
            // get values
            String book_name = resultSet.getString("book_name");
            String topic_name = resultSet.getString("topic_name");
            String author_name = resultSet.getString("author_name");
            boolean is_available = resultSet.getBoolean("is_available");
            int book_id_2 = resultSet.getInt("book_id");

            // create book object
            BookModel bookModel = new BookModel(book_name, topic_name, author_name, is_available,book_id_2);

            // add object to list
            list.add(bookModel);
        }
        return list;

        /*
        String qGetMusic = null;
        List<MusicModel> list = new ArrayList<>();
        if(albumid == 999) {
            qGetMusic = "SELECT A.album_name, S.song_name, S.song_id, S.album_id FROM albums as A, songs as S WHERE A.album_id = S.album_id";
        } else {
            qGetMusic = "SELECT A.album_name, S.song_name, S.song_id, S.album_id FROM albums as A, songs as S WHERE A.album_id = S.album_id AND S.album_id = '"+albumid+"'";
        }
        PreparedStatement preparedStatement = connection.prepareStatement(qGetMusic);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int song_id = resultSet.getInt("song_id");
            int album_id = resultSet.getInt("album_id");
            String song_name = resultSet.getString("song_name");
            String album_name = resultSet.getString("album_name");
            MusicModel musicModel = new MusicModel(song_id, album_id, song_name, album_name);

            list.add(musicModel);
        }
        resultSet.close();
        preparedStatement.close();
        */
    }

    public List<TopicModel> fetchTopics() throws SQLException {
        // define topic list
        List<TopicModel> list = new ArrayList<>();
        // SQL Statement to get topic table
        String qGetTopics = "SELECT * FROM topics";
        // get prepared statement
        PreparedStatement preparedStatement = connection.prepareStatement(qGetTopics);
        // get results from query
        ResultSet resultSet = preparedStatement.executeQuery();

        // iterate over returned table
        while(resultSet.next()) {
            int topic_id = resultSet.getInt("topic_id");
            String topic_name = resultSet.getString("topic_name");
            TopicModel topicModel = new TopicModel(topic_id, topic_name);
            list.add(topicModel);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }

    public boolean doReserve(int user_id, int book_id) throws SQLException {
        boolean result = false; // default return

        // check if the reservation already exists
        String qCheck = "SELECT COUNT(*) AS count FROM reservations WHERE user_id = " +user_id+ " AND book_id = " + book_id;
        Statement checkStatement = connection.createStatement();
        ResultSet checkResult = checkStatement.executeQuery(qCheck);
        checkResult.next();
        int count = checkResult.getInt("count");
        if (count > 0) {
            checkStatement.close();
            System.err.println("Username already exists");
            return false;
        }

        // make reservation
        String qDoReserve = "INSERT INTO reservations VALUES("+user_id+","+book_id+");"; // create new reservation
        PreparedStatement preparedStatement = connection.prepareStatement(qDoReserve); // prepare query
        int rows_update = preparedStatement.executeUpdate(); // execute query
        if(rows_update > 0) {
            result = true;
        }
        preparedStatement.close();
        return result;
    }

    public boolean doRemoveReserve(int user_id, int book_id) throws SQLException {
        boolean result = false; // default return

        // Remove reservation
        String qDoRemoveReserve = "DELETE FROM reservations WHERE user_id = "+user_id+" AND book_id = "+book_id+";"; // create new reservation
        PreparedStatement preparedStatement = connection.prepareStatement(qDoRemoveReserve); // prepare query
        int rows_update = preparedStatement.executeUpdate(); // execute query
        if(rows_update > 0) {
            result = true;
        }
        preparedStatement.close();
        return result;
    }

    public List<BookModel> getReservedBooks(int user_id) throws SQLException {
        // query the db
        List<BookModel> list = new ArrayList<>(); // define return list
        String qGetReserved = "SELECT B.book_name, T.topic_name, A.author_name, B.book_id, B.is_available " +
                "FROM books as B, authors as A, topics as T, reservations as R "+
                "WHERE B.author_id = A.author_id AND B.topic_id = T.topic_id AND B.book_id = R.book_id";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetReserved); // query db
        ResultSet resultSet = preparedStatement.executeQuery(); // get results

        // iterate results
        while(resultSet.next()) {
            String book_name = resultSet.getString("book_name");
            String topic_name = resultSet.getString("topic_name");
            String author_name = resultSet.getString("author_name");
            boolean is_available = resultSet.getBoolean("is_available");
            int book_id = resultSet.getInt("book_id");

            BookModel bookModel = new BookModel(book_name,topic_name,author_name,is_available,book_id); // create book object
            list.add(bookModel);
        }
        return list; // return list of books
    }

}
