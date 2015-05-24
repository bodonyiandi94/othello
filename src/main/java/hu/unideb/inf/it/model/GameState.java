package hu.unideb.inf.it.model;

/**
 * A jatek jelenlegi allasat reprezentalo osztaly.
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
	 * Megadja, hogy a jatek folyamatban van-e meg.
	 * 
	 * @return {@code true}, ha a jateknak meg nincs vege, {@code false} egyebkent
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Beallitja, hogy a jatek folyamatban van-e meg, vagy nem.
	 * 
	 * @param active a jatek allapota
	 * @see #isActive()
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Visszaadja a nyertes jatekos indexet.
	 * 
	 * @return {@code -1}, ha a jateknak meg nincs vege, egyebkent pedig a nyertes jatekos indexet
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * Beallitja a nyertes jatekos indexet.
	 * 
	 * @param winner nyertes jatekos indexe
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}

	/**
	 * Letrehoz egy uj jatekallapotot a megadott tablamerettel.
	 * 
	 * @param tableSize a tablameret
	 */
	public GameState(int tableSize) {
		table=new Table(tableSize);
	}
	
	/**
	 * Letrehoz egy uj jatekallapotot a klasszikus tablamerettel.
	 */
	public GameState() {
		this.table=new Table();
	}

	/**
	 * Visszaadja a soron kovetkezo jatekos indexet.
	 * 
	 * @return kovetkezo jatekos indexe
	 */
	public int getNextPlayerId() {
		return nextPlayerId;
	}
	
	/**
	 * Visszaadja a soron kovetkezo jatekost.
	 * 
	 * @return soron kovetkezo jatekos
	 */
	public Player getNextPlayer(){
		return players[nextPlayerId];
	}
	
	/**
	 * Beallitja a soron kovetkezo jatekost.
	 * 
	 * @param nextPlayer kovetkezo jatekos indexe
	 */
	public void setNextPlayerId(int nextPlayer) {
		this.nextPlayerId = nextPlayer;
	}
	
	/**
	 * Visszaadja a jatekmezot
	 * 
	 * @return jatekmezo
	 */
	public Table getTable() {
		return table;
	}

	/**
	 * Visszaadja a jatekban resztvevo jatekosokat.
	 * 
	 * @return a jatekban resztvevo jatekosok
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * Beallitja a jatekban resztvevo jatekosokat.
	 * 
	 * @param players a jatekban resztvevo jatekosok tombje
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
	/**
	 * Beallitja a jatek egy jatekosat.
	 * 
	 * @param id a jatekos indexe
	 * @param player a jatekos objektum
	 */
	public void setPlayer(int id, Player player) {
		this.players[id] = player;
	}	
}