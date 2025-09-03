import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EmailBoxR;
	private JPasswordField PswBoxR;
	private JTextField UserBoxR;
	private JLabel ErroreUserR;
	private JLabel ErrorePswR;
	private JLabel ErroreMailR;
	private boolean visibile = false;
	private Login Login;
	private boolean validuser = true;
	private boolean validemail = true;
	private boolean validpsw = true;
	private GestoreUtenti gestore;

	/**
	 * Create the frame.
	 */
	public Register(GestoreUtenti gestore) {
		this.gestore = gestore;
		
		setTitle("Register");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 600, 1000));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel EmailR = new JLabel("Email ");
		EmailR.setForeground(new Color(0, 0, 0));
		EmailR.setHorizontalAlignment(SwingConstants.RIGHT);
		EmailR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		EmailR.setBounds(209, 260, 157, 19);
		contentPane.add(EmailR);
		
		EmailBoxR = new JTextField();
		EmailBoxR.setColumns(10);
		EmailBoxR.setBackground(Color.WHITE);
		EmailBoxR.setBounds(376, 260, 247, 19);
		contentPane.add(EmailBoxR);
		
		JLabel HaiAccount = new JLabel("Hai già un account?");
		HaiAccount.setForeground(new Color(0, 0, 0));
		HaiAccount.setBounds(411, 503, 146, 13);
		contentPane.add(HaiAccount);
		
		JButton Loggati = new JButton("Accedi!");
		Loggati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login = new Login(gestore);
				Register.this.setVisible(false);
				Login.setVisible(true);
			}
		});
		Loggati.setOpaque(false);
		Loggati.setForeground(Color.BLUE);
		Loggati.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Loggati.setFocusPainted(false);
		Loggati.setContentAreaFilled(false);
		Loggati.setBorderPainted(false);
		Loggati.setBounds(495, 497, 111, 22);
		contentPane.add(Loggati);
		
		JButton RegisterButton = new JButton("REGISTER");
		RegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGrafica();
			}
		});
		RegisterButton.setFocusPainted(false);
		RegisterButton.setBounds(440, 465, 111, 21);
		contentPane.add(RegisterButton);
		
		JButton MostraPswR = new JButton("");
		MostraPswR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibile = !visibile;
				if(visibile) PswBoxR.setEchoChar((char) 0);
				else	PswBoxR.setEchoChar('•');
			}
		});
		MostraPswR.setRequestFocusEnabled(false);
		MostraPswR.setIcon(new ImageIcon("C:\\Users\\info\\Downloads\\6086018-anteprima-mostra-interfaccia-icona-gratuito-vettoriale (2).jpg"));
		MostraPswR.setBounds(621, 392, 26, 19);
		contentPane.add(MostraPswR);
		
		PswBoxR = new JPasswordField();
		PswBoxR.setBounds(376, 392, 247, 19);
		contentPane.add(PswBoxR);
		
		UserBoxR = new JTextField();
		UserBoxR.setColumns(10);
		UserBoxR.setBackground(Color.WHITE);
		UserBoxR.setBounds(376, 326, 247, 19);
		contentPane.add(UserBoxR);
		
		JLabel UserR = new JLabel("Username ");
		UserR.setForeground(new Color(0, 0, 0));
		UserR.setHorizontalAlignment(SwingConstants.RIGHT);
		UserR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		UserR.setBounds(209, 326, 157, 19);
		contentPane.add(UserR);
		
		JLabel PswR = new JLabel("Password ");
		PswR.setForeground(new Color(0, 0, 0));
		PswR.setHorizontalAlignment(SwingConstants.RIGHT);
		PswR.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PswR.setBounds(233, 392, 133, 19);
		contentPane.add(PswR);
		
		ErroreMailR = new JLabel("Devi inserire una email");
		ErroreMailR.setForeground(new Color(255, 0, 0));
		ErroreMailR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		ErroreMailR.setBounds(376, 290, 290, 14);
		contentPane.add(ErroreMailR);
		ErroreMailR.setVisible(false);
		
		ErroreUserR = new JLabel("Devi inserire un username");
		ErroreUserR.setForeground(new Color(255, 0, 0));
		ErroreUserR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		ErroreUserR.setBounds(376, 356, 271, 14);
		contentPane.add(ErroreUserR);
		ErroreUserR.setVisible(false);
		
		ErrorePswR = new JLabel("Devi inserire una password");
		ErrorePswR.setForeground(new Color(255, 0, 0));
		ErrorePswR.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		ErrorePswR.setBounds(376, 422, 418, 14);
		contentPane.add(ErrorePswR);
		
		JLabel sfondoR = new JLabel("");
		sfondoR.setIcon(new ImageIcon("C:\\EclipseVerse\\Credenziali\\Credenziali\\src\\CR.png"));
		sfondoR.setBounds(0, 0, 984, 561);
		contentPane.add(sfondoR);
		ErrorePswR.setVisible(false);
	}
	
	public void RegisterGrafica() {
		String email = EmailBoxR.getText();
		String username = UserBoxR.getText();
		String psw = PswBoxR.getText();
		
		
	    if (username.length() == 0) {
	        ErroreUserR.setVisible(true);
	        UserBoxR.setBackground(new Color(255, 128, 128)); 
	        validuser = false;
	    } else {
	        ErroreUserR.setVisible(false);
	        UserBoxR.setBackground(new Color(255, 255, 255));
	        validuser = true;
	    }

	    if(email.length()!= 0 && !email.contains("@")) {
	    	ErroreMailR.setText("Email non valida");
	    	ErroreMailR.setVisible(true);
	    	EmailBoxR.setBackground(new Color(255, 128, 128)); 
	    	validemail = false;
	    }
	    else  if (email.length() == 0) {
	        ErroreMailR.setVisible(true);
	        EmailBoxR.setBackground(new Color(255, 128, 128)); 
	        validemail = false;
	    } else {
	        ErroreMailR.setVisible(false);
	        EmailBoxR.setBackground(new Color(255, 255, 255)); 
	        validemail = true;
	    }
	    
	    
	    if (psw.length() == 0) {
	        ErrorePswR.setText("Devi inserire una password");
	        ErrorePswR.setVisible(true);
	        PswBoxR.setBackground(new Color(255, 128, 128)); 
	        validpsw = false;
	    }
	    else if (psw.length() < 8) {
	        ErrorePswR.setText("La password deve essere lunga almeno 8 caratteri");
	        ErrorePswR.setVisible(true);
	        PswBoxR.setBackground(new Color(255, 128, 128));
	        validpsw = false;
	    }
	    else if (!psw.matches(".*[A-Z].*")) {
	        ErrorePswR.setText("La password deve contenere almeno una lettera maiuscola");
	        ErrorePswR.setVisible(true);
	        PswBoxR.setBackground(new Color(255, 128, 128));
	        validpsw = false;
	    }
	    else if (!psw.matches(".*[0-9].*")) {
	        ErrorePswR.setText("La password deve contenere almeno un numero");
	        ErrorePswR.setVisible(true);
	        PswBoxR.setBackground(new Color(255, 128, 128));
	        validpsw = false;
	    }
	    else if (!psw.matches(".*[^a-zA-Z0-9].*")) {
	        ErrorePswR.setText("La password deve contenere un carattere speciale (!,*,?, ...)");
	        ErrorePswR.setVisible(true);
	        PswBoxR.setBackground(new Color(255, 128, 128));
	        validpsw = false;
	    }
	    else {
	        ErrorePswR.setVisible(false);
	        PswBoxR.setBackground(new Color(255, 255, 255));
	        validpsw = true;
	    }
	    
	    if(validuser && validemail && validpsw) {
	    	JOptionPane.showMessageDialog(this, "Registrato con successo!");
	    	Utente utente = new Utente(username, psw, email);
	    	gestore.aggiungiUtente(utente);
	    }

	}
}
