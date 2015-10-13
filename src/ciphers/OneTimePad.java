/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ciphers;

/**
 *
 * @author 
 */
public class OneTimePad extends Cipher{
    
    public OneTimePad(String plaintext, String key){
        this.key = (key.toLowerCase()).replaceAll("\\s+", "");
        this.plaintext = plaintext.toLowerCase();
        encrypt(this.plaintext, this.key);
        decrypt(this.ciphertext, this.key);
        System.out.println();
    }

    @Override
    protected void encrypt(String plaintext, String key) {        
        if(key.length() >= (plaintext.replaceAll("\\s+", "")).length()){
            
            String cipher = "";
            int charAscii, holder, keyAscii, keyTrack = 0;
            
            for (int i = 0; i < plaintext.length(); i++){
                
                charAscii = (int) (plaintext.charAt(i) - 97);
                keyAscii = (int) (key.charAt(keyTrack) - 97);
                
                if (charAscii < 0){
                    cipher += plaintext.charAt(i);
                    continue;
                }
                keyTrack++;
                holder = (charAscii + keyAscii) % 26;
                cipher += (char) (holder + 97);
            }
            
            System.out.println("Cipher: " + cipher);
            ciphertext = cipher;
        } else {
            System.out.println("CANNOT encrypt: Need a longer key");
        }
    }

    @Override
    protected void decrypt(String ciphertext, String key) {
        if(key.length() >= (plaintext.replaceAll("\\s+", "")).length()){
            
            String plain = "";
            int charAscii, holder, keyTrack = 0, keyAscii;
            
            for (int i = 0; i < ciphertext.length(); i++){
                    
                charAscii = (int) (ciphertext.charAt(i) - 97);
                keyAscii = (int) (key.charAt(keyTrack) - 97);
                
                if (charAscii < 0){
                    plain += ciphertext.charAt(i);
                    continue;
                }
                
                keyTrack++;
                holder = ((charAscii - keyAscii) + 26) % 26;
                plain += (char) (holder + 97);
            }
            
            System.out.println("Plaintext: " + plain);
            
        } else {
            System.out.println("CANNOT decrypt: Need a longer key");
        }
    }
    
}
