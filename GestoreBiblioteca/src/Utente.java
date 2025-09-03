
public class Utente {
	private String nome;
	private String cognome;
	private String email;
	
	public Utente(String nomeC, String cognomeC, String emailC) {
		nome=nomeC;
		cognome=cognomeC;
		email=emailC;
	}
	
	public String getNomeU() {
		return nome;
	}
	
	public String getCognomeU() {
		return cognome;
	}
	
	@Override
	public String toString() {
		return nome+" "+cognome+" "+email;
	}
}
