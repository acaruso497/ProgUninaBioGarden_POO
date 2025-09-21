package controller;

import dao.DAO;

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
}
