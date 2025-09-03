public class PartitaAPerdere extends PartitaNew {
	
	public PartitaAPerdere(Giocatore[] GiocatoriIn) {
		super(GiocatoriIn);
	}
	
	@Override
	public String decretaVincitore() {
		String stringaOut="";
		int[] punteggiTotali = new int[giocatori.length];
		int maxPunteggio=0;
		
		// per ogni giocatore calcolo il punteggio totale sui turni
		stringaOut += "\n";
		for (int g=0; g< giocatori.length; g++) {
			punteggiTotali[g]=0;
			for (int t = 0; t < numeroTurni; t++) {
				punteggiTotali[g] += punteggiTurno[t][g];
			}
			if (maxPunteggio < punteggiTotali[g]) {
				maxPunteggio=punteggiTotali[g];
			}
			stringaOut += "Il giocatore " + giocatori[g].getNickname() + " ha ottenuto il punteggio totale: " + punteggiTotali[g] +"\n";

		}
		
		// conta quanti giocatori hanno fatto il massimo punteggio
		int countVincitori=0;
		int indiceVincitore=-1;
		for (int g=0; g< giocatori.length; g++) {
			if (maxPunteggio==punteggiTotali[g]) {
				indiceVincitore=g;
				// salva l'indice del massimo. Se non ho parità mi evita un for
				countVincitori++;	
			}	
		}
		
		// verifica se c'è un singolo vincitore o parità
		if (countVincitori>1) {
			stringaOut += "\nPARITÀ! I vincitori a pari merito sono: \n";
			for (int g=0; g< giocatori.length; g++) {
				if (maxPunteggio==punteggiTotali[g]) {
					stringaOut += giocatori[g].getNickname() + "\n";
				}	
			}
		} else {
			stringaOut += "Il giocatore " + giocatori[indiceVincitore].getNickname() + " HA VINTO!!\n";
		}
		
		
		
		return stringaOut;
	}
	}
}