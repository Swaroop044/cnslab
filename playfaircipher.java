import java.util.Scanner;

public class playfaircipher {

    static final int SIZE = 5;

    // Generate the key table
    static void generateKeyTable(String key, char[][] keyTable) {
        boolean[] dict = new boolean[26]; // To keep track of used characters
        int k = 0, l = 0;

        // Add key characters to the key table
        for (int i = 0; i < key.length(); i++) {
            char currentChar = key.charAt(i);
            if (currentChar != 'j' && !dict[currentChar - 'a']) {
                keyTable[k][l] = currentChar;
                dict[currentChar - 'a'] = true;
                l++;
                if (l == SIZE) {
                    k++;
                    l = 0;
                }
            }
        }

        // Fill the remaining key table with unused letters (except 'j')
        for (int i = 0; i < 26; i++) {
            if (!dict[i] && (i != 9)) { // Skip 'j'
                keyTable[k][l] = (char) ('a' + i);
                l++;
                if (l == SIZE) {
                    k++;
                    l = 0;
                }
            }
        }
    }

    // Search for the positions of characters in the key table
    static void search(char[][] keyTable, char a, char b, int[] pos) {
        if (a == 'j') a = 'i';
        if (b == 'j') b = 'i';

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (keyTable[i][j] == a) {
                    pos[0] = i;
                    pos[1] = j;
                }
                if (keyTable[i][j] == b) {
                    pos[2] = i;
                    pos[3] = j;
                }
            }
        }
    }

    // Encrypt the given message
    static String encrypt(String str, char[][] keyTable) {
        StringBuilder encrypted = new StringBuilder(str);

        for (int i = 0; i < encrypted.length(); i += 2) {
            int[] pos = new int[4];
            search(keyTable, encrypted.charAt(i), encrypted.charAt(i + 1), pos);

            if (pos[0] == pos[2]) { // Same row
                encrypted.setCharAt(i, keyTable[pos[0]][(pos[1] + 1) % SIZE]);
                encrypted.setCharAt(i + 1, keyTable[pos[2]][(pos[3] + 1) % SIZE]);
            } else if (pos[1] == pos[3]) { // Same column
                encrypted.setCharAt(i, keyTable[(pos[0] + 1) % SIZE][pos[1]]);
                encrypted.setCharAt(i + 1, keyTable[(pos[2] + 1) % SIZE][pos[3]]);
            } else { // Rectangle
                encrypted.setCharAt(i, keyTable[pos[0]][pos[3]]);
                encrypted.setCharAt(i + 1, keyTable[pos[2]][pos[1]]);
            }
        }

        return encrypted.toString();
    }

    // Decrypt the given message
    static void decrypt(StringBuilder str, char[][] keyTable) {
        for (int i = 0; i < str.length(); i += 2) {
            int[] pos = new int[4];
            search(keyTable, str.charAt(i), str.charAt(i + 1), pos);

            if (pos[0] == pos[2]) { // Same row
                str.setCharAt(i, keyTable[pos[0]][(pos[1] + SIZE - 1) % SIZE]);
                str.setCharAt(i + 1, keyTable[pos[2]][(pos[3] + SIZE - 1) % SIZE]);
            } else if (pos[1] == pos[3]) { // Same column
                str.setCharAt(i, keyTable[(pos[0] + SIZE - 1) % SIZE][pos[1]]);
                str.setCharAt(i + 1, keyTable[(pos[2] + SIZE - 1) % SIZE][pos[3]]);
            } else { // Rectangle
                str.setCharAt(i, keyTable[pos[0]][pos[3]]);
                str.setCharAt(i + 1, keyTable[pos[2]][pos[1]]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] keyTable = new char[SIZE][SIZE];

        // Read the key
        System.out.println("Enter the key (in lowercase, without 'j'): ");
        String key = scanner.nextLine();

        // Generate the key table
        generateKeyTable(key, keyTable);

        // Read the message
        System.out.println("Enter the message to encrypt/decrypt (in lowercase, without 'j'): ");
        String message = scanner.nextLine();

        // If the length of the message is odd, append 'x' to make it even
        if (message.length() % 2 != 0) {
            message += 'x';
        }

        // Encrypt the message
        String encryptedMessage = encrypt(message, keyTable);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt the message
        StringBuilder decryptedMessage = new StringBuilder(encryptedMessage);
        decrypt(decryptedMessage, keyTable);
        System.out.println("Decrypted Message: " + decryptedMessage.toString());

        scanner.close();
    }
}
