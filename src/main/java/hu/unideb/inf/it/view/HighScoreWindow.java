package hu.unideb.inf.it.view;

import hu.unideb.inf.it.model.HighScoreEntry;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

public class HighScoreWindow extends JDialog {
	private static final long serialVersionUID = -3498035476642004684L;
	
	private JTable dataTable;
	private JButton okButton;

	public HighScoreWindow(JFrame frame, List<HighScoreEntry> entries) {
		super(frame, true);
		setTitle("Ranglista");
		setSize(400, 500);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] columnNames = { "#", "Név", "Győzelem", "Vereség",
				"Legjobb eredmény" };
		Object[][] data = new Object[10][5];
		for (int i = 0; i < 10; i++) {
			data[i][0] = (i + 1) + ".";
			if (i < entries.size()) {
				HighScoreEntry entry=entries.get(i);
				data[i][1] = entry.getName();
				data[i][2] = entry.getWins();
				data[i][3] = entry.getLosses();
				data[i][4] = entry.getBestScore();
			} else {
				data[i][1] = "";
				data[i][2] = "";
				data[i][3] = "";
				data[i][4] = "";
			}
		}

		dataTable = new JTable(data, columnNames);
		dataTable.setShowGrid(false);
		dataTable.setPreferredSize(new Dimension(200, 200));

		add(dataTable.getTableHeader(), BorderLayout.PAGE_START);
		add(dataTable, BorderLayout.CENTER);

		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				HighScoreWindow.this.dispose();

			}
		});
		add(okButton, BorderLayout.SOUTH);

		pack();

		setLocationRelativeTo(frame);
	}

}
