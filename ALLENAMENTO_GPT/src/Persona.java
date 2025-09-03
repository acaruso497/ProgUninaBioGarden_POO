//CapitoloDue
//“Scrivi una classe Persona con nome, età, costruttore e metodo saluta()”
public class Persona {
	
	String nome;
	int eta;
	
	public Persona() {
		
	}
	
	public Persona(int e, String n){
		nome = n;
		eta = e;
	}
	
	public void saluta() {
		System.out.println("Ciao! Sono " + nome + " e ho " + eta +" anni");
	}
}
