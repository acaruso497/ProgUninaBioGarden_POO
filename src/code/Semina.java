package code;

public class Semina extends Attivita {
	//ATTRIBUTI
	private int ID_Semina;
	private float profondita;
	private String giornoInizio;
	private String giornoFine;
	
	//COSTRUTTORE
	public Semina(int ID_Attivita, String giornoAssegnazione, Stato stato, int ID_Semina, String giornoInizio,
				  String giornoFine, int ID_Lotto, int ID_Progetto, String codiceFiscaleCol) {
		super(ID_Attivita, giornoAssegnazione, stato, ID_Lotto, ID_Progetto, codiceFiscaleCol);
		this.ID_Semina=ID_Semina;
		this.giornoInizio=giornoInizio;
		this.giornoFine=giornoFine;
	}

	//getters e setters
	public int getID_Semina() {
		return ID_Semina;
	}

	public void setID_Semina(int iD_Semina) {
		ID_Semina = iD_Semina;
	}

	public float getProfondita() {
		return profondita;
	}

	public void setProfondita(float profondita) {
		this.profondita = profondita;
	}

	public String getGiornoInizio() {
		return giornoInizio;
	}

	public void setGiornoInizio(String giornoInizio) {
		this.giornoInizio = giornoInizio;
	}

	public String getGiornoFine() {
		return giornoFine;
	}

	public void setGiornoFine(String giornoFine) {
		this.giornoFine = giornoFine;
	}
	
	
}
