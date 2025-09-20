package gui;

import java.awt.EventQueue;
import java.sql.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.net.URL;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import gui.Login;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JComboBox;
import controller.ControllerReg;
import java.awt.Scrollbar;
import java.awt.Font;
@SuppressWarnings("unused")
public class registraUtente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldUsername;
	private JPasswordField FieldPassword;
	private String username;
	
	private JButton buttonRegistra;
	private JPasswordField passwordField;
	private JLabel lblConfermaPassword;
	private JComboBox<String> comboBox; 
	private JLabel lblRuolo;
	private JButton reg;
	private JLabel CF;
	private JTextField CodFfield;
	private JTextField name;
	private JTextField surname;
	private JLabel cognomelbl;
	private JLabel lblNome;
	private JButton btnback;

	public registraUtente() {
		setTitle("Login Schede");
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setBounds(100, 100, 842, 577);
		    
		    URL imageUrl = getClass().getResource("/img/sfondo.PNG");
		    contentPane = new BackgroundPanel(imageUrl);
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    setContentPane(contentPane);

		    // Layout: 15 colonne grow e push, 15 righe grow e push
		    @SuppressWarnings("unused")
		    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
		    @SuppressWarnings("unused")
		    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

		    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow]"
		    		+ "[grow][grow][grow][grow][grow][grow][grow][grow]", 
		    		"[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][][grow][grow][grow][grow]"));
		    
		    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/logo.png"));
		    Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		    
		    lblNome = new JLabel("nome");
		    contentPane.add(lblNome, "cell 6 2,alignx center");
		    
		    name = new JTextField();
		    name.setColumns(10);
		    contentPane.add(name, "cell 7 2,growx");
		    
		    btnback = new JButton("◀");
		    btnback.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		Login login = new Login();
		    		registraUtente.this.setVisible(false);
		    		login.setVisible(true);}
		    });
		    contentPane.add(btnback, "cell 12 2");
		    
		    cognomelbl = new JLabel("cognome");
		    contentPane.add(cognomelbl, "cell 6 3,alignx center");
		    
		    surname = new JTextField();
		    surname.setColumns(10);
		    contentPane.add(surname, "cell 7 3,growx");
		    
		    lblRuolo = new JLabel("RUOLO");
		    contentPane.add(lblRuolo, "cell 6 4,alignx center");
		    
		    comboBox =  new JComboBox<>(); 
		    contentPane.add(comboBox, "cell 7 4,growx");
		    
		    comboBox.setModel(new DefaultComboBoxModel<>(
		    	    new String[] { "-- Seleziona ruolo --",
		    	    				"Proprietario", 
		    	    				"Coltivatore" }
		    	));
		    
		    CF = new JLabel("Codice Fiscale");
		    contentPane.add(CF, "cell 6 5,alignx center");
		    
		    CodFfield = new JTextField();
		    CodFfield.setColumns(10);
		    contentPane.add(CodFfield, "cell 7 5,growx");
		    
		    JLabel LabelUsername = new JLabel("Username");
		    contentPane.add(LabelUsername, "cell 6 6,alignx center,aligny center");
		    
		    FieldUsername = new JTextField();
		    contentPane.add(FieldUsername, "cell 7 6,growx");
		    FieldUsername.setColumns(10);
		    
		    JLabel LabelPassword = new JLabel("Password");
		    contentPane.add(LabelPassword, "cell 6 7,alignx center,aligny center");
		    
		    FieldPassword = new JPasswordField();
		    contentPane.add(FieldPassword, "cell 7 7,growx");
		    
		    lblConfermaPassword = new JLabel("conferma Password");
		    contentPane.add(lblConfermaPassword, "cell 6 8,alignx trailing");
		    
		    passwordField = new JPasswordField();
		    contentPane.add(passwordField, "cell 7 8,growx");
		    
		    reg = new JButton("salva profilo");
		    contentPane.add(reg, "cell 7 10,alignx center");	    
		   
		    reg.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		Login login = new Login();
		    		String nome = name.getText();
		    		String cognome = surname.getText();
		    		String RUOLO= (String) comboBox.getSelectedItem();
		    		//System.out.println(RUOLO);
		    		String cf = CodFfield.getText();
		    		String user = FieldUsername.getText();
		    		String pass = new String(FieldPassword.getPassword());
		    		String confermaPass = new String(passwordField.getPassword());
		    		boolean[] value  = new boolean[4];
		    		//validazioni campi
		    		if(!pass.equals(confermaPass)) {
		    			JOptionPane.showMessageDialog(registraUtente.this, "Le password non corrispondono", "Errore", JOptionPane.ERROR_MESSAGE);
		    		
		    		}else if(nome.equals(cognome)) {
		    			JOptionPane.showMessageDialog(registraUtente.this, "Nome e cognome devono essere diversi", "Errore", JOptionPane.ERROR_MESSAGE);
		    		
		    		} else if (nome.isEmpty() || cognome.isEmpty() || cf.isEmpty() || user.isEmpty() || pass.isEmpty() || confermaPass.isEmpty()|| RUOLO.equals("-- Seleziona ruolo --")) {
		    			JOptionPane.showMessageDialog(registraUtente.this, "COMPILA TUTTI I CAMPI!!!", "Errore", JOptionPane.ERROR_MESSAGE);
		    			
		    		}else if (pass.length() > 8) {
		    		     JOptionPane.showMessageDialog(contentPane,
		    		              "La password deve essere lunga al massimo 8 caratteri.",
		    		              "Errore", JOptionPane.ERROR_MESSAGE);           
		    		}else {	
		    			 ControllerReg controller = new ControllerReg();
		    			 
		    			 value= controller.registra(nome , cognome,user, pass, cf, RUOLO.toString());
		    		 }
		    		
		    		
		    		
		    	//messaggi di avviso stato registrazione	
		    	try {	
		    	if (value[0]==false && value[1]==false && value [2]==false && value[3]==true) {
		    			JOptionPane.showMessageDialog(registraUtente.this, 
				    			"USERNAME ESISTENTE ", "Errore", JOptionPane.ERROR_MESSAGE);
		    		}
		    		
		    	else if (value [0]==true && value [2]==true) {		    	
		    			JOptionPane.showMessageDialog(registraUtente.this, 
		    			"Registrazione proprietario avvenuta con successo", "ESITO POSITIVO", JOptionPane.INFORMATION_MESSAGE);
		    			dispose();			    			
		    			registraUtente.this.setVisible(false);
						login.setVisible(true);	
						
		    	}else if (value [1]==true&& value [2]==true) {
		    		JOptionPane.showMessageDialog(registraUtente.this, 
		    			"Registrazione coltivatore avvenuta con successo", "ESITO POSITIVO", JOptionPane.INFORMATION_MESSAGE);
		    		registraUtente.this.setVisible(false);
		    		login.setVisible(true);	
		    	}else if (value[2]==false) {
		    		JOptionPane.showMessageDialog(registraUtente.this, 
		    			"Registrazione non riuscita", "Errore", JOptionPane.ERROR_MESSAGE);
		    	}
		    	}catch (Exception ex) {
		    			ex.printStackTrace();
		    	}
		    	}
		    });  
	}
}


//result[0] = false; // non è proprietario
//result[1] = false; // non è coltivatore
//result[2] = false; // registrazione non riuscita

