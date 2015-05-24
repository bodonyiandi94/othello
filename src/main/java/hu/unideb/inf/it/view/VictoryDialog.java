package hu.unideb.inf.it.view;

import hu.unideb.inf.it.controller.GameController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VictoryDialog {

	public VictoryDialog(int playerId) {
		showInputDialog(playerId);
	}

	private void showInputDialog(int playerId) {
		String[] options = { "OK" };
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel("A jateknak vege! " + GameController.getInstance().getPlayers()[playerId].getName() + " nyerte a jatekot.");
		panel.add(lbl);
		JOptionPane.showOptionDialog(null, panel, "Jatek vege",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
	}
}
