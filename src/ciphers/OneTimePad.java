package ciphers;
/**
 * The One-Time Pad is identical to the Vigenere Cipher, except the key is at least as long as the plaintext. Because of this,
 * the ciphertext is unbreakable (in any reasonable period of time) if the key is not known.
 * @author Nameyka Myrie
 * @version 02/12/2015
 */
public class OneTimePad extends Cipher
{
	/**
	 * Construct a new One Time Pad.
	 * @param input The input text
	 * @param key The encryption key
	 * @param plain Whether the input is plaintext (true) or ciphertext (false)
	 */
	public OneTimePad(String input, String key, boolean plain)
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
	 * Validity of key for One-Time-Pad
	 * Should be at least equal length of the plaintext/ciphertext
	 */
	protected boolean isValidKey(boolean encrypt)
	{
		boolean valid = false;
		int minVal = 0;
		if (encrypt) minVal = plaintext.length();
		else minVal = ciphertext.length();
		if (key.length() < minVal)
		{
			output += "The key, after the removal of non-letter characters, must be at least as long as the input text. ";
			output += "For shorter keys, please use the Vigenere cipher.";
		}
		else valid = true;
		return valid;
	}
}