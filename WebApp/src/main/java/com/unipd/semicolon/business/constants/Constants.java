package com.unipd.semicolon.business.constants;


public class Constants {

    public static String URL_DB;
    public static String USERNAME_DB;
    public static String PASSWORD_DB;

    public static void initialize(ConstantsBean bean) {
        URL_DB = bean.getUrlDb();
        USERNAME_DB = bean.getUsernameDb();
        PASSWORD_DB = bean.getPasswordDb();
    }

}

