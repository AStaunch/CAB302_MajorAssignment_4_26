package client;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface user {
    String identity();

    Integer userID();

    Boolean isAdmin();

    byte[] hashPassword(String pwd) throws NoSuchAlgorithmException, InvalidKeySpecException;
}
