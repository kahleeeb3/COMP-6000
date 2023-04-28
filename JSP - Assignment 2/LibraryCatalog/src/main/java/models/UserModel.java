package models;

public class UserModel {

    int user_id;
    String fname;

    public UserModel(int user_id, String fname) {
        this.user_id = user_id;
        this.fname = fname;
    }

    public String getName() {
        return fname;
    }

    public void setName(String Newfname) {this.fname = Newfname;}
    public int getUserid() {
        return user_id;
    }

    public void setUserid(int user_id) {this.user_id = user_id;}

    //public String getEmail() {return email;} // not storing email
    //public void setEmail(String email) {this.email = email;} // not storing email
}
