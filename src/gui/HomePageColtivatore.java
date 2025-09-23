package gui;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerColtivatore;
import controller.ControllerLogin;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

@SuppressWarnings("unused")
public class HomePageColtivatore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldDataIP;
	private JTextField FieldDataFP;
	private JTextField FieldDataIA;
	private JTextField FieldDataFA;
	private JTextField FieldPosizione;
	private JTextField FieldVarieta;
	private JTextField FieldIrrigazione;
	private JTextField FieldEsperienza;
	private JTextField FieldStima;
	private JComboBox<String> ComboProgetti;
	
    private JComboBox<String> ComboAttivita;
    private List<String> tipiAttivita;
    private JTextField lottovisualizza;
	
	public HomePageColtivatore() {
		setTitle("HomePageColtivatore");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 843, 564);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	   
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][][][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][][][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelBenvenuto = new JLabel("Benvenuto sei un coltivatore!");
	    contentPane.add(LabelBenvenuto, "cell 0 0");
	    //prova
	    //inzio sezione notifiche
	    
	    JToggleButton TButtonNotifiche = new JToggleButton("");
	    contentPane.add(TButtonNotifiche, "cell 15 0,alignx center,aligny center");
	    TButtonNotifiche.setBorderPainted(false);
	    TButtonNotifiche.setContentAreaFilled(false);
	    TButtonNotifiche.setFocusPainted(false);

	    
	    ControllerColtivatore ControllerC = new ControllerColtivatore();		//SISTEMA NOTIFICHE
	    
	    JTextArea TxtListaNotifiche = new JTextArea();
	    TxtListaNotifiche.setEditable(false);
	    JScrollPane scrollNotifiche = new JScrollPane(TxtListaNotifiche);
	    scrollNotifiche.setVisible(false); // Inizialmente nascosto
	    
	    JLabel LabelEsperienza = new JLabel("Esperienza");
	    contentPane.add(LabelEsperienza, "cell 6 1,alignx trailing");
	    
	    FieldEsperienza = new JTextField();
	    contentPane.add(FieldEsperienza, "cell 8 1,growx");
	    FieldEsperienza.setColumns(10);
	    contentPane.add(scrollNotifiche, "cell 15 1 1 4,grow");
	    
	    //CONTROLLO NOTIFICHE
	    if(!ControllerC.checknotifiche(ControllerLogin.getUsernameGlobale())) {
		    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/notifichevuote.png"));
		    Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);	//ridimensionamento immagine
		    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		    TButtonNotifiche.setIcon(scaledIcon);
	    }
	    else {
	    	TxtListaNotifiche.setText(ControllerC.mostranotifiche(ControllerLogin.getUsernameGlobale()));
	    	ControllerC.legginotifiche(ControllerLogin.getUsernameGlobale());
		    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/notifichepiene.png"));
		    Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);	//ridimensionamento immagine
		    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		    TButtonNotifiche.setIcon(scaledIcon);
	    }
	    
	    
	    TButtonNotifiche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        // Se il bottone è attivato, mostra la tendina
		        scrollNotifiche.setVisible(TButtonNotifiche.isSelected());
		        // Per ridisegnare il pannello dopo il cambio visibilità
		        contentPane.revalidate();
		        contentPane.repaint();
		        
		        ControllerColtivatore controllerColtivatore = new ControllerColtivatore();
				controllerColtivatore.legginotifiche(ControllerLogin.getUsernameGlobale());
			}
		});
	    
	    //fine sezione notifiche
	    //prova
	    JLabel LabelProgetti = new JLabel("Progetti");
	    contentPane.add(LabelProgetti, "cell 1 1,alignx trailing");
	   
	   
	    ComboProgetti = new JComboBox<>(); // Usa la variabile d'istanza
	    contentPane.add(ComboProgetti, "cell 2 1,growx");
	    dropdownProg();
	    //data inizio e fine progetto
	    JLabel LabelDataIP = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIP, "flowx,cell 0 2,alignx right");
	    
	    JLabel LabelDataFP = new JLabel("Data Fine");
	    contentPane.add(LabelDataFP, "flowx,cell 1 2,alignx trailing");
	    
	    FieldDataFP = new JTextField();
	    contentPane.add(FieldDataFP, "cell 2 2,growx");
	    FieldDataFP.setColumns(10);
	    
	    ComboProgetti.addActionListener(e -> {
	    aggiornaCampiProgetto();
	    aggiornaLottoEPosizione(); }
	    		);
	    //data inizio e fine progetto
	    JLabel LabelStima = new JLabel("Stima raccolto");
	    contentPane.add(LabelStima, "cell 6 3,alignx trailing");
	    
	    FieldStima = new JTextField();
	    contentPane.add(FieldStima, "cell 8 3,growx");
	    FieldStima.setColumns(10);
	    
	    JLabel LabelAttivita = new JLabel("Attività");
	    contentPane.add(LabelAttivita, "cell 1 4,alignx trailing,aligny baseline");
	    
	    //drop down attivita
	   
		ComboAttivita = new JComboBox();
	    contentPane.add(ComboAttivita, "cell 2 4,growx");
	    popolaAttivita() ;
	    //drop down attivita
	    
	    JLabel LabelDataIA = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIA, "flowx,cell 0 5");
	    
	    JLabel LabelDataFA = new JLabel("Data Fine");
	    contentPane.add(LabelDataFA, "cell 1 5,alignx trailing");
	    
	    FieldDataFA = new JTextField();
	    contentPane.add(FieldDataFA, "cell 2 5,growx");
	    FieldDataFA.setColumns(10);
	    
	    FieldDataIA = new JTextField();
	    contentPane.add(FieldDataIA, "cell 0 5");
	    FieldDataIA.setColumns(10);
	    
	    JLabel LabelColtura = new JLabel("Coltura ed Irrigazione");
	    contentPane.add(LabelColtura, "cell 8 5");
	    
	    JLabel LabelTipologia = new JLabel("Tipologia");
	    contentPane.add(LabelTipologia, "cell 7 6,alignx trailing");
	    
	    JTextArea FieldTipologia = new JTextArea();
	    FieldTipologia.setEditable(false);
	    contentPane.add(FieldTipologia, "cell 8 6 1 2,grow");
	    
	    JLabel LabelLotti = new JLabel("Lotti assegnato");
	    contentPane.add(LabelLotti, "cell 1 7,alignx trailing");
	    
	    lottovisualizza = new JTextField();
	    lottovisualizza.setColumns(10);
	    contentPane.add(lottovisualizza, "cell 2 7,growx");
	    
	    JLabel LabelPosizioneLotto = new JLabel("Posizione lotto");
	    contentPane.add(LabelPosizioneLotto, "cell 1 8,alignx trailing");
	    
	    FieldPosizione = new JTextField();
	    contentPane.add(FieldPosizione, "cell 2 8,growx");
	    FieldPosizione.setColumns(10);
	    
	    FieldDataIP = new JTextField();
	    contentPane.add(FieldDataIP, "cell 0 2");
	    FieldDataIP.setColumns(10);
	    
	    JLabel LabelVarieta = new JLabel("Varietà");
	    contentPane.add(LabelVarieta, "cell 7 8,alignx trailing");
	    
	    FieldVarieta = new JTextField();
	    contentPane.add(FieldVarieta, "cell 8 8,growx");
	    FieldVarieta.setColumns(10);
	    
	    JLabel LabelIrrigazione = new JLabel("Irrigazione");
	    contentPane.add(LabelIrrigazione, "cell 7 9,alignx trailing");
	    
	    
	    FieldIrrigazione = new JTextField();
	    contentPane.add(FieldIrrigazione, "cell 8 9,growx");
	    FieldIrrigazione.setColumns(10);
	    
	    }//costruttore
	    
	private void dropdownProg() {
	    ControllerColtivatore controller = new ControllerColtivatore();
	    ComboProgetti.removeAllItems();
	    ComboProgetti.addItem("--seleziona--");
	    for (String p : controller.popolaPrComboBox(ControllerLogin.getUsernameGlobale())) {
	        ComboProgetti.addItem(p);
	    }
	    ComboProgetti.setSelectedItem("--seleziona--");
	}//function
	
	
	private void aggiornaCampiProgetto() {
	    String progettoSelezionato = (String) ComboProgetti.getSelectedItem();
	    
	    //  ELIMINA: ComboProgetti.addItem("--seleziona--");
	    
	    if (progettoSelezionato != null && !progettoSelezionato.equals("--seleziona--")) {
	        ControllerColtivatore controller = new ControllerColtivatore();
	        
	        List<String> dateProgetto = controller.DateInizioFineP(progettoSelezionato, ControllerLogin.getUsernameGlobale());
	        
	        if (dateProgetto != null && dateProgetto.size() >= 2) {
	            FieldDataIP.setText(dateProgetto.get(0));  // IMPOSTA DATA INIZIO
	            FieldDataFP.setText(dateProgetto.get(1));  // IMPOSTA DATA FINE
	        } else {
	            FieldDataIP.setText("");  // PULISCE SE NON CI SONO DATE
	            FieldDataFP.setText("");  // PULISCE SE NON CI SONO DATE
	        }
	    } else {
	        //  QUANDO SI SELEZIONA "--seleziona--", PULISCE I CAMPI
	        FieldDataIP.setText("");  //  PULISCE DATA INIZIO
	        FieldDataFP.setText("");  //  PULISCE DATA FINE
	    }
	}

	private void popolaAttivita() {
	    ControllerColtivatore controller = new ControllerColtivatore();
	    ComboAttivita.removeAllItems();
	    
	    ComboAttivita.addItem("--seleziona--");
	    
	    
	    tipiAttivita = controller.getTipiAttivita(ControllerLogin.getUsernameGlobale());
	    
	    for (String idSpecifico : controller.getIdAttivita(ControllerLogin.getUsernameGlobale())) {
	        ComboAttivita.addItem(idSpecifico);
	    }
	    
	    ComboAttivita.setSelectedItem("--seleziona--");
	    
	    
	    ComboAttivita.addActionListener(e -> aggiornaDateAttivita());
	}
	    
	private void aggiornaDateAttivita() {
	    String attivitaSelezionata = (String) ComboAttivita.getSelectedItem();
	    
	    if (attivitaSelezionata != null && !attivitaSelezionata.equals("--seleziona--")) {
	        int selectedIndex = ComboAttivita.getSelectedIndex() - 1;
	        
	        if (tipiAttivita != null && tipiAttivita.size() > selectedIndex && selectedIndex >= 0) {
	            String tipo = tipiAttivita.get(selectedIndex);
	            
	            // CONTROLLO SPLIT SICURO
	            String[] parts = attivitaSelezionata.split("-");
	            if (parts.length >= 2) {
	                String id = parts[1];
	                
	                // CONTROLLA SE L'ID È UN NUMERO VALIDO
	                try {
	                    Integer.parseInt(id); // Verifica che sia un numero
	                    
	                    ControllerColtivatore controller = new ControllerColtivatore();
	                    String[] date = controller.getDateByAttivitaId(id);
	                    
	                    if (date != null && date[0] != null) {
	                        FieldDataIA.setText(date[0]);
	                        FieldDataFA.setText(date[1]);
	                    } else {
	                        FieldDataIA.setText("");
	                        FieldDataFA.setText("");
	                    }
	                } catch (NumberFormatException e) {
	                    System.err.println("ID non valido: " + id);
	                    FieldDataIA.setText("");
	                    FieldDataFA.setText("");
	                }
	            }
	        }
	    } else {
	        FieldDataIA.setText("");
	        FieldDataFA.setText("");
	    }
	}
	
	//lotto e posizione in base al progetto selezionato
	
	private void aggiornaLottoEPosizione() {
	    String progettoSelezionato = (String) ComboProgetti.getSelectedItem();
	    
	    if (progettoSelezionato != null && !progettoSelezionato.equals("--seleziona--")) {
	        ControllerColtivatore controller = new ControllerColtivatore();
	        
	        String lottoEPosizione = controller.getLottoEPosizioneByProgetto(progettoSelezionato, ControllerLogin.getUsernameGlobale());
	        
	        if (lottoEPosizione != null && !lottoEPosizione.isEmpty()) {
	            // 🎯 ESTRAI LOTTO E POSIZIONE SEPARATAMENTE
	            String[] parti = lottoEPosizione.split(", ");
	            if (parti.length >= 2) {
	                String lotto = parti[0].replace("Lotto: ", "Lotto ");
	                String posizione = parti[1].replace("Posizione: ", "");
	                
	                lottovisualizza.setText(lotto);      // "Lotto 2"
	                FieldPosizione.setText(posizione);   // "2"
	            }
	        } else {
	            lottovisualizza.setText("");
	            FieldPosizione.setText("");
	        }
	    } else {
	        lottovisualizza.setText("");
	        FieldPosizione.setText("");
	    }
	}


}//classe