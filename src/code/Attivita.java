package code;

// stati delle attività
enum Stato{
	pianificata,
    svolgimento,
    terminata
}

public abstract class Attivita {
	//ATTRIBUTI
	protected int ID_Attivita;
	protected String giornoAssegnazione;
	protected Stato stato;
	private int ID_Lotto;
	private int ID_Progetto;
	private String codiceFiscaleCol;
	
	//COSTRUTTORE
	public Attivita(int ID_Attivita, String giornoAssegnazione, Stato stato, int ID_Lotto, int ID_Progetto, String codiceFiscaleCol) {
		this.ID_Attivita=ID_Attivita;
		this.giornoAssegnazione=giornoAssegnazione;
		this.stato=stato;
		this.ID_Lotto=ID_Lotto;
		this.ID_Progetto=ID_Progetto;
		this.codiceFiscaleCol=codiceFiscaleCol;
		
	}

	// getters e setters
	public int getID_Attivita() {
		return ID_Attivita;
	}

	public void setID_Attivita(int iD_Attivita) {
		ID_Attivita = iD_Attivita;
	}

	public String getGiornoAssegnazione() {
		return giornoAssegnazione;
	}

	public void setGiornoAssegnazione(String giornoAssegnazione) {
		this.giornoAssegnazione = giornoAssegnazione;
	}

	public int getID_Lotto() {
		return ID_Lotto;
	}

	public void setID_Lotto(int iD_Lotto) {
		ID_Lotto = iD_Lotto;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public int getID_Progetto() {
		return ID_Progetto;
	}

	public void setID_Progetto(int iD_Progetto) {
		ID_Progetto = iD_Progetto;
	}

	public String getCodiceFiscaleCol() {
		return codiceFiscaleCol;
	}

	public void setCodiceFiscaleCol(String codiceFiscaleCol) {
		this.codiceFiscaleCol = codiceFiscaleCol;
	}
	
	
}
