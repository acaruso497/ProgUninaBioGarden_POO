import java.util.Scanner;
public class Gara {
	private Pilota[] piloti;
	private int numerogiri;
	private int[][] velocitagiri;
	
	
	public Gara(Pilota[] p) {
		piloti=p;
	}
	
	public void ImpostaGiri(int n) {
		if(n<1)	numerogiri = 1;
		else	numerogiri=n;
		
		velocitagiri = new int[n][piloti.length];
	}
	
	public void InserisciVelocita() {
		Scanner input = new Scanner(System.in);
		
		for(int i=0; i<numerogiri; i++) {
			System.out.println("=== Giro "+ (i +1) + " ===");
			for(int j=0; j<piloti.length; j++) {
				System.out.println("Inserisci la velocità per il pilota " + piloti[j].getNome() + ": ");
				velocitagiri[i][j] = input.nextInt();
			}
		}
	}
	
	public void DecretaVincitore() {
		int[] totali = new int[piloti.length];
		int max = 0;
		
		for(int i=0; i<piloti.length; i++) {
			for(int j=0; j<numerogiri; j++) {
				//totali //
			}
		}
	}
}
