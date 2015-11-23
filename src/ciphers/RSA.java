package ciphers;
/**
 * @author Nameyka Myrie, Jack Taylor
 * @version 23/11/2015
 */
public class RSA extends Cipher
{
	//private int prime1;
	//private int prime2;
	//private String plaintext = "This is some text";
	/**
	 * Construct a new RSA instance.
	* @param input The input text
	 * @param key The encryption key
	 * @param plain Whether the input is plaintext (true) or ciphertext (false)
	 */
	public RSA(String input, String key, boolean plain)
	{
		
	}
	/**
	 * Encrypts the plaintext.
	 */
	public void encrypt()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	/**
	 * Decrypts the ciphertext.
	 */
	public void decrypt()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}
	/**
	 * Calculates the totient value of a given number n. A totient value is the number of positive integers less than n
	 * which are relatively prime to it.
	 * @param n The number whose totient to calculate
	 * @return The totient value of n
	 */
	public static int phi(int n)
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
	private static int hcf(int x, int y)
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
}