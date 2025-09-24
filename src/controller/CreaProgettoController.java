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

    public CreaProgettoController(DAO dao) {
        this.dao = dao;
        this.daoCreap = new daoCreaP();
    }
	
    // Popola ComboLotto con ID lotti del proprietario
    public List<String> getLottiPerCombo(String username) {
        return dao.getLottiByProprietario(username); 
    }
    
    //Crea il progetto di coltivazione inserendo i parametri tramite dao
    public boolean creaProgetto(String titolo, String idLotto, String descrizione, Date dataInizio, Date dataFine) {
    	return daoCreaP.registraProgetto(titolo, idLotto, descrizione, dataInizio, dataFine);
    }
    
    //Crea l'attività inserendo i parametri tramite dao
    public boolean creaAttivita(String tipoAttivita, Date dataIA, Date dataFA, String tipoIrrigazione, String tipoSemina) {
    	return daoCreaP.registraAttivita(tipoAttivita, dataIA, dataFA, tipoIrrigazione, tipoSemina);
    }
    
    //popola i textfield relativi alla coltura
    public void popolaColtura(JTextField FieldTipologia, JTextField FieldVarieta, String idLottoStr) {
    	daoCreap.popolaColtura(FieldTipologia, FieldVarieta, idLottoStr);
    } 
    
    // Aggiorna tipo di semina e irrigazione tramite dao
    public boolean aggiornaTipo(String tipoIrrigazione, String tipoSemina) {
    	return daoCreap.aggiornaTipo(tipoIrrigazione, tipoSemina);
    }
}
