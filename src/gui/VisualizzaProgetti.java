package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import controller.ControllerLogin;
import controller.ControllerVisualizzaP;
import dao.daoVisualizzaP;
import net.miginfocom.swing.MigLayout;
import utils.ControlloData;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;
import javax.swing.JTextField;
@SuppressWarnings("unused")
public class VisualizzaProgetti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	HomePageProprietario home;
	private JTextField FieldStima;
	private JTextField FieldEffettivo;
	private JTextField FieldDataIP;
	private JTextField FieldDataFP;
	private JTextField FieldDataIA;
	private JTextField FieldDataFA;
	private ControllerVisualizzaP controller; 
    private daoVisualizzaP dao; 
    private String username = ControllerLogin.getUsernameGlobale();

    //combobox
    JComboBox<String> ComboAttivita = new JComboBox<>();
    JComboBox<String> ComboProgetto = new JComboBox<>();
    JComboBox<String> ComboLotto = new JComboBox<>();
    
    //stati attivita
    JRadioButton RadioPianificata = new JRadioButton("pianificata");
    JRadioButton RadioInCorso = new JRadioButton("in corso");
    JRadioButton RadioCompletata = new JRadioButton("completata");
	
	public VisualizzaProgetti(HomePageProprietario home) {
		this.home = home;
		
		//Tipo di attività selezionabile
		ComboAttivita.setModel(new DefaultComboBoxModel<>(
	    	    new String[] { "-- Seleziona --",
	    	    				"Semina", 
	    	    				"Irrigazione",
	    	    				"Raccolta" }));
		
		setTitle("Visualizza Progetti");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 884, 553);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    @SuppressWarnings("unused")
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    @SuppressWarnings("unused")
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", 
	    									"[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelVisualizza = new JLabel("Visualizza i tuoi progetti!");
	    LabelVisualizza.setFont(new Font("Tahoma", Font.BOLD, 17));
	    contentPane.add(LabelVisualizza, "cell 0 0");
	    
	    
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
	    
	    JLabel LabelProgetto = new JLabel("Progetto");
	    contentPane.add(LabelProgetto, "cell 0 2,alignx trailing");
	    
	    
	    contentPane.add(ComboProgetto, "cell 1 2,growx");
	    ComboProgetto.setPreferredSize(new Dimension(150, 20));
	
	 
	    
	    JButton ButtonGrafici = new JButton("Visualizza Grafici");
	    ButtonGrafici.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    contentPane.add(ButtonGrafici, "cell 10 2");
	    ButtonGrafici.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelDataIP = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIP, "cell 0 3,alignx right,aligny center");
	    
	    FieldDataIP = new JTextField(); //field data inizio progetto
	    contentPane.add(FieldDataIP, "cell 1 3,growx");
	    FieldDataIP.setColumns(10);
	    FieldDataIP.setEditable(false); //blocca il textfield
	    
	    JLabel LabelDataFP = new JLabel("Data Fine");
	    contentPane.add(LabelDataFP, "cell 0 4,alignx right,aligny center");
	    
	    FieldDataFP = new JTextField(); //field data fine progetto
	    contentPane.add(FieldDataFP, "cell 1 4,growx");
	    FieldDataFP.setColumns(10);
	    FieldDataFP.setEditable(false); //blocca il textfield
	    
	    ButtonGroup gruppoStato = new ButtonGroup(); 
	    
	    JLabel LabelStima = new JLabel("Stima raccolto");
	    contentPane.add(LabelStima, "cell 9 6,alignx trailing");
	    
	    FieldStima = new JTextField();
	    contentPane.add(FieldStima, "cell 10 6,growx");
	    FieldStima.setColumns(10);
	    FieldStima.setEditable(false); //blocca il textfield
	    
	    JLabel LabelEffettivo = new JLabel("Raccolto effettivo");
	    contentPane.add(LabelEffettivo, "cell 9 7,alignx trailing");
	    
	    FieldEffettivo = new JTextField();
	    contentPane.add(FieldEffettivo, "cell 10 7,growx");
	    FieldEffettivo.setColumns(10);
	    FieldEffettivo.setEditable(false); //blocca il textfield
	    	    
	    	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 8,alignx trailing");
	    
	    
	    contentPane.add(ComboLotto, "cell 1 8,growx");
	    ComboLotto.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelAttivita = new JLabel("Ultima Attività");
	    contentPane.add(LabelAttivita, "cell 0 10,alignx trailing");
	    
	    
	    contentPane.add(ComboAttivita, "cell 1 10,growx");
	    ComboAttivita.setPreferredSize(new Dimension(150, 20));
	    
	    
	    
	    contentPane.add(RadioPianificata, "cell 3 10");
	    RadioPianificata.setOpaque(false);
	    gruppoStato.add(RadioPianificata);
	    
	    
	    contentPane.add(RadioInCorso, "cell 6 10");
	    RadioInCorso.setOpaque(false);
	    gruppoStato.add(RadioInCorso);
	    
	    
	    contentPane.add(RadioCompletata, "cell 9 10");
	    RadioCompletata.setOpaque(false);
	    gruppoStato.add(RadioCompletata);
	    
	    
	    
	    JLabel LabelDataIA = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIA, "cell 0 11,alignx trailing");
	    
	    FieldDataIA = new JTextField(); //field data inizio attività
	    contentPane.add(FieldDataIA, "cell 1 11,growx");
	    FieldDataIA.setColumns(10);
	    FieldDataIA.setEditable(false); //blocca il textfield
	    
	    
	    
	    JButton ButtonModificaAttivita = new JButton("Modifica");
	    ButtonModificaAttivita.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) { 
	    			String selectedProgetto = (String) ComboProgetto.getSelectedItem();
		    	    String selectedLotto = (String) ComboLotto.getSelectedItem();
		    	    String selectedAttivita = (String) ComboAttivita.getSelectedItem();
		    	    if (ComboAttivita.getSelectedItem().equals("-- Seleziona --") || selectedProgetto.isEmpty() || selectedLotto.isEmpty()) {
		                JOptionPane.showMessageDialog(VisualizzaProgetti.this, "COMPILA TUTTI I CAMPI!!", "Errore", JOptionPane.ERROR_MESSAGE);
		            } else {
		            	String selectedStato;
			    	    // setta i pallini del radiobutton
					    if (RadioPianificata.isSelected()) {
					    	  selectedStato = RadioPianificata.getText(); // Restituisce "pianificata"
					    	  controller.aggiornaStato(selectedStato, selectedAttivita, selectedLotto);
					     } else if (RadioInCorso.isSelected()) {
					    	   selectedStato = RadioInCorso.getText(); // Restituisce "in corso"
					    	   controller.aggiornaStato(selectedStato, selectedAttivita, selectedLotto);
					     } else if (RadioCompletata.isSelected()) {
					    	   selectedStato = RadioCompletata.getText(); // Restituisce "completata"
					    	   controller.aggiornaStato(selectedStato, selectedAttivita, selectedLotto);
					     } 
			    	    JOptionPane.showMessageDialog(VisualizzaProgetti.this, "Attività aggiornate con successo!");
		            }	      
	    	}
	    });
	    contentPane.add(ButtonModificaAttivita, "cell 6 11");
	    
	    JLabel LabelDataFA = new JLabel("Data Fine");
	    contentPane.add(LabelDataFA, "cell 0 12,alignx trailing");
	    
	    FieldDataFA = new JTextField(); //field data fine attività
	    contentPane.add(FieldDataFA, "cell 1 12,growx");
	    FieldDataFA.setColumns(10);
	    FieldDataFA.setEditable(false); //blocca il textfield
	    
	    dao = new daoVisualizzaP();
        controller = new ControllerVisualizzaP(dao);
	    
        popolaComboProgetto();
        popolaComboLotto();
     
        
        ComboProgetto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              try {
	                String selectedProgetto = (String) ComboProgetto.getSelectedItem();
	                controller.popolaDatiProgetto(selectedProgetto, FieldStima, FieldEffettivo, FieldDataIP, FieldDataFP); // Popola i campi progetto
                }catch (NullPointerException ex) {
            		JOptionPane.showMessageDialog(VisualizzaProgetti.this, "Seleziona un progetto valido", "Errore", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
 
          // ActionListener per ComboAttivita: recupera stato e seleziona radio button
        ComboAttivita.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              try {
            	  	String selectedAttivita = (String) ComboAttivita.getSelectedItem(); //converto il tipo di attività selezionata in una stringa
                    Object selectedProgetto = ComboProgetto.getSelectedItem(); //estraggo l'id del progetto selezionato dalla combobox
                    String idProgettoStr = selectedProgetto.toString(); //converto l'id del progetto selezionato in una stringa
                    
                    // Chiamata al controller per ottenere lo stato e popolare data inizio e data fine
		    	    String stato = controller.popolaAttivita(idProgettoStr, selectedAttivita, FieldDataIA, FieldDataFA);
		    	    
                    // il radio button corrispondente confronta lo stato nel dao e setta il pallino
                    if ("pianificata".equals(stato)) {
                        RadioPianificata.setSelected(true);
                    } else if ("in corso".equals(stato)) {
                        RadioInCorso.setSelected(true);
                    } else if ("completata".equals(stato)) {
                        RadioCompletata.setSelected(true);
                    } 
                    
                	}catch (NullPointerException ex) {
                		JOptionPane.showMessageDialog(VisualizzaProgetti.this, "Seleziona un'attività valida", "Errore", JOptionPane.ERROR_MESSAGE);
                	}
                	
                }
                  
        }); 
        
	}			
	 // Popola ComboProgetto 
    private void popolaComboProgetto() {
        List<String> progetti = controller.getProgettiByProprietario(username); // Usa usernameGlobale
        for (String idProgetto : progetti) {
            ComboProgetto.addItem(idProgetto); //popola la combobox con l'id progetto
        }
        ComboProgetto.setSelectedIndex(-1);
    }

    // Popola ComboLotto 
    private void popolaComboLotto() {
        List<String> lotti = controller.getLottiByProprietario(username); // Usa usernameGlobale
        for (String lotto : lotti) {
            ComboLotto.addItem(lotto); //popola la combobox con l'id progetto
        }
        ComboLotto.setSelectedIndex(-1);
    }
    
	

}


