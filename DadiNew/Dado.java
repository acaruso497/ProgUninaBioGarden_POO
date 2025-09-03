package partitaADadi;
import java.util.Random;

public class Dado {
	
	int minimoValore;
	int massimoValore;
	//
	Random generatore = new Random();
	
	//OOP: DEFINIAMO IL COSTRUTTORE
	public Dado(int minIn, int maxIn) {
		if ((minIn >= 0) && (minIn < maxIn))
		{
		minimoValore=minIn;
		massimoValore=maxIn;
		} else { // assegnamo valori di default
			minimoValore=1;
			massimoValore=6;
		}
		
//		System.out.println("Ho appena creato un dado con minimo " +minimoValore +" e massimo " +massimoValore);
	}
	
	
	public int lancia() {
		// la classe Random ha il metodo  nextInt(bound) che restituisce
		// valori fra [0, bound)
		// mi devo assicurare di gestire correttamente il minimo, nel caso sia zero
		// e di riscalare i risultati quando sto partendo da un minimo > 0
		int valore = generatore.nextInt(massimoValore-minimoValore +1);
		return valore + minimoValore;
		
	}

	public int getMinimoValore(){
		return minimoValore;
	}
	
	public int getMassimoValore(){
		return massimoValore;
	}
}
