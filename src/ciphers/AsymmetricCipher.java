package ciphers;
/**
 * This class represents an asymmetric cipher. This has an additional key field to represent the private key;
 * in addition, the encrypt and decrypt methods are not "mirror images" of each other.
 * @version 09/10/2015
 */
public abstract class AsymmetricCipher extends Cipher
{
	protected String privateKey;
}