package program;
import ciphers.*;
public class Main
{
	public static void main(String[] args)
	{
		RSAUser rawr = new RSAUser(53, 17);
		rawr.encrypt(77,  "hello");
	}
}