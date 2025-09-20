package code;
@SuppressWarnings("unused")
public class Raccolta extends Attivita {
	//ATTRIBUTI
	private int ID_Raccolta;
	private float raccolto_effettivo;
	private String giornoInizio;
	private String giornoFine;
	
	//COSTRUTTORE
	public Raccolta(int ID_Attivita, String giornoAssegnazione, Stato stato, int ID_Raccolta, float raccolto_effettivo, String giornoInizio, String giornoFine) {
		super(ID_Attivita, giornoAssegnazione, stato);
		this.ID_Raccolta=ID_Raccolta;
		this.raccolto_effettivo=raccolto_effettivo;
		this.giornoInizio=giornoInizio;
		this.giornoFine=giornoFine;
	}
}
