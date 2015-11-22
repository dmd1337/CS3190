package ciphers;
/**
 * The Shift cipher encrypts a plaintext string by incrementing each letter by the value of the key. It is a symmetric cipher; the decryption process simply does the reverse (i.e. subtract from each ciphertext letter).
 * @author Nameyka Myrie
 * @version 14/10/2015
 */
public class Shift extends Cipher
{
	/**
	 * Construct a new Shift Cipher.
	 * @param plaintext The plaintext string
	 * @param key The encryption key
	 */
	public Shift(String input, String key, boolean plain)
	{
		if (plain) plaintext = input.toLowerCase();
		else ciphertext = input.toLowerCase();
		this.key = key;
	}
	/**
	 * Encrypts the plaintext by incrementing each letter by the key value.
	 */
	public void encrypt()
	{
		String cipher = "";
		output = "";
		int charAscii, holder;
		for (int i = 0; i < plaintext.length(); i++)
		{
			charAscii = (int) (plaintext.charAt(i) - 97);
			if (charAscii < 0)
			{
				cipher += plaintext.charAt(i);
				continue;
			}
			holder = (charAscii + Integer.parseInt(key)) % 26;
			cipher += (char) (holder + 97);
		}
		output += "Cipher: " + cipher;
		ciphertext = cipher;
	}
	/**
	 * Decrypts the ciphertext by decrementing each letter by the key value.
	 */
	public void decrypt()
	{
		String plain = "";
		output = "";
		int charAscii, holder;
		for (int i = 0; i < ciphertext.length(); i++)
		{
			charAscii = (int) (ciphertext.charAt(i) - 97);
			if (charAscii < 0)
			{
				plain += ciphertext.charAt(i);
				continue;
			}
			holder = ((charAscii - Integer.parseInt(key)) + 26) % 26;
			plain += (char) (holder + 97);
		}
		output += "Plaintext: " + plain;
		plaintext = plain;
	}
}