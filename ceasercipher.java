import java.util.Scanner;

public class ceasercipher {
    public static void ceasercipher(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message to encrypt: ");
        String text = scanner.nextLine();
        System.out.print("Enter the key: ");
        int key = scanner.nextInt();

        // Encrypt the message
        String encryptedMessage = encrypt(text, key);
        if (encryptedMessage == null) {
            System.out.println("Invalid Message");
            return;
        }
        System.out.println("Encrypted message: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, key);
        if (decryptedMessage == null) {
            System.out.println("Invalid Message");
            return;
        }
        System.out.println("Decrypted message: " + decryptedMessage);
        scanner.close();
    }

    public static String encrypt(String text, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                if (Character.isLowerCase(ch)) {
                    ch = (char) ((ch - 'a' + key) % 26 + 'a');
                } else if (Character.isUpperCase(ch)) {
                    ch = (char) ((ch - 'A' + key) % 26 + 'A');
                } else if (Character.isDigit(ch)) {
                    ch = (char) ((ch - '0' + key) % 10 + '0');
                }
            } else {
                return null; // Invalid character found
            }
            encryptedText.append(ch);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String text, int key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                if (Character.isLowerCase(ch)) {
                    ch = (char) ((ch - 'a' - key + 26) % 26 + 'a');
                } else if (Character.isUpperCase(ch)) {
                    ch = (char) ((ch - 'A' - key + 26) % 26 + 'A');
                } else if (Character.isDigit(ch)) {
                    ch = (char) ((ch - '0' - key + 10) % 10 + '0');
                }
            } else {
                return null; // Invalid character found
            }
            decryptedText.append(ch);
        }
        return decryptedText.toString();
    }
}
