package ciphers;
/**
 * Base class for a Cipher. Ciphers use a cryptosystem consisting of a plaintext string, a ciphertext string, a key, an encryption function and a decryption function.
 * @author Jack Taylor
 * @version 14/10/2015
 */
public abstract class Cipher
{
	protected String plaintext;
	protected String ciphertext;
	protected String key;
	protected String encryptOutput;
	protected String decryptOutput;
	/**
	 * Encrypts the plaintext string, modifying the ciphertext.
	 */
	protected abstract void encrypt();
	/**
	 * Decrypts the ciphertext string, modifying the plaintext.
	 */
	protected abstract void decrypt();
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
	 * @return The encryption output
	 */
	public String getEncryptOutput()
	{
		return encryptOutput;
	}
	/**
	 * @return The decryption output
	 */
	public String getDecryptOutput()
	{
		return decryptOutput;
	}
}