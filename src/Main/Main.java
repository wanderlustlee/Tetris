package Main;
import java.awt.EventQueue;

import Controller.Login;
import view.LoginUI;
import view.MainFrame;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LoginUI().frame.setVisible(true);
					//MainFrame frame = new MainFrame();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
