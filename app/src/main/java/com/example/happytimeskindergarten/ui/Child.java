package com.example.happytimeskindergarten.ui;

import java.io.Serializable;
import java.util.List;

public class Child implements Serializable {
    public enum Gender {
        MALE,
        FEMALE
    }
    private int id;
    private String fullName;
    private Gender gender;
    private String birthday;
    private String allergies;
    private String illnesses;
    private String image_data;
    private Integer group_id;
    private String group_name;
    private int family_account_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) { this.gender = gender; }
    public void setGender(String gender) { setGender(Gender.valueOf(gender)); }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getIllnesses() {
        return illnesses;
    }

    public void setIllnesses(String illnesses) {
        this.illnesses = illnesses;
    }

    public String getImage_data() {
        return image_data;
    }

    public void setImage_data(String image_data) {
        this.image_data = image_data;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public int getFamily_account_id() {
        return family_account_id;
    }

    public void setFamily_account_id(int family_account_id) {
        this.family_account_id = family_account_id;
    }

    public Child(ChildData childData)
    {
        setId(childData.getData().getId());
        setFullName(childData.getData().getName());
        setGender(childData.getData().getGender());
        setBirthday(childData.getData().getBirthday());
        setAllergies(childData.getData().getAllergies());
        setIllnesses(childData.getData().getIllnesses());
        setImage_data(childData.getData().getImage_data());
        setGroup_id(childData.getData().getGroup_id());
        setGroup_name(childData.getData().getGroup_name());
        setFamily_account_id(childData.getData().getFamily_account_id());
    }
    public Child() {}
}
