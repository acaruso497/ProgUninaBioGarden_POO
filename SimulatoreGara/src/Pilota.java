
public class Pilota {
	private String nome;
	private Macchina macchina;
	
	public Pilota(String n, Macchina m) {
		nome = n;
		macchina = m;
	}
	
	public String getNome() {
		return nome;
	}
	
	public Macchina getMacchina() {
		return macchina;
	}
}
