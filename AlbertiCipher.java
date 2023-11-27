import java.util.Scanner;

public class AlbertiCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the key (a single letter): ");
        String key = scanner.nextLine().toUpperCase();
        if (key.length() != 1 || !Character.isLetter(key.charAt(0))) {
            System.out.println("Invalid key. Please enter a single letter.");
            return;
        }

        AlbertiDisk disk = new AlbertiDisk(key.charAt(0));

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine().toUpperCase();

        String encryptedText = encrypt(plaintext, disk);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, disk);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    public static String encrypt(String plaintext, AlbertiDisk disk) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : plaintext.toCharArray()) {
            if (Character.isLetter(character)) {
                char encryptedChar = disk.encrypt(character);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(character);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, AlbertiDisk disk) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : ciphertext.toCharArray()) {
            if (Character.isLetter(character)) {
                char decryptedChar = disk.decrypt(character);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(character);
            }
        }

        return decryptedText.toString();
    }
}

class AlbertiDisk {
    private final String outerRing;
    private final String innerRing;
    private final int shift;

    public AlbertiDisk(char key) {
        outerRing = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        innerRing = "FGHIJKLMNOPQRSTUVWXYZABCDE";
        shift = outerRing.indexOf(Character.toUpperCase(key));
    }

    public char encrypt(char plaintextChar) {
        if (Character.isLetter(plaintextChar)) {
            int index = outerRing.indexOf(Character.toUpperCase(plaintextChar));
            return innerRing.charAt((index + shift) % 26);
        } else {
            return plaintextChar;
        }
    }

    public char decrypt(char ciphertextChar) {
        if (Character.isLetter(ciphertextChar)) {
            int index = innerRing.indexOf(Character.toUpperCase(ciphertextChar));
            return outerRing.charAt((index - shift + 26) % 26);
        } else {
            return ciphertextChar;
        }
    }
}
