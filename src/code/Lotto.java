package code;

public class Lotto {
	//ATTRIBUTI
	private int ID_Lotto;
	private float metriQuadri;
	private String tipoTerreno;
	private int posizione;
	private float costoTerreno;
	private String codiceFiscalePr;
	
	//COSTRUTTORE
	public Lotto(int ID_Lotto, float metriQuadri, String tipoTerreno, int posizione, float costoTerreno, String codiceFiscalePr) {
		this.ID_Lotto=ID_Lotto;
		this.metriQuadri=metriQuadri;
		this.tipoTerreno=tipoTerreno;
		this.posizione=posizione;
		this.costoTerreno=costoTerreno;
		this.codiceFiscalePr=codiceFiscalePr;
	}
	
	//getters e setters
	public int getID_Lotto() {
		return ID_Lotto;
	}

	public void setID_Lotto(int iD_Lotto) {
		ID_Lotto = iD_Lotto;
	}

	public float getMetriQuadri() {
		return metriQuadri;
	}

	public void setMetriQuadri(float metriQuadri) {
		this.metriQuadri = metriQuadri;
	}

	public String getTipoTerreno() {
		return tipoTerreno;
	}

	public void setTipoTerreno(String tipoTerreno) {
		this.tipoTerreno = tipoTerreno;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public float getCostoTerreno() {
		return costoTerreno;
	}

	public void setCostoTerreno(float costoTerreno) {
		this.costoTerreno = costoTerreno;
	}

	public String getCodiceFiscalePr() {
		return codiceFiscalePr;
	}

	public void setCodiceFiscalePr(String codiceFiscalePr) {
		this.codiceFiscalePr = codiceFiscalePr;
	}
	
	
}
