package ciphers;
/**
 * Base class for a Cipher. Ciphers use a cryptosystem consisting of a plaintext string, a ciphertext string, a key,
 * an encryption function and a decryption function.
 * @author Jack Taylor
 * @version 23/11/2015
 */
public abstract class Cipher
{
	protected String plaintext;
	protected String ciphertext;
	protected String key;
	protected String output;
	/**
	 * Encrypts the plaintext string, modifying the ciphertext.
	 */
	public abstract void encrypt();
	/**
	 * Decrypts the ciphertext string, modifying the plaintext.
	 */
	public abstract void decrypt();
	/**
	 * @return The plaintext string
	 */
	public String getPlaintext()
	{
		return plaintext;
	}
	/**
	 * @return The ciphertext string
	 */
	public String getCiphertext()
	{
		return ciphertext;
	}
	/**
	 * @return The output text
	 */
	public String getOutput()
	{
		return output;
	}
}