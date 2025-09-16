package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import dao.DAO;
import database.Connessione;
import controller.CreaProgettoController;
import net.miginfocom.swing.MigLayout;
import utils.ControlloData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class CreaProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldStima;
	private JTextField FieldDataIT;
	private JTextField FieldDataFT;
	private JTextField FieldColtivatori;
	private JTextField FieldTitolo;
	private JTextField FieldDataIA;
	private JTextField FieldDataFA;
	HomePageProprietario home;
	Colture colture;
	
    private JComboBox<String> ComboLotto = new JComboBox<String>();  //La JComboBox è un array di stringhe per contenere i lotti
    private CreaProgettoController creaProgettoController;  
	
	public CreaProgetto(HomePageProprietario home, String username) {
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

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    colture = new Colture(this);
	    
	    JLabel LabelProgetto = new JLabel("Crea Il Tuo Progetto");
	    LabelProgetto.setFont(new Font("Tahoma", Font.BOLD, 17));
	    contentPane.add(LabelProgetto, "cell 0 0");
	    
	    // Pulsante freccia indietro
	    BasicArrowButton ButtonIndietro = new BasicArrowButton(BasicArrowButton.WEST);
	    ButtonIndietro.setPreferredSize(new Dimension(40, 40));
	    contentPane.add(ButtonIndietro, "cell 11 0,alignx right,aligny center");
	    ButtonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home.setVisible(true);
			}
		});
	    
	    JLabel LabelTitolo = new JLabel("Titolo");
	    contentPane.add(LabelTitolo, "cell 0 2,alignx trailing");
	    
	    FieldTitolo = new JTextField();
	    contentPane.add(FieldTitolo, "cell 1 2 2 1,growx");
	    FieldTitolo.setColumns(10);
	    
	    JLabel LabelStima = new JLabel("Stima Raccolto");
	    contentPane.add(LabelStima, "cell 8 2,alignx trailing");
	    
	    FieldStima = new JTextField();
	    contentPane.add(FieldStima, "cell 9 2,growx");
	    FieldStima.setColumns(10);
	    //inserimento di "kg" al campo stima
	    String stimakg = FieldStima.getText();
	    FieldStima.addFocusListener(new FocusAdapter() {
	        @Override
	        public void focusLost(FocusEvent e) {
	            String stimakg = FieldStima.getText();
	            if(!stimakg.toLowerCase().contains("kg") && !stimakg.isEmpty())
	            FieldStima.setText(stimakg + " kg");
	        }
	    });
	    
	    JLabel LabelDataIT = new JLabel("Data Di Inizio");
	    contentPane.add(LabelDataIT, "flowx,cell 0 3,alignx trailing");
	    
	    JLabel LabelDFT = new JLabel("Data Di Fine");
	    contentPane.add(LabelDFT, "flowx,cell 2 3,alignx trailing");
	    
	    JLabel LabelDescrizione = new JLabel("Descrizione");
	    contentPane.add(LabelDescrizione, "cell 8 4");
	    
	    JLabel LabelAttivita = new JLabel("Attivita");
	    contentPane.add(LabelAttivita, "cell 0 5,alignx trailing");
	    
	    JComboBox<String> ComboAttivita = new JComboBox<>();
	    contentPane.add(ComboAttivita, "cell 1 5 2 1,growx");
	    // Attività selezionabili
	    ComboAttivita.addItem("Semina");
	    ComboAttivita.addItem("Irrigazione");
	    ComboAttivita.addItem("Raccolta");
	    // Default: campo vuoto
	    ComboAttivita.setSelectedIndex(-1);

	    
	    JTextArea textArea = new JTextArea();
	    contentPane.add(textArea, "cell 8 5 4 6,grow");
	    
	    JLabel LabelDataIA = new JLabel("Data Di Inizio");
	    contentPane.add(LabelDataIA, "flowx,cell 0 6");
	    
	    JLabel LabelDataFA = new JLabel("Data Di Fine");
	    contentPane.add(LabelDataFA, "flowx,cell 2 6");
	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 8,alignx right");
	    
	    contentPane.add(ComboLotto, "cell 1 8 2 1,growx");
	    
	    JLabel LabelColtivatori = new JLabel("Coltivatori");
	    contentPane.add(LabelColtivatori, "cell 0 10,alignx trailing");
	    
	    FieldColtivatori = new JTextField();
	    contentPane.add(FieldColtivatori, "cell 1 10 2 1,growx");
	    FieldColtivatori.setColumns(10);
	    
	    JButton ButtonSalva = new JButton("Salva");
	    contentPane.add(ButtonSalva, "cell 0 12 3 1,alignx center");
	    ButtonSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String DataInseritaIT = FieldDataIT.getText();
				String DataInseritaFT = FieldDataFT.getText();
				String DataInseritaIA = FieldDataIA.getText();
				String DataInseritaFA = FieldDataFA.getText();
				//Controllo che le date di fine siano DOPO le date di inizio e che le date delle attività siano comprese nelle date del progetto, inoltre controllo che le date siano in formato "data
				if(ControlloData.isDataValida(DataInseritaIT) && ControlloData.isDataValida(DataInseritaFT) && ControlloData.isDataValida(DataInseritaIA) && ControlloData.isDataValida(DataInseritaFA)) {
					if(ControlloData.isPrimaDataMinore(DataInseritaIT, DataInseritaFT) && ControlloData.isPrimaDataMinore(DataInseritaIA, DataInseritaFA) && ControlloData.isPrimaDataMinore(DataInseritaIA, DataInseritaFT) && ControlloData.isPrimaDataMinore(DataInseritaFA, DataInseritaFT) && ControlloData.isPrimaDataMinore(DataInseritaIT, DataInseritaIA))
					{
						
						String titolo = FieldTitolo.getText();
						String descrizione = textArea.getText();
						String stimaRaccolto = FieldStima.getText();
			      
						
				        // Raccolgo la scelta dalla ComboBox
				        String tipoAttivita = (String) ComboAttivita.getSelectedItem();  // Cast semplice
			          
						String lotto = (String) ComboLotto.getSelectedItem();  
						int idLotto = Integer.parseInt(lotto); //converte l'array list di lotti in un intero per salvare l'id del lotto
			                        
			           String coltivatori = FieldColtivatori.getText();
			           
			           
			        // Chiamo il metodo che salva il progetto
			             boolean checkPr = DAO.creaP(titolo, descrizione, stimaRaccolto, DataInseritaIT, DataInseritaFT, tipoAttivita, DataInseritaIA, DataInseritaFA, coltivatori, idLotto); 
						
						JOptionPane.showMessageDialog(null, "Progetto creato con successo!");
					}
					else
						JOptionPane.showMessageDialog(null, "L'ordine delle date è errato!");				
				}
				else
					JOptionPane.showMessageDialog(null, "Il formato delle date deve essere 'GG/MM/AAAA'"); 
			}
   	
		});


	    JButton ButtonColtura = new JButton("Colture ed irrigazione");
	    contentPane.add(ButtonColtura, "cell 11 12");
	    ButtonColtura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				colture.setVisible(true);
			}
		});
	     
	    FieldDataIT = new JTextField();
	    contentPane.add(FieldDataIT, "cell 0 3,growx");
	    FieldDataIT.setColumns(10);
	    
	    FieldDataFT = new JTextField();
	    contentPane.add(FieldDataFT, "cell 2 3,growx");
	    FieldDataFT.setColumns(10);
	    
	    FieldDataIA = new JTextField();
	    contentPane.add(FieldDataIA, "cell 0 6");
	    FieldDataIA.setColumns(10);
	    
	    FieldDataFA = new JTextField();
	    contentPane.add(FieldDataFA, "cell 2 6");
	    FieldDataFA.setColumns(10);
	    
        DAO dao = new DAO(); //crea il DAO
        creaProgettoController = new CreaProgettoController(dao); //crea il controller
        
        //Popola la ComboLotto con i lotti del proprietario loggato
        popolaComboLotto(username);
	}
        private void popolaComboLotto(String username) { 
            List<String> lotti = creaProgettoController.getLottiPerCombo(username);  // GUI chiama Controller
            for (String lotto : lotti) { 
                ComboLotto.addItem(lotto);  // Aggiunge ogni ID_Lotto alla ComboBox (es. "1", "2")
            }
	}
	
}
