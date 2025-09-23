package dao;
import database.Connessione;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DAO {
	// ______________registrazione____________

		// Registrazione COLTIVATORE
		public static boolean registraC(String nome ,String cognome ,String username, String password, String cf) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    try {
		        conn = Connessione.getConnection();
		        String sql = "INSERT INTO coltivatore (nome, cognome ,username, psw,Codice_Fiscale) VALUES (?, ?,?, ?,?)";
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, nome);
		        stmt.setString(2, cognome);
		        stmt.setString(3, username);
		        stmt.setString(4, password);
		        stmt.setString(5, cf);
		        
		        int rows = stmt.executeUpdate();   // ritorna quante righe inserite
		        if (rows ==1) { return true;}
		        else {return false;}                // true se una riga inserita
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return false;
		    } finally {
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}

		// Registrazione PROPRIETARIO
		public static boolean registraP(String nome ,String cognome ,String username, String password, String cf) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    try {
		        conn = Connessione.getConnection();
		        String sql = "INSERT INTO proprietario (nome, cognome ,username, psw,Codice_Fiscale)VALUES (?, ?,?, ?,?)";
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, nome);
		        stmt.setString(2, cognome);
		        stmt.setString(3, username);
		        stmt.setString(4, password);
		        stmt.setString(5, cf);
		        int rows = stmt.executeUpdate();
		        if (rows ==1) { return true;}
		        else {return false;}
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return false;
		    } finally {
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}
		//username esistente
		public static boolean usernameEsiste(String username) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		        conn = Connessione.getConnection();
		        
		        // Query che cerca lo username in entrambe le tabelle
		        String sql = "SELECT username FROM Proprietario WHERE username = ? " +
		                     "UNION " +
		                     "SELECT username FROM Coltivatore WHERE username = ?";
		        
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, username);
		        stmt.setString(2, username);
		        
		        rs = stmt.executeQuery();
		        
		        // Se il ResultSet ha almeno una riga, lo username esiste
		        return rs.next();
		        
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return false; // In caso di errore, assumiamo che lo username non esista
		    } finally {
		        // Chiudi le risorse
		        try { if (rs != null) rs.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}
		// username esistente
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
	
	
	
	// GUI: CREA PROGETTO 
		public static boolean creaP (String titolo, String descrizione, String stimaRaccolto, 
									String dataIT, String dataFT, String tipoAttivita, 
									String dataIA,String dataFA, String coltivatori, int idLotto) {
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet risultato = null;
			try {
					conn = Connessione.getConnection();
					String sql1 = "INSERT INTO Progetto_Coltivazione (titolo, descrizione, "
							+ "stima_raccolto, data_inizio, data_fine) VALUES (?, ?, ?, ?, ?)";
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
		                String sqlSemina = "INSERT INTO Semina (giorno_inizio, giorno_fine, "
		                		+ "profondita, tipo_semina, ID_Attivita) "
		                		+ "VALUES (?, ?, 10, 'Diretta', (SELECT MAX(ID_Attivita) FROM Attivita))";
		                stmt = conn.prepareStatement(sqlSemina);
		                stmt.setString(1, dataIA);
		                stmt.setString(2, dataFA);
		                stmt.executeUpdate();
		            } else if (tipoAttivita.equals("Irrigazione")) {
		                String sqlIrrigazione = "INSERT INTO Irrigazione (giorno_inizio, giorno_fine, "
		                		+ "tipo_irrigazione, ID_Attivita) "
		                		+ "VALUES (?, ?, 'a goccia', (SELECT MAX(ID_Attivita) FROM Attivita))";
		                stmt = conn.prepareStatement(sqlIrrigazione);
		                stmt.setString(1, dataIA);
		                stmt.setString(2, dataFA);
		                stmt.executeUpdate();
		            } else if (tipoAttivita.equals("Raccolta")) {
		                String sqlRaccolta = "INSERT INTO Raccolta (giorno_inizio, giorno_fine, "
		                		+ "raccolto_effettivo, ID_Attivita) "
		                		+ "VALUES (?, ?, 0, (SELECT MAX(ID_Attivita) FROM Attivita))";
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

	
	
		// GUI: CREA NOTIFICAArrayList<String> usernamesList
		public boolean Inserisci_NotificaDB(String utentiTag,Date dataEvento, 
											String titolo, String descrizione) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet risultato = null;
			
			try {
				conn = Connessione.getConnection(); 
				
				String sql = "INSERT INTO \"notifica\" (\"attivita_programmate\", \"data_evento\", \"utenti_tag\", \"titolo\", \"descrizione\") " +
					    "VALUES (?, ?, ?, ?, ?)";
			    stmt = conn.prepareStatement(sql);
			    stmt.setString(1, "");    // Attivita_programmate (vuoto)
			    stmt.setDate(2,new java.sql.Date(dataEvento.getTime()) );
			    stmt.setString(3, utentiTag);
			    stmt.setString(4, titolo);
			    stmt.setString(5, descrizione);

			    return stmt.executeUpdate() > 0; // Ritorna true se almeno una riga è stata inserita
		}catch(SQLException ex) {
			ex.printStackTrace();
			  return false;
		}
		finally {
				try { if (risultato != null) risultato.close(); } catch (Exception e) {}
			    try { if (stmt != null) stmt.close(); } catch (Exception e) {}
			    try { if (conn != null) conn.close(); } catch (Exception e) {}
			  }	
		}
		
		public String getDestinatariUsernamesByProprietario(String usernameProprietario) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet risultato = null;

		    try {
		        conn = Connessione.getConnection();

		        // Tutti i coltivatori che lavorano su QUALSIASI lotto del proprietario indicato
		        String sql =
		                "SELECT COALESCE(string_agg(DISTINCT c.username, ',' ORDER BY c.username), '') AS usernames " +
		                        "FROM proprietario p " +
		                        "JOIN lotto l       ON l.codice_fiscalepr = p.codice_fiscale " +
		                        "JOIN attivita a    ON a.id_lotto = l.id_lotto " +
		                        "JOIN coltivatore c ON c.codice_fiscale = a.codice_fiscalecol " +
		                        "WHERE p.username = ?";

		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, usernameProprietario);

		        risultato = stmt.executeQuery();
		        if (risultato.next()) {
		            String s = risultato.getString(1);
		            return s != null ? s : "";
		        }
		        return "";
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return "";
		    } finally {
		        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}
		
		public boolean ciSonoNotificheNonLette(String usernameColtivatore) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;

		    try {
		        conn = Connessione.getConnection();

		        String sql = 
		        	    "SELECT COUNT(*) " +
		        	    "FROM \"notifica\" " +
		        	    "WHERE \"lettura\" = FALSE " +
		        	    "  AND \"utenti_tag\" LIKE ?";

		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, "%" + usernameColtivatore + "%");

		        rs = stmt.executeQuery();
		        if (rs.next()) {
		            int count = rs.getInt(1);
		            return count > 0;   // true se ci sono notifiche non lette
		        }
		        return false;
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return false;
		    } finally {
		        try { if (rs != null) rs.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}
		
		public boolean segnaNotificheColtivatoreComeLette(String usernameColtivatore) {
		    Connection conn = null;
		    PreparedStatement stmt = null;

		    try {
		        conn = Connessione.getConnection();

		        String sql = 
		        	    "UPDATE \"notifica\" " +
		        	    "SET \"lettura\" = TRUE " +
		        	    "WHERE \"lettura\" = FALSE " +
		        	    "  AND \"utenti_tag\" LIKE ?";


		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, "%" + usernameColtivatore + "%");

		        int updated = stmt.executeUpdate();
		        return updated > 0;  // true se almeno una notifica è stata aggiornata
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return false;
		    } finally {
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}
		
		public String getNotificheNonLette(String usernameColtivatore) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;

		    try {
		        conn = Connessione.getConnection();

		        String sql =
		        	    "SELECT \"titolo\", \"descrizione\" " +
		        	    "FROM \"notifica\" " +
		        	    "WHERE \"lettura\" = FALSE " +
		        	    "  AND \"utenti_tag\" LIKE ?";


		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, "%" + usernameColtivatore + "%");

		        rs = stmt.executeQuery();

		        StringBuilder sb = new StringBuilder();
		        while (rs.next()) {
		            String titolo = rs.getString("titolo");
		            String descrizione = rs.getString("descrizione");

		            sb.append(titolo).append(" : ").append(descrizione).append("\n");
		        }

		        return sb.toString().trim(); // rimuove eventuale \n finale
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return "";
		    } finally {
		        try { if (rs != null) rs.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}

		
		
		
	//GUI: CREA NOTIFICA


//_____________________!!!   DAO:coltivatore !!!!____________________________________

public List<String> popolaProgettiCB(String username) {
    List<String> lista = new ArrayList<>(); // Lista vuota per i titoli dei progetti
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet risultato = null;

    try {
        conn = Connessione.getConnection(); // Ottiene la connessione al DB

        String sql = "SELECT titolo_progetto FROM coltivatoreview WHERE username_coltivatore = ?"; 
        // Seleziona solo i titoli dei progetti associati all'username

        stmt = conn.prepareStatement(sql);   
        stmt.setString(1, username);         // Imposta il parametro username
        risultato = stmt.executeQuery();     // Esegue la query

        while (risultato.next()) {
            String titolo = risultato.getString("titolo_progetto"); // Ottiene il titolo
            lista.add(titolo); // Aggiunge il titolo alla lista
        }

    } catch (SQLException ex) {
        ex.printStackTrace(); // Stampa l'errore in console
    } finally {
        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }

    return lista; // Restituisce la lista (vuota se nessun risultato o errore)
}
//           _____________________________________________________________

public ArrayList<String> dateI_FProgCB(String titolo_progetto, String username) {
	ArrayList<String> date = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet risultato = null;

    try {
        conn = Connessione.getConnection();
        String sql = "SELECT data_inizio_progetto, data_fine_progetto "
        		+ "FROM coltivatoreview WHERE username_coltivatore = ? AND titolo_progetto = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, titolo_progetto);
        risultato = stmt.executeQuery();

        while (risultato.next()) {
            String dataInizio = risultato.getString("data_inizio_progetto");
            String dataFine = risultato.getString("data_fine_progetto");
            date.add(dataInizio);
            date.add(dataFine);
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }

    return date;
}

//           _____________________________________________________________

//attivita data inizio e fine
public static ArrayList<String> idList = new ArrayList<>();

public ArrayList<String> getAttivitaByPr(String titolo_progetto, String username) {
    ArrayList<String> tipi = new ArrayList<>();
    idList.clear();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Connessione.getConnection();
        String sql = "SELECT ID_Attivita, " +
                     "CASE WHEN ID_Semina IS NOT NULL THEN 'Semina' " +
                     "     WHEN ID_Irrigazione IS NOT NULL THEN 'Irrigazione' " +
                     "     WHEN ID_Raccolta IS NOT NULL THEN 'Raccolta' END AS tipo_attivita " +
                     "FROM coltivatoreview " +
                     "WHERE username = ? AND titolo_progetto = ? " +
                     "ORDER BY data_inizio_attivita";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, titolo_progetto);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String tipo = rs.getString("tipo_attivita");
            String id = rs.getString("ID_Attivita");
            if (tipo != null) {
                tipi.add(tipo);
                idList.add(id);
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
        try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
        try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
    }

    return tipi;
}

public String[] getDateByAttivitaId(String idAttivita) {
    String[] date = new String[2];
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Connessione.getConnection();
        String sql = "SELECT " +
                     "COALESCE(data_inizio_semina, data_inizio_irrigazione, data_inizio_raccolta) AS data_inizio, " +
                     "COALESCE(data_fine_semina, data_fine_irrigazione, data_fine_raccolta) AS data_fine " +
                     "FROM coltivatoreview WHERE ID_Attivita = ?";
        
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(idAttivita)); // ✅ CONVERTI String → int
        rs = stmt.executeQuery();

        if (rs.next()) {
            date[0] = rs.getString("data_inizio");
            date[1] = rs.getString("data_fine");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    } catch (NumberFormatException e) {
        System.err.println("ID attivita non valido: " + idAttivita);
    } finally {
        try { if (rs != null) rs.close(); } catch (SQLException ignored) {}
        try { if (stmt != null) stmt.close(); } catch (SQLException ignored) {}
        try { if (conn != null) conn.close(); } catch (SQLException ignored) {}
    }

    return date;
}

