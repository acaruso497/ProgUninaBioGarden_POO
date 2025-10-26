package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import database.Connessione;

public class daoVisualizzaP {
	
	//popola il text field di giorno inizio, giorno fine e il radio button dello stato dell'attività
	public String popolaAttivita(String titoloProgetto, String tipoAttivita,				
            JTextField fieldDataIA, JTextField fieldDataFA) {
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				String sql = null;

				try {
					conn = Connessione.getConnection();

					if ("Raccolta".equalsIgnoreCase(tipoAttivita)) {
						sql = """
								SELECT r.id_attivita, r.stato, r.giorno_inizio, r.giorno_fine
								FROM progetto_coltivazione pc
								JOIN attivita a   ON a.id_progetto = pc.id_progetto
								JOIN raccolta r   ON r.id_attivita = a.id_attivita
								WHERE pc.titolo = ?
								AND (
								r.giorno_inizio BETWEEN pc.data_inizio AND pc.data_fine
								OR r.giorno_fine   BETWEEN pc.data_inizio AND pc.data_fine
								OR pc.data_inizio  BETWEEN r.giorno_inizio AND r.giorno_fine
								)
								ORDER BY r.giorno_inizio DESC, r.giorno_fine DESC
								LIMIT 1
								""";
					} else if ("Irrigazione".equalsIgnoreCase(tipoAttivita)) {
						sql = """
								SELECT i.id_attivita, i.stato, i.giorno_inizio, i.giorno_fine
								FROM progetto_coltivazione pc
								JOIN attivita a    ON a.id_progetto = pc.id_progetto
								JOIN irrigazione i ON i.id_attivita = a.id_attivita
								WHERE pc.titolo = ?
								AND (
								i.giorno_inizio BETWEEN pc.data_inizio AND pc.data_fine
								OR i.giorno_fine   BETWEEN pc.data_inizio AND pc.data_fine
								OR pc.data_inizio  BETWEEN i.giorno_inizio AND i.giorno_fine
								)
								ORDER BY i.giorno_inizio DESC, i.giorno_fine DESC
								LIMIT 1
								""";
					} else if ("Semina".equalsIgnoreCase(tipoAttivita)) {
						sql = """
								SELECT s.id_attivita, s.stato, s.giorno_inizio, s.giorno_fine
								FROM progetto_coltivazione pc
								JOIN attivita a  ON a.id_progetto = pc.id_progetto
								JOIN semina s    ON s.id_attivita = a.id_attivita
								WHERE pc.titolo = ?
								AND (
								s.giorno_inizio BETWEEN pc.data_inizio AND pc.data_fine
								OR s.giorno_fine   BETWEEN pc.data_inizio AND pc.data_fine
								OR pc.data_inizio  BETWEEN s.giorno_inizio AND s.giorno_fine
								)
								ORDER BY s.giorno_inizio DESC, s.giorno_fine DESC
								LIMIT 1
								""";
					} else {
						fieldDataIA.setText(""); fieldDataFA.setText("");
						return null;
					}

					stmt = conn.prepareStatement(sql);
					stmt.setString(1, titoloProgetto);
					rs = stmt.executeQuery();

					if (!rs.next()) { fieldDataIA.setText(""); fieldDataFA.setText(""); return null; }

					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					java.sql.Date di = rs.getDate("giorno_inizio");
					java.sql.Date df = rs.getDate("giorno_fine");
					fieldDataIA.setText(di != null ? di.toLocalDate().format(fmt) : "");
					fieldDataFA.setText(df != null ? df.toLocalDate().format(fmt) : "");
					return rs.getString("stato");

				} catch (SQLException ex) {
					ex.printStackTrace();
					fieldDataIA.setText(""); fieldDataFA.setText(""); 
					return null;
				} finally {
					try { if (rs != null) rs.close(); } catch (Exception ignored) {}
					try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
					try { if (conn != null) conn.close(); } catch (Exception ignored) {}
				}
}

	
	// Aggiorna lo stato di ciascuna attività
	public boolean aggiornaStato(String stato, String tipoAttivita, String idLottoStr) {
	    int idLotto = Integer.parseInt(idLottoStr);
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    String sql1 = null;
	    String sql2 = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        
	        int idAttivita = 0;
	        int rows = 0;
	        
	        // seleziona l'ID attività più recente per il lotto e tipo di attività
	        if ("Raccolta".equals(tipoAttivita)) {
	            sql1 = "SELECT r.id_attivita " +
	                   "FROM Raccolta r " +
	                   "JOIN Attivita a ON r.id_attivita = a.id_attivita " +
	                   "WHERE a.id_lotto = ? " +
	                   "ORDER BY r.giorno_inizio DESC, r.giorno_fine DESC " +
	                   "LIMIT 1";
	            
	        } else if ("Irrigazione".equals(tipoAttivita)) {
	            sql1 = "SELECT i.id_attivita " +
	                   "FROM Irrigazione i " +
	                   "JOIN Attivita a ON i.id_attivita = a.id_attivita " +
	                   "WHERE a.id_lotto = ? " +
	                   "ORDER BY i.giorno_inizio DESC, i.giorno_fine DESC " +
	                   "LIMIT 1";
	            
	        } else if ("Semina".equals(tipoAttivita)) {
	            sql1 = "SELECT s.id_attivita " +
	                   "FROM Semina s " +
	                   "JOIN Attivita a ON s.id_attivita = a.id_attivita " +
	                   "WHERE a.id_lotto = ? " +
	                   "ORDER BY s.giorno_inizio DESC, s.giorno_fine DESC " +
	                   "LIMIT 1";
	        }
	        
	        stmt = conn.prepareStatement(sql1);
	        stmt.setInt(1, idLotto);
	        risultato = stmt.executeQuery();
	        
	        if (risultato.next()) {
	            idAttivita = risultato.getInt("id_attivita");
	        } else {  // Nessuna attività trovata per questo lotto e tipo
	            return false;
	        }
	        risultato.close();
	        stmt.close();
	        
	        // Aggiorna lo stato dell'attività più recente
	        sql2 = "UPDATE " + tipoAttivita + " SET stato = ? WHERE id_attivita = ?";
	        stmt = conn.prepareStatement(sql2);
	        stmt.setString(1, stato);
	        stmt.setInt(2, idAttivita);
	        rows = stmt.executeUpdate();

	        return rows > 0;
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false; 
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}
	

