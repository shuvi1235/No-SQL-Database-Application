package nosql;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Forgot_Pass {

	private JFrame frame;
	private JTextField txtExistingUsername;
	private JPasswordField pwdOldPassword;
	private JPasswordField pwdNewPassword;
	private JPasswordField pwdConfirmPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Forgot_Pass window = new Forgot_Pass();
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
	public Forgot_Pass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image docs=new ImageIcon(this.getClass().getResource("/docsicon.png")).getImage();
		frame.getContentPane().setLayout(null);
		
		JButton btnDocs = new JButton("");
		btnDocs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Docs.main(new String[] {});
			}
		});
		btnDocs.setBounds(1307, 11, 41, 43);
		btnDocs.setIcon(new ImageIcon(docs));
		frame.getContentPane().add(btnDocs);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 20));
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(474, 345, 129, 26);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setForeground(new Color(255, 255, 255));
		lblOldPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblOldPassword.setFont(new Font("Calibri", Font.BOLD, 20));
		lblOldPassword.setBounds(474, 382, 151, 38);
		frame.getContentPane().add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setForeground(new Color(255, 255, 255));
		lblNewPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewPassword.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewPassword.setBounds(474, 431, 151, 38);
		frame.getContentPane().add(lblNewPassword);
		
		JLabel lblConfirm = new JLabel("Confirm Password");
		lblConfirm.setForeground(new Color(255, 255, 255));
		lblConfirm.setFont(new Font("Calibri", Font.BOLD, 20));
		lblConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		lblConfirm.setBounds(474, 480, 198, 38);
		frame.getContentPane().add(lblConfirm);
		
		txtExistingUsername = new JTextField();
		txtExistingUsername.setFont(new Font("Verdana", Font.PLAIN, 30));
		txtExistingUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtExistingUsername.setText("");
			}
		});
		txtExistingUsername.setText("Existing Username");
		txtExistingUsername.setBounds(865, 287, 414, 61);
		frame.getContentPane().add(txtExistingUsername);
		txtExistingUsername.setColumns(10);
		
		pwdOldPassword = new JPasswordField();
		pwdOldPassword.setFont(new Font("Verdana", Font.PLAIN, 30));
		pwdOldPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdOldPassword.setText("");
			}
		});
		pwdOldPassword.setText("Old Password");
		pwdOldPassword.setBounds(865, 365, 414, 61);
		frame.getContentPane().add(pwdOldPassword);
		
		pwdNewPassword = new JPasswordField();
		pwdNewPassword.setFont(new Font("Verdana", Font.PLAIN, 30));
		pwdNewPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdNewPassword.setText("");
			}
		});
		pwdNewPassword.setText("New Password");
		pwdNewPassword.setBounds(865, 447, 414, 61);
		frame.getContentPane().add(pwdNewPassword);
		
		pwdConfirmPassword = new JPasswordField();
		pwdConfirmPassword.setFont(new Font("Verdana", Font.PLAIN, 30));
		pwdConfirmPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdConfirmPassword.setText("");
			}
		});
		pwdConfirmPassword.setText("Confirm Password");
		pwdConfirmPassword.setBounds(865, 526, 414, 61);
		frame.getContentPane().add(pwdConfirmPassword);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtExistingUsername.setText("Existing Username");
				pwdOldPassword.setText("Old Password");
				pwdNewPassword.setText("New Password");
				pwdConfirmPassword.setText("Confirm Password");
			}
		});
		btnReset.setBounds(998, 612, 129, 38);
		frame.getContentPane().add(btnReset);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Username=txtExistingUsername.getText().toString();
				String Oldpassword=String.valueOf(pwdOldPassword.getPassword());
				String Newpassword=String.valueOf(pwdNewPassword.getPassword());
				String Confirmpassword=String.valueOf(pwdConfirmPassword.getPassword());
				
				if(Newpassword.equals(Confirmpassword)){
					if(Sqliteconnect.changepass(Username, Oldpassword, Newpassword)) {
						JOptionPane.showMessageDialog(null, "Password Changed Successfully");
						frame.dispose();
						Member_Login.main(new String[] {});
					}
					else {
						JOptionPane.showMessageDialog(null, "Wrong Entry");
						frame.dispose();
						Member_Login.main(new String[] {});
					}
				} else {
					JOptionPane.showMessageDialog(null, "New Password is not confirm");
					frame.dispose();
					Member_Login.main(new String[] {});
				}
			}
		});
		btnChange.setBounds(1158, 612, 121, 38);
		frame.getContentPane().add(btnChange);
		
		Image icon=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		Image background=new ImageIcon(this.getClass().getResource("/back1.png")).getImage();
		
		frame.setIconImage(icon);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(background));
		frame.getContentPane().add(lblNewLabel);
	}
}

