package dao;
import database.Connessione;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DAO {

	//METODI
	
	//Autenticazione proprietario
	//____________________!!!   DAO: LOGIN     !!!!____________________________________
	
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
// GUI: CREA PROGETTO	!!!METODO NON ADTATTATO AL SINGLETON!!!
	//recupera i lotti di un proprietario (utile per popolare ComboLotti)
	public List<String> getLottiByProprietario(String username) {
	    List<String> lista = new ArrayList<>(); // Lista vuota per ID lotti
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;

	    try {
	        conn = Connessione.getConnection(); 

	        String sql = "SELECT l.ID_Lotto, l.posizione " + 
                    "FROM Lotto l " +
                    "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
                    "WHERE p.username = ? " +
                    "ORDER BY l.posizione"; 

	        stmt = conn.prepareStatement(sql);   
	        stmt.setString(1, username);         // Inserisce l'username del proprietario
	        risultato = stmt.executeQuery();

	        while (risultato.next()) {
	            int idLotto = risultato.getInt("ID_Lotto"); // Legge solo ID
                String idStr = String.valueOf(idLotto); 
                lista.add(idStr); // Aggiunge alla lista
	        }

	    } catch (SQLException e) {
	        System.err.println("Errore SELECT: " + e.getMessage());
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
	        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
	    }

	    return lista;
	}

	public static String getColtivatoreByUsername(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Connessione.getConnection();
            String sql = "SELECT username FROM coltivatore WHERE username = ?";  // O da Proprietario se misto
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("username");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (conn != null) conn.close(); } catch (Exception e) {}
        }
        return null;  // Se non trovato
    }
	
	
	
	// GUI: CREA PROGETTO  !!!METODO NON ADTATTATO AL SINGLETON!!!
	public static boolean creaP (String titolo, String descrizione, String stimaRaccolto, 
								String dataIT, String dataFT, String tipoAttivita, 
								String dataIA,String dataFA, String coltivatori, int idLotto) {
		
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
				int rowsProgetto = stmt.executeUpdate();  // Esegui l'INSERT e ottieni quante righe sono state modificate
		        if (rowsProgetto == 0) {  // Controlla se l'INSERT ha fallito (es. violazione constraint nel DB)
		            System.err.println("Errore: Nessuna riga inserita nel progetto");
		            return false;  // Esci con errore
		        }
				
			/* 	risultato = stmt.executeQuery();
				
			 	if(risultato.next()) {
					return true;
				}else {
					return false;
				}  */
			 
		        
		     // Passo 2: Valida coltivatori (singolo username, controlli rigorosi)
	            if (coltivatori == null || coltivatori.trim().isEmpty()) {
	                System.err.println("Errore: campo coltivatori vuoto o nullo");
	                return false;
	            }
	            String coltivatore = coltivatori.trim();
	            // Controllo base: solo alfanumerici, lunghezza tra 1 e 50
	            if (!coltivatore.matches("[a-zA-Z0-9]+") || coltivatore.length() > 50) {
	                System.err.println("Errore: username coltivatore non valido: " + coltivatore);
	                return false;
	            }
	            String usernameCol = getColtivatoreByUsername(coltivatore);
	            if (usernameCol == null) {
	                System.err.println("Errore: coltivatore non trovato nel DB: " + coltivatore);
	                return false;
	            }
	            
	           
	            
	            // Passo 3: INSERT in Attivita
	            String sqlAttivita = "INSERT INTO Attivita (Codice_FiscaleCol, ID_Lotto) VALUES (?, ?)";
	            stmt = conn.prepareStatement(sqlAttivita);
	            stmt.setString(1, usernameCol);
	            stmt.setInt(2, idLotto);
	            stmt.executeUpdate();
	            
	            
	            
	            // Passo 4: INSERT nella tabella specifica (if-else semplice)
	            if (tipoAttivita.equals("Semina")) {
	                String sqlSemina = "INSERT INTO Semina (giorno_inizio, giorno_fine, profondita, tipo_semina, ID_Attivita) VALUES (?, ?, 10, 'Diretta', (SELECT MAX(ID_Attivita) FROM Attivita))";
	                stmt = conn.prepareStatement(sqlSemina);
	                stmt.setString(1, dataIA);
	                stmt.setString(2, dataFA);
	                stmt.executeUpdate();
	            } else if (tipoAttivita.equals("Irrigazione")) {
	                String sqlIrrigazione = "INSERT INTO Irrigazione (giorno_inizio, giorno_fine, tipo_irrigazione, ID_Attivita) VALUES (?, ?, 'a goccia', (SELECT MAX(ID_Attivita) FROM Attivita))";
	                stmt = conn.prepareStatement(sqlIrrigazione);
	                stmt.setString(1, dataIA);
	                stmt.setString(2, dataFA);
	                stmt.executeUpdate();
	            } else if (tipoAttivita.equals("Raccolta")) {
	                String sqlRaccolta = "INSERT INTO Raccolta (giorno_inizio, giorno_fine, raccolto_effettivo, ID_Attivita) VALUES (?, ?, 0, (SELECT MAX(ID_Attivita) FROM Attivita))";
	                stmt = conn.prepareStatement(sqlRaccolta);
	                stmt.setString(1, dataIA);
	                stmt.setString(2, dataFA);
	                stmt.executeUpdate();
	            } else {
	                System.err.println("Errore: tipo attività non valido: " + tipoAttivita);
	                return false;
	            }
	            
	            return true; 			
			} catch(SQLException ex) {
			   ex.printStackTrace();
			   return false;
			} finally {
			   try { if (risultato != null) risultato.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		   }
		
	}
			
	// GUI: CREA PROGETTO	

	
	
	// GUI: CREA NOTIFICA  !!!METODO NON ADTATTATO AL SINGLETON!!!
	
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
