package main;

import gui.LoginScreen;
import logic.KPSmartController;

public class Main{

	public static void main(String[] args) {
		KPSmartController controller = new KPSmartController();
		LoginScreen loginScreen = new LoginScreen(controller);
		loginScreen.openLoginScreen();
	}

}
