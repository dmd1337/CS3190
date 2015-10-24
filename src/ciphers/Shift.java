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
	public Shift(String plaintext, String key)
	{
		this.key = key;
		this.plaintext = plaintext.toLowerCase();
		encrypt();
		decrypt();
	}
	/**
	 * Encrypts the plaintext by incrementing each letter by the key value.
	 */
	protected void encrypt()
	{
		String cipher = "";
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
		System.out.println("Cipher: " + cipher);
		ciphertext = cipher;
	}
	/**
	 * Decrypts the ciphertext by decrementing each letter by the key value.
	 */
	protected void decrypt()
	{
		String plain = "";
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
		System.out.println("Plaintext: " + plain);
		System.out.println();
		plaintext = plain;
	}
}