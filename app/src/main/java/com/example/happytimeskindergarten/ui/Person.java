package com.example.happytimeskindergarten.ui;
import java.io.Serializable;

public class Person implements Serializable
{
    public String fullName, email, phoneNumber;

    public Person(String fullName, String email, String phoneNumber)
    {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}