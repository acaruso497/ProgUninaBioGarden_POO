/*🧪 Esercizio 1 – quadrato()
Scrivi un metodo che riceve un numero intero come parametro
e restituisce il quadrato di quel numero.

🧪 Esercizio 2 – èPari()
Scrivi un metodo che riceve un numero intero
e restituisce true se è pari, altrimenti false.

🧪 Esercizio 3 – inverti()
Scrivi un metodo che riceve una String
e restituisce quella stringa invertita (al contrario).*/

public class CapitoloDue {
	
	public CapitoloDue() {
		//costruttore default
	}

	
	public int quadrato(int intero) {
		return intero*intero;
	}
	
	public boolean epari(int intero) {
		if(intero%2==0) return true;
		else return false;
	}
	
	public String inverti(String stringa) {
	    String risultato = "";
	    for (int i = stringa.length() - 1; i >= 0; i--) {
	        risultato += stringa.charAt(i);
	    }
	    return risultato;
	}

	
}
