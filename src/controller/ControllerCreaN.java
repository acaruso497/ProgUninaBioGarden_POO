package controller;

import java.util.Arrays;

import dao.DAO;

import java.util.ArrayList;
import java.sql.Date;

public class ControllerCreaN {
	
public ControllerCreaN () {}

// viene chiamato se la spunta "tutti i coltivatori" è disattivata	
public boolean dividiUsername(String usernameProprietario, String usernameConcatenati, Date data, 
							  String titolo, String descrizione) {		
	
				// Split della stringa
				String[] usernamesArray = usernameConcatenati.split(",");	        
	        
				// Converti l'array in ArrayList
				ArrayList<String> usernamesList = new ArrayList<>(Arrays.asList(usernamesArray));
	       
				// Chiamo il dao per ogni utente
				DAO dao = new DAO();
				
				// Crea un arraylist contenenti tutti i coltivatori che appartengono al proprietario loggato
				ArrayList<String> coltivatoriProprietario= dao.getColtivatoriByProprietario(usernameProprietario);
				
				// Verifica se i coltivatori appartengono al proprietario loggato
				for(int i = 0; i < usernamesList.size(); i++) {
			        if (!coltivatoriProprietario.contains(usernamesList.get(i))) {
			            return false; 
			        }
			    }
				
				for(int i = 0; i < usernamesList.size(); i++) {
					dao.Inserisci_NotificaDB(usernamesList.get(i), data, titolo, descrizione);
				}
				
				return true;

	    }

//viene chiamato se la spunta "tutti i coltivatori" è attivata	
public void dividiUsernameTutti(String usernameproprietario, Date data, String titolo, String descrizione) {		
	
	DAO dao = new DAO();
	String usernameConcatenati=dao.getDestinatariUsernamesByProprietario(usernameproprietario);
	
	// Split della stringa
	String[] usernamesArray = usernameConcatenati.trim().split(",");	        

	// Converti l'array in ArrayList
	ArrayList<String> usernamesList = new ArrayList<>(Arrays.asList(usernamesArray));

	// Chiamo il dao per ogni utente
	for(int i = 0; i < usernamesList.size(); i++) {
		dao.Inserisci_NotificaDB(usernamesList.get(i), data, titolo, descrizione);
	}

}

//controlla l'esistenza di un username di un coltivatore
public boolean controllaUsername(String username) {
	return DAO.usernameEsiste(username);
}

//restituisce i coltivatori appartenenti ad un dato proprietario
public ArrayList <String> getColtivatoriByProprietario(String usernameProprietario) {
	DAO dao = new DAO();
	return dao.getColtivatoriByProprietario(usernameProprietario);
}

	public void Notifica (String DataInserita,String titolo ,String usernameC,String descrizione ) {
		
	}
}