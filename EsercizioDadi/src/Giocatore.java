
public class Giocatore {
	
	private String nome;
	private String nickname;
	private Dado dado;
	private int punteggio;
	
	public Giocatore(String n, String nick, int min, int max) {
		nome = n;
		nickname = nick;
		dado = new Dado(min, max);
		punteggio = 0;
	}
	
	public int lanciaDado() {
		int valore = dado.lancio();
		punteggio += valore;
		return valore;
	}

	public int getPunteggio() {
		return punteggio;
	}

	public String getNome() {
		return nome;
	}

	public String getNickname() {
		return nickname;
	}

	public void stampaInfo() {
		System.out.println("Giocatore: " + nome + " (@" + nickname + ") - Punti: " + punteggio);
	}
}
