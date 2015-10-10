package ciphers;
public class VigenereCipher extends Cipher
{
	public VigenereCipher(String plaintext, String key)
	{
		this.plaintext = plaintext.toLowerCase();
		ciphertext = "";
		this.key = key;
		encrypt(plaintext, key);
		decrypt(ciphertext, key);
	}
	public void encrypt(String plaintext, String key)
	{
		ciphertext = "";
		System.out.print("Vigenere Cipher Encryption \n\n");
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
		plaintext = plaintext.replaceAll("\\W", "");
		int[] plaintextNumbers = new int[plaintext.length()];
		for (int i = 0; i < plaintext.length(); i++)
		{
			if (plaintext.charAt(i) < 97 || plaintext.charAt(i) > 122) continue;
			plaintextNumbers[i] = plaintext.charAt(i) - 97;
			System.out.print(String.format("%02d", plaintextNumbers[i]) + " ");
		}
		System.out.print("\nPunctuation and spaces have been removed.");
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
	public void decrypt(String ciphertext, String key)
	{
		plaintext = "";
		System.out.print("Vigenere Cipher Decryption \n\n");
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
		ciphertext = ciphertext.replaceAll("\\W", "");
		int[] ciphertextNumbers = new int[ciphertext.length()];
		for (int i = 0; i < ciphertext.length(); i++)
		{
			if (ciphertext.charAt(i) < 97 || ciphertext.charAt(i) > 122) continue;
			ciphertextNumbers[i] = ciphertext.charAt(i) - 97;
			System.out.print(String.format("%02d", ciphertextNumbers[i]) + " ");
		}
		System.out.print("\nPunctuation and spaces have been removed.");
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