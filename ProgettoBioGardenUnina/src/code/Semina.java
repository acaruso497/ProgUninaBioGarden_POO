package code;

public class Semina extends Attivita {
	//ATTRIBUTI
	private String ID_Semina;
	private float profondita;
	private String giornoInizio;
	private String giornoFine;
	
	//COSTRUTTORE
	public Semina(int ID_Attivita, String giornoAssegnazione, Stato stato, String ID_Semina, String giornoInizio, String giornoFine) {
		super(ID_Attivita, giornoAssegnazione, stato);
		this.ID_Semina=ID_Semina;
		this.giornoInizio=giornoInizio;
		this.giornoFine=giornoFine;
	}
}
