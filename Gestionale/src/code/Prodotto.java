package code;

import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;


import utili.ConnessioneDB;

enum UM{
	pz,
	kg,
	mt,
	lt
}

public class Prodotto {
    private int idProdotto;
    private String descrizione;
    private String categoria;
    private String sottocategoria;
    private int quantita;
    private UM unitamisura;
    private String fornitore; //?
    private double listino1;
    private double listino2;
    private double listino3;
    private double aliquotaiva;
    
    

    // Costruttore
    public Prodotto(int idProdotto, String descrizione, String categoria, String sottocategoria, int quantita, UM unitamisura, String fornitore, double listino1, double listino2, double listino3, double aliquotaiva) {
    			this.idProdotto = idProdotto;
    			this.descrizione = descrizione;
    			this.categoria = categoria;
    			this.sottocategoria = sottocategoria;
    			this.quantita = quantita;
    			this.unitamisura = unitamisura;
    			this.fornitore = fornitore;
    			this.listino1 = listino1;
    			this.listino2 = listino2;
    			this.listino3 = listino3;
    			this.aliquotaiva = aliquotaiva;
}

    
    
    public static void add(String descrizione, String categoria, int quantita, UM unitamisura, String fornitore, double listino1) {
    	String sql = "INSERT INTO prodotto (descrizione, quantita, unitamisura, fornitore, categoria, listino1) VALUES (?,?,?::um,?,?,?)";
    	
    	try(Connection conn = ConnessioneDB.getConnection();
    			PreparedStatement stmt = conn.prepareStatement(sql)){
    		
    		stmt.setString(1, descrizione);		//1 indica il primo "?" della query, questo comando serve per impostare il parametro al posto del "?"
    		stmt.setInt(2, quantita);
    		stmt.setString(3, unitamisura.kg.name());
    		stmt.setString(4, fornitore);
    		stmt.setString(5, categoria);
    		stmt.setDouble(6, listino1);
    		stmt.executeUpdate();
    		JOptionPane.showMessageDialog(null, "Prodotto aggiunto con successo!");
    
    	}catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore inserimento: " + e.getMessage());
    	}
    }
    
    
    
    public static void modify(int id, String descrizione, String categoria, String sottocategoria, int quantita, UM unitamisura, String fornitore, double listino1, double listino2, double listino3, double aliquotaiva) {
        String sql = "UPDATE prodotto SET descrizione = ?, categoria = ?, sottocategoria = ?, quantita = ?, unitamisura = ?::um, fornitore = ?, listino1 = ?, listino2 = ?, listino3 = ?, aliquotaiva = ? WHERE id_prodotto = ?";
        try (Connection conn = ConnessioneDB.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, descrizione);
            stmt.setString(2, categoria);
            stmt.setString(3, sottocategoria);
            stmt.setInt(4, quantita);
            stmt.setString(5, unitamisura != null ? unitamisura.name() : null);
            stmt.setString(6, fornitore);
            stmt.setDouble(7, listino1);
            stmt.setDouble(8, listino2);
            stmt.setDouble(9, listino3);
            stmt.setDouble(10, aliquotaiva);
            stmt.setInt(11, id);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Prodotto modificato con successo!");
        } catch (SQLException e) {
            e.printStackTrace(); JOptionPane.showMessageDialog(null, "Errore modifica: " + e.getMessage());
        }
    }
    
    
    
    public static void delete(int id) {
    	String sql = "DELETE FROM prodotto WHERE id_prodotto = ?";
    	
    	try(Connection conn = ConnessioneDB.getConnection();
    			PreparedStatement stmt = conn.prepareStatement(sql)){
    		
    		stmt.setInt(1, id);
    		stmt.executeUpdate();
    		
    		JOptionPane.showMessageDialog(null, "Prodotto rimosso con successo!");
    
    	}catch(SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore rimozione: " + e.getMessage());
    	}
    }
    }
    
