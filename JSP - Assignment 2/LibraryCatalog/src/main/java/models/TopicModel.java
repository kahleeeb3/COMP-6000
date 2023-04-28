package models;

public class TopicModel {
    int topic_id;
    String topic_name;

    public TopicModel(int topic_id, String topic_name) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }

    public int getTopic_id(){
        return topic_id;
    }

    public String getTopic_name(){
        return topic_name;
    }

    /*
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
    */
}
