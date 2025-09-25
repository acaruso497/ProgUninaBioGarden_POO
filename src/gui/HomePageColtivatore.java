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
	//no editable text field
	private JTextField FieldDataIP;
	private JTextField FieldDataFP;
	private JTextField FieldDataIA;
	private JTextField FieldDataFA;
	private JTextField FieldPosizione;
	private JTextField FieldVarieta;
	private JTextField FieldIrrigazione;
	private JTextField FieldEsperienza;
	private JTextField FieldStima;
	private JTextField FieldTipologia; 
	private JTextField lottovisualizza;
    private JTextField tipoSeminaField;
    //drop down
	private JComboBox<String> ComboProgetti;	
    private JComboBox<String> ComboAttivita;
    private List<String> tipiAttivita;
   
	
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

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][][][grow][][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][][grow][grow][][][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelBenvenuto = new JLabel("Benvenuto sei un coltivatore!");
	    contentPane.add(LabelBenvenuto, "cell 0 0");
	    //prova
	    //inzio sezione notifiche
	    
	    JToggleButton TButtonNotifiche = new JToggleButton("");
	    contentPane.add(TButtonNotifiche, "cell 16 0,alignx center,aligny center");
	    TButtonNotifiche.setBorderPainted(false);
	    TButtonNotifiche.setContentAreaFilled(false);
	    TButtonNotifiche.setFocusPainted(false);

	    
	    ControllerColtivatore ControllerC = new ControllerColtivatore();		//SISTEMA NOTIFICHE
	    
	    JTextArea TxtListaNotifiche = new JTextArea();
	    TxtListaNotifiche.setEditable(false);
	    JScrollPane scrollNotifiche = new JScrollPane(TxtListaNotifiche);
	    scrollNotifiche.setVisible(false); // Inizialmente nascosto
	    
	    JLabel LabelEsperienza = new JLabel("Esperienza");
	    contentPane.add(LabelEsperienza, "cell 7 1,alignx right");
	    
	    FieldEsperienza = new JTextField();
	    FieldEsperienza.setEditable(false);
	    contentPane.add(FieldEsperienza, "cell 8 1,growx");
	    FieldEsperienza.setColumns(10);
	    contentPane.add(scrollNotifiche, "cell 16 1 1 4,grow");
	    //popola esperienza
	    ControllerColtivatore controllerEsperienza = new ControllerColtivatore();
	    String esperienza = controllerEsperienza.getEsperienzaColtivatore(ControllerLogin.getUsernameGlobale());
	    FieldEsperienza.setText(esperienza);
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
	    FieldDataFP.setEditable(false);
	    contentPane.add(FieldDataFP, "cell 2 2,growx");
	    FieldDataFP.setColumns(10);
	    
	    ComboProgetti.addActionListener(e -> {
	        aggiornaLottoEPosizione();    
	        aggiornaCampiProgetto();      
	        popolaAttivita();
	    });
	    //data inizio e fine progetto
	    JLabel LabelStima = new JLabel("Stima raccolto");
	    contentPane.add(LabelStima, "cell 7 3,alignx right");
	    
	    FieldStima = new JTextField();
	    FieldStima.setEditable(false);
	    contentPane.add(FieldStima, "cell 8 3,growx");
	    FieldStima.setColumns(10);
	    
	    JLabel kg = new JLabel("KG");
	    contentPane.add(kg, "cell 9 3");
	    
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
	    FieldDataFA.setEditable(false);
	    contentPane.add(FieldDataFA, "cell 2 5,growx");
	    FieldDataFA.setColumns(10);
	    
	    FieldDataIA = new JTextField();
	    FieldDataIA.setEditable(false);
	    contentPane.add(FieldDataIA, "cell 0 5");
	    FieldDataIA.setColumns(10);
	    
	    JLabel LabelTipologia = new JLabel("Tipologia Coltura");
	    contentPane.add(LabelTipologia, "cell 7 6,alignx trailing");
	    
	    FieldTipologia = new JTextField();
	    FieldTipologia.setEditable(false);
	    contentPane.add(FieldTipologia, "cell 8 6 1 3,grow");
	    
	    FieldDataIP = new JTextField();
	    FieldDataIP.setEditable(false);
	    contentPane.add(FieldDataIP, "cell 0 2");
	    FieldDataIP.setColumns(10);
	    
	    JLabel lblTipoSemina = new JLabel("Tipo Semina");
	    contentPane.add(lblTipoSemina, "cell 1 7,alignx trailing");
	    
	    tipoSeminaField = new JTextField();
	    tipoSeminaField.setEditable(false);
	    tipoSeminaField.setColumns(10);
	    contentPane.add(tipoSeminaField, "cell 2 7,growx");
	    
	    JLabel LabelVarieta = new JLabel("Varietà Coltura");
	    contentPane.add(LabelVarieta, "cell 7 9,alignx trailing");
	    
	    FieldVarieta = new JTextField();
	    FieldVarieta.setEditable(false);
	    contentPane.add(FieldVarieta, "cell 8 9,growx");
	    FieldVarieta.setColumns(10);
	    
	    JLabel LabelLotti = new JLabel("Lotto assegnato");
	    contentPane.add(LabelLotti, "cell 1 10,alignx trailing");
	    
	    lottovisualizza = new JTextField();
	    lottovisualizza.setEditable(false);
	    lottovisualizza.setColumns(10);
	    contentPane.add(lottovisualizza, "cell 2 10,growx");
	    
	    JLabel LabelIrrigazione = new JLabel("Irrigazione");
	    contentPane.add(LabelIrrigazione, "cell 7 10,alignx trailing");
	    
	    
	    FieldIrrigazione = new JTextField();
	    FieldIrrigazione.setEditable(false);
	    contentPane.add(FieldIrrigazione, "cell 8 10,growx");
	    FieldIrrigazione.setColumns(10);
	    
	    JLabel LabelPosizioneLotto = new JLabel("Posizione lotto");
	    contentPane.add(LabelPosizioneLotto, "cell 1 11,alignx trailing");
	    
	    FieldPosizione = new JTextField();
	    FieldPosizione.setEditable(false);
	    contentPane.add(FieldPosizione, "cell 2 11,growx");
	    FieldPosizione.setColumns(10);
	    
	    }//costruttore
	 // funzioni gui   
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
	    String username = ControllerLogin.getUsernameGlobale();
	    
	    if (progettoSelezionato != null && !progettoSelezionato.equals("--seleziona--")) {
	        ControllerColtivatore controller = new ControllerColtivatore();
	        
	        // Date progetto
	        List<String> dateProgetto = controller.DateInizioFineP(progettoSelezionato, username);
	        if (dateProgetto != null && dateProgetto.size() >= 2) {
	            FieldDataIP.setText(dateProgetto.get(0));
	            FieldDataFP.setText(dateProgetto.get(1));
	        }
	        
	        // COLTURA E VARIETÀ 
	        String[] colturaVarieta = controller.getColturaEVarieta(username, progettoSelezionato);
	        FieldTipologia.setText(colturaVarieta[0]);
	        FieldVarieta.setText(colturaVarieta[1]);
	        
	        // Altri campi
	        FieldStima.setText(controller.getStimaRaccolto(username, progettoSelezionato));
	        FieldIrrigazione.setText(controller.getIrrigazione(username, progettoSelezionato));
	        
	    } else {
	        // Pulisci tutti i campi
	        FieldDataIP.setText("");
	        FieldDataFP.setText("");
	        FieldStima.setText("");
	        FieldTipologia.setText("");
	        FieldVarieta.setText("");
	        FieldIrrigazione.setText("");
	    }
	}
	    
	    
	private void popolaAttivita() {
	    String progettoSelezionato = (String) ComboProgetti.getSelectedItem();
	    String username = ControllerLogin.getUsernameGlobale();
	    
	    ComboAttivita.removeAllItems();
	    ComboAttivita.addItem("--seleziona--");
	    
	    if (progettoSelezionato == null || progettoSelezionato.equals("--seleziona--")) {
	        return;
	    }
	    
	    ControllerColtivatore controller = new ControllerColtivatore();
	    
	    // CORREGGI: PASSA 2 PARAMETRI (username E progetto)
	    tipiAttivita = controller.getTipiAttivita(username, progettoSelezionato);
	    
	    for (String idSpecifico : controller.getIdAttivita(username, progettoSelezionato)) {
	        ComboAttivita.addItem(idSpecifico);
	    }
	    
	    ComboAttivita.setSelectedItem("--seleziona--");
	    ComboAttivita.addActionListener(e -> aggiornaDateAttivita());
	}
	    
	private void aggiornaDateAttivita() {
	    // Recupera l'attività selezionata dalla dropdown
	    String attivitaSelezionata = (String) ComboAttivita.getSelectedItem();
	    
	    // Verifica se è stata selezionata un'attività valida (non "--seleziona--")
	    if (attivitaSelezionata != null && !attivitaSelezionata.equals("--seleziona--")) {
	        // Calcola l'indice effettivo (tolto 1 per escludere "--seleziona--")
	        int selectedIndex = ComboAttivita.getSelectedIndex() - 1;
	        
	        // Verifica che l'indice sia valido e che la lista dei tipi esista
	        if (tipiAttivita != null && tipiAttivita.size() > selectedIndex && selectedIndex >= 0) {
	            // Recupera il tipo di attività (Semina, Irrigazione, Raccolta) dalla lista
	            String tipo = tipiAttivita.get(selectedIndex);
	            
	            // POPOLA IL TIPO DI SEMINA SE L'ATTIVITÀ È "Semina"
	            // Controlla se l'attività selezionata è di tipo Semina
	            if (tipo.equals("Semina")) {
	                // Estrai l'ID dalla stringa (es: "Semina-1" -> 1)
	                // Splitta la stringa usando il trattino come separatore
	                String[] parts = attivitaSelezionata.split("-");
	                // Verifica che ci siano almeno 2 parti (tipo e ID)
	                if (parts.length >= 2) {
	                    // Prendi la seconda parte che contiene l'ID
	                    String id = parts[1];
	                    // Gestione delle eccezioni per evitare crash
	                    try {
	                        // Crea un nuovo controller per le operazioni sul database
	                        ControllerColtivatore controller = new ControllerColtivatore();
	                        // Recupera il tipo di semina dal database usando l'ID
	                        String tipoSemina = controller.getTipoSemina(id);
	                        // Imposta il testo nel campo tipoSeminaField
	                        tipoSeminaField.setText(tipoSemina);
	                    } catch (Exception e) {
	                        // In caso di errore, pulisce il campo
	                        tipoSeminaField.setText("");
	                    }
	                }
	            } else {
	                // SE NON È SEMINA, PULISCI IL CAMPO
	                // Se l'attività non è Semina, lascia vuoto il campo
	                tipoSeminaField.setText("");
	            }
	            
	            // CONTROLLO SPLIT SICURO PER LE DATE
	            // Ripete lo split per estrarre l'ID per le date (potrebbe essere ottimizzato)
	            String[] parts = attivitaSelezionata.split("-");
	            // Verifica che ci siano almeno 2 parti
	            if (parts.length >= 2) {
	                // Prendi la seconda parte che contiene l'ID
	                String id = parts[1];
	                
	                // CONTROLLA SE L'ID È UN NUMERO VALIDO
	                try {
	                    // Verifica che l'ID sia un numero valido
	                    Integer.parseInt(id);
	                    
	                    // Crea un nuovo controller per le operazioni sul database
	                    ControllerColtivatore controller = new ControllerColtivatore();
	                    // Recupera le date dell'attività dal database
	                    String[] date = controller.getDateByAttivitaId(id);
	                    
	                    // Se le date sono state trovate, popola i campi
	                    if (date != null && date[0] != null) {
	                        // Imposta la data di inizio attività
	                        FieldDataIA.setText(date[0]);
	                        // Imposta la data di fine attività
	                        FieldDataFA.setText(date[1]);
	                    } else {
	                        // Se non ci sono date, pulisce i campi
	                        FieldDataIA.setText("");
	                        FieldDataFA.setText("");
	                    }
	                } catch (NumberFormatException e) {
	                    // Gestione errore se l'ID non è un numero valido
	                    System.err.println("ID non valido: " + id);
	                    // Pulisce i campi date
	                    FieldDataIA.setText("");
	                    FieldDataFA.setText("");
	                }
	            }
	        }
	    } else {
	        // SE NON È SELEZIONATA NESSUNA ATTIVITÀ VALIDA
	        // Pulisce tutti i campi
	        FieldDataIA.setText("");
	        FieldDataFA.setText("");
	        tipoSeminaField.setText(""); // PULISCI ANCHE IL CAMPO TIPO SEMINA
	    }
	}
	//lotto e posizione in base al progetto selezionato
	
	private void aggiornaLottoEPosizione() {
	    String progettoSelezionato = (String) ComboProgetti.getSelectedItem();
	    
	    if (progettoSelezionato != null && !progettoSelezionato.equals("--seleziona--")) {
	        ControllerColtivatore controller = new ControllerColtivatore();
	        
	        String lottoEPosizione = controller.getLottoEPosizioneByProgetto(progettoSelezionato, ControllerLogin.getUsernameGlobale());
	        
	        if (lottoEPosizione != null && !lottoEPosizione.isEmpty()) {
	            //  ESTRAI LOTTO E POSIZIONE SEPARATAMENTE
	            String[] parti = lottoEPosizione.split(", ");
	            if (parti.length >= 2) {
	                String lotto = parti[0].replace("Lotto: ", "Lotto ");
	                String posizione = parti[1].replace("Posizione: ", "");
	                
	                lottovisualizza.setText(lotto);       
	                FieldPosizione.setText(posizione);   
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