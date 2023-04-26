package models;

public class UserModel {

    String username, fname;

    public UserModel(String username, String fname) {
        this.username = username;
        this.fname = fname;
    }

    public String getName() {
        return fname;
    }

    public void setName(String Newfname) {this.fname = Newfname;}
    public String getUsername() {
        return username;
    }

    public void setUsername(String NewUsername) {this.username = NewUsername;}

    //public String getEmail() {return email;} // not storing email
    //public void setEmail(String email) {this.email = email;} // not storing email
}
