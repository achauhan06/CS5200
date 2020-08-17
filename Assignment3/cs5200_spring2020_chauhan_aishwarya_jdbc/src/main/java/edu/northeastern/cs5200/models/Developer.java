package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Developer extends Person {

    private String developerKey;
    private int developerId;

    public Developer(){
        super();
    };

    public Developer(String developerKey, int id, String lastName, String firstName, String username, String password,
                     String email, Date dob, Collection<Address> addresses, Collection<Phone> phones) {
        super(id, lastName, firstName, username,  password, email, dob, addresses, phones);
        this.developerKey = developerKey;
    }

    public Developer(String developerKey, int id, String lastName, String firstName) {
        super(id, lastName, firstName);
        this.developerKey = developerKey;
    }

    public Developer(String developerKey, int id, String lastName, String firstName, String username, String password, String email, Date dob) {
        super(id, lastName, firstName, username,  password, email, dob);
        this.developerKey = developerKey;
        this.developerId = id;
    }

    public String getDeveloperKey() {
        return developerKey;
    }

    public void setDeveloperKey(String developerKey) {
        this.developerKey = developerKey;
    }


    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int id) {
        this.developerId = id;
    }


}
