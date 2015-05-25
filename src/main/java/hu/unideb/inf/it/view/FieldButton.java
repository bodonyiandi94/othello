package hu.unideb.inf.it.view;

import hu.unideb.inf.it.controller.GameController;
import hu.unideb.inf.it.controller.Main;
import hu.unideb.inf.it.model.FigureType;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class FieldButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 2647873936363467778L;
	
	static Color NON_CHOICE_COLOR = new Color(0.0f, 0.0f, 0.0f);
	static Color CHOICE_COLOR = new Color(0.7f, 0.1f, 0.1f);
	static Icon BLACK_ICON, WHITE_ICON, EMPTY_ICON;

	static {
		try {
			BLACK_ICON = new ImageIcon(ImageIO.read(Main.class.getClassLoader()
					.getResource(FigureType.BLACK.getTexturePath())));
			WHITE_ICON = new ImageIcon(ImageIO.read(Main.class.getClassLoader()
					.getResource(FigureType.WHITE.getTexturePath())));
			EMPTY_ICON = new ImageIcon(ImageIO.read(Main.class.getClassLoader()
					.getResource(FigureType.NONE.getTexturePath())));
		} catch (IOException ex) {
		}
	}

	private int fieldX, fieldY;
	private FigureType figureType;
	private boolean choice;
	
	public void actionPerformed(ActionEvent e) {
		GameController.getInstance().handleButtonClick((FieldButton) e.getSource());
	}
	
	public FieldButton(int fieldX, int fieldY, int size) {
		super();
		this.fieldX = fieldX;
		this.fieldY = fieldY;
		
		setSize(size, size);
		setChoice(false);
		setFigureType(FigureType.NONE);
		
		addActionListener(this);
	}

	public int getFieldX() {
		return fieldX;
	}
	
	public int getFieldY() {
		return fieldY;
	}

	public FigureType getFigureType() {
		return figureType;
	}

	public void setFigureType(FigureType figureType) {
		this.figureType = figureType;
		
		switch (this.figureType) {
		case BLACK:
			setIcon(BLACK_ICON);
			break;

		case WHITE:
			setIcon(WHITE_ICON);
			break;

		case NONE:
			setIcon(EMPTY_ICON);
			break;
		}
	}

	public boolean isChoice() {
		return choice;
	}

	public void setChoice(boolean choice) {
		this.choice = choice;
		this.setFocusable(choice);
		if (choice) {
			setBackground(CHOICE_COLOR);
		} else {
			setBackground(NON_CHOICE_COLOR);
		}
	}
}
