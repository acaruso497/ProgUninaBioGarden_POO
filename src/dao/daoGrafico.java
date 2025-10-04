package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connessione;

public class daoGrafico {
// GUI: Grafico
	
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
	
    // COUNT(*)
    public long getNumeroRaccolte(int idLotto, String varieta) {
        Number n = queryAggregato("COUNT(*)", idLotto, varieta);
        return n != null ? n.longValue() : 0L;
    }

    // AVG
    public double getMediaRaccolto(int idLotto, String varieta) {
        Number n = queryAggregato("AVG(r.raccolto_effettivo)", idLotto, varieta);
        return n != null ? n.doubleValue() : 0.0;
    }

    // MIN
    public double getMinRaccolto(int idLotto, String varieta) {
        Number n = queryAggregato("MIN(r.raccolto_effettivo)", idLotto, varieta);
        return n != null ? n.doubleValue() : 0.0;
    }

    // MAX
    public double getMaxRaccolto(int idLotto, String varieta) {
        Number n = queryAggregato("MAX(r.raccolto_effettivo)", idLotto, varieta);
        return n != null ? n.doubleValue() : 0.0;
    }

    // ===== Helper per evitare duplicazioni =====
    private Number queryAggregato(String expr, int idLotto, String varieta) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Connessione.getConnection();

            
            String sql =
                "SELECT " + expr + " AS val " +
                "FROM raccolta r " +
                "JOIN attivita a   ON a.id_attivita = r.id_attivita " +
                "JOIN contiene ct  ON ct.id_lotto   = a.id_lotto " +
                "JOIN coltura  c   ON c.id_coltura  = ct.id_coltura " +
                "WHERE a.id_lotto = ? " +
                "  AND lower(trim(c.\"varietà\")) = lower(trim(?)) " +
                "  AND r.stato IN ('completata')"; 

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idLotto);
            stmt.setString(2, varieta);

            rs = stmt.executeQuery();
            if (rs.next()) {
                
                Object o = rs.getObject("val");
                if (o == null) return 0; // normalizziamo a 0
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
	
}
//GUI: Grafico