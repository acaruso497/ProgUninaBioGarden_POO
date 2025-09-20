package controller;


import gui.CreaNotifica;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
@SuppressWarnings("unused")
public class ControllerCreaN {
	
	public ControllerCreaN () {}
	
ArrayList<String>dividiUsername(Date data ,String usernameConcatenati, String titolo, String testo) {
	        // 1. Split della stringa
	        String[] usernamesArray = usernameConcatenati.split(",");	        
	        
	        // 2. Converti l'array in ArrayList
	        ArrayList<String> usernamesList = new ArrayList<>(Arrays.asList(usernamesArray));
	       
	        // 3. RESTITUISCI la lista
	        return usernamesList;
	    }
	
	public void Notifica (String DataInserita,String titolo ,String usernameC,String descrizione ) {
		
	}
}