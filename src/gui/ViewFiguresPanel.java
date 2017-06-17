package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.GridLayout;

import logic.KPSmartController;

public class ViewFiguresPanel extends JPanel implements ActionListener{

	JTextPane textPane;
	private KPSmartController controller;
	
	/**
	 * Create the panel.
	 */
	public ViewFiguresPanel(KPSmartController controller) {
		
		this.controller = controller;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{139, 192, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{1, 29, 258, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new GridLayout(1, 4, 0, 0));
		
		JButton btnViewAllEvents = new JButton("View All Events");
		GridBagConstraints gbc_btnViewAllEvents = new GridBagConstraints();
		gbc_btnViewAllEvents.insets = new Insets(0, 0, 5, 5);
		gbc_btnViewAllEvents.gridx = 0;
		gbc_btnViewAllEvents.gridy = 1;
		btnViewAllEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTextPane(controller.readBusinessFigures());
			}
		});
		add(btnViewAllEvents, gbc_btnViewAllEvents);
		
		JButton btnMailAnalysis = new JButton("Mail Analysis");
		GridBagConstraints gbc_btnMailAnalysis = new GridBagConstraints();
		gbc_btnMailAnalysis.insets = new Insets(0, 0, 5, 5);
		gbc_btnMailAnalysis.gridx = 1;
		gbc_btnMailAnalysis.gridy = 1;
		btnMailAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTextPane(controller.readBusinessFigures());
			}
		});
		add(btnMailAnalysis, gbc_btnMailAnalysis);
		
		JButton btnNewButton = new JButton("Total Revenue");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTextPane(controller.readBusinessFigures());
			}
		});
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNumberOfEvents = new JButton("Number of Events");
		GridBagConstraints gbc_btnNumberOfEvents = new GridBagConstraints();
		gbc_btnNumberOfEvents.insets = new Insets(0, 0, 5, 0);
		gbc_btnNumberOfEvents.gridx = 3;
		gbc_btnNumberOfEvents.gridy = 1;
		btnNumberOfEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTextPane(controller.readBusinessFigures());
			}
		});
		add(btnNumberOfEvents, gbc_btnNumberOfEvents);
		
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		JScrollPane jsp = new JScrollPane(textPane);
		GridBagConstraints gbc_jsp = new GridBagConstraints();
		gbc_jsp.insets = new Insets(0, 0, 0, 5);
		gbc_jsp.fill = GridBagConstraints.BOTH;
		gbc_jsp.gridwidth = 4;
		gbc_jsp.gridx = 0;
		gbc_jsp.gridy = 2;
		add(jsp, gbc_jsp);

	}
	
	public void updateTextPane(String content){
		this.textPane.setText(content);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonPressed = e.getActionCommand();
//		System.out.println(buttonPressed);
//		switch (buttonPressed.trim()) {
//		case "View All Events":
//			System.out.println("hit here");
//			updateTextPane(controller.readBusinessFigures());
//			break;
//		}
		//get controller to retreive xml and then update the text area
		
	}
	
	

}
