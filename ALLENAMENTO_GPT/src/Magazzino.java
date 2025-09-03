import java.util.Scanner;
public class Magazzino {
	private Prodotto[] prodotto;
	Scanner input = new Scanner(System.in);
	int signal=0;
	
	public Magazzino(int quanti) {
		prodotto = new Prodotto[quanti];
	}
	
	public void InserisciProdotti() {
		
		for(int i = 0; i < prodotto.length; i++) {
			System.out.println("Inserisci i dati del prodotto "+ (i+1) + ":");
			
			System.out.println("Inserire il nome del prodotto: ");
			String nome = input.nextLine();
		
			System.out.println("Inserire il prezzo del prodotto");
			double prezzo = input.nextDouble();
			input.nextLine();
		
			prodotto[i] = new Prodotto(nome,prezzo);
		}
	}
	
	public void StampaProdotto() {
		for(int i=0; i<prodotto.length; i++)
			System.out.println("Prodotti: " + prodotto[i]);
	}
	
	public String TrovaCostoso() {
		double costoso = 0;
		for(int i=0; i<prodotto.length; i++) {
			if(prodotto[i].getPrezzo() > costoso) {
				costoso = prodotto[i].getPrezzo();
				signal = i;
			}
		}
		return prodotto[signal].getNome();
	}
	
}
