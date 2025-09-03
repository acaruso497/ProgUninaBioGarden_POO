//CapitoloTre
//CapitoloQuattro
/*🧪 Esercizio 2 – Classe Studente
Crea una classe Studente con:

Attributi privati: nome, matricola, media

Costruttore completo

Metodo isIdoneo() che restituisce true se media >= 24, altrimenti false

Nel main(), crea 2 studenti e stampa per ognuno:

"<nome> è idoneo? true/false"*/

public class Studente {
	
	private String nome;
	private String matricola;
	private float media;
	
	public Studente(String n, String mt, float m) {
		nome = n;
		matricola = mt;
		media = m;
	}
	
	public boolean IsIdoneo() {
		if(media>=24)	return true;
		else	return false;
	}
	
	@Override
	public String toString() {
		return nome;
	}
}
