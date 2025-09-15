package controller;
import dao.daoVisualizzaP;
import java.util.List;

import javax.swing.JTextField;

public class ControllerVisualizzaP {
private daoVisualizzaP dao;
	
	public ControllerVisualizzaP (daoVisualizzaP dao) {
		this.dao = dao;
	}

    // Popola ComboLotto con ID lotti del proprietario (riusa DAO esistente, ma filtra per progetti se serve)
    public List<String> getLottiByProprietario(String username) {
        return dao.getLottiByProprietario(username);  // Esatto come in CreaProgettoController
    }

    // Popola ComboColtivatori con username coltivatori assegnati al progetto selezionato
    public List<String> getColtivatoriByProgetto(String idProgetto) {
        return dao.getColtivatoriByProgetto(idProgetto);  // int idProgetto da parsing
    }
    
    
    // Popola ComboAttivita con tipi/ID attività del progetto selezionato
    public List<String> getAttivitaByProgetto(String idProgetto) {
        return dao.getAttivitaByProgetto(idProgetto);
    }
    
    	// Popola ComboProgetto con titoli/ID progetti del proprietario
    public List<String> getProgettiByProprietario(String username) {
        return dao.getProgettiByProprietario(username);  // Restituisce List<String> (es. titoli o "ID: Titolo")
    } 
    

    // Per settare campi auto: Fetcha dati specifici per un progetto/attività
    public void popolaDatiProgetto(String idProgetto, JTextField fieldStima, JTextField fieldEffettivo, 
                                   JTextField fieldDataIP, javax.swing.JTextField fieldDataFP) {
        dao.popolaDatiProgetto(idProgetto, fieldStima, fieldEffettivo, fieldDataIP, fieldDataFP);
    } 

  
    
}
