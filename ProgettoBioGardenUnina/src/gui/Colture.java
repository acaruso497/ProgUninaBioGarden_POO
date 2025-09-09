package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Colture extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldTipologia;
	private JTextField FieldVarieta;
	private JTextField FieldProfondita;
	private JTextField FieldTipoSemina;
	CreaProgetto creaprogetto;

	public Colture(CreaProgetto creaprogetto) {
		setTitle("HomePage");
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
	    
	    // Pulsante freccia indietro
	    BasicArrowButton ButtonIndietro = new BasicArrowButton(BasicArrowButton.WEST);
	    ButtonIndietro.setPreferredSize(new Dimension(40, 40));
	    contentPane.add(ButtonIndietro, "cell 11 0,alignx right,aligny center");
	    ButtonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				creaprogetto.setVisible(true);
			}
		});
	    
	    JLabel LabelColtura = new JLabel("Colture Ed Irrigazione");
	    LabelColtura.setFont(new Font("Tahoma", Font.BOLD, 17));
	    contentPane.add(LabelColtura, "cell 0 0");
	    
	    JLabel LabelTipologia = new JLabel("Tipologia");
	    contentPane.add(LabelTipologia, "cell 0 3,alignx trailing");
	    
	    FieldTipologia = new JTextField();
	    contentPane.add(FieldTipologia, "cell 1 3,growx");
	    FieldTipologia.setColumns(10);
	    
	    JLabel LabelProfondita = new JLabel("Profondità Semina");
	    contentPane.add(LabelProfondita, "cell 6 3,alignx trailing");
	    
	    FieldProfondita = new JTextField();
	    FieldProfondita.setEditable(false);
	    contentPane.add(FieldProfondita, "cell 7 3,growx");
	    FieldProfondita.setColumns(10);
	    FieldProfondita.setText("10cm (default)");
	    
	    JLabel LabelVarieta = new JLabel("Varietà");
	    contentPane.add(LabelVarieta, "cell 0 5,alignx trailing");
	    
	    FieldVarieta = new JTextField();
	    contentPane.add(FieldVarieta, "cell 1 5,growx");
	    FieldVarieta.setColumns(10);
	    
	    JLabel LabelTipoSemina = new JLabel("Tipo Semina");
	    contentPane.add(LabelTipoSemina, "cell 6 5,alignx trailing");
	    
	    FieldTipoSemina = new JTextField();
	    contentPane.add(FieldTipoSemina, "cell 7 5,growx");
	    FieldTipoSemina.setColumns(10);
	    
	    JLabel LabelTipologiaI = new JLabel("Tipologia Irrigazione");
	    contentPane.add(LabelTipologiaI, "cell 0 9,alignx trailing");
	    
	    JComboBox<String> ComboTipoIrr = new JComboBox<>();
	    contentPane.add(ComboTipoIrr, "cell 1 9,growx");
	    // Attività selezionabili
	    ComboTipoIrr.addItem("A goccia");
	    ComboTipoIrr.addItem("A pioggia");
	    ComboTipoIrr.addItem("Per scorrimento");
	    // Default: campo vuoto
	    ComboTipoIrr.setSelectedIndex(-1);
	    
	    JButton ButtonSalva = new JButton("Salva");
	    contentPane.add(ButtonSalva, "cell 5 13");
	  
	}

}
