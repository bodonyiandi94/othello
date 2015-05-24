package hu.unideb.inf.it.controller;

import java.util.List;

import hu.unideb.inf.it.model.HighScoreEntry;

/**
 * A ranglistaadatbazis kezelesere szolgalo interfesz.
 * 
 * <p>
 * A program es az adatbazis kozti kommunikacio a {@link HighScoreEntry} osztaly
 * peldanyain keresztul zajlik.
 * 
 * @author Andi
 *
 */
public interface HighScoreDAO {

	/**
	 * Letrehoz egy ranglistabejegyzest az adatbazisba a megadott
	 * bejegyzesobjektum attributumai altal.
	 * 
	 * @param entry
	 *            bejegyzesobjektum, amelynek attributumai altal az
	 *            adatbazis-bejegyzes letrejon
	 */
	public void createEntry(HighScoreEntry entry);

	/**
	 * Frissiti az adatbazisban a megadott bejegyzesben levo jatekos adatait az
	 * ugyancsak itt levo adatok segitsegevel.
	 * 
	 * @param entry
	 *            adatbazis-bejegyzes
	 */
	public void updateEntry(HighScoreEntry entry);

	/**
	 * Lekeri az adott jatekoshz tartozo ranglista-bejegyzest az adatbazisbol.
	 * 
	 * @param name
	 *            a jatekos neve
	 * @return a jatekoshoz tartozo adatbazis-bejegyzes, ha az letezik az
	 *         adatbazisban, {@code null} egyebkent
	 */
	public HighScoreEntry getEntryByPlayerName(String name);

	/**
	 * Lekeri az adatbazisbol a legjobb eredmenyeket, a nyert meccsek szerint
	 * csokkeno sorba rendezve.
	 * 
	 * @param number
	 *            lekerendo bejegyzesek szama
	 * @return egy legfeljebb {@code number} darab elemet tartalmazo
	 *         bejegyzeslista
	 */
	public List<HighScoreEntry> getBestEntries(int number);
}
