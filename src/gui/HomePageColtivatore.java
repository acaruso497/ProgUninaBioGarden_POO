package gui;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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

@SuppressWarnings("unused")
public class HomePageColtivatore extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldDataIP;
	private JTextField FieldDataFP;
	private JTextField FiledDataIA;
	private JTextField FieldDataFA;
	private JTextField FieldPosizione;
	private JTextField FiledColtivatori;
	private JTextField FieldTipologia;
	private JTextField FieldVarieta;
	private JTextField FieldIrrigazione;
	private JTextField FieldEsperienza;
	private JTextField FieldStima;
	
	
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

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelBenvenuto = new JLabel("Benvenuto sei un coltivatore!");
	    contentPane.add(LabelBenvenuto, "cell 0 0");
	    //prova
	    //inzio sezione notifiche
	    
	    JToggleButton TButtonNotifiche = new JToggleButton("");
	    contentPane.add(TButtonNotifiche, "cell 13 0,alignx center,aligny center");
	    TButtonNotifiche.setBorderPainted(false);
	    TButtonNotifiche.setContentAreaFilled(false);
	    TButtonNotifiche.setFocusPainted(false);

	    
	    ControllerColtivatore ControllerC = new ControllerColtivatore();		//SISTEMA NOTIFICHE
	    
	    JTextArea TxtListaNotifiche = new JTextArea();
	    TxtListaNotifiche.setEditable(false);
	    JScrollPane scrollNotifiche = new JScrollPane(TxtListaNotifiche);
	    scrollNotifiche.setVisible(false); // Inizialmente nascosto
	    contentPane.add(scrollNotifiche, "cell 13 1 1 4, grow");
	    
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
	   
	    @SuppressWarnings("rawtypes")
		JComboBox ComboProgetti = new JComboBox();
	    contentPane.add(ComboProgetti, "cell 2 1,growx");
	    
	    JLabel LabelDataIP = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIP, "flowx,cell 0 2,alignx right");
	    
	    JLabel LabelDataFP = new JLabel("Data Fine");
	    contentPane.add(LabelDataFP, "flowx,cell 1 2,alignx trailing");
	    
	    FieldDataFP = new JTextField();
	    contentPane.add(FieldDataFP, "cell 2 2,growx");
	    FieldDataFP.setColumns(10);
	    
	    JLabel LabelAttivita = new JLabel("Attività");
	    contentPane.add(LabelAttivita, "cell 1 4,alignx trailing,aligny baseline");
	    
	    @SuppressWarnings("rawtypes")
		JComboBox ComboAttivita = new JComboBox();
	    contentPane.add(ComboAttivita, "cell 2 4,growx");
	    
	    JLabel LabelDataIA = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIA, "flowx,cell 0 5");
	    
	    JLabel LabelDataFA = new JLabel("Data Fine");
	    contentPane.add(LabelDataFA, "cell 1 5,alignx trailing");
	    
	    FieldDataFA = new JTextField();
	    contentPane.add(FieldDataFA, "cell 2 5,growx");
	    FieldDataFA.setColumns(10);
	    
	    JLabel LabelNotifica = new JLabel("Notifica");
	    contentPane.add(LabelNotifica, "cell 11 6");
	    
	    JLabel LabelLotti = new JLabel("Lotti assegnati");
	    contentPane.add(LabelLotti, "cell 1 7,alignx trailing");
	    @SuppressWarnings({  "rawtypes" })
	    JComboBox ComboLotti = new JComboBox();
	    contentPane.add(ComboLotti, "cell 2 7,growx");
	    
	    JLabel LabelPosizioneLotto = new JLabel("Posizione lotto");
	    contentPane.add(LabelPosizioneLotto, "cell 1 8,alignx trailing");
	    
	    FieldPosizione = new JTextField();
	    contentPane.add(FieldPosizione, "cell 2 8,growx");
	    FieldPosizione.setColumns(10);
	    
	    JLabel LabelColtivatori = new JLabel("Coltivatori");
	    contentPane.add(LabelColtivatori, "cell 1 9,alignx trailing");
	    
	    FiledColtivatori = new JTextField();
	    contentPane.add(FiledColtivatori, "cell 2 9,growx");
	    FiledColtivatori.setColumns(10);
	    
	    JLabel LabelEsperienza = new JLabel("Esperienza");
	    contentPane.add(LabelEsperienza, "cell 11 9,alignx trailing");
	    
	    FieldEsperienza = new JTextField();
	    contentPane.add(FieldEsperienza, "cell 12 9,growx");
	    FieldEsperienza.setColumns(10);
	    
	    JLabel LabelStima = new JLabel("Stima raccolto");
	    contentPane.add(LabelStima, "cell 11 10,alignx trailing");
	    
	    FieldStima = new JTextField();
	    contentPane.add(FieldStima, "cell 12 10,growx");
	    FieldStima.setColumns(10);
	    
	    JLabel LabelColtura = new JLabel("Coltura ed Irrigazione");
	    contentPane.add(LabelColtura, "cell 2 11");
	    
	    JLabel LabelTipologia = new JLabel("Tipologia");
	    contentPane.add(LabelTipologia, "cell 1 12,alignx trailing");
	    
	    FieldTipologia = new JTextField();
	    contentPane.add(FieldTipologia, "cell 2 12,growx");
	    FieldTipologia.setColumns(10);
	    
	    JLabel LabelVarieta = new JLabel("Varietà");
	    contentPane.add(LabelVarieta, "cell 1 13,alignx trailing");
	    
	    FieldVarieta = new JTextField();
	    contentPane.add(FieldVarieta, "cell 2 13,growx");
	    FieldVarieta.setColumns(10);
	    
	    JLabel LabelIrrigazione = new JLabel("Irrigazione");
	    contentPane.add(LabelIrrigazione, "cell 1 14,alignx trailing");
	    
	    FieldDataIP = new JTextField();
	    contentPane.add(FieldDataIP, "cell 0 2");
	    FieldDataIP.setColumns(10);
	    
	    FiledDataIA = new JTextField();
	    contentPane.add(FiledDataIA, "cell 0 5");
	    FiledDataIA.setColumns(10);
	    
	    FieldIrrigazione = new JTextField();
	    contentPane.add(FieldIrrigazione, "cell 2 14,growx");
	    FieldIrrigazione.setColumns(10);
	}

}