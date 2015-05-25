package hu.unideb.inf.it.controller;

import java.awt.event.ActionListener;

import hu.unideb.inf.it.model.Figure;
import hu.unideb.inf.it.model.FigureType;
import hu.unideb.inf.it.model.GameState;
import hu.unideb.inf.it.model.HighScoreEntry;
import hu.unideb.inf.it.model.Player;
import hu.unideb.inf.it.model.Table;
import hu.unideb.inf.it.view.FieldButton;
import hu.unideb.inf.it.view.MainFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A jatéklogikát megvalósító osztály.
 * 
 * <p>
 * A játék indításához az alábbi utasítás elvégzése szükséges:
 * 
 * <pre>
 * 		GameController.getInstance().start();
 * </pre>
 * 
 * <p>
 * A fenti metódushívás eredménye az alábbi tevékenységsorozat végrehajtásával
 * ekvivalent:
 * 
 * <pre>
 * 		GameController controller = GameContoller.getInstance();
 * 		controller.addPlayer(&quot;Játékos 1&quot;);
 * 		controller.addPlayer(&quot;Játékos 2&quot;);
 * 		controller.newGame(8, 0);
 * </pre>
 * 
 * <p>
 * Manuális körváltás az alábbi módon lehetséges:
 * 
 * <pre>
 * 		GameController controller = GameContoller.getInstance();
 * 		controller.setTurnPlayer(0);
 * 		System.out.println(controller.getGameState().getNextPlayerId());
 * 		System.out.println(controller.getGameState().getNextPlayer().getName());
 * </pre>
 * 
 * <p>
 * A felhasználói interakció kezeléséhez szükség van az eseményt kiváltó
 * {@link FieldButton} példányra. Ha {@code e} egy {@link ActionListener}
 * példány {@link ActionListener#actionPerformed(java.awt.event.ActionEvent)}
 * argumentuma, akkor az alábbi példakódban szereplő módon történhet a mezőkre
 * történő kattintás kezelése:
 * 
 * <pre>
 * 		GameContoller.getInstance().handleButtonClick((FieldButton) e.getSource());
 * </pre>
 * 
 * @author Andi
 *
 */
public class GameController {
	private static Logger logger = LoggerFactory.getLogger(GameController.class);

	private GameState gameState;
	private Player players[] = new Player[] { new Player("", FigureType.BLACK), new Player("", FigureType.WHITE) };

	/**
	 * Inicializálja a játékot, felkészítve a futásra.
	 * 
	 * <p>
	 * Bekéri a két játékos nevét, megjeleníti a játéktáblát, majd elindít egy
	 * klasszikus játékot.
	 */
	public void start() {
		MainFrame.getInstance().setVisible(true);
		addPlayer(MainFrame.getInstance().getPlayerName(0));
		addPlayer(MainFrame.getInstance().getPlayerName(1));
		newGame();
	}

	/**
	 * Befejezteti a program működését
	 */
	public void stop() {
		System.exit(0);
	}

	/**
	 * Hozzáad egy új játékost a játékhoz a megadott névvel az első üres helyre.
	 * 
	 * @param name
	 *            a játékos neve
	 */
	public void addPlayer(String name) {
		int id = -1;
		for (int i = 0; i < 2; i++) {
			if (players[i].getName().equals("")) {
				id = i;
				break;
			}
		}

		if (id == -1) {
			throw new RuntimeException("mar megvan a ket jatekos");
		}
		players[id].setName(name);
	}

	/**
	 * Visszaadja a folyamatban lévő játék állását.
	 * 
	 * @return a játék állása
	 * @see GameState
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * Visszaadja a játékosobjektumokat.
	 * 
	 * @return játékosok tömbje
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Elindít egy új, klasszikus játekot, fekete kezdőjátékossal.
	 */
	public void newGame() {
		newGame(new GameState(), 0);
	}

	/**
	 * Elindít egy új játékot a megadott táblamérettel és fekete
	 * kezdőjátékossal.
	 * 
	 * @param tableSize
	 *            táblaméret
	 */
	public void newGame(int tableSize) {
		newGame(tableSize, 0);
	}

	/**
	 * Elindít egy új játékot a megadott táblamérettel és a megadott
	 * kezdőjátékossal.
	 * 
	 * @param tableSize
	 *            táblaméret
	 * @param startingPlayer
	 *            kezdőjátékos indexe
	 */
	public void newGame(int tableSize, int startingPlayer) {
		newGame(new GameState(tableSize), startingPlayer);
	}

	/**
	 * Elindít egy új játékot a megadott játékállással és kezdőjátékossal.
	 * 
	 * @param gameState
	 *            kezdőállás
	 * @param startingPlayer
	 *            kezdőjátékos indexe
	 */
	public void newGame(GameState gameState, int startingPlayer) {
		this.gameState = gameState;
		this.gameState.setPlayer(0, players[0]);
		this.gameState.setPlayer(1, players[1]);
		setTurnPlayer(startingPlayer);

		MainFrame.getInstance().initTable(gameState);

		markChoices();
	}

	private void endGame() {
		updateHighScoreEntry(players[0]);
		updateHighScoreEntry(players[1]);
		MainFrame.getInstance().showVictoryDialog(gameState.getWinner());
	}

	private void updateHighScoreEntry(Player player) {
		FigureType playerFigure = player.getFigureType();
		Figure[][] figures = gameState.getTable().getFigures();
		int ally = 0, enemy = 0;

		for (int i = 0; i < figures.length; i++) {
			for (int j = 0; j < figures[i].length; j++) {
				if (figures[i][j].getFigureType().equals(playerFigure)) {
					ally++;
				} else {
					enemy++;
				}
			}
		}

		HighScoreEntry entry = HighScoreDAOImpl.getInstance().getEntryByPlayerName(player.getName());
		if (entry == null) {
			entry = new HighScoreEntry();
			entry.setName(player.getName());
			entry.setWins(0);
			entry.setLosses(0);
			entry.setBestScore(0.0f);
			HighScoreDAOImpl.getInstance().createEntry(entry);
		}

		if (ally > enemy) {
			entry.setWins(entry.getWins() + 1);
			entry.setBestScore(Math.max(entry.getBestScore(), (float) ally / (float) (ally + enemy)));
		} else {
			entry.setLosses(entry.getLosses() + 1);
		}

		HighScoreDAOImpl.getInstance().updateEntry(entry);
	}

	private void flipFigures(FigureType playerFigure, int x, int y, int dirX, int dirY) {
		final int originalX = x, originalY = y;
		Table table = GameController.getInstance().getGameState().getTable();
		boolean flip = false;

		while (true) {
			x += dirX;
			y += dirY;

			if (x < 0 || y < 0 || x >= table.getTableSize() || y >= table.getTableSize()) {
				break;
			}

			if (table.getFigures()[x][y].getFigureType().equals(FigureType.NONE)) {
				break;
			} else if (table.getFigures()[x][y].getFigureType().equals(playerFigure)) {
				flip = true;
				break;
			}
		}

		if (!flip) {
			return;
		}

		x = originalX;
		y = originalY;

		while (true) {
			x += dirX;
			y += dirY;

			if (table.getFigures()[x][y].getFigureType().equals(playerFigure))
				break;
			table.getFigures()[x][y].setFigureType(playerFigure);
		}
	}

	/**
	 * A jelenlegi játékos lépését hajtja végre a táblán.
	 *
	 * @param x
	 *            a lerakott bábu {@code X} koordinátája
	 * @param y
	 *            a lerakott bábu {@code Y} koordinátája
	 */
	public void move(int x, int y) {
		FigureType playerFigure = GameController.getInstance().getGameState().getNextPlayer().getFigureType();

		GameController.getInstance().getGameState().getTable().getFigures()[x][y].setFigureType(playerFigure);

		flipFigures(playerFigure, x, y, -1, -1);
		flipFigures(playerFigure, x, y, 0, -1);
		flipFigures(playerFigure, x, y, 1, -1);
		flipFigures(playerFigure, x, y, -1, 0);
		flipFigures(playerFigure, x, y, 1, 0);
		flipFigures(playerFigure, x, y, -1, 1);
		flipFigures(playerFigure, x, y, 0, 1);
		flipFigures(playerFigure, x, y, 1, 1);
	}

	/**
	 * Beállítja a soron következő játékost.
	 * 
	 * @param playerId
	 *            a következő játékos indexe
	 */
	public void setTurnPlayer(int playerId) {
		GameController.getInstance().getGameState().setNextPlayerId(playerId);
	}

	/**
	 * Átadja a kört a következő játékosnak.
	 */
	public void switchTurns() {
		GameController.getInstance().getGameState()
				.setNextPlayerId(1 - GameController.getInstance().getGameState().getNextPlayerId());
	}

	private void findValidField(FigureType playerFigure, int x, int y, int dirX, int dirY) {
		Table table = GameController.getInstance().getGameState().getTable();
		boolean enemyFound = false;

		while (true) {
			x += dirX;
			y += dirY;

			if (x < 0 || y < 0 || x >= table.getTableSize() || y >= table.getTableSize()) {
				break;
			}

			if (table.getFigures()[x][y].getFigureType().equals(FigureType.NONE)) {
				if (enemyFound) {
					MainFrame.getInstance().getButtons()[x][y].setChoice(true);
				}
				break;
			} else if (!table.getFigures()[x][y].getFigureType().equals(playerFigure)) {
				enemyFound = true;
			} else {
				break;
			}
		}
	}

	private void findValidFields(FigureType playerFigure, int x, int y) {
		findValidField(playerFigure, x, y, -1, -1);
		findValidField(playerFigure, x, y, 0, -1);
		findValidField(playerFigure, x, y, 1, -1);
		findValidField(playerFigure, x, y, -1, 0);
		findValidField(playerFigure, x, y, 1, 0);
		findValidField(playerFigure, x, y, -1, 1);
		findValidField(playerFigure, x, y, 0, 1);
		findValidField(playerFigure, x, y, 1, 1);
	}

	/**
	 * Kitörli a lépési lehetőségek listáját.
	 */
	public void flushChoices() {
		Table table = GameController.getInstance().getGameState().getTable();
		FieldButton[][] buttons = MainFrame.getInstance().getButtons();

		for (int i = 0; i < table.getTableSize(); i++) {
			for (int j = 0; j < table.getTableSize(); j++) {
				buttons[i][j].setChoice(false);
			}
		}
	}

	/**
	 * Megkeresi a léphető mezőket és megjelöli őket.
	 */
	public void markChoices() {
		Table table = GameController.getInstance().getGameState().getTable();
		FigureType playerFigure = GameController.getInstance().getGameState().getNextPlayer().getFigureType();

		for (int i = 0; i < table.getTableSize(); i++) {
			for (int j = 0; j < table.getTableSize(); j++) {
				if (table.getFigures()[i][j].getFigureType().equals(playerFigure)) {
					findValidFields(playerFigure, i, j);
				}
			}
		}
	}

	/**
	 * Ellenőrzi, hogy a soron következő játékosnak van-e lehetősége lépni.
	 * 
	 * @return {@code true}, ha létezik olyan mező, ahova a játékos léphet,
	 *         {@code false} egyébként
	 */
	public boolean checkPlayerMoveAbility() {
		Table table = GameController.getInstance().getGameState().getTable();
		FieldButton[][] buttons = MainFrame.getInstance().getButtons();

		for (int i = 0; i < table.getTableSize(); i++) {
			for (int j = 0; j < table.getTableSize(); j++) {
				if (buttons[i][j].isChoice()) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Megkeresi, hogy melyik játékosnak van több bábuja a mezőn.
	 * 
	 * @return a nyertes játékos indexe
	 */
	public int findWinner() {
		int black = 0, white = 0;
		Figure[][] figures = GameController.getInstance().getGameState().getTable().getFigures();

		for (int i = 0; i < figures.length; i++) {
			for (int j = 0; j < figures[i].length; j++) {
				switch (figures[i][j].getFigureType()) {
				case BLACK:
					black++;
					break;

				case WHITE:
					white++;
					break;

				default:
					break;
				}
			}
		}

		return black > white ? 0 : 1;
	}

	/**
	 * A mezőn történő kattintást kezeli.
	 * 
	 * @param button
	 *            a megnyomott gomb
	 */
	public void handleButtonClick(FieldButton button) {
		if (!button.isChoice()) {
			return;
		}

		move(button.getFieldX(), button.getFieldY());
		flushChoices();
		switchTurns();
		markChoices();

		if (!checkPlayerMoveAbility()) {
			switchTurns();
			markChoices();

			if (!checkPlayerMoveAbility()) {
				gameState.setActive(false);
			}
		}

		MainFrame.getInstance().updateView();

		if (!gameState.isActive()) {
			gameState.setWinner(findWinner());
			endGame();
		}
	}

	private static GameController instance = new GameController();

	/**
	 * Visszaadja a játékvezérlő objektumot.
	 * 
	 * @return a vezérlő példány
	 */
	public static GameController getInstance() {
		return instance;
	}

	private GameController() {

	}
}
