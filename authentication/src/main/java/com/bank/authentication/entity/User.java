package com.bank.authentication.entity;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "users")
@Table(name = "users")
public class User implements Serializable{

    @Id
    private int id;
    private String username;
    private String password;
    @Column(name="accbalance")
    private double accBalance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }
}
