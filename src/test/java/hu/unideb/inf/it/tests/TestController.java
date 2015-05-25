package hu.unideb.inf.it.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import hu.unideb.inf.it.controller.GameController;
import hu.unideb.inf.it.model.Figure;
import hu.unideb.inf.it.model.FigureType;
import hu.unideb.inf.it.model.GameState;
import hu.unideb.inf.it.view.FieldButton;
import hu.unideb.inf.it.view.MainFrame;

import org.junit.Test;

/**
 * A játékvezerlő osztályhoz tartozó tesztesetek.
 * 
 * @see GameController
 * @author Andi
 *
 */
public class TestController {

	@Test
	/**
	 * A figurák forgatásának tesztelése.
	 */
	public void testFigureFlipping() {
		GameState stateBefore = new GameState();
		GameState stateAfter = new GameState();
		GameController.getInstance().newGame(stateBefore, 0);
		GameController.getInstance().move(3, 2);
		stateAfter.getTable().getFigures()[3][2].setFigureType(FigureType.BLACK);
		stateAfter.getTable().getFigures()[3][3].setFigureType(FigureType.BLACK);

		Figure[][] expected = stateAfter.getTable().getFigures();
		Figure[][] generated = stateBefore.getTable().getFigures();

		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				assertEquals(expected[i][j].getFigureType(), generated[i][j].getFigureType());
			}
		}
	}

	@Test
	/**
	 * A körök váltásának tesztelése.
	 */
	public void testTurnSwitch() {
		GameController.getInstance().newGame(4, 0);
		assertEquals(0, GameController.getInstance().getGameState().getNextPlayerId());
		
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[1][0]);
		assertEquals(1, GameController.getInstance().getGameState().getNextPlayerId());
		
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[2][0]);
		assertEquals(0, GameController.getInstance().getGameState().getNextPlayerId());
		
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[3][2]);
		assertEquals(1, GameController.getInstance().getGameState().getNextPlayerId());
		
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[0][0]);
		assertEquals(1, GameController.getInstance().getGameState().getNextPlayerId());
	}

	@Test
	/**
	 * A javaslatok kiosztásának tesztelése.
	 * 
	 */
	public void testChoiceManagement() {
		GameController.getInstance().newGame(8, 0);
		FieldButton[][] buttons = MainFrame.getInstance().getButtons();

		GameController.getInstance().markChoices();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				if (buttons[i][j].isChoice()) {
					if (!((i == 2 && j == 3) || (i == 3 && j == 2) || (i == 5 && j == 4) || (i == 4 && j == 5))) {
						fail();
					}
				}
			}
		}

		GameController.getInstance().flushChoices();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				assertFalse(buttons[i][j].isChoice());
			}
		}
	}

	@Test
	/**
	 * A játékos mozgási képességeinek meghatározásának tesztelése.
	 */
	public void testPlayerMoveAbility() {
		GameController.getInstance().newGame(4, 0);
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[1][0]);
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[2][0]);
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[3][2]);

		GameController.getInstance().move(0, 0);
		GameController.getInstance().flushChoices();
		GameController.getInstance().switchTurns();
		GameController.getInstance().markChoices();
		assertFalse(GameController.getInstance().checkPlayerMoveAbility());
		GameController.getInstance().switchTurns();
		GameController.getInstance().markChoices();
		assertTrue(GameController.getInstance().checkPlayerMoveAbility());
	}

	@Test
	/**
	 * Teszteset a nyertes játékos meghatározására.
	 */
	public void testFindWinner() {
		GameController.getInstance().newGame(8, 0);
		GameController.getInstance().move(2, 3);
		GameController.getInstance().switchTurns();
		assertEquals(0, GameController.getInstance().findWinner());

		GameController.getInstance().move(2, 2);
		GameController.getInstance().switchTurns();
		GameController.getInstance().move(2, 1);
		GameController.getInstance().switchTurns();
		GameController.getInstance().move(2, 4);
		GameController.getInstance().switchTurns();
		GameController.getInstance().move(4, 5);
		GameController.getInstance().switchTurns();
		GameController.getInstance().move(2, 0);
		GameController.getInstance().switchTurns();
		assertEquals(1, GameController.getInstance().findWinner());
	}
	
	@Test
	/**
	 * Teszteset helytelen mezőre való lépésre.
	 */
	public void testMoveOnInvalidField(){
		GameController.getInstance().newGame(8,0);
		GameController.getInstance().handleButtonClick(MainFrame.getInstance().getButtons()[0][0]);
	}
	
	@Test
	/**
	 * Teszteset a játék végére
	 */
	public void testGameEnd() {
		GameController controller = GameController.getInstance();
		controller.addPlayer("Tesztjátékos 1");
		controller.addPlayer("Tesztjátékos 2");
		controller.newGame(4,0);

		controller.handleButtonClick(MainFrame.getInstance().getButtons()[0][1]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[0][2]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[1][3]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[2][0]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[3][1]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[3][2]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[2][3]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[1][0]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[3][0]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[0][0]);
		controller.handleButtonClick(MainFrame.getInstance().getButtons()[3][3]);
		
		assertFalse(controller.getGameState().isActive());
	}
}
