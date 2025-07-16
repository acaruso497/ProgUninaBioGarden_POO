package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class VisualizzaProgetti extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	HomePageProprietario home;

	
	public VisualizzaProgetti(HomePageProprietario home) {
		this.home = home;
		
		setTitle("Visualizza Progetti");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 963, 690);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelVisualizza = new JLabel("Visualizza i tuoi progetti!");
	    contentPane.add(LabelVisualizza, "cell 0 0");
	    
	    
	    
	    // Pulsante freccia indietro
	    BasicArrowButton ButtonIndietro = new BasicArrowButton(BasicArrowButton.WEST);
	    ButtonIndietro.setPreferredSize(new Dimension(40, 40));
	    contentPane.add(ButtonIndietro, "cell 12 0,alignx right,aligny center");
	    ButtonIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				home.setVisible(true);
			}
		});

	    
	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 2,alignx trailing");
	    
	    JComboBox ComboLotto = new JComboBox();
	    contentPane.add(ComboLotto, "cell 1 2,growx");
	    ComboLotto.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelProgetto = new JLabel("Progetto");
	    contentPane.add(LabelProgetto, "cell 0 4,alignx trailing");
	    
	    JComboBox ComboProgetto = new JComboBox();
	    contentPane.add(ComboProgetto, "cell 1 4,growx");
	    ComboProgetto.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelColtivatori = new JLabel("Coltivatori");
	    contentPane.add(LabelColtivatori, "cell 0 6,alignx trailing");
	    
	    JComboBox ComboColtivatori = new JComboBox();
	    contentPane.add(ComboColtivatori, "cell 1 6,growx");
	    ComboColtivatori.setPreferredSize(new Dimension(150, 20));
	    
	    JLabel LabelAttivita = new JLabel("Attività");
	    contentPane.add(LabelAttivita, "cell 0 8,alignx trailing");
	    
	    JComboBox ComboAttivita = new JComboBox();
	    contentPane.add(ComboAttivita, "cell 1 8,growx");
	    ComboAttivita.setPreferredSize(new Dimension(150, 20));
	    
	    
	    
	    JRadioButton RadioPianificata = new JRadioButton("Pianficata");
	    contentPane.add(RadioPianificata, "cell 3 8");
	    RadioPianificata.setOpaque(false);
	    
	    JRadioButton RadioInCorso = new JRadioButton("In Corso");
	    contentPane.add(RadioInCorso, "cell 6 8");
	    RadioInCorso.setOpaque(false);
	    
	    JRadioButton RadioCompletata = new JRadioButton("Completata");
	    contentPane.add(RadioCompletata, "cell 9 8");
	    RadioCompletata.setOpaque(false);
	    
	    ButtonGroup gruppoStato = new ButtonGroup();
	    gruppoStato.add(RadioPianificata);
	    gruppoStato.add(RadioInCorso);
	    gruppoStato.add(RadioCompletata);
	    
	    
	    
	    JButton ButtonSalva = new JButton("Salva");
	    contentPane.add(ButtonSalva, "cell 6 9");
	    
	    
	    
	    JButton ButtonGrafici = new JButton("Visualizza Grafici");
	    contentPane.add(ButtonGrafici, "cell 1 12");
	    ButtonGrafici.setPreferredSize(new Dimension(150, 20));
	}

}
