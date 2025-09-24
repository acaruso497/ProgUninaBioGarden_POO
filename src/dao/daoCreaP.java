package dao;

import database.Connessione;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTextField;

import java.util.ArrayList;

public class daoCreaP {
//GUI: Crea Progetto
	
	//Registrazione dei dati del progetto
	public static boolean registraProgetto(String titolo, String idLottoStr, String descrizione, Date dataIP, Date dataFP) {
	    int idLotto = Integer.parseInt(idLottoStr);
	    
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        
	        String sql1 = "INSERT INTO Progetto_Coltivazione (titolo, descrizione, data_inizio, data_fine) VALUES (?, ?, ?, ?) RETURNING id_progetto";
	        stmt = conn.prepareStatement(sql1);
	        stmt.setString(1, titolo);
	        stmt.setString(2, descrizione);
	        stmt.setDate(3, dataIP);
	        stmt.setDate(4, dataFP);
	        risultato = stmt.executeQuery();
	        
	        int idProgetto = 0;
	        if (risultato.next()) {
	            idProgetto = risultato.getInt("id_progetto");
	        }
	        
	        risultato.close();
	        stmt.close();
	        
	        String sql2 = "INSERT INTO Ospita_Lotto_Progetto (id_progetto, id_lotto) VALUES (?, ?)";
	        stmt = conn.prepareStatement(sql2);
	        stmt.setInt(1, idProgetto);
	        stmt.setInt(2, idLotto);
	        int rows = stmt.executeUpdate();
	        
	        if (rows == 1) {
	            return true;
	        } else {
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
	
	//Registrazione dei dati dell'attività
	public static boolean registraAttivita(String tipoAttivita, Date dataIA, Date dataFA, String tipoIrrigazione, String tipoSemina) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    String sql = null;
	    
	    try {
	        conn = Connessione.getConnection();
	        
	        
	        if ("Raccolta".equals(tipoAttivita)) {
	        	sql = "INSERT INTO Raccolta (giorno_inizio, giorno_fine, raccolto_effettivo, id_attivita) VALUES (?,?,0, (SELECT MAX(ID_Attivita) FROM Attivita))";
	        	stmt = conn.prepareStatement(sql);
		        stmt.setDate(1, dataIA);
		        stmt.setDate(2, dataFA);
	        }
	        
	        if ("Semina".equals(tipoAttivita)) {
	        	sql = "INSERT INTO Semina (giorno_inizio, giorno_fine, tipo_semina, profondita, id_attivita) VALUES (?,?,?,10, (SELECT MAX(ID_Attivita) FROM Attivita))";
	        	stmt = conn.prepareStatement(sql);
		        stmt.setDate(1, dataIA);
		        stmt.setDate(2, dataFA);
		        stmt.setString(3, tipoSemina);
	        }
	        
	        if ("Irrigazione".equals(tipoAttivita)) {
	        	sql = "INSERT INTO Irrigazione (giorno_inizio, giorno_fine, tipo_irrigazione, id_attivita) VALUES (?,?,?, (SELECT MAX(ID_Attivita) FROM Attivita))";
	        	stmt = conn.prepareStatement(sql);
	        	stmt.setDate(1, dataIA);
		        stmt.setDate(2, dataFA);
		        stmt.setString(3, tipoIrrigazione);
	        }
	        
	        
	        return stmt.executeUpdate() > 0; // Ritorna true se almeno una riga è stata aggiornata
	        
	    } catch(SQLException ex) {
	        ex.printStackTrace();
	        return false;
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception e) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}
	
	//Popola i textfield relativi alla coltura
		public void popolaColtura(JTextField FieldTipologia, JTextField FieldVarieta, String idLottoStr) {
				int idLotto = Integer.parseInt(idLottoStr);
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet risultato = null;
				
				try {
				conn = Connessione.getConnection(); 
				String sql = "SELECT * FROM view_coltura WHERE ID_Lotto = ?"; //recupera tutti i dati della coltura tramite la view
				
				stmt = conn.prepareStatement(sql);   
				stmt.setInt(1, idLotto);
				risultato = stmt.executeQuery();
				
					if (risultato.next()) {
						FieldTipologia.setText(risultato.getString("tipo")); //setta il textfield tipologia
						FieldVarieta.setText(risultato.getString("varietà")); //setta il textfield varietà
					}
					
				} catch (SQLException | NumberFormatException ex) {
					ex.printStackTrace();
				} finally {
					try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
					try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
					try { if (conn != null) conn.close(); } catch (Exception ignored) {}
				}
		}
		
		// NB: DA TESTARE: Aggiorna tipo di semina e irrigazione inseriti nei textfield 
		public boolean aggiornaTipo(String tipoIrrigazione, String tipoSemina) {
			Connection conn = null;
		    PreparedStatement stmt = null;
		    String sql1 = null;
		    String sql2 = null;
		    try {
		    	conn = Connessione.getConnection();

		    	sql1 = "UPDATE Semina SET tipo_semina = ? WHERE ID_Attivita = (SELECT MAX(ID_Attivita) FROM Attivita)"; //aggiorna lo stato di quell'attività da un id attività collegato ad un lotto
		    	stmt = conn.prepareStatement(sql1);
		        stmt.setString(1, tipoSemina);
		        stmt.executeUpdate();
		        
		        sql2 = "UPDATE Irrigazione SET tipo_irrigazione = ? WHERE ID_Attivita = (SELECT MAX(ID_Attivita) FROM Attivita)"; //aggiorna lo stato di quell'attività da un id attività collegato ad un lotto
		    	stmt = conn.prepareStatement(sql2);
		        stmt.setString(1, tipoIrrigazione);
		        stmt.executeUpdate();
		        

		        return stmt.executeUpdate() > 0; // Ritorna true se almeno una riga è stata aggiornata
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		        return false; 
		    } finally {
		        try { if (stmt != null) stmt.close(); } catch (Exception e) {}
		        try { if (conn != null) conn.close(); } catch (Exception e) {}
		    }
		}
	 
	
}
//GUI: Crea Progetto
