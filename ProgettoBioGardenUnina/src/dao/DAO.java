package dao;
import database.Connessione;
import java.sql.*;

public class DAO {

	//METODI
	
	//Autenticazione proprietario
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


	//Autenticazione coltivatore
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

	
	//Creazione progetto
	public static boolean creaP (String titolo, String descrizione, String stimaRaccolto, String dataIT, String dataFT, String tipoAttivita, String dataIA, String dataFA, String coltivatori) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		try {
				conn = Connessione.getConnection();
				String sql1 = "INSERT INTO Progetto_Coltivazione (titolo, descrizione, stima_raccolto, data_inizio, data_fine) VALUES (?, ?, ?, ?, ?)";
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, titolo);
				stmt.setString(2, descrizione);
				stmt.setString(3, stimaRaccolto);
				stmt.setString(4, dataIT);
				stmt.setString(5, dataFT);
				
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
			
	

		
		
		
		
		

	
	
	
	
	//Creazione notifica
	
	/* NB: mettere i controlli per quando si clicca tutti i coltivatori per taggarli tutti. Nel caso di singoli, fare come in SQL
	 * Dinamico che andiamo ad estrapolare le stringhe separate da virgole.
	  */
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
