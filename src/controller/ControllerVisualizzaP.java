package controller;
import dao.daoVisualizzaP;
import java.util.List;

import javax.swing.JTextField;

public class ControllerVisualizzaP {
private daoVisualizzaP dao;
	
	// COSTRUTTORE
	public ControllerVisualizzaP (daoVisualizzaP dao) {
		this.dao = dao;
	}

	// Popola i RadioButton con lo stato estratto dal dao, data inizio e data fine
    public String popolaAttivita(String idProgettoStr, String tipoAttivita, JTextField fieldDataIA, JTextField fieldDataFA) {
        return dao.popolaAttivita(idProgettoStr, tipoAttivita, fieldDataIA, fieldDataFA); 
    }
	
    // Aggiorna lo stato delle attività
    public boolean aggiornaStato(String stato, String tipoAttivita, String idLottoStr) {
    	return dao.aggiornaStato(stato, tipoAttivita, idLottoStr);
    }
    
    // Popola ComboProgetto con ID progetti del proprietario
    public List<String> getProgettiByProprietario(String username) {
        return dao.getProgettiByProprietario(username);  
    }
	
    // Popola ComboLotto con ID lotti del proprietario
//    public String getLottiByProprietario(String username) {
//        return dao.getLottiByProprietario(username);
//    }
    
    // Popola il field del lotto tramite l'id del progetto e codice fiscale del del proprietario
    public String getLottiByProprietario(int idProgetto, String codiceFiscaleProprietario) {
	  return dao.getLottiByProprietario(idProgetto, codiceFiscaleProprietario);
  }
    
   
    //popola la combobox delle colture tramite l'id del lotto e l'id del progetto
    public List<String> getColtureByLotto(String idLottoStr, String idProgettoStr) {
    	return dao.getColtureByLotto(idLottoStr, idProgettoStr);
    }
    
    
    // Setta l'ID del progetto, i campi di data inizio e data fine
    public void popolaDatiProgetto(String idProgettoStr, JTextField fieldStima, 
    	                           JTextField fieldDataIP, JTextField fieldDataFP) {
    	
        dao.popolaDatiProgetto(idProgettoStr, fieldStima, fieldDataIP, fieldDataFP);
    } 
     
    // Mostra il raccolto stimato e quello effettivo
    public void mostraRaccolto (String idProgettoStr, String idLottoStr, String coltura, JTextField FieldEffettivo) {
    	dao.mostraRaccolto(idProgettoStr, idLottoStr, coltura, FieldEffettivo);
    }
    
    // termina il progetto di coltivazione 
    public boolean terminaProgetto(String idProgettoStr, String idLottoStr) {
    	return dao.terminaProgetto(idProgettoStr, idProgettoStr);
    	
    }
    
    public boolean isCompletata(String username, String idProgettoStr) {
    	return dao.isCompletata(username, idProgettoStr);
    	
    }
    
}