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
}
