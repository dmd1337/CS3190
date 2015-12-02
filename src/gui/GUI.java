package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ciphers.*;
/**
 * Top-level class to handle the GUI. Also contains the program's main method.
 * @author Hayley Billingham, Jack Taylor
 * @version 02/12/2015
 */
public class GUI extends JFrame
{
	private static final long serialVersionUID = 5125858476108975022L;
	private JLabel title;
	//Input Section
	private JPanel radioPanel;
	private JLabel lblInputText;
	private JLabel lblChooseCipher;
	private JLabel lblCipherBreakdown;
	private JLabel lblCipherSummary;
	private JTextArea txtInputText;
	private JTextArea txtCipherSummary;
	private JComboBox<String> cobChooseCipher;
	private JRadioButton rbSubstitution;
	private JRadioButton rbTransposition;
	private JRadioButton rbSymmetric;
	private JRadioButton rbAsymmetric;
	private JRadioButton rbStream;
	private JRadioButton rbBlock;
	//Key Section
	private JLabel lblInputKey;
	private JTextArea txtInputKey;
	//Buttons
	private JButton btnEncrypt;
	private JButton btnDecrypt;
	//Output Section
	private JLabel lblOutput;
	private JTextArea txtOutput;
	/**
	 * Creates GUI
	 */
	public GUI()
	{
		initTitle();
		initTheme();
		initInputText();
		initInputKey();
		initChooseCipher();
		initCipherBreakdown();
		initCipherSummary();
		initEncryptButton();
		initDecryptButton();
		initOutput();
		initLayout();
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	/**
	 * Initialises the GUI theme.
	 */
	private void initTheme()
	{
		try
		{
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch (UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}
	/**
	 * Initialises the title label.
	 */
	private void initTitle()
	{
		title = new JLabel("010000110100100101010000010010000100010101010010");
		title.setToolTipText("Means \"CIPHER\" in ASCII");
		title.setFont(new Font("Tahoma", Font.BOLD, 28));
		title.setSize(140, 35);
	}
	/**
	 * Initialises the plaintext input area.
	 */
	private void initInputText()
	{
		lblInputText = new JLabel("Input Text");
		txtInputText = new JTextArea();
		lblInputText.setToolTipText("This will be the plaintext if you are encrypting, or the ciphertext if you are decrypting.");
		lblInputText.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInputText.setSize(464, 20);
		txtInputText.setPreferredSize(new Dimension(464, 50));
		txtInputText.setFont(new Font("Tahoma", 0, 14));
		txtInputText.setLineWrap(true);
		txtInputText.setWrapStyleWord(true);
	}
	/**
	 * Initialise the key input text area.
	 */
	private void initInputKey()
	{
		lblInputKey = new JLabel("Input Key");
		txtInputKey = new JTextArea();
		lblInputKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInputKey.setSize(464, 20);
		txtInputKey.setPreferredSize(new Dimension(464, 30));
		txtInputKey.setFont(new Font("Tahoma", 0, 14));
		txtInputKey.setLineWrap(true);
		txtInputKey.setWrapStyleWord(true);
	}
	/**
	 * Initialises the cipher selection box.
	 */
	private void initChooseCipher()
	{
		String[] cipherNames =
		{
			"None",
			"Shift Cipher",
			"Vigenere Cipher",
			"One Time Pad",
			"Rail Fence Cipher",
			"RSA"
		};
		lblChooseCipher = new JLabel("Choose Cipher");
		cobChooseCipher = new JComboBox<String>(cipherNames);
		lblChooseCipher.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChooseCipher.setSize(464, 20);
		cobChooseCipher.setFont(new Font("Tahoma", Font.BOLD, 20));
		cobChooseCipher.setPreferredSize(new Dimension(464, 50));
		cobChooseCipher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cobChooseCipher.getSelectedItem() == "None")
				{
					lblInputKey.setText("Input Stuff");
					txtCipherSummary.setText("The None Cipher does exactly what you would expect: absolutely nothing.\n\n"
						+ "Valid key values: Anything, I guess...");
					rbSubstitution.setEnabled(false);
					rbSubstitution.setSelected(false);
					rbTransposition.setEnabled(false);
					rbTransposition.setSelected(false);
					rbSymmetric.setEnabled(false);
					rbSymmetric.setSelected(false);
					rbAsymmetric.setEnabled(false);
					rbAsymmetric.setSelected(false);
					rbStream.setEnabled(false);
					rbStream.setSelected(false);
					rbBlock.setEnabled(false);
					rbBlock.setSelected(false);
				}
				else if (cobChooseCipher.getSelectedItem() == "Shift Cipher")
				{
					lblInputKey.setText("Input Key");
					txtCipherSummary.setText("The Shift Cipher is very simple; it takes the key and shifts each letter by "
						+ "its value. For example, a key of 2 will shift \"a\" to \"c\".\n\n"
						+ "Valid key values: Any number between 1 and 25.");
					rbSubstitution.setEnabled(true);
					rbSubstitution.setSelected(true);
					rbTransposition.setEnabled(false);
					rbTransposition.setSelected(false);
					rbSymmetric.setEnabled(true);
					rbSymmetric.setSelected(true);
					rbAsymmetric.setEnabled(false);
					rbAsymmetric.setSelected(false);
					rbStream.setEnabled(true);
					rbStream.setSelected(true);
					rbBlock.setEnabled(false);
					rbBlock.setSelected(false);
				}
				else if (cobChooseCipher.getSelectedItem() == "Vigenere Cipher")
				{
					lblInputKey.setText("Input Key");
					txtCipherSummary.setText("The Vigenere Cipher is a block version of the Shift cipher. It shifts each "
						+ "letter by the corresponding key value, returning to the start of the key "
						+ "when it reaches the last key letter.\n\n"
						+ "Valid key values: Any sequence of letters; no numbers or punctuation.");
					rbSubstitution.setEnabled(true);
					rbSubstitution.setSelected(true);
					rbTransposition.setEnabled(false);
					rbTransposition.setSelected(false);
					rbSymmetric.setEnabled(true);
					rbSymmetric.setSelected(true);
					rbAsymmetric.setEnabled(false);
					rbAsymmetric.setSelected(false);
					rbStream.setEnabled(false);
					rbStream.setSelected(false);
					rbBlock.setEnabled(true);
					rbBlock.setSelected(true);
				}
				else if (cobChooseCipher.getSelectedItem() == "One Time Pad")
				{
					lblInputKey.setText("Input Key");
					txtCipherSummary.setText("The One Time Pad is functionally identical to the Vigenere Cipher, except "
						+ "the key is at least as long as the plaintext. Typically, the key would be a long literary excerpt.\n\n"
						+ "Valid key values: Same as Vigenere, except it must be at least as long as the plaintext.");
					rbSubstitution.setEnabled(true);
					rbSubstitution.setSelected(true);
					rbTransposition.setEnabled(false);
					rbTransposition.setSelected(false);
					rbSymmetric.setEnabled(true);
					rbSymmetric.setSelected(true);
					rbAsymmetric.setEnabled(false);
					rbAsymmetric.setSelected(false);
					rbStream.setEnabled(false);
					rbStream.setSelected(false);
					rbBlock.setEnabled(true);
					rbBlock.setSelected(true);
				}
				else if (cobChooseCipher.getSelectedItem() == "Rail Fence Cipher")
				{
					lblInputKey.setText("Input Key");
					txtCipherSummary.setText("The Rail Fence Cipher is a transposition cipher which organises the "
						+ "plaintext into a series of rows (the number of rows being determined by \n"
						+ "the key). The rows are then added to each other to obtain the ciphertext.\n\n"
						+ "Valid key values: Any number greater than or equal to 2.");
					rbSubstitution.setEnabled(false);
					rbSubstitution.setSelected(false);
					rbTransposition.setEnabled(true);
					rbTransposition.setSelected(true);
					rbSymmetric.setEnabled(true);
					rbSymmetric.setSelected(true);
					rbAsymmetric.setEnabled(false);
					rbAsymmetric.setSelected(false);
					rbStream.setEnabled(true);
					rbStream.setSelected(true);
					rbBlock.setEnabled(false);
					rbBlock.setSelected(false);
				}
				else if (cobChooseCipher.getSelectedItem() == "RSA")
				{
					lblInputKey.setText("Input Key/Primes");
					txtCipherSummary.setText("Encryption: once the keys are calculated from the supplied primes, each character in the plaintext "
						+ "is subject to p ^ (encryption key) % (modulo). Decryption is a similar process, except it uses the other key.\n\n"
						+ "Valid keys: For encryption, two prime numbers separated with a space. For decryption, the "
						+ "modulo and decryption key separated with a space.");
					rbSubstitution.setEnabled(true);
					rbSubstitution.setSelected(true);
					rbTransposition.setEnabled(false);
					rbTransposition.setSelected(false);
					rbSymmetric.setEnabled(false);
					rbSymmetric.setSelected(false);
					rbAsymmetric.setEnabled(true);
					rbAsymmetric.setSelected(true);
					rbStream.setEnabled(true);
					rbStream.setSelected(true);
					rbBlock.setEnabled(false);
					rbBlock.setSelected(false);
				}
				else txtCipherSummary.setText("You BROKE IT");
			}
		});
	}
	/**
	 * Initialises the cipher breakdown panel.
	 */
	private void initCipherBreakdown()
	{
		radioPanel = new JPanel();
		lblCipherBreakdown = new JLabel("Cipher Breakdown");
		rbSubstitution = new JRadioButton("Substitution");
		rbTransposition = new JRadioButton("Transposition");
		rbSymmetric = new JRadioButton("Symmetric");
		rbAsymmetric = new JRadioButton("Asymmetric");
		rbStream = new JRadioButton("Stream");
		rbBlock = new JRadioButton("Block");
		lblCipherBreakdown.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCipherBreakdown.setSize(464, 20);
		rbSubstitution.setEnabled(false);
		rbTransposition.setEnabled(false);
		rbSymmetric.setEnabled(false);
		rbAsymmetric.setEnabled(false);
		rbStream.setEnabled(false);
		rbBlock.setEnabled(false);
		rbSubstitution.setToolTipText("Substitution ciphers work by replacing each character in the plaintext with a different character.");
		rbTransposition.setToolTipText("Transposition ciphers work by rearranging the plaintext; individual characters are not changed.");
		rbSymmetric.setToolTipText("Symmetric ciphers decrypt ciphertext using the same method used to encrypt the plaintext, but in reverse.");
		rbAsymmetric.setToolTipText("Asymmetric ciphers have a different process for encryption and decryption. Some of these even have more than one key.");
		rbStream.setToolTipText("Stream ciphers encrypt all characters in the plaintext the same way.");
		rbBlock.setToolTipText("Block ciphers may encrypt different characters in the plaintext in different ways.");
		radioPanel.setLayout(new GridLayout(2, 3));
		radioPanel.setPreferredSize(new Dimension(464, 50));
		radioPanel.add(rbSubstitution);
		radioPanel.add(rbSymmetric);
		radioPanel.add(rbStream);
		radioPanel.add(rbTransposition);
		radioPanel.add(rbAsymmetric);
		radioPanel.add(rbBlock);
		radioPanel.setBackground(new Color(255, 255, 255));
	}
	/**
	 * Initialises the cipher summary text area.
	 */
	private void initCipherSummary()
	{
		lblCipherSummary = new JLabel("Quick Summary of Cipher");
		txtCipherSummary = new JTextArea();
		lblCipherSummary.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCipherSummary.setSize(464, 20);
		txtCipherSummary.setFont(new Font("Tahoma", 0, 14));
		txtCipherSummary.setPreferredSize(new Dimension(464, 120));
		txtCipherSummary.setLineWrap(true);
		txtCipherSummary.setWrapStyleWord(true);
		txtCipherSummary.setRequestFocusEnabled(false);
	}
	/**
	 * Initialises the encrypt button.
	 */
	private void initEncryptButton()
	{
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEncrypt.setPreferredSize(new Dimension(220, 50));
		btnEncrypt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cobChooseCipher.getSelectedItem() == "None")
				{
					// Easter eggs - mostly video game references
					if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("stuff"))
					{
						txtOutput.setText("I didn't mean literally type 'stuff' into the key box...");
					}
					// Legend of Zelda
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("heylisten"))
					{
						txtOutput.setText("Be quiet, Navi.");
					}
					// Castlevania
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("whatisaman"))
					{
						txtOutput.setText("A miserable little pile of secrets. But enough talk, have at you!");
					}
					// Xenoblade Chronicles
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("imreallyfeelingit"))
					{
						txtOutput.setText("But we mustn't be careless.");
					}
					// Super Smash Bros.
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("falcon"))
					{
						txtOutput.setText("...PUUUUNCH!");
					}
					// Sonic
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("gottagofast"))
					{
						txtOutput.setText("YOU'RE TOO SLOW!");
					}
					else txtOutput.setText("I lied. It prints this to the output window.");
				}
				if (cobChooseCipher.getSelectedItem() == "Shift Cipher")
				{
					Cipher c = new Shift(txtInputText.getText(), txtInputKey.getText(), true);
					c.encrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Vigenere Cipher")
				{
					Cipher c = new VigenereCipher(txtInputText.getText(), txtInputKey.getText(), true);
					c.encrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "One Time Pad")
				{
					Cipher c = new OneTimePad(txtInputText.getText(), txtInputKey.getText(), true);
					c.encrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Rail Fence Cipher")
				{
					Cipher c = new RailFence(txtInputText.getText(), txtInputKey.getText(), true);
					c.encrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "RSA")
				{
					Cipher c = new RSA(txtInputText.getText(), txtInputKey.getText(), true);
					c.encrypt();
					txtOutput.setText(c.getOutput());
				}
			}
		});
	}
	/**
	 * Initialises the decrypt button.
	 */
	private void initDecryptButton()
	{
		btnDecrypt = new JButton("Decrypt");
		btnDecrypt.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDecrypt.setPreferredSize(new Dimension(220, 50));
		btnDecrypt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cobChooseCipher.getSelectedItem() == "None")
				{
					// Easter eggs - mostly video game references
					if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("stuff"))
					{
						txtOutput.setText("I didn't mean literally type 'stuff' into the key box...");
					}
					// Legend of Zelda
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("heylisten"))
					{
						txtOutput.setText("Be quiet, Navi.");
					}
					// Castlevania
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("whatisaman"))
					{
						txtOutput.setText("A miserable little pile of secrets. But enough talk, have at you!");
					}
					// Xenoblade Chronicles
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("imreallyfeelingit"))
					{
						txtOutput.setText("But we mustn't be careless.");
					}
					// Super Smash Bros.
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("falcon"))
					{
						txtOutput.setText("...PUUUUNCH!");
					}
					// Sonic
					else if (txtInputKey.getText().toLowerCase().replaceAll("[^a-z]", "").equals("gottagofast"))
					{
						txtOutput.setText("YOU'RE TOO SLOW!");
					}
					else txtOutput.setText("I lied. It prints this to the output window.");
				}
				if (cobChooseCipher.getSelectedItem() == "Shift Cipher")
				{
					Cipher c = new Shift(txtInputText.getText(), txtInputKey.getText(), false);
					c.decrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Vigenere Cipher")
				{
					Cipher c = new VigenereCipher(txtInputText.getText(), txtInputKey.getText(), false);
					c.decrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "One Time Pad")
				{
					Cipher c = new OneTimePad(txtInputText.getText(), txtInputKey.getText(), false);
					c.decrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Rail Fence Cipher")
				{
					Cipher c = new RailFence(txtInputText.getText(), txtInputKey.getText(), false);
					c.decrypt();
					txtOutput.setText(c.getOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "RSA")
				{
					Cipher c = new RSA(txtInputText.getText(), txtInputKey.getText(), false);
					c.decrypt();
					txtOutput.setText(c.getOutput());
				}
			}
		});
	}
	/**
	 * Initialises the Output text area.
	 */
	private void initOutput()
	{
		lblOutput = new JLabel("Output");
		txtOutput = new JTextArea();
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOutput.setSize(464, 20);
		txtOutput.setPreferredSize(new Dimension(510, 586));
		txtOutput.setFont(new Font("Courier New", 0, 12));
		txtOutput.setRequestFocusEnabled(false);
		txtOutput.setLineWrap(true);
		txtOutput.setWrapStyleWord(true);
	}
	/**
	 * Sets the layout of this frame.
	 */
	private void initLayout()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(8, 12, 8, 12);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		add(title, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		add(lblInputText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(txtInputText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(lblInputKey, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(txtInputKey, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(lblChooseCipher, gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(cobChooseCipher, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		add(lblCipherBreakdown, gbc);
		gbc.gridx = 0;
		gbc.gridy = 8;
		add(radioPanel, gbc);
		gbc.gridx = 0;
		gbc.gridy = 9;
		add(lblCipherSummary, gbc);
		gbc.gridx = 0;
		gbc.gridy = 10;
		add(txtCipherSummary, gbc);
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		add(btnEncrypt, gbc);
		gbc.gridx = 1;
		gbc.gridy = 11;
		add(btnDecrypt, gbc);
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		add(lblOutput, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridheight = 10;
		add(txtOutput, gbc);
		pack();
		setVisible(true);
	}
	/**
	 * Main Method
	 */
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new GUI();
			}
		});
	}
}