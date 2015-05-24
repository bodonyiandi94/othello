package hu.unideb.inf.it.view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerNameDialog {
	private String result;

	public String getResult() {
		return result;
	}

	public PlayerNameDialog(int playerId) {

		while (result == null || result.length() == 0)
			showInputDialog(playerId);

	}

	private void showInputDialog(int playerId) {
		String[] options = { "OK" };
		JPanel panel = new JPanel();
		JLabel lbl = new JLabel((playerId == 0 ? "Kerem az elso jatekos nevet:"
				: "Kerem a masodik jatekos nevet:"));
		JTextField txt = new JTextField("Jatekos " + (playerId+1), 20);
		panel.add(lbl);
		panel.add(txt);
		int selectedOption = JOptionPane.showOptionDialog(null, panel,
				(playerId == 0 ? "Elso jatekos" : "Masodik jatekos"),
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,
				options, options[0]);

		if (selectedOption == 0) {
			result = txt.getText();
		}
	}

}
