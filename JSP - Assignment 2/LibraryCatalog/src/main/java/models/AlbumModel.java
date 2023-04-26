package models;

public class AlbumModel {
    int album_id;
    String album_name;

    public AlbumModel(int album_id, String album_name) {
        this.album_id = album_id;
        this.album_name = album_name;
    }

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }
}