//PRIMA FUNZIONE: Restituisce gli ID per la dropdown
public List<String> getIdAttivitaColtivatore(String username) {
 List<String> idList = new ArrayList<>();
 Connection conn = null;
 PreparedStatement stmt = null;
 ResultSet rs = null;

 try {
     conn = Connessione.getConnection();
     String sql = "SELECT id_semina, id_irrigazione, id_raccolta " +
                  "FROM coltivatoreview " +
                  "WHERE username_coltivatore = ? " +
                  "ORDER BY giorno_assegnazione";
     
     stmt = conn.prepareStatement(sql);
     stmt.setString(1, username);
     rs = stmt.executeQuery();

     while (rs.next()) {
 	    if (rs.getObject("id_semina") != null) {
 	        idList.add("Semina-" + rs.getInt("id_semina"));
 	    }
 	    if (rs.getObject("id_irrigazione") != null) {
 	        idList.add("Irrigazione-" + rs.getInt("id_irrigazione"));
 	    }
 	    if (rs.getObject("id_raccolta") != null) {
 	        idList.add("Raccolta-" + rs.getInt("id_raccolta"));
 	    }
 	}
 } catch (SQLException ex) {
     ex.printStackTrace();
 } finally {
     // cleanup
 }
 return idList;
}

//SECONDA FUNZIONE: Restituisce i tipi per indice
public List<String> getTipiAttivitaColtivatore(String username) {
 List<String> tipoList = new ArrayList<>();
 Connection conn = null;
 PreparedStatement stmt = null;
 ResultSet rs = null;

 try {
     conn = Connessione.getConnection();
     String sql = "SELECT id_semina, id_irrigazione, id_raccolta " +
                  "FROM coltivatoreview " +
                  "WHERE username_coltivatore = ? " +
                  "ORDER BY giorno_assegnazione";
     
     stmt = conn.prepareStatement(sql);
     stmt.setString(1, username);
     rs = stmt.executeQuery();

     while (rs.next()) {
         if (rs.getObject("id_semina") != null) {
             tipoList.add("Semina");  // CORRETTO: aggiungi solo il tipo
         }
         if (rs.getObject("id_irrigazione") != null) {
             tipoList.add("Irrigazione");  // CORRETTO: aggiungi solo il tipo
         }
         if (rs.getObject("id_raccolta") != null) {
             tipoList.add("Raccolta");  // CORRETTO: aggiungi solo il tipo
         }
     }
 } catch (SQLException ex) {
     ex.printStackTrace();
 } finally {
     // cleanup
 }
 return tipoList;
}

public String getLottoEPosizione(String progetto, String username) {
    String risultato = "";
    
    try (Connection conn = Connessione.getConnection();
         PreparedStatement stmt = conn.prepareStatement(
             "SELECT DISTINCT id_lotto, posizione " +
             "FROM coltivatoreview " +
             "WHERE titolo_progetto = ? AND username_coltivatore = ?")) {
        
        stmt.setString(1, progetto);
        stmt.setString(2, username);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            risultato = "Lotto: " + rs.getInt("id_lotto") + ", Posizione: " + rs.getInt("posizione");
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return risultato;
}
}


//_____________________!!!   DAO:coltivatore !!!!____________________________________

