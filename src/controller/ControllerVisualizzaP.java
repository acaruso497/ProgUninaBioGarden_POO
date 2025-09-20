package controller;
import dao.daoVisualizzaP;
import java.util.List;

import javax.swing.JTextField;

public class ControllerVisualizzaP {
private daoVisualizzaP dao;
	
	public ControllerVisualizzaP (daoVisualizzaP dao) {
		this.dao = dao;
	}

    // Popola ComboLotto con ID lotti del proprietario
    public List<String> getLottiByProprietario(String username) {
        return dao.getLottiByProprietario(username);
    }
    
    
    //Popola i RadioButton con lo stato estratto dal dao, data inizio e data fine
    public String popolaAttivita(String idProgetto, String tipoAttivita, JTextField fieldDataIA, JTextField fieldDataFA) {
        return dao.popolaAttivita(idProgetto, tipoAttivita, fieldDataIA, fieldDataFA); 
    }
    
    	// Popola ComboProgetto con titoli/ID progetti del proprietario
    public List<String> getProgettiByProprietario(String username) {
        return dao.getProgettiByProprietario(username);  // Restituisce List<String> (es. titoli o "ID: Titolo")
    } 
    

    // Per settare campi auto: Fetcha dati specifici per un progetto/attività
    public void popolaDatiProgetto(String idProgetto, JTextField fieldStima, JTextField fieldEffettivo, 
                                   JTextField fieldDataIP, JTextField fieldDataFP) {
        dao.popolaDatiProgetto(idProgetto, fieldStima, fieldEffettivo, fieldDataIP, fieldDataFP);
    } 
    
    public boolean aggiornaStato(String stato, String tipoAttivita, String idLotto) {
    	return dao.aggiornaStato(stato, tipoAttivita, idLotto);
    }
    
  
    
}