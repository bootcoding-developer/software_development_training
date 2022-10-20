package com.online.restaurant;

public class Customer {

    // To declare variable
    // DataType variableName;
    private String name;
    private String address;
    private long phoneNumber;
    private String city;
    private String state;
    private String emailId;


    // Getters
    // To get the value of a variable
    // Setters
    // To set the value of a variable

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
