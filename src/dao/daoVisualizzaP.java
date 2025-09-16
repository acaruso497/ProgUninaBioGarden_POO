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
	
	public List<String> getAttivitaByProgetto(String idProgettoStr) {
        List<String> lista = new ArrayList<>();
        // Estrai ID numerico
        int idProgetto = Integer.parseInt(idProgettoStr);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet risultato = null;

        try {
            conn = Connessione.getConnection(); 
            // Query: UNION per raccogliere ID attività da Semina, Irrigazione, Raccolta
            String sql = "SELECT 'Irrigazione' as tipo FROM Irrigazione i " +
                    "JOIN Attivita a ON i.ID_Attivita = a.ID_Attivita " +
                    "JOIN Lotto l ON a.ID_Lotto = l.ID_Lotto " +
                    "JOIN Ospita_Lotto_Progetto olp ON l.ID_Lotto = olp.ID_Lotto " +
                    "WHERE olp.ID_Progetto = ? " +
                    "UNION " +
                    "SELECT 'Semina' as tipo FROM Semina s " +
                    "JOIN Attivita a ON s.ID_Attivita = a.ID_Attivita " +
                    "JOIN Lotto l ON a.ID_Lotto = l.ID_Lotto " +
                    "JOIN Ospita_Lotto_Progetto olp ON l.ID_Lotto = olp.ID_Lotto " +
                    "WHERE olp.ID_Progetto = ? " +
                    "UNION " +
                    "SELECT 'Raccolta' as tipo FROM Raccolta r " +
                    "JOIN Attivita a ON r.ID_Attivita = a.ID_Attivita " +
                    "JOIN Lotto l ON a.ID_Lotto = l.ID_Lotto " +
                    "JOIN Ospita_Lotto_Progetto olp ON l.ID_Lotto = olp.ID_Lotto " +
                    "WHERE olp.ID_Progetto = ? " +
                    "ORDER BY tipo";

            stmt = conn.prepareStatement(sql);   
            stmt.setInt(1, idProgetto);
            stmt.setInt(2, idProgetto);
            stmt.setInt(3, idProgetto);
            risultato = stmt.executeQuery();

            // Aggiungi ogni ID_Attivita come stringa
            while (risultato.next()) {
                String tipo = risultato.getString("tipo");
                lista.add(tipo);
            }

        } catch (SQLException | NumberFormatException e) {
            System.err.println("Errore SELECT attività: " + e.getMessage());
        } finally {
            // Chiudi risorse
            try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return lista;
    }
	
	
	public List<String> getProgettiByProprietario(String username) {
        List<String> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet risultato = null;

        try {
            conn = Connessione.getConnection(); 
            // Query: Collega progetti a lotti del proprietario tramite tabelle ponte
            String sql = "SELECT pc.ID_Progetto " +
                        "FROM Progetto_Coltivazione pc " +
                        "JOIN Ospita_Lotto_Progetto olp ON pc.ID_Progetto = olp.ID_Progetto " +
                        "JOIN Lotto l ON olp.ID_Lotto = l.ID_Lotto " +
                        "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
                        "WHERE p.username = ? " +
                        "ORDER BY pc.ID_Progetto"; // Ordinato per ID numerico

            stmt = conn.prepareStatement(sql);   
            stmt.setString(1, username);
            risultato = stmt.executeQuery();

            // Aggiungi solo ID_Progetto come stringa
            while (risultato.next()) {
                int idProgetto = risultato.getInt("ID_Progetto");
                lista.add(String.valueOf(idProgetto));
            }

        } catch (SQLException e) {
            System.err.println("Errore SELECT progetti: " + e.getMessage());
        } finally {
            // Chiudi risorse
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
	
		
	    
	 // Nuovo: Coltivatori per progetto (tramite Attivita e lotti del progetto)
	    public List<String> getColtivatoriByProgetto(String idProgettoStr) {
	        List<String> lista = new ArrayList<>();
	        int idProgetto = Integer.parseInt(idProgettoStr.split(":")[0].trim());  // Estrai ID da "1: Titolo"
	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet risultato = null;

	        try {
	            conn = Connessione.getConnection(); 
	            String sql = "SELECT c.username " +
	                        "FROM Coltivatore c " +
	                        "JOIN Attivita a ON c.Codice_Fiscale = a.Codice_FiscaleCol " +
	                        "JOIN Lotto l ON a.ID_Lotto = l.ID_Lotto " +
	                        "JOIN Ospita_Lotto_Progetto olp ON l.ID_Lotto = olp.ID_Lotto " +
	                        "WHERE olp.ID_Progetto = ? ";

	            stmt = conn.prepareStatement(sql);   
	            stmt.setInt(1, idProgetto);
	            risultato = stmt.executeQuery();

	            while (risultato.next()) {
	                String username = risultato.getString("username");
	                lista.add(username);
	            }

	        } catch (SQLException | NumberFormatException e) {
	            System.err.println("Errore SELECT coltivatori: " + e.getMessage());
	        } finally {
	            try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
	            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
	            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
	        }

	        return lista;
	    }
	    
	public void popolaDatiProgetto(String idProgettoStr, JTextField fieldStima, JTextField fieldEffettivo, 
			                JTextField fieldDataIP, JTextField fieldDataFP) {
			// Estrai ID numerico
			int idProgetto = Integer.parseInt(idProgettoStr);
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet risultato = null;
			
			try {
			conn = Connessione.getConnection(); 
			// Query: Recupera dati progetto
			String sql = "SELECT stima_raccolto, 0 as effettivo, data_inizio, data_fine " +
			     "FROM Progetto_Coltivazione WHERE ID_Progetto = ?";
			
			stmt = conn.prepareStatement(sql);   
			stmt.setInt(1, idProgetto);
			risultato = stmt.executeQuery();
			
			if (risultato.next()) {
			// Setta stima (aggiungi "kg" se non null)
			fieldStima.setText(risultato.getString("stima_raccolto") != null ? 
			               risultato.getString("stima_raccolto") + " kg" : "");
			// Setta raccolto effettivo (default 0; modifica se fetch da Raccolta)
			fieldEffettivo.setText(risultato.getString("effettivo"));
			
			// Converti date SQL in stringa semplice
			java.sql.Date sqlDataInizio = risultato.getDate("data_inizio");
			java.sql.Date sqlDataFine = risultato.getDate("data_fine");
			if (sqlDataInizio != null) {
			 LocalDate dataInizio = sqlDataInizio.toLocalDate();
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			 fieldDataIP.setText(dataInizio.format(formatter));
			} else {
			 fieldDataIP.setText("");
			}
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

	                    

