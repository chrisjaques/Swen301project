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

import logic.KPSmartController;
import logic.RouteService;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;

public class UpdatePricePanel extends JPanel implements ActionListener {

	private KPSmartController controller;
	private JComboBox originDropDownBox;
	private JComboBox destinationDropDownBox;
	private JComboBox priorityDropDownBox;
	private JFormattedTextField newPriceInputField;
	
	/**
	 * Create the panel.
	 */
	public UpdatePricePanel(KPSmartController controller) {
		this.controller = controller;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblKpsmartCreate = new JLabel("Update Route Price");
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
		
		this.originDropDownBox = new JComboBox();
		ArrayList<String> origins = RouteService.getOrigins();
		originDropDownBox.setModel(new DefaultComboBoxModel(origins.toArray()));
		originDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_originDropDownBox = new GridBagConstraints();
		gbc_originDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_originDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_originDropDownBox.gridx = 0;
		gbc_originDropDownBox.gridy = 3;
		add(originDropDownBox, gbc_originDropDownBox);
		
		this.destinationDropDownBox = new JComboBox();
		ArrayList<String> destinations = RouteService.getDestinations();
		destinationDropDownBox.setModel(new DefaultComboBoxModel(destinations.toArray()));
		destinationDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_destinationDropDownBox = new GridBagConstraints();
		gbc_destinationDropDownBox.insets = new Insets(0, 0, 5, 0);
		gbc_destinationDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationDropDownBox.gridx = 2;
		gbc_destinationDropDownBox.gridy = 3;
		add(destinationDropDownBox, gbc_destinationDropDownBox);
		
		JLabel priorityLabel = new JLabel("Priority");
		GridBagConstraints gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 4;
		add(priorityLabel, gbc_priorityLabel);
		
		JLabel newPriceLabel = new JLabel("New Price");
		GridBagConstraints gbc_newPriceLabel = new GridBagConstraints();
		gbc_newPriceLabel.insets = new Insets(0, 0, 5, 0);
		gbc_newPriceLabel.gridx = 2;
		gbc_newPriceLabel.gridy = 4;
		add(newPriceLabel, gbc_newPriceLabel);
		
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
		this.newPriceInputField = new JFormattedTextField(format);
		GridBagConstraints gbc_newPriceInputField = new GridBagConstraints();
		gbc_newPriceInputField.insets = new Insets(0, 0, 5, 0);
		gbc_newPriceInputField.fill = GridBagConstraints.HORIZONTAL;
		gbc_newPriceInputField.gridx = 2;
		gbc_newPriceInputField.gridy = 5;
		add(newPriceInputField, gbc_newPriceInputField);
		
		JButton doneButton = new JButton("Update Price");
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 6;
		add(doneButton, gbc_doneButton);
		doneButton.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.originDropDownBox.getSelectedItem() != null
				&& this.destinationDropDownBox.getSelectedItem() != null
				&& this.priorityDropDownBox.getSelectedItem() != null
				&& this.newPriceInputField.getValue() != null) {
			String createAttempt = this.controller.addRoute(this.originDropDownBox.getSelectedItem().toString(),
					this.destinationDropDownBox.getSelectedItem().toString(), this.priorityDropDownBox.getSelectedItem().toString(),
					this.newPriceInputField.getValue().toString());
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
