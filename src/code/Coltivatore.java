package code;

//esperienza del coltivatore
enum Esperienza{
	principiante,
    intermedio,
    professionista
}

public class Coltivatore extends Utente {
	//ATTRIBUTI
	private Esperienza esperienza;
	private String username_proprietario;
	
	//COSTRUTTORE
	public Coltivatore(String codiceFiscale, String nome, String cognome, String userName, String password, Esperienza esperienza, String username_proprietario) {
			super(codiceFiscale, nome, cognome, userName, password);
			this.esperienza=esperienza;
			this.username_proprietario=username_proprietario;
	}

	//getters e setters
	public Esperienza getEsperienza() {
		return esperienza;
	}

	public void setEsperienza(Esperienza esperienza) {
		this.esperienza = esperienza;
	}

	public String getUsername_proprietario() {
		return username_proprietario;
	}

	public void setUsername_proprietario(String username_proprietario) {
		this.username_proprietario = username_proprietario;
	}
	
}
