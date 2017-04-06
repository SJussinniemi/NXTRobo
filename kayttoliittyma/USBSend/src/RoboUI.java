import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;


public class RoboUI {

	private JFrame frame;
	
	USBSend usbYhteys = new USBSend();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoboUI window = new RoboUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoboUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLhet = new JButton("L\u00E4het\u00E4");
		btnLhet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				usbYhteys.ajaKysely(textField.getText());
				
			}
		});
		btnLhet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnLhet.setBounds(168, 125, 89, 23);
		frame.getContentPane().add(btnLhet);
		//usbYhteys.
		//textField.setBounds(157, 76, 115, 20);
		
		JLabel lblAnnaRobolleNimi = new JLabel("Anna Robolle nimi");
		lblAnnaRobolleNimi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAnnaRobolleNimi.setBounds(147, 26, 149, 46);
		frame.getContentPane().add(lblAnnaRobolleNimi);
		
		textField = new JTextField();
		textField.setBounds(171, 83, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
