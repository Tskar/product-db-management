import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;	
	private JTextField textField;
	private JPasswordField passwordField;
	private static Login frame;

	private String user;
	private String pass;

	public AppJS app;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Login();
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
	public Login() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(335, 0, 453, 365);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblManagement = new JLabel("MANAGEMENT");
		lblManagement.setHorizontalAlignment(SwingConstants.LEFT);
		lblManagement.setForeground(Color.DARK_GRAY);
		lblManagement.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblManagement.setBounds(10, 11, 315, 70);
		panel.add(lblManagement);
		
		JLabel usernameLabel = new JLabel("USERNAME");
		usernameLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		usernameLabel.setBounds(10, 136, 118, 34);
		panel.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		passwordLabel.setBounds(10, 208, 118, 34);
		panel.add(passwordLabel);
		
		textField = new JTextField();
		textField.setBounds(138, 136, 305, 34);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(138, 208, 305, 34);
		panel.add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLogin.setBounds(87, 288, 148, 40);
		panel.add(btnLogin);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnClear.setBounds(245, 288, 148, 40);
		panel.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("SHOP DATABASE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 315, 70);
		contentPane.add(lblNewLabel);
		
		//Login function
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				
				user = textField.getText();
				pass = passwordField.getText();
				app = new AppJS(user, pass);
				app.setVisible(true);
				
			}
		});

		
		//Clear function
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});

	}
	
	public String getUser() {
		return user;
	}
	
	public String getPass() {
		return pass;
	}

}
