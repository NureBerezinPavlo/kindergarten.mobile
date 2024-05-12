package com.example.happytimeskindergarten.ui;

public class Auth {
    SupportClass supportclass;
    String token, id;
    String[] family_account_id;

    public String[] getFamily_account_id() {
        return family_account_id;
    }

    public void setFamily_account_id(String[] family_account_id) {
        this.family_account_id = family_account_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    class SupportClass {
        String url, text;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
