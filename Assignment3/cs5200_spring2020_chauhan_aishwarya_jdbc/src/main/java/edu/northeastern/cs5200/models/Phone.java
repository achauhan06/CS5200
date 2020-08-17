package edu.northeastern.cs5200.models;

public class Phone {

    private int id;
    private String phone;
    private Boolean primary;

    public Phone(String phone, Boolean primary) {
        this.phone = phone;
        this.primary = primary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getPrimary() {
        return primary;
    }

    public void setPrimary(Boolean primary) {
        this.primary = primary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
