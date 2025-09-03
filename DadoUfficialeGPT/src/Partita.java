
public class Partita {
	private Giocatore[] giocatori;
	private int numeroturni = 1;
	private int[][] punteggiot;
	
	public Partita(Giocatore[] g) {
		giocatori = g;
	}
	
	public void ImpostaTurni(int n) {
		if(n>0) numeroturni = n;
		else numeroturni = 1;
		
		punteggiot = new int[n][giocatori.length];
	}
	
	public void ImpostaDadi(int min, int max) {
		for(int i=0; i<giocatori.length;i++) {
			giocatori[i].AssegnaDado(new Dado(min,max));
		}
	}
	
	public void Gioca() {
		for(int i = 0; i<numeroturni; i++) {
			for(int j=0; j<giocatori.length; j++) {
				int punteggio = giocatori[j].LanciaDado();
				punteggiot[i][j] = punteggio;
			}
		}
	}
	
	public String DecretaVincitore() {
		return "x";
	}
	
	public Giocatore[] GetGiocatore() {
		return giocatori;
	}
	
	public int GetNumeroTurni() {
		return numeroturni;
	}
	
	public int[][] GetPunteggio() {
		return punteggiot;
	}
}
