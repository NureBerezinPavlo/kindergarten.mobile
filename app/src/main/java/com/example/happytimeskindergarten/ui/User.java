package com.example.happytimeskindergarten.ui;

public class User {
    static String token, id;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        User.id = id;
    }

    public static String[] getFamily_account_id() {
        return family_account_id;
    }

    public static void setFamily_account_id(String[] family_account_id) {
        User.family_account_id = family_account_id;
    }

    static String[] family_account_id;
    public static String getToken() {
        return "Bearer " + token;
    }

    public static void setToken(String token) {
        User.token = token;
    }

    public static family_accountData family_account;

    public static com.example.happytimeskindergarten.ui.family_accountData getFamily_account() {
        return family_account;
    }

    public static void setFamily_account(com.example.happytimeskindergarten.ui.family_accountData family_account) {
        User.family_account = family_account;
    }
}
