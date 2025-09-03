package partitaADadi;

public class PartitaNew {
	
	// impostazioni di default
	private int numeroTurni=1;
	// per versione generale
	private Giocatore[] giocatori;
	
	// ogni array ha un elemento che 
	// è a sua volta un array
	private int[][] punteggiTurno;
	
	// IL COSTRUTTORE È cambiato
	public PartitaNew(Giocatore[] giocatoriIn) {
		giocatori=giocatoriIn;
	}
		
	
	//OOP: impareremo poi a fare metodi specifici per impostare e  prelevare i valori di variabili di classe 
	public void impostaTurni(int numTurniIn) {
		if (numTurniIn > 0) {
			numeroTurni=numTurniIn;
		} else {
			numeroTurni=1;
		}
		// OOP: impareremo poi ad usare tecniche più sofisticate
		// array con numero turni elementi [][][]
		// in ognuno di questi elemnti metto un array
		// di dimensione numero giocatori
		punteggiTurno = new int[numeroTurni][];
		for (int t = 0; t < numTurniIn; t++) {
			punteggiTurno[t]=new int[giocatori.length];
			for (int g = 0; g < giocatori.length; g++) {
				punteggiTurno[t][g]=0;
			}
		}
	}
	
	public void impostaDadi(int minimo, int massimo) {
		if ((minimo >= 0) && (massimo > minimo))  {
			for (int g = 0; g < giocatori.length; g++) {
				giocatori[g].sceglieDado(minimo, massimo);
			}
		} else
		//OOP: qui andrebbe lanciato una eccezione. Vedremo poi come si fa
		//{System.out.println("I dadi sono truccati!!!");}
	}
		
	public String chiGioca() {
		String chiSono="In questa partita giocano: ";
		for (int g = 0; g < giocatori.length; g++) {
			chiSono += giocatori[g].getNickname() + ", ";
		}
		return chiSono;
	
	}
	
	// lo svolgimento della partita
	public void gioca() {
		
		for (int turno=0;turno < numeroTurni; turno++) {
			System.out.println("Turno " + (turno +1) + ": " );
			for (int g=0;g< giocatori.length; g++) {
			
			int punteggio = giocatori[g].lanciaDado();
			System.out.println("Il giocatore " + giocatori[g].getNickname() +" ha tirato: " + punteggio);
	
			punteggiTurno[turno][g]=punteggio;
			}
		}
	}

	// OOP: impareremo poi ad usare tecniche più sofisticate
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
	
	public int getPunteggioTurno(int turno, int giocatore) {
	    return punteggiTurno[turno][giocatore];
	}

	public void setPunteggioTurno(int turno, int giocatore, int punteggio) {
	    punteggiTurno[turno][giocatore] = punteggio;
	}
	
}
