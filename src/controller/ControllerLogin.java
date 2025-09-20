package controller;

import dao.DAO;

public class ControllerLogin {
	
public static String usernameGlobale; //variabile globale, in modo che l'username della persona loggata sia visibile nelle gui
	
	public ControllerLogin() {}


    public boolean[] login(String username, String password) {
    	usernameGlobale = username; 
    	
        boolean[] lista = new boolean[3]; // [0]=true (user e password)campiOK, [1]=proprietario, [2]=coltivatore

        if (username == null || username.trim().isEmpty()
            || password == null || password.trim().isEmpty())
        {
            lista[0] = false;            // campi non validi
            return lista;           
        }

        lista[0] = true;                  // campi ok
        
        lista[1] = DAO.authP(username, password);//proprietario
        if (lista[1]== false) {
            lista[2] = DAO.authC(username, password);//coltivatore
        }
        return lista;
    }


	public static String getUsernameGlobale() {
		return usernameGlobale;
	}


	public static void setUsernameGlobale(String usernameGlobale) {
		ControllerLogin.usernameGlobale = usernameGlobale;
	}
}
