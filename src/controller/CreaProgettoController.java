package controller;

import java.sql.Date;
import java.util.List;

import dao.DAO;
import dao.daoCreaP;

public class CreaProgettoController {
	private DAO dao; 
	private daoCreaP daoCreap;
	private int countSemina = 0;
    private int countIrrigazione = 0;
    private int countRaccolta = 0;

    public CreaProgettoController(DAO dao) {
        this.dao = dao;
        this.daoCreap = new daoCreaP();
    }
	
    // Popola ComboLotto con ID lotti del proprietario
    public List<String> getLottiPerCombo(String username) {
        return dao.getLottiByProprietario(username); 
    }
    
    //Crea il progetto di coltivazione inserendo i parametri tramite dao 
    public boolean creaProgetto(String titolo, String idLottoStr, String descrizione, String stimaRaccoltoStr, 
    							String [] coltureString, Date dataIP, Date dataFP) {
	    return daoCreaP.registraProgetto(titolo, idLottoStr, stimaRaccoltoStr, 
	    								 coltureString, descrizione, dataIP, dataFP);
    }
    
    //crea l'attività
    public boolean creaAttivita(String tipoAttivita, Date dataIA, Date dataFA, String tipoIrrigazione, String tipoSemina, String lotto) {
    	boolean risultato = daoCreaP.registraAttivita(tipoAttivita, dataIA, dataFA, tipoIrrigazione, tipoSemina, lotto);
    	
    	// Se l'attività è stata salvata con successo, incrementa i contatori
    	if (risultato==true) {
    		incrementaContatore(tipoAttivita);
    	}else {
    		return false;
    	}
    	
    	return risultato;
    }
    
    //incrementa il contatore delle attività
    private void incrementaContatore(String tipoAttivita) {
        if ("Semina".equals(tipoAttivita)) {
            countSemina++;
        } else if ("Irrigazione".equals(tipoAttivita)) {
            countIrrigazione++;
        } else if ("Raccolta".equals(tipoAttivita)) {
            countRaccolta++;
        }
    }
    
    // Verifica se l'utente ha completato almeno 1 semina, 1 irrigazione e 1 raccolta
    public boolean puoAvanzare() {
        return countSemina >= 1 && countIrrigazione >= 1 && countRaccolta >= 1;
    }
    
    // Reset dei contatori (utile quando si va alla prossima fase)
    public void resetContatori() {
        countSemina = 0;
        countIrrigazione = 0;
        countRaccolta = 0;
    }
    
    //controlla se le colture sono già piantate nel lotto
    public boolean checkColt(String idLotto, String[] coltureArray) {
    	return daoCreaP.checkColtura(idLotto, coltureArray);
    }
    
    //controlla se il progetto è completato
    public boolean controlloProgettoChiuso(String idLottoStr) {
    	return daoCreap.controlloProgettoChiuso(idLottoStr);
    }
    
   //divide la coltura dalla virgola e dagli spazi
    public String[] dividiPerVirgola(String input) { 	
	    String[] parti = input.split(",");
	    for (int i = 0; i < parti.length; i++) {
	        parti[i] = parti[i].trim();
	    }
	    return parti;
    }
    
}
