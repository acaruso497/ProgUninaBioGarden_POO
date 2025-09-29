package controller;

import dao.DAO;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.util.ArrayList;
import java.util.List;

public class ControllerColtivatore {
    private DAO dao = new DAO(); // UNICA istanza
    
    public ControllerColtivatore() {}
    
    public void legginotifiche(String usernamecoltivatore) {
        dao.segnaNotificheColtivatoreComeLette(usernamecoltivatore);
    }
    
    public boolean checknotifiche(String usernamecoltivatore) {
        return dao.ciSonoNotificheNonLette(usernamecoltivatore);
    }
    
    public String mostranotifiche(String usernamecoltivatore) {
        return dao.getNotificheNonLette(usernamecoltivatore);
    }
    
    public List<String> popolaPrComboBox(String username) {
        return dao.popolaProgettiCB(username);
    }
    
    public List<String> DateInizioFineP(String titolo_progetto, String username) {
        return dao.dateI_FProgCB(titolo_progetto, username);
    }
    
    public List<String> getTipiAttivita(String username, String progetto) {
        return dao.getTipiAttivitaColtivatore(username, progetto);
    }

    public List<String> getIdAttivita(String username, String progetto) {
        return dao.getIdAttivitaColtivatore(username, progetto);
    }

    public String getEsperienzaColtivatore(String username) {
        return dao.getEsperienzaColtivatore(username);
    }
    //metodo per ottenere le date di inizio e fine di una specifica attivita
    public String[] getDateByAttivitaId(String idAttivita, String tipoAttivita) {
        return dao.getDateByAttivitaId(idAttivita, tipoAttivita);
    }
    
    public String getLottoEPosizioneByProgetto(String progetto, String username) {
        return dao.getLottoEPosizione(progetto, username);
    }
    
    public String getStimaRaccolto(String username, String progetto) {
        return dao.getStimaRaccolto(username, progetto);
    }

    public String[] getColturaEVarieta(String username, String progetto) {
        return dao.getColturaEVarieta(username, progetto);
    }
    
    public String getIrrigazione(String username, String progetto) {
        return dao.getIrrigazione(username, progetto);
    } 
    
    public String getTipoSemina(String idSemina) {
        return dao.getTipoSemina(idSemina);
    }
}