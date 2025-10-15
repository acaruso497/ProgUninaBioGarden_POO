package controller;

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
	
	// chiama il dao per ottenere le statistiche
    public double[] getStatistiche(int idLotto, String varieta) {
        long   num   = dao.getNumeroRaccolte(idLotto, varieta);
        double media = dao.getMediaRaccolto(idLotto, varieta);
        double min   = dao.getMinRaccolto(idLotto, varieta);
        double max   = dao.getMaxRaccolto(idLotto, varieta);
        return new double[]{ num, media, min, max };
    }
}