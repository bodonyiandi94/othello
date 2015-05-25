package hu.unideb.inf.it.controller;

import java.util.List;

import hu.unideb.inf.it.model.HighScoreEntry;

/**
 * A ranglistaadatbázis kezelésére szolgáló interfész.
 * 
 * <p>
 * A program és az adatbázis közti kommunikació a {@link HighScoreEntry} osztály
 * példányain keresztül zajlik.
 * 
 * @author Andi
 *
 */
public interface HighScoreDAO {

	/**
	 * Létrehoz egy ranglistabejegyzést az adatbázisba a megadott
	 * bejegyzésobjektum attribútumai által.
	 * 
	 * @param entry
	 *            bejegyzésobjektum, amelynek attribútumai által az
	 *            adatbázis-bejegyzés létrejön
	 */
	public void createEntry(HighScoreEntry entry);

	/**
	 * Frissíti az adatbázisban a megadott bejegyzésben lévő játékos adatait az
	 * ugyancsak itt lévő adatok segítségével.
	 * 
	 * @param entry
	 *            adatbázis-bejegyzés
	 */
	public void updateEntry(HighScoreEntry entry);

	/**
	 * Lekéri az adott jatékoshoz tartozó ranglista-bejegyzést az adatbázisból.
	 * 
	 * @param name
	 *            a játékos neve
	 * @return a játékoshoz tartozó adatbázis-bejegyzés, ha az létezik az
	 *         adatbázisban, {@code null} egyébként
	 */
	public HighScoreEntry getEntryByPlayerName(String name);

	/**
	 * Lekéri az adatbázisból a legjobb eredményeket, a nyert meccsek szerint
	 * csökkenő sorba rendezve.
	 * 
	 * @param number
	 *            lekérendő bejegyzések száma
	 * @return egy legfeljebb {@code number} darab elemet tartalmazó
	 *         bejegyzéslista
	 */
	public List<HighScoreEntry> getBestEntries(int number);
}
