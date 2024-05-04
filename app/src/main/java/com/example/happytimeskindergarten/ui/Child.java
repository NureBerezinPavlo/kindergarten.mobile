package com.example.happytimeskindergarten.ui;

import java.io.Serializable;

public class Child implements Serializable {
    public enum Gender {
        MALE,
        FEMALE
    }
    public String fullName;
    public Gender gender;

    public Child(String fullName, Gender gender)
    {
        this.fullName = fullName;
        this.gender = gender;
    }
}
