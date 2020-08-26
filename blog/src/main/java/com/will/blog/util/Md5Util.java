package com.will.blog.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public static String encryptMd5Upper(String originString) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(originString.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer(hash.length * 2);
            for (byte b : hash) {
                if ((b&0xff)<0x10)
                    stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(b&0xff));
            }
            return stringBuffer.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //hash 为128位2进制，转为16进制，并转为大写
        return null;
    }

    public static void test() {
//        try {
////                byte [] hash = MessageDigest.getInstance("MD5").digest("jsdkjsdkj".getBytes("UTF-8"));
////                byte [] hash = MessageDigest.getInstance("MD5").digest("jsdkjsdkj".getBytes("GBK"));
//            byte[] hash = MessageDigest.getInstance("MD5").digest("jsdkjsdkj".getBytes());
//            System.out.println(Arrays.toString(hash));
//              [76, -113, -22, 71, -44, 41, -9, -1, 35, -109, -83, -60, 125, 102, -56, -56]
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(-113&0xff);
//        System.out.println("0"+Integer.toHexString(13&0xff));
//        System.out.println(Integer.toHexString(0x10));
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        //128位，8byte一个数，所以数组中有16个数字，大小是0---2^8,可以用两位16进制表示，对于小于16的数字用0补齐
        byte [] bytes = {76, -113, -22, 71, -44, 41, -9, 1, 35, -109, -83, -60, 125, 102, -56, -56};
        for (byte b : bytes){
            stringBuffer1.append(Integer.toHexString(b&0xff));
        }
        System.out.println("小于16没加0："+stringBuffer1.toString()+"，长度："+stringBuffer1.toString().length());
        for (byte b : bytes){
            String hex = Integer.toHexString(b&0xff);
            if (hex.length()==1){
                stringBuffer2.append("0"+hex);
            }else {
                stringBuffer2.append(hex);
            }
        }
        System.out.println("小于16加0："+stringBuffer2.toString()+"，长度："+stringBuffer2.toString().length());
    }

    public static void main(String[] args) {
        System.out.println("md5:"+encryptMd5Upper("alalalala"));
    }
}
