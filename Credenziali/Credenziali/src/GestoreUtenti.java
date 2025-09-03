import java.util.ArrayList;

public class GestoreUtenti {
	
	   private ArrayList<Utente> listaUtenti;

	   public GestoreUtenti() {
	        listaUtenti = new ArrayList<>();
	    }

	   public void aggiungiUtente(Utente u) {
	        listaUtenti.add(u);
	    }

	   public ArrayList<Utente> getUtenti() {
	        return listaUtenti;
	    }

	   public boolean utenteEsiste(String username) {
	       for (Utente u : listaUtenti) {
	           if (u.getUsername().equalsIgnoreCase(username)) {
	               return true;
	           }
	       }
	       return false;
	   }

	    public Utente cercaUtente(String username, String password) {
	        for (Utente u : listaUtenti) {
	            if (u.getUsername().equalsIgnoreCase(username) && u.getPsw().equals(password)) {
	                return u;
	            }
	        }
	        return null;
	    }
}
