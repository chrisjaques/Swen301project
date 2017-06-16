package gui;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

import logic.KPSmartController;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class CreateNewUser extends JPanel implements ActionListener {
	private JTextField firstnameTextField;
	private JTextField lastnameTextField;
	private JTextField passwordTextField;
	private JTextField confirmPasswordField;
	
	private KPSmartController controller;
	private JComboBox userType;

	/**
	 * Create the panel.
	 */
	public CreateNewUser(KPSmartController controller) {
		this.controller = controller;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblKpsmartCreate = new JLabel("Create New User");
		lblKpsmartCreate.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_lblKpsmartCreate = new GridBagConstraints();
		gbc_lblKpsmartCreate.gridwidth = 3;
		gbc_lblKpsmartCreate.insets = new Insets(0, 0, 5, 0);
		gbc_lblKpsmartCreate.gridx = 0;
		gbc_lblKpsmartCreate.gridy = 0;
		add(lblKpsmartCreate, gbc_lblKpsmartCreate);
		
		JLabel firstnameLabel = new JLabel("Firstname");
		GridBagConstraints gbc_firstnameLabel = new GridBagConstraints();
		gbc_firstnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameLabel.gridx = 1;
		gbc_firstnameLabel.gridy = 1;
		add(firstnameLabel, gbc_firstnameLabel);
		
		firstnameTextField = new JTextField();
		firstnameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_firstnameTextField = new GridBagConstraints();
		gbc_firstnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_firstnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstnameTextField.gridx = 1;
		gbc_firstnameTextField.gridy = 2;
		add(firstnameTextField, gbc_firstnameTextField);
		firstnameTextField.setColumns(10);
		
		JLabel lastnameLabel = new JLabel("Lastname");
		GridBagConstraints gbc_lastnameLabel = new GridBagConstraints();
		gbc_lastnameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lastnameLabel.gridx = 1;
		gbc_lastnameLabel.gridy = 3;
		add(lastnameLabel, gbc_lastnameLabel);
		
		lastnameTextField = new JTextField();
		lastnameTextField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lastnameTextField = new GridBagConstraints();
		gbc_lastnameTextField.insets = new Insets(0, 0, 5, 5);
		gbc_lastnameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastnameTextField.gridx = 1;
		gbc_lastnameTextField.gridy = 4;
		add(lastnameTextField, gbc_lastnameTextField);
		lastnameTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		GridBagConstraints gbc_passwordLabel = new GridBagConstraints();
		gbc_passwordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_passwordLabel.gridx = 1;
		gbc_passwordLabel.gridy = 5;
		add(passwordLabel, gbc_passwordLabel);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_passwordTextField = new GridBagConstraints();
		gbc_passwordTextField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordTextField.gridx = 1;
		gbc_passwordTextField.gridy = 6;
		add(passwordTextField, gbc_passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		GridBagConstraints gbc_confirmPasswordLabel = new GridBagConstraints();
		gbc_confirmPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordLabel.gridx = 1;
		gbc_confirmPasswordLabel.gridy = 7;
		add(confirmPasswordLabel, gbc_confirmPasswordLabel);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_confirmPasswordField = new GridBagConstraints();
		gbc_confirmPasswordField.insets = new Insets(0, 0, 5, 5);
		gbc_confirmPasswordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_confirmPasswordField.gridx = 1;
		gbc_confirmPasswordField.gridy = 8;
		add(confirmPasswordField, gbc_confirmPasswordField);
		confirmPasswordField.setColumns(10);
		
		JLabel lblUserTypr = new JLabel("User Type");
		GridBagConstraints gbc_lblUserTypr = new GridBagConstraints();
		gbc_lblUserTypr.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserTypr.gridx = 1;
		gbc_lblUserTypr.gridy = 9;
		add(lblUserTypr, gbc_lblUserTypr);
		
		this.userType = new JComboBox();
		userType.setModel(new DefaultComboBoxModel(new String[] {"Clerk\t", "Manager"}));
		userType.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 10;
		add(userType, gbc_comboBox);
		
		JButton doneButton = new JButton("Create User");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 11;
		add(doneButton, gbc_doneButton);
		doneButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.firstnameTextField.getText() != null && this.lastnameTextField.getText() != null
				&& this.passwordTextField.getText() != null && this.confirmPasswordField != null
				&& this.userType.getSelectedItem() != null) {
			if (this.passwordTextField.getText().equals(this.confirmPasswordField.getText())) {
				String createAttempt = this.controller.createUser(this.firstnameTextField.getText(), this.passwordTextField.getText(), this.userType.getSelectedItem().toString());
				if (!createAttempt.equals("Success")) {
					JOptionPane.showMessageDialog(new JFrame(), createAttempt, "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					this.controller.getKPSmartFrame().changeFocus("Home Screen");
				}
			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Passwords do not match, try again.", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "Please fill out all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
