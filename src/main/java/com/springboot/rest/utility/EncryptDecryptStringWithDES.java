package com.springboot.rest.utility;

import com.springboot.rest.customexception.CommonException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SpringBootRestDemo 2019 Filename: EncryptDecryptStringWithDES.java Description: EncryptDecryptStringWithDES class
 * request and response to appropriate format,
 *
 * @author Itmusketeers
 * @version 1.0
 * @Last modified 2019-03-07
 */
public class EncryptDecryptStringWithDES {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    private static final Logger LOGGER = LogManager.getLogger(EncryptDecryptStringWithDES.class);
    
    EncryptDecryptStringWithDES(){
        //do nothing constructor
    }
 
    public static void setKey(String myKey) throws CommonException
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
    }
 

	/**
	 * Using this method you can encrypt your data which is used for password encryption
	 *
	 * @author Itmusketeers
	 * @version 1.0
	 * @Last modified 2019-03-07
	 */
    public static String encrypt(String strToEncrypt, String secret) throws CommonException
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)
        {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
        
    }
 

    /**
	 * Using this method you can decrypt your data which is used for password encryption
	 *
	 * @author Itmusketeers
	 * @version 1.0
	 * @Last modified 2019-03-07
	 */
    public static String decrypt(String strToDecrypt, String secret) throws CommonException
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            LOGGER.error(e);
            throw new CommonException(e.getMessage());
        }
    }
 
   
 
}