package main;

import ui.GUI;

public class Application implements Runnable {
	/**
	 * Lanza la interfaz de la aplicación
	 */
	public void run() {
		new GUI();
	}
	
	public static void main(String[] args) {
		new Application().run();
	}
}
