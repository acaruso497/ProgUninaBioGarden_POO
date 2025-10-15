package controller;
import dao.DAO;
public class ControllerProprietario {
	private DAO dao = new DAO();
	
	
	public boolean aggiungiL(String cf) {
		return dao.aggiungiL(cf);
		
	}
}
