package client;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * A Class for creating admin user as objects
 */

public class adminUser implements user {
    private String name;
    private Integer userID;
    private Boolean isAdmin;

    /**
     * Create an instance of adminUser
     *
     * @param name Name of the user
     * @param userID ID of the user
     */
    public adminUser(String name, Integer userID){
        this.name = name;
        this.userID = userID;
        this.isAdmin = true;
    }

    /**
     * Get method for the ID of the user
     *
     * @return the ID of the user
     */
    @Override
    public Integer userID() {
        return userID;
    }

    /**
     * Get method for the status of the user
     * For admin user this will true
     * @return the status of the user
     */
    @Override
    public Boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Get method for the name of the user
     *
     * @return the name of the user
     */
    @Override
    public String identity() {
        return name;
    }

    @Override
    public byte[] hashPassword(String pwd) throws NoSuchAlgorithmException, InvalidKeySpecException {

        //String password = "andre21312";

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        //System.out.println(hash);

        return factory.generateSecret(spec).getEncoded();
    }
}
