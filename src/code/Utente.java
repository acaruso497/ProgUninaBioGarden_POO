package code;

public abstract class Utente {
	//ATTRIBUTI
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String userName;
	private String password;
	
	//COSTRUTTORE
	public Utente(String codiceFiscale, String nome, String cognome, String userName, String password) {
		this.codiceFiscale=codiceFiscale;
		this.nome=nome;
		this.cognome=cognome;
		this.userName=userName;
		this.password=password;
	}

	//getters e setters
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
