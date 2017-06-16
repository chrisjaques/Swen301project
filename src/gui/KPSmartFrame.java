package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.KPSmartController;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.CardLayout;

public class KPSmartFrame extends JFrame {

	private JPanel contentPane;
	JPanel mainPanel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private KPSmartController controller; 
	private CardLayout mainPanelLayout;


	/**
	 * Launch the application.
	 */
	public void openKPSmartFrame(KPSmartController controller){
		this.controller = controller;
		KPSmartFrame frame = new KPSmartFrame(this.controller);
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public KPSmartFrame(KPSmartController controller) {
		this.controller = controller;
		System.out.println(controller);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblKpsmart = new JLabel("KPSmart");
		lblKpsmart.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 24));
		lblKpsmart.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblKpsmart, BorderLayout.NORTH);
		lblKpsmart.setBorder(new EmptyBorder(0, 0, 20, 0));

		
		this.mainPanelLayout = new CardLayout(0, 0);
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(mainPanelLayout);
		
		//add components to main panel and set login as initial focus
		HomeScreenPanel homePanel = new HomeScreenPanel(this.controller);
		this.mainPanel.add(homePanel, "Home Screen");
		LoginScreenPanel loginPanel = new LoginScreenPanel(this.controller, this);
		this.mainPanel.add(loginPanel, "Login Screen");
		this.mainPanelLayout.show(mainPanel, "Login Screen");
		this.contentPane.add(mainPanel, BorderLayout.CENTER);
	}
	
	public void changeFocus(String focus){
		this.mainPanelLayout.show(mainPanel, focus);
	}

}
