package ciphers;

import java.util.Scanner;

public class Shift extends Cipher{
	
	private Scanner scan;
	private String key;
	private String plainText;
	private String cipherText;
	
	public Shift(String key, String plainText){
		Scanner Scan = new Scanner(System.in);
		this.key = key;
		this.plainText = plainText.toLowerCase();
		
		encrypt(this.key, this.plainText);
		decrypt(this.key, this.cipherText);
		
	}
	//97 - 122
	@Override
        protected void encrypt(String plaintext, String key){
		String cipher = "";
		int charAscii, holder;
		for(int i = 0; i < plaintext.length(); i++){
			charAscii = (int) (plaintext.charAt(i) - 97);
			if(charAscii < 0){
				cipher += plaintext.charAt(i);
				continue;
			}
			holder = (charAscii + Integer.parseInt(key)) % 26;
			cipher += (char) (holder + 97);
		}
		System.out.println("Cipher: "+cipher);		
		this.cipherText = cipher;
	}
	
	@Override
        protected void decrypt(String ciphertext, String key) {
		String plaintext = "";
		int charAscii, holder;
		for(int i = 0; i < ciphertext.length(); i++){
			charAscii = (int) (ciphertext.charAt(i) - 97);
			if(charAscii < 0){
				plaintext += ciphertext.charAt(i);
				continue;
			}
			holder = ((charAscii - Integer.parseInt(key)) + 26) % 26;
			plaintext += (char) (holder + 97);
		}
		System.out.println("Plaintext: " + plaintext);
		System.out.println();
		this.plainText = plaintext;
	}
	
	/*public static void main(String[] args){
		new Shift("0", "Hi my name is");
		new Shift("2", "Hi my name is");
		new Shift("26", "Hi my name is");
		new Shift("26", "Love, forever! Love? is free ^_^");
		new Shift("10", "Love, forever! Love? is free ^_^");
	}*/

}