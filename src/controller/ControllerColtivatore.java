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


    
    public List<String> getIdAttivita(String username) {
        return dao.getIdAttivitaColtivatore(username);
    }

    public List<String> getTipiAttivita(String username) {
        return dao.getTipiAttivitaColtivatore(username);
    }

    public String getTipoAttivitaByIndex(String username, int index) {
        List<String> tipi = getTipiAttivita(username);
        if (tipi != null && tipi.size() > index) {
            return tipi.get(index);
        }
        return null;
    }
    
    
    public String[] getDateByAttivitaId(String idAttivita) {
        return dao.getDateByAttivitaId(idAttivita);
    }
    
    public String getLottoEPosizioneByProgetto(String progetto, String username) {
        DAO dao = new DAO();
        return dao.getLottoEPosizione(progetto, username);
    }
    
    
    
}