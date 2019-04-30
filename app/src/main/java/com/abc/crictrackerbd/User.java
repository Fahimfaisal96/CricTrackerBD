package com.abc.crictrackerbd;

public class User {
    private String email;
    private String name;
    private String isModerator;
    private String token;


    public User() {
    }

    public User(String email, String name, String isModerator,String token) {
        this.email = email;
        this.name = name;
        this.isModerator = isModerator;
        this.token = token;
    }


    public String getEmail() {

        return email;

    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {

        return token;
    }


    public String getName() {
        return name;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setIsModerator(String isModerator) {
        this.isModerator = isModerator;
    }

    public String getIsModerator() {
        return isModerator;
    }
}
