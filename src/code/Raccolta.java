package code;

public class Raccolta extends Attivita {
	//ATTRIBUTI
	private int ID_Raccolta;
	private float raccolto_effettivo;
	private String giornoInizio;
	private String giornoFine;
	
	//COSTRUTTORE
	public Raccolta(int ID_Attivita, String giornoAssegnazione, Stato stato, int ID_Raccolta, float raccolto_effettivo, 
					String giornoInizio, String giornoFine, int ID_Lotto, int ID_Progetto, String codiceFiscaleCol) {
		
		super(ID_Attivita, giornoAssegnazione, stato, ID_Lotto, ID_Progetto, codiceFiscaleCol);
		this.ID_Raccolta=ID_Raccolta;
		this.raccolto_effettivo=raccolto_effettivo;
		this.giornoInizio=giornoInizio;
		this.giornoFine=giornoFine;
	}

	//getters e setters
	public int getID_Raccolta() {
		return ID_Raccolta;
	}

	public void setID_Raccolta(int iD_Raccolta) {
		ID_Raccolta = iD_Raccolta;
	}

	public float getRaccolto_effettivo() {
		return raccolto_effettivo;
	}

	public void setRaccolto_effettivo(float raccolto_effettivo) {
		this.raccolto_effettivo = raccolto_effettivo;
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
