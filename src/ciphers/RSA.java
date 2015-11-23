package ciphers;
/**
 * RSA Cipher
 * @author Nameyka Myrie, Jack Taylor
 * @version 23/11/2015
 */
public class RSA extends Cipher
{
	private int priv;
	private final int n = 77;
	/**
	 * Construct a new RSA instance.
	 * @param input The input text
	 * @param key The encryption key
	 * @param plain Whether the input is plaintext (true) or ciphertext (false)
	 */
	public RSA(String input, String key, boolean plain)
	{
		if (plain)
		{
			plaintext = input.toLowerCase().replaceAll("\\W", "");
			ciphertext = "";
			this.key = key;
		}
		else
		{
			ciphertext = input.toLowerCase().replaceAll("\\W", "");
			plaintext = "";
			priv = Integer.parseInt(key);
		}
	}
	/**
	 * Encrypts the plaintext.
	 */
	public void encrypt()
	{
		output = "";
		priv = 17;
		int[] plaintextNum = new int[plaintext.length()];
		int keyNum = Integer.parseInt(key);
		for (int i = 0; i < plaintext.length(); i++)
		{
			plaintextNum[i] = ((int)plaintext.charAt(i)) - 97;
			int ciphertextNum = getMod(plaintextNum[i], keyNum, n);
			ciphertext += ciphertextNum;
		}
	}
	/**
	 * Decrypts the ciphertext.
	 */
	public void decrypt()
	{
		output = "";
		int[] ciphertextNum = new int[ciphertext.length()];
		for (int i = 0; i < ciphertext.length(); i++)
		{
			ciphertextNum[i] = ((int)ciphertext.charAt(i)) - 97;
			int plaintextNum = getMod(ciphertextNum[i], priv, n);
			plaintext += plaintextNum;
		}
	}
	/**
	 * Calculates the totient value of a given number n. A totient value is the number of positive integers less than n
	 * which are relatively prime to it.
	 * @param n The number whose totient to calculate
	 * @return The totient value of n
	 */
	@SuppressWarnings("unused")
	private int phi(int n)
	{
		int tot = 1;
		for (int i = 2; i < n; i++)
		{
			if (hcf(n, i) == 1) tot++;
		}
		return tot;
	}
	/**
	 * Calculates the highest common factor of two integers. If this is equal to 1, the two numbers are relatively prime
	 * to each other and therefore the totient value can be incremented.
	 * @param x The first integer
	 * @param y The second integer
	 * @return The highest common factor
	 */
	private int hcf(int x, int y)
	{
		int temp;
		while (y != 0)
		{
			temp = x;
			x = y;
			y = temp % y;
		}
		return x;
	}
	/**
	 * Add description here
	 * @param letter
	 * @param pow
	 * @param n
	 * @return
	 */
	private int getMod(int letter, int pow, int n)
	{
		// Get binary representation
		String binary = Integer.toBinaryString(pow);
		// Array to hold previous computations
		int[] pastMod = new int[binary.length()];
		// To double value for exponent
		int doubler = 1;
		int currentVal = 0;
		for (int i = binary.length() - 1; i >= 0; i--)
		{
			if (doubler == 1)
			{
				pastMod[i] = letter % n;
				if (binary.charAt(i) == '1')
				{
					currentVal = pastMod[i];
				}
				doubler = doubler + doubler;
				continue;
			}
			pastMod[i] = (pastMod[i + 1] * pastMod[i + 1]) % n;
			if (binary.charAt(i) == '1')
			{
				if (currentVal == 0) currentVal = pastMod[i];
				currentVal = (currentVal * pastMod[i]) % n;
			}
			doubler = doubler + doubler;
		}
		output += "* " + currentVal + "\n";
		return currentVal;
	}
}