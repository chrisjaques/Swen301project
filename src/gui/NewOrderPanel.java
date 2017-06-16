package gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import logic.RouteService;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class NewOrderPanel extends JPanel {
	private JTextField weightTextField;
	private JTextField volumeTextField;

	/**
	 * Create the panel.
	 */
	public NewOrderPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblKpsmartCreate = new JLabel("KPSmart - Create New Order");
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
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JComboBox originDropDownBox = new JComboBox();
		//get contents for origin drop down
		ArrayList<String> origins = RouteService.getOrigins();
		originDropDownBox.setModel(new DefaultComboBoxModel(origins.toArray()));
		originDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_originDropDownBox = new GridBagConstraints();
		gbc_originDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_originDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_originDropDownBox.gridx = 0;
		gbc_originDropDownBox.gridy = 3;
		add(originDropDownBox, gbc_originDropDownBox);
		
		JComboBox destinationDropDownBox = new JComboBox();
		ArrayList<String> destinations = RouteService.getDestinations();
		destinationDropDownBox.setModel(new DefaultComboBoxModel(destinations.toArray()));
		destinationDropDownBox.setSelectedIndex(-1);
		GridBagConstraints gbc_destinationDropDownBox = new GridBagConstraints();
		gbc_destinationDropDownBox.insets = new Insets(0, 0, 5, 0);
		gbc_destinationDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_destinationDropDownBox.gridx = 2;
		gbc_destinationDropDownBox.gridy = 3;
		add(destinationDropDownBox, gbc_destinationDropDownBox);
		
		JLabel weightLabel = new JLabel("Weight");
		GridBagConstraints gbc_weightLabel = new GridBagConstraints();
		gbc_weightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_weightLabel.gridx = 0;
		gbc_weightLabel.gridy = 4;
		add(weightLabel, gbc_weightLabel);
		
		JLabel volumeLabel = new JLabel("Volume");
		GridBagConstraints gbc_volumeLabel = new GridBagConstraints();
		gbc_volumeLabel.insets = new Insets(0, 0, 5, 0);
		gbc_volumeLabel.gridx = 2;
		gbc_volumeLabel.gridy = 4;
		add(volumeLabel, gbc_volumeLabel);
		
		weightTextField = new JTextField();
		GridBagConstraints gbc_weightTextField = new GridBagConstraints();
		gbc_weightTextField.insets = new Insets(0, 0, 5, 5);
		gbc_weightTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_weightTextField.gridx = 0;
		gbc_weightTextField.gridy = 5;
		add(weightTextField, gbc_weightTextField);
		weightTextField.setColumns(10);
		
		volumeTextField = new JTextField();
		GridBagConstraints gbc_volumeTextField = new GridBagConstraints();
		gbc_volumeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_volumeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_volumeTextField.gridx = 2;
		gbc_volumeTextField.gridy = 5;
		add(volumeTextField, gbc_volumeTextField);
		volumeTextField.setColumns(10);
		
		JLabel lblPriority = new JLabel("Priority");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 6;
		add(lblPriority, gbc_lblPriority);
		
		JComboBox priorityDropDownBox = new JComboBox();
		priorityDropDownBox.setModel(new DefaultComboBoxModel(new String[] {"Air ", "Land ", "Sea"}));
		GridBagConstraints gbc_priorityDropDownBox = new GridBagConstraints();
		priorityDropDownBox.setSelectedIndex(-1);
		gbc_priorityDropDownBox.insets = new Insets(0, 0, 5, 5);
		gbc_priorityDropDownBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_priorityDropDownBox.gridx = 0;
		gbc_priorityDropDownBox.gridy = 7;
		add(priorityDropDownBox, gbc_priorityDropDownBox);
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 8;
		add(doneButton, gbc_doneButton);

	}

}
