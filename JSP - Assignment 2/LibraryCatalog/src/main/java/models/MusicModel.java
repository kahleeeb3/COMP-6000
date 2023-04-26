package models;

public class MusicModel {

    int song_id;
    int album_id;
    String song_name;
    String album_name;

    public MusicModel(int song_id, int album_id, String song_name, String album_name) {
        this.song_id = song_id;
        this.album_id = album_id;
        this.song_name = song_name;
        this.album_name = album_name;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
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
}
