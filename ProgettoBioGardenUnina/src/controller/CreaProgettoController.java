package controller;

import java.util.List;

import dao.DAO;

public class CreaProgettoController {
//GUI: Crea Progetto
	private DAO dao;  

    public CreaProgettoController(DAO dao) {
        this.dao = dao;
    }

    public List<String> getLottiPerCombo(String username) {
        return dao.getLottiByProprietario(username); //restituisce gli ID dei lotti di quel proprietario che ha fatto il login
    }
}
