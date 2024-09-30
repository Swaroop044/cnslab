import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class blowfish {

    public static void main(String[] args) {
        try {
            // Original text to be encrypted and decrypted
            String text = "Hello, world!";
            
            // Generate Blowfish key
            SecretKey secretKey = generateBlowfishKey();

            // Display original text
            System.out.println("Original Text: " + text);
            
            // Encrypt the text
            String encryptedText = encrypt(text, secretKey);
            System.out.println("Encrypted Text (Base64): " + encryptedText);

            // Decrypt the encrypted text
            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generate a Blowfish secret key.
     * 
     * @return SecretKey Blowfish secret key
     * @throws Exception If an error occurs during key generation
     */
    public static SecretKey generateBlowfishKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        keyGenerator.init(128);  // Key size (Blowfish supports 32 to 448 bits)
        return keyGenerator.generateKey();
    }

    /**
     * Encrypt the given plaintext using Blowfish algorithm.
     * 
     * @param plaintext The original text to encrypt
     * @param key The secret key used for encryption
     * @return The encrypted text, encoded in Base64
     * @throws Exception If an error occurs during encryption
     */
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");  // ECB mode with PKCS5 padding
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);  // Encode to Base64 for readability
    }

    /**
     * Decrypt the given encrypted text using Blowfish algorithm.
     * 
     * @param encryptedText The encrypted text in Base64 format
     * @param key The secret key used for decryption
     * @return The decrypted original text
     * @throws Exception If an error occurs during decryption
     */
    public static String decrypt(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");  // ECB mode with PKCS5 padding
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);  // Decode from Base64
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);  // Perform decryption
        return new String(decryptedBytes, "UTF-8");  // Convert bytes back to string
    }
}
