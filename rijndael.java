import java.util.Base64;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

class AES {

    // Constants for the secret key and salt
    private static final String SECRET_KEY = "my_super_secret_key_ho_ho_ho";
    private static final String SALT = "ssshhhhhhhhhhh!!!!";

    /**
     * Encrypts a given string using AES algorithm with CBC mode and PKCS5Padding.
     *
     * @param strToEncrypt The string to encrypt.
     * @return The encrypted string in Base64 format.
     */
    public static String encrypt(String strToEncrypt) {
        try {
            // Initialization vector with all zeros (16 bytes for AES)
            byte[] iv = new byte[16]; 
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Generate a key using PBKDF2 with HMAC SHA256
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Configure cipher to use AES in CBC mode with PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);

            // Encrypt the string and return the Base64-encoded result
            byte[] encryptedText = cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedText);

        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.getMessage());
        }
        return null;
    }

    /**
     * Decrypts an encrypted Base64 string using AES algorithm with CBC mode and PKCS5Padding.
     *
     * @param strToDecrypt The string to decrypt (in Base64 format).
     * @return The decrypted original string.
     */
    public static String decrypt(String strToDecrypt) {
        try {
            // Initialization vector with all zeros (16 bytes for AES)
            byte[] iv = new byte[16];
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Generate a key using PBKDF2 with HMAC SHA256
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Configure cipher to use AES in CBC mode with PKCS5 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);

            // Decrypt the string and return the original text
            byte[] decodedText = Base64.getDecoder().decode(strToDecrypt);
            byte[] decryptedText = cipher.doFinal(decodedText);
            return new String(decryptedText, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.getMessage());
        }
        return null;
    }
}

public class rijndael{

    public static void main(String[] args) {

        // The original string to be encrypted and decrypted
        String originalString = "GeeksforGeeks";

        // Encrypting the original string
        String encryptedString = AES.encrypt(originalString);

        // Decrypting the encrypted string
        String decryptedString = AES.decrypt(encryptedString);

        // Output the original, encrypted, and decrypted strings
        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }
}
