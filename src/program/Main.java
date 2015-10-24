package program;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ciphers.*;
import gui.GUI;
import gui.InputPanel;
import gui.KeyPanel;
/**
 * The main class for this program. Contains the main method.
 * @author Nameyka Myrie, Jack Taylor
 * @version 17/10/2015
 */
public class Main
{
	/**
	 * Main Method
	 */
	public static void main(String[] args)
    {
    	//new Shift("Ermmm...", "2");
    	//new VigenereCipher("this is a sentence", "word");
    	//new OneTimePad("This for encoding", "Ernie Ball Super Slinky"); //Converts everything to lowercase. Both Key and Text
    	//new OneTimePad("This for encoding", "There was a man called michael finnegan");
    	//new OneTimePad("Just for for tonight", "Ernie Ball Super Slinky");
    	//new OneTimePad("Just for for tonight", "Ernie Ball we uip we");  
    	//new OneTimePad("Just for for tonight", "Ernie Ball we uip weq");
		new RailFence("really long plaintext string", "3");
		new RailFence("really long plaintext string", "4");
		new RailFence("really long plaintext string", "5");
		new RailFence("really long plaintext string", "6");
		new RailFence("really long plaintext string", "7");
		new RailFence("really long plaintext string", "8");
		new RailFence("really long plaintext string", "9");
		new RailFence("really long plaintext string", "10");
		new RailFence("really long plaintext string", "11");
		/*
		 *  3 -> 4
		 *  2 -> 5
                Fine if it's going down
                Bad if it's going up
		 */
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
		InputPanel i = new InputPanel();
		KeyPanel k = new KeyPanel();
		i.setBackground(new Color(255, 255, 255));
		k.setBackground(new Color(255, 255, 255));
		JFrame f = new JFrame();
		f.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		f.add(i, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		f.add(k, gbc);
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
    }
}