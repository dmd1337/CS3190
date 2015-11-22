/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciphers;

/**
 *
 * @author Nameyka Myrie
 * @version 19-Nov-2015
 */
public class RSAUser {

    private int privateKey;
    private int publicKey;
    
    public RSAUser(int privateKey, int publicKey){
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
    
    public int[] encrypt(int n, String plaintext) {
        
        //Convert to lower case, remove whitespace and punctuation
        plaintext = plaintext.toLowerCase().replaceAll("\\W", "");
        
        int[] plaintextNum = new int[plaintext.length()]; //plaintext to array
        int[] ciphertextNum = new int[plaintext.length()]; //plaintext to encrypt
        for(int i = 0; i < plaintext.length(); i++){
            
            //conver to number array
            plaintextNum[i] = ((int) plaintext.charAt(i)) - 97;
            //System.out.print(plaintextNum[i]);
            
            //encrypt function
            //ciphertextNum[i] = ((int) Math.pow(plaintextNum[i], this.privateKey)) % n; // need function too large for container
            ciphertextNum[i] = getMod(plaintextNum[i], this.privateKey, n);
            
            //getMod(plaintextNum[i], this.privateKey, n);
            //System.out.println(":"+ciphertextNum[i]);
            
        }
        
        return ciphertextNum;
        
        /*
        
        int outcome = (int) (Math.pow(charNum, key) % n);
        
        System.out.println(outcome);*/
    }
    
    private int getMod(int letter, int pow, int n){
        //Get binary representation
        String binary = Integer.toBinaryString(pow);
        //System.out.println(binary);
        //Array to hold previous computations
        int[] pastMod = new int[binary.length()];
        //To double value for exponent
        int doubler = 1;
        int currentVal = 0, lastVal = 0;
        //System.out.println("Here");
        for(int i = binary.length()-1; i >= 0; i--){
            //System.out.println(i+":ii");
            //System.out.println("Here 222");
            /*if(binary.charAt(i) == '1'){
                
                if(doubler == 1){
                    currentVal = letter % n;
                    lastVal = currentVal;
                }
                
                pastMod[i] = 
                
            
            } else {
                //This part is irrelevant but meh
                pastMod[i] = 0;
            }*/
            
            if(doubler == 1){
                pastMod[i] = letter % n;
                //System.out.println(doubler+":"+pastMod[i]);
                if(binary.charAt(i) == '1'){
                    currentVal = pastMod[i];
                    //System.out.println(doubler+":doubler");
                }
                doubler = doubler + doubler;
                continue;
            }
            
            pastMod[i] = (pastMod[i+1] * pastMod[i+1]) % n;
            
            //System.out.println(doubler+":"+pastMod[i]);
            
            if(binary.charAt(i) == '1'){
                if(currentVal == 0) currentVal = pastMod[i];
                
                currentVal = (currentVal * pastMod[i]) % n;
                //System.out.println(doubler+":doubler");
            }
            
            doubler = doubler + doubler;
        }
        //System.out.println("****"+currentVal);
            //System.out.println("");
        return currentVal;
    }
}
