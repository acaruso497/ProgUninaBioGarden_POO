package grafica;
import utili.ConnessioneDB;

import java.sql.*;

import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import code.Prodotto;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class HomeProdotto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField FieldDescrizione;
	private JTextField FieldCategoria;
	private JTextField FieldSottoC;
	private JTextField FieldListino1;
	private JTextField FieldListino2;
	private JTextField FieldListino3;
	private JTextField FieldQT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeProdotto frame = new HomeProdotto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeProdotto() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1399, 912);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem ItemProfilo = new JMenuItem("Profilo");
		menuBar.add(ItemProfilo);
		
		JMenuItem ItemProdotti = new JMenuItem("Prodotti");
		ItemProdotti.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemProdotti);
		
		JMenuItem ItemClienti = new JMenuItem("Clienti");
		ItemClienti.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemClienti);
		
		JMenuItem ItemFornitori = new JMenuItem("Fornitori");
		ItemFornitori.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemFornitori);
		
		JMenuItem ItemDocumenti = new JMenuItem("Documenti");
		ItemDocumenti.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemDocumenti);
		
		JMenuItem ItemFatture = new JMenuItem("Fatture");
		ItemFatture.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemFatture);
		
		JMenuItem ItemPagamenti = new JMenuItem("Pagamenti");
		ItemPagamenti.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemPagamenti);
		
		JMenuItem ItemMagazzino = new JMenuItem("Magazzino");
		ItemMagazzino.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(ItemMagazzino);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		String columns = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";
	    String rows = "push " + " ".repeat(14).replace(" ", "[grow] ") + "push";

	    contentPane.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    
	    
	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Codice");
	    model.addColumn("Descrizione");
	    model.addColumn("Q.tà");
	    model.addColumn("U.mis");
	    model.addColumn("Fornitore");
	    model.addColumn("Categoria");
	    model.addColumn("Listino1");

	    table = new JTable(model);
	    table.setBackground(new Color(255, 255, 255));

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    contentPane.add(scrollPane, "cell 0 0 13 13,grow");
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	    table.getTableHeader().setResizingAllowed(false);
	    table.getColumnModel().getColumn(0).setPreferredWidth(100);
	    table.getColumnModel().getColumn(1).setPreferredWidth(400);
	    
		aggiornaTabellaProdotti(model);
	    
	    table.getSelectionModel().addListSelectionListener(event -> {
	        if (!event.getValueIsAdjusting()) {
	            int selectedRow = table.getSelectedRow();
	            if (selectedRow != -1) {
	                String desc = (String) table.getValueAt(selectedRow, 1);
	                String cat = (String) table.getValueAt(selectedRow, 2);
	                FieldDescrizione.setText(desc);
	                FieldCategoria.setText(cat);
	            }
	        }
	    });
	    
	    JPanel panel = new JPanel();
	    contentPane.add(panel, "cell 13 0 1 14,grow");
	    panel.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]", "[][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
	    
	    JButton ButtonProdotto = new JButton("New button");
	    panel.add(ButtonProdotto, "flowx,cell 0 0");
	    
	    
	    
	    JLabel LabelDescrizione = new JLabel("Descrizione");
	    panel.add(LabelDescrizione, "cell 0 1");
	    
	    FieldDescrizione = new JTextField();
	    panel.add(FieldDescrizione, "cell 2 1,growx");
	    FieldDescrizione.setColumns(10);
	    
	    JLabel LabelCategoria = new JLabel("Categoria");
	    panel.add(LabelCategoria, "cell 0 2");
	    
	    FieldCategoria = new JTextField();
	    panel.add(FieldCategoria, "cell 2 2,growx");
	    FieldCategoria.setColumns(10);
	    
	    JLabel LabelSottoC = new JLabel("Sottocategoia");
	    panel.add(LabelSottoC, "cell 0 3");
	    
	    FieldSottoC = new JTextField();
	    panel.add(FieldSottoC, "cell 2 3,growx");
	    FieldSottoC.setColumns(10);
	    
	    JLabel LabelUM = new JLabel("Unità di misura");
	    panel.add(LabelUM, "cell 0 4");
	    
	    JComboBox ComboUM = new JComboBox();
	    panel.add(ComboUM, "cell 2 4,growx");
	    
	    JLabel LabelQT = new JLabel("Quantità");
	    panel.add(LabelQT, "cell 0 5");
	    
	    FieldQT = new JTextField();
	    panel.add(FieldQT, "cell 2 5,growx");
	    FieldQT.setColumns(10);
	    
	    JLabel LabelListino1 = new JLabel("Listino 1");
	    panel.add(LabelListino1, "cell 0 6");
	    
	    FieldListino1 = new JTextField();
	    panel.add(FieldListino1, "cell 2 6,growx");
	    FieldListino1.setColumns(10);
	    
	    JLabel LabelListino2 = new JLabel("Listino 2");
	    panel.add(LabelListino2, "cell 0 7");
	    
	    FieldListino2 = new JTextField();
	    panel.add(FieldListino2, "cell 2 7,growx");
	    FieldListino2.setColumns(10);
	    
	    JLabel LabelListino3 = new JLabel("Listino3");
	    panel.add(LabelListino3, "cell 0 8");
	    
	    FieldListino3 = new JTextField();
	    panel.add(FieldListino3, "cell 2 8,growx");
	    FieldListino3.setColumns(10);
	    
	    JLabel LabelIva = new JLabel("Aliquota Iva");
	    panel.add(LabelIva, "cell 0 11");
	    
	    JComboBox ComboIva = new JComboBox();
	    panel.add(ComboIva, "cell 2 11,growx");
	    
	    JLabel LabelFornitore = new JLabel("Fornitore");
	    panel.add(LabelFornitore, "cell 0 13");
	    
	    JComboBox ComboFornitore = new JComboBox();
	    panel.add(ComboFornitore, "cell 2 13,growx");
	    
	    JButton ButtonSalva = new JButton("Salva");
	    panel.add(ButtonSalva, "cell 0 14,alignx center");
	    ButtonSalva.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int selectedRow = table.getSelectedRow();
				String desc = FieldDescrizione.getText();
				String cat = FieldCategoria.getText();
				String sottoc = FieldSottoC.getText();
				int qt = Integer.parseInt(FieldQT.getSelectedText());
				double l1 = Double.parseDouble(FieldListino1.getText());
				double l2 = Double.parseDouble(FieldListino2.getText());
				double l3 = Double.parseDouble(FieldListino3.getText());		//mancano i menu a tendina e occhio, ad ora non escono gli attributi scritti a destra sul prodotto
				
				
				int id = (int) table.getValueAt(selectedRow, 0);
				
				//Prodotto.modify(id, desc, cat);  //da sistemare tutto.
				aggiornaTabellaProdotti(model);
			}
	    });



	    
	    JButton ButtonNuovo = new JButton("Nuovo");
	    contentPane.add(ButtonNuovo, "flowx,cell 0 13,alignx center");
	    ButtonNuovo.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				//newp.setVisible(true);
				Prodotto.add(null, null, 0, null, null, 0);
				aggiornaTabellaProdotti(model);
			}
	    });
	    
	    JButton ButtonDuplica = new JButton("Duplica");
	    contentPane.add(ButtonDuplica, "cell 0 13,alignx center");
	    
	    
	    JButton ButtonElimina = new JButton("Elimina");
	    contentPane.add(ButtonElimina, "cell 0 13,alignx center");
	    ButtonElimina.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				int SelectedRow = table.getSelectedRow();
				int id = (int) table.getValueAt(SelectedRow, 0);
				Prodotto.delete(id);
				aggiornaTabellaProdotti(model);
			}
	    });
	    
	}
	
	public void aggiornaTabellaProdotti(DefaultTableModel model) {
	    model.setRowCount(0); // Svuota la tabella attuale

	    String query = "SELECT id_prodotto, descrizione, categoria FROM prodotto ORDER BY id_prodotto";

	    try (Connection conn = ConnessioneDB.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {

	        while (rs.next()) {
	            int id = rs.getInt("id_prodotto");
	            String descrizione = rs.getString("descrizione");
	            String categoria = rs.getString("categoria");

	            model.addRow(new Object[]{id, descrizione, categoria});
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Errore durante il caricamento prodotti: " + e.getMessage());
	    }
	}
}
