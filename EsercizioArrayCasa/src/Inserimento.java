import java.util.Scanner;
public class Inserimento {
	
	public String[] InserisciStringhe(Scanner scanner, int numero) {
		String[] array = new String[numero];
		for(int i=0; i<numero; i++) {
			System.out.print("Inserisci la stringa: ");
			array[i] = scanner.nextLine();
		}
		
		return array;
	}
}