package com.BillRift;

/**
 * Created by Andrew on 11/9/2016.
 */

public class TokenManager {
    private static String token = null;

    public static String getToken() {
        return token;
    }

    public static void saveToken(String t) {
        token = t;
    }

    public static void clearToken() {
        token = null;
    }
}
