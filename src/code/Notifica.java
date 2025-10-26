package code;

public class Notifica {
	//ATTRIBUTI
	private int ID_Notifica;
	private String attivitaProgrammate;
	private String dataEvento;
	private String utentiTag;
	private boolean tuttiColtivatori;
	private String titolo;
	private String descrizione;
	private boolean lettura;
	private String errori;
	private String anomalie;
	private int ID_Attivita;
	
	//COSTRUTTORE
	public Notifica(int ID_Notifica, String attivitaProgrammate, String dataEvento, String utentiTag, boolean tuttiColtivatori, 
					String titolo, String descrizione, boolean lettura, String errori, String anomalie, int ID_Attivita) {
		
		this.ID_Notifica=ID_Notifica;
		this.attivitaProgrammate=attivitaProgrammate;
		this.dataEvento=dataEvento;
		this.utentiTag=utentiTag;
		this.tuttiColtivatori=tuttiColtivatori;
		this.titolo=titolo;
		this.descrizione=descrizione;
		this.lettura=lettura;
		this.errori=errori;
		this.anomalie=anomalie;
		this.ID_Attivita=ID_Attivita;
	}

	//getters e setters
	public int getID_Notifica() {
		return ID_Notifica;
	}

	public void setID_Notifica(int iD_Notifica) {
		ID_Notifica = iD_Notifica;
	}

	public String getAttivitaProgrammate() {
		return attivitaProgrammate;
	}

	public void setAttivitaProgrammate(String attivitaProgrammate) {
		this.attivitaProgrammate = attivitaProgrammate;
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getUtentiTag() {
		return utentiTag;
	}

	public void setUtentiTag(String utentiTag) {
		this.utentiTag = utentiTag;
	}

	public boolean isTuttiColtivatori() {
		return tuttiColtivatori;
	}

	public void setTuttiColtivatori(boolean tuttiColtivatori) {
		this.tuttiColtivatori = tuttiColtivatori;
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

	public boolean isLettura() {
		return lettura;
	}

	public void setLettura(boolean lettura) {
		this.lettura = lettura;
	}

	public String getErrori() {
		return errori;
	}

	public void setErrori(String errori) {
		this.errori = errori;
	}

	public String getAnomalie() {
		return anomalie;
	}

	public void setAnomalie(String anomalie) {
		this.anomalie = anomalie;
	}

	public int getID_Attivita() {
		return ID_Attivita;
	}

	public void setID_Attivita(int iD_Attivita) {
		ID_Attivita = iD_Attivita;
	}
	
	
	
}
