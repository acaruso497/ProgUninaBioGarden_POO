import java.util.Scanner;

public class GiocaPartita {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.print("Quanti giocatori partecipano? ");
		int numeroGiocatori = input.nextInt();
		input.nextLine(); // pulizia buffer

		Giocatore[] giocatori = new Giocatore[numeroGiocatori];

		// Creazione dei giocatori
		for (int i = 0; i < numeroGiocatori; i++) {
			System.out.println("\nGiocatore " + (i + 1));
			System.out.print("Nome: ");
			String nome = input.nextLine();
			System.out.print("Nickname: ");
			String nickname = input.nextLine();
			System.out.print("Valore minimo del dado: ");
			int min = input.nextInt();
			System.out.print("Valore massimo del dado: ");
			int max = input.nextInt();
			input.nextLine(); 

			giocatori[i] = new Giocatore(nome, nickname, min, max);
		}

		// Turni di gioco
		System.out.print("\nQuanti turni vuoi giocare? ");
		int turni = input.nextInt();

		for (int t = 1; t <= turni; t++) {
			System.out.println("\n Turno " + t);
			for (int i = 0; i < numeroGiocatori; i++) {
				int valore = giocatori[i].lanciaDado();
				System.out.println(giocatori[i].getNickname() + " ha lanciato: " + valore);
			}
		}

		// Risultati finali
		System.out.println("\n RISULTATI FINALI");
		int maxPunti = -1;
		String vincitore = "";
		boolean pareggio = false;

		for (int i = 0; i < numeroGiocatori; i++) {
			giocatori[i].stampaInfo();

			if (giocatori[i].getPunteggio() > maxPunti) {
				maxPunti = giocatori[i].getPunteggio();
				vincitore = giocatori[i].getNickname();
				pareggio = false;
			} else if (giocatori[i].getPunteggio() == maxPunti) {
				pareggio = true;
			}
		}

		// Vincitore
		if (pareggio) {
			System.out.println("\n La partita è terminata in PAREGGIO!");
		} else {
			System.out.println("\n Il vincitore è: " + vincitore + " con " + maxPunti + " punti!");
		}

		input.close();
	}
}
