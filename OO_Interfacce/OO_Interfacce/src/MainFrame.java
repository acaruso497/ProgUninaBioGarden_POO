import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.*;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDigitaQui;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField UserBox;
	private JPasswordField PswBox;
	JTextArea CredenzialiBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {			//apertura threads e varie robe (ignora)
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		this.setResizable(true); //false non la puoi ridimensionare
		this.setTitle("La mia prima finestra");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\ECLIPSE\\ProvaGrafica\\src\\icon.png"));
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setBackground(Color.orange);
		
		JButton btnNewButton = new JButton("clicca qui");
		btnNewButton.setBounds(711, 10, 200, 80);
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 20));
		btnNewButton.setPreferredSize(new Dimension(200, 80));	//dimensione del bottone
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BottonePrincipalePremuto();
			}
		});
		
		txtDigitaQui = new JTextField();
		txtDigitaQui.setBounds(610, 40, 96, 19);
		txtDigitaQui.setText("digita qui");
		txtDigitaQui.setToolTipText("devi scrivere qui!");
		txtDigitaQui.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(655, 236, 200, 200);
		panel.setBackground(new Color(0, 0, 255));
		
		JButton btnNewButton_1 = new JButton("Prova");
		btnNewButton_1.setBounds(50, 10, 101, 40);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton_1);
		contentPane.setLayout(null);
		contentPane.add(txtDigitaQui);
		contentPane.add(btnNewButton);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("prova");
		lblNewLabel_1.setToolTipText("scrivo io, gli altri scrive l'utente");
		lblNewLabel_1.setBounds(48, 77, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setToolTipText("solo su una riga");
		textField.setBounds(50, 125, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea txtrKhj = new JTextArea();
		txtrKhj.setToolTipText("Su più righe");
		txtrKhj.setText("khj");
		txtrKhj.setBounds(49, 179, 113, 86);
		contentPane.add(txtrKhj);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("password");
		passwordField.setBounds(50, 302, 127, 19);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Appoggia il mouse sugli elementi!!!");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblNewLabel.setBounds(25, 10, 447, 30);
		contentPane.add(lblNewLabel);
		
		UserBox = new JTextField();
		UserBox.setBackground(new Color(255, 255, 255));
		UserBox.setBounds(352, 125, 96, 19);
		contentPane.add(UserBox);
		UserBox.setColumns(10);
		
		PswBox = new JPasswordField();
		PswBox.setBounds(352, 154, 96, 19);
		contentPane.add(PswBox);
		
		JLabel Username = new JLabel("Username");
		Username.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Username.setHorizontalAlignment(SwingConstants.RIGHT);
		Username.setBounds(252, 125, 84, 16);
		contentPane.add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Password.setHorizontalAlignment(SwingConstants.RIGHT);
		Password.setBounds(252, 154, 84, 16);
		contentPane.add(Password);
		
		JButton Login = new JButton("Login");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFunction();
			}
		});
		Login.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Login.setBounds(352, 194, 85, 50);
		contentPane.add(Login);
		
		CredenzialiBox = new JTextArea();
		CredenzialiBox.setBounds(261, 281, 190, 167);
		contentPane.add(CredenzialiBox);
	}
	
	public void BottonePrincipalePremuto() {
		System.out.println("Hello World in una GUI");
		for(int i=0; i<10; i++) System.out.println(i);
	}
	
	public void LoginFunction() {
		String username = UserBox.getText();	
		String psw = PswBox.getText();
		
		if(username.length()==0 && psw.length()==0) {
			JOptionPane.showMessageDialog(this, "Inserisci le credenziali!");		//finestra modale, non mi fa fare nulla se non la chiudo
			UserBox.setBackground(new Color(255, 0, 0));
			PswBox.setBackground(new Color(255, 0, 0));
		}
		
		else if(username.length()==0) {
			JOptionPane.showMessageDialog(this, "Inserisci l'username!");		//finestra modale, non mi fa fare nulla se non la chiudo
			PswBox.setBackground(new Color(255, 255, 255));
			UserBox.setBackground(new Color(255, 0, 0));
			UserBox.setBackground(new Color(255, 0, 0));
		}
		
		else if(psw.length()==0) {
			JOptionPane.showMessageDialog(this, "Inserisci la password!");
			UserBox.setBackground(new Color(255, 255, 255));
			PswBox.setBackground(new Color(255, 255, 255));
			PswBox.setBackground(new Color(255, 0, 0));
		}
		
		else {	//invio al DB la coppia username + psw
		CredenzialiBox.setText("Account registrato con successo!\n" + "Username: " + username + "\nPassword: " + psw);
		UserBox.setBackground(new Color(255, 255, 255));
		PswBox.setBackground(new Color(255, 255, 255));
		}
	}
}
