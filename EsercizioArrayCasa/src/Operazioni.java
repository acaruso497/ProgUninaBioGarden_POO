
public class Operazioni {
	
	public String Trovapiucorta(String[] array) {
		String min=array[0];
		for(String x: array) {
			if(x.length() < min.length())
				min=x;
		}
		return min;
	}
	public String Trovapiulunga(String[] array) {
		String max=array[0];
		for(String y: array) {
			if(y.length()>max.length())
				max=y;
		}
		return max;
	}
	public String Trovaordine(String[] array) {
		String prima=array[0];
		for(String z: array) {
			if(z.compareToIgnoreCase(prima)<0) {
				prima=z;
			}
		}
		return prima;
	}
	
}
