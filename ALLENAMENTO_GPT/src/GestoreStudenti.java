import java.util.Scanner;
public class GestoreStudenti {
	private Studente[] studenti;
	
	public GestoreStudenti(int quanti) {
		studenti = new Studente[quanti];
	}
	
	public void InserisciStudenti() {
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < studenti.length; i++) {
			System.out.println("Inserisci i dati dello studente "+ (i+1) + ":");
			
			System.out.println("Nome: ");
			String nome = input.nextLine();
			
			System.out.println("Matricola: ");
			String matricola = input.nextLine();
			
			System.out.println("Media: ");
			Float media = input.nextFloat();
			
			studenti[i] = new Studente(nome, matricola, media);
		}
	}
	
	public void StampaIdonei() {
		System.out.println("Studenti idonei (media >= 24): ");
		for(Studente s: studenti) {
			if(s.IsIdoneo())	System.out.println(s);
		}
	}
}
