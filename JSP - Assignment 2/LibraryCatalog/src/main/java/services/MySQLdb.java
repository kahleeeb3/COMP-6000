package services;

import models.AlbumModel;
import models.MusicModel;
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
        String qLogin = "SELECT fname FROM users WHERE username = '"+ username +"' AND password = '"+ password +"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(qLogin);

        // get the user model
        UserModel userModel = null;
        if(resultSet.next()) {
            String name = resultSet.getString("fname");
            userModel = new UserModel(username, name);
        }
        resultSet.close();
        statement.close();
        return userModel;
    }

    public List<MusicModel> fetchMusic(int albumid) throws SQLException {
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

        return list;
    }

    public List<AlbumModel> fetchAlbums() throws SQLException {
        List<AlbumModel> list = new ArrayList<>();
        String qGetAlbums = "SELECT * FROM albums";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetAlbums);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int album_id = resultSet.getInt("album_id");
            String album_name = resultSet.getString("album_name");
            AlbumModel albumModel = new AlbumModel(album_id, album_name);
            list.add(albumModel);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }


    // WHEN USING INSERT/UPDATE/DELETE --> executeUpdate()
    // SELECT --> executeQuery()
    public boolean doReserve(String email, int song_id) throws SQLException {
        boolean result = false;
        String qDoReserve = "INSERT INTO reserve VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(qDoReserve);
        preparedStatement.setString(1, email);
        preparedStatement.setInt(2, song_id);
        int rows_update = preparedStatement.executeUpdate();
        if(rows_update > 0) {
            result = true;
        }
        preparedStatement.close();
        return result;
    }

    public List<MusicModel> getReservedMusic(String email) throws SQLException {
        List<MusicModel> list = new ArrayList<>();
        String qGetReserved = "SELECT S.song_id, A.album_id, S.song_name, A.album_name FROM songs as S, albums as A, reserve as R WHERE R.song_id = S.song_id AND S.album_id = A.album_id AND R.email = '"+email+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(qGetReserved);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            int album_id = resultSet.getInt("album_id");
            String album_name = resultSet.getString("album_name");
            int song_id = resultSet.getInt("song_id");
            String song_name = resultSet.getString("song_name");
            MusicModel musicModel = new MusicModel(song_id, album_id, song_name, album_name);
            list.add(musicModel);
        }
        resultSet.close();
        preparedStatement.close();
        return list;
    }
}
