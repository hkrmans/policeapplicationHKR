package sample;

import java.nio.charset.StandardCharsets;
import java.security.*;

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
            String hex = Integer.toHexString(0xff& e);
            hexString.append(hex);
        }
        return hexString;
    }
}
