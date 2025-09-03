
public class Catalogo {
	private int ISBN;
	private String titolo;
	private int anno;
	private boolean disponibile;
	
	public Catalogo(int ISBNC, String titoloC, int annoC) {
		ISBN=ISBNC;
		titolo=titoloC;
		anno=annoC;
		disponibile = true;
	}
	
	public Catalogo() {
		
	}
	
	public int getISBN() {
		return ISBN;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public int getAnno() {
		return anno;
	}
	
	public void setDisponibile(boolean stato) {
		disponibile = stato;
	}
	
	public boolean IsDisponibile(Catalogo elemento) {
		if(disponibile == true) return true;
		else return false;
	}
	
}
