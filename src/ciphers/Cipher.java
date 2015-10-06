package ciphers;
/**
 * Base class for a Cipher. Ciphers use a cryptosystem consisting of a plaintext string, a ciphertext string,
 * a key, an encryption function and a decryption function.
 * @author 
 * @version 06/10/2015
 */
public abstract class Cipher
{
	protected String plaintext;
	protected String ciphertext;
	protected String key;
	protected abstract void encrypt(String plaintext, String key);
	protected abstract void decrypt(String ciphertext, String key);
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
}