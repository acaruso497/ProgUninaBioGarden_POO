package gui;

import java.awt.EventQueue;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Attivita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldTitolo;
	private JTextField FieldDataIP;
	private JTextField FieldDataFP;
	private JTextField FieldLotto;
	private JTextField FieldDataIA;
	private JTextField FieldDataFA;

	/**


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unused")
	public Attivita() {
		setTitle("Attività");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 843, 564);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    @SuppressWarnings("unused")
		String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelTitolo = new JLabel("Titolo del progetto");
	    contentPane.add(LabelTitolo, "flowx,cell 0 0");
	    
	    JLabel LabelLotto = new JLabel("Lotto Numero");
	    contentPane.add(LabelLotto, "flowx,cell 2 0");
	    
	    JLabel LabelAttivita = new JLabel("Attività");
	    contentPane.add(LabelAttivita, "flowx,cell 10 0,alignx trailing,aligny bottom");
	    
	    JLabel LabelDataIP = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIP, "flowx,cell 0 1,alignx right");
	    
	    JLabel LabelDataFP = new JLabel("Data Fine");
	    contentPane.add(LabelDataFP, "flowx,cell 2 1,alignx right");
	    
	    FieldTitolo = new JTextField();
	    contentPane.add(FieldTitolo, "cell 0 0");
	    FieldTitolo.setColumns(10);
	    
	    FieldDataIP = new JTextField();
	    contentPane.add(FieldDataIP, "cell 0 1,alignx right");
	    FieldDataIP.setColumns(10);
	    
	    FieldDataFP = new JTextField();
	    contentPane.add(FieldDataFP, "cell 2 1,alignx right");
	    FieldDataFP.setColumns(10);
	    
	    FieldLotto = new JTextField();
	    contentPane.add(FieldLotto, "cell 2 0");
	    FieldLotto.setColumns(10);
	    
	    JComboBox<String> ComboAttivita = new JComboBox<>();
	    //Tipo di attività selezionabile
		ComboAttivita.setModel(new DefaultComboBoxModel<>(
	    	    new String[] { "-- Seleziona --",
	    	    				"Semina", 
	    	    				"Irrigazione",
	    	    				"Raccolta" }));
	    contentPane.add(ComboAttivita, "cell 9 1 5 1,growx");
	    
	    JLabel LabelDataIA = new JLabel("Data Inizio");
	    contentPane.add(LabelDataIA, "cell 8 2,alignx trailing");
	    
	    FieldDataIA = new JTextField();
	    contentPane.add(FieldDataIA, "cell 9 2,growx");
	    FieldDataIA.setColumns(10);
	    
	    JLabel LavelDataFA = new JLabel("Data Fine");
	    contentPane.add(LavelDataFA, "cell 8 3,alignx trailing");
	    
	    FieldDataFA = new JTextField();
	    contentPane.add(FieldDataFA, "cell 9 3,growx");
	    FieldDataFA.setColumns(10);
	    
	    JButton ButtonSalva = new JButton("Salva");
	    ButtonSalva.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    	}
	    });
	    contentPane.add(ButtonSalva, "cell 10 4");
	    
	    JLabel LabelDescrizione = new JLabel("Descrizione");
	    contentPane.add(LabelDescrizione, "cell 0 5");
	    
	    JTextArea TextDescrizione = new JTextArea();
	    contentPane.add(TextDescrizione, "cell 0 6 3 7,grow");
	    
	}

}
