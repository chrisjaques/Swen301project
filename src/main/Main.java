package main;

import gui.KPSmartFrame;
import gui.LoginScreen;
import logic.KPSmartController;

public class Main{

	public static void main(String[] args) {
		KPSmartController controller = new KPSmartController();
		KPSmartFrame frame = new KPSmartFrame(controller);
		frame.setVisible(true);
	}

}
