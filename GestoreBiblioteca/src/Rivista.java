
public class Rivista extends Catalogo {
	private String settore;
	private int numero;
	
	public Rivista(int ISBNC, String titoloC, int annoC, String settoreC, int numeroC) {
		super(ISBNC, titoloC, annoC);
		settore = settoreC;
		numero = numeroC;
	}
}
