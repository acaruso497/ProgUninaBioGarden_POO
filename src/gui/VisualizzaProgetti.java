package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import controller.ControllerVisualizzaP;
import dao.daoVisualizzaP;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JTextField;

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
	String username;
	private ControllerVisualizzaP controller; 
    private daoVisualizzaP dao; 

    JComboBox<String> ComboAttivita = new JComboBox<>();
    JComboBox<String> ComboProgetto = new JComboBox<>();
    JComboBox<String> ComboLotto = new JComboBox<>();
    JComboBox<String> ComboColtivatori = new JComboBox<>();
	
	public VisualizzaProgetti(HomePageProprietario home, String username) {
		this.home = home;
		this.username = username;
		
		setTitle("Visualizza Progetti");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 884, 553);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
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
	    contentPane.add(ButtonGrafici, "cell 10 2");
	    ButtonGrafici.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelDataIP = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIP, "cell 0 3,alignx right,aligny center");
	    
	    FieldDataIP = new JTextField();
	    contentPane.add(FieldDataIP, "cell 1 3,growx");
	    FieldDataIP.setColumns(10);
	    
	    JLabel LabelDataFP = new JLabel("Data Fine");
	    contentPane.add(LabelDataFP, "cell 0 4,alignx right,aligny center");
	    
	    FieldDataFP = new JTextField();
	    contentPane.add(FieldDataFP, "cell 1 4,growx");
	    FieldDataFP.setColumns(10);
	    
	    JLabel LabelColtivatori = new JLabel("Coltivatori");
	    contentPane.add(LabelColtivatori, "cell 9 4,alignx trailing");
	    
	    ButtonGroup gruppoStato = new ButtonGroup();
	    
	    
	    contentPane.add(ComboColtivatori, "cell 10 4,growx");
	    ComboColtivatori.setPreferredSize(new Dimension(150, 20));
	    
	    JButton ButtonSalvaProgetto = new JButton("Salva");
	    contentPane.add(ButtonSalvaProgetto, "cell 1 5,alignx center");
	    
	    JLabel LabelStima = new JLabel("Stima raccolto");
	    contentPane.add(LabelStima, "cell 9 6,alignx trailing");
	    
	    FieldStima = new JTextField();
	    contentPane.add(FieldStima, "cell 10 6,growx");
	    FieldStima.setColumns(10);
	    
	    JLabel LabelEffettivo = new JLabel("Raccolto effettivo");
	    contentPane.add(LabelEffettivo, "cell 9 7,alignx trailing");
	    
	    FieldEffettivo = new JTextField();
	    contentPane.add(FieldEffettivo, "cell 10 7,growx");
	    FieldEffettivo.setColumns(10);
	    
	    	    
	    	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 8,alignx trailing");
	    
	    
	    contentPane.add(ComboLotto, "cell 1 8,growx");
	    ComboLotto.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelAttivita = new JLabel("Attività");
	    contentPane.add(LabelAttivita, "cell 0 10,alignx trailing");
	    
	    
	    contentPane.add(ComboAttivita, "cell 1 10,growx");
	    ComboAttivita.setPreferredSize(new Dimension(150, 20));
	    
	    
	    JRadioButton RadioPianificata = new JRadioButton("Pianificata");
	    contentPane.add(RadioPianificata, "cell 3 10");
	    RadioPianificata.setOpaque(false);
	    gruppoStato.add(RadioPianificata);
	    
	    JRadioButton RadioInCorso = new JRadioButton("In Corso");
	    contentPane.add(RadioInCorso, "cell 6 10");
	    RadioInCorso.setOpaque(false);
	    gruppoStato.add(RadioInCorso);
	    
	    JRadioButton RadioCompletata = new JRadioButton("Completata");
	    contentPane.add(RadioCompletata, "cell 9 10");
	    RadioCompletata.setOpaque(false);
	    gruppoStato.add(RadioCompletata);
	    
	    
	    JLabel LabelDataIA = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIA, "cell 0 11,alignx trailing");
	    
	    FieldDataIA = new JTextField();
	    contentPane.add(FieldDataIA, "cell 1 11,growx");
	    FieldDataIA.setColumns(10);
	    
	    
	    
	    JButton ButtonSalvaAttivita = new JButton("Salva");
	    contentPane.add(ButtonSalvaAttivita, "cell 6 11");
	    
	    JLabel LabelDataFA = new JLabel("Data Fine");
	    contentPane.add(LabelDataFA, "cell 0 12,alignx trailing");
	    
	    FieldDataFA = new JTextField();
	    contentPane.add(FieldDataFA, "cell 1 12,growx");
	    FieldDataFA.setColumns(10);
	    
	    dao = new daoVisualizzaP();
        controller = new ControllerVisualizzaP(dao);
	    
        popolaComboProgetto();
        popolaComboLotto();
        
        ComboProgetto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedProgetto = (String) ComboProgetto.getSelectedItem();
                if (selectedProgetto != null && !selectedProgetto.isEmpty()) {
                    // Popola ComboColtivatori direttamente
                    ComboColtivatori.removeAllItems();
                    List<String> coltivatori = controller.getColtivatoriByProgetto(selectedProgetto);
                    for (String coltivatore : coltivatori) {
                        ComboColtivatori.addItem(coltivatore);
                    }
                    

                    // Popola ComboAttivita direttamente
                    ComboAttivita.removeAllItems();
                    List<String> attivita = controller.getAttivitaByProgetto(selectedProgetto);
                    for (String attivitaId : attivita) {
                        ComboAttivita.addItem(attivitaId);
                    }
                    ComboAttivita.setSelectedIndex(-1);

                    // Popola i campi progetto
                    controller.popolaDatiProgetto(selectedProgetto, FieldStima, FieldEffettivo, FieldDataIP, FieldDataFP);
                }
            }
        });
        
	}
	
	 // Popola ComboProgetto 
    private void popolaComboProgetto() {
        List<String> progetti = controller.getProgettiByProprietario(username);
        for (String idProgetto : progetti) {
            ComboProgetto.addItem(idProgetto); // Solo ID numerico (es. "1")
        }
        ComboProgetto.setSelectedIndex(-1);
    }

    // Popola ComboLotto 
    private void popolaComboLotto() {
        List<String> lotti = controller.getLottiByProprietario(username);
        for (String lotto : lotti) {
            ComboLotto.addItem(lotto);
        }
        ComboLotto.setSelectedIndex(-1);
    }
	

}


