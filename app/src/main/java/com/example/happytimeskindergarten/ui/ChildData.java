package com.example.happytimeskindergarten.ui;

import java.util.List;

public class ChildData {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        private int id;
        private String name;
        private String gender;
        private String birthday;
        private String allergies;
        private String illnesses;
        private String image_data;
        private List<Attendance> attendances;
        private int group_id;
        private String group_name;
        private int family_account_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

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

        public List<Attendance> getAttendances() {
            return attendances;
        }

        public void setAttendances(List<Attendance> attendances) {
            this.attendances = attendances;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
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

        class Attendance {
            private int id;
            private String date;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
