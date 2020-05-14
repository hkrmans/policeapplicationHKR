package sample;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import java.io.*;

public class Sec{
    private static final SecureRandom rand = new SecureRandom();

    public void checkPassAndUser(String input){
        try{
            if(input.length() < 12 || input.length() > 40){
                throw new IllegalArgumentException("Error");
            }
            if(input.chars().filter(c-> c>='A' && c<='Z').count() == 0 ||input.chars().filter(c-> c>='0' && c<='9').count() == 0){
                throw new IllegalArgumentException("Error does not fufill requirements!");
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void checkMysql(String input){
        try{
            if(input.chars().filter(c-> c>='A' && c<='Z').count() + input.chars().filter(c-> c>='a' && c<='z').count() != input.length()){
                throw new IllegalArgumentException("Only letters are allowed!");
            }
        }catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public StringBuffer hashPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for(Byte e: encodedhash){
            String hex = Integer.toHexString(0xff & e);
            hexString.append(hex);
        }
        return hexString;
    }

    public String decrypter(String password){
        if(password.length() != 24){
            return "password";
        }
        ArrayList<Integer> number = new ArrayList<>();
        int l = password.length();
        int convert;
        for (int i = 0; i < l; i++) {
            convert = password.charAt(i);
            number.add(convert);
        }
        ArrayList<Integer> passnumbers = new ArrayList<>();
        for(int p = 0; p<24;p+=2){
            if(p>12 || p==0|| p==6|| p== 4) {
                passnumbers.add(number.get(p) - number.get(p+1));
            }else{
                passnumbers.add(number.get(p) + number.get(p+1));
            }
        }
        String passreturn = "";
        for(int x: passnumbers){
            passreturn += (char) x;
            System.out.println(x);
        }
        return(passreturn);
    }


}
