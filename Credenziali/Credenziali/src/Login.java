import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.GridLayout;
import java.awt.CardLayout;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Register Register;
	private JLabel UserL;
	private JTextField UserBoxL;
	private JLabel PswL;
	private JPasswordField PswBoxL;
	private JButton LoginButton;
	private JLabel SeiRegistrato;
	private JButton Registrati;
	private JLabel ErroreUsernameL;
	private JLabel ErrorePswL;
	private GestoreUtenti gestore;
	private Home home;
	private JButton mostraPswL;
	private boolean visibile;
	private JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestoreUtenti gestore = new GestoreUtenti();
					Login frame = new Login(gestore);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login(GestoreUtenti gestore) {
		this.gestore = gestore;
		home = new Home(this);
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UserL = new JLabel("Username ");
		UserL.setForeground(new Color(0, 0, 0));
		UserL.setHorizontalAlignment(SwingConstants.RIGHT);
		UserL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		UserL.setBounds(220, 267, 157, 19);
		contentPane.add(UserL);
		
		UserBoxL = new JTextField();
		UserBoxL.setColumns(10);
		UserBoxL.setBackground(Color.WHITE);
		UserBoxL.setBounds(387, 267, 247, 19);
		contentPane.add(UserBoxL);
		
		PswL = new JLabel("Password ");
		PswL.setForeground(new Color(0, 0, 0));
		PswL.setHorizontalAlignment(SwingConstants.RIGHT);
		PswL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		PswL.setBounds(244, 333, 133, 19);
		contentPane.add(PswL);
		
		PswBoxL = new JPasswordField();
		PswBoxL.setBounds(387, 333, 247, 19);
		contentPane.add(PswBoxL);
		
		LoginButton = new JButton("LOGIN");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGrafica();
			}
		});
		LoginButton.setFocusPainted(false);
		LoginButton.setBounds(463, 400, 85, 21);
		contentPane.add(LoginButton);
		
		SeiRegistrato = new JLabel("Non sei registrato?");
		SeiRegistrato.setForeground(new Color(0, 0, 0));
		SeiRegistrato.setBounds(417, 431, 146, 13);
		contentPane.add(SeiRegistrato);
		
		Registrati = new JButton("Registrati Ora!");
		Registrati.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register = new Register(gestore);
				Login.this.setVisible(false);
				Register.setVisible(true);
			}
		});
		Registrati.setOpaque(false);
		Registrati.setForeground(Color.BLUE);
		Registrati.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Registrati.setFocusPainted(false);
		Registrati.setContentAreaFilled(false);
		Registrati.setBorderPainted(false);
		Registrati.setBounds(512, 427, 111, 21);
		contentPane.add(Registrati);
		
		ErroreUsernameL = new JLabel("Devi inserire un username");
		ErroreUsernameL.setForeground(new Color(255, 0, 0));
		ErroreUsernameL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		ErroreUsernameL.setBounds(387, 297, 247, 14);
		contentPane.add(ErroreUsernameL);
		ErroreUsernameL.setVisible(false);
		
		ErrorePswL = new JLabel("Devi inserire una password");
		ErrorePswL.setForeground(new Color(255, 0, 0));
		ErrorePswL.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		ErrorePswL.setBounds(387, 363, 247, 14);
		contentPane.add(ErrorePswL);
		
		mostraPswL = new JButton("");
		mostraPswL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					visibile = !visibile;
					if(visibile) PswBoxL.setEchoChar((char) 0);
					else	PswBoxL.setEchoChar('•');
		}});
		mostraPswL.setRequestFocusEnabled(false);
		mostraPswL.setIcon(new ImageIcon("C:\\Users\\info\\Downloads\\6086018-anteprima-mostra-interfaccia-icona-gratuito-vettoriale (2).jpg"));
		mostraPswL.setBounds(634, 333, 26, 19);
		contentPane.add(mostraPswL);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\EclipseVerse\\Credenziali\\Credenziali\\src\\CR.png"));
		lblNewLabel.setBounds(0, 0, 984, 561);
		contentPane.add(lblNewLabel);
		ErrorePswL.setVisible(false);
		
	}
	
	public void LoginGrafica() {
        String username = UserBoxL.getText();
        String psw = PswBoxL.getText();

        if(username.length() == 0 && psw.length() == 0) {
            ErroreUsernameL.setVisible(true);
            ErrorePswL.setVisible(true);
            UserBoxL.setBackground(new Color(255, 128, 128));
            PswBoxL.setBackground(new Color(255, 128, 128));
        }
        else if(username.length() == 0) {
            ErrorePswL.setVisible(false);
            PswBoxL.setBackground(new Color(255, 255, 255));
            ErroreUsernameL.setVisible(true);
            UserBoxL.setBackground(new Color(255, 128, 128));
        }
        else if(psw.length() == 0) {
            ErroreUsernameL.setVisible(false);
            UserBoxL.setBackground(new Color(255, 255, 255));
            ErrorePswL.setVisible(true);
            PswBoxL.setBackground(new Color(255, 128, 128));
        }
        else {
            ErroreUsernameL.setVisible(false);
            UserBoxL.setBackground(new Color(255, 255, 255));
            ErrorePswL.setVisible(false);
            PswBoxL.setBackground(new Color(255, 255, 255));

        }
        
        Utente u = gestore.cercaUtente(username, psw);

        if (u != null && u.getPsw().equals(psw)) {
            // Login corretto
            home.SetUserName(u.getUsername());
            this.setVisible(false);
            home.setVisible(true);
        } else {
            // Login fallito
	    	JOptionPane.showMessageDialog(this, "Credenziali errate!");
        }
        
        
        
        
    }
}
