package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class LoginScreen extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
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
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{440, 0};
		gbl_contentPane.rowHeights = new int[]{38, 38, 38, 38, 38, 38, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel titleLabel = new JLabel("KPSmart Login");
		titleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.fill = GridBagConstraints.BOTH;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		contentPane.add(titleLabel, gbc_titleLabel);
		
		JLabel usernameLabel = new JLabel("UserName");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.fill = GridBagConstraints.BOTH;
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 1;
		contentPane.add(usernameLabel, gbc_usernameLabel);
		
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.fill = GridBagConstraints.BOTH;
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 2;
		contentPane.add(usernameField, gbc_usernameField);
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.fill = GridBagConstraints.BOTH;
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 3;
		contentPane.add(passwordLabel, gbc_passwordLabel);
		
		passwordField = new JTextField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 4;
		contentPane.add(passwordField, gbc_passwordField);
		passwordField.setColumns(10);
		
		JButton btnDone = new JButton("Done");
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.fill = GridBagConstraints.VERTICAL;
		gbc_btnDone.gridx = 0;
		gbc_btnDone.gridy = 6;
		contentPane.add(btnDone, gbc_btnDone);
	}

}
