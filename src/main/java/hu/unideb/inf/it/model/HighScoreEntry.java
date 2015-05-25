package hu.unideb.inf.it.model;

/**
 * A ranglista egy bejegyzését reprezentáló osztály.
 * 
 * @author Andi
 *
 */
public class HighScoreEntry {
	private String name;
	private Integer wins;
	private Integer losses;
	private Float bestScore;
	
	/**
	 * Lekéri a játékos nevét.
	 * 
	 * @return a játékos neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beállítja a bejegyzésben lévő játékos nevét.
	 * 
	 * @param name a játékos neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszaadja a nyertes jászmák számát.
	 * 
	 * @return nyertes jászmák száma
	 */
	public Integer getWins() {
		return wins;
	}
	/**
	 * Beállítja a nyertes jászmák számát.
	 * 
	 * @param wins a nyertes jászmák száma
	 */
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	/**
	 * Lekéri a vesztes jászmák számát.
	 * 
	 * @return a vesztes jászmák száma
	 */
	public Integer getLosses() {
		return losses;
	}
	/**
	 * Beállítja a vesztes jászmák számát.
	 * 
	 * @param losses a vesztes jászmák száma
	 */
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	/**
	 * Lekéri a játékos legjobb eredményt.
	 * 
	 * @return a legjobb eredmény
	 */
	public Float getBestScore() {
		return bestScore;
	}
	/**
	 * Beállítja a játékos legjobb eredményét.
	 * 
	 * @param bestScore a játékos legjobb eredménye
	 */
	public void setBestScore(Float bestScore) {
		this.bestScore = bestScore;
	}
	/**
	 * Létrehoz egy új ranglistabejegyzést a megadott paraméterekkel.
	 * 
	 * @param name a játékos neve
	 * @param wins a játékos nyertes jászmáinak száma
	 * @param losses a játékos vesztett jászmáinak száma
	 * @param bestScore a játékos legjobb eredménye
	 */
	public HighScoreEntry(String name, Integer wins, Integer losses,
			Float bestScore) {
		super();
		this.name = name;
		this.wins = wins;
		this.losses = losses;
		this.bestScore = bestScore;
	}
	/**
	 * Létrehoz egy új üres ranglistabejegyzést.
	 */
	public HighScoreEntry() {
		super();
	}
}
