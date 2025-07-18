package code;

public class Coltura {
	//ATTRIBUTI
	private int ID_Coltura;
	private String varietà;
	private String tipo;
	private int tempi_maturazione;
	private int frequenza_irrigazione;
	private String periodo_semina;
	
	//COSTRUTTORE
	public Coltura(int ID_Coltura, String varietà, String tipo, int tempi_maturazione, int frequenza_irrigazione, String periodo_semina) {
		this.ID_Coltura=ID_Coltura;
		this.varietà=varietà;
		this.tipo=tipo;
		this.tempi_maturazione=tempi_maturazione;
		this.frequenza_irrigazione=frequenza_irrigazione;
		this.periodo_semina=periodo_semina;
	}
}
