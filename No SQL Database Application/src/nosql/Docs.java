package nosql;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Docs {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Docs window = new Docs();
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
	public Docs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(1243, 62, 105, 38);
		frame.getContentPane().add(btnExit);
		
		JLabel lblDocument = new JLabel("Document");
		lblDocument.setBackground(new Color(30, 144, 255));
		lblDocument.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocument.setFont(new Font("Calibri", Font.BOLD, 44));
		lblDocument.setBounds(-8, 0, 1366, 100);
		frame.getContentPane().add(lblDocument);
		
		JLabel lblFooter = new JLabel("\u00A9 2017 SHUBHAM SINGH BISHT, All Rights Reserved");
		lblFooter.setForeground(new Color(0, 0, 0));
		lblFooter.setHorizontalAlignment(SwingConstants.CENTER);
		lblFooter.setBounds(0, 727, 1358, 14);
		frame.getContentPane().add(lblFooter);
		
		Image icon=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		Image background=new ImageIcon(this.getClass().getResource("/docs.png")).getImage();
		
		frame.setIconImage(icon);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1358, 768);
		lblNewLabel.setIcon(new ImageIcon(background));
		frame.getContentPane().add(lblNewLabel);
	}
}

