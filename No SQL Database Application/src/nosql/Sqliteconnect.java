package nosql;

import java.sql.*;
import javax.swing.*;
public class Sqliteconnect {
	
	public static boolean save(String Username, String Password, int Serial, String Admin){
		
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			
			if(Admin.equals("null")) {
				PreparedStatement ps=conn.prepareStatement("insert into usernamepassword(Serial,Username,Password) values(?,?,?)");
				ps.setInt(1,Serial);
				ps.setString(2,Username);
				ps.setString(3,Password);
				Admin=Admin.toUpperCase();
				ps.executeUpdate();
				conn.close();
			} else {
				PreparedStatement ps=conn.prepareStatement("insert into usernamepassword(Serial,Username,Password,Admin) values(?,?,?,?)");
				ps.setInt(1,Serial);
				ps.setString(2,Username);
				ps.setString(3,Password);
				Admin=Admin.toUpperCase();
				ps.setString(4,Admin);
				ps.executeUpdate();
				conn.close();
			}
			return true;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	
	public static boolean delete(String Username){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("delete from usernamepassword where Username=?");
			ps.setString(1,Username);
			ps.executeUpdate();
			conn.close();
			return true;
		}catch(Exception e){JOptionPane.showMessageDialog(null, e);}
		return false;
	}

	
	
	public static boolean validate(String Username, String Password){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("select Password from usernamepassword where Username=? and Password=?");
			ps.setString(1, Username);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			
			String Spass=rs.getString("Password");
			
			if(Spass.equals(Password)) {
				conn.close();
				return true;
			}
		}  catch(Exception e){JOptionPane.showMessageDialog(null, e);
		return false;
	} return false;
	}
	
	
	
	public static boolean validateadmin(String Username, String Password ) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("select Admin,Password from usernamepassword where Username=? and Password=?");
			ps.setString(1, Username);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			
			String Spass=rs.getString("Password");
			String admin=rs.getString("Admin");

			if(Spass.equals(Password) && admin.equals("YES") ) {
				conn.close();
				return true;
			}
			return false;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	
	public static boolean changepass(String Username, String Oldpassword, String Newpassword) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("select Password from usernamepassword where Username=? and Password=?");
			ps.setString(1, Username);
			ps.setString(2, Oldpassword);
			ResultSet rs=ps.executeQuery();
			String Spass=rs.getString("Password");
			System.out.print(Spass.equals(Oldpassword));
			if(Spass.equals(Oldpassword)) {
				ps=conn.prepareStatement("update usernamepassword set Password=? where Username=?");
				ps.setString(1, Newpassword);
				ps.setString(2, Username);
				ps.execute();
				conn.close();
				return true;
			}
		}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return false;
		}
			return false;
	}
	
	
	public static ResultSet showtable() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("select * from usernamepassword");
			return ps.executeQuery();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			
			return null;
		}
	}


	public static boolean changepassfrominside(String text, String pass) {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("select Password from usernamepassword where Username=?");
			ps.setString(1, text);
			ResultSet rs=ps.executeQuery();
			//System.out.print(rs.next()+" "+text+" "+pass);
;
			if(rs.next()) {
				ps=conn.prepareStatement("update usernamepassword set Password=? where Username=?");
				ps.setString(1, pass);
				ps.setString(2, text);
				ps.execute();
				conn.close();
				return true;
			}
			return false;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
	
	
	public static boolean showpass(String text, JTextArea jtext) {
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:UserPassInfo.sqlite");
			PreparedStatement ps=conn.prepareStatement("select Password from usernamepassword where Username=?");
			ps.setString(1, text);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				jtext.setText("Password: "+rs.getString("Password"));
				conn.close();
				return true;
			}
			return false;
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return false;
		}
	}
}
