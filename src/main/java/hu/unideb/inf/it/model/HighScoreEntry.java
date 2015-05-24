package hu.unideb.inf.it.model;

/**
 * A ranglista egy bejegyzeset reprezentalo osztaly.
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
	 * Lekeri a jatekos nevet.
	 * 
	 * @return a jatekos neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beallitja a bejegyzesben levo jatekos nevet.
	 * 
	 * @param name a jatekos neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszaadja a nyertes jatszmak szamat.
	 * 
	 * @return nyertes jatszmak szama
	 */
	public Integer getWins() {
		return wins;
	}
	/**
	 * Beallitja a nyertes jatszmak szamat.
	 * 
	 * @param wins a nyertes jatszmak szama
	 */
	public void setWins(Integer wins) {
		this.wins = wins;
	}
	/**
	 * Lekeri a vesztes jatszmak szamat.
	 * 
	 * @return a vesztes jatszmak szama
	 */
	public Integer getLosses() {
		return losses;
	}
	/**
	 * Beallitja a vesztes jatszmak szamat.
	 * 
	 * @param losses a vesztes jatszmak szama
	 */
	public void setLosses(Integer losses) {
		this.losses = losses;
	}
	/**
	 * Lekeri a jatekos legjobb eredmenyt.
	 * 
	 * @return a legjobb eredmeny
	 */
	public Float getBestScore() {
		return bestScore;
	}
	/**
	 * Beallitja a jatekos legjobb eredmenyet.
	 * 
	 * @param bestScore a jatekos legjobb eredmenye
	 */
	public void setBestScore(Float bestScore) {
		this.bestScore = bestScore;
	}
	/**
	 * Letrehoz egy uj ranglistabejegyzest a megadott parameterekkel.
	 * 
	 * @param name a jatekos neve
	 * @param wins a jatekos nyertes jatszmainak szama
	 * @param losses a jatekos vesztett jatszmainak szama
	 * @param bestScore a jatekos legjobb eredmenye
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
	 * Letrehoz egy uj ures ranglistabejegyzest.
	 */
	public HighScoreEntry() {
		super();
	}
}
