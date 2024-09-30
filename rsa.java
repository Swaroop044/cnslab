import java.util.*;

public class rsa {
    
    // Function to calculate Euler's totient function
    public static int phi(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    n /= i;
                }
                result -= result / i;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    // Function to calculate GCD
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to calculate a^b mod m using modular exponentiation
    public static int modpow(int a, int b, int m) {
        int result = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % m;
            }
            a = (a * a) % m;
            b >>= 1; // b = b / 2
        }
        return result;
    }

    // Function to generate a primitive root modulo n
    public static int generatePrimitiveRoot(int n) {
        int phiN = phi(n);
        int[] factors = new int[30];
        int count = 0;
        int temp = phiN;
        
        for (int i = 2; i * i <= temp; i++) {
            if (temp % i == 0) {
                factors[count++] = i;
                while (temp % i == 0) {
                    temp /= i;
                }
            }
        }
        if (temp > 1) {
            factors[count++] = temp;
        }
        
        for (int i = 2; i <= n; i++) {
            boolean isRoot = true;
            for (int j = 0; j < count; j++) {
                if (modpow(i, phiN / factors[j], n) == 1) {
                    isRoot = false;
                    break;
                }
            }
            if (isRoot) {
                return i;
            }
        }
        return -1; // No primitive root found
    }

    public static void main(String[] args) {
        int p = 11;
        int q = 13;
        int n = p * q; // Modulus for public and private keys
        int phiN = (p - 1) * (q - 1); // Euler's totient
        int e = 7; // Example value of e
        int d = 0;

        // Calculate d such that (d * e) % phiN = 1
        while ((d * e) % phiN != 1) {
            d++;
        }

        // Print public and private keys
        System.out.printf("Public key: {%d, %d}%n", e, n);
        System.out.printf("Private key: {%d, %d}%n", d, n);

        int m = 11; // Original message
        int c = modpow(m, e, n); // Encrypted message
        int decrypted = modpow(c, d, n); // Decrypted message

        // Print results
        System.out.printf("Original message: %d%n", m);
        System.out.printf("Encrypted message: %d%n", c);
        System.out.printf("Decrypted message: %d%n", decrypted);
    }
}
