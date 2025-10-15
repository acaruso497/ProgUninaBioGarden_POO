package controller;

import dao.DAO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import gui.HomePageColtivatore;
import gui.HomePageProprietario;
public class ControllerLogin {
	
public static String usernameGlobale; //variabile globale, in modo che l'username della persona loggata sia visibile nelle gui
	
	public ControllerLogin() {}

	//effettua l'autenticazione dell'utente
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
        
        //riconosce il tipo di utente
        lista[1] = DAO.authP(username, password);
        if (lista[1]== false) {
            lista[2] = DAO.authC(username, password);
        }
        return lista;
    }
    
    //controlli generici
    public void LoginResult(JFrame loginFrame, boolean[] check) {
        if (check[0]==false) {
            JOptionPane.showMessageDialog(loginFrame, "\n USERNAME E/O PASSWORD \n RISULTANO VUOTI O NULLI");
        } else if (check[0] && check[1] && !check[2]) {
            loginFrame.setVisible(false);
			HomePageProprietario homeP = new HomePageProprietario();
			homeP.setVisible(true);
            
        } else if (check[0] && !check[1] && check[2]) {
            loginFrame.setVisible(false);
            HomePageColtivatore homeC = new HomePageColtivatore();
            homeC.setVisible(true);
            
        } else if (check[0] && !check[1] && !check[2]) {
            JOptionPane.showMessageDialog(loginFrame, " Username o Password errati!! ");
        }
    	
    }

    //Restituisce l'username della persona loggata
	public static String getUsernameGlobale() {
		return usernameGlobale;
	}

	//Imposta l'username della persona loggata
	public static void setUsernameGlobale(String usernameGlobale) {
		ControllerLogin.usernameGlobale = usernameGlobale;
	}
	
	//Restituisce il Codice Fiscale del proprietario loggato
    public static String getCodiceFiscaleByUsername(String username) {
    	return DAO.getCodiceFiscaleByUsername(username);
    }
    
}
