package code;

public class Lotto {
	//ATTRIBUTI
	private int ID_Lotto;
	private float metriQuadri;
	private String tipoTerreno;
	private int posizione;
	private float costoTerreno;
	
	//COSTRUTTORE
	public Lotto(int ID_Lotto, float metriQuadri, String tipoTerreno, int posizione, float costoTerreno) {
		this.ID_Lotto=ID_Lotto;
		this.metriQuadri=metriQuadri;
		this.tipoTerreno=tipoTerreno;
		this.posizione=posizione;
		this.costoTerreno=costoTerreno;
	}
}
