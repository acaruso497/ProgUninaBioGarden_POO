package code;

public class ProgettoColtivazione {
	//ATTRIBUTI
	private int ID_Progetto;
	private double stimaRaccolto;
	private String dataInizio;
	private String dataFine;
	
	//COSTRUTTORE
	public ProgettoColtivazione(int ID_Progetto, double stimaRaccolto, String dataInizio, String dataFine) {
		this.ID_Progetto=ID_Progetto;
		this.stimaRaccolto=stimaRaccolto;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
}
