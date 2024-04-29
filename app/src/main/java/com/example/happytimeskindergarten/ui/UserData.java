package com.example.happytimeskindergarten.ui;

public class UserData {
    DataClass dataclass;
    SupportClass supportclass;

    public DataClass getDataclass() {
        return dataclass;
    }

    public void setDataclass(DataClass dataclass) {
        this.dataclass = dataclass;
    }

    public SupportClass getSupportclass() {
        return supportclass;
    }

    public void setSupportclass(SupportClass supportclass) {
        this.supportclass = supportclass;
    }

    class DataClass {
        String id, name, email, email_verified_at, password, remember_token, created_at, updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(String remember_token) {
            this.remember_token = remember_token;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
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


