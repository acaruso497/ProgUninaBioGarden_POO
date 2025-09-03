import java.util.ArrayList;

public class Utente {
	private String username;
	private String psw;
	private String email;
	
	public Utente(String u, String p, String e) {
		username = u;
		psw = p;
		email = e;
	}
	
    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return psw;
    }

    public String getEmail() {
        return email;
    }
}
