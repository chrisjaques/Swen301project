package main;

import gui.LoginScreen;
import logic.KPSmartController;

public class Main{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		KPSmartController controller = new KPSmartController();
		LoginScreen loginScreen = new LoginScreen(controller);
		loginScreen.openLoginScreen();
	}

}
