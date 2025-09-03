
public class Giocatore {
	private String nome;
	private String nickname;
	private Dado dado;
	
	public Giocatore(String n, String nick) {
		nome=n;
		nickname=nick;
	}
	
	public void AssegnaDado(Dado d) {
		dado = d;
	}
	
	public int LanciaDado() {
		return dado.Lancia();
	}
	
	public String GetNick() {
		return nickname;
	}

}
