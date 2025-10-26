package code;

public class Coltura {
	//ATTRIBUTI
	private int ID_Coltura;
	private String varietà;
	private int raccoltoProdotto;
	private int min;
	private int max;
	private int avg;
	private int counter;
	
	//COSTRUTTORE
	public Coltura(int ID_Coltura, String varietà, int raccoltoProdotto, int min, int max, int avg, int counter) {
		this.ID_Coltura=ID_Coltura;
		this.varietà=varietà;
		this.raccoltoProdotto=raccoltoProdotto;
		this.min=min;
		this.max=max;
		this.avg=avg;
		this.counter=counter;
	}

	//getters e setters
	public int getID_Coltura() {
		return ID_Coltura;
	}

	public void setID_Coltura(int iD_Coltura) {
		ID_Coltura = iD_Coltura;
	}

	public String getVarietà() {
		return varietà;
	}

	public void setVarietà(String varietà) {
		this.varietà = varietà;
	}

	public int getRaccoltoProdotto() {
		return raccoltoProdotto;
	}

	public void setRaccoltoProdotto(int raccoltoProdotto) {
		this.raccoltoProdotto = raccoltoProdotto;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
}
