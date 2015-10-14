package ciphers;
/**
 * The Vigenere Cipher is a block version of the Shift cipher. Instead of using one key value, it uses an array of them
 * (typically a word or short sentence) and encrypts a plaintext letter with the corresponding key value (mod its length).
 * Like the Shift cipher, this is symmetric; decryption simply substracts rather than adds.
 * @author Jack Taylor
 * @version 14/10/2015
 */
public class VigenereCipher extends Cipher
{
	/**
     * Construct a new Vigenere Cipher.
     * @param plaintext The plaintext string
     * @param key The encryption key
     */
	public VigenereCipher(String plaintext, String key)
	{
		this.plaintext = plaintext.toLowerCase().replaceAll("\\W", "");
		ciphertext = "";
		this.key = key.toLowerCase().replaceAll("\\W", "");;
		encrypt();
		decrypt();
	}
	/**
	 * Encrypts the plaintext by incrementing each letter by the corresponding key value mod key length.
	 */
	public void encrypt()
	{
		ciphertext = "";
		System.out.print("Vigenere Cipher: Encryption Process \n\n");
		System.out.print("Note: any punctuation and spaces have been removed from the key and plaintext. \n\n");
		//Numeric representation of key
		System.out.print("Key: " + key + "\n");
		System.out.print("Numeric Representation: \n");
		int[] keyNumbers = new int[key.length()];
		for (int i = 0; i < key.length(); i++)
		{
			keyNumbers[i] = key.charAt(i) - 97;
			System.out.print(String.format("%02d", keyNumbers[i]) + " ");
		}
		System.out.print("\n\n");
		//Numeric representation of plaintext
		System.out.print("Plaintext: " + plaintext + "\n");
		System.out.print("Numeric Representation: \n");
		int[] plaintextNumbers = new int[plaintext.length()];
		for (int i = 0; i < plaintext.length(); i++)
		{
			if (plaintext.charAt(i) < 97 || plaintext.charAt(i) > 122) continue;
			plaintextNumbers[i] = plaintext.charAt(i) - 97;
			System.out.print(String.format("%02d", plaintextNumbers[i]) + " ");
		}
		System.out.print("\n\n");
		//Encryption process
		System.out.print("Add key to plaintext (mod 26): \n");
		for (int i = 0; i < plaintextNumbers.length; i++)
		{
			int ciphertextNumber = (plaintextNumbers[i] + keyNumbers[i % keyNumbers.length]) % 26;
			System.out.print(String.format("%02d", ciphertextNumber) + " ");
			ciphertext += (char)(ciphertextNumber + 97);
		}
		System.out.print("\n\n");
		//Print ciphertext
		System.out.print("Ciphertext: " + ciphertext);
		System.out.print("\n\n");
	}
	/**
	 * Decrypts the ciphertext by decrementing each letter by the corresponding key value mod key length.
	 */
	public void decrypt()
	{
		plaintext = "";
		System.out.print("Vigenere Cipher: Decryption Process \n\n");
		//Numeric representation of key
		System.out.print("Key: " + key + "\n");
		System.out.print("Numeric Representation: \n");
		int[] keyNumbers = new int[key.length()];
		for (int i = 0; i < key.length(); i++)
		{
			keyNumbers[i] = key.charAt(i) - 97;
			System.out.print(String.format("%02d", keyNumbers[i]) + " ");
		}
		System.out.print("\n\n");
		//Numeric representation of ciphertext
		System.out.print("Ciphertext: " + ciphertext + "\n");
		System.out.print("Numeric Representation: \n");
		int[] ciphertextNumbers = new int[ciphertext.length()];
		for (int i = 0; i < ciphertext.length(); i++)
		{
			if (ciphertext.charAt(i) < 97 || ciphertext.charAt(i) > 122) continue;
			ciphertextNumbers[i] = ciphertext.charAt(i) - 97;
			System.out.print(String.format("%02d", ciphertextNumbers[i]) + " ");
		}
		System.out.print("\n\n");
		//Decryption process
		System.out.print("Subtract key from ciphertext (mod 26): \n");
		for (int i = 0; i < ciphertextNumbers.length; i++)
		{
			int plaintextNumber = (ciphertextNumbers[i] - keyNumbers[i % keyNumbers.length]) % 26;
			if (plaintextNumber < 0) plaintextNumber += 26;
			System.out.print(String.format("%02d", plaintextNumber) + " ");
			plaintext += (char)(plaintextNumber + 97);
		}
		System.out.print("\n\n");
		//Print plaintext
		System.out.print("Plaintext: " + plaintext);
		System.out.print("\n\n");
	}
}