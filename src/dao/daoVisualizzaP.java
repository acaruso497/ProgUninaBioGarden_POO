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
	//GUI: Visualizza Progetti
	
	//popola il text field di giorno inizio, giorno fine e il radio button dello stato dell'attività
	public String popolaAttivita(String idProgettoStr, String tipoAttivita, JTextField fieldDataIA, JTextField fieldDataFA) {
	    int idProgetto = Integer.parseInt(idProgettoStr);
	    
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    String sql = null;

	    try {
	        conn = Connessione.getConnection();
	        
	        
	        if ("Raccolta".equals(tipoAttivita)) { //in base al tipo di attività selezionata nella combobox, mi estrae lo stato, giorno inizio e giorno fine
	            sql = "SELECT r.id_attivita, a.stato, r.giorno_inizio, r.giorno_fine " +
	                  "FROM Raccolta AS r NATURAL JOIN Attivita AS a NATURAL JOIN Ospita_Lotto_Progetto AS o " +
	                  "WHERE o.id_progetto = ?";
	        } else if ("Irrigazione".equals(tipoAttivita)) {
	            sql = "SELECT i.id_attivita, a.stato, i.giorno_inizio, i.giorno_fine " +
	                  "FROM Irrigazione AS i NATURAL JOIN Attivita AS a NATURAL JOIN Ospita_Lotto_Progetto AS o " +
	                  "WHERE o.id_progetto = ?";
	        } else if ("Semina".equals(tipoAttivita)) {
	            sql = "SELECT s.id_attivita, a.stato, s.giorno_inizio, s.giorno_fine " +
	                  "FROM Semina AS s NATURAL JOIN Attivita AS a NATURAL JOIN Ospita_Lotto_Progetto AS o " +
	                  "WHERE o.id_progetto = ?";
	        } 

	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idProgetto);  
	        risultato = stmt.executeQuery();

	     
	        if (risultato.next()) {
	        	//formatta le date del text field in date sql
	        	java.sql.Date sqlDataInizio = risultato.getDate("giorno_inizio");
			    if (sqlDataInizio != null) {
			    LocalDate dataInizio = sqlDataInizio.toLocalDate();
			    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			    fieldDataIA.setText(dataInizio.format(formatter));
			    } else {
			     fieldDataIA.setText("");
			    } 
	        	
			    java.sql.Date sqlDataFine = risultato.getDate("giorno_fine");
			     if (sqlDataFine != null) {
			     LocalDate dataFine = sqlDataFine.toLocalDate();
			     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			     fieldDataFA.setText(dataFine.format(formatter));
			     } else {
			     	fieldDataFA.setText("");
			     } 
	        	
	        	return risultato.getString("stato"); // Restituisce lo stato dell'attività
	        } 
	        else {
	            System.err.println("Nessun stato trovato per attività " + tipoAttivita + " nel progetto " + idProgetto);
	            return null; 
	        }
	        
	        
	    } catch (SQLException e) {
	        System.err.println("Errore SELECT stato attività: " + e.getMessage());
	        return null;
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception ex) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception ex) {}
	        try { if (conn != null) conn.close(); } catch (Exception ex) {}
	    }
	}
	
	//aggiorna lo stato nella tabella attività
	public boolean aggiornaStato(String stato, String tipoAttivita, String idLottoStr) {
		int idLotto = Integer.parseInt(idLottoStr);
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    String sql = null;

	    try {
	        conn = Connessione.getConnection();
	        
	        sql = "UPDATE Attivita SET stato = ?, giorno_assegnazione = CURRENT_DATE WHERE ID_Lotto = ?"; 
	        
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, stato); 
	        stmt.setInt(2, idLotto);

	        return stmt.executeUpdate() > 0; // Ritorna true se almeno una riga è stata aggiornata
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return false; 
	    } finally {
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
            String sql = "SELECT DISTINCT pc.ID_Progetto " + 
                        "FROM Progetto_Coltivazione pc " +
                        "JOIN Ospita_Lotto_Progetto olp ON pc.ID_Progetto = olp.ID_Progetto " +
                        "JOIN Lotto l ON olp.ID_Lotto = l.ID_Lotto " +
                        "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
                        "WHERE p.username = ? " +
                        "ORDER BY pc.ID_Progetto";

            stmt = conn.prepareStatement(sql);   
            stmt.setString(1, username);
            risultato = stmt.executeQuery();

            while (risultato.next()) {
                int idProgetto = risultato.getInt("ID_Progetto");
                lista.add(String.valueOf(idProgetto));
            }

            // Debug: Stampa username e numero di progetti trovati
            System.out.println("Username: " + username + ", Progetti trovati: " + lista.size());

        } catch (SQLException e) {
            System.err.println("Errore SELECT progetti: " + e.getMessage());
        } finally {
            try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return lista;
    }
	
	
	//recupera i lotti di un proprietario (utile per popolare ComboLotti)
		public List<String> getLottiByProprietario(String username) {
		    List<String> lista = new ArrayList<>(); // Lista vuota per ID lotti
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet risultato = null;

		    try {
		        conn = Connessione.getConnection(); 

		        String sql = "SELECT l.ID_Lotto, l.posizione " + //seleziono tutti i lotti del proprietario dato il suo username
	                    "FROM Lotto l " +
	                    "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
	                    "WHERE p.username = ? " +
	                    "ORDER BY l.posizione"; 

		        stmt = conn.prepareStatement(sql);   
		        stmt.setString(1, username);         
		        risultato = stmt.executeQuery();

		        while (risultato.next()) {
		            int idLotto = risultato.getInt("ID_Lotto"); 
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
	
		
	//popola la combobox del progetto, il text field di data inizio, data fine, stima raccolto e raccolto effettivo  
	public void popolaDatiProgetto(String idProgettoStr, JTextField fieldStima, JTextField fieldEffettivo, 
			                JTextField fieldDataIP, JTextField fieldDataFP) {
			// Estrai ID numerico
			int idProgetto = Integer.parseInt(idProgettoStr);
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet risultato = null;
			
			try {
			conn = Connessione.getConnection(); 
			String sql = "SELECT * FROM view_raccolto WHERE ID_Progetto = ?"; //recupera tutti i dati del progetto tramite la view
			
			stmt = conn.prepareStatement(sql);   
			stmt.setInt(1, idProgetto);
			risultato = stmt.executeQuery();
			
			if (risultato.next()) {
			// Setta stima del raccolto
			fieldStima.setText(risultato.getString("stima_raccolto") != null ? 
			               risultato.getString("stima_raccolto") + " kg" : "");
			fieldEffettivo.setText(risultato.getString("raccolto_effettivo"));
			
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
			
			
			} catch (SQLException | NumberFormatException e) {
			System.err.println("Errore popola dati progetto: " + e.getMessage());
			} finally {
			// Chiudi risorse
			try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
			try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
			try { if (conn != null) conn.close(); } catch (Exception ignored) {}
			}
	}
}   
	
		

//GUI: Visualizza Progetti	                    

