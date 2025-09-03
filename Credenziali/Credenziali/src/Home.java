import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel Benvenuto;

	/**
	 * Create the frame.
	 */
	public Home(Login accesso) {
		setTitle("HomePage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Benvenuto = new JLabel("New label");
		Benvenuto.setForeground(new Color(0, 0, 255));
		Benvenuto.setBackground(new Color(255, 255, 255));
		Benvenuto.setHorizontalAlignment(SwingConstants.CENTER);
		Benvenuto.setHorizontalTextPosition(SwingConstants.LEADING);
		Benvenuto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		Benvenuto.setBounds(0, 11, 984, 31);
		contentPane.add(Benvenuto);
		
		JLabel SfondoHome = new JLabel("");
		SfondoHome.setIcon(new ImageIcon("C:\\EclipseVerse\\Credenziali\\Credenziali\\src\\sfondocredenziali.png"));
		SfondoHome.setBounds(0, 0, 984, 561);
		contentPane.add(SfondoHome);
	}
	
	public void SetUserName(String nome) {
		Benvenuto.setText("Benvenuto " + nome);
	}

}
