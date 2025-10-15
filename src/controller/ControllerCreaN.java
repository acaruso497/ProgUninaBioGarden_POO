package controller;


import gui.CreaNotifica;
import java.util.Arrays;
import java.util.List;

import dao.DAO;

import java.util.ArrayList;
import java.sql.Date;
@SuppressWarnings("unused")
public class ControllerCreaN {
	
public ControllerCreaN () {}
	
public boolean dividiUsername(String usernameProprietario, String usernameConcatenati, Date data, String titolo, String descrizione) {		//se ha inserito i coltivatori
	
				// 1. Split della stringa
				String[] usernamesArray = usernameConcatenati.split(",");	        
	        
				// 2. Converti l'array in ArrayList
				ArrayList<String> usernamesList = new ArrayList<>(Arrays.asList(usernamesArray));
	       
				//chiamo il dao per ogni utente
				DAO dao = new DAO();
				
				//crea un arraylist contenenti tutti i coltivatori che appartengono al proprietario loggato
				ArrayList<String> coltivatoriProprietario= dao.getColtivatoriByProprietario(usernameProprietario);
				
				//verifica se i coltivatori appartengono al proprietario loggato
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

public void dividiUsernameTutti(String usernameproprietario, Date data, String titolo, String descrizione) {		//se ha attivato la spunta
	
	DAO dao = new DAO();
	String usernameConcatenati=dao.getDestinatariUsernamesByProprietario(usernameproprietario);
	
	// 1. Split della stringa
	String[] usernamesArray = usernameConcatenati.trim().split(",");	        

	// 2. Converti l'array in ArrayList
	ArrayList<String> usernamesList = new ArrayList<>(Arrays.asList(usernamesArray));

	//chiamo il dao per ogni utente
	for(int i = 0; i < usernamesList.size(); i++) {
		dao.Inserisci_NotificaDB(usernamesList.get(i), data, titolo, descrizione);
	}

}
	
public boolean controllaUsername(String username) {
	return DAO.usernameEsiste(username);
}

public ArrayList <String> getColtivatoriByProprietario(String usernameProprietario) {
	DAO dao = new DAO();
	return dao.getColtivatoriByProprietario(usernameProprietario);
}

	public void Notifica (String DataInserita,String titolo ,String usernameC,String descrizione ) {
		
	}
}