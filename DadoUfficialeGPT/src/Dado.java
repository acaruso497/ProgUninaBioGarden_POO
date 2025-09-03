import java.util.*;
public class Dado {
	private int minimo;
	private int massimo;
	private Random generatore;
	
	public Dado(int min, int max) {
		if(min>0 && max>min) {
			minimo = min;
			massimo = max;
		}
		else {
			minimo = 1;
			massimo = 6;
		}
		generatore = new Random();
	}
	
	public int Lancia() {
		return  generatore.nextInt((massimo-minimo+1))+minimo;
	}
}
