import java.util.Arrays;

public class hillcipher {
    static final int N = 3;

    // Encryption method
    public static void encrypt(String key, String item, int[][] keyMatrix, int[] itemMatrix, int[] answer) {
        // Convert item characters to corresponding numerical values (A=0, B=1, ..., Z=25)
        for (int i = 0; i < N; i++) {
            itemMatrix[i] = item.charAt(i) - 'A';
        }

        // Convert key characters to key matrix in numerical form
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                keyMatrix[i][j] = key.charAt(k) - 'A';
                k++;
            }
        }

        // Perform matrix multiplication of keyMatrix and itemMatrix
        for (int i = 0; i < N; i++) {
            int p = 0;
            for (int j = 0; j < N; j++) {
                p += keyMatrix[i][j] * itemMatrix[j];
            }
            answer[i] = p % 26; // Modulo 26 to stay within the alphabet
        }
    }

    // Decryption method
    public static void decrypt(int[][] inverseKeyMatrix, int[] encryptedMatrix, int[] decryptedMatrix) {
        // Perform matrix multiplication of inverseKeyMatrix and encryptedMatrix
        for (int i = 0; i < N; i++) {
            int p = 0;
            for (int j = 0; j < N; j++) {
                p += inverseKeyMatrix[i][j] * encryptedMatrix[j];
            }
            decryptedMatrix[i] = p % 26; // Modulo 26 to stay within the alphabet
            if (decryptedMatrix[i] < 0) {
                decryptedMatrix[i] += 26; // Handle negative numbers
            }
        }
    }

    public static void main(String[] args) {
        // Encryption key and message to be encrypted
        String key = "GYBNQKURP";
        String item = "ACT";

        int[][] keyMatrix = new int[N][N];
        int[] itemMatrix = new int[N];
        int[] encryptedMatrix = new int[N];
        char[] encryptedMessage = new char[N];

        // Encrypt the message
        encrypt(key, item, keyMatrix, itemMatrix, encryptedMatrix);

        // Convert encrypted matrix back to characters
        for (int i = 0; i < N; i++) {
            encryptedMessage[i] = (char) (encryptedMatrix[i] + 'A');
        }

        System.out.println("Encrypted Message: " + new String(encryptedMessage));

        // Inverse of the key matrix (pre-calculated)
        int[][] inverseKeyMatrix = {
            {8, 5, 10},
            {21, 8, 21},
            {21, 12, 8}
        };

        int[] decryptedMatrix = new int[N];
        char[] decryptedMessage = new char[N];

        // Decrypt the message
        decrypt(inverseKeyMatrix, encryptedMatrix, decryptedMatrix);

        // Convert decrypted matrix back to characters
        for (int i = 0; i < N; i++) {
            decryptedMessage[i] = (char) (decryptedMatrix[i] + 'A');
        }

        System.out.println("Decrypted Message: " + new String(decryptedMessage));
    }
}
