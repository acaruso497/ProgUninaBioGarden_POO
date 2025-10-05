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
	    int idProgetto = Integer.parseInt(idProgettoStr); //converte l'ID del progetto nella combo box in un intero
	    
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet risultato = null;
	    String sql = null;

	    try {
	        conn = Connessione.getConnection();
	      
	        if ("Raccolta".equals(tipoAttivita)) {
//	            sql = "SELECT r.id_attivita, r.stato, r.giorno_inizio, r.giorno_fine " +
//	                    "FROM Raccolta r " +
//	                    "JOIN Attivita a ON r.id_attivita = a.id_attivita " +
//	                    "JOIN Ospita_Lotto_Progetto o ON a.id_lotto = o.id_lotto " +
//	                    "WHERE o.id_progetto = ?";

	        	sql="SELECT r.id_attivita, r.stato, r.giorno_inizio, r.giorno_fine " +
	        			" FROM Raccolta r " +
	        		    " JOIN Attivita a ON r.id_attivita = a.id_attivita " +
	        			" JOIN Lotto l ON l.id_lotto = a.id_lotto " +
	        			" JOIN Progetto_Coltivazione pc ON pc.id_progetto=l.id_progetto " +
	        			" WHERE pc.id_progetto = ?";
	                    
	          } else if ("Irrigazione".equals(tipoAttivita)) {
//	              sql = "SELECT i.id_attivita, i.stato, i.giorno_inizio, i.giorno_fine " +
//	                    "FROM Irrigazione i " +
//	                    "JOIN Attivita a ON i.id_attivita = a.id_attivita " +
//	                    "JOIN Ospita_Lotto_Progetto o ON a.id_lotto = o.id_lotto " +
//	                    "WHERE o.id_progetto = ?";
	        	  
	        	  sql="SELECT i.id_attivita, i.stato, i.giorno_inizio, i.giorno_fine " +
		        			" FROM Irrigazione i " +
		        		    " JOIN Attivita a ON i.id_attivita = a.id_attivita " +
		        			" JOIN Lotto l ON l.id_lotto = a.id_lotto " +
		        			" JOIN Progetto_Coltivazione pc ON pc.id_progetto=l.id_progetto " +
		        			" WHERE pc.id_progetto = ?";
	        	  
	                    
	          } else if ("Semina".equals(tipoAttivita)) {
//	              sql = "SELECT s.id_attivita, s.stato, s.giorno_inizio, s.giorno_fine " +
//	                    "FROM Semina s " +
//	                    "JOIN Attivita a ON s.id_attivita = a.id_attivita " +
//	                    "JOIN Ospita_Lotto_Progetto o ON a.id_lotto = o.id_lotto " +
//	                    "WHERE o.id_progetto = ?";
	        	  sql="SELECT s.id_attivita, s.stato, s.giorno_inizio, s.giorno_fine " +
		        			" FROM Semina s " +
		        		    " JOIN Attivita a ON s.id_attivita = a.id_attivita " +
		        			" JOIN Lotto l ON l.id_lotto = a.id_lotto " +
		        			" JOIN Progetto_Coltivazione pc ON pc.id_progetto=l.id_progetto " +
		        			" WHERE pc.id_progetto = ?";
	        	  
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
	            return null; 
	        }
	        
	        
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	        return null;
	    } finally {
	        try { if (risultato != null) risultato.close(); } catch (Exception ex) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception ex) {}
	        try { if (conn != null) conn.close(); } catch (Exception ex) {}
	    }
	}
	

	// Aggiorna lo stato di ciascuna attività
	public boolean aggiornaStato(String stato, String tipoAttivita, String idLottoStr) {
		int idLotto = Integer.parseInt(idLottoStr); //converte l'ID del lotto nella combo box in un intero
		Connection conn = null;
	    PreparedStatement stmt = null;
	    String sql1 = null;
	    String sql2 = null;
	    try {
	    	conn = Connessione.getConnection();
	    	
	    	sql1 ="SELECT ID_Attivita FROM Attivita WHERE ID_Lotto = ?"; //seleziona l'id dell'attività da un lotto
	    	stmt = conn.prepareStatement(sql1);
	    	stmt.setInt(1, idLotto);
	    	
	    	sql2 = "UPDATE " + tipoAttivita + " SET stato = ? WHERE ID_Attivita = ?"; //aggiorna lo stato di quell'attività da un id attività collegato ad un lotto
	    	stmt = conn.prepareStatement(sql2);
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
//            String sql = "SELECT DISTINCT pc.ID_Progetto " + 
//                        "FROM Progetto_Coltivazione pc " +
//                        "JOIN Ospita_Lotto_Progetto olp ON pc.ID_Progetto = olp.ID_Progetto " +
//                        "JOIN Lotto l ON olp.ID_Lotto = l.ID_Lotto " +
//                        "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
//                        "WHERE p.username = ? " +
//                        "ORDER BY pc.ID_Progetto";

          String sql = "SELECT pc.ID_Progetto " + 
          "FROM Progetto_Coltivazione pc " +
          "JOIN Lotto l ON l.ID_Lotto = pc.ID_Lotto " +
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
            
            
            

        } catch (SQLException ex) {
        	ex.printStackTrace();
        } finally {
            try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return lista;
    }
    
    public boolean isCompletata(String username, String idProgetto) {
    	Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet risultato = null;
    	
    	try {
    		conn = Connessione.getConnection();
    		
    		String sql = "SELECT pc.done " +
                    "FROM Progetto_Coltivazione pc " +
                    "JOIN Lotto l ON l.ID_Lotto = pc.ID_Lotto " +
                    "JOIN Proprietario p ON l.Codice_FiscalePr = p.Codice_Fiscale " +
                    "WHERE p.username = ? AND pc.ID_Progetto = ?";
        
        stmt = conn.prepareStatement(sql);   
        stmt.setString(1, username);
        stmt.setInt(2, Integer.parseInt(idProgetto));
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
    
  //seleziono tutte le tipologie colture presenti in un lotto dato il suo progetto (utile per ComboListaColture)
    public List<String> getColtureByLotto(String idLottoStr, String idProgettoStr) {
    	int idLotto= Integer.parseInt(idLottoStr);
    	int idProgetto= Integer.parseInt(idProgettoStr);
    	
        List<String> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet risultato = null;

        try {
            conn = Connessione.getConnection(); 
            //String sql = "SELECT DISTINCT varietà FROM coltivatoreview WHERE id_progetto = ? AND id_lotto = ?";
            String sql = "SELECT varietà FROM ComboListaColture WHERE id_progetto = ? AND id_lotto = ?";
            
            stmt = conn.prepareStatement(sql);   
            stmt.setInt(1, idProgetto);
            stmt.setInt(2, idLotto);
            risultato = stmt.executeQuery();

            while (risultato.next()) {
                String coltura = risultato.getString("varietà");
                lista.add(coltura);
            }


        } catch (SQLException | NumberFormatException ex) {
        	ex.printStackTrace();
        } finally {
            try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }

        return lista;
    }
	
	
	//recupera i lotti di un proprietario (utile per popolare ComboLotti)
    //public String getLottiByProprietario(String username)
		public String getLottiByProprietario(int idProgetto, String codiceFiscaleProprietario) {
		    Connection conn = null;
		    PreparedStatement stmt = null;
		    ResultSet risultato = null;
		    String idLotto = null;

		    try {
		        conn = Connessione.getConnection(); 

		      //seleziono tutti i lotti del proprietario dato il suo username 
//		        String sql = "SELECT l.ID_Lotto " + 
//	                    "FROM Proprietario p " +
//	                    "JOIN Lotto l ON l.Codice_FiscalePr = p.Codice_Fiscale " +
//	                    "WHERE p.username = ? ";
		        
		        String sql = "SELECT l.ID_Lotto " +
		                     "FROM Lotto l " +
		                     "JOIN Progetto_Coltivazione pc ON l.ID_Progetto = pc.ID_Progetto " +
		                     "WHERE pc.ID_Progetto = ? " +
		                     "AND l.Codice_FiscalePr = ?";
		        

		        stmt = conn.prepareStatement(sql);   
		        stmt.setInt(1, idProgetto); 
		        stmt.setString(2, codiceFiscaleProprietario);
		        risultato = stmt.executeQuery();

//		        while (risultato.next()) {
//		            int idLotto = risultato.getInt("ID_Lotto"); 
//	                String idStr = String.valueOf(idLotto); 
//	                lista.add(idStr); // Aggiunge alla lista
//		        }
		        
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
	
		
	//popola la combobox del progetto, il text field di data inizio, data fine, stima raccolto e raccolto effettivo  
	public void popolaDatiProgetto(String idProgettoStr, JTextField fieldStima, JTextField fieldDataIP, JTextField fieldDataFP) {
			int idProgetto = Integer.parseInt(idProgettoStr); //converte l'ID del progetto nella combo box in un intero
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet risultato = null;
			
			try {
			conn = Connessione.getConnection(); 
			String sql = "SELECT stima_raccolto, data_inizio, data_fine FROM view_raccolto WHERE ID_Progetto = ?"; //recupera tutti i dati del progetto tramite la view
			
			stmt = conn.prepareStatement(sql);   
			stmt.setInt(1, idProgetto);
			risultato = stmt.executeQuery();
			
			if (risultato.next()) {
				// Setta stima del raccolto
	            String stima = risultato.getString("stima_raccolto");
	            if (stima != null) {
	                fieldStima.setText(stima + " kg");
	            } else {
	                fieldStima.setText("");
	            }
	            
	            // Setta raccolto effettivo
//	            String effettivo = risultato.getString("raccolto_effettivo");
//	            if (effettivo != null) {
//	                fieldEffettivo.setText(effettivo + " kg");
//	            } else {
//	                fieldEffettivo.setText("");
//	            }
			
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
	
	
	
		
	
	public void mostraRaccolto(String idProgettoStr, String idLottoStr, String coltura, JTextField FieldEffettivo) {
		int idProgetto = Integer.parseInt(idProgettoStr);
		int idLotto = Integer.parseInt(idLottoStr);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		
		try {
				conn = Connessione.getConnection(); 
				String sql="SELECT raccoltoprodotto FROM ProprietarioRaccoltoColture WHERE varietà = ? AND id_lotto = ? AND id_progetto = ?";
				stmt = conn.prepareStatement(sql);   
				stmt.setString(1, coltura);
				stmt.setInt(2, idLotto);
				stmt.setInt(3, idProgetto);
				risultato = stmt.executeQuery();
			
				if(risultato.next()) {
					int raccolto = risultato.getInt("raccoltoprodotto");
					FieldEffettivo.setText(raccolto + " kg");
		         } else {
		        	 FieldEffettivo.setText("");
		         }
			
			
		}  catch (SQLException | NumberFormatException ex) {
			ex.printStackTrace();
		} finally {
			try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
			try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
			try { if (conn != null) conn.close(); } catch (Exception ignored) {}
		}
		
		
	}
	
	
	//!!NUOVO!! Metodo per liberare un lotto da un progetto di coltivazione e tutti i suoi riferimenti
	public boolean terminaProgetto(String idProgettoStr, String idLottoStr) {
		int idProgetto = Integer.parseInt(idProgettoStr);
		int idLotto = Integer.parseInt(idLottoStr);
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet risultato = null;
		
		try {
				conn = Connessione.getConnection(); 
				int rows = 0;
				
				// segno il progetto come completato con la flag done
		        String sql1 = "UPDATE Progetto_Coltivazione SET done = true WHERE id_lotto = ? AND id_progetto= ? ";
		        stmt = conn.prepareStatement(sql1);
		        stmt.setInt(1, idLotto);
		        stmt.setInt(2, idProgetto);
		        rows = stmt.executeUpdate();
		        stmt.close();
		        
		        //segno l'attività come completata
		        String sql2 = "UPDATE Attivita SET stato = 'completata' WHERE id_lotto = ? ";
		        stmt = conn.prepareStatement(sql2);
		        stmt.setInt(1, idLotto);
		        rows = stmt.executeUpdate();
		        stmt.close();
		        
		        //ricavo l'id dell'attività in modo da collegare le 3 attività
		        String sqlAttivita = "SELECT id_attivita FROM Attivita WHERE id_lotto = ?";
		        stmt = conn.prepareStatement(sqlAttivita);
		        stmt.setInt(1, idLotto);
		        risultato = stmt.executeQuery();
		        
		        int idAttivita = 0;
		        if (risultato.next()) {
			        idAttivita = risultato.getInt("id_attivita");
			        risultato.close();
			        stmt.close();
			    }
		        
		        
		        //segno semina come completata
		        String sql3 = "UPDATE Semina SET stato = 'completata' WHERE id_attivita = ?";
		        stmt = conn.prepareStatement(sql3);
		        stmt.setInt(1, idAttivita);
		        rows = stmt.executeUpdate();
		        stmt.close();
		        
		       //segno irrigazione come completata
		        String sql4 = "UPDATE Irrigazione SET stato = 'completata' WHERE id_attivita = ?";
		        stmt = conn.prepareStatement(sql4);
		        stmt.setInt(1, idAttivita);
		        rows = stmt.executeUpdate();
		        stmt.close();
		        
		       //segno raccolta come completata
		        String sql5 = "UPDATE Raccolta SET stato = 'completata' WHERE id_attivita = ?";
		        stmt = conn.prepareStatement(sql5);
		        stmt.setInt(1, idAttivita);
		        rows = stmt.executeUpdate();
		        stmt.close();
		      
				
				System.out.println("Progetto terminato con successo!"); //DEBUG
				return rows > 0; // Ritorna true se almeno una riga è stata aggiornata
		
		}  catch (SQLException | NumberFormatException ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try { if (risultato != null) risultato.close(); } catch (Exception ignored) {}
			try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
			try { if (conn != null) conn.close(); } catch (Exception ignored) {}
		}
	
		
		
	}
	
	
}   
	

	
		

//GUI: Visualizza Progetti	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     