package partitaADadi;


public class Giocatore {
	
	private Dado unDado;
	private String nominativo;
	private String nickname;
	
	public Giocatore(String nomeIn, String nicknameIn) {
		nominativo=nomeIn;
		nickname= nicknameIn;
	}
	
//	public void siPresenta() {
//		System.out.println ("Ciao io sono " + nominativo + " ma puoi chiamarmi " + nickname);
//	}
//	
	
	public void sceglieDado(int minimo, int massimo){
		unDado = new Dado(minimo,massimo);
	}

	public int lanciaDado() {
		int punteggio = unDado.lancia();
		return punteggio;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getNominativo() {
		return nominativo;
	}
	
	public int[] getValoriDado() {
		int [] valori = new int[2];
		valori[0]=unDado.getMinimoValore();
		valori[1]=unDado.getMassimoValore();
		return valori;
	}
}
