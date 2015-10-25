package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
/**
 * The input panel. Allows the user to enter plaintext and select a cipher, then categorises the cipher and displays its summary.
 * @author Hayley Billingham, Jack Taylor
 * @version 24/10/2015
 */
public class InputPanel extends JPanel
{
	private static final long serialVersionUID = 7603090258673682140L;
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
	private boolean updateflag;
	/**
	 * Construct a new InputPanel.
	 */
	public InputPanel()
	{
		updateflag = false;
		initInputText();
		initChooseCipher();
		initCipherBreakdown();
		initCipherSummary();
		initLayout();
	}
	/**
	 * Initialises the plaintext input area.
	 */
	private void initInputText()
	{
		lblInputText = new JLabel("Input Plaintext");
		txtInputText = new JTextArea();
		lblInputText.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInputText.setSize(320, 20);
		txtInputText.setPreferredSize(new Dimension(320, 50));
		txtInputText.setFont(new Font("Tahoma", 0, 14));
		txtInputText.setLineWrap(true);
	}
	/**
	 * Initialises the cipher selection box.
	 */
	private void initChooseCipher()
	{
		lblChooseCipher = new JLabel("Choose Cipher");
		cobChooseCipher = new JComboBox<String>();
		lblChooseCipher.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChooseCipher.setSize(320, 20);
		cobChooseCipher.setFont(new Font("Tahoma", Font.BOLD, 20));
		cobChooseCipher.setModel(new DefaultComboBoxModel<String>(new String[] {"None", "Shift Cipher", "Vigenere Cipher", "One Time Pad", "Rail Fence Cipher"}));
		cobChooseCipher.setPreferredSize(new Dimension(320, 50));
		cobChooseCipher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				updateflag = true;
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
		lblCipherBreakdown.setSize(320, 20);
		rbSubstitution.setEnabled(false);
		rbTransposition.setEnabled(false);
		rbSymmetric.setEnabled(false);
		rbAsymmetric.setEnabled(false);
		rbStream.setEnabled(false);
		rbBlock.setEnabled(false);
		radioPanel.setLayout(new GridLayout(2, 3));
		radioPanel.setPreferredSize(new Dimension(320, 100));
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
		lblCipherSummary.setSize(320, 20);
		txtCipherSummary.setFont(new Font("Tahoma", 0, 14));
		txtCipherSummary.setPreferredSize(new Dimension(320, 100));
		txtCipherSummary.setRequestFocusEnabled(false);
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
		add(lblInputText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(txtInputText, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(lblChooseCipher, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(cobChooseCipher, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblCipherBreakdown, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(radioPanel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(lblCipherSummary, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(txtCipherSummary, gbc);
	}
	public String getSelectedItem()
	{
		return (String)cobChooseCipher.getSelectedItem();
	}
	public boolean getFlag()
	{
		return updateflag;
	}
	public void setFlag(boolean flag)
	{
		updateflag = flag;
	}
}