package code;
enum Esperienza{
	principiante,
    intermedio,
    professionista
}

public class Coltivatore {
	//ATTRIBUTI
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String userName;
	private String password;
	private Esperienza esperienza;
	
	//COSTRUTTORE
		public Coltivatore(String codiceFiscale, String nome, String cognome, String userName, String password, Esperienza esperienza) {
			this.codiceFiscale=codiceFiscale;
			this.nome=nome;
			this.cognome=cognome;
			this.userName=userName;
			this.password=password;
			this.esperienza=esperienza;
		}
}