	//seleziono tutti i progetti del proprietario dato il suo username (utile per ComboProgetto)
    public List<String> getProgettiByProprietario(String username) {
        List<String> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet risultato = null;

        try {
            conn = Connessione.getConnection(); 

	        String sql = "SELECT pc.titolo " +
	        		  "FROM Progetto_Coltivazione pc " +
	        		  "JOIN Lotto l ON l.ID_Lotto = pc.ID_Lotto " +
	        		  "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
	        		  "WHERE p.username = ? " +
	        		  "ORDER BY pc.ID_Progetto ";
            
            stmt = conn.prepareStatement(sql);   
            stmt.setString(1, username);
            risultato = stmt.executeQuery();
            
            while (risultato.next()) {
                String titoloProgetto = risultato.getString("titolo");
                lista.add(titoloProgetto);
            }  
        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
            try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
        return lista;
    }
    
  //controlla se il progetto è completato
    public boolean isCompletata(String username, String titoloProgetto) {
    	Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet risultato = null;
    	
    	try {
    		conn = Connessione.getConnection();
    		
    		String sql = "SELECT pc.done " +
	                    "FROM Progetto_Coltivazione pc " +
	                    "JOIN Lotto l ON l.ID_Lotto = pc.ID_Lotto " +
	                    "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
	                    "WHERE p.username = ? AND pc.titolo = ?";
        
			 stmt = conn.prepareStatement(sql);   
			 stmt.setString(1, username);
			 stmt.setString(2, titoloProgetto);
			 risultato = stmt.executeQuery();

			 if (risultato.next()) {
				 return risultato.getBoolean("done");
		     }
		    	return false;
    } catch (SQLException ex) {
    	ex.printStackTrace();
    	return false;
    } finally {
        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }
 }   
    

	//recupera i lotti di un proprietario (utile per popolare ComboLotti)
		public String getLottiByProprietario(String titoloProgetto, String codiceFiscaleProprietario) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet risultato = null;
		    String idLotto = null;

		    try {
		        conn = Connessione.getConnection(); 
		        String sql = "SELECT l.ID_Lotto " +
		                     "FROM Lotto l " +
		                     "JOIN Progetto_Coltivazione pc ON l.ID_Lotto = pc.ID_Lotto " +  
		                     "WHERE pc.titolo = ? " +
		                     "AND l.Codice_FiscalePr = ?";
		     
		        stmt = conn.prepareStatement(sql);   
		        stmt.setString(1, titoloProgetto); 
		        stmt.setString(2, codiceFiscaleProprietario);
		        risultato = stmt.executeQuery();

		        if (risultato.next()) {
		        	idLotto = String.valueOf(risultato.getInt("ID_Lotto"));
		        }

		    } catch (SQLException ex) {
		    	ex.printStackTrace();
		    } finally {
		        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
		        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
		    }
		    return idLotto;
		}
	
		
	//popola la combobox del progetto, il text field di data inizio, data fine, stima raccolto
	public void popolaDatiProgetto(String titoloProgetto, JTextField fieldStima, JTextField fieldDataIP, JTextField fieldDataFP) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet risultato = null;
			
