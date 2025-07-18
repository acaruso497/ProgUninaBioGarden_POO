package code;

public class Notifica {
	//ATTRIBUTI
	private int ID_Notifica;
	private String attivitaProgrammate;
	private String errori;
	private String anomalie;
	
	//COSTRUTTORE
	public Notifica(int ID_Notifica, String attivitaProgrammate, String errori, String anomalie) {
		this.ID_Notifica=ID_Notifica;
		this.attivitaProgrammate=attivitaProgrammate;
		this.errori=errori;
		this.anomalie=anomalie;
	}
}
