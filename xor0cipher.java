public class xor0cipher {
    public static void main(String[] args) {
        String str = "Hello World";
        char[] str1 = new char[str.length()]; // Create a char array for the cipher text
        int len = str.length();
        
        // Print the plain text
        System.out.print("The Plain Text: ");
        System.out.println(str);
        
        // Perform XOR operation (using 0 as the key)
        System.out.print("The Cipher Text: ");
        for (int i = 0; i < len; i++) {
            str1[i] = (char) (str.charAt(i) ^ 0); // XOR with 0
            System.out.print(str1[i]);
        }
        
        System.out.println();
    }
}
