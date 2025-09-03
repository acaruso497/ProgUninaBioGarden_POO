import java.util.Scanner;
public class main {

	public static void main(String[] args) {
		
		//CAPITOLO 2

		/*CapitoloDue prova = new CapitoloDue();
		
		int lato = prova.quadrato(4);
		
		System.out.println("Il quadrato di 4 è "+lato);
		
		boolean bool = prova.epari(3);
		
		System.out.println("3 è Pari? "+bool);
		
		String stringa = prova.inverti("Canide");
		
		System.out.println("L'inverso di Canide è "+stringa);
		
		Persona persona = new Persona(22, "Nicolò");
		
		persona.saluta();
		
		
		//CAPITOLO 3

				
		System.out.println("-----------------------------------------------------------------------");
		
		Libro libro1 = new Libro("AOE GUIDE", "Beastyqt", 500);
		Libro libro2 = new Libro("How To Beat Beasty", "MarineLord", 158);
		
		libro1.info();
		libro2.info();
		
		Studente studente1 = new Studente("Antonio", "N86127371", 18);
		Studente studente2 = new Studente("Armando", "N98273424", 28);
		
		System.out.println(studente1 + " è idoneo? " + studente1.IsIdoneo());
		System.out.println(studente2 + " è idoneo? " + studente2.IsIdoneo());
		
		Prodotto prodotto1 = new Prodotto("Motorino", 100);
		
		System.out.println("Il prezzo con l'iva aggiunta dell'oggetto " +prodotto1.getNome() + " è " + prodotto1.PrezzoFinale(prodotto1.getPrezzo()));
		
		
		//CAPITOLO 4

		
		System.out.println("-----------------------------------------------------------------------");
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Quanti numeri vuoi inserire? ");
		int quanti = input.nextInt();
		
		AnalizzatoreNumeri analisi = new AnalizzatoreNumeri(quanti);
		analisi.InserisciNumeri();
		
        System.out.println("Il numero massimo è: " + analisi.TrovaMassimo());
        System.out.println("La somma totale è: " + analisi.CalcolaSomma());

        //
        System.out.println("Quanti studenti vuoi inserire? ");
        int n = input.nextInt();
        input.nextLine();
        
        
        GestoreStudenti gestore = new GestoreStudenti(n);
        gestore.InserisciStudenti();
        gestore.StampaIdonei();
        
        //
        System.out.println("Quanti prodotti vuoi inserire? ");
        int numero = input.nextInt();
        input.nextLine();
        
        Magazzino magazzino = new Magazzino(numero);
        magazzino.InserisciProdotti();
        magazzino.StampaProdotto();
        System.out.println("Prodotto più costoso: " + magazzino.TrovaCostoso());
        
        
        input.close();*/
        
		//CAPITOLO 7

		
		System.out.println("-----------------------------------------------------------------------");
		Persona p = new Persona(21, "Luca");
		Dipendente d = new Dipendente(21, "Nicolò", 1800);
		
		p.saluta();
		d.saluta();
		
				
	}

}
