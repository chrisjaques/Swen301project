package gui;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logic.KPSmartController;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;

public class LoginScreenPanel extends JPanel implements ActionListener{

	private JPanel contentPane;
	private KPSmartController controller;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private KPSmartFrame topFrame;

	/**
	 * Create the panel.
	 */
	public LoginScreenPanel(KPSmartController controller, KPSmartFrame frame) {
		this.topFrame = frame;
		this.controller = controller;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		add(lblLogin, gbc_lblLogin);
		lblLogin.setBorder(new EmptyBorder(0, 0, 10, 0));

		JLabel usernameLabel = new JLabel("Username");
		GridBagConstraints gbc_usernameLabel = new GridBagConstraints();
		gbc_usernameLabel.insets = new Insets(0, 0, 5, 0);
		gbc_usernameLabel.gridx = 0;
		gbc_usernameLabel.gridy = 1;
		add(usernameLabel, gbc_usernameLabel);

		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_usernameField = new GridBagConstraints();
		gbc_usernameField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameField.gridx = 0;
		gbc_usernameField.gridy = 2;
		add(usernameField, gbc_usernameField);
		usernameField.setColumns(15);

		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 0);
		gbc_passwordLabel.gridx = 0;
		gbc_passwordLabel.gridy = 3;
		add(passwordLabel, gbc_passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setColumns(15);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 0;
		gbc_passwordField.gridy = 4;
		add(passwordField, gbc_passwordField);

		JButton doneButton = new JButton("Done");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.gridx = 0;
		gbc_doneButton.gridy = 5;
		add(doneButton, gbc_doneButton);
		doneButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String loginAttempt = controller.loginUser(usernameField.getText(), String.valueOf(passwordField.getPassword()), topFrame);		
		if (!loginAttempt.equals("Success")) {
			JOptionPane.showMessageDialog(new JFrame(), loginAttempt, "ERROR", JOptionPane.ERROR_MESSAGE);
		} else {
			usernameField.setText("");
			passwordField.setText("");
		}
	}

}
