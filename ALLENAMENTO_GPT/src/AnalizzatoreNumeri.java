//CapitoloQuattro
import java.util.Scanner;
public class AnalizzatoreNumeri {
	
	private int[] numeri;
	
	public AnalizzatoreNumeri(int quanti) {
		numeri = new int[quanti];
	}
	
	public void InserisciNumeri() {
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < numeri.length; i++) {
            System.out.print("Inserisci il numero " + (i + 1) + ": ");
            numeri[i] = input.nextInt();
		}
	}
	
	public int TrovaMassimo() {
		int max = 0;
		for(int i = 0; i < numeri.length; i++) {
			if(numeri[i]>max) max = numeri[i];
		}
		return max;
	}
	
	public int CalcolaSomma() {
		int somma = 0;
		for(int i = 0; i < numeri.length; i++) {
			somma+=numeri[i];
		}
		return somma;
	}
}
