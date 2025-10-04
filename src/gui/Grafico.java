package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import controller.ControllerGrafico;
import controller.ControllerLogin;
import controller.CreaProgettoController;
import dao.DAO;
import dao.daoGrafico;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JButton;

public class Grafico extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String username = ControllerLogin.getUsernameGlobale();
	CreaProgettoController creaProgettoController;
	ControllerGrafico controllerGrafico;
	JComboBox<String> ComboLotto = new JComboBox<String>();
	JComboBox<String> ComboColtura = new JComboBox<String>();

	/**
	 * Create the frame.
	 */
	public Grafico(String selectedLotto) {
		setTitle("Grafico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 553);
		
		URL imageUrl = getClass().getResource("/img/sfondoschede.PNG");
	    contentPane = new BackgroundPanel(imageUrl);
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    
	    // Layout: 15 colonne grow e push, 15 righe grow e push
	    String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", 
	    										"[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JLabel LabelLotto = new JLabel("Lotto");
	    contentPane.add(LabelLotto, "cell 0 1,alignx trailing");
	    
	    
	    ComboLotto.setSelectedIndex(-1);
	    ComboLotto.setPreferredSize(new Dimension(150, 20));
	    contentPane.add(ComboLotto, "cell 1 1,growx");
	    
	    JLabel LabelColtura = new JLabel("Coltura");
	    contentPane.add(LabelColtura, "cell 0 2,alignx trailing");
	    
	    
	    ComboColtura.setSelectedIndex(-1);
	    ComboColtura.setPreferredSize(new Dimension(150, 20));
	    contentPane.add(ComboColtura, "cell 1 2,growx");
	    
	    JButton ButtonReport = new JButton("Report grafico");
	    ButtonReport.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	            Object selLotto = ComboLotto.getSelectedItem();
	            Object selColt  = ComboColtura.getSelectedItem();

	            if (selLotto == null || selColt == null) {
	                JOptionPane.showMessageDialog(Grafico.this, "Seleziona Lotto e Coltura");
	                return;
	            }

	            final int idlotto;
	            try {
	                idlotto = Integer.parseInt(selLotto.toString().trim());
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(Grafico.this, "ID lotto non valido.");
	                return;
	            }
	            final String varieta = selColt.toString().trim();

	            // Controller (meglio avere un field, ma se vuoi lasciarlo qui va bene)
	            ControllerGrafico controller = new ControllerGrafico();

	            double[] stats = controller.getStatistiche(idlotto, varieta);
	            // stats: [0]=num_raccolte, [1]=media, [2]=min, [3]=max
	            System.out.println("DEBUG stats: " + java.util.Arrays.toString(stats));

	            //se non c'è nessun dato, avvisa
	            if (stats == null) {
	                JOptionPane.showMessageDialog(Grafico.this,
	                        "Errore nel recupero delle statistiche.");
	                return;
	            }
	            long count = Math.round(stats[0]);
	            boolean noNumeri = (Double.isNaN(stats[1]) && Double.isNaN(stats[2]) && Double.isNaN(stats[3]));
	            if (count <= 0 || noNumeri) {
	                JOptionPane.showMessageDialog(Grafico.this,
	                        "Nessuna raccolta per Lotto " + idlotto + " e " + varieta);
	                return;
	            }

	            //preparo il dataset per JFreeChart
	            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	            String serie = varieta;                   // riga/serie (legenda)
	            dataset.addValue(count,     serie, "Totale raccolte");
	            dataset.addValue(stats[1],  serie, "Media");
	            dataset.addValue(stats[2],  serie, "Min");
	            dataset.addValue(stats[3],  serie, "Max");

	            //creo e mostro il grafico
	            String titolo = "Lotto " + idlotto + " - " + varieta;
	            JFreeChart chart = ChartFactory.createBarChart(
	                    titolo,
	                    "Statistiche",
	                    "Quantità",
	                    dataset,
	                    PlotOrientation.HORIZONTAL,
	                    true,   // legenda
	                    true,   // tooltips
	                    false   // urls
	            );

	            JFrame chartFrame = new JFrame("Grafico");
	            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	            chartFrame.setSize(700, 500);
	            chartFrame.add(new ChartPanel(chart));
	            chartFrame.setLocationRelativeTo(Grafico.this);
	            chartFrame.setVisible(true);
	        }
	    });
	    contentPane.add(ButtonReport, "cell 1 4,alignx center");
	    
	    //controlla la ComboLotto
	    if (selectedLotto != null) {
            ComboLotto.setSelectedItem(selectedLotto);
        }
	    
	    DAO dao = new DAO(); //creo il DAO
	    creaProgettoController = new CreaProgettoController(dao); //creo il controller di crea progetto
	    
	    controllerGrafico = new ControllerGrafico(); //creo il controller
	    
	    popolaComboLotto(); //Popola la ComboLotto con i lotti del proprietario loggato
	    
	    // Imposta il lotto selezionato e popola ComboColtura
        if (selectedLotto != null) {
            ComboLotto.setSelectedItem(selectedLotto);
            popolaComboColtura(selectedLotto);
        }

        // Aggiungi listener per aggiornare ComboColtura quando cambia ComboLotto
        ComboLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLotto = (String) ComboLotto.getSelectedItem();
                popolaComboColtura(selectedLotto);
            }
        });
	    

	}
	
	private void popolaComboLotto() {  //Popolo il combolotto passato come parametro e uso il crea progetto controller
        String username = ControllerLogin.getUsernameGlobale(); // Usa l'username globale
		List<String> lotti = creaProgettoController.getLottiPerCombo(username);  
        for (String lotto : lotti) { 
            ComboLotto.addItem(lotto);  // Aggiunge ogni ID_Lotto alla ComboBox (es. "1", "2")
        }
        ComboLotto.setSelectedIndex(-1);
    }
	
	private void popolaComboColtura(String idLotto) { 
        if (idLotto != null) {
            List<String> colture = controllerGrafico.getColturaByLotto(idLotto); 
            for (String coltura : colture) {
                ComboColtura.addItem(coltura); // Aggiunge ogni coltura alla ComboBox (es. "Pomodoro San Marzano")
            }
        }
        ComboColtura.setSelectedIndex(-1);
    }

}