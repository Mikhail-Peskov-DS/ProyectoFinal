package main;

import java.awt.EventQueue;

import banco.Banco;
import frontEnd.VentanaAutorizacion;

public class Main {

	public static void main(String[] args) {
		Banco banco = Banco.getInstance();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					VentanaAutorizacion frame = new VentanaAutorizacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
