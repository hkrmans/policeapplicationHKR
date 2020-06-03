package sample;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;

public class Security {

    public String hashPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuffer hexString = new StringBuffer();
        for (Byte e : encodedhash) {
            String hex = Integer.toHexString(0xff & e);
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String decrypter(String password) {
        if (password.length() != 24) {
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
        for (int p = 0; p < 24; p += 2) {
            if (p < 4 || p == 10 || p == 8) {
                passnumbers.add(number.get(p) + number.get(p + 1));
            } else {
                passnumbers.add(number.get(p) - number.get(p + 1));
            }
        }
        String passreturn = "";
        for (int x : passnumbers) {
            passreturn += (char) x;
        }
        return (passreturn);
    }

    public String mailDecrypter(String password){
        ArrayList<Integer> numbers = new ArrayList<>();

        int l = password.length();
        int convert;
        for (int i = 0; i < l; i++) {
            convert = password.charAt(i);
            numbers.add(convert);
        }
        ArrayList<Integer> passnumbers = new ArrayList<>();
        for(int i = 0; i <numbers.size()-3; i+=2){
            passnumbers.add(numbers.get(i) + numbers.get(i+1));
        }
        String passreturn = "";
        for (int x : passnumbers) {
            passreturn += (char) x;
        }
        passreturn+="123";
        return (passreturn);
    }
}
