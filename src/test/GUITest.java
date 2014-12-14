package test;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ui.GUI;

public class GUITest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
