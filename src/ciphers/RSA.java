/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ciphers;

/**
 *
 * @author Nameyka Myrie, Jack Taylor
 * @version 19-Nov-2015
 */
public class RSA extends Cipher{
    
    private int prime1;
    private int prime2;
    private String plaintext = "This is some text";
    
    
    /**
    * Calculates the totient value of a given number n. A totient value is the number of positive integers less than n which are relatively
    * prime to it.
    * @param n The number whose totient to calculate
    * @return The totient value of n
    */
    public static int phi(int n)
    {
        int tot = 1;
        for (int i = 2; i < n; i++)
        {
            if (hcf(n, i) == 1) tot++;
        }
        return tot;
    }
    /**
     * Calculates the highest common factor of two integers. If this is equal to 1, the two numbers are relatively prime to each other and
     * therefore the totient value can be incremented.
     * @param x The first integer
     * @param y The second integer
     * @return The greatest common divisor
     */
    private static int hcf(int x, int y)
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

    @Override
    public void encrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void decrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
