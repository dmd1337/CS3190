package ciphers;
/**
 * RSA Cipher
 * @author Nameyka Myrie, Jack Taylor
 * @version 23/11/2015
 */
public class RSA extends Cipher
{
	/* For Nammy:
	 * I have decided that this would be much simpler if we provided two prime numbers instead of a key.
	 * With these, the algorithm should generate a valid value for n, then values for kE and kD.
	 * Once kE and kD are calculated, we can plug those into the encryption as normal.
	 * 
	 * Also, decryption will work slightly differently. Instead of a regular ciphertext like those in the other
	 * ciphers, the ciphertext will be a sequence of numbers. We will need to ensure that these are decrypted individually,
	 * then converted to text AFTER the decryption process is complete.
	 */
	private int ke;
	private int kd;
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
			ke = Integer.parseInt(key);
			kd = 0;
			int temp = 2;
			// Calculate decryption key
			while (kd == 0 && temp < 2500000)
			{
				if ((ke * temp) % phi(n) == 1) kd = temp;
				else temp++;
			}
			
		}
		else
		{
			ciphertext = input.toLowerCase().replaceAll("\\W", "");
			plaintext = "";
			kd = Integer.parseInt(key);
			ke = 0;
		}
	}
	/**
	 * Encrypts the plaintext.
	 */
	public void encrypt()
	{
		output = "";
		if (kd == 0)
		{
			output += "Error: could not find decryption key.";
		}
		else
		{
			// Display decryption key
			output += "Your decryption key is " + kd + ". Please enter this into the key field to decrypt the ciphertext.\n\n";
			// Convert numbers to plaintext
			int[] plaintextNum = new int[plaintext.length()];
			// Numeric representation of plaintext
			output += "Plaintext: " + plaintext + "\n";
			output += "Numeric Representation: \n";
			for (int i = 0; i < plaintext.length(); i++)
			{
				if (plaintext.charAt(i) < 97 || plaintext.charAt(i) > 122) continue;
				plaintextNum[i] = plaintext.charAt(i) - 97;
				output += String.format("%02d", plaintextNum[i]) + " ";
			}
			output += "\n\n";
			// Encryption process
			output += "For each character p, perform p ^ " + ke + " % " + n + "\n";
			for (int i = 0; i < plaintext.length(); i++)
			{
				int ciphertextNum = getMod(plaintextNum[i], ke, n);
				output += String.format("%02d", ciphertextNum) + " ";
				ciphertext += ciphertextNum + " ";
			}
			output += "\n\n";
			// Print ciphertext
			output += "Ciphertext: " + ciphertext + "\n\n";
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
			int plaintextNum = getMod(ciphertextNum[i], kd, n);
			plaintext += plaintextNum;
		}
	}
	/**
	 * Calculates the totient value of a given number n. A totient value is the number of positive integers less than n
	 * which are relatively prime to it.
	 * @param n The number whose totient to calculate
	 * @return The totient value of n
	 */
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
	 * Performs the calculation for a single character
	 * @param letter
	 * @param pow
	 * @param n
	 * @return The result of the calculation
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
		return currentVal;
	}
}