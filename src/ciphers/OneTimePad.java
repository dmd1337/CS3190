package ciphers;
/**
 * @author
 */
public class OneTimePad extends Cipher
{
	public OneTimePad(String input, String key, boolean plain)
	{
		if (plain) plaintext = input.toLowerCase().replaceAll("\\W", "");
		else ciphertext = input.toLowerCase().replaceAll("\\W", "");
		this.key = (key.toLowerCase()).replaceAll("\\s+", "");
	}
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

    /*
     *  Validity of key for One-Time-Pad
        Should be at least equal lenght of the plaintext/ciphertext
     */
    @Override
    protected boolean isValidKey(String key) {
        int minVal = 0; 
        if(this.plaintext.length() > this.ciphertext.length()){
            minVal = this.plaintext.length();
        } else {
            minVal = this.ciphertext.length();
        }
        
        if(key.length() < minVal){
            System.out.println("Key is not long enough");
            return false;
        }
        
        return true;
    }
}