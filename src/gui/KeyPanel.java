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
	/**
	 * Construct a new KeyPanel.
	 */
	public KeyPanel()
	{
		initInputKey();
		initEncryptButton();
		initLayout();
	}
	/**
	 * Initialise the key input text area.
	 */
	private void initInputKey()
	{
		lblInputKey = new JLabel();
		txtInputKey = new JTextArea();
		lblInputKey.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInputKey.setText("Input Key");
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
				//Start encryption process
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
}