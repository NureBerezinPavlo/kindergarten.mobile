package com.example.happytimeskindergarten.ui;

import com.google.gson.annotations.SerializedName;

public class attendancesData {
    @SerializedName("error")
    String error;
    @SerializedName("message")
    String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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
