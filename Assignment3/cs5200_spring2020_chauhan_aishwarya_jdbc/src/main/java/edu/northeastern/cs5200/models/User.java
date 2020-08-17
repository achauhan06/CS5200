package edu.northeastern.cs5200.models;


public class User extends Person {

    private Boolean userAgreement;
    private int userId;

    public User(int id, String lastName, String firstName) {
        super(id, lastName, firstName);
    }

    public Boolean getUserAgreement() {
        return userAgreement;
    }

    public void setUserAgreement(Boolean userAgreement) {
        this.userAgreement = userAgreement;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
