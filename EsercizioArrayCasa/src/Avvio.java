import java.util.Scanner;
public class Avvio {

	public static void main(String[] args) {
		Scanner tastiera = new Scanner(System.in);
		System.out.print("Inserisci il numero di stringhe che si vogliono inserire: ");
		int NumeroRighe = tastiera.nextInt();
		tastiera.nextLine();
		
		
		Inserimento inserimento = new Inserimento();
		String[] Stringhe = inserimento.InserisciStringhe(tastiera, NumeroRighe);
		
		Operazioni analisistringhe=new Operazioni();
		
		System.out.println("Stringa più corta: " + analisistringhe.Trovapiucorta(Stringhe));
		System.out.println("Stringa più lunga: " + analisistringhe.Trovapiulunga(Stringhe));
		System.out.println("Prima in ordine alfabetico: " + analisistringhe.Trovaordine(Stringhe));
		
	}

}
