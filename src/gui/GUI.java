package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * Top-level class to handle the GUI. Also contains the program's main method.
 * @author Hayley Billingham
 * @version 24/10/2015
 */
public class GUI extends JFrame
{
	private static final long serialVersionUID = 5125858476108975022L;
	private JLabel title;
	private InputPanel input;
	private KeyPanel key;
	private OutputPanel output;
	/**
	 * Creates GUI
	 */
	public GUI()
	{
		initTitle();
		initTheme();
		initPanels();
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
		title = new JLabel();
		title.setFont(new Font("Tahoma", Font.BOLD, 36));
		title.setText("EnDeCo");
		title.setSize(140, 35);
	}
	/**
	 * Initialises the input, key and output panels.
	 */
	private void initPanels()
	{
		input = new InputPanel();
		key = new KeyPanel();
		output = new OutputPanel();
		input.setBackground(new Color(255, 255, 255));
		key.setBackground(new Color(255, 255, 255));
		output.setBackground(new Color(255, 255, 255));
	}
	/**
	 * Sets the layout of this frame.
	 */
	private void initLayout()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		add(title, gbc);
		gbc.gridy = 1;
		add(input, gbc);
		gbc.gridy = 2;
		add(key, gbc);
		gbc.gridy = 3;
		add(output, gbc);
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
// Leftovers
// COMBO BOX
/*
 * private JComboBox cobChooseCipher; //SCROLLPANES private JScrollPane scrInputText; private JScrollPane jScrollPane4; private JScrollPane jScrollPane5; private JScrollPane lblEncryption; private JScrollPane scrCipherSummary; //LABELS private JLabel
 * lblChooseCipher; private JLabel lblCipherBreakdown; private JLabel lblDeEncryption; private JLabel lblEncyProcess; private JLabel lblInputText; private JLabel lblKeyChar; private JLabel lblKeyNum; private JLabel lblOutcome; private JLabel lblSumCipher;
 * private JLabel title; //RADIO BUTTONS private JRadioButton rbAsymmetrical; private JRadioButton rbBlock; private JRadioButton rbStream; private JRadioButton rbSubstitution; private JRadioButton rbSymmetrical; private JRadioButton rbTransposition;
 * //TEXT private JTextArea txtCipherSummary; private JTextArea txtDecryptionProcess; private JTextArea txtEncryptionProcess; private JTextArea txtInputText; private JTextArea txtInputChar; private JTextArea txtInputNum; private JTextArea txtOutcome;
 */
/*
 * private void initComponents() { //TITLE title = new JLabel(); //INPUT TEXT lblInputText = new JLabel(); txtInputText = new JTextArea(); scrInputText = new JScrollPane(); //COMBO BOX lblChooseCipher = new JLabel(); cobChooseCipher = new JComboBox();
 * //RADIO BUTTONS - CIPHER BREAKDOWN lblCipherBreakdown = new JLabel(); rbSymmetrical = new JRadioButton(); rbAsymmetrical = new JRadioButton(); rbTransposition = new JRadioButton(); rbSubstitution = new JRadioButton(); rbBlock = new JRadioButton();
 * rbStream = new JRadioButton(); //CIPHER SUMMARY TEXT BOX lblSumCipher = new JLabel(); scrCipherSummary = new JScrollPane(); txtCipherSummary = new JTextArea(); //KEY INPUT lblKeyNum = new JLabel(); lblKeyChar = new JLabel(); txtInputChar = new
 * JTextArea(); txtInputNum = new JTextArea(); //ENCRYPTION lblEncryption = new JScrollPane(); lblEncyProcess = new JLabel(); txtEncryptionProcess = new JTextArea(); jScrollPane4 = new JScrollPane(); //DECRYPTUON txtDecryptionProcess = new JTextArea();
 * lblDeEncryption = new JLabel(); jScrollPane5 = new JScrollPane(); //OUTCOME TEXT txtOutcome = new JTextArea(); lblOutcome = new JLabel(); //FRAME SETUP setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); setBackground(new Color(229, 255, 204));
 * setSize(700,825); setResizable(false); //TITLE title.setFont(new Font("Tahoma", 1, 36)); // NOI18N title.setText("EnDeCo"); title.setSize(140,35); //INPUT TEXT SETUP lblInputText.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblInputText.setText(
 * "Step 1) Input Text"); lblInputText.setSize(320,20); txtInputText.setRows(3); txtInputText.setRequestFocusEnabled(false); scrInputText.setViewportView(txtInputText); //DROP DOWN SETUP lblChooseCipher.setFont(new Font("Tahoma", 0, 14)); // NOI18N
 * lblChooseCipher.setText("Step 2) Choose Cipher"); lblChooseCipher.setSize(320,20); cobChooseCipher.setFont(new Font("Tahoma", 0, 14)); // NOI18N cobChooseCipher.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4"
 * })); cobChooseCipher.setSize(320, 50); cobChooseCipher.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { cbChooseCipherActionPerformed(evt); }}); //RADIO BUTTONS
 * lblCipherBreakdown.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblCipherBreakdown.setText("Cipher Breakdown"); lblCipherBreakdown.setSize(320,20); rbSymmetrical.setText("Symmetrical"); rbSymmetrical.addActionListener(new
 * java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) { rbSymmetricalActionPerformed(evt);}}); rbAsymmetrical.setText("Asymmetrical"); rbAsymmetrical.addActionListener(new java.awt.event.ActionListener() { public
 * void actionPerformed(java.awt.event.ActionEvent evt) { rbAsymmetricalActionPerformed(evt);}}); rbTransposition.setText("Transposition"); rbTransposition.addActionListener(new java.awt.event.ActionListener() { public void
 * actionPerformed(java.awt.event.ActionEvent evt) { rbTranspositionActionPerformed(evt);}}); rbSubstitution.setText("Substitution"); rbSubstitution.addActionListener(new java.awt.event.ActionListener() { public void
 * actionPerformed(java.awt.event.ActionEvent evt) { rbSubstitutionActionPerformed(evt);}}); rbBlock.setText("Block"); rbBlock.addActionListener(new java.awt.event.ActionListener() { public void actionPerformed(java.awt.event.ActionEvent evt) {
 * rbBlockActionPerformed(evt);}}); rbStream.setText("Stream"); //SUMMARY OF CIPHER - SETTINGS lblSumCipher.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblSumCipher.setText("Quick Summary of Cipher"); lblSumCipher.setSize(320,20);
 * txtCipherSummary.setRows(3); txtCipherSummary.setSize(320, 100); scrCipherSummary.setViewportView(txtCipherSummary); //KEY INPUT - SETTINGS lblKeyNum.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblKeyNum.setText("Key (Choose num 1-10) ");
 * lblKeyNum.setSize(320,20); lblKeyChar.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblKeyChar.setText("Key: Please enter character (5 max)"); lblKeyChar.setSize(320,20); txtInputChar.setSize(320,50); txtInputChar.setRows(2);
 * txtInputNum.setSize(320,50); txtInputNum.setRows(2); //ENCRYPTION PROCESS SETTINGS lblEncyProcess.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblEncyProcess.setText("Encryption Process"); lblEncyProcess.setSize(320,20);
 * txtEncryptionProcess.setRows(5); txtEncryptionProcess.setSize(320,100); lblEncryption.setViewportView(txtEncryptionProcess); //DECRYPTION PROCESS SETTINGS lblDeEncryption.setFont(new Font("Tahoma", 0, 14)); // NOI18N lblDeEncryption.setText(
 * "De-encryption Process"); lblDeEncryption.setSize(320,20); txtDecryptionProcess.setRows(5); txtDecryptionProcess.setSize(320,100); jScrollPane4.setViewportView(txtDecryptionProcess); //OUTCOME SETTINGS lblOutcome.setFont(new Font("Tahoma", 0, 14)); //
 * NOI18N lblOutcome.setText("Ouput"); lblOutcome.setSize(660,20); txtOutcome.setRows(5); txtOutcome.setSize(660,85); jScrollPane5.setViewportView(txtOutcome); //LAYOUT //HORIZONTAL GroupLayout layout = new GroupLayout(getContentPane());
 * getContentPane().setLayout(layout); layout.setHorizontalGroup( layout.createParallelGroup(Alignment.LEADING) .addGroup(Alignment.TRAILING, layout.createSequentialGroup() .addComponent(title) .addGap(282, 282, 282)) //SECTION A - OK
 * .addGroup(layout.createSequentialGroup() .addGap(20, 20, 20) .addGroup(layout.createParallelGroup(Alignment.LEADING) .addGroup(layout.createSequentialGroup() .addGroup(layout.createParallelGroup(Alignment.TRAILING) .addComponent(scrInputText)
 * .addGroup(layout.createSequentialGroup() .addGroup(layout.createParallelGroup(Alignment.LEADING) .addComponent(rbTransposition) .addComponent(rbSubstitution)) .addGap(35, 35, 35) .addGroup(layout.createParallelGroup(Alignment.LEADING)
 * .addComponent(rbStream, Alignment.TRAILING) .addComponent(rbBlock, Alignment.TRAILING)))) .addGap(20, 20, 20) //SECTION B - OK .addComponent(cobChooseCipher,GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)) //!!
 * .addGroup(layout.createSequentialGroup() .addGroup(layout.createParallelGroup(Alignment.LEADING) .addGroup(layout.createParallelGroup(Alignment.LEADING, false) .addGroup(layout.createSequentialGroup()
 * .addGroup(layout.createParallelGroup(Alignment.LEADING) .addComponent(rbAsymmetrical) .addComponent(rbSymmetrical)) .addGap(34, 34, 34)) .addGroup(layout.createSequentialGroup() .addGroup(layout.createParallelGroup(Alignment.LEADING)
 * .addComponent(txtInputChar) .addComponent(lblInputText) .addComponent(lblCipherBreakdown) .addComponent(lblKeyChar, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE) .addComponent(lblEncryption)) .addGap(20, 20, 20)))
 * .addGroup(layout.createSequentialGroup() .addComponent(lblEncyProcess, GroupLayout.PREFERRED_SIZE, 320,GroupLayout.PREFERRED_SIZE) .addGap(20, 20, 20))) .addGroup(layout.createParallelGroup(Alignment.LEADING) .addComponent(lblDeEncryption)
 * .addComponent(lblChooseCipher) .addComponent(lblSumCipher) .addComponent(lblKeyNum) .addComponent(txtCipherSummary) .addComponent(txtInputNum) .addComponent(jScrollPane4))) .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
 * .addComponent(jScrollPane5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE) .addComponent(lblOutcome, Alignment.LEADING,GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))) .addGap(20, 20, 20)) ); layout.setVerticalGroup(
 * layout.createParallelGroup(GroupLayout.Alignment.LEADING) .addGroup(layout.createSequentialGroup() //SECTION 1 .addComponent(title) .addGap(20, 20, 20) .addGroup(layout.createParallelGroup(Alignment.BASELINE) .addComponent(lblInputText)
 * .addComponent(lblChooseCipher)) .addGap(5, 5, 5) .addGroup(layout.createParallelGroup(Alignment.LEADING) .addComponent(cobChooseCipher) .addComponent(scrInputText)) .addGap(20, 20, 20) //SECTION 2 .addGroup(layout.createParallelGroup(Alignment.LEADING)
 * .addComponent(lblCipherBreakdown) .addComponent(lblSumCipher)) .addGroup(layout.createParallelGroup(Alignment.LEADING) .addGroup(layout.createSequentialGroup() .addGap(20, 20, 20) .addGroup(layout.createParallelGroup(Alignment.BASELINE)
 * .addComponent(rbBlock) .addComponent(rbTransposition) .addComponent(rbSymmetrical)) .addGap(20, 20, 20) .addGroup(layout.createParallelGroup(Alignment.BASELINE) .addComponent(rbAsymmetrical) .addComponent(rbSubstitution) .addComponent(rbStream))
 * .addGap(48, 48, 48)) .addGroup(layout.createSequentialGroup() .addGap(5, 5, 5) .addComponent(txtCipherSummary) .addGap(20, 20, 20))) //SECTION 3 !!need to look at this!! THE KEY FIELDS ARE SUPPOSED TO BE A BIGGER HEIGHT
 * .addGroup(layout.createParallelGroup(Alignment.BASELINE) .addComponent(lblKeyNum) .addComponent(lblKeyChar)) .addGap(5, 5, 5) .addGroup(layout.createParallelGroup(Alignment.LEADING) .addComponent(txtInputChar) .addComponent(txtInputNum)) .addGap(20,
 * 20, 20) //SECTION 4 -OK .addGroup(layout.createParallelGroup(Alignment.BASELINE) .addComponent(lblEncyProcess) .addComponent(lblDeEncryption)) .addGap(5, 5, 5) .addGroup(layout.createParallelGroup(Alignment.LEADING) .addComponent(lblEncryption)
 * .addComponent(jScrollPane4)) .addGap(20, 20, 20) //SECTION 5 -OK .addComponent(lblOutcome) .addGap(5, 5, 5) .addComponent(jScrollPane5) .addGap(20, 20, 20)) ); pack(); }// </editor-fold> private void
 * cbChooseCipherActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void rbSymmetricalActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void
 * rbSubstitutionActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void txtInputCharActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void
 * txtInputNumActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void rbAsymmetricalActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void
 * rbBlockActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: } private void rbTranspositionActionPerformed(java.awt.event.ActionEvent evt) { // TODO add your handling code here: }
 */
/*
 * try { for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) { if ("Nimbus".equals(info.getName())) { UIManager.setLookAndFeel(info.getClassName()); break; } } } catch (ClassNotFoundException ex) {
 * java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); } catch (InstantiationException ex) { java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); } catch
 * (IllegalAccessException ex) { java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); } catch (UnsupportedLookAndFeelException ex) {
 * java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex); } //</editor-fold>
 */