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

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.mongodb.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.awt.SystemColor;

public class Admin_Login_Success {

	private JFrame frame;
	private JTextField txtCollections;
	private JTextField txtDatabase;
	private JTextField txtNewUsername;
	private JPasswordField pwdNewPassword;
	private JTextField txtYesOrLeave;
	private JTextField txtUsername;
	private JTextField txtSerial;
	Mongo mc=new Mongo();
	private JTextField txtDatabasename;
	private JTextField txtExistingUsername;
	private JPasswordField pwdNewPassword_1;
	private JTextField txtUsername_1;
	private JTextField txtNameOfCollection;
	private JTextField txtNameOfDatabase;
	private JTextField txtDatabaseName;
	private JTextField txtCollectionName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Login_Success window = new Admin_Login_Success();
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
	public Admin_Login_Success() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(0, 0, 1366, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image docs=new ImageIcon(this.getClass().getResource("/docsicon.png")).getImage();
		frame.getContentPane().setLayout(null);
		
		JButton btnDocs = new JButton("");
		btnDocs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Docs.main(new String[] {});
			}
		});
		btnDocs.setBounds(1307, 111, 41, 43);
		btnDocs.setIcon(new ImageIcon(docs));
		frame.getContentPane().add(btnDocs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(24, 160, 446, 488);
		scrollPane.setViewportBorder(UIManager.getBorder("TextArea.border"));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		JTextArea txtrText = new JTextArea();
		txtrText.setBackground(Color.BLACK);
		txtrText.setForeground(Color.WHITE);
		txtrText.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtrText.setEditable(false);
		scrollPane.setViewportView(txtrText);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.setBounds(361, 111, 109, 38);
		btnShowData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				MongoCollection<Document> coll=Mongo.showdata(txtDatabase.getText().toString(), txtCollections.getText().toString(), txtDatabase, txtCollections);
				MongoIterable<Document> mi=coll.find(new Document());
				MongoCursor<Document> cur=mi.iterator();
				
				txtrText.setText("");
				while(cur.hasNext()) {
					txtrText.append(cur.next()+"\n");
				}
				txtDatabase.setText("Enter Database Name");
				txtCollections.setText("Enter Collection Name");
			}
		});
		frame.getContentPane().add(btnShowData);
		
		txtCollections = new JTextField();
		txtCollections.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCollections.setText("");
			}
		});
		txtCollections.setBounds(191, 111, 160, 38);
		txtCollections.setText("Enter Collection Name");
		frame.getContentPane().add(txtCollections);
		txtCollections.setColumns(10);
		
		txtDatabase = new JTextField();
		txtDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDatabase.setText("");
			}
		});
		txtDatabase.setBounds(24, 111, 157, 38);
		txtDatabase.setText("Enter Database Name");
		frame.getContentPane().add(txtDatabase);
		txtDatabase.setColumns(10);
		
		JButton btnListOfAll = new JButton("List Of All Databases");
		btnListOfAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtrText.setText("");
				
				MongoIterable<String> strings=Mongo.listdatabase();
				MongoCursor<String> iterator=strings.iterator();
				while (iterator.hasNext()) {
				    txtrText.append("Database: "+iterator.next().toString()+"\n");
				}
			}
		});
		btnListOfAll.setBounds(513, 157, 366, 38);
		frame.getContentPane().add(btnListOfAll);
		
		JLabel lblAdminFunctions = new JLabel("Admin Functions");
		lblAdminFunctions.setBounds(513, 111, 366, 43);
		lblAdminFunctions.setForeground(SystemColor.desktop);
		lblAdminFunctions.setFont(new Font("Calibri", Font.BOLD, 40));
		lblAdminFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblAdminFunctions);
		
		JLabel lblOtherFunctions = new JLabel("Other Functions");
		lblOtherFunctions.setBounds(931, 111, 366, 43);
		lblOtherFunctions.setForeground(SystemColor.desktop);
		lblOtherFunctions.setFont(new Font("Calibri", Font.BOLD, 40));
		lblOtherFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblOtherFunctions);
		
		JButton btnListOfAll_1 = new JButton("Collection list");
		btnListOfAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MongoIterable<String> collection=Mongo.listcollection(txtDatabasename.getText(), txtDatabasename);
				MongoCursor<String> cur=collection.iterator();
				txtrText.setText("");
				
				if(cur.hasNext()==false)
					txtrText.setText("No collection to display");
				
				while(cur.hasNext()) {
					txtrText.append(cur.next()+"\n");
				}				
				txtDatabasename.setText("Database Name");
			}
		});
		btnListOfAll_1.setBounds(738, 255, 141, 38);
		frame.getContentPane().add(btnListOfAll_1);
		
		JButton btnMaximumClosingPrice = new JButton("Maximum Closing Price ");
		btnMaximumClosingPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoCollection<Document> coll=Mongo.showdata(txtDatabaseName.getText().toString(), txtCollectionName.getText().toString(), txtDatabase, txtCollections );					
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Close", 1)).limit(1);
		        txtrText.setText(cursor.iterator().next().toString());
		        txtDatabaseName.setText("Databse Name");
		        txtCollectionName.setText("Collection Name");
		   
			}
		});
		btnMaximumClosingPrice.setBounds(931, 206, 366, 38);
		frame.getContentPane().add(btnMaximumClosingPrice);
		
		JButton MaximumOpeningPrice = new JButton("Maximum Opening Price ");
		MaximumOpeningPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoCollection<Document> coll=Mongo.showdata(txtDatabaseName.getText().toString(), txtCollectionName.getText().toString(), txtDatabase, txtCollections );					
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Open", 1)).limit(1);
		        txtrText.setText(cursor.iterator().next().toString());
		        txtDatabaseName.setText("Databse Name");
		        txtCollectionName.setText("Collection Name");
			}
		});
		MaximumOpeningPrice.setBounds(931, 255, 366, 38);
		frame.getContentPane().add(MaximumOpeningPrice);
		
		JButton btnMinimumClosingPrice = new JButton("Minimum Closing Price ");
		btnMinimumClosingPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MongoCollection<Document> coll=Mongo.showdata(txtDatabaseName.getText().toString(), txtCollectionName.getText().toString(), txtDatabase, txtCollections);					
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Close", -1)).limit(1);
		        txtrText.setText(cursor.iterator().next().toString());
		        txtDatabaseName.setText("Databse Name");
		        txtCollectionName.setText("Collection Name");
			}
		});
		btnMinimumClosingPrice.setBounds(931, 304, 366, 38);
		frame.getContentPane().add(btnMinimumClosingPrice);
		
		JButton btnMinimumOpeningPrice = new JButton("Minimum Opening Price ");
		btnMinimumOpeningPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MongoCollection<Document> coll=Mongo.showdata(txtDatabaseName.getText().toString(), txtCollectionName.getText().toString(), txtDatabase, txtCollections );					
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Open", -1)).limit(1);
		        txtrText.setText(cursor.iterator().next().toString());		
		        txtDatabaseName.setText("Databse Name");
		        txtCollectionName.setText("Collection Name");
			}
		});
		btnMinimumOpeningPrice.setBounds(931, 353, 366, 38);
		frame.getContentPane().add(btnMinimumOpeningPrice);
		
		JButton btnListOfAll_2 = new JButton("List Of All  Users And Passwords");
		btnListOfAll_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					ResultSet rs=Sqliteconnect.showtable();
				
					txtrText.setText("");
					   while (rs.next()) {
						   int no=rs.getInt("Serial");
						   String user=rs.getString("Username");
						   String pass=rs.getString("Password");
						   String admin=rs.getString("Admin");
						   
						   txtrText.append("S.NO: "+no+"    Username: "+user+"    Password: "+pass+"    Admin Rights: "+admin+"\n");
					   }
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				

			}
		});
		btnListOfAll_2.setBounds(513, 206, 366, 38);
		frame.getContentPane().add(btnListOfAll_2);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(24, 659, 446, 38);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Mongo.mongoClient.close();
				frame.dispose();
				Member_Login.main(new String[] {});
			}
		});
		frame.getContentPane().add(btnLogout);
		
		txtNewUsername = new JTextField();
		txtNewUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNewUsername.setText("");
			}
		});
		txtNewUsername.setBounds(642, 573, 172, 38);
		txtNewUsername.setText("New Username");
		frame.getContentPane().add(txtNewUsername);
		txtNewUsername.setColumns(10);
		
		pwdNewPassword = new JPasswordField();
		pwdNewPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdNewPassword.setText("");
			}
		});
		pwdNewPassword.setBounds(824, 574, 172, 36);
		pwdNewPassword.setText("New Password");
		frame.getContentPane().add(pwdNewPassword);
		
		txtYesOrLeave = new JTextField();
		txtYesOrLeave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtYesOrLeave.setText("");
			}
		});
		txtYesOrLeave.setBounds(1006, 574, 172, 36);
		txtYesOrLeave.setText("Admin Rights: yes or null");
		frame.getContentPane().add(txtYesOrLeave);
		txtYesOrLeave.setColumns(10);
		
		JButton btnAdd = new JButton("Add User");
		btnAdd.setBounds(1188, 574, 109, 38);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Admin;
				if(txtYesOrLeave.getText().equals("YES") || txtYesOrLeave.getText().equals("yes"))
					Admin="YES";
				else
					Admin="null";
				
				boolean i=Sqliteconnect.save(txtNewUsername.getText(), String.valueOf(pwdNewPassword.getPassword()), Integer.valueOf(txtSerial.getText()), Admin);
				
				if(i==true)
					txtrText.setText("New User Entry Saved");
				else
					txtrText.setText("Error: Something wrong in Serial/Username/Passwors/AdminRights");
				
				txtNewUsername.setText("New Username");
				pwdNewPassword.setText("New Password");
				txtSerial.setText("Serial");
				txtYesOrLeave.setText("Admin Rights: yes or null");
			}
		});
		frame.getContentPane().add(btnAdd);
		
		txtUsername = new JTextField();
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername.setText("");
			}
		});
		txtUsername.setBounds(513, 306, 215, 38);
		txtUsername.setText("Existing Username");
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setBounds(738, 306, 141, 38);
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				boolean i=Sqliteconnect.delete(txtUsername.getText());
				txtrText.setText("Existing Username");
				
				if(i==true)
					txtrText.setText("Username and Password deleted Successfully");
				else
					txtrText.setText("Username and Password doesn't exist");
				
				txtUsername.setText("Existing Username");
				
			}
		});
		frame.getContentPane().add(btnDeleteUser);
		
		txtSerial = new JTextField();
		txtSerial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtSerial.setText("");
			}
		});
		txtSerial.setText("Serial");
		txtSerial.setBounds(513, 574, 119, 37);
		frame.getContentPane().add(txtSerial);
		txtSerial.setColumns(10);
		
		txtDatabasename = new JTextField();
		txtDatabasename.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDatabasename.setText("");
			}
		});
		txtDatabasename.setText("Database Name");
		txtDatabasename.setBounds(513, 255, 215, 38);
		frame.getContentPane().add(txtDatabasename);
		txtDatabasename.setColumns(10);
		
		txtExistingUsername = new JTextField();
		txtExistingUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtExistingUsername.setText("");
			}
		});
		txtExistingUsername.setText("Existing Username");
		txtExistingUsername.setBounds(513, 357, 215, 38);
		frame.getContentPane().add(txtExistingUsername);
		txtExistingUsername.setColumns(10);
		
		JButton btnChangePassword = new JButton("Check Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean status=Sqliteconnect.showpass(txtExistingUsername.getText(), txtrText );
				txtExistingUsername.setText("Existing Username");
				
				if(status==false)
					txtrText.setText("Username does not exist");
			}
		});
		btnChangePassword.setBounds(738, 357, 141, 38);
		frame.getContentPane().add(btnChangePassword);
		
		pwdNewPassword_1 = new JPasswordField();
		pwdNewPassword_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdNewPassword_1.setText("");
			}
		});
		pwdNewPassword_1.setText("New Password");
		pwdNewPassword_1.setBounds(850, 658, 328, 39);
		frame.getContentPane().add(pwdNewPassword_1);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean status=Sqliteconnect.changepassfrominside(txtUsername_1.getText().toString(), String.valueOf(pwdNewPassword_1.getPassword()) );
				
				if(status==true)
					txtrText.setText("Password changed successfully");
				else
					txtrText.setText("Username does not exist");
				
				txtUsername_1.setText("Existing Username");
				pwdNewPassword_1.setText("New Password");
			}
		});
		btnChange.setBounds(1188, 660, 109, 37);
		frame.getContentPane().add(btnChange);
		
		txtUsername_1 = new JTextField();
		txtUsername_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsername_1.setText("");
			}
		});
		txtUsername_1.setText("Existing Username");
		txtUsername_1.setBounds(513, 659, 327, 38);
		frame.getContentPane().add(txtUsername_1);
		txtUsername_1.setColumns(10);
		
		JLabel lblAddNewUser = new JLabel("Add New User");
		lblAddNewUser.setForeground(SystemColor.desktop);
		lblAddNewUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddNewUser.setFont(new Font("Calibri", Font.BOLD, 28));
		lblAddNewUser.setBounds(513, 536, 665, 43);
		frame.getContentPane().add(lblAddNewUser);
		
		JLabel lblChangePasswordOf = new JLabel("Change Password Of Existing User");
		lblChangePasswordOf.setHorizontalAlignment(SwingConstants.LEFT);
		lblChangePasswordOf.setForeground(SystemColor.desktop);
		lblChangePasswordOf.setFont(new Font("Calibri", Font.BOLD, 28));
		lblChangePasswordOf.setBounds(513, 618, 665, 43);
		frame.getContentPane().add(lblChangePasswordOf);
		
		txtNameOfCollection = new JTextField();
		txtNameOfCollection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNameOfCollection.setText("");
			}
		});
		txtNameOfCollection.setText("Name Of Collection");
		txtNameOfCollection.setBounds(850, 487, 328, 38);
		frame.getContentPane().add(txtNameOfCollection);
		txtNameOfCollection.setColumns(10);
		
		txtNameOfDatabase = new JTextField();
		txtNameOfDatabase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNameOfDatabase.setText("");
			}
		});
		txtNameOfDatabase.setText("Name Of Database");
		txtNameOfDatabase.setBounds(513, 487, 327, 38);
		frame.getContentPane().add(txtNameOfDatabase);
		txtNameOfDatabase.setColumns(10);
		
		JButton btnCount = new JButton("Count");
		btnCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoCollection<Document> coll=Mongo.showdata(txtNameOfDatabase.getText().toString(), txtNameOfCollection.getText().toString(), txtDatabase, txtCollections);
				long count=coll.count();

				txtrText.setText("Database: "+txtNameOfDatabase.getText().toString()+"\n"+"Collection: "+txtNameOfCollection.getText().toString()+"\n"+"Total No of Documents: "+count);
				txtNameOfDatabase.setText("Name Of Database");
				txtNameOfCollection.setText("Name Of Collection");
			}
		});
		btnCount.setBounds(1188, 487, 109, 38);
		frame.getContentPane().add(btnCount);
		
		JLabel lblCountNoOf = new JLabel("Count No Of Documents In Collection");
		lblCountNoOf.setForeground(SystemColor.desktop);
		lblCountNoOf.setFont(new Font("Calibri", Font.BOLD, 28));
		lblCountNoOf.setBounds(513, 449, 493, 38);
		frame.getContentPane().add(lblCountNoOf);
		
		txtDatabaseName = new JTextField();
		txtDatabaseName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDatabaseName.setText("");
			}
		});
		txtDatabaseName.setText("Database Name");
		txtDatabaseName.setBounds(931, 157, 172, 38);
		frame.getContentPane().add(txtDatabaseName);
		txtDatabaseName.setColumns(10);
		
		txtCollectionName = new JTextField();
		txtCollectionName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtCollectionName.setText("");
			}
		});
		txtCollectionName.setText("Collection Name");
		txtCollectionName.setBounds(1125, 156, 172, 38);
		frame.getContentPane().add(txtCollectionName);
		txtCollectionName.setColumns(10);
		
		Image icon=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		Image background=new ImageIcon(this.getClass().getResource("/back2.png")).getImage();
		
		frame.setIconImage(icon);
		
		JLabel label = new JLabel("Mongo Stock DB");
		label.setForeground(new Color(30, 144, 255));
		label.setFont(new Font("Verdana", Font.BOLD, 40));
		label.setBounds(924, 0, 373, 70);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(background));
		frame.getContentPane().add(lblNewLabel);
		
	}
}

