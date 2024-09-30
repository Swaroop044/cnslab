import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.spec.KeySpec;

public class des {

    // Constants for encryption
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede"; // Triple DES

    // Encryption attributes
    private KeySpec keySpec;
    private SecretKeyFactory secretKeyFactory;
    private Cipher cipher;
    private SecretKey secretKey;

    // Encryption key (24 bytes for Triple DES)
    private final String encryptionKey = "ThisIsSecretEncryptionKey"; // Must be 24 characters

    // Buffered reader for user input
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Constructor to set up the encryption/decryption mechanism
    public des() throws Exception {
        byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT); // Convert key to bytes
        keySpec = new DESedeKeySpec(keyAsBytes); // Create key specification for Triple DES
        secretKeyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME); // Key factory
        cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME); // Cipher for encryption
        secretKey = secretKeyFactory.generateSecret(keySpec); // Generate the secret key
    }

    // Method to encrypt a string
    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Initialize cipher for encryption
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT); // Convert to bytes
            byte[] encryptedText = cipher.doFinal(plainText); // Perform encryption
            encryptedString = Base64.getEncoder().encodeToString(encryptedText); // Encode in Base64
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    // Method to decrypt an encrypted string
    public String decrypt(String encryptedString) {
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey); // Initialize cipher for decryption
            byte[] encryptedText = Base64.getDecoder().decode(encryptedString); // Decode Base64
            byte[] plainText = cipher.doFinal(encryptedText); // Perform decryption
            decryptedText = new String(plainText, UNICODE_FORMAT); // Convert bytes back to string
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

    // Main method to handle user input and display encryption/decryption
    public static void main(String[] args) {
        try {
            System.out.print("Enter the string to encrypt: ");
            des desEncryptor = new des(); // Create DES object
            String stringToEncrypt = reader.readLine(); // Get input from user

            String encryptedString = desEncryptor.encrypt(stringToEncrypt); // Encrypt the input
            String decryptedString = desEncryptor.decrypt(encryptedString); // Decrypt the encrypted text

            // Display results
            System.out.println("\nOriginal String: " + stringToEncrypt);
            System.out.println("Encrypted String: " + encryptedString);
            System.out.println("Decrypted String: " + decryptedString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
