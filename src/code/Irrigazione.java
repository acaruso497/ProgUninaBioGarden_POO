package code;
enum tipoIrrigazione{
	goccia,
    pioggia,
    scorrimento
}

public class Irrigazione extends Attivita {
	//ATTRIBUTI
	private int ID_Irrigazione;
	private tipoIrrigazione tipoi;
	private String giornoInizio;
	private String giornoFine;
	
	//COSTRUTTORE
	public Irrigazione(int ID_Attivita, String giornoAssegnazione, Stato stato, tipoIrrigazione tipoi, String giornoInizio, String giornoFine) {
		super(ID_Attivita, giornoAssegnazione, stato);
		this.tipoi=tipoi;
		this.giornoInizio=giornoInizio;
		this.giornoFine=giornoFine;
	}
}
