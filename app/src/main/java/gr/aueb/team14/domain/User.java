package gr.aueb.team14.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = getSHA1Hash(password);
    }

    public static String getSHA1Hash(String text) {
        String hashedText = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] textDigest = md.digest(text.getBytes());
            BigInteger no = new BigInteger(1, textDigest);
            hashedText = no.toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return hashedText;
    }

    // Checks whether the two given passwords match
    public static boolean checkPasswords(String password1, String password2) {
        return password1.equals(password2);
    }
}
