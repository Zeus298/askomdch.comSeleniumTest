package org.selenium.pom.objects;

import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

public class UserLogin {
    private String userName;
    private String passWord;
    private int id;

    private String gmail;


    public UserLogin () {}
    public UserLogin(int id) throws IOException {
        UserLogin [] userLogin = JacksonUtils.coventStringToObjectJson("userAndPassword.json", UserLogin[].class);
        for (UserLogin user : userLogin) {
            if (user.getId() == id) {
                this.userName = user.getUserName();
                this.passWord = user.getPassWord();
            }
        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

}
