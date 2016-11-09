package com.BillRift.security;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

    // Serialize object to string
    public static String serializeObject(Object o) {
        String sObj = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(o);
            oo.close();
            sObj = new String(Base64Coder.encode(bo.toByteArray()));
        } catch (IOException e) {
            Log.e("ERROR", e.getLocalizedMessage());
        }
        return sObj;
    }

    // Deserialize object from string
    public static Object deserializeObject(String s) {
        Object o = null;
        try {
            byte[] data = Base64Coder.decode(s);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            o = ois.readObject();
            ois.close();
        } catch (Exception e) {
            Log.e("ERROR", e.getLocalizedMessage());
        }
        return o;
    }
}
