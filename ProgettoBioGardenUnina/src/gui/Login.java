package gui;

import java.awt.EventQueue;
import java.sql.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import database.Connessione;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldUsername;
	private JPasswordField FieldPassword;
	private JLabel Logo;
	private JButton ButtonLogin;
	HomePageProprietario homeP = new HomePageProprietario();
	HomePageColtivatore homeC = new HomePageColtivatore();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login Schede");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setBounds(100, 100, 842, 577);
		    
		    URL imageUrl = getClass().getResource("/img/sfondo.PNG");
		    contentPane = new BackgroundPanel(imageUrl);
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    setContentPane(contentPane);

		    // Layout: 15 colonne grow e push, 15 righe grow e push
		    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
		    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

		    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		    
		    
		    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
		    Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		    Logo = new JLabel(new ImageIcon(scaledImage));
		    contentPane.add(Logo, "cell 6 3 2 1,alignx center,gapbottom 20");

		    
		    JLabel LabelUsername = new JLabel("Username");
		    contentPane.add(LabelUsername, "cell 6 6,alignx center,aligny center");
		    
		    FieldUsername = new JTextField();
		    contentPane.add(FieldUsername, "cell 7 6,growx");
		    FieldUsername.setColumns(10);
		    
		    JLabel LabelPassword = new JLabel("Password");
		    contentPane.add(LabelPassword, "cell 6 7,alignx center,aligny center");
		    
		    FieldPassword = new JPasswordField();
		    contentPane.add(FieldPassword, "cell 7 7,growx");
		    
		    ButtonLogin = new JButton("Login");
		    ButtonLogin.setPreferredSize(new Dimension(150, 20));
		    contentPane.add(ButtonLogin, "cell 6 9 2 1, alignx center");
		    ButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = FieldUsername.getText();
				String psw = FieldPassword.getText();
				
				boolean checkP = Controller.authP(username, psw);
				boolean checkC = Controller.authC(username, psw);
				
				if(checkP==true) {
					setVisible(false);
					homeP.setVisible(true); 
				} else if(checkC==true) {
					setVisible(false);
					homeC.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Credenziali errate");
				}
				

				}
		    });
		    
	}

}
