package com.goran.zadatak3;

import java.io.Serializable;

public class Contact implements Serializable {

    private  int id;
    private  String name;
    private  String email;
    private  String address;
    private  String city;
    String phoneNumber;
    private Pol sex;

    public Contact() {

    }

    public Contact(String name, String email, String address, String city, String phoneNumber, Pol sex) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Pol getSex() {
        return sex;
    }

    public void setSex(Pol sex) {
        this.sex = sex;
    }
}
