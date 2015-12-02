package ciphers;
/**
 * The Shift cipher encrypts a plaintext string by incrementing each letter by the value of the key. It is a symmetric cipher;
 * the decryption process simply does the reverse (i.e. subtract from each ciphertext letter).
 * @author Nameyka Myrie
 * @version 02/12/2015
 */
public class Shift extends Cipher
{
	/**
	 * Construct a new Shift Cipher.
	 * @param input The input text
	 * @param key The encryption key
	 * @param plain Whether the input is plaintext (true) or ciphertext (false)
	 */
	public Shift(String input, String key, boolean plain)
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
		this.key = key;
	}
	/**
	 * Encrypts the plaintext by incrementing each letter by the key value.
	 */
	public void encrypt()
	{
		output = "";
		if (isValidKey(true))
		{
			output += "Note: numbers, spaces and punctuation have been removed from the plaintext.\n\n";
			// Print key
			output += "Key: " + key + "\n\n";
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
				int ciphertextNumber = (plaintextNumbers[i] + Integer.parseInt(key)) % 26;
				output += String.format("%02d", ciphertextNumber) + " ";
				ciphertext += (char)(ciphertextNumber + 97);
			}
			output += "\n\n";
			// Print ciphertext
			output += "Ciphertext: " + ciphertext;
		}
	}
	/**
	 * Decrypts the ciphertext by decrementing each letter by the key value.
	 */
	public void decrypt()
	{
		output = "";
		if (isValidKey(true))
		{
			output += "Note: numbers, spaces and punctuation have been removed from the ciphertext.\n\n";
			// Print key
			output += "Key: " + key + "\n\n";
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
			// Encryption process
			output += "Subtract key from ciphertext (mod 26): \n";
			for (int i = 0; i < ciphertextNumbers.length; i++)
			{
				int plaintextNumber = (ciphertextNumbers[i] - Integer.parseInt(key)) % 26;
				output += String.format("%02d", plaintextNumber) + " ";
				plaintext += (char)(plaintextNumber + 97);
			}
			output += "\n\n";
			// Print plaintext
			output += "Plaintext: " + plaintext;
		}
	}   
	/**
	 * Validity of key for Shift
	 * Should be an integer between 1 and 25
	 */
	protected boolean isValidKey(boolean encrypt)
	{
		boolean valid = false;
		try
		{
			int val = Integer.parseInt(key);
			if (val >= 1 && val <= 25) valid = true;
			else output += "The key must be greater than or equal to zero.";
		}
		catch (NumberFormatException e)
		{
			output += "The key must be numeric.";
		}
		return valid;
	}
}