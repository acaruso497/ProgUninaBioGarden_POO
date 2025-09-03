package partitaADadi;
import java.util.Scanner;

public class giocaPartitaADadiNew {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

	     // Chiedi il primo numero intero
	    System.out.print("Inserisci il numero di turni: ");
	    int numeroTurni = scanner.nextInt();
	    scanner.close();
	    
		// gli altri per ora sono hardcoded, vanno modificati
		int valoreMinimoDado=1;
		int valoreMassimoDado=6;
		//int numeroTurni=3;
		int numeroGiocatori=4;
		Giocatore[] giocIn= new Giocatore[numeroGiocatori]; 
		// OOP questa è una classe di comodo per creare nuovi oggetti fatti da noi
		
		// il numero Giocatori deve essere specificato prima
//		giocIn[0] = new Giocatore("Mara Sangiovanni", "Aram");
//		giocIn[1] = new Giocatore("Albert Einstein", "Trebla");
//		giocIn[2] = new Giocatore("Ada Lovelace", "Ada");
//		giocIn[3] = new Giocatore("Cannavacciuolo", "Cav");
				
		
		PartitaNew partita = new PartitaNew(giocIn);
		System.out.println(partita.chiGioca());
		
		// impostiamo i dettagli della partita:
		if(numeroTurni == 0)		System.out.println("Bisogna giocare almeno un turno!\n");
		partita.impostaTurni(numeroTurni); //il numero di turni
		if((minimo >= 0) && (massimo > minimo))	System.out.println("I dadi sono truccati!!!");
		partita.impostaDadi(valoreMinimoDado,valoreMassimoDado); 
		partita.gioca(); //effettua i turni
		System.out.println(partita.decretaVincitore());
			
		
	}
	

}
