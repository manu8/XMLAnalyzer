package main;

import ui.GUI;

public class Application implements Runnable {
	/**
	 * Lanza la interfaz de la aplicaci�n
	 */
	public void run() {
		new GUI();
	}
}