			try {
			conn = Connessione.getConnection(); 
			//recupera tutti i dati del progetto tramite la view
			String sql = "SELECT stima_raccolto, data_inizio, data_fine FROM view_raccolto WHERE titolo = ?"; 
			
			stmt = conn.prepareStatement(sql);   
			stmt.setString(1, titoloProgetto);
			risultato = stmt.executeQuery();
			
			if (risultato.next()) {
				// Setta stima del raccolto
	            String stima = risultato.getString("stima_raccolto");
	            if (stima != null) {
	                fieldStima.setText(stima + " kg");
	            } else {
	                fieldStima.setText("");
	            }
	            
			
			// Converte date SQL in stringa semplice in modo da popolare il field
			java.sql.Date sqlDataInizio = risultato.getDate("data_inizio");
			if (sqlDataInizio != null) {
				 LocalDate dataInizio = sqlDataInizio.toLocalDate();
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 fieldDataIP.setText(dataInizio.format(formatter));
				  } else {
					 fieldDataIP.setText("");
				  } 
			
				java.sql.Date sqlDataFine = risultato.getDate("data_fine");
				if (sqlDataFine != null) {
				 LocalDate dataFine = sqlDataFine.toLocalDate();
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 fieldDataFP.setText(dataFine.format(formatter));
					} else {
						fieldDataFP.setText("");
					} 
			}
			} catch (SQLException | NumberFormatException ex) {
				ex.printStackTrace();
			} finally {
				try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
				try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
				try { if (conn != null) conn.close(); } catch (Exception ignored) {}
			}
	}
	
	

	//mostra il raccolto prodotto per ogni coltura selezionata
	public void mostraRaccolto(String titoloProgetto, String idLottoStr, String coltura) {
		int idLotto = Integer.parseInt(idLottoStr);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		
		try {
				conn = Connessione.getConnection(); 
				String sql="SELECT raccoltoprodotto FROM ProprietarioRaccoltoColture WHERE varietà = ? AND id_lotto = ? AND titolo = ?";
				stmt = conn.prepareStatement(sql);   
				stmt.setString(1, coltura);
				stmt.setInt(2, idLotto);
				stmt.setString(3, titoloProgetto);
				risultato = stmt.executeQuery();

		}  catch (SQLException | NumberFormatException ex) {
			ex.printStackTrace();
		} finally {
			try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
			try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
			try { if (conn != null) conn.close(); } catch (Exception ignored) {}
		}

	}
	
	
	//Libera un lotto da un progetto di coltivazione e tutti i suoi riferimenti
	public boolean terminaProgetto(String titoloProgetto, String idLottoStr) {
		int idLotto = Integer.parseInt(idLottoStr);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		
		try {
				conn = Connessione.getConnection(); 
				@SuppressWarnings("unused")
				int rows = 0;
	
				// segno il progetto come completato con la flag done
		        String sql1 = "UPDATE Progetto_Coltivazione SET done = true WHERE id_lotto = ? AND titolo = ? ";
		        stmt = conn.prepareStatement(sql1);
		        stmt.setInt(1, idLotto);
		        stmt.setString(2, titoloProgetto);
		        rows = stmt.executeUpdate();
		        stmt.close();
				
		        //segno l'attività come completata
		        String sql2 = "UPDATE Attivita SET stato = 'completata' WHERE id_lotto = ? ";
		        stmt = conn.prepareStatement(sql2);
		        stmt.setInt(1, idLotto);
		        rows = stmt.executeUpdate();
		        stmt.close();
		        
		        //ricavo l'id dell'attività in modo da collegare le 3 attività
		        String sqlAttivita = "SELECT id_attivita FROM Attivita WHERE id_lotto = ? ";
		        stmt = conn.prepareStatement(sqlAttivita);
		        stmt.setInt(1, idLotto);
		        risultato = stmt.executeQuery();
		        
		        List<Integer> idAttivitaList = new ArrayList<>();
		        
		        while (risultato.next()) {
		            idAttivitaList.add(risultato.getInt("id_attivita"));
		        }
		        risultato.close();
		        stmt.close();
		        
		        //per ogni id dell'attività, segna ogni attività come completata
		        for (int idAttivita : idAttivitaList) {
		            // Segna semina come completata
		            String sql3 = "UPDATE Semina SET stato = 'completata' WHERE id_attivita = ?";
		            stmt = conn.prepareStatement(sql3);
		            stmt.setInt(1, idAttivita);
		            stmt.executeUpdate();
		            stmt.close();
		            
		            // Segna irrigazione come completata
		            String sql4 = "UPDATE Irrigazione SET stato = 'completata' WHERE id_attivita = ?";
		            stmt = conn.prepareStatement(sql4);
		            stmt.setInt(1, idAttivita);
		            stmt.executeUpdate();
		            stmt.close();
		            
		            // Segna raccolta come completata
		            String sql5 = "UPDATE Raccolta SET stato = 'completata' WHERE id_attivita = ?";
		            stmt = conn.prepareStatement(sql5);
		            stmt.setInt(1, idAttivita);
		            stmt.executeUpdate();
		            stmt.close();
		        }
				return true;
		}  catch (SQLException | NumberFormatException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
			try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
			try { if (conn != null) conn.close(); } catch (Exception ignored) {}
		}		
	}
	
	
	//restituisce la raccolta del prodotto selezionato nella dropdown
	public String getRaccoltoProdotto(String username, int idLotto) {
	    String raccolto = "";
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        String sql = "SELECT c.raccoltoProdotto " +
	                     "FROM Coltura c " +
	                     "JOIN Progetto_Coltura pc ON c.ID_Coltura = pc.ID_Coltura " +
	                     "JOIN Progetto_Coltivazione pcol ON pc.ID_Progetto = pcol.ID_Progetto " +
	                     "JOIN Lotto l ON pcol.ID_Lotto = l.ID_Lotto " +
	                     "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
	                     "WHERE p.username = ? AND l.ID_Lotto = ?";
	        
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setInt(2, idLotto);
	        risultato = stmt.executeQuery();
	        
	        if (risultato.next()) {
	            raccolto = risultato.getString("raccoltoProdotto");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
	        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
	    }
	    return raccolto;
	}	
	
	//restituisce le colture presenti nel lotto del progetto di coltivazione in riferimento al proprietario (utile per la dropdown)
	public ArrayList<String> getColtureProprietario(String CF, String titoloProgetto) {
	    ArrayList<String> listaC = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        String sql = "SELECT DISTINCT c.varietà " +
	                     "FROM Coltura c " +
	                     "JOIN Progetto_Coltura pc ON c.ID_Coltura = pc.ID_Coltura " +
	                     "JOIN Progetto_Coltivazione pcol ON pc.ID_Progetto = pcol.ID_Progetto " +
	                     "JOIN Lotto l ON pcol.ID_Lotto = l.ID_Lotto " +
	                     "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
	                     "WHERE p.codice_fiscale = ? AND pcol.titolo = ?";
	        
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, CF);
	        stmt.setString(2, titoloProgetto);
	        risultato = stmt.executeQuery();
	        
	        while (risultato.next()) {
	            listaC.add(risultato.getString("varietà"));
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
	        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
	    }
	    return listaC;
	}
	
  
//restituisce il raccolto prodotto dalla coltura	
public String getRaccoltoProdotto(String username, int idLotto, String coltura) {
    String raccolto = "";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet risultato = null;
    
    try {
        conn = Connessione.getConnection();
        String sql = "SELECT c.raccoltoProdotto " +
                     "FROM Coltura c " +
                     "JOIN Progetto_Coltura pc ON c.ID_Coltura = pc.ID_Coltura " +
                     "JOIN Progetto_Coltivazione pcol ON pc.ID_Progetto = pcol.ID_Progetto " +
                     "JOIN Lotto l ON pcol.ID_Lotto = l.ID_Lotto " +
                     "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
                     "WHERE p.username = ? AND l.ID_Lotto = ? AND c.varietà = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setInt(2, idLotto);
        stmt.setString(3, coltura);
        risultato = stmt.executeQuery();
        
        if (risultato.next()) {
            raccolto = risultato.getString("raccoltoProdotto");
            if (raccolto != null) {
                raccolto += " kg"; // Aggiungi l'unità per uniformità
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }
    return raccolto.isEmpty() ? "nessun dato!!!" : raccolto; // Restituisci "0 kg" se non trovato
}
}
