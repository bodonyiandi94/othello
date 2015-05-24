package hu.unideb.inf.it.view;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NewGameDialog {
	private Integer result;

	public int getResult() {
		return result;
	}

	public NewGameDialog() {
		showInputDialog();

	}

	private void showInputDialog() {
		String[] options = { "OK", "Megse" };
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel lbl = new JLabel("Valassz tablameretet!");

		Integer[] tableSizes = { 4, 6, 8, 10, 12 };
		JComboBox<Integer> tableSizeBox = new JComboBox<Integer>(tableSizes);

		panel.add(lbl);
		panel.add(tableSizeBox);

		int selectedOption = JOptionPane.showOptionDialog(null, panel, "Uj jatek", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selectedOption == 0) {
			result = Integer.parseInt(tableSizeBox.getSelectedItem().toString());
		}
	}
}
