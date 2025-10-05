package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import dao.DAO;
import dao.daoCreaP;
import database.Connessione;
import controller.ControllerLogin;
import controller.CreaProgettoController;
import net.miginfocom.swing.MigLayout;
import utils.ControlloData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
@SuppressWarnings("unused")

public class CreaProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldDataIP;
	private JTextField FieldDataFP;
	private JTextField FieldTitolo;
	HomePageProprietario home;
	Attivita attivita;
	
    private JComboBox<String> ComboLotto = new JComboBox<String>();  //La JComboBox è un array di stringhe per contenere i lotti
    private CreaProgettoController creaProgettoController;  
    private JTextField FieldStimaRaccolto;
    private JTextField FieldTipologiaColtura;
	
	public CreaProgetto(HomePageProprietario home) {
		this.home = home;
		setTitle("Crea Progetto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 842, 577);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][][][grow][][][][grow][grow][][grow][grow][grow][][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][][grow][][grow][][grow][grow][grow][grow][grow][grow][grow][grow]"));	    
	    JLabel LabelProgetto = new JLabel("Crea Il Tuo Progetto");
	    LabelProgetto.setFont(new Font("Tahoma", Font.BOLD, 17));
	    contentPane.add(LabelProgetto, "cell 0 0");
	    
	    // Pulsante freccia indietro
	    BasicArrowButton ButtonIndietro = new BasicArrowButton(BasicArrowButton.WEST);
	    ButtonIndietro.setPreferredSize(new Dimension(40, 40));
	    contentPane.add(ButtonIndietro, "cell 19 0,alignx right,aligny center");
	    ButtonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home.setVisible(true);
			}
		});
	    
	    JLabel LabelTitolo = new JLabel("Titolo");
	    contentPane.add(LabelTitolo, "cell 0 1,alignx trailing");
	    
	    FieldTitolo = new JTextField();
	    contentPane.add(FieldTitolo, "cell 1 1 4 1,growx");
	    FieldTitolo.setColumns(10);
	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 2,alignx right");
	    
	    contentPane.add(ComboLotto, "cell 1 2,growx");
	     
	     JLabel LabelStima = new JLabel("Stima Raccolto");
	     contentPane.add(LabelStima, "cell 0 4,alignx trailing");
	     
	     FieldStimaRaccolto = new JTextField();
	     FieldStimaRaccolto.setColumns(10);
	     contentPane.add(FieldStimaRaccolto, "cell 1 4,growx");
	     
	     JLabel kg = new JLabel("KG");
	     contentPane.add(kg, "cell 2 4");
	     
	     JLabel lblTipologiaColtura = new JLabel("Tipologia Coltura");
	     contentPane.add(lblTipologiaColtura, "cell 0 6,alignx trailing");
	     
	     FieldTipologiaColtura = new JTextField();
	     FieldTipologiaColtura.setColumns(10);
	     contentPane.add(FieldTipologiaColtura, "cell 1 6,growx");
	     
	     JLabel indicazioneUtente = new JLabel("coltura1,coltura2,coltura3...");
	     contentPane.add(indicazioneUtente, "cell 2 6,alignx left");
	     
	     JLabel date = new JLabel("GG/MM/AAAA");
	     contentPane.add(date, "cell 1 8,alignx center,aligny center");
	     
	     JLabel date_1 = new JLabel("GG/MM/AAAA");
	     contentPane.add(date_1, "cell 5 8,alignx center");
	     
	     JLabel LabelDataIP = new JLabel("Data Di Inizio");
	     contentPane.add(LabelDataIP, "flowx,cell 0 9,alignx trailing");
	     
	     FieldDataIP = new JTextField();
	     contentPane.add(FieldDataIP, "cell 1 9,growx");
	     FieldDataIP.setColumns(10);
	     
	     JLabel LabelDataFP = new JLabel("Data Di Fine");
	     contentPane.add(LabelDataFP, "cell 4 9,alignx trailing");
	     
	     FieldDataFP = new JTextField();
	     contentPane.add(FieldDataFP, "cell 5 9,growx");
	     FieldDataFP.setColumns(10);
	    	    
	     JLabel LabelDescrizione = new JLabel("Descrizione");
	     contentPane.add(LabelDescrizione, "cell 0 10");
	    
	    	    
	    JTextArea textArea = new JTextArea();
	    contentPane.add(textArea, "cell 0 11 9 4,grow");
	    
	    JButton ButtonAvanti = new JButton("Avanti");
	    contentPane.add(ButtonAvanti, "cell 4 15");
	    ButtonAvanti.setEnabled(false); //inizialmente bloccato
	    
	    JButton ButtonSalva = new JButton("Salva");
	    contentPane.add(ButtonSalva, "cell 1 15,alignx center");
	    ButtonSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titolo = FieldTitolo.getText();
				String tipoColtura = FieldTipologiaColtura.getText();
				
				String lotto = (String) ComboLotto.getSelectedItem();
				String stimaRaccolto = FieldStimaRaccolto.getText();
				String dataInizioP = FieldDataIP.getText();
				String dataFineP = FieldDataFP.getText();
				String descrizione = textArea.getText();
				
				//controlli fields gui 
				if (titolo.isEmpty() || lotto == null || stimaRaccolto.isEmpty() || tipoColtura.isEmpty() ||dataInizioP.isEmpty() ||  dataFineP.isEmpty() || descrizione.isEmpty()) {
				    JOptionPane.showMessageDialog(CreaProgetto.this, "COMPILA TUTTI I CAMPI !!", "Errore", JOptionPane.ERROR_MESSAGE);
				    return; 
				}
				
				try {
		            double stima = Double.parseDouble(stimaRaccolto);
		            if (stima <= 0) {
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La stima raccolto deve essere maggiore di zero!", "Errore", JOptionPane.ERROR_MESSAGE);
		                FieldStimaRaccolto.setBackground(Color.RED);
		                return;
		            }
		        } catch (NumberFormatException ex) {
		            JOptionPane.showMessageDialog(CreaProgetto.this, "La stima raccolto deve essere un numero valido! Es: 100 o 50.5", "Errore", JOptionPane.ERROR_MESSAGE);
		            FieldStimaRaccolto.setBackground(Color.RED);
		            return;
		        }
				
				try {
					LocalDate dataInseritaIP = LocalDate.parse(dataInizioP, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            if (dataInseritaIP.isBefore(LocalDate.now())) { //data inizio progetto < di oggi
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere minore di oggi!");
		                FieldDataIP.setBackground(Color.RED);
		                
		                return;
		            }
		            
		            LocalDate dataInseritaFP = LocalDate.parse(dataFineP, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            if (dataInseritaFP.isBefore(LocalDate.now())) { //data fine progetto < di oggi
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere minore di oggi!");
		                FieldDataFP.setBackground(Color.RED);
		                
		                return;
		            }
		            
		            if (dataInseritaFP.isBefore(dataInseritaIP)) { //data fine progetto < data inizio progetto
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere minore della data di inizio!");
		                FieldDataFP.setBackground(Color.RED);
		                
		                return;
		            }
		            
		            if (dataInseritaIP.isAfter(dataInseritaFP)) { //data inizio progetto > data inizio progetto
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere maggiore della data di fine!");
		                FieldDataIP.setBackground(Color.RED);
		                
		                return;
		            }
					
		        } catch (DateTimeParseException ex) {
		            JOptionPane.showMessageDialog(CreaProgetto.this, "Inserisci una data valida con formato: 'GG/MM/AAAA'");
		            return;
					
				}
				
				DAO dao = new DAO(); // Crea il DAO
		        creaProgettoController = new CreaProgettoController(dao); // Crea il controller
		        
				String [] creaArr=dividiPerVirgola(FieldTipologiaColtura.getText());
				
	            LocalDate datalocalIP = LocalDate.parse(dataInizioP, DateTimeFormatter.ofPattern("dd/MM/yyyy")); //converte il textfield della data inizio in tipo data di sql
				Date dataIP = Date.valueOf(datalocalIP);
				
				LocalDate datalocalFP = LocalDate.parse(dataFineP, DateTimeFormatter.ofPattern("dd/MM/yyyy")); //converte il textfield della data fine in tipo data di sql
				Date dataFP = Date.valueOf(datalocalFP);
				//aggiunta
				boolean controllo = creaProgettoController.checkColt(lotto, creaArr);
				
				if(controllo== true) {
					JOptionPane.showMessageDialog(CreaProgetto.this, "\n\nuna tra le colture inserite è gia stata piantata");
				}else {
				
			    //vado a controllare se esiste già un progetto in quel lotto
				boolean creaProgetto = creaProgettoController.creaProgetto(titolo, lotto, descrizione, stimaRaccolto, creaArr, dataIP, dataFP);
				if(creaProgetto==true) {
					JOptionPane.showMessageDialog(CreaProgetto.this, "Progetto creato con successo successo!");
					ButtonAvanti.setEnabled(true);
				}else {
					JOptionPane.showMessageDialog(CreaProgetto.this, 
				            "ERRORE: Questo lotto ha già un progetto di coltivazione esistente!", 
				            "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
				}//fine else
			}
   	
		});
	    
	    
	    ButtonAvanti.addActionListener(new ActionListener() {
	    	//Prima di passare alla prossima GUI, effettua dei controlli
	    	public void actionPerformed(ActionEvent e) {
	            String titolo = FieldTitolo.getText();
	            String lotto = (String) ComboLotto.getSelectedItem();
	            String stimaRaccolto = FieldStimaRaccolto.getText();
	            String tipoColtura = FieldTipologiaColtura.getText();
	            
	            String dataInizioP = FieldDataIP.getText();
	            String dataFineP = FieldDataFP.getText();
	            String descrizione = textArea.getText();

	            // controlla i fields gui
	            if (titolo.isEmpty() || lotto == null || stimaRaccolto.isEmpty() || dataInizioP.isEmpty() || dataFineP.isEmpty() || descrizione.isEmpty()) {
	                JOptionPane.showMessageDialog(CreaProgetto.this, "COMPILA TUTTI I CAMPI PRIMA DI PROCEDERE!", "Errore", JOptionPane.ERROR_MESSAGE);
	                return;
	            }
	            
	            
	            try {
	            	// Converte le date del progetto
					LocalDate dataInseritaIP = LocalDate.parse(dataInizioP, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            if (dataInseritaIP.isBefore(LocalDate.now())) { //data inizio progetto < di oggi
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere minore di oggi!");
		                FieldDataIP.setBackground(Color.RED);
		                
		                return;
		            }
		            
		            LocalDate dataInseritaFP = LocalDate.parse(dataFineP, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		            if (dataInseritaFP.isBefore(LocalDate.now())) { //data fine progetto < di oggi
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere minore di oggi!");
		                FieldDataFP.setBackground(Color.RED);
		                
		                return;
		            }
		            
		            if (dataInseritaFP.isBefore(dataInseritaIP)) { //data fine progetto < data inizio progetto
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere minore della data di inizio!");
		                FieldDataFP.setBackground(Color.RED);
		                
		                return;
		            }
		            
		            if (dataInseritaIP.isAfter(dataInseritaFP)) { //data inizio progetto > data inizio progetto
		                JOptionPane.showMessageDialog(CreaProgetto.this, "La data non può essere maggiore della data di fine!");
		                FieldDataIP.setBackground(Color.RED);
		                
		                return;
		            }
					
		        } catch (DateTimeParseException ex) {
		            JOptionPane.showMessageDialog(CreaProgetto.this, "Inserisci una data valida con formato: 'GG/MM/AAAA'");
		            return;
					
				}
	            
	            // Passa i field già popolati alla GUI attività salvati nelle variabili locali
	            Attivita attivita = new Attivita(titolo, lotto, stimaRaccolto, tipoColtura, dataInizioP, dataFineP, descrizione);

	            CreaProgetto.this.setVisible(false);
	            attivita.setVisible(true);
	    		
	    		
	    	
	    	}
	    });
	    
	    
	    DAO dao = new DAO(); // Crea il DAO
        creaProgettoController = new CreaProgettoController(dao); // Crea il controller
        
        popolaComboLotto();   //Popola la ComboLotto con i lotti del proprietario loggato
	}
	
	private void popolaComboLotto() { 
        String username = ControllerLogin.getUsernameGlobale(); // Usa l'username globale
        List<String> lotti = creaProgettoController.getLottiPerCombo(username);  
        for (String lotto : lotti) { 
            ComboLotto.addItem(lotto);  // Aggiunge ogni ID_Lotto alla ComboBox (es. "1", "2")
        }
        ComboLotto.setSelectedIndex(-1);
    }
	public String[] dividiPerVirgola(String input) {
	    String[] parti = input.split(",");
	    for (int i = 0; i < parti.length; i++) {
	        parti[i] = parti[i].trim();
	    }
	    System.out.println(Arrays.toString(parti));
	    return parti;
	    }
	

}
	
