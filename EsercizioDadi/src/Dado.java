import java.util.Random;  //importo, prendendo il nome dal manuale java, la funzione random

public class Dado {
	
	int MinimoValore;
	int MassimoValore;
	Random Generatore = new Random();
	
	//PO: definiamo il costruttore
	public Dado(int minIn, int maxIn) {
		
		if ((minIn>0) && (minIn < maxIn)){
			MinimoValore=minIn;
			MassimoValore=maxIn;
		}
		else {							//PO: se i valori non sono validi, vengono assegnati dei valori di deafault (1-6)
			MinimoValore=1;
			MassimoValore=6;
		}
		
		System.out.println("Ho appena creato un dato con minimo " +MinimoValore+ " e massimo " +MassimoValore);
	}
	
	public int lancio() {
		
		/*La classe Random ha il metodo nextInt(bound) che restituisce valori tra [0,bound]*/
		return Generatore.nextInt(MassimoValore - MinimoValore + 1) + MinimoValore;
		
	}
}
