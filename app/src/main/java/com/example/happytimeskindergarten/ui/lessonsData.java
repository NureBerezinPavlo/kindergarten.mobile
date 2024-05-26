package com.example.happytimeskindergarten.ui;

import com.google.gson.annotations.SerializedName;

public class lessonsData {
    @SerializedName("1")
    Data[] monday;
    @SerializedName("2")
    Data[] Tuesday;
    @SerializedName("3")
    Data[] Wednesday;

    @SerializedName("4")
    Data[] Thursday;
    @SerializedName("5")
    Data[] Friday;
    @SerializedName("6")
    Data[] Saturday;
    @SerializedName("7")
    Data[] Sunday;
    public Data[] getMonday() {
        return monday;
    }

    public void setMonday(Data[] monday) {
        this.monday = monday;
    }

    public Data[] getTuesday() {
        return Tuesday;
    }

    public void setTuesday(Data[] tuesday) {
        Tuesday = tuesday;
    }

    public Data[] getWednesday() {
        return Wednesday;
    }

    public void setWednesday(Data[] wednesday) {
        Wednesday = wednesday;
    }

    public Data[] getThursday() {
        return Thursday;
    }

    public void setThursday(Data[] thursday) {
        Thursday = thursday;
    }

    public Data[] getFriday() {
        return Friday;
    }

    public void setFriday(Data[] friday) {
        Friday = friday;
    }

    public Data[] getSaturday() {
        return Saturday;
    }

    public void setSaturday(Data[] saturday) {
        Saturday = saturday;
    }

    public Data[] getSunday() {
        return Sunday;
    }

    public void setSunday(Data[] sunday) {
        Sunday = sunday;
    }

    public class Data {
        private int id_lesson;
        private String start_time;
        private String end_time;
        private int id_group;
        private String group_name;
        private int id_day;
        private String day_of_week;
        private int id_action;
        private String action_name;

        public int getId_lesson() {
            return id_lesson;
        }

        public void setId_lesson(int id_lesson) {
            this.id_lesson = id_lesson;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getId_group() {
            return id_group;
        }

        public void setId_group(int id_group) {
            this.id_group = id_group;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public int getId_day() {
            return id_day;
        }

        public void setId_day(int id_day) {
            this.id_day = id_day;
        }

        public String getDay_of_week() {
            return day_of_week;
        }

        public void setDay_of_week(String day_of_week) {
            this.day_of_week = day_of_week;
        }

        public int getId_action() {
            return id_action;
        }

        public void setId_action(int id_action) {
            this.id_action = id_action;
        }

        public String getAction_name() {
            return action_name;
        }

        public void setAction_name(String action_name) {
            this.action_name = action_name;
        }
    }
}
