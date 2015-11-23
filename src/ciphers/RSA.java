package ciphers;
/**
 * @author Nameyka Myrie, Jack Taylor
 * @version 23/11/2015
 */
public class RSA extends Cipher
{
	//private int prime1;
	//private int prime2;
	private int priv;
	private int n;
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
		n = 77;
	}
	/**
	 * Encrypts the plaintext.
	 */
	public void encrypt()
	{
		output = "";
		priv = 17;
		int[] plaintextNum = new int[plaintext.length()]; // plaintext to array
		int keyNum = Integer.parseInt(key);
		for (int i = 0; i < plaintext.length(); i++)
		{
			// conver to number array
			plaintextNum[i] = ((int) plaintext.charAt(i)) - 97;
			// System.out.print(plaintextNum[i]);
			// encrypt function
			// ciphertextNum[i] = ((int) Math.pow(plaintextNum[i], this.privateKey)) % n; // need function too large for container
			int ciphertextNum = getMod(plaintextNum[i], keyNum, n);
			ciphertext += ciphertextNum;
			// getMod(plaintextNum[i], this.privateKey, n);
			// System.out.println(":"+ciphertextNum[i]);
		}
	}
	/**
	 * Decrypts the ciphertext.
	 */
	public void decrypt()
	{
		output = "";
		int[] ciphertextNum = new int[ciphertext.length()]; // plaintext to array
		for (int i = 0; i < ciphertext.length(); i++)
		{
			// conver to number array
			ciphertextNum[i] = ((int) ciphertext.charAt(i)) - 97;
			// System.out.print(plaintextNum[i]);
			// encrypt function
			// ciphertextNum[i] = ((int) Math.pow(plaintextNum[i], this.privateKey)) % n; // need function too large for container
			int plaintextNum = getMod(ciphertextNum[i], priv, n);
			plaintext += plaintextNum;
			// getMod(plaintextNum[i], this.privateKey, n);
			// System.out.println(":"+ciphertextNum[i]);
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
	private int getMod(int letter, int pow, int n)
	{
		// Get binary representation
		String binary = Integer.toBinaryString(pow);
		// System.out.println(binary);
		// Array to hold previous computations
		int[] pastMod = new int[binary.length()];
		// To double value for exponent
		int doubler = 1;
		int currentVal = 0;
		//int lastVal = 0;
		// System.out.println("Here");
		for (int i = binary.length() - 1; i >= 0; i--)
		{
			// System.out.println(i+":ii");
			// System.out.println("Here 222");
			/*
			 * if(binary.charAt(i) == '1'){ if(doubler == 1){ currentVal = letter % n; lastVal = currentVal; } pastMod[i] = } else { //This part is irrelevant but meh pastMod[i] = 0; }
			 */
			if (doubler == 1)
			{
				pastMod[i] = letter % n;
				// System.out.println(doubler+":"+pastMod[i]);
				if (binary.charAt(i) == '1')
				{
					currentVal = pastMod[i];
					// System.out.println(doubler+":doubler");
				}
				doubler = doubler + doubler;
				continue;
			}
			pastMod[i] = (pastMod[i + 1] * pastMod[i + 1]) % n;
			// System.out.println(doubler+":"+pastMod[i]);
			if (binary.charAt(i) == '1')
			{
				if (currentVal == 0) currentVal = pastMod[i];
				currentVal = (currentVal * pastMod[i]) % n;
				// System.out.println(doubler+":doubler");
			}
			doubler = doubler + doubler;
		}
		output += "* " + currentVal + "\n";
		// System.out.println("");
		return currentVal;
	}
}