package code;

public class ProgettoColtivazione {
	//ATTRIBUTI
	private int ID_Progetto;
	private String titolo;
	private String descrizione;
	private double stimaRaccolto;
	private String dataInizio;
	private String dataFine;
	private int ID_Lotto;
	private boolean done;
	
	//COSTRUTTORE
	public ProgettoColtivazione(int ID_Progetto, String titolo, String descrizione, 
								double stimaRaccolto, String dataInizio, String dataFine, int ID_Lotto, boolean done) {
		
		this.ID_Progetto=ID_Progetto;
		this.titolo=titolo;
		this.descrizione=descrizione;
		this.stimaRaccolto=stimaRaccolto;
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
		this.ID_Lotto=ID_Lotto;
		this.done=done;
	}

	//getters e setters
	public int getID_Progetto() {
		return ID_Progetto;
	}

	public void setID_Progetto(int iD_Progetto) {
		ID_Progetto = iD_Progetto;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getStimaRaccolto() {
		return stimaRaccolto;
	}

	public void setStimaRaccolto(double stimaRaccolto) {
		this.stimaRaccolto = stimaRaccolto;
	}

	public String getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}

	public String getDataFine() {
		return dataFine;
	}

	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}

	public int getID_Lotto() {
		return ID_Lotto;
	}

	public void setID_Lotto(int iD_Lotto) {
		ID_Lotto = iD_Lotto;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
	
	
}
