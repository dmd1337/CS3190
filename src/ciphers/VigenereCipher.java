package ciphers;
/**
 * The Vigenere Cipher is a block version of the Shift cipher. Instead of using one key value, it uses an array of them
 * (typically a word or short sentence) and encrypts a plaintext letter with the corresponding key value (mod its length).
 * Like the Shift cipher, this is symmetric; decryption simply substracts rather than adds.
 * @author Jack Taylor
 * @version 02/12/2015
 */
public class VigenereCipher extends Cipher
{
	/**
	 * Construct a new Vigenere Cipher.
	 * @param input The input text
	 * @param key The encryption key
	 * @param plain Whether the input is plaintext (true) or ciphertext (false)
	 */
	public VigenereCipher(String input, String key, boolean plain)
	{
		if (plain)
		{
			plaintext = input.toLowerCase().replaceAll("[^a-z]", "");
			ciphertext = "";
		}
		else
		{
			plaintext = "";
			ciphertext = input.toLowerCase().replaceAll("[^a-z]", "");
		}
		this.key = key.toLowerCase().replaceAll("[^a-z]", "");
	}
	/**
	 * Encrypts the plaintext by incrementing each letter by the corresponding key value mod key length.
	 */
	public void encrypt()
	{
		output = "";
		if (isValidKey(true))
		{
			output += "Note: numbers, spaces and punctuation have been removed from the plaintext and key.\n\n";
			// Numeric representation of key
			output += "Key: " + key + "\n";
			output += "Numeric Representation: \n";
			int[] keyNumbers = new int[key.length()];
			for (int i = 0; i < key.length(); i++)
			{
				keyNumbers[i] = key.charAt(i) - 97;
				output += String.format("%02d", keyNumbers[i]) + " ";
			}
			output += "\n\n";
			// Numeric representation of plaintext
			output += "Plaintext: " + plaintext + "\n";
			output += "Numeric Representation: \n";
			int[] plaintextNumbers = new int[plaintext.length()];
			for (int i = 0; i < plaintext.length(); i++)
			{
				if (plaintext.charAt(i) < 97 || plaintext.charAt(i) > 122) continue;
				plaintextNumbers[i] = plaintext.charAt(i) - 97;
				output += String.format("%02d", plaintextNumbers[i]) + " ";
			}
			output += "\n\n";
			// Encryption process
			output += "Add key to plaintext (mod 26): \n";
			for (int i = 0; i < plaintextNumbers.length; i++)
			{
				int ciphertextNumber = (plaintextNumbers[i] + keyNumbers[i % keyNumbers.length]) % 26;
				output += String.format("%02d", ciphertextNumber) + " ";
				ciphertext += (char)(ciphertextNumber + 97);
			}
			output += "\n\n";
			// Print ciphertext
			output += "Ciphertext: " + ciphertext + "\n\n";
		}
	}
	/**
	 * Decrypts the ciphertext by decrementing each letter by the corresponding key value mod key length.
	 */
	public void decrypt()
	{
		output = "";
		if (isValidKey(false))
		{
			output += "Note: numbers, spaces and punctuation have been removed from the ciphertext and key.\n\n";
			// Numeric representation of key
			output += "Key: " + key + "\n";
			output += "Numeric Representation: \n";
			int[] keyNumbers = new int[key.length()];
			for (int i = 0; i < key.length(); i++)
			{
				keyNumbers[i] = key.charAt(i) - 97;
				output += String.format("%02d", keyNumbers[i]) + " ";
			}
			output += "\n\n";
			// Numeric representation of ciphertext
			output += "Ciphertext: " + ciphertext + "\n";
			output += "Numeric Representation: \n";
			int[] ciphertextNumbers = new int[ciphertext.length()];
			for (int i = 0; i < ciphertext.length(); i++)
			{
				if (ciphertext.charAt(i) < 97 || ciphertext.charAt(i) > 122) continue;
				ciphertextNumbers[i] = ciphertext.charAt(i) - 97;
				output += String.format("%02d", ciphertextNumbers[i]) + " ";
			}
			output += "\n\n";
			// Decryption process
			output += "Subtract key from ciphertext (mod 26): \n";
			for (int i = 0; i < ciphertextNumbers.length; i++)
			{
				int plaintextNumber = (ciphertextNumbers[i] - keyNumbers[i % keyNumbers.length]) % 26;
				if (plaintextNumber < 0) plaintextNumber += 26;
				output += String.format("%02d", plaintextNumber) + " ";
				plaintext += (char) (plaintextNumber + 97);
			}
			output += "\n\n";
			System.out.print("");
			// Print plaintext
			output += "Plaintext: " + plaintext + "\n\n";
		}
	}
	/**
	 * Validity of key for Vigenere
	 * Should be a sequence of letters
	 */
	protected boolean isValidKey(boolean encrypt)
	{
		boolean valid = false;
		char[] keyCheck = key.toCharArray();
		if (key.length() > 0)
		{
			valid = true;
			for (char c : keyCheck)
			{
				if (!Character.isLetter(c)) valid = false;
			}
		}
		else
		{
			output += "Please enter a key. It must contain at least one letter; numbers and punctuation will be removed.";
		}
		return valid;
	}
}