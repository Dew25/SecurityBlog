/**
 * Класс шифрует пароль алгоритмом SHA-256.
 * 
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jvm
 */
public class EncriptPass {
    private String encriptPassword;
    
    /**
     * 
     * @param password - шифруемый пароль (String)
     * @param salts - соль (String)
     * @throws NoSuchAlgorithmException 
     */
    public EncriptPass(String password, String salts) throws NoSuchAlgorithmException {
        setEncriptPassword(password,salts);
    }

    public EncriptPass(String password) throws NoSuchAlgorithmException {
        setEncriptPassword(password,"0");
    }
    
    private void setEncriptPassword(String password, String salts) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("SHA-256");
        m.update(password.getBytes(),0,password.length());
        this.encriptPassword = new BigInteger(1,m.digest()).toString(16);
    }

    /**
     * 
     * @return - зашифрованный пароль (SHA-256) (String)
     */
    public String getEncriptPassword(){
        return this.encriptPassword;
    }
    
}
