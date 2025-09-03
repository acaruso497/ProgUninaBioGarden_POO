
public class Prestito {
		
	public void RegistraPrestito(Utente utente, Catalogo elemento, String dataO, String DataS) {
		if(elemento.IsDisponibile(elemento) == false)	System.out.println("Elemento non disponibile");
		else {
			if(elemento instanceof Rivista) {
				System.out.println("Prestito Registrato: '" + elemento.getTitolo() + "' (Rivista) prestata a " + utente.toString() + " dal " + dataO + " al " + DataS);
				elemento.setDisponibile(false);
			}
			if(elemento instanceof Libro) {
				System.out.println("Prestito Registrato: '" + elemento.getTitolo() + "' (Libro) prestato a " + utente.toString() + " dal " + dataO + " al " + DataS);
				elemento.setDisponibile(false);
			}
		}
		
	}

}
