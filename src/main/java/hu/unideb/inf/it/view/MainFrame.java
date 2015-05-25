package hu.unideb.inf.it.view;

import hu.unideb.inf.it.controller.GameController;
import hu.unideb.inf.it.controller.HighScoreDAOImpl;
import hu.unideb.inf.it.model.GameState;
import hu.unideb.inf.it.model.HighScoreEntry;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1917452349263691833L;
	
	private static Logger logger = LoggerFactory
			.getLogger(MainFrame.class);

	private static final int HEADER_HEIGHT = 30;
	private static final int WIDTH = 750;
	private static final int HEIGHT = 750;
	private static final String TITLE = "Othello";
	private static final Color BACKGROUND_COLOR = new Color(0, 255, 0);
	private static final Font NORMAL_FONT = new Font(Font.SERIF, Font.PLAIN, 24);
	private static final Font BOLD_FONT = new Font(Font.SERIF, Font.BOLD, 24);

	private GameState gameState;
	private JPanel namesPanel = new JPanel();
	private JPanel tablePanel = new JPanel();
	private JTextArea[] playerNames = new JTextArea[2];
	private FieldButton[][] buttons;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Jatek");
	private JMenuItem[] menuItems = new JMenuItem[3];

	/**
	 * @return the gameState
	 */
	public GameState getGameState() {
		return gameState;
	}

	private void init() {
		logger.info("Initializing main frame");
		setTitle(TITLE);
		setSize(WIDTH, HEIGHT + HEADER_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(BACKGROUND_COLOR);

		setLayout(new BorderLayout());

		namesPanel.setSize(WIDTH, HEADER_HEIGHT);
		namesPanel.setLayout(new BorderLayout());
		namesPanel.setBackground(new Color(0, 0, 0, 0));

		for (int i = 0; i < 2; i++) {
			playerNames[i] = new JTextArea();
			playerNames[i].setEditable(false);
			playerNames[i].setForeground(Color.BLUE);
			playerNames[i].setBackground(new Color(0, 0, 0, 0));
			namesPanel.add(playerNames[i], (i == 0 ? BorderLayout.WEST
					: BorderLayout.EAST));
		}
		add(namesPanel, BorderLayout.NORTH);

		tablePanel.setSize(WIDTH, HEIGHT);
		tablePanel.setBackground(new Color(0, 0, 0, 0));
		add(tablePanel, BorderLayout.CENTER);

		menuItems[0] = new JMenuItem("Uj jatek");
		menuItems[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().newGame(getTableSize());
				
			}
		});
		
		menuItems[1] = new JMenuItem("Ranglista");
		menuItems[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.getInstance().showHighScoreWindow(HighScoreDAOImpl.getInstance().getBestEntries(10));
			}
		});
		
		menuItems[2] = new JMenuItem("Kilepes");
		menuItems[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameController.getInstance().stop();
			}
		});
		gameMenu.add(menuItems[0]);
		gameMenu.add(menuItems[1]);
		gameMenu.add(menuItems[2]);
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);
		
		logger.info("Main frame successfully initialized");
	}

	public void initTable(GameState gameState) {
		logger.info("Setting up main frame to display a table of size " + gameState.getTable().getTableSize());
		tablePanel.removeAll();

		this.gameState = gameState;

		for (int i = 0; i < 2; i++) {
			playerNames[i].setText(gameState.getPlayers()[i].getName() + " ("
					+ (i == 0 ? "fekete" : "feher") + ")");
		}

		int tableSize = gameState.getTable().getTableSize();
		tablePanel.setLayout(new GridLayout(tableSize, tableSize));

		int buttonSize = (int) (tablePanel.getSize().getHeight() / tableSize);
		this.buttons = new FieldButton[tableSize][tableSize];

		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				this.buttons[i][j] = new FieldButton(i, j, buttonSize);
				tablePanel.add(this.buttons[i][j]);
			}
		}

		updateView();
		
		logger.info("Display set up successful");
	}

	public String getPlayerName(int playerId) {
		logger.info("Querying the nem of player #" + (playerId+1));
		PlayerNameDialog inputField = new PlayerNameDialog(playerId);
		logger.info("The queried name of player #" + (playerId+1) + " is " + inputField.getResult());
		return inputField.getResult();
	}
	
	public void showHighScoreWindow(List<HighScoreEntry> entries){
		logger.info("Showing highscore window");
		HighScoreWindow window=new HighScoreWindow(this, entries);
		
		window.setVisible(true);
	}

	public int getTableSize() {
		logger.info("Querying tablesize for new game");
		
		NewGameDialog inputField = new NewGameDialog();
		return inputField.getResult();
	}
	
	public void showVictoryDialog(int playerId){
		logger.info("Displaying victory dialog, winner is player #" + (playerId+1));
		
		new VictoryDialog(playerId);
	}

	public void updateView() {
		logger.info("Updating main frame...");
		for (int i = 0; i < this.buttons.length; i++) {
			for (int j = 0; j < this.buttons[i].length; j++) {
				this.buttons[i][j].setFigureType(gameState.getTable()
						.getFigures()[i][j].getFigureType());
				this.buttons[i][j].validate();
				this.buttons[i][j].repaint();
			}
		}
		
		this.playerNames[gameState.getNextPlayerId()].setFont(BOLD_FONT);
		this.playerNames[1-gameState.getNextPlayerId()].setFont(NORMAL_FONT);

		getContentPane().validate();
		getContentPane().repaint();
		
		logger.info("Main frame updated");
	}

	/**
	 * @return the buttons
	 */
	public FieldButton[][] getButtons() {
		return buttons;
	}

	private static MainFrame instance = new MainFrame();

	public static MainFrame getInstance() {
		return instance;
	}

	private MainFrame() {
		init();
	}

}
