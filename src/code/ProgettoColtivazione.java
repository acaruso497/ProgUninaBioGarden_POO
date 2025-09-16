package code;

public class ProgettoColtivazione {
	//ATTRIBUTI
	private int ID_Progetto;
	private String titolo;
	private String descrizione;
	private double stimaRaccolto;
	private String dataInizio;
	private String dataFine;
	
	//COSTRUTTORE
	public ProgettoColtivazione(int ID_Progetto, String titolo, String descrizione, double stimaRaccolto, String dataInizio, String dataFine) {
		this.ID_Progetto=ID_Progetto;
		this.titolo=titolo;
		this.descrizione=descrizione;
		this.stimaRaccolto=stimaRaccolto;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
	}
}
