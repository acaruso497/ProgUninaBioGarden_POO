package dao;

import database.Connessione;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class daoCreaP {
	// controlla se esiste la coltura già su un lotto
	public static boolean checkColtura(String idLotto, String[] coltureArray) {
		String sql = "SELECT 1 FROM controllocolture " +
		              "WHERE id_lotto = ? AND varietà = ANY(?) " +
		              "LIMIT 1";
	    
	    try (Connection conn = Connessione.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        
	        stmt.setInt(1, Integer.parseInt(idLotto));
	        stmt.setArray(2, conn.createArrayOf("VARCHAR", coltureArray));
	        
	        ResultSet rs = stmt.executeQuery();
	        return rs.next(); // true se trova ALMENO una corrispondenza
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//Registrazione dei dati del progetto
	public static boolean registraProgetto(String titolo, String idLottoStr, String stimaRaccoltoStr, 
										   String[] coltureArray, String descrizione, Date dataIP, Date dataFP) {
	    int idLotto = Integer.parseInt(idLottoStr);  //converte l'ID del lotto nella combo box in un intero
	    
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        
	        /* controlla se esiste almeno 1 progetto di coltivazione all'interno 
	        di un determinato lotto e se non è stato completato */
	        
	        String sql = "SELECT EXISTS (  " +
	        		" SELECT 1 " +
	        	    " FROM Progetto_Coltivazione " +
	        	    " WHERE id_lotto = ? AND done = false" + 
	        	    " ); ";
	        
	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idLotto);
	        risultato = stmt.executeQuery();
	        
	        boolean esiste = false;
	        if(risultato.next()) {
	        	esiste = risultato.getBoolean("exists");
	        }
	        risultato.close();
	        stmt.close();
	        
	        if(esiste==true) {
	        	System.out.println("Esiste già un progetto in questo lotto!");
	        	 return false;
	        }else {
	        	
	        //inserisce tutte le informazioni dei textfield dentro progetto coltivazione
	        String sql1 = "INSERT INTO Progetto_Coltivazione (titolo, descrizione, stima_raccolto, data_inizio, data_fine, id_lotto) "
	        		      + "VALUES (?, ?, ?, ?, ?, ?) RETURNING id_progetto";
	        stmt = conn.prepareStatement(sql1);
	        stmt.setString(1, titolo);
	        stmt.setString(2, descrizione);
	        stmt.setDouble(3, Double.parseDouble(stimaRaccoltoStr));
	        stmt.setDate(4, dataIP);
	        stmt.setDate(5, dataFP);
	        stmt.setInt(6, idLotto);
	        
	        risultato = stmt.executeQuery();
	        
	        int idProgetto = 0;
	        if (risultato.next()) {
	            idProgetto = risultato.getInt("id_progetto");
	        }
	        
	        risultato.close();
	        stmt.close();
	        
	        //pulisce le colture dalle virgole
	        if (coltureArray != null && coltureArray.length > 0) {
	            for (int i = 0; i < coltureArray.length; i++) {
	                String coltura = coltureArray[i];
	                String colturaPulita = coltura.trim();
	                
	                if (!colturaPulita.isEmpty()) {
	                    // Crea o recupera coltura
	                    int colturaId = getOrCreateColtura(conn, colturaPulita);
	                    
	                    // Associa coltura al lotto
	                    associaColturaALotto(conn, idLotto, colturaId, idProgetto);
	                }
	            }
	        }
	        return true;
	      }  
	    } catch(SQLException | NumberFormatException ex) {
	        ex.printStackTrace();
	        return false;
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}

//controlla se il progetto è completato
public boolean controlloProgettoChiuso(String idLottoStr) {
	int idLotto = Integer.parseInt(idLottoStr);  
    
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet risultato = null;

    try {
    	
    	conn = Connessione.getConnection();
    	
    	String sql = "SELECT done FROM Progetto_Coltivazione WHERE id_lotto = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idLotto);
        risultato = stmt.executeQuery();
        
        boolean completato = false;
        if(risultato.next()) {
            completato = risultato.getBoolean("done");
        } else { // Se non trova progetti, il lotto è libero
            return true;
        }
        
        risultato.close();
        stmt.close();
        
        if(completato==false) {
            return false;
        }
    	return true;
    } catch(SQLException | NumberFormatException ex) {
        ex.printStackTrace();
        return false;
    } finally {
        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
        try { if (conn != null) conn.close(); } catch (Exception e) {}
    }
    
	}
	
	
	// ==HELPER==
	// crea la coltura dalla varietà
	private static int getOrCreateColtura(Connection conn, String nomeColtura) throws SQLException {
	    String selectSql = "SELECT id_coltura FROM Coltura WHERE varietà = ?";
	    PreparedStatement stmt = conn.prepareStatement(selectSql);
	    stmt.setString(1, nomeColtura);
	    ResultSet rs = stmt.executeQuery();
	    
	    if (rs.next()) {
	        int id = rs.getInt("id_coltura");
	        rs.close();
	        stmt.close();
	        return id;
	    }
	    rs.close();
	    stmt.close();
	    
	    
	    String insertSql = "INSERT INTO Coltura (varietà) VALUES (?) RETURNING id_coltura";
	    stmt = conn.prepareStatement(insertSql);
	    stmt.setString(1, nomeColtura);
	    rs = stmt.executeQuery();
	    
	    if (rs.next()) {
	        int id = rs.getInt("id_coltura");
	        rs.close();
	        stmt.close();
	        return id;
	    }
	    
	    throw new SQLException("Impossibile creare coltura: " + nomeColtura);
	}
	
	//==HELPER==
	// associa la coltura al lotto
	private static void associaColturaALotto(Connection conn, int lottoId, int colturaId, int progettoId) throws SQLException {
		
		String checkSql = "SELECT 1 FROM Progetto_Coltura WHERE id_progetto = ? AND id_coltura = ?";
	    PreparedStatement checkStmt = conn.prepareStatement(checkSql);
	    checkStmt.setInt(1, progettoId);
	    checkStmt.setInt(2, colturaId);
	    ResultSet rs = checkStmt.executeQuery();
	    
	    if (!rs.next()) {  // Inserisci solo se non esiste
	        String sql = "INSERT INTO Progetto_Coltura (id_progetto, id_coltura) VALUES (?, ?)";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, progettoId);
	        stmt.setInt(2, colturaId);
	        stmt.executeUpdate();
	        stmt.close();
	    }
	    
	    rs.close();
	    checkStmt.close();
		
	}
	
	//registra l'attività verso i coltivatori che lavorano nel lotto
	public static boolean registraAttivita(String tipoAttivita, Date dataIA, Date dataFA, 
		            					   String tipoIrrigazione, String tipoSemina, String idLottoStr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		boolean success = true;
		
		try {
			conn = Connessione.getConnection();
			int idLotto = Integer.parseInt(idLottoStr);
		
			// recupera tutti i coltivatori che lavorano in quel lotto
			List<String> coltivatori = getColtivatoriLotto(idLotto);
			if (coltivatori.isEmpty()) {
				return false;
			}
		
			// per ogni coltivatore, viene assegnata un'attività
			for (String coltivatore : coltivatori) {
				String sqlAttivita = "INSERT INTO Attivita (ID_Lotto, Codice_FiscaleCol, giorno_assegnazione, stato) VALUES (?, ?, CURRENT_DATE, 'pianificata') RETURNING ID_Attivita";
				stmt = conn.prepareStatement(sqlAttivita);
				stmt.setInt(1, idLotto);
				stmt.setString(2, coltivatore);
				risultato = stmt.executeQuery();
		
				int idAttivita = 0;
				if (risultato.next()) {
				idAttivita = risultato.getInt("ID_Attivita");
				}
				risultato.close();
				stmt.close();
		
				
				String sql = null;
				
				// inserisce la specifica attività ai coltivatori
				if ("Raccolta".equals(tipoAttivita)) {
					sql = "INSERT INTO Raccolta (giorno_inizio, giorno_fine, raccolto_effettivo, id_attivita, stato) VALUES (?, ?, 0, ?, 'pianificata')";
					stmt = conn.prepareStatement(sql);
					stmt.setDate(1, dataIA);
					stmt.setDate(2, dataFA);
					stmt.setInt(3, idAttivita);
				}
				
				else if ("Semina".equals(tipoAttivita)) {
					sql = "INSERT INTO Semina (giorno_inizio, giorno_fine, tipo_semina, profondita, id_attivita, stato) VALUES (?, ?, ?, 10, ?, 'pianificata')";
					stmt = conn.prepareStatement(sql);
					stmt.setDate(1, dataIA);
					stmt.setDate(2, dataFA);
					stmt.setString(3, tipoSemina);
					stmt.setInt(4, idAttivita);
				}
				
				else if ("Irrigazione".equals(tipoAttivita)) {
					sql = "INSERT INTO Irrigazione (giorno_inizio, giorno_fine, tipo_irrigazione, id_attivita, stato) VALUES (?, ?, ?, ?, 'pianificata')";
					stmt = conn.prepareStatement(sql);
					stmt.setDate(1, dataIA);
					stmt.setDate(2, dataFA);
					stmt.setString(3, tipoIrrigazione);
					stmt.setInt(4, idAttivita);
				}
				
				int rowsAffected = stmt.executeUpdate();
				if (rowsAffected <= 0) {
					success = false;
				}
					stmt.close();
				}
		
		return success;
		
		} catch(SQLException | NumberFormatException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try { if (risultato != null) risultato.close(); } catch (Exception e) {}
			try { if (stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (conn != null) conn.close(); } catch (Exception e) {}
		}
	}
	
	
//==HELPER==
//Recupera il coltivatore dal lotto
private static List<String> getColtivatoriLotto(int idLotto) {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	List<String> coltivatori = new ArrayList<>();
	
	try {
		conn = Connessione.getConnection();
		String sql = "SELECT DISTINCT a.Codice_FiscaleCol FROM Attivita a WHERE a.ID_Lotto = ? ";;

		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, idLotto);
		rs = stmt.executeQuery();
	
	while (rs.next()) {
		coltivatori.add(rs.getString("Codice_FiscaleCol"));
	}
		return coltivatori; 
	
	} catch (SQLException ex) {
		ex.printStackTrace();
		return null;
	} finally {
		try { if (rs != null) rs.close(); } catch (Exception e) {}
		try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		try { if (conn != null) conn.close(); } catch (Exception e) {}
	}
}		
	
}
