
public class Automobile { //nel file Avvio.java NON posso inserire un'altra classe (come questa), quindi ne creo un'altra
	
	
	//attributi
	String Marca;
	String Modello;
	String Targa;
	String Proprietario; //nome e cognome
	int AnnoImmatricolazione;
	
	
	//costruttori
	
	//costruttore di default
	public Automobile() {} //mi consente di stampare anche senza utilizzare il costruttore qui sotto
	
	public Automobile(String newMarca, String newModello, String newTarga, String newProprietario, int newAnnoImmatricolazione) {
		Marca = newMarca;
		Modello = newModello;
		Targa = newTarga;
		Proprietario = newProprietario;
		AnnoImmatricolazione = newAnnoImmatricolazione;
	}
	
	
	//metodi
	public void StampaDettagliAutomobile() {
		System.out.println(Marca + ", " + Modello + ", " + Targa + ", " + Proprietario + ", " + AnnoImmatricolazione);
	}
	
	public int CalcolaEtaVeicolo(int AnnoCorrente) {
		return AnnoCorrente - AnnoImmatricolazione;
	}
	
	
}
