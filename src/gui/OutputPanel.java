package gui;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
/**
 * The output panel. Displays the output of the encryption and decryption processes.
 * @author Hayley Billingham, Jack Taylor
 * @version 24/10/2015
 */
public class OutputPanel extends JPanel
{
	private static final long serialVersionUID = -8130648670494751518L;
	private JLabel lblEncryptionProcess;
	private JLabel lblDecryptionProcess;
	private JTextArea txtEncryptionProcess;
	private JTextArea txtDecryptionProcess;
	/**
	 * Construct a new OutputPanel.
	 */
	public OutputPanel()
	{
		initEncryptionProcess();
		initDecryptionProcess();
		initLayout();
	}
	/**
	 * Initialises the Encryption Process text area.
	 */
	private void initEncryptionProcess()
	{
		lblEncryptionProcess = new JLabel("Encryption Process");
		txtEncryptionProcess = new JTextArea();
		lblEncryptionProcess.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEncryptionProcess.setSize(320, 20);
		txtEncryptionProcess.setPreferredSize(new Dimension(320, 200));
		txtEncryptionProcess.setFont(new Font("Tahoma", 0, 14));
		txtEncryptionProcess.setRequestFocusEnabled(false);
	}
	/**
	 * Initialises the Decryption Process text area.
	 */
	private void initDecryptionProcess()
	{
		lblDecryptionProcess = new JLabel("Decryption Process");
		txtDecryptionProcess = new JTextArea();
		lblDecryptionProcess.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDecryptionProcess.setSize(320, 20);
		txtDecryptionProcess.setPreferredSize(new Dimension(320, 200));
		txtDecryptionProcess.setFont(new Font("Tahoma", 0, 14));
		txtDecryptionProcess.setRequestFocusEnabled(false);
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
		add(lblEncryptionProcess, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(txtEncryptionProcess, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(lblDecryptionProcess, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(txtDecryptionProcess, gbc);
	}
}