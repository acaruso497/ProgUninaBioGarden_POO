package controller;
import dao.daoVisualizzaP;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTextField;

public class ControllerVisualizzaP {
private daoVisualizzaP dao;
	
	// COSTRUTTORE
	public ControllerVisualizzaP (daoVisualizzaP dao) {
		this.dao = dao;
	}

	// Popola i RadioButton con lo stato estratto dal dao, data inizio e data fine
    public String popolaAttivita(String titoloProgetto, String tipoAttivita, JTextField fieldDataIA, JTextField fieldDataFA) {
        return dao.popolaAttivita(titoloProgetto, tipoAttivita, fieldDataIA, fieldDataFA); 
    }
	
    // Aggiorna lo stato delle attività
    public boolean aggiornaStato(String stato, String tipoAttivita, String idLottoStr) {
    	return dao.aggiornaStato(stato, tipoAttivita, idLottoStr);
    }
    
    // Popola ComboProgetto con il titolo del progetto del proprietario
    public List<String> getProgettiByProprietario(String username) {
        return dao.getProgettiByProprietario(username);  
    }
	
    // Popola il field del lotto tramite il titolo del progetto e codice fiscale del del proprietario
    public String getLottiByProprietario(String titoloProgetto, String codiceFiscaleProprietario) {
	  return dao.getLottiByProprietario(titoloProgetto, codiceFiscaleProprietario);
  }
    
    // Setta l'ID del progetto, i campi di data inizio e data fine
    public void popolaDatiProgetto(String titoloProgetto, JTextField fieldStima, 
    	                           JTextField fieldDataIP, JTextField fieldDataFP) {
        dao.popolaDatiProgetto(titoloProgetto, fieldStima, fieldDataIP, fieldDataFP);
    } 
     
    // Mostra il raccolto stimato
    public void mostraRaccolto (String titoloProgetto, String idLottoStr, String coltura) {
    	dao.mostraRaccolto(titoloProgetto, idLottoStr, coltura);
    }
    
    // termina il progetto di coltivazione 
    public boolean terminaProgetto(String titoloProgetto, String idLottoStr) {
    	return dao.terminaProgetto(titoloProgetto, idLottoStr);
    }
    
    //controlla se il progetto è completato
    public boolean isCompletata(String username, String titoloProgetto) {
    	return dao.isCompletata(username, titoloProgetto);
    }
    
    //restituisce le colture presenti nel lotto del progetto di coltivazione in riferimento al proprietario
    public ArrayList<String> getColtureProprietario(String CF, String titoloProgetto) {
    	return dao.getColtureProprietario(CF, titoloProgetto);
	}
    
    //restituisce il raccolto prodotto della coltura
    public String getRaccoltoProdotto(String username, int idLotto, String coltura){
    	return dao.getRaccoltoProdotto(username, idLotto, coltura);
	}
    
}