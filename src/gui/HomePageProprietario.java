package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ControllerLogin;
import controller.ControllerProprietario;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;

@SuppressWarnings("unused")
public class HomePageProprietario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VisualizzaProgetti visualizza;
	private CreaNotifica creanotifica;
	private CreaProgetto creaprogetto;
	private ControllerProprietario controllerProprietario;
	private String username = ControllerLogin.getUsernameGlobale();
	private String CFProprietario = ControllerLogin.getCodiceFiscaleByUsername(username);
	
	public HomePageProprietario() {
		controllerProprietario = new ControllerProprietario();
		setTitle("HomePageProprietario");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 843, 564);
	    
	    URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);

	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    @SuppressWarnings("unused")
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    @SuppressWarnings("unused")
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][][grow][grow][grow][grow][grow][grow]"));
	  
	    visualizza = new VisualizzaProgetti(this);
	    creanotifica = new CreaNotifica(this);
	    creaprogetto = new CreaProgetto(this); 
	    
	    JLabel LabelBenvenuto = new JLabel("Benvenuto! Sei un Proprietario");
	    LabelBenvenuto.setFont(new Font("Tahoma", Font.BOLD, 17));
	    contentPane.add(LabelBenvenuto, "cell 0 0,alignx center");
	    
	    //inzio sezione notifiche
	    
	    JToggleButton TButtonNotifiche = new JToggleButton("");
	    contentPane.add(TButtonNotifiche, "cell 13 0,alignx center,aligny center");
	    TButtonNotifiche.setBorderPainted(false);
	    TButtonNotifiche.setContentAreaFilled(false);
	    TButtonNotifiche.setFocusPainted(false);

	    
	    JTextArea TxtListaNotifiche = new JTextArea();
	    TxtListaNotifiche.setText("""
	    		1. Devi irrigare
	    		2. Controlla il lotto C2
	    		3. Coltivazione in scadenza
	    		4. Nuova notifica ricevuta
	    		5. Aggiorna i dati colturali
	    		""");
	    TxtListaNotifiche.setEditable(false);
	    JScrollPane scrollNotifiche = new JScrollPane(TxtListaNotifiche);
	    scrollNotifiche.setVisible(false); // Inizialmente nascosto
	    contentPane.add(scrollNotifiche, "cell 13 1 1 4, grow");
	    
	    //CONTROLLO NOTIFICHE
	    String notifiche = TxtListaNotifiche.getText();
	    if(notifiche.isEmpty()) {
		    ImageIcon originalIcon = new ImageIcon(getClass().getResource("/img/notifichevuote.png"));
		    Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);	//ridimensionamento immagine
		    ImageIcon scaledIcon = new ImageIcon(scaledImage);
		    TButtonNotifiche.setIcon(scaledIcon);
	    }
	    else {
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
			}
		});
	    
	    //fine sezione notifiche
	    
	    JTextArea TxtScelta = new JTextArea();
	    TxtScelta.setFont(new Font("Monospaced", Font.BOLD, 13));
	    TxtScelta.setEditable(false);
	    TxtScelta.setText("Scegli se visualizzare\r\no creare un progetto");
	    contentPane.add(TxtScelta, "cell 5 4,alignx center,aligny center");
	    TxtScelta.setOpaque(false);
	    
	    JButton ButtonVisualizza = new JButton("Visualizza");
	    contentPane.add(ButtonVisualizza, "cell 5 6,alignx center");
	    ButtonVisualizza.setPreferredSize(new Dimension(150, 20));
	    ButtonVisualizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				visualizza.setVisible(true);
			}
		});
	    
	    JButton ButtonCreaP = new JButton("Crea Progetto");
	    contentPane.add(ButtonCreaP, "cell 5 7,alignx center");
	    ButtonCreaP.setPreferredSize(new Dimension(150, 20));
	    ButtonCreaP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				creaprogetto.setVisible(true);
			}
		});
	    
	    JButton ButtonCreaN = new JButton("Crea Notifica");
	    contentPane.add(ButtonCreaN, "cell 5 8,alignx center");
	    ButtonCreaN.setPreferredSize(new Dimension(150, 20));
	    
	    JButton ButtonAggiungiL = new JButton("Aggiungi Lotto");
	    ButtonAggiungiL.setPreferredSize(new Dimension(150, 20));
	    contentPane.add(ButtonAggiungiL, "cell 5 9,alignx center");
	    
	    ButtonCreaN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				creanotifica.setVisible(true);
			}
		});
	    
	    ButtonAggiungiL.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            
	        	
	            boolean aggiuntaLotto = controllerProprietario.aggiungiL(CFProprietario);
	            
	            if (aggiuntaLotto==true) {
	            	creaprogetto.popolaComboLotto();
	                //aggiunta lotto
	                JOptionPane.showMessageDialog(HomePageProprietario.this, 
	                    "Lotto aggiunto con successo!", "Successo", 
	                    JOptionPane.INFORMATION_MESSAGE);
	            } else {
	                JOptionPane.showMessageDialog(HomePageProprietario.this, 
	                    "Errore: nessun lotto libero disponibile", "Errore", 
	                    JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    });
	    
	   
	}

}
