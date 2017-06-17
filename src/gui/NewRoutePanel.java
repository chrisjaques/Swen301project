package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import logic.KPSmartController;
import logic.RouteService;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

public class NewRoutePanel extends JPanel implements ActionListener {

	private KPSmartController controller;
	private JComboBox priorityDropDownBox;
	private JFormattedTextField priceInputField;
	private JTextField originField;
	private JTextField destinationField;

	/**
	 * Create the panel.
	 */
	public NewRoutePanel(KPSmartController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel lblKpsmartCreate = new JLabel("Create New Route");
		lblKpsmartCreate.setForeground(new Color(0, 51, 102));
		lblKpsmartCreate.setBorder(new EmptyBorder(10, 0, 20, 0));
		lblKpsmartCreate.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		GridBagConstraints gbc_lblKpsmartCreate = new GridBagConstraints();
		gbc_lblKpsmartCreate.gridwidth = 3;
		gbc_lblKpsmartCreate.insets = new Insets(0, 0, 5, 0);
		gbc_lblKpsmartCreate.gridx = 0;
		gbc_lblKpsmartCreate.gridy = 0;
		add(lblKpsmartCreate, gbc_lblKpsmartCreate);

		JLabel originLabel = new JLabel("Origin");
		GridBagConstraints gbc_originLabel = new GridBagConstraints();
		gbc_originLabel.insets = new Insets(0, 0, 5, 5);
		gbc_originLabel.gridx = 0;
		gbc_originLabel.gridy = 2;
		add(originLabel, gbc_originLabel);

		JLabel destinationLabel = new JLabel("Destination");
		GridBagConstraints gbc_destinationLabel = new GridBagConstraints();
		gbc_destinationLabel.insets = new Insets(0, 0, 5, 0);
		gbc_destinationLabel.gridx = 2;
		gbc_destinationLabel.gridy = 2;
		add(destinationLabel, gbc_destinationLabel);
		ArrayList<String> origins = RouteService.getOrigins();
		ArrayList<String> destinations = RouteService.getDestinations();

		originField = new JTextField();
		GridBagConstraints gbc_originField = new GridBagConstraints();
		gbc_originField.insets = new Insets(0, 0, 5, 5);
		gbc_originField.fill = GridBagConstraints.HORIZONTAL;
		gbc_originField.gridx = 0;
		gbc_originField.gridy = 3;
		add(originField, gbc_originField);
		originField.setColumns(10);

		destinationField = new JTextField();
		GridBagConstraints gbc_destinationField = new GridBagConstraints();
		gbc_destinationField.anchor = GridBagConstraints.NORTH;
		gbc_destinationField.insets = new Insets(0, 0, 5, 0);
		gbc_destinationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationField.gridx = 2;
		gbc_destinationField.gridy = 3;
		add(destinationField, gbc_destinationField);
		destinationField.setColumns(10);

		JLabel priorityLabel = new JLabel("Priority");
		GridBagConstraints gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 4;
		add(priorityLabel, gbc_priorityLabel);

		JLabel priceLabel = new JLabel("Price");
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.insets = new Insets(0, 0, 5, 0);
		gbc_priceLabel.gridx = 2;
		gbc_priceLabel.gridy = 4;
		add(priceLabel, gbc_priceLabel);

		this.priorityDropDownBox = new JComboBox();
		priorityDropDownBox.setModel(new DefaultComboBoxModel(new String[] {"Air", "Land", "Sea"}));
		priorityDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_priorityDropDownBox = new GridBagConstraints();
		gbc_priorityDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_priorityDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_priorityDropDownBox.gridx = 0;
		gbc_priorityDropDownBox.gridy = 5;
		add(priorityDropDownBox, gbc_priorityDropDownBox);

		NumberFormat format = DecimalFormat.getInstance();
		this.priceInputField = new JFormattedTextField(format);
		GridBagConstraints gbc_priceInputField = new GridBagConstraints();
		gbc_priceInputField.insets = new Insets(0, 0, 5, 0);
		gbc_priceInputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priceInputField.gridx = 2;
		gbc_priceInputField.gridy = 5;
		add(priceInputField, gbc_priceInputField);

		JButton doneButton = new JButton("Create New Route");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 6;
		add(doneButton, gbc_doneButton);
		doneButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.originField.getText().trim().equals("")
			&& !this.destinationField.getText().trim().equals("")
			&& this.priorityDropDownBox.getSelectedItem() != null
			&& this.priceInputField.getValue() != null) {
				String createAttempt = this.controller.addRoute(this.originField.getText().trim(),
						this.destinationField.getText().trim(), this.priorityDropDownBox.getSelectedItem().toString(),
						this.priceInputField.getValue().toString());
				if (!createAttempt.equals("Success")) {
					JOptionPane.showMessageDialog(new JFrame(), createAttempt, "ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					this.controller.getKPSmartFrame().changeFocus("Home Screen");
				}

			} else {
				JOptionPane.showMessageDialog(new JFrame(), "Please fill out all fields", "ERROR", JOptionPane.ERROR_MESSAGE);
			}

	}

}
