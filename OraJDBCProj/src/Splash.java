import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class Splash extends JFrame {

	private JPanel contentPane;
	private JProgressBar progressBar;
	
	
	/**
	 * Create the frame.
	 */
	public Splash() {
		setTitle("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.DARK_GRAY);
//		contentPane.setBounds(5, 50, 50, 50);
		contentPane.setBorder(new EmptyBorder(20, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SHOP DATABASE MANAGEMENT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblNewLabel.setBounds(10, 11, 768, 71);
		contentPane.add(lblNewLabel);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.MAGENTA);
		progressBar.setBackground(Color.WHITE);
		progressBar.setStringPainted(true);
		progressBar.setBounds(185, 160, 400, 17);
		contentPane.add(progressBar);
	}



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Splash mySplash = new Splash();
		mySplash.setVisible(true);
		try {
			for (int i = 0; i <= 100; i++) {
				Thread.sleep(100);
				mySplash.progressBar.setValue(i);
			}
		}
		catch(Exception e) {
			
		}
		new Login().setVisible(true);
		mySplash.dispose();
	}
}
