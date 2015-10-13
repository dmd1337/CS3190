package ciphers;

public class Shift extends Cipher
{	
    
	public Shift(String plaintext, String key)
	{
		this.key = key;
		this.plaintext = plaintext.toLowerCase();
		encrypt(this.plaintext, this.key);
		decrypt(this.ciphertext, this.key);
	}
	//97 - 122
	protected void encrypt(String plaintext, String key)
	{
		String cipher = "";
		int charAscii, holder;
		for (int i = 0; i < plaintext.length(); i++)
		{
			charAscii = (int) (plaintext.charAt(i) - 97);
			if (charAscii < 0)
			{
				cipher += plaintext.charAt(i);
				continue;
			}
			holder = (charAscii + Integer.parseInt(key)) % 26;
			cipher += (char) (holder + 97);
		}
		System.out.println("Cipher: " + cipher);		
		ciphertext = cipher;
	}
	protected void decrypt(String ciphertext, String key)
	{
		String plain = "";
		int charAscii, holder;
		for (int i = 0; i < ciphertext.length(); i++)
		{
			charAscii = (int) (ciphertext.charAt(i) - 97);
			if (charAscii < 0)
			{
				plain += ciphertext.charAt(i);
				continue;
			}
			holder = ((charAscii - Integer.parseInt(key)) + 26) % 26;
			plain += (char) (holder + 97);
		}
		System.out.println("Plaintext: " + plain);
		System.out.println();
		plaintext = plain;
	}
	/*public static void main(String[] args)
	{
		new Shift("0", "Hi my name is");
		new Shift("2", "Hi my name is");
		new Shift("26", "Hi my name is");
		new Shift("26", "Love, forever! Love? is free ^_^");
		new Shift("10", "Love, forever! Love? is free ^_^");
	}*/
}