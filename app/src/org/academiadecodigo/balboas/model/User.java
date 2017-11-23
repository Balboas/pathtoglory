package org.academiadecodigo.balboas.model;

/**
 * Created by codecadet on 07/11/17.
 */
public class User {

    private String userName;
    private String passWord;
    private String email;

    public User(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User {"+
                "User =" + userName + '\'' +
                ",Email ='"+email + '\'' +
                ",Pass ='"+ passWord + '\'' +
                '}';
    }
}
