package nosql;

import com.mongodb.MongoClient;
 import com.mongodb.MongoClientURI;
 import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
 import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class Mongo {
	
	static  MongoClientURI uri = new MongoClientURI("/* Paste your connection string here */");
	static  MongoClient mongoClient = new MongoClient(uri);
	  
	public static MongoCollection<Document> showdata(String Database, String Collection, JTextField txtDatabase, JTextField txtCollections) {
	
      try{
    	  MongoDatabase database=mongoClient.getDatabase(Database);
    	  MongoCollection<Document> coll = database.getCollection(Collection);
    	  return coll;
    	  
      }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
         txtDatabase.setText("Enter Database Name");
         txtCollections.setText("Enter Collection Name");
         return null;
      }
   }
	
	
	public static MongoIterable<String> listdatabase() {
		try {
			return mongoClient.listDatabaseNames();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
	public static MongoIterable<String> listcollection(String databasename, JTextField txtDatabasename) {
		try{
			MongoDatabase database=mongoClient.getDatabase(databasename);
			return database.listCollectionNames();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			txtDatabasename.setText("Database Names");
			return null;
		}
	}
	
	public static boolean validateUser(String username, String password) {
		
		return false;
	}
}
