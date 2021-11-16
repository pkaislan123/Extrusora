package main.java.principal;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import main.java.controller.ConexaoArduino;
import main.java.gui.TelaPadrao;

public class Main {


	
	public static void main (String args[]) {
		
		try {
			for ( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() ) {
			if ( "Nimbus".equals( info.getName() ) ) {
			UIManager.setLookAndFeel( info.getClassName() );
			break;
			}
			}
			} catch ( UnsupportedLookAndFeelException exc ) {
			exc.printStackTrace();
			} catch ( ClassNotFoundException exc ) {
			exc.printStackTrace();
			} catch ( InstantiationException exc ) {
			exc.printStackTrace();
			} catch ( IllegalAccessException exc ) {
			exc.printStackTrace();
			}
		
		TelaPadrao tela = new TelaPadrao(null);
		tela.setVisible(true);
	}
	
}
