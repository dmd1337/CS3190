package ciphers;
/**
 * Rail Fence Cipher
 * @author Jack Taylor
 * @version 17/10/2015
 */
public class RailFence extends Cipher
{
	/**
     * Construct a new Rail Fence Cipher.
     * @param plaintext The plaintext string
     * @param key The encryption key (i.e. number of rails)
     */
	public RailFence(String plaintext, String key)
	{
		this.plaintext = plaintext.toLowerCase().replaceAll("\\W",  "");
		ciphertext = "";
		this.key = key;
		encrypt();
		decrypt();
	}
	/**
	 * Encrypts the plaintext by arranging it into a number of rails equal to the value of the key, then
	 * concatenates the rails to obtain the ciphertext.
	 */
	protected void encrypt()
	{
		ciphertext = "";
		System.out.print("Rail Fence Cipher: Encryption Process \n\n");
		System.out.print("Note: any punctuation and spaces have been removed from the plaintext. \n\n");
		//Convert key to number
		int rails = Integer.parseInt(key);
		System.out.print("Key: " + rails + "\n\n");
		System.out.print("Plaintext: " + plaintext + "\n\n");
		//Initialise rails
		String[] temp = new String[rails];
		for (int i = 0; i < temp.length; i++) temp[i] = "";
		//Arrange plaintext into rails
		System.out.print("Arrange plaintext into rails: \n");
		boolean directionDown = true;
		int index = 0;
		for (int i = 0; i < plaintext.length(); i++)
		{
			temp[index] += plaintext.charAt(i);
			for (int j = 0; j < temp.length; j++) if (j != index) temp[j] += ".";
			//Modify index so that it "zigzags"
			if (rails > 1)
			{
				if (directionDown) index++;
				else index--;
				if (index <= 0 || index >= temp.length - 1) directionDown = !directionDown;
			}
		}
		//Obtain ciphertext
		for (int i = 0; i < temp.length; i++)
		{
			System.out.println(temp[i]);
			ciphertext += temp[i].replaceAll("\\W",  "");
		}
		System.out.print("\n");
		System.out.print("Ciphertext: " + ciphertext + "\n\n");
	}
	/**
	 * Decrypts the ciphertext
	 */
	protected void decrypt()
	{
		plaintext = "";
		System.out.print("Rail Fence Cipher: Decryption Process \n\n");
		int rails = Integer.parseInt(key);
		System.out.print("Key: " + rails + "\n\n");
		System.out.print("Ciphertext: " + ciphertext + "\n\n");
	}
}