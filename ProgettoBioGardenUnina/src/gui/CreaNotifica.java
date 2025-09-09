package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicArrowButton;

import dao.DAO;
//checkData
import utils.ControlloData;

public class CreaNotifica extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField FieldData;
	private JTextField FieldUsernameC;
	private JTextField FieldTitolo;
	HomePageProprietario home;

	public CreaNotifica(HomePageProprietario home) {
			setTitle("Crea Notifica");
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
		    
		    JLabel LabelNotifica = new JLabel("Crea La Tua Notifica!");
		    LabelNotifica.setFont(new Font("Tahoma", Font.BOLD, 17));
		    contentPane.add(LabelNotifica, "cell 0 0");
		    
		    
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
		    
		    
		    JLabel LabelData = new JLabel("Data");
		    contentPane.add(LabelData, "cell 0 2,alignx trailing");
		    
		    FieldData = new JTextField();
		    contentPane.add(FieldData, "cell 1 2,growx");
		    FieldData.setColumns(10);
		    
		    JLabel LabelUsernameC = new JLabel("Username Coltivatori");
		    contentPane.add(LabelUsernameC, "cell 0 4,alignx trailing");
		    
		    FieldUsernameC = new JTextField();
		    contentPane.add(FieldUsernameC, "cell 1 4,growx");
		    FieldUsernameC.setColumns(10);
		    
		    JCheckBox CheckTuttiColtivatori = new JCheckBox("Tutti i coltivatori");
		    contentPane.add(CheckTuttiColtivatori, "cell 1 5");
		    CheckTuttiColtivatori.setOpaque(false);
		    CheckTuttiColtivatori.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(CheckTuttiColtivatori.isSelected()) {
					FieldUsernameC.setEditable(false);
					FieldUsernameC.setBackground(Color.LIGHT_GRAY);
					FieldUsernameC.setText("");
				}
				else {
					FieldUsernameC.setEditable(true);
					FieldUsernameC.setBackground(Color.WHITE);
				}
				}
		    });
		    
		    JLabel LabelTitolo = new JLabel("Titolo");
		    contentPane.add(LabelTitolo, "cell 0 7,alignx trailing");
		    
		    FieldTitolo = new JTextField();
		    contentPane.add(FieldTitolo, "cell 1 7,growx");
		    FieldTitolo.setColumns(10);
		    
		    JLabel LabelDescrizione = new JLabel("Descrizione");
		    contentPane.add(LabelDescrizione, "cell 0 9,alignx right");
		    
		    JTextArea TxtDescrizione = new JTextArea();
		    contentPane.add(TxtDescrizione, "cell 1 9 5 4,grow");
		    //wrapping : andare a capo automaticamente e non spezza le parole
		    TxtDescrizione.setLineWrap(true);
		    TxtDescrizione.setWrapStyleWord(true);
		    
		    JButton ButtonInviaN = new JButton("Invia Notifica");
		    contentPane.add(ButtonInviaN, "cell 13 13");
		    ButtonInviaN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String DataInserita = FieldData.getText();
					String usernameC = FieldUsernameC.getText();
					String titolo = FieldTitolo.getText();
					String descrizione = TxtDescrizione.getText();
					
					boolean check = DAO.creaN(DataInserita, usernameC, titolo, descrizione);
					
					if(ControlloData.isDataValida(DataInserita)) {
						JOptionPane.showMessageDialog(null, "Notifica inviata con successo!");
						FieldData.setBackground(Color.WHITE);
						FieldData.setText("");
						setVisible(false);
						home.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Inserisci una data con formato: 'GG/MM/AAAA'");
						FieldData.setBackground(Color.RED);
					}
				}
		    });
	}

}
