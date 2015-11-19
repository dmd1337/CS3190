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
 * @version 19/11/2015
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
		title.setFont(new Font("Tahoma", Font.BOLD, 28));
		title.setSize(140, 35);
	}
	/**
	 * Initialises the plaintext input area.
	 */
	private void initInputText()
	{
		lblInputText = new JLabel("Input Plaintext");
		txtInputText = new JTextArea();
		lblInputText.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInputText.setSize(464, 20);
		txtInputText.setPreferredSize(new Dimension(464, 50));
		txtInputText.setFont(new Font("Tahoma", 0, 14));
		txtInputText.setLineWrap(true);
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
					txtCipherSummary.setText("The None Cipher does exactly what you would expect: absolutely \nnothing.\n\n"
						+ "Valid key values: I honestly don't think it matters.");
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
					txtCipherSummary.setText("The Shift Cipher is very simple; it takes the key and shifts each letter by \nits value. "
						+ "For example, a key of 2 will shift \"a\" to \"c\".\n\n"
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
					txtCipherSummary.setText("The Vigenere Cipher is a block version of the Shift cipher. It shifts each \nletter "
						+ "by the corresponding key value, returning to the start of the key \nwhen it reaches the last key letter.\n\n"
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
					txtCipherSummary.setText("The One Time Pad is functionally identical to the Vigenere Cipher, except \nthe key is "
						+ "longer than the plaintext. Typically, the key would be a long \nliterary excerpt.\n\n"
						+ "Valid key values: Same as Vigenere, except it should be at least as long as \nthe plaintext.");
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
					txtCipherSummary.setText("The Rail Fence Cipher is a transposition cipher which organises the \nplaintext into "
						+ "a series of rows (the number of rows being determined by \nthe key). The rows are then added to each other to "
						+ "obtain the ciphertext.\n\n"
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
					txtCipherSummary.setText("This cipher has not yet been implemented.\n\n"
						+ "Valid key values: Any number greater than or equal to 2.");
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
		txtCipherSummary.setRequestFocusEnabled(false);
	}
	/**
	 * Initialises the encrypt button.
	 */
	private void initEncryptButton()
	{
		btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEncrypt.setPreferredSize(new Dimension(217, 50));
		btnEncrypt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cobChooseCipher.getSelectedItem() == "Shift Cipher")
				{
					Cipher c = new Shift(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getEncryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Vigenere Cipher")
				{
					Cipher c = new VigenereCipher(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getEncryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "One Time Pad")
				{
					Cipher c = new OneTimePad(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getEncryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Rail Fence Cipher")
				{
					Cipher c = new RailFence(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getEncryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "RSA")
				{
					txtOutput.setText("This has not yet been implemented.");
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
		btnDecrypt.setPreferredSize(new Dimension(217, 50));
		btnDecrypt.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (cobChooseCipher.getSelectedItem() == "Shift Cipher")
				{
					Cipher c = new Shift(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getDecryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Vigenere Cipher")
				{
					Cipher c = new VigenereCipher(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getDecryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "One Time Pad")
				{
					Cipher c = new OneTimePad(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getDecryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "Rail Fence Cipher")
				{
					Cipher c = new RailFence(txtInputText.getText(), txtInputKey.getText());
					txtOutput.setText(c.getDecryptOutput());
				}
				if (cobChooseCipher.getSelectedItem() == "RSA")
				{
					txtOutput.setText("This has not yet been implemented.");
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