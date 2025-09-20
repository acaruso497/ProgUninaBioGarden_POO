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
import controller.ControllerLogin;
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
@SuppressWarnings("unused")

public class CreaProgetto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldDataIT;
	private JTextField FieldDataFT;
	private JTextField FieldTitolo;
	HomePageProprietario home;
	Colture colture;
	
    private JComboBox<String> ComboLotto = new JComboBox<String>();  //La JComboBox è un array di stringhe per contenere i lotti
    private CreaProgettoController creaProgettoController;  
	
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
	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 3,alignx right");
	    
	    contentPane.add(ComboLotto, "cell 1 3 2 1,growx");
	    
	    JLabel LabelDataIT = new JLabel("Data Di Inizio");
	    contentPane.add(LabelDataIT, "flowx,cell 0 5,alignx trailing");
	    
	    FieldDataIT = new JTextField();
	    contentPane.add(FieldDataIT, "cell 0 5,growx");
	    FieldDataIT.setColumns(10);
	    
	    JLabel LabelDFT = new JLabel("Data Di Fine");
	    contentPane.add(LabelDFT, "flowx,cell 2 5,alignx trailing");
	    
	    FieldDataFT = new JTextField();
	    contentPane.add(FieldDataFT, "cell 2 5,growx");
	    FieldDataFT.setColumns(10);
	    	    
	    	    JLabel LabelDescrizione = new JLabel("Descrizione");
	    	    contentPane.add(LabelDescrizione, "cell 0 7");
	    	    
	    	    
	    	    	    JButton ButtonColtura = new JButton("Colture ed irrigazione");
	    	    	    contentPane.add(ButtonColtura, "cell 2 7,alignx center");
	    	    	    ButtonColtura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				colture.setVisible(true);
			}
		});
	    
	    	    
	    	    JTextArea textArea = new JTextArea();
	    	    contentPane.add(textArea, "cell 0 8 3 4,grow");
	    
	    JButton ButtonSalva = new JButton("Salva");
	    contentPane.add(ButtonSalva, "cell 0 12 3 1,alignx center");
	    ButtonSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
   	
		});
	    
        DAO dao = new DAO(); //crea il DAO
        creaProgettoController = new CreaProgettoController(dao); //crea il controller
        
        //Popola la ComboLotto con i lotti del proprietario loggato
        popolaComboLotto();
	}
	
	private void popolaComboLotto() { 
        String username = ControllerLogin.getUsernameGlobale(); // Usa l'username globale
        List<String> lotti = creaProgettoController.getLottiPerCombo(username);  
        for (String lotto : lotti) { 
            ComboLotto.addItem(lotto);  // Aggiunge ogni ID_Lotto alla ComboBox (es. "1", "2")
        }
        ComboLotto.setSelectedIndex(-1);
    }
}
	
