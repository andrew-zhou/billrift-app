/*
    TokenManager.java
    Auth Token Manager Component
    Reference Number: 5
 */

package com.BillRift;

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
