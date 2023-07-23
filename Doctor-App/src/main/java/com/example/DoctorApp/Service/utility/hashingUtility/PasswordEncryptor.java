package com.example.DoctorApp.Service.utility.hashingUtility;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    public static String getEncryptedPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();
        return DatatypeConverter.printHexBinary(digested);
    }
}
