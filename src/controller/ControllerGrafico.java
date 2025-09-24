package controller;

import javax.swing.JTextField;

import java.util.List;

import dao.daoGrafico;

public class ControllerGrafico {
	private daoGrafico dao;
	
	public ControllerGrafico () {
		this.dao = new daoGrafico();
	}
	
	 // Popola ComboColtura con l'ID del lotto
	public List<String> getColturaByLotto(String idLottoStr) {
        return dao.getColturaByLotto(idLottoStr);
    }
}
