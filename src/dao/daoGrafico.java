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

			        String sql = "SELECT varietà FROM view_coltura WHERE id_lotto = ?";  //seleziona le informazioni della coltura dalla view

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
	
}
//GUI: Grafico