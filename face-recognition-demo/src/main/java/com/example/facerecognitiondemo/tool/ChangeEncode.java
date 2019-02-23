package com.example.facerecognitiondemo.tool;


import org.apache.commons.codec.binary.Base64;

public class ChangeEncode {

    public static byte[] base64ToByte(String base64str) {
        return Base64.decodeBase64(base64str);
    }
    public static String byteToBase64(byte[] b){
        return Base64.encodeBase64String(b);
    }
}
