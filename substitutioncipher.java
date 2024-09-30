import java.util.Scanner;

public class substitutioncipher {
    
    // Function to encrypt the message using the substitution key
    public static void encrypt(char[] message, char[] key) {
        for (int i = 0; i < message.length; i++) {
            if (message[i] >= 'a' && message[i] <= 'z') {
                message[i] = key[message[i] - 'a']; // Substitute based on the key
            }
        }
    }

    // Function to decrypt the message using the substitution key
    public static void decrypt(char[] message, char[] key) {
        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < 26; j++) {
                if (message[i] == key[j]) {
                    message[i] = (char) ('a' + j); // Find the original letter
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the substitution key
        System.out.print("Enter the substitution key (26 lowercase letters in random order): ");
        String keyInput = scanner.nextLine();
        char[] key = keyInput.toCharArray();

        // Validate key length and content
        if (key.length != 26) {
            System.out.println("Invalid key length. Please provide 26 letters.");
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (key[i] < 'a' || key[i] > 'z') {
                System.out.println("Invalid key. Please provide only lowercase letters.");
                return;
            }
        }

        // Read the message to encrypt
        System.out.print("Enter the message to encrypt: ");
        String messageInput = scanner.nextLine();
        char[] message = messageInput.toCharArray();

        // Encrypt the message
        encrypt(message, key);
        System.out.println("Encrypted message: " + new String(message));

        // Decrypt the message
        decrypt(message, key);
        System.out.println("Decrypted message: " + new String(message));

        scanner.close();
    }
}
