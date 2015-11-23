package ciphers;
/**
 * One Time Pad
 * @author Nameyka Myrie
 * @version 23/11/2015
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
		if (plain) plaintext = input.toLowerCase().replaceAll("\\W", "");
		else ciphertext = input.toLowerCase().replaceAll("\\W", "");
		this.key = (key.toLowerCase()).replaceAll("\\s+", "");
	}
	/**
	 * Encrypts the plaintext by incrementing each letter by the corresponding key value mod key length.
	 */
	public void encrypt()
	{
		output = "";
		if (key.length() >= (plaintext.replaceAll("\\s+", "")).length())
		{
			String cipher = "";
			int charAscii, holder, keyAscii, keyTrack = 0;
			for (int i = 0; i < plaintext.length(); i++)
			{
				charAscii = (int) (plaintext.charAt(i) - 97);
				keyAscii = (int) (key.charAt(keyTrack) - 97);
				if (charAscii < 0)
				{
					cipher += plaintext.charAt(i);
					continue;
				}
				keyTrack++;
				holder = (charAscii + keyAscii) % 26;
				cipher += (char) (holder + 97);
			}
			output += "Cipher: " + cipher;
			ciphertext = cipher;
		}
		else
		{
			output += "CANNOT encrypt: Need a longer key";
		}
	}
	/**
	 * Decrypts the ciphertext by decrementing each letter by the corresponding key value mod key length.
	 */
	public void decrypt()
	{
		output = "";
		if (key.length() >= (ciphertext.replaceAll("\\s+", "")).length())
		{
			String plain = "";
			int charAscii, holder, keyTrack = 0, keyAscii;
			for (int i = 0; i < ciphertext.length(); i++)
			{
				charAscii = (int) (ciphertext.charAt(i) - 97);
				keyAscii = (int) (key.charAt(keyTrack) - 97);
				if (charAscii < 0)
				{
					plain += ciphertext.charAt(i);
					continue;
				}
				keyTrack++;
				holder = ((charAscii - keyAscii) + 26) % 26;
				plain += (char) (holder + 97);
			}
			output += "Plaintext: " + plain;
		}
		else
		{
			output += "CANNOT decrypt: Need a longer key";
		}
	}
}