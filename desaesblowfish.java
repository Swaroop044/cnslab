import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SimpleDES {

    private Cipher cipher;
    private SecretKey key;

    public SimpleDES() throws Exception {
        // Initialize the DES cipher and key generator
        cipher = Cipher.getInstance("DES");
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        key = keyGen.generateKey();
    }

    public String encrypt(String data) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decrypt(String encryptedData) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF8");
    }

    public static void main(String[] args) throws Exception {
        SimpleDES simpleDES = new SimpleDES();
        
        String textToEncrypt = "Hello, World!";
        String encryptedText = simpleDES.encrypt(textToEncrypt);
        String decryptedText = simpleDES.decrypt(encryptedText);

        System.out.println("Original Text: " + textToEncrypt);
        System.out.println("Encrypted Text: " + encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}