//CapitoloSette
public class Dipendente extends Persona{

	private double stipendio;
	
	public Dipendente(int eta, String nome, double stip) {
		super(eta, nome);
		stipendio=stip;
	}
	
	@Override
	
	public void saluta() {
		System.out.println("Ciao! Sono un dipendente, mi chiamo " + nome + ", ho " + eta +" anni e percepisco uno stipendio di "+stipendio+"€");
	}
}
