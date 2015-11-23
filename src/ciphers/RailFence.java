package ciphers;
/**
 * Rail Fence Cipher
 * @author Jack Taylor, Nameyka Myrie
 * @version 17/10/2015
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
	 * Encrypts the plaintext by arranging it into a number of rails equal to the value of the key, then concatenates the rails to obtain the ciphertext.
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
		/*
		 * if ((initialLength - 1) % (rails - 1) != 0) { for (int i = 0; i < (initialLength - 1) % (rails - 1); i++) { plaintext += "-"; } }
		 */
		System.out.println("Thing");
		int holder = initialLength % (rails - 1); // holder == remainder
		// int nextSet = (int) Math.ceil(initialLength / (rails - 1) * 1.0); //Where would the next end rail be
		// if(divv % 2 == 1) holder++; //invert it
		System.out.println("Holder:" + holder);
		// holder == remainder - rails (+1)
		int addOn = rails - holder + 1; // Not sure why it's +1 on the end
		System.out.println("addOn: " + addOn);
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
				// holder--;
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
	 * Decrypts the ciphertext
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
		// System.out.println("stlen"+strlen);
		// divide by 3.round (gives middles)
		int midRail = (int) strlen / (rails - 1);
		System.out.println("midRail:" + midRail);
		// divide by 2 for outer
		int bottomRail = (int) Math.ceil(midRail / 2.0); // always gives bottom rail
		// System.out.println(Math.ceil(midRail/2.0));
		System.out.println("br" + bottomRail);
		int topRail = bottomRail;
		// topRail += ((midRail % 2) == 0)? 0 : 1; // To make it accurate for top rail. I'm not sure why this is working
		System.out.println("midRail:" + midRail);
		topRail += ((midRail % 2) == 0) ? 1 : 0;
		System.out.println("topR:" + topRail);
		// get rail start positions
		int[] startPos = new int[rails];
		startPos[0] = 0;
		for (int i = 1; i < rails; i++)
		{
			startPos[i] = topRail + (midRail * (i - 1));// Should probably test this
			// System.out.println("startPos: " +startPos[i]);
		}
		int[] deRandom = new int[strlen];
		// create sequence. Pick out letters
		boolean directionDown = true, onOuterRail = true;
		int railTrack = -1, line = 0, outerRailCounter = 0;
		// deRandom[0] = startPos[++railTrack] + line;
		// deRandom[0] = 0;
		// System.out.println("DR:"+deRandom[0]);
		for (int i = 0; i < strlen; i++)
		{
			// System.out.println(line);
			//
			// Need to add another counter for the middle and outer rails
			// Bottom rail is the same as the top rail when the counter is odd
			// If they're different, the top one is higher
			// System.out.println("i:"+i);
			// System.out.println("Railb4:"+railTrack);
			if (directionDown)
			{
				// System.out.println("Down");
				// System.out.println("rt"+(railTrack++));
				if (onOuterRail)
				{
					// System.out.println("Line" + line +", count:" + outerRailCounter+", outer" + railTrack);
					deRandom[i] = (startPos[++railTrack]) + outerRailCounter;
					// System.out.println("deRandom1:"+deRandom[i]);
					// System.out.println("Line" + line +", count:" + outerRailCounter+", outer" + railTrack);
					onOuterRail = !onOuterRail;
				}
				else
				{
					// System.out.println("Else");
					deRandom[i] = (startPos[++railTrack]) + line;
					// System.out.println("deRandom2:"+deRandom[i]);
					// System.out.println("Line" + line +", count:" + line);
				}
				// ++railTrack;
				// System.out.println("rt"+railTrack);
				// System.out.println("Down " +i);
			}
			else
			{
				// System.out.println("Up");
				// System.out.println(railTrack);//1 and 0?
				if (onOuterRail)
				{
					deRandom[i] = startPos[--railTrack] + outerRailCounter;
					// System.out.println("Line" + line +", count:" + outerRailCounter+", outer12");
					onOuterRail = !onOuterRail;
				}
				else
				{
					deRandom[i] = startPos[--railTrack] + line;
					// System.out.println("Line" + line +", count:" + line);
				}
				// --railTrack;
				// System.out.println("rt"+railTrack);
				// System.out.println("UP " + i);
			}
			// System.out.println("RailAfter:"+railTrack);
			if (i > 0 && ((i) % (rails - 1) == 0))
			{
				directionDown = !directionDown;
				// System.out.println("Change dircetion");
				line++;
				if (line % 2 == 1)
				{
					outerRailCounter++;
					// System.out.println("OuterRailCounter: "+outerRailCounter);
				}
			}
			// System.out.println("Line: " + line);
			if (((railTrack + 2) == rails && directionDown) || (railTrack - 1 == 0) && !directionDown)
			{
				// System.out.println("rt" + railTrack);
				onOuterRail = !onOuterRail;
			}
		}
		// See array
		for (int i = 0; i < strlen; i++)
		{
			System.out.print((i + 1) + ":" + deRandom[i] + ", ");
		}
		// Get chars from the list in the order the array says
		String plain = "";
		// System.out.println(ciphertext.charAt(deRandom[27]));
		System.out.println("rand" + deRandom.length);
		// plain += ciphertext.charAt(deRandom[1]);
		for (int j = 0; j < strlen; j++)
		{
			plain += ciphertext.charAt(deRandom[j]);
		}
		output += "Plaintext: " + plain;
	}
}
