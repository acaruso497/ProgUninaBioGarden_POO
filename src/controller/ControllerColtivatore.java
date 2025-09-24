package controller;

import dao.DAO;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public class ControllerColtivatore {
	public ControllerColtivatore() {}
	
	public void legginotifiche(String usernamecoltivatore) {
		DAO dao = new DAO();
		dao.segnaNotificheColtivatoreComeLette(usernamecoltivatore);
	}
	
	public boolean checknotifiche(String usernamecoltivatore) {
		DAO dao = new DAO();
		if(dao.ciSonoNotificheNonLette(usernamecoltivatore)) return true;
		else return false;
	}
	
	public String mostranotifiche(String usernamecoltivatore) {
		DAO dao = new DAO();
		
		return dao.getNotificheNonLette(usernamecoltivatore);
	}
	
	// progetto data inizio e fine con nome progetti
	
	String username0 = ControllerLogin.getUsernameGlobale();

	public List<String> popolaPrComboBox(String username) {
		return dao. popolaProgettiCB(username);
		
	}
	public List<String> DateInizioFineP(String titolo_progetto, String username) {
		
		return dao.dateI_FProgCB(titolo_progetto, username);
	}
	
	
    private DAO dao = new DAO();


    
    public List<String> getTipiAttivita(String username, String progetto) {
        return dao.getTipiAttivitaColtivatore(username, progetto);
    }

    public List<String> getIdAttivita(String username, String progetto) {
        return dao.getIdAttivitaColtivatore(username, progetto);
    }

  
    public String getEsperienzaColtivatore(String username) {
        DAO dao = new DAO();
        return dao.getEsperienzaColtivatore(username);
    }
    	
    
    public String[] getDateByAttivitaId(String idAttivita) {
        return dao.getDateByAttivitaId(idAttivita);
    }
    
    public String getLottoEPosizioneByProgetto(String progetto, String username) {
        DAO dao = new DAO();
        return dao.getLottoEPosizione(progetto, username);
    }
    
    public String getStimaRaccolto(String username, String progetto) {
        return dao.getStimaRaccolto(username, progetto);
    }

    public String[] getColturaEVarieta(String username, String progetto) {
        DAO dao = new DAO();
        return dao.getColturaEVarieta(username, progetto);
    }
    public String getIrrigazione(String username, String progetto) {
        return dao.getIrrigazione(username, progetto);
    } 
    public String getTipoSemina(String idSemina) {
        DAO dao = new DAO();
        return dao.getTipoSemina(idSemina);
    }
    
}