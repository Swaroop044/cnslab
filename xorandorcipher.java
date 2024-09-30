public class xorandorcipher {
    public static void main(String[] args) {
        String str = "Hello World";
        char[] str1 = new char[str.length()];
        char[] str2 = new char[str.length()];
        char[] str3 = new char[str.length()];
        int len = str.length();

        // Print the plain text
        System.out.print("The Plain Text: ");
        System.out.println(str);

        // Cipher text after AND operation
        System.out.print("Cipher text after AND Operation: ");
        for (int i = 0; i < len; i++) {
            str1[i] = (char) (str.charAt(i) & 127); // AND with 127
            System.out.print(str1[i]);
        }

        // Cipher text after XOR operation
        System.out.print("\nCipher text after XOR Operation: ");
        for (int i = 0; i < len; i++) {
            str3[i] = (char) (str.charAt(i) ^ 127); // XOR with 127
            System.out.print(str3[i]);
        }

        // Cipher text after OR operation
        System.out.print("\nCipher text after OR Operation: ");
        for (int i = 0; i < len; i++) {
            str2[i] = (char) (str.charAt(i) | 127); // OR with 127
            System.out.print(str2[i]);
        }

        System.out.println();
    }
}
