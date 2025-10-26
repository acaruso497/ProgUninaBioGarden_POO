package code;

//tipologia di irrigazione
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
    public Irrigazione(int ID_Attivita, String giornoAssegnazione, Stato stato, int ID_Lotto, int ID_Progetto, 
					   String codiceFiscaleCol,tipoIrrigazione tipoi, String giornoInizio, String giornoFine) {
    	
		super(ID_Attivita, giornoAssegnazione, stato, ID_Lotto, ID_Progetto, codiceFiscaleCol);
		this.tipoi = tipoi;
		this.giornoInizio = giornoInizio;
		this.giornoFine = giornoFine;
    }

    //getters e setters
	public int getID_Irrigazione() {
		return ID_Irrigazione;
	}
	
	public void setID_Irrigazione(int iD_Irrigazione) {
		ID_Irrigazione = iD_Irrigazione;
	}
	
	public tipoIrrigazione getTipoi() {
		return tipoi;
	}
	
	public void setTipoi(tipoIrrigazione tipoi) {
		this.tipoi = tipoi;
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

