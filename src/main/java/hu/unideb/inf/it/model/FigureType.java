package hu.unideb.inf.it.model;

/**
 * A játék által használt bábuk típusa.
 * 
 * @author Andi
 *
 */
public enum FigureType {
	/**
	 * Fekete színű bábu
	 */
	BLACK("figure_black.png"), 
	
	/**
	 * Fehér színű bábu
	 */
	WHITE("figure_white.png"), 
	
	/**
	 * Üres mező
	 */
	NONE("figure_empty.png");
	
	private String texturePath;
	
	/**
	 * Visszaadja a bábutípus által használt textúra elérési útját.
	 * 
	 * @return a textúra elérési útvonala
	 */
	public String getTexturePath() {
		return texturePath;
	}
	
	private FigureType(String texturePath){
		this.texturePath = texturePath;
	}
}
