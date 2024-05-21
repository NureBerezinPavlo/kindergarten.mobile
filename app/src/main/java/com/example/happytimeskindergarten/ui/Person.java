package com.example.happytimeskindergarten.ui;
import java.io.Serializable;

public class Person implements Serializable
{
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String imageData;
    private int familyAccountId;

    public int getId() { return id; }
    public void setId(int id) {this.id = id;}
    public String getFullName() {return fullName;}
    public void setFullName(String fullName){this.fullName = fullName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getImageData() {return imageData;}
    public void setImageData(String imageData) {this.imageData = imageData;}
    public int getFamilyAccountId() {return familyAccountId;}
    public void setFamilyAccountId(int familyAccountId){this.familyAccountId = familyAccountId;}

    public Person(){}
    public Person(int id, String fullName, String email, String phoneNumber, String imageData,
                  int familyAccountId) {
        setId(id);
        setFullName(fullName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setImageData(imageData);
        setFamilyAccountId(familyAccountId);
    }
    public Person(TrustedPersonData trustedPersonData)
    {
        setFullName(trustedPersonData.getData().getName());
        setEmail(trustedPersonData.getData().getEmail());
        setPhoneNumber(trustedPersonData.getData().getImage_data());
    }
}