package program;
import ciphers.*;
/**
 * The main class for this program. Contains the main method.
 * @author Nameyka Myrie, Jack Taylor
 * @version 17/10/2015
 */
public class Main
{
	/**
	 * Main Method
	 */
	public static void main(String[] args)
    {
    	//new Shift("Ermmm...", "2");
    	//new VigenereCipher("this is a sentence", "word");
    	//new OneTimePad("This for encoding", "Ernie Ball Super Slinky"); //Converts everything to lowercase. Both Key and Text
    	//new OneTimePad("This for encoding", "There was a man called michael finnegan");
    	//new OneTimePad("Just for for tonight", "Ernie Ball Super Slinky");
    	//new OneTimePad("Just for for tonight", "Ernie Ball we uip we");  
    	//new OneTimePad("Just for for tonight", "Ernie Ball we uip weq");
		new RailFence("really long plaintext string", "6");
		/*
		 *  3 -> 4
		 *  2 -> 5
		 */
    }
}