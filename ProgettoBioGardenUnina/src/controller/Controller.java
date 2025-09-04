package controller;
import database.Connessione;
import java.sql.*;

public class Controller {

	//METODI
	public static boolean authP(String username, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		try {
				conn = Connessione.getConnection(); 
				String sql = "SELECT * FROM proprietario WHERE username=? AND psw=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, password);
				risultato = stmt.executeQuery();
				if(risultato.next()) {
					return true;
				}else {
					return false;
				}
		   } catch(SQLException ex) {
			   ex.printStackTrace();
			   return false;
		   } finally {
			   try { if (risultato != null) risultato.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		   }
		
	}
	
	public static boolean authC(String username, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		try {
				conn = Connessione.getConnection(); 
				String sql = "SELECT * FROM coltivatore WHERE username=? AND psw=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, password);
				risultato = stmt.executeQuery();
				if(risultato.next()) {
					return true;
				}else {
					return false;
				}
		   } catch(SQLException ex) {
			   ex.printStackTrace();
			   return false;
		   } finally {
			   try { if (risultato != null) risultato.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		   }
		
	}

	
	
	 public static boolean creaN(String data, String usernameC, String titolo, String descrizione) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		try {
				conn = Connessione.getConnection(); 
				String sql = "INSERT INTO notifica () VALUES (?, ?, ?, ?)";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, data);
				stmt.setString(2, usernameC);
				stmt.setString(3, titolo);
				stmt.setString(4, descrizione);
				risultato = stmt.executeQuery();
				if(risultato.next()) {
					return true;
				}else {
					return false;
				}
		   } catch(SQLException ex) {
			   ex.printStackTrace();
			   return false;
		   } finally {
			   try { if (risultato != null) risultato.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		   }
		
	} 
}
