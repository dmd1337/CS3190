package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import ciphers.OneTimePad;
import ciphers.RailFence;
import ciphers.Shift;
import ciphers.VigenereCipher;
/**
 * The key panel. Allows the user to enter a key and start the encryption process.
 * @author Hayley Billingham, Jack Taylor
 * @version 24/10/2015
 */
public class KeyPanel extends JPanel
{
	private static final long serialVersionUID = -8921476836524814740L;
	private JLabel lblInputKey;
	private JTextArea txtInputKey;
	private JButton btnEncryptButton;
	private String selected;
	/**
	 * Construct a new KeyPanel.
	 */
	public KeyPanel()
	{
		selected = "";
		initInputKey();
		initEncryptButton();
		initLayout();
	}
	/**
	 * Initialise the key input text area.
	 */
	private void initInputKey()
	{
		lblInputKey = new JLabel("Input Key");
		txtInputKey = new JTextArea();
		lblInputKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInputKey.setSize(320, 20);
		txtInputKey.setPreferredSize(new Dimension(320, 30));
		txtInputKey.setFont(new Font("Tahoma", 0, 14));
	}
	/**
	 * Initialises the encrypt button.
	 */
	private void initEncryptButton()
	{
		btnEncryptButton = new JButton("Encrypt");
		btnEncryptButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEncryptButton.setPreferredSize(new Dimension(320, 50));
		btnEncryptButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (selected == "Shift Cipher") new Shift("thing", "2");
				if (selected == "Vigenere Cipher") new VigenereCipher("longstring", "key");
				if (selected == "One Time Pad") new OneTimePad("longstring", "evenlongerstring");
				if (selected == "Rail Fence Cipher") new RailFence("really long plaintext string", "5");
			}
		});
	}
	/**
	 * Sets the layout of this panel.
	 */
	private void initLayout()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 20, 10, 20);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblInputKey, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(txtInputKey, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		add(btnEncryptButton, gbc);
	}
	public void setSelectedItem(String selected)
	{
		this.selected = selected;
	}
}