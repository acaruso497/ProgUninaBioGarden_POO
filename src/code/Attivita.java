package code;
enum Stato{
	pianificata,
    svolgimento,
    terminata
}

public class Attivita {
	//ATTRIBUTI
	protected int ID_Attivita;
	protected String giornoAssegnazione;
	protected Stato stato;
	
	//COSTRUTTORE
	public Attivita(int ID_Attivita, String giornoAssegnazione, Stato stato) {
		this.ID_Attivita=ID_Attivita;
		this.giornoAssegnazione=giornoAssegnazione;
		this.stato=stato;
	}
}
