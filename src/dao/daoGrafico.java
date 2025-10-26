package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connessione;

public class daoGrafico {
	
	//recupera la varietà della coltura (utile per popolare ComboColtura)
	public List<String> getColturaByLotto(String idLottoStr) {
		int idLotto = Integer.parseInt(idLottoStr); //converte l'ID del lotto nella combo box in un intero
			    List<String> lista = new ArrayList<>(); 
			    Connection conn = null;
			    PreparedStatement stmt = null;
			    ResultSet risultato = null;

			    try {
			        conn = Connessione.getConnection(); 

			        String sql = "SELECT varietà FROM ProprietarioRaccoltoColture WHERE id_lotto = ?";  //seleziona le informazioni della coltura dalla view
			    
			        stmt = conn.prepareStatement(sql);   
			        stmt.setInt(1, idLotto);         
			        risultato = stmt.executeQuery();

			        while (risultato.next()) {
			            String varietà = risultato.getString("varietà"); 
		                lista.add(varietà); // Aggiunge la varietà alla lista
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
	
	// === Letture dagli aggregati già salvati in Coltura ===

	public long getNumeroRaccolte(int idLotto, String varieta) {
	    Number n = queryColturaVal("counter", varieta);
	    return n != null ? n.longValue() : 0L;
	}

	public double getMediaRaccolto(int idLotto, String varieta) {
	    Number n = queryColturaVal("avg", varieta);
	    return n != null ? n.doubleValue() : 0.0;
	}

	public double getMinRaccolto(int idLotto, String varieta) {
	    Number n = queryColturaVal("min", varieta);
	    return n != null ? n.doubleValue() : 0.0;
	}

	public double getMaxRaccolto(int idLotto, String varieta) {
	    Number n = queryColturaVal("max", varieta);
	    return n != null ? n.doubleValue() : 0.0;
	}

	// ===== Helper unico per leggere una colonna dalla tabella Coltura =====
	private Number queryColturaVal(String column, String varieta) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        conn = Connessione.getConnection();

	        String sql =
	            "SELECT " + column + " AS val " +
	            "FROM Coltura " +
	            "WHERE lower(trim(\"varietà\")) = lower(trim(?)) " +
	            "LIMIT 1";

	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, varieta);

	        rs = stmt.executeQuery();
	        if (rs.next()) {
	            Object o = rs.getObject("val");
	            if (o == null) return 0;
	            if (o instanceof Number) return (Number) o;
	            return Double.valueOf(o.toString());
	        }
	        return 0;
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        return 0;
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception ignore) {}
	        try { if (stmt != null) stmt.close(); } catch (Exception ignore) {}
	        try { if (conn != null) conn.close(); } catch (Exception ignore) {}
	    }
	}
	
	public List<String> getLotti(String username) {
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

	
}