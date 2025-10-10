package dao;

import database.Connessione;
import java.sql.*;
import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


import java.util.ArrayList;

public class daoCreaP {
//GUI: Crea Progetto
	
	//Registrazione dei dati del progetto
	
	// controlla se esiste la coltura gia su un lotto !!AGGIUNTO!!
	public static boolean checkColtura(String idLotto, String[] coltureArray) {
//	    String sql = "SELECT 1 FROM coltivatoreview " +
//	                 "WHERE id_lotto = ? AND varietà = ANY(?) " +
//	                 "LIMIT 1";
		
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
	
	
	
	public static boolean registraProgetto(String titolo, String idLottoStr, String stimaRaccoltoStr, 
										   String[] coltureArray, String descrizione, Date dataIP, Date dataFP) {
	    int idLotto = Integer.parseInt(idLottoStr);  //converte l'ID del lotto nella combo box in un intero//converte la stringa della stima del raccolto in un double
	    
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        
	        //controlla se esiste almeno 1 progetto di coltivazione all'interno di un determinato lotto e se non è stato completato
	        
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
	        
	        
	        
	      //inserisce l'id lotto e l'id del progetto nella tabella ponte
//	        String sql2 = "INSERT INTO Ospita_Lotto_Progetto (id_progetto, id_lotto) VALUES (?, ?)";
//	        
//	        stmt = conn.prepareStatement(sql2);
//	        stmt.setInt(1, idProgetto);
//	        stmt.setInt(2, idLotto);
//	        int rows = stmt.executeUpdate();
//	        
//	        stmt.close();
	        
	        
	        
	     // 2. CREA LE N COLTURE (NUOVO)
//	        if (coltureArray != null && coltureArray.length > 0) {
//	            for (String coltura : coltureArray) {
//	                String colturaPulita = coltura.trim();
//	                
//	                if (!colturaPulita.isEmpty()) {
//	                    // Crea o recupera coltura
//	                    int colturaId = getOrCreateColtura(conn, colturaPulita);
//	                    
//	                    // Associa coltura al lotto
//	                    associaColturaALotto(conn, idLotto, colturaId, idProgetto);
//	                }
//	            }
//	        }
	        //prova
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

//!!NUOVO!! controlla se il progetto è completato
public boolean controlloProgettoChiuso(String idLottoStr) {
	int idLotto = Integer.parseInt(idLottoStr);  
    
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet risultato = null;

    try {
    	
    	conn = Connessione.getConnection();
    	
    	String sql = "SELECT done FROM Progetto_Coltivazione WHERE id_lotto = ?"; //controlla se il progetto è completato tramite done
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idLotto);
        risultato = stmt.executeQuery();
        
        boolean completato = false;
        if(risultato.next()) {
        	completato = risultato.getBoolean("done");
        }
        risultato.close();
        stmt.close();
        
        if(completato==false) {
        	System.out.println("Devi terminare il progetto"); //DEBUG
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
	
	
	
	
	// Aggiungi questi metodi helper nello stesso DAO
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
	//!!MODIFICATO!!
	private static void associaColturaALotto(Connection conn, int lottoId, int colturaId, int progettoId) throws SQLException {
//	    String sql1 = "INSERT INTO Contiene (id_lotto, id_coltura) VALUES (?, ?)";
//	    PreparedStatement stmt = conn.prepareStatement(sql1);
//	    stmt.setInt(1, lottoId);
//	    stmt.setInt(2, colturaId);
//	    stmt.executeUpdate();
//	    stmt.close();
	    
//	    String sql2 = "SELECT id_progetto FROM Ospita_Lotto_Progetto WHERE id_lotto = ?";
//	    stmt = conn.prepareStatement(sql2);
//	    stmt.setInt(1, lottoId);
//	    ResultSet rs = stmt.executeQuery();
//	   
//	    int id = 0;
//	    if (rs.next()) {
//	        id = rs.getInt("id_progetto");
//	    }
//	    rs.close();
//	    stmt.close();
	    
//	    String sql3 = "INSERT INTO Progetto_Coltura (id_progetto, id_coltura) VALUES (?, ?)";
//	    PreparedStatement stmt = conn.prepareStatement(sql3);
//	    stmt.setInt(1, progettoId);
//	    stmt.setInt(2, colturaId);
//	    stmt.executeUpdate();
//	    
//	    stmt.close();
		
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
	
	
public static boolean registraAttivita(String tipoAttivita, Date dataIA, Date dataFA, 
		            					String tipoIrrigazione, String tipoSemina, String idLottoStr) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		boolean success = true;
		
		try {
		conn = Connessione.getConnection();
		int idLotto = Integer.parseInt(idLottoStr);
		
		// FASE 1: RECUPERA TUTTI I COLTIVATORI DEL LOTTO
		List<String> coltivatori = getColtivatoriLotto(idLotto);
		if (coltivatori.isEmpty()) {
		throw new SQLException("Nessun coltivatore assegnato al lotto " + idLotto);
		}
		
		// FASE 2: PER OGNI COLTIVATORE, CREA UN'ATTIVITÀ COMPLETA
		for (String coltivatore : coltivatori) {
		// INSERISCI ATTIVITA BASE
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
		
		// INSERISCI ATTIVITA SPECIFICA PER OGNI COLTIVATORE
		String sql = null;
		
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

	

//METODO HELPER PER RECUPERARE COLTIVATORE
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
		//return rs.getString("Codice_FiscaleCol");
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
//GUI: Crea Progetto
