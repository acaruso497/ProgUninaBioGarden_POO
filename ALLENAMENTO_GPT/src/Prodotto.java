//CapitoloTre
/*🧪 Esercizio 3 – Classe Prodotto
Crea una classe Prodotto con:

Attributi privati: nome, prezzo, iva (es. 0.22)

Costruttore

Metodo prezzoFinale() che restituisce il prezzo con IVA inclusa

Nel main(), crea 1 prodotto, calcola e stampa il prezzo finale.*/
public class Prodotto {
	
	private String nome;
	private double prezzo;
	private double iva=0.22;
	
	public Prodotto(String n, double p) {
		nome = n;
		prezzo = p;
	}
	
	public double PrezzoFinale(double prezzo) {
		return prezzo = prezzo + (prezzo*iva);
	}
	
	public double getPrezzo() {
		return prezzo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String toString() {
		return nome + prezzo+" ";
	}
}
