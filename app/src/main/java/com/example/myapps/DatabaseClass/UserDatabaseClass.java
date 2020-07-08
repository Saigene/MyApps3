package com.example.myapps.DatabaseClass;

public class UserDatabaseClass {
String Username;
String UserphoneNo;
String Userid;
String Useremail;

public UserDatabaseClass(){

    }
    public UserDatabaseClass(String username, String userphoneNo, String userid, String useremail) {
        Username = username;
        UserphoneNo = userphoneNo;
        Userid = userid;
        Useremail=useremail;
    }

    public String getUseremail(){
    return Useremail;
    }
    public String getUsername() {
        return Username;
    }

    public String getUserphoneNo() {
        return UserphoneNo;
    }

    public String getUserid() {
        return Userid;
    }
}
