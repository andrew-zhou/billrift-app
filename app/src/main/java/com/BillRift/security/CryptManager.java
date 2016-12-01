/*
    CryptManager.java
    Cryptography Manager Component
    Reference Number: 9
 */

package com.BillRift.security;

import android.util.Log;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class CryptManager {
    public static String encryptObject(Object o, String token) {
        String s = null;
        try {
            s = AESCrypt.encrypt(token, Serializer.serializeObject(o));
        } catch (Exception e) {
            Log.e("ERROR", e.getLocalizedMessage());
        }
        return s;
    }

    public static Object decryptString(String s, String token) {
        Object o = null;
        try {
            o = Serializer.deserializeObject(AESCrypt.decrypt(token, s));
        } catch (Exception e) {
            Log.e("ERROR", e.getLocalizedMessage());
        }
        return o;
    }
}
