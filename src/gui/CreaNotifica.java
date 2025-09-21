package gui;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicArrowButton;
import controller.ControllerCreaN;
import controller.ControllerLogin;
import dao.DAO;
//checkData
import utils.ControlloData;
import java.util.ArrayList;
@SuppressWarnings("unused")
public class CreaNotifica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldData;
	private JTextField FieldUsernameC;
	private JTextField FieldTitolo;
	private boolean tutti;
	HomePageProprietario home;

	public CreaNotifica(HomePageProprietario home) {
			setTitle("Crea Notifica");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    setBounds(100, 100, 842, 577);
		    
		    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
		    contentPane = new BackgroundPanel(imageUrl);
		    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		    setContentPane(contentPane);

		    // Layout: 15 colonne grow e push, 15 righe grow e push
		    
		    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
		    
		    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

		    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		    
		    JLabel LabelNotifica = new JLabel("Crea La Tua Notifica!");
		    LabelNotifica.setFont(new Font("Tahoma", Font.BOLD, 17));
		    contentPane.add(LabelNotifica, "cell 0 0");
		    
		    
		    // Pulsante freccia indietro
		    BasicArrowButton ButtonIndietro = new BasicArrowButton(BasicArrowButton.WEST);
		    ButtonIndietro.setPreferredSize(new Dimension(40, 40));
		    contentPane.add(ButtonIndietro, "cell 13 0,alignx right,aligny center");
		    ButtonIndietro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					home.setVisible(true);
				}
			});
		    
		    
		    JLabel LabelData = new JLabel("Data");
		    contentPane.add(LabelData, "cell 0 2,alignx trailing");
		    
		    FieldData = new JTextField();
		    contentPane.add(FieldData, "cell 1 2,growx");
		    FieldData.setColumns(10);
		    
		    JLabel LabelUsernameC = new JLabel("Username Coltivatori");
		    contentPane.add(LabelUsernameC, "cell 0 4,alignx trailing");
		    
		    FieldUsernameC = new JTextField();
		    contentPane.add(FieldUsernameC, "cell 1 4,growx");
		    FieldUsernameC.setColumns(10);
		    
		    JCheckBox CheckTuttiColtivatori = new JCheckBox("Tutti i coltivatori");
		    contentPane.add(CheckTuttiColtivatori, "cell 1 5");
		    CheckTuttiColtivatori.setOpaque(false);
		    CheckTuttiColtivatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CheckTuttiColtivatori.isSelected()) {
					FieldUsernameC.setEditable(false);
					FieldUsernameC.setBackground(Color.LIGHT_GRAY);
					FieldUsernameC.setText("");
					tutti = true;
				}
				else {
					FieldUsernameC.setEditable(true);
					FieldUsernameC.setBackground(Color.WHITE);
					tutti = false;
				}
				}
		    });
		    
		    JLabel LabelTitolo = new JLabel("Titolo");
		    contentPane.add(LabelTitolo, "cell 0 7,alignx trailing");
		    
		    FieldTitolo = new JTextField();
		    contentPane.add(FieldTitolo, "cell 1 7,growx");
		    FieldTitolo.setColumns(10);
		    
		    JLabel LabelDescrizione = new JLabel("Descrizione");
		    contentPane.add(LabelDescrizione, "cell 0 9,alignx right");
		    
		    JTextArea TxtDescrizione = new JTextArea();
		    contentPane.add(TxtDescrizione, "cell 1 9 5 4,grow");
		    //wrapping : andare a capo automaticamente e non spezza le parole
		    TxtDescrizione.setLineWrap(true);
		    TxtDescrizione.setWrapStyleWord(true);
		    
		    JButton ButtonInviaN = new JButton("Invia Notifica");
		    contentPane.add(ButtonInviaN, "cell 13 13");
		    ButtonInviaN.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
					String DataInserita = FieldData.getText();
					String usernameC = FieldUsernameC.getText().trim();
					String titolo = FieldTitolo.getText();
					String descrizione = TxtDescrizione.getText();
					
					
					//controlli fields gui 
					if (usernameC.isEmpty() && tutti == false || titolo.isEmpty() || descrizione.isEmpty()) {
					    JOptionPane.showMessageDialog(CreaNotifica.this, "COMPILA TUTTI I CAMPI !!", "Errore", JOptionPane.ERROR_MESSAGE);
					    return; 
					} else if (tutti == false) {
			            ControllerCreaN CreaN = new ControllerCreaN();
			            boolean checkUser = CreaN.controllaUsername(usernameC); //flag per controllare l'esistenza dell'username
			            if (checkUser == false) { //se l'username non è presente nel database, stampa un errore
			                JOptionPane.showMessageDialog(CreaNotifica.this, "L'username non esiste !!", "Errore", JOptionPane.ERROR_MESSAGE);
			                return; 
			            }
			        }
					
					try {
			            LocalDate dataInserita = LocalDate.parse(DataInserita, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			            if (dataInserita.isBefore(LocalDate.now())) {
			                JOptionPane.showMessageDialog(CreaNotifica.this, "La data non può essere minore di oggi!");
			                FieldData.setBackground(Color.RED);
			                
			                return;
			            }
			        } catch (DateTimeParseException ex) {
			            JOptionPane.showMessageDialog(CreaNotifica.this, "Inserisci una data valida con formato: 'GG/MM/AAAA'");
			            FieldData.setBackground(Color.RED);
			            return;
			        }
					//controlli fields gui 
					
					ControllerCreaN CreaN = new ControllerCreaN();
					LocalDate datalocal = LocalDate.parse(DataInserita, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					Date data = Date.valueOf(datalocal);
					
					if (tutti == false) {
						CreaN.dividiUsername(usernameC, data, titolo, descrizione);
						JOptionPane.showMessageDialog(CreaNotifica.this, "Notifica inviata con successo!");
			            } else {
						CreaN.dividiUsernameTutti(ControllerLogin.getUsernameGlobale(), data, titolo, descrizione);
						JOptionPane.showMessageDialog(CreaNotifica.this, "Notifica inviata con successo!");
					}
					
					
			
			}    
		    });
	}

}
