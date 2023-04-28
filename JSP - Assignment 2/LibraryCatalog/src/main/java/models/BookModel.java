package models;

public class BookModel {

    String book_name;
    String topic_name;
    String author_name;
    boolean is_available;
    int book_id;

    public BookModel(String book_name,String topic_name,String author_name, boolean is_available, int book_id) {
        this.book_name = book_name;
        this.topic_name = topic_name;
        this.author_name = author_name;
        this.is_available = is_available;
        this.book_id = book_id;
    }

    public String getBook_name(){
        return book_name;
    }

    public String getTopic_name(){
        return topic_name;
    }
    public  String getAuthor_name(){
        return author_name;
    }
    /*
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
    */
}
