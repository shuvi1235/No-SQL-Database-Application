package nosql;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;


import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin_Login {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Login window = new Admin_Login();
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
	public Admin_Login() {
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
		
		Image back2=new ImageIcon(this.getClass().getResource("/back1.png")).getImage();
		Image docs=new ImageIcon(this.getClass().getResource("/docsicon.png")).getImage();
		
		txtUsername = new JTextField();
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUsername.setText("");
			}
		});
		txtUsername.setFont(new Font("Verdana", Font.PLAIN, 40));
		txtUsername.setText("Admin ID");
		txtUsername.setBounds(867, 288, 411, 63);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.setText("Password");
		pwdPassword.setFont(new Font("Verdana", Font.PLAIN, 40));
		pwdPassword.setBounds(867, 363, 411, 63);
		frame.getContentPane().add(pwdPassword);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setBounds(971, 554, 307, 38);
		frame.getContentPane().add(lblMessage);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnLogin.setForeground(new Color(30, 144, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Sqliteconnect.validateadmin(txtUsername.getText().toString(), String.valueOf(pwdPassword.getPassword()))) {
					frame.dispose();
					Admin_Login_Success.main(new String[] {});
				} else {
					lblMessage.setText("Username/Password Incorrect");
					txtUsername.setText("Admin ID");
					pwdPassword.setText("Password");
				}
			}
		});
		btnLogin.setBounds(1071, 449, 206, 60);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setForeground(new Color(30, 144, 255));
		btnReset.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("Admin ID");
				pwdPassword.setText("Password");
			}
		});
		btnReset.setBounds(867, 449, 205, 60);
		frame.getContentPane().add(btnReset);

		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Forgot_Pass.main(new String[] {});
			}
		});
		btnForgotPassword.setBounds(994, 660, 153, 38);
		frame.getContentPane().add(btnForgotPassword);
		
		JButton btnDocs = new JButton("");
		btnDocs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Docs.main(new String[] {});
			}
		});
		btnDocs.setBounds(1307, 11, 41, 43);
		btnDocs.setIcon(new ImageIcon(docs));
		frame.getContentPane().add(btnDocs);
		
		JButton btnLoginAsMember = new JButton("Login As Member");
		btnLoginAsMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Member_Login.main(new String[] {});
			}
		});
		btnLoginAsMember.setBounds(1172, 660, 160, 38);
		frame.getContentPane().add(btnLoginAsMember);		
		
		Image icon=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		
		frame.setIconImage(icon);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(back2));
		frame.getContentPane().add(lblNewLabel);
		
	}
}