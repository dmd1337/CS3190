package ciphers;
/**
 *
 * @author 
 */
public class OneTimePad extends Cipher
{    
	public OneTimePad(String plaintext, String key)
	{
		this.key = (key.toLowerCase()).replaceAll("\\s+", "");
		this.plaintext = plaintext.toLowerCase();
		encrypt();
		decrypt();
		System.out.println();
	}
	protected void encrypt()
	{        
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
			System.out.println("Cipher: " + cipher);
			ciphertext = cipher;
		}
		else
		{
			System.out.println("CANNOT encrypt: Need a longer key");
		}
	}
    protected void decrypt()
    {
    	if (key.length() >= (plaintext.replaceAll("\\s+", "")).length())
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
    		System.out.println("Plaintext: " + plain);
    	}
    	else
    	{
    		System.out.println("CANNOT decrypt: Need a longer key");
    	}
	}
}