package controller;

import java.sql.Date;
import java.util.List;

import javax.swing.JTextField;

import dao.DAO;
import dao.daoCreaP;

public class CreaProgettoController {
//GUI: Crea Progetto
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
    
    //Crea il progetto di coltivazione inserendo i parametri tramite dao !!MODIFICATO!!
    public boolean creaProgetto(String titolo, String idLottoStr, String descrizione, String stimaRaccoltoStr, 
    							String [] coltureString, Date dataIP, Date dataFP) {
    	
	    	
	    //PASSA AL DAO !!MODIFICATO!!
	    return daoCreaP.registraProgetto(titolo, idLottoStr, stimaRaccoltoStr, 
	    		coltureString, descrizione, dataIP, dataFP);
    }
    
    
    public boolean creaAttivita(String tipoAttivita, Date dataIA, Date dataFA, String tipoIrrigazione, String tipoSemina, String lotto) {
    	boolean risultato = daoCreaP.registraAttivita(tipoAttivita, dataIA, dataFA, tipoIrrigazione, tipoSemina, lotto);
    	
    	// Se l'attività è stata salvata con successo, incrementa i contatori
    	if (risultato==true) {
    		incrementaContatore(tipoAttivita);
    	}
    	
    	return risultato;
    }
    
    
    private void incrementaContatore(String tipoAttivita) {
        if ("Semina".equals(tipoAttivita)) {
            countSemina++;
            System.out.println("DEBUG: Incrementato countSemina a " + countSemina);
        } else if ("Irrigazione".equals(tipoAttivita)) {
            countIrrigazione++;
            System.out.println("DEBUG: Incrementato countIrrigazione a " + countIrrigazione);
        } else if ("Raccolta".equals(tipoAttivita)) {
            countRaccolta++;
            System.out.println("DEBUG: Incrementato countRaccolta a " + countRaccolta);
        }
    }
    
    // Verifica se l'utente ha completato almeno 1 semina, 1 irrigazione e 1 raccolta
    public boolean puoAvanzare() {
        System.out.println("DEBUG: Controllo puoAvanzare() - Semina: " + countSemina + ", Irrigazione: " + countIrrigazione + ", Raccolta: " + countRaccolta);
        return countSemina >= 1 && countIrrigazione >= 1 && countRaccolta >= 1;
    }
    
    // Reset dei contatori (utile quando si va alla prossima fase)
    public void resetContatori() {
        countSemina = 0;
        countIrrigazione = 0;
        countRaccolta = 0;
        System.out.println("DEBUG: Contatori resettati - Semina: " + countSemina + ", Irrigazione: " + countIrrigazione + ", Raccolta: " + countRaccolta);
    }
    public boolean checkColt(String idLotto, String[] coltureArray) {
    	return daoCreaP.checkColtura(idLotto, coltureArray);
    }
    
    
    
}
