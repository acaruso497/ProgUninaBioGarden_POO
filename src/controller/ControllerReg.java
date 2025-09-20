package controller;

import dao.DAO;
public class ControllerReg {
	 public ControllerReg() {}
	 
	DAO dao = new dao.DAO();
	    public boolean[] registra(String nome ,String cognome , String username, String password,String cf, String ruolo) {
	        boolean[] result = new boolean[4];
	        // Inizializza l'array con valori di default
	        result[0] = false; // non è proprietario
	        result[1] = false; // non è coltivatore
	        result[2] = false; // registrazione non riuscita
	        result[3] = false;//user esiste 
	        
	        result[3]= DAO.usernameEsiste (username);
	        
	        if (result[3]==true) {
	            return result; // Se username esiste, esci subito
	        }
	        if (ruolo.equals("Proprietario")) {
	            result[0] = true; // è proprietario
	            result[2] =DAO.registraP(nome , cognome ,username, password,cf); // esito registrazione
	            
	        } else if (ruolo.equals("Coltivatore")) {
	            result[1] = true; // è coltivatore
	            result[2] = DAO.registraC(nome , cognome,username, password,cf); // esito registrazione
	        }
	        // Se il ruolo non è riconosciuto, restituisce [false, false, false]
	        return result;
	    }
	}