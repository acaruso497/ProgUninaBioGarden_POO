//CapitoloTre

/*🧪 Esercizio 1 – Classe Libro
Crea una classe Libro con:

Attributi privati: titolo, autore, numeroPagine

Un costruttore con tutti e 3 i parametri

Un metodo info() che stampa:
"Titolo: <titolo>, Autore: <autore>, Pagine: <numeroPagine>"

Nel main(), crea 2 libri e chiama info() su entrambi.*/
public class Libro {
	
	private String titolo;
	private String Autore;
	private int pagine;

	
	public Libro(String t, String a, int p) {
		titolo = t;
		Autore = a;
		pagine = p;
	}
	
	public void info() {
		System.out.println("Titolo: "+ titolo + " Autore: " + Autore + " Pagine: " + pagine);
	}
}
