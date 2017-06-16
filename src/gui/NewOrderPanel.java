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
		
		JLabel lblOrigin = new JLabel("Origin");
		GridBagConstraints gbc_lblOrigin = new GridBagConstraints();
		gbc_lblOrigin.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrigin.gridx = 0;
		gbc_lblOrigin.gridy = 2;
		add(lblOrigin, gbc_lblOrigin);
		
		JLabel lblDestination = new JLabel("Destination");
		GridBagConstraints gbc_lblDestination = new GridBagConstraints();
		gbc_lblDestination.insets = new Insets(0, 0, 5, 0);
		gbc_lblDestination.gridx = 2;
		gbc_lblDestination.gridy = 2;
		add(lblDestination, gbc_lblDestination);
		
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
		
		JLabel lblWeight = new JLabel("Weight");
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 0;
		gbc_lblWeight.gridy = 4;
		add(lblWeight, gbc_lblWeight);
		
		JLabel lblVolume = new JLabel("Volume");
		GridBagConstraints gbc_lblVolume = new GridBagConstraints();
		gbc_lblVolume.insets = new Insets(0, 0, 5, 0);
		gbc_lblVolume.gridx = 2;
		gbc_lblVolume.gridy = 4;
		add(lblVolume, gbc_lblVolume);
		
		JSpinner spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 0;
		gbc_spinner.gridy = 5;
		add(spinner, gbc_spinner);
		
		JSpinner spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 2;
		gbc_spinner_1.gridy = 5;
		add(spinner_1, gbc_spinner_1);
		
		JLabel lblPriority = new JLabel("Priority");
		GridBagConstraints gbc_lblPriority = new GridBagConstraints();
		gbc_lblPriority.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriority.gridx = 0;
		gbc_lblPriority.gridy = 6;
		add(lblPriority, gbc_lblPriority);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Air ", "Land ", "Sea"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 7;
		add(comboBox, gbc_comboBox);
		GridBagConstraints gbc_doneButton = new GridBagConstraints();
		gbc_doneButton.insets = new Insets(0, 0, 0, 5);
		gbc_doneButton.gridx = 1;
		gbc_doneButton.gridy = 8;
		add(doneButton, gbc_doneButton);

	}

}
