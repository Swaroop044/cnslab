import java.util.Scanner;

public class rc4 {
    
    // Swaps two elements in the array
    private static void swap(byte[] array, int i, int j) {
        byte temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Key Scheduling Algorithm (KSA) - Initializes the permutation in array S
    private static void KSA(byte[] key, int keylen, byte[] S) {
        int j = 0;

        // Initialize the permutation S to the identity permutation
        for (int i = 0; i < 256; i++) {
            S[i] = (byte) i;
        }

        // Scramble the permutation using the key
        for (int i = 0; i < 256; i++) {
            j = (j + S[i] + key[i % keylen]) & 0xFF;  // & 0xFF ensures values are within byte range
            swap(S, i, j);
        }
    }

    // Pseudo-Random Generation Algorithm (PRGA) - Generates the keystream
    private static void PRGA(byte[] S, byte[] data, byte[] output) {
        int i = 0, j = 0;

        for (int k = 0; k < data.length; k++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            swap(S, i, j);
            int t = (S[i] + S[j]) & 0xFF;
            output[k] = (byte) (data[k] ^ S[t]);
        }
    }

    // Main RC4 encryption/decryption function
    public static void rc4(byte[] key, byte[] data, byte[] output) {
        byte[] S = new byte[256];
        KSA(key, key.length, S);
        PRGA(S, data, output);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the key from the user
        System.out.print("Enter the key: ");
        String keyInput = scanner.nextLine();
        byte[] key = keyInput.getBytes();

        // Get the plaintext from the user
        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();
        byte[] data = plaintext.getBytes();

        // Encrypt the plaintext
        byte[] ciphertext = new byte[data.length];
        rc4(key, data, ciphertext);

        // Display the ciphertext in hexadecimal format
        System.out.print("Ciphertext: ");
        for (byte b : ciphertext) {
            System.out.printf("%02X ", b);  // Output in hex format
        }
        System.out.println();

        // Decrypt the ciphertext
        byte[] decryptedText = new byte[ciphertext.length];
        rc4(key, ciphertext, decryptedText);

        // Display the decrypted text
        System.out.println("Decrypted Text: " + new String(decryptedText));

        scanner.close();
    }
}
