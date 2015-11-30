package ciphers;
/**
 * Rail Fence Cipher
 * @author Jack Taylor, Nameyka Myrie
 * @version 23/11/2015
 */
public class RailFence extends Cipher
{
	/**
	 * Construct a new Rail Fence Cipher.
	 * @param input The input text
	 * @param key The encryption key
	 * @param plain Whether the input is plaintext (true) or ciphertext (false)
	 */
	public RailFence(String input, String key, boolean plain)
	{
		if (plain) plaintext = input.toLowerCase().replaceAll("[.]", "");
		else ciphertext = input.toLowerCase().replaceAll("[.]", "");
		this.key = key;
	}
	/**
	 * Encrypts the plaintext by arranging it into a number of rails equal to the value of the key,
	 * then concatenates the rails to obtain the ciphertext.
	 */
	public void encrypt()
	{
		ciphertext = "";
		output = "";
		output += "Note: any full stops have been removed from the plaintext. \n\n";
		// Convert key to number
		int rails = Integer.parseInt(key);
		output += "Key: " + rails + "\n\n";
		output += "Plaintext: " + plaintext + "\n\n";
		// Initialise rails
		String[] temp = new String[rails];
		for (int i = 0; i < temp.length; i++)
		{
			temp[i] = "";
		}
		// Arrange plaintext into rails
		output += "Arrange plaintext into rails: \n";
		boolean directionDown = true;
		int index = 0;
		// Pad plaintext string so it ends on the top or bottom rail
		int initialLength = plaintext.length();
		int holder = initialLength % (rails - 1);
		int addOn = rails - holder + 1;
		if (holder != 1)
		{
			if (holder == 0)
			{
				plaintext += "-";
				addOn = 0;
			}
			while (addOn > 1)
			{
				plaintext += "-";
				addOn--;
			}
		}
		for (int i = 0; i < plaintext.length(); i++)
		{
			temp[index] += plaintext.charAt(i);
			for (int j = 0; j < temp.length; j++)
			{
				if (j != index) temp[j] += ".";
			}
			// Modify index so that it "zigzags"
			if (rails > 1)
			{
				if (directionDown) index++;
				else index--;
				if (index <= 0 || index >= temp.length - 1) directionDown = !directionDown;
			}
		}
		// Obtain ciphertext
		for (int i = 0; i < temp.length; i++)
		{
			output += temp[i] + "\n";
			ciphertext += temp[i].replaceAll("[.]", "");
		}
		output += "\n";
		output += "Ciphertext: " + ciphertext + "\n\n";
	}
	/**
	 * Decrypts the ciphertext.
	 */
	public void decrypt()
	{
		plaintext = "";
		output = "";
		int rails = Integer.parseInt(key);
		output += "Key: " + rails + "\n\n";
		output += "Ciphertext: " + ciphertext + "\n\n";
		// Get string length
		int strlen = ciphertext.length();
		int midRail = (int) strlen / (rails - 1);
		// Divide by 2 for outer
		int bottomRail = (int) Math.ceil(midRail / 2.0);
		int topRail = bottomRail;
		topRail += ((midRail % 2) == 0) ? 1 : 0;
		// Get rail start positions
		int[] startPos = new int[rails];
		startPos[0] = 0;
		for (int i = 1; i < rails; i++)
		{
			startPos[i] = topRail + (midRail * (i - 1));
		}
		int[] deRandom = new int[strlen];
		// Create sequence, pick out letters
		boolean directionDown = true, onOuterRail = true;
		int railTrack = -1, line = 0, outerRailCounter = 0;
		for (int i = 0; i < strlen; i++)
		{
			if (directionDown)
			{
				if (onOuterRail)
				{
					deRandom[i] = (startPos[++railTrack]) + outerRailCounter;
					onOuterRail = !onOuterRail;
				}
				else
				{
					deRandom[i] = (startPos[++railTrack]) + line;
				}
			}
			else
			{
				if (onOuterRail)
				{
					deRandom[i] = startPos[--railTrack] + outerRailCounter;
					onOuterRail = !onOuterRail;
				}
				else
				{
					deRandom[i] = startPos[--railTrack] + line;
				}
			}
			if (i > 0 && ((i) % (rails - 1) == 0))
			{
				directionDown = !directionDown;
				line++;
				if (line % 2 == 1)
				{
					outerRailCounter++;
				}
			}
			if (((railTrack + 2) == rails && directionDown) || (railTrack - 1 == 0) && !directionDown)
			{
				onOuterRail = !onOuterRail;
			}
		}
		// Get chars from the list in the order the array says
		String plain = "";
		for (int j = 0; j < strlen; j++)
		{
			plain += ciphertext.charAt(deRandom[j]);
		}
		output += "Plaintext: " + plain;
	}
        
    
    /*
     *  Validity of key for Raildence
     *  Should be an integer more than 1
     */

    @Override
    protected boolean isValidKey(String key) {
        try{
            int val = Integer.parseInt(key);
            return val > 1;
        } catch (NumberFormatException e) {
            System.out.println("This is not a number, number required");
        }
        
        return false;
        
    }
}
