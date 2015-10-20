package program;
/**
 * A class containing a method which calculates totient values.
 * @author Jack Taylor
 * @version 20/10/2015
 */
public class Totient
{
	/**
	 * Calculates the totient value of a given number n. A totient value is the number of positive integers less
	 * than n which are relatively prime to it.
	 * @param n The number whose totient to calculate
	 * @return The totient value of n
	 */
	public static int phi(int n)
	{
		int tot = 1;
		for (int i = 2; i < n; i++) if (gcd(n, i) == 1) tot++;
		return tot;
	}
	/**
	 * Calculates the greatest common divisor (also known as highest common factor of two integers. If this is equal to 1, the two numbers
	 * are relatively prime to each other and therefore the totient value can be incremented.
	 * @param x The first integer
	 * @param y The second integer
	 * @return The greatest common divisor
	 */
	private static int gcd(int x, int y)
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
}