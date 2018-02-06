package nosql;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoIterable;

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
import java.awt.SystemColor;

public class Member_Login_Success {

	private JFrame frame;
	private JTextField txtCollections;
	private JTextField txtDatabase;
	private JTextField txtDatabasename;
	private JTextField txtNameOfCollection;
	private JTextField txtNameOfDatabase;
	private JTextField txtDatabaseName;
	private JTextField txtCollectionName;
	private JTextField txtEnterDatabaseName;
	private JTextField txtEnterCollectionName;
	private JTextField txtEnterDate;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member_Login_Success window = new Member_Login_Success();
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
	public Member_Login_Success() {
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
			public void actionPerformed(ActionEvent e) {
				Docs.main(new String[] {});
			}
		});
		btnDocs.setBounds(1307, 111, 41, 43);
		btnDocs.setIcon(new ImageIcon(docs));
		frame.getContentPane().add(btnDocs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(true);
		scrollPane.setBounds(24, 111, 446, 537);
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
		btnShowData.setBounds(1188, 590, 109, 38);
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
		txtCollections.setBounds(850, 589, 328, 38);
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
		txtDatabase.setBounds(513, 590, 327, 38);
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
		
		JLabel lblAdminFunctions = new JLabel("General Functions");
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
		btnListOfAll_1.setBounds(738, 206, 141, 38);
		frame.getContentPane().add(btnListOfAll_1);
		
		JButton btnMaximumClosingPrice = new JButton("Maximum Closing Price ");
		btnMaximumClosingPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MongoCollection<Document> coll=Mongo.showdata(txtDatabaseName.getText().toString(), txtCollectionName.getText().toString(), txtDatabase, txtCollections );					
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Close", -1)).limit(1);
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
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Open", -1)).limit(1);
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
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Close", 1)).limit(1);
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
		        FindIterable<Document> cursor=coll.find().sort((Bson) new BasicDBObject("Open", 1)).limit(1);
		        txtrText.setText(cursor.iterator().next().toString());		
		        txtDatabaseName.setText("Databse Name");
		        txtCollectionName.setText("Collection Name");
			}
		});
		btnMinimumOpeningPrice.setBounds(931, 353, 366, 38);
		frame.getContentPane().add(btnMinimumOpeningPrice);
		
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
		
		txtDatabasename = new JTextField();
		txtDatabasename.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtDatabasename.setText("");
			}
		});
		txtDatabasename.setText("Database Name");
		txtDatabasename.setBounds(513, 206, 215, 38);
		frame.getContentPane().add(txtDatabasename);
		txtDatabasename.setColumns(10);
		
		JLabel lblAddNewUser = new JLabel("Show All Data Of Collection");
		lblAddNewUser.setForeground(SystemColor.desktop);
		lblAddNewUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddNewUser.setFont(new Font("Calibri", Font.BOLD, 28));
		lblAddNewUser.setBounds(513, 550, 665, 43);
		frame.getContentPane().add(lblAddNewUser);
		
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
		
		JLabel lblSearchDataAccording = new JLabel("Search Data According To Date");
		lblSearchDataAccording.setFont(new Font("Calibri", Font.BOLD, 28));
		lblSearchDataAccording.setForeground(SystemColor.desktop);
		lblSearchDataAccording.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchDataAccording.setBounds(513, 255, 366, 38);
		frame.getContentPane().add(lblSearchDataAccording);
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
		
		txtEnterDatabaseName = new JTextField();
		txtEnterDatabaseName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtEnterDatabaseName.setText("");
			}
		});
		txtEnterDatabaseName.setText("Database Name");
		txtEnterDatabaseName.setBounds(513, 304, 113, 38);
		frame.getContentPane().add(txtEnterDatabaseName);
		txtEnterDatabaseName.setColumns(10);
		
		txtEnterCollectionName = new JTextField();
		txtEnterCollectionName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtEnterCollectionName.setText("");
			}
		});
		txtEnterCollectionName.setText("Collection Name");
		txtEnterCollectionName.setBounds(633, 304, 120, 38);
		frame.getContentPane().add(txtEnterCollectionName);
		txtEnterCollectionName.setColumns(10);
		
		txtEnterDate = new JTextField();
		txtEnterDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtEnterDate.setText("");
			}
		});
		txtEnterDate.setText("Date(yyyy-mm-dd)");
		txtEnterDate.setBounds(759, 304, 120, 38);
		frame.getContentPane().add(txtEnterDate);
		txtEnterDate.setColumns(10);
		
		JButton btnSearchInGiven = new JButton("Search In Given Database Collection");
		btnSearchInGiven.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MongoCollection<Document> coll=Mongo.showdata(txtEnterDatabaseName.getText().toString(), txtEnterCollectionName.getText().toString(), txtDatabase, txtCollections);
				FindIterable<Document> cursor=coll.find((Bson) new BasicDBObject("Date", txtEnterDate.getText().toString()));
				MongoCursor<Document> cur=cursor.iterator();
				txtrText.setText("");
				
				while(cur.hasNext()) {
					txtrText.append(cur.next().toString()+"\n");
				}

				txtEnterDatabaseName.setText("Database Name");
				txtEnterCollectionName.setText("Collection Name");
				txtEnterDate.setText("Date(yyyy-mm-dd)");
			}
		});
		btnSearchInGiven.setBounds(513, 353, 366, 38);
		frame.getContentPane().add(btnSearchInGiven);
		
		Image icon=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
		Image background=new ImageIcon(this.getClass().getResource("/back2.png")).getImage();
		
		frame.setIconImage(icon);
		
		JLabel lblMongoStockDb = new JLabel("Mongo Stock DB");
		lblMongoStockDb.setFont(new Font("Verdana", Font.BOLD, 40));
		lblMongoStockDb.setForeground(new Color(30, 144, 255));
		lblMongoStockDb.setBounds(924, 0, 373, 70);
		frame.getContentPane().add(lblMongoStockDb);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1366, 768);
		lblNewLabel.setIcon(new ImageIcon(background));
		frame.getContentPane().add(lblNewLabel);
		
	}
}

