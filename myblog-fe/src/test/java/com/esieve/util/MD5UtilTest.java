package com.esieve.util;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5UtilTest {

    @Test
    public void encoderPassword() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(MD5Util.encoderPassword("zqy"));
    }
}