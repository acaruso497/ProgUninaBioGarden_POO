package controller;

import java.util.ArrayList;
import dao.DAO;
public class ControllerReg {
	public ControllerReg() {}
	 
	DAO dao = new dao.DAO();
	public boolean[] registra(String nome, String cognome, String username, 
							  String password, String cf, String ruolo, String usernameProprietario) {
        boolean[] result = new boolean[4];
        result[0] = false; // non è proprietario
        result[1] = false; // non è coltivatore
        result[2] = false; // registrazione non riuscita
        result[3] = false; // user non esiste (inizialmente)

        result[3] = DAO.usernameEsiste(username); // Verifica se username esiste
        
        if (result[3] == true) {
            return result; // Esce se username esiste
        }

        if (ruolo.equals("Proprietario")) {
            result[0] = true; // è proprietario
            result[2] = DAO.registraP(nome, cognome, username, password, cf); // esito registrazione
        } else if (ruolo.equals("Coltivatore")) {
            result[1] = true; // è coltivatore
            result[2] = DAO.registraC(nome, cognome, username, password, cf, usernameProprietario); // esito registrazione
            if (result[2] && usernameProprietario != null && !usernameProprietario.equals("--Seleziona--")) {
                // Recupera i lotti del proprietario e associa il coltivatore
            	
                ArrayList<Integer> lotti = dao.getLottiByProprietarioUsername(usernameProprietario);
                
                for (int idLotto : lotti) {
                    dao.associaColtivatoreProprietario(cf, idLotto);
                }
            }
        }
        return result;
    }

	//popola la combobox dei proprietari
    public ArrayList<String> popolaComboProprietari() {
        return dao.popolaComboProprietari();
    }
}