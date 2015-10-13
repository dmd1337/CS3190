package program;
import ciphers.*;
/**
 *
 * @author Nameyka
 */
public class Main
{
    public static void main(String[] args)
    {
        new Shift("Ermmm...", "2");
        new VigenereCipher("this is a sentence", "word");
        new OneTimePad("This for encoding", "Ernie Ball Super Slinky"); //Converts everything to lowercase. Both Key and Text
        new OneTimePad("This for encoding", "There was a man called michael finnegan");
        new OneTimePad("Just for for tonight", "Ernie Ball Super Slinky");
        new OneTimePad("Just for for tonight", "Ernie Ball we uip we");  
        new OneTimePad("Just for for tonight", "Ernie Ball we uip weq");
    }
}