
public class PartitaNormale extends Partita {
	
	public PartitaNormale(Giocatore[] g) {
		super(g);
	}
	
	@Override
	public String DecretaVincitore(){
		Giocatore[] Array = GetGiocatore();
		int nturni = GetNumeroTurni();
		int[][] pturni = GetPunteggio();
		int[] totali = new int[Array.length];
		int max = 0;
		
		for(int i=0; i<Array.length; i++) {
			for(int j=0; j<nturni; j++) {
				totali[i] += pturni[j][i];
			}
			if(totali[i] > max) max=totali[i];
		}
		
		
		String vincitori= "";
		for(int i=0; i<Array.length; i++) {
			if(totali[i] == max)	vincitori += Array[i].GetNick()+" ";
		}
		
        if (vincitori.trim().contains(" ")) {
            return "Parità! Hanno vinto: " + vincitori.trim();
        } else {
            return "Ha vinto: " + vincitori.trim();
        }
	}
}
