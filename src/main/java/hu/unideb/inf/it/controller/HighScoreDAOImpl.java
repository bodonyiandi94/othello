package hu.unideb.inf.it.controller;

import hu.unideb.inf.it.model.HighScoreEntry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A ranglistaadatbazis kezeleset biztosito osztaly.
 * 
 * @author Andi
 *
 */
public class HighScoreDAOImpl implements HighScoreDAO {

	private static Logger logger = LoggerFactory
			.getLogger(HighScoreDAOImpl.class);

	/**
	 * {@inheritDoc}
	 */
	public void createEntry(HighScoreEntry entry) {
		Connection connection = getConnection();

		String sql = "insert into o_highscore(name, wins, losses, best_score) values (?,?,?,?)";
		PreparedStatement statement = getStatement(connection, sql);

		try {
			statement.setString(1, entry.getName());
			statement.setInt(2, entry.getWins());
			statement.setInt(3, entry.getLosses());
			statement.setFloat(4, entry.getBestScore());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

		execute(statement);

		closeStatement(statement);
		closeConnection(connection);
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateEntry(HighScoreEntry entry) {
		Connection connection = getConnection();

		String sql = "update o_highscore set wins=?, losses=?, best_score=? where name = ?";
		PreparedStatement statement = getStatement(connection, sql);

		try {
			statement.setInt(1, entry.getWins());
			statement.setInt(2, entry.getLosses());
			statement.setFloat(3, entry.getBestScore());
			statement.setString(4, entry.getName());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

		execute(statement);

		closeStatement(statement);
		closeConnection(connection);
	}

	/**
	 * {@inheritDoc}
	 */
	public HighScoreEntry getEntryByPlayerName(String name) {
		Connection connection = getConnection();

		String sql = "select name, wins, losses, best_score from o_highscore where name=?";
		PreparedStatement statement = getStatement(connection, sql);

		try {
			statement.setString(1, name);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

		ResultSet rs = executeQuery(statement);
		List<HighScoreEntry> entries = convertResultSet(rs);
		HighScoreEntry entry = entries.size()>0?entries.get(0):null;

		closeStatement(statement);
		closeConnection(connection);

		return entry;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<HighScoreEntry> getBestEntries(int number) {
		Connection connection = getConnection();

		String sql = "select name, wins, losses, best_score from o_highscore where rownum<=? order by wins desc";
		PreparedStatement statement = getStatement(connection, sql);

		try {
			statement.setInt(1, number);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

		ResultSet rs = executeQuery(statement);
		List<HighScoreEntry> entries = convertResultSet(rs);

		closeStatement(statement);
		closeConnection(connection);

		return entries;
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			logger.info("Opening connection");
			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g",
					"H_SFBAQU", "kassai");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return connection;
	}

	private PreparedStatement getStatement(Connection connection, String sql) {
		PreparedStatement statement = null;
		try {
			logger.info("Preparing statement");
			statement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return statement;
	}

	private boolean execute(PreparedStatement statement) {
		boolean result = false;
		try {
			logger.info("Executing insert/update/delete statement");
			result = statement.execute();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	private ResultSet executeQuery(PreparedStatement statement) {
		ResultSet result = null;
		try {
			logger.info("Executing select statement");
			result = statement.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	private List<HighScoreEntry> convertResultSet(ResultSet rs) {
		List<HighScoreEntry> result = new ArrayList<HighScoreEntry>();
		try {
			while (rs.next()) {
				HighScoreEntry entry = new HighScoreEntry();
				entry.setName(rs.getString(1));
				entry.setWins(rs.getInt(2));
				entry.setLosses(rs.getInt(3));
				entry.setBestScore(rs.getFloat(4));
				result.add(entry);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

		return result;
	}

	private void closeStatement(PreparedStatement statement) {
		try {
			logger.info("Closing statement");
			statement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void closeConnection(Connection connection) {
		try {
			logger.info("Closing connection");
			connection.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private HighScoreDAOImpl() {
		try {
			logger.info("Registering Oracle driver");
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private static HighScoreDAOImpl instance = new HighScoreDAOImpl();

	/**
	 * Visszaadja az adatbazis-kezelo peldanyt.
	 * 
	 * @return a kapcsolatot megvalosito objektumot
	 */
	public static HighScoreDAOImpl getInstance() {
		return instance;
	}
}