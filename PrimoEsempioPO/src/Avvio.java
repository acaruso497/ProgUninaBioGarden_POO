
public class Avvio {

	public static void main(String[] args) { //main
		

		Automobile auto1 = new Automobile();
		auto1.Marca="Fiat";
		auto1.Modello="Panda";
		auto1.Targa="AB123CD";
		auto1.Proprietario="Pippo";
		auto1.AnnoImmatricolazione=2010;
		
		Automobile auto2 = new Automobile();
		auto2.Marca="Volkswagen";
		auto2.Modello="Golf";
		auto2.Targa="GG999YY";
		auto2.Proprietario="Pluto";
		auto2.AnnoImmatricolazione=2024;
		
		//dopo il costruttore posso fare questo:
		Automobile auto3 = new Automobile("Honda", "X", "ACD124KD", "Antonio", 2010);
		
		
		System.out.println(auto1.CalcolaEtaVeicolo(2025));
		System.out.println(auto2.CalcolaEtaVeicolo(2025));

		auto1.StampaDettagliAutomobile(); //non è la funzione ad utilizzare il parametro, ma la variabile a chiamare la funzione;
		auto2.StampaDettagliAutomobile();
		auto3.StampaDettagliAutomobile();

	}

}
