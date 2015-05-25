package hu.unideb.inf.it.model;

/**
 * A játék jelenlegi állását reprezentáló osztály.
 * 
 * @author Andi
 *
 */
public class GameState {
	Player players[] = new Player[2];
	int nextPlayerId = 0;
	Table table;
	boolean active=true;
	int winner=-1;
	

	/**
	 * Megadja, hogy a játék folyamatban van-e még.
	 * 
	 * @return {@code true}, ha a játéknak még nincs vége, {@code false} egyébként
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Beallítja, hogy a játék folyamatban van-e még, vagy nem.
	 * 
	 * @param active a játék állapota
	 * @see #isActive()
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Visszaadja a nyertes játékos indexét.
	 * 
	 * @return {@code -1}, ha a játéknak még nincs vége, egyébként pedig a nyertes játékos indexét
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * Beallítja a nyertes játékos indexét.
	 * 
	 * @param winner nyertes játékos indexe
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * Létrehoz egy új játékállapotot a megadott táblamérettel.
	 * 
	 * @param tableSize a táblaméret
	 */
	public GameState(int tableSize) {
		table=new Table(tableSize);
	}
	
	/**
	 * Létrehoz egy új játékállapotot a klasszikus táblamérettel.
	 */
	public GameState() {
		this.table=new Table();
	}

	/**
	 * Visszaadja a soron következő játékos indexét.
	 * 
	 * @return következő játékos indexe
	 */
	public int getNextPlayerId() {
		return nextPlayerId;
	}
	
	/**
	 * Visszaadja a soron következő játékost.
	 * 
	 * @return soron következő játékos
	 */
	public Player getNextPlayer(){
		return players[nextPlayerId];
	}
	
	/**
	 * Beallítja a soron következő játékost.
	 * 
	 * @param nextPlayer következő játékos indexe
	 */
	public void setNextPlayerId(int nextPlayer) {
		this.nextPlayerId = nextPlayer;
	}
	
	/**
	 * Visszaadja a játékmezőt.
	 * 
	 * @return játékmező
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Visszaadja a játékban résztvevő játékosokat.
	 * 
	 * @return a játékban résztvevő játékosok
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Beallítja a játékban résztvevő játékosokat.
	 * 
	 * @param players a játékban résztvevő játékosok tömbje
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	/**
	 * Beallítja a játék egy játékosát.
	 * 
	 * @param id a játékos indexe
	 * @param player a játékos objektum
	 */
	public void setPlayer(int id, Player player) {
		this.players[id] = player;
	}	
}