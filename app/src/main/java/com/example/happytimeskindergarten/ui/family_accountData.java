package com.example.happytimeskindergarten.ui;

import java.util.List;

public class family_accountData {
    private Data data;
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Data {
        private int family_account_id;
        private int user_id;
        private String name;
        private String email;
        private int[] child_profiles;
        private int[] trusted_persons;
        private Object image_data;
        private Object phone;

        public int getFamily_account_id() {
            return family_account_id;
        }

        public void setFamily_account_id(int family_account_id) {
            this.family_account_id = family_account_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public int[] getChild_profiles() {
            return child_profiles;
        }

        public void setChild_profiles(int[] child_profiles) {
            this.child_profiles = child_profiles;
        }

        public int[] getTrusted_persons() {
            return trusted_persons;
        }

        public void setTrusted_persons(int[] trusted_persons) {
            this.trusted_persons = trusted_persons;
        }

        public Object getImage_data() {
            return image_data;
        }

        public void setImage_data(Object image_data) {
            this.image_data = image_data;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }
    }
}
