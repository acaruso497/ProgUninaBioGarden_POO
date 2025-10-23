package dao;
import database.Connessione;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DAO {
	//home proprietario 
	
	public boolean aggiungiL(String cf) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        //controlla l'esistenza di lotti liberi
	        String sql1 = "SELECT ID_Lotto FROM Lotto WHERE Codice_FiscalePr IS NULL LIMIT 1";
	        stmt = conn.prepareStatement(sql1);
	        risultato = stmt.executeQuery();
	        
	        if (risultato.next()) {
	            // quando trovo un lotto libero, ricavo l'id
	            int idLottoLibero = risultato.getInt("ID_Lotto");
	            
	            // aggiungo il lotto al proprietario usando il suo codice fiscale
	            String sql2 = "UPDATE Lotto SET Codice_FiscalePr = ? WHERE ID_Lotto = ?";
	            stmt = conn.prepareStatement(sql2);
	            stmt.setString(1, cf);
	            stmt.setInt(2, idLottoLibero);
	            
	            int rows = stmt.executeUpdate();
	            return rows == 1; 
	        } else {
	            return false;
	        }
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}
	
	
	
	//home proprietario
	
	
	
	// ______________registrazione____________

		// Registrazione COLTIVATORE
		public static boolean registraC(String nome ,String cognome ,String username, String password, String cf, String usernameProprietario) {		//MODIFICA
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    try {
		        conn = Connessione.getConnection();

		        String sql = """
		            INSERT INTO Coltivatore 
		            (Codice_Fiscale, nome, cognome, username, psw, esperienza, username_proprietario)
		            VALUES (?, ?, ?, ?, ?, 'principiante', ?)
		        """;

		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, cf);
		        stmt.setString(2, nome);
		        stmt.setString(3, cognome);
		        stmt.setString(4, username);
		        stmt.setString(5, password);
		        stmt.setString(6, usernameProprietario);

		        int rows = stmt.executeUpdate();
		        return rows == 1;

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
		}// username esistente

		
		public ArrayList<String> popolaComboProprietari() {
			
	        ArrayList<String> usernames = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            // Ottieni la connessione al database
	            conn = Connessione.getConnection();

	            // Query SQL diretta per selezionare tutti gli username dalla tabella Proprietario
	            String sql = "SELECT username FROM Proprietario";
	            stmt = conn.prepareStatement(sql);

	            // Esegui la query
	            rs = stmt.executeQuery();

	            // Aggiungi ogni username all'ArrayList
	            while (rs.next()) {
	                usernames.add(rs.getString("username"));
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace(); // Stampa l'errore per debugging
	            System.out.println("Errore durante l'esecuzione della query: " + ex.getMessage());
	        } finally {
	            // Chiudi tutte le risorse nel blocco finally
	            try { if (rs != null) rs.close(); } catch (Exception e) {}
	            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	            try { if (conn != null) conn.close(); } catch (Exception e) {}
	        }

	        return usernames; // Restituisce l'ArrayList con gli username
	    }	
		
		// Metodo per recuperare i lotti di un proprietario dato l'username
	    public ArrayList<Integer> getLottiByProprietarioUsername(String usernameProprietario) {
	        ArrayList<Integer> lotti = new ArrayList<>();
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;
	        try {
	            conn = Connessione.getConnection();
	            String sql = "SELECT l.ID_Lotto " +
	                         "FROM Lotto l " +
	                         "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
	                         "WHERE p.username = ?";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, usernameProprietario);
	            rs = stmt.executeQuery();
	            while (rs.next()) {
	                lotti.add(rs.getInt("ID_Lotto"));
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        } finally {
	            try { if (rs != null) rs.close(); } catch (Exception e) {}
	            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	            try { if (conn != null) conn.close(); } catch (Exception e) {}
	        }
	        return lotti;
	    }
	    
	 // Metodo per associare un coltivatore a un lotto
	    public static boolean associaColtivatoreProprietario(String codiceFiscaleColtivatore, int idLotto) {		//MODIFICA
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = Connessione.getConnection();
	            // Inserimento in Attivita con ID_Attivita generato automaticamente (assumiamo autoincrement)
	            String sql = "INSERT INTO Attivita (Codice_FiscaleCol, ID_Lotto) VALUES (?, ?)";
	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, codiceFiscaleColtivatore);
	            stmt.setInt(2, idLotto);
	            int rows = stmt.executeUpdate();
	            return rows == 1;
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            return false;
	        } finally {
	            try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	            try { if (conn != null) conn.close(); } catch (Exception e) {}
	        }
	    }

	 // ______________registrazione____________

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
		
		
		//!!da pulire tutte le system out interne di debug!!
		public static String getCodiceFiscaleByUsername(String username) {
		    String codiceFiscale = null;
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet rs = null;

		    try {
		        // Ottieni la connessione al database (assumo che Connessione sia una classe di utilità)
		        conn = Connessione.getConnection();

		        // Query SQL diretta sulla tabella Proprietario
		        String sql = "SELECT Codice_Fiscale FROM Proprietario WHERE username = ?";
		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, username); // Imposta il parametro username

		        // Esegui la query
		        rs = stmt.executeQuery();

		        // Recupera il risultato
		        if (rs.next()) {
		            codiceFiscale = rs.getString("Codice_Fiscale"); // Recupera il Codice_Fiscale
		        } else {
		            System.out.println("Nessun proprietario trovato con username: " + username);
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace(); // Stampa l'errore per debugging
		        System.out.println("Errore durante l'esecuzione della query: " + ex.getMessage());
		    } finally {
		        // Chiudi tutte le risorse nel blocco finally
		        try { if (rs != null) rs.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }

		    return codiceFiscale; // Restituisce il Codice_Fiscale o null se non trovato
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
	
	
		

	
	
// GUI: CREA NOTIFICA ArrayList<String> usernamesList
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

		
		public ArrayList<String> getColtivatoriByProprietario(String usernameProprietario) {
			ArrayList<String> coltivatori = new ArrayList<>();
			
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet risultato = null;

		    try {
		        conn = Connessione.getConnection();

		        // Tutti i coltivatori che lavorano su QUALSIASI lotto del proprietario indicato
		        String sql =
		                "SELECT DISTINCT c.username " +
		                        "FROM proprietario p " +
		                        "JOIN lotto l       ON l.codice_fiscalepr = p.codice_fiscale " +
		                        "JOIN attivita a    ON a.id_lotto = l.id_lotto " +
		                        "JOIN coltivatore c ON c.codice_fiscale = a.codice_fiscalecol " +
		                        "WHERE p.username = ?";

		        stmt = conn.prepareStatement(sql);
		        stmt.setString(1, usernameProprietario);

		        risultato = stmt.executeQuery();
		        while (risultato.next()) {
		            String coltivatore = risultato.getString("username");
		            coltivatori.add(coltivatore);
		        }
		        
		        return coltivatori;
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    } finally {
		        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		    
		    return coltivatori;
		}
		
		
		
	//GUI: CREA NOTIFICA


//_____________________!!!   DAO:coltivatore !!!!____________________________________

public List<String> popolaProgettiCB(String usernameColtivatore) {		//MODIFICATO
		    List<String> lista = new ArrayList<>();
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;

		    try {
		        conn = Connessione.getConnection();

		        String sql = """
		            SELECT pc.titolo
		            FROM coltivatore c
		            JOIN proprietario p      ON p.username = c.username_proprietario
		            JOIN lotto l             ON l.codice_fiscalepr = p.codice_fiscale
		            JOIN progetto_coltivazione pc ON pc.id_lotto = l.id_lotto
		            WHERE c.username = ?
		              AND pc.done = false
		            ORDER BY pc.data_inizio DESC
		        """;

		        ps = conn.prepareStatement(sql);
		        ps.setString(1, usernameColtivatore);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            lista.add(rs.getString("titolo"));
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    } finally {
		        try { if (rs != null) rs.close(); } catch (Exception ignore) {}
		        try { if (ps != null) ps.close(); } catch (Exception ignore) {}
		        try { if (conn != null) conn.close(); } catch (Exception ignore) {}
		    }
		    return lista;
}


//           _____________________________________________________________

public ArrayList<String> dateI_FProgCB(String titolo_progetto, String usernameColtivatore) {		//MODIFICATO
    ArrayList<String> date = new ArrayList<>();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Connessione.getConnection();

        String sql = """
            SELECT pc.data_inizio, pc.data_fine
            FROM progetto_coltivazione pc
            JOIN lotto l         ON l.id_lotto = pc.id_lotto
            JOIN proprietario p  ON p.codice_fiscale = l.codice_fiscalepr
            JOIN coltivatore c   ON c.username_proprietario = p.username
            WHERE c.username = ?
              AND pc.titolo = ?
            ORDER BY pc.data_inizio DESC
            LIMIT 1
        """;

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, usernameColtivatore);
        stmt.setString(2, titolo_progetto);

        rs = stmt.executeQuery();

        if (rs.next()) {
            date.add(rs.getString("data_inizio"));
            date.add(rs.getString("data_fine"));
        }

    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
        try { if (conn != null) conn.close(); } catch (Exception ignored) {}
    }

    return date;
}


//           _____________________________________________________________

//attivita data inizio e fine
public static ArrayList<String> idList = new ArrayList<>();

public ArrayList<String> getAttivitaByPr(String titolo_progetto, String usernameColtivatore) {
    ArrayList<String> tipi = new ArrayList<>();
    idList.clear();
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Connessione.getConnection();

        String sql = """
            SELECT 
                a.id_attivita,
                CASE 
                    WHEN s.id_semina IS NOT NULL THEN 'Semina'
                    WHEN i.id_irrigazione IS NOT NULL THEN 'Irrigazione'
                    WHEN r.id_raccolta IS NOT NULL THEN 'Raccolta'
                END AS tipo_attivita
            FROM coltivatore c
            JOIN proprietario p         ON p.username = c.username_proprietario
            JOIN lotto l                ON l.codice_fiscalepr = p.codice_fiscale
            JOIN progetto_coltivazione pc ON pc.id_lotto = l.id_lotto
            JOIN attivita a             ON a.id_progetto = pc.id_progetto
            LEFT JOIN semina s          ON s.id_attivita = a.id_attivita
            LEFT JOIN irrigazione i     ON i.id_attivita = a.id_attivita
            LEFT JOIN raccolta r        ON r.id_attivita = a.id_attivita
            WHERE c.username = ?
              AND pc.titolo = ?
              AND pc.done = false
            ORDER BY a.giorno_inizio
        """;

        stmt = conn.prepareStatement(sql);
        stmt.setString(1, usernameColtivatore);
        stmt.setString(2, titolo_progetto);
        rs = stmt.executeQuery();

        while (rs.next()) {
            String tipo = rs.getString("tipo_attivita");
            String id = rs.getString("id_attivita");

            if (tipo != null && id != null) {
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



public String[] getDateByAttivitaId(String idAttivita, String tipoAttivita) {
    String[] date = new String[2];
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = Connessione.getConnection();
        // Query DINAMICA in base al tipo di attività
        
        //String sql = "SELECT giorno_inizio, giorno_fine FROM DateAttivitaColtivatore WHERE id_attivita = ? AND done = false";

        
         String sql = "";
        switch(tipoAttivita) {
            case "Semina":
                //sql = "SELECT data_inizio_semina, data_fine_semina FROM coltivatoreview WHERE ID_Attivita = ?";
            	sql = "SELECT giorno_inizio, giorno_fine FROM DateAttivitaColtivatore WHERE id_attivita = ? AND done=false AND tipo_attivita = 'Semina'";
                break;
            case "Irrigazione":
                //sql = "SELECT data_inizio_irrigazione, data_fine_irrigazione FROM coltivatoreview WHERE ID_Attivita = ?";
            	sql = "SELECT giorno_inizio, giorno_fine FROM DateAttivitaColtivatore WHERE id_attivita = ? AND done=false AND tipo_attivita = 'Irrigazione'";
                break;
            case "Raccolta":
                //sql = "SELECT data_inizio_raccolta, data_fine_raccolta FROM coltivatoreview WHERE ID_Attivita = ?";
            	sql = "SELECT giorno_inizio, giorno_fine FROM DateAttivitaColtivatore WHERE id_attivita = ? AND done=false AND tipo_attivita = 'Raccolta'";
                break;
            default:
                return date;
        }
        
        
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(idAttivita));
        rs = stmt.executeQuery();

        if (rs.next()) {
        	date[0] = rs.getString(1); // Prima colonna (data inizio)
            date[1] = rs.getString(2); // Seconda colonna (data fine)
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
public List<String> getIdAttivitaColtivatore(String username, String progetto) {
    List<String> idList = new ArrayList<>();
    
        
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT tipo_attivita, id_attivita " +
                "FROM DateAttivitaColtivatore " +
                "WHERE username = ? AND titolo = ? AND done = false AND stato IN ('pianificata', 'in corso')" +
                "ORDER BY giorno_inizio")) {
    
        stmt.setString(1, username);
        stmt.setString(2, progetto);
        ResultSet rs = stmt.executeQuery();

        
        while (rs.next()) {
            String tipoAttivita = rs.getString("tipo_attivita");
            int idAttivita = rs.getInt("id_attivita");
            idList.add(tipoAttivita + "-" + idAttivita);
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return idList;
}


//SECONDA FUNZIONE: Restituisce i tipi per indice
public List<String> getTipiAttivitaColtivatore(String username, String progetto) {
    List<String> tipoList = new ArrayList<>();

    String sql = "SELECT tipo_attivita " +
                 "FROM DateAttivitaColtivatore " +
                 "WHERE username = ? AND titolo = ? AND done = false " +
                 "ORDER BY " +
                 "CASE tipo_attivita " +
                 "    WHEN 'Semina' THEN 1 " +
                 "    WHEN 'Irrigazione' THEN 2 " +
                 "    WHEN 'Raccolta' THEN 3 " +
                 "END";

    try (Connection conn = Connessione.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, username);
        stmt.setString(2, progetto);
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tipoList.add(rs.getString("tipo_attivita"));
            }
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return tipoList;
}



public String getLottoEPosizione(String progetto, String username) {
    String risultato = "";
    
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT id_lotto, posizione " +
                "FROM VisualizzaLottoColtivatore " +
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

// da testare 
public String getEsperienzaColtivatore(String username) {
    String esperienza = "";        
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT DISTINCT esperienza " +
                "FROM Coltivatore " +
                "WHERE username = ?")) {
        
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            esperienza = rs.getString("esperienza");
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return esperienza;
}


public String getStimaRaccolto(String username, String progetto) {
    String stima = "";
        
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT stima_raccolto FROM stima_raccoltoColtivatore " +
                "WHERE username_coltivatore = ? AND titolo_progetto = ?")) {
    
        stmt.setString(1, username);
        stmt.setString(2, progetto);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            stima = rs.getString("stima_raccolto");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return stima;
}


public String getTipologia(String username, String progetto) {
    String tipologia = "";

        
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
            		"SELECT tipo_semina FROM tipologia_seminaColtivatore " +
                    "WHERE username_coltivatore = ? AND titolo_progetto = ?")) {
    
        stmt.setString(1, username);
        stmt.setString(2, progetto);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            tipologia = rs.getString("tipo_semina");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return tipologia;
}

public String getVarieta(String username, String progetto) {
    // Implementazione simile - dipende da quale colonna nella vista contiene la varietà
    return "";
}

public String getIrrigazione(String username, String progetto) {
    String irrigazione = "";
//    try (Connection conn = Connessione.getConnection();
//         PreparedStatement stmt = conn.prepareStatement(
//             "SELECT tipo_irrigazione FROM coltivatoreview " +
//             "WHERE username_coltivatore = ? AND titolo_progetto = ?")) {
    
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
            		"SELECT DISTINCT tipo_irrigazione FROM irrigazione_coltivatore " +
                    "WHERE username_coltivatore = ? AND titolo_progetto = ?")) {
        
        stmt.setString(1, username);
        stmt.setString(2, progetto);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            irrigazione = rs.getString("tipo_irrigazione");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return irrigazione;
}


public List<String> getColtura(String username, String progetto) {
	List<String> lista = new ArrayList<>();
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet risultato = null;
	
	try {
		  conn = Connessione.getConnection();
//	       String sql = "SELECT DISTINCT tipo_coltura " +
//	       "FROM coltivatoreview " +
//	       "WHERE username_coltivatore = ? AND titolo_progetto = ?"; 

		  String sql = "SELECT varieta_coltura " +
                  "FROM ComboTipologiaColturaColtivatore " +
                  "WHERE username_coltivatore = ? AND titolo_progetto = ?";
		 
	 
		 stmt = conn.prepareStatement(sql); 
		 stmt.setString(1, username);
		 stmt.setString(2, progetto);
		 risultato= stmt.executeQuery();
	  
	  while (risultato.next()) {
//	      String tipoColtura = risultato.getString("tipo_coltura").toLowerCase();
		  String varieta = risultato.getString("varieta_coltura");
	      //lista.add(String.valueOf(tipoColtura));
		  lista.add(String.valueOf(varieta));
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




public String getTipoSemina(String idSemina) {
    String tipoSemina = "";
    
//    try (Connection conn = Connessione.getConnection();
//         PreparedStatement stmt = conn.prepareStatement(
//             "SELECT tipo_semina FROM coltivatoreview WHERE id_semina = ?")) {
    
    try (Connection conn = Connessione.getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT tipo_semina FROM Semina WHERE id_attivita = ?")) {
        
        stmt.setInt(1, Integer.parseInt(idSemina));
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            tipoSemina = rs.getString("tipo_semina");
        }
        
    } catch (SQLException | NumberFormatException ex) {
        ex.printStackTrace();
    }
    
    return tipoSemina != null ? tipoSemina : "";
}


public boolean sommaRaccolto(String raccolto, String coltura, String progetto) {
	
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet risultato = null;
	String sql1 = null;
	String sql2 = null;
	
	try {
		
		conn = Connessione.getConnection();
		sql1 = "SELECT SUM(raccoltoProdotto) AS somma, id_raccolta, titolo_progetto, varietà FROM sommaRaccolti WHERE titolo_progetto = ? AND varietà = ? GROUP BY id_raccolta, titolo_progetto, varietà";
		stmt = conn.prepareStatement(sql1);
		stmt.setString(1, progetto);
		stmt.setString(2, coltura);
		risultato = stmt.executeQuery();
		
		int sommaRaccolti = Integer.parseInt(raccolto);

		if (risultato.next()) {
            sommaRaccolti += risultato.getInt("somma"); // SOMMA il nuovo raccolto all'esistente
            System.out.println("Nuova somma dei raccolti: " + sommaRaccolti); // Debug
        }
		stmt.close();
        risultato.close();
		
        sql2 = "UPDATE Coltura SET raccoltoProdotto = ? WHERE varietà = ?";
        stmt = conn.prepareStatement(sql2);
        stmt.setInt(1, sommaRaccolti);
        stmt.setString(2, coltura);
        
        int rows = stmt.executeUpdate();
        
        return rows > 0;
		
	}catch (SQLException ex) {
		  ex.printStackTrace();
		  return false;
	} finally {
		    try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
		    try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
		    try { if (conn != null) conn.close(); } catch (Exception ignored) {}
	}
	
	
	
}





}

//_____________________!!!   DAO:coltivatore !!!!____________________________________
