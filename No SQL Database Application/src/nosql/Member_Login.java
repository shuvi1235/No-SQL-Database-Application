package nosql;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Member_Login {

	private JFrame frame;
	private JTextField txtAdminId;
	private JPasswordField pwdPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member_Login window = new Member_Login();
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
	public Member_Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Image docs=new ImageIcon(this.getClass().getResource("/docsicon.png")).getImage();
		Image icon=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		Image back1=new ImageIcon(this.getClass().getResource("/back1.png")).getImage();
		
		frame.setIconImage(icon);
		
		txtAdminId = new JTextField();
		txtAdminId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtAdminId.setText("");
			}
		});
		txtAdminId.setFont(new Font("Verdana", Font.PLAIN, 30));
		txtAdminId.setText("Username");
		txtAdminId.setBounds(865, 286, 414, 62);
		frame.getContentPane().add(txtAdminId);
		txtAdminId.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.setFont(new Font("Verdana", Font.PLAIN, 30));
		pwdPassword.setText("Password");
		pwdPassword.setBounds(865, 366, 414, 62);
		frame.getContentPane().add(pwdPassword);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setBounds(604, 560, 232, 43);
		frame.getContentPane().add(lblMessage);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(30, 144, 255));
		btnLogin.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if() {
					frame.dispose();
					Member_Login_Success.main(new String[] {});
				} else {
					lblMessage.setText("Username/Password Incorrect");
					txtAdminId.setText("Username");
					pwdPassword.setText("Password");
				}
			}
		});
		btnLogin.setBounds(1074, 451, 207, 56);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtAdminId.setText("Username");
				pwdPassword.setText("Password");
			}
		});
		btnReset.setForeground(new Color(30, 144, 255));
		btnReset.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnReset.setBounds(868, 451, 207, 56);
		frame.getContentPane().add(btnReset);

		
		JButton btnForgotPassword = new JButton("Forgot Password");
		btnForgotPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Forgot_Pass.main(new String[] {});
			}
		});
		btnForgotPassword.setBounds(988, 660, 153, 38);
		frame.getContentPane().add(btnForgotPassword);
		
		JButton btnLoginAsAdmin = new JButton("Login As Admin");
		btnLoginAsAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Admin_Login.main(new String[] {});
			}
		});
		btnLoginAsAdmin.setBounds(1173, 659, 160, 38);
		frame.getContentPane().add(btnLoginAsAdmin);
		
		JButton btnDocs = new JButton("");
		btnDocs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Docs.main(new String[] {});
			}
		});
		btnDocs.setBounds(1307, 11, 41, 43);
		btnDocs.setIcon(new ImageIcon(docs));
		frame.getContentPane().add(btnDocs);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(back1));
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}