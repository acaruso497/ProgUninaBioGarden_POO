
public class Libro extends Catalogo {
	private String genere;
	
	public Libro(int ISBNC, String titoloC, int annoC, String genereC) {
		super(ISBNC, titoloC, annoC);
		genere = genereC;
		
	}
}
