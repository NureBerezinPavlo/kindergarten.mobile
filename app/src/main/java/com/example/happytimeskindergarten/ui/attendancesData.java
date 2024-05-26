package com.example.happytimeskindergarten.ui;

public class attendancesData {
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        String[] date;

        public String[] getDate() {
            return date;
        }

        public void setDate(String[] date) {
            this.date = date;
        }
    }
}
