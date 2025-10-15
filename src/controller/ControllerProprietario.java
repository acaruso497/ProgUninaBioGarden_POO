package controller;
import dao.DAO;
public class ControllerProprietario {
	private DAO dao = new DAO();
	
	//aggiunge il lotto in base al codice fiscale del proprietario loggato
	public boolean aggiungiL(String cf) {
		return dao.aggiungiL(cf);
		
	}
}
