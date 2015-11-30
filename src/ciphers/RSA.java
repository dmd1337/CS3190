package ciphers;
/**
 * RSA Cipher
 * @author Nameyka Myrie, Jack Taylor
 * @version 23/11/2015
 */
public class RSA extends Cipher
{
	private int p1;
	private int p2;
	private int modulo;
	private int ke;
	private int kd;
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
			String[] primes = key.split(" ");
			p1 = Integer.parseInt(primes[0]);
			p2 = Integer.parseInt(primes[1]);
			modulo = 0;
			ke = 0;
			kd = 0;
		}
		else
		{
			plaintext = "";
			ciphertext = input;
			this.key = key;
			p1 = 0;
			p2 = 0;
			String[] keys = key.split(" ");
			modulo = Integer.parseInt(keys[0]);
			ke = 0;
			kd = Integer.parseInt(keys[1]);
		}
	}
	/**
	 * Encrypts the plaintext.
	 */
	public void encrypt()
	{
		output = "";
		// Calculate modulo
		int tempn = 2;
		while (modulo == 0)
		{
			if (phi(tempn) == (p1 - 1) * (p2 - 1)) modulo = tempn;
			modulo = p1 * p2;
			tempn++;
		}
		output += "Modulo: " + modulo + "\n";
		// Calculate keys
		while (kd == 0)
		{
			int tempe = ke;
			while (ke == 0 && tempe < modulo)
			{
				if (hcf(tempe, phi(modulo)) == 1 && tempe > 2) ke = tempe;
				tempe++;
			}
			
			int tempd = 2;
			while (kd == 0 && tempd < modulo)
			{
				if ((ke * tempd) % phi(modulo) == 1) kd = tempd;
				tempd++;
			}
		}
		output += "Encryption key: " + ke + "\n";
		output += "Decryption key: " + kd + "\n\n";
		output += "IMPORTANT: IF YOU WOULD LIKE TO DECRYPT THE CIPHERTEXT, PLEASE RETAIN THE MODULO AND DECRYPTION KEY.\n\n";
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
		output += "For each character p, perform p ^ " + ke + " % " + modulo + "\n";
		for (int i = 0; i < plaintext.length(); i++)
		{
			int ciphertextNum = getMod(plaintextNum[i], ke);
			output += String.format("%02d", ciphertextNum) + " ";
			ciphertext += ciphertextNum + " ";
		}
		output += "\n\n";
		// Print ciphertext
		output += "Ciphertext: " + ciphertext + "\n\n";
	}
	/**
	 * Decrypts the ciphertext.
	 */
	public void decrypt()
	{
		output = "";
		String[] tempCipher = ciphertext.split(" ");
		int[] ciphertextNum = new int[tempCipher.length];
		for (int i = 0; i < tempCipher.length; i++) ciphertextNum[i] = Integer.parseInt(tempCipher[i]);
		// Print ciphertext
		output += "Ciphertext: " + ciphertext + "\n\n";
		// Decryption process
		output += "For each character c, perform c ^ " + kd + " % " + modulo + "\n";
		for (int i = 0; i < ciphertextNum.length; i++)
		{
			int plaintextNum = getMod(ciphertextNum[i], kd);
			output += String.format("%02d", plaintextNum) + " ";
			plaintext += (char)(plaintextNum + 97);
		}
		output += "\n\n";
		// Print plaintext
		output += "Convert plaintext to characters: " + plaintext + "\n\n";
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
	 * @param letter The individual character for the calculation
	 * @param pow The exponent value
	 * @return The result of the calculation
	 */
	private int getMod(int letter, int pow)
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
				pastMod[i] = letter % modulo;
				if (binary.charAt(i) == '1')
				{
					currentVal = pastMod[i];
				}
				doubler = doubler + doubler;
				continue;
			}
			pastMod[i] = (pastMod[i + 1] * pastMod[i + 1]) % modulo;
			if (binary.charAt(i) == '1')
			{
				if (currentVal == 0) currentVal = pastMod[i];
				currentVal = (currentVal * pastMod[i]) % modulo;
			}
			doubler = doubler + doubler;
		}
		return currentVal;
	}
}