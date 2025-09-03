import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Inserimento giocatori
        System.out.print("Inserisci il nome del primo giocatore: ");
        String nome1 = input.nextLine();
        System.out.print("Inserisci il nickname del primo giocatore: ");
        String nick1 = input.nextLine();

        System.out.print("Inserisci il nome del secondo giocatore: ");
        String nome2 = input.nextLine();
        System.out.print("Inserisci il nickname del secondo giocatore: ");
        String nick2 = input.nextLine();

        Giocatore g1 = new Giocatore(nome1, nick1);
        Giocatore g2 = new Giocatore(nome2, nick2);
        Giocatore[] listaGiocatori = { g1, g2 };

        // Creazione partita
        PartitaNormale partita = new PartitaNormale(listaGiocatori);

        // Imposta turni
        System.out.print("Inserisci il numero di turni: ");
        int turni = input.nextInt();

        partita.ImpostaTurni(turni);

        // Imposta dado 1-6
        partita.ImpostaDadi(1, 6);

        // Gioca!
        partita.Gioca();

        // Stampa risultato
        String vincitore = partita.DecretaVincitore();
        System.out.println("\nRisultato: " + vincitore);

        input.close();
    }
}

