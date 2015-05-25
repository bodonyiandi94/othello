package hu.unideb.inf.it.model;

/**
 * A játékmezőt reprezentaló osztaly.
 * 
 * @author Andi
 *
 */
public class Table {
	private Figure[][] figures;
	private int tableSize;

	/**
	 * Létrehoz egy új, klasszikus méretű játéktáblát.
	 */
	public Table() {
		this(8);
	}

	/**
	 * Létrehoz egy új táblát a megadott mérettel.
	 * 
	 * @param tableSize a tábla mérete, amelynek párosnak kell lennie
	 */
	public Table(int tableSize) {
		if (tableSize%2==1){
			throw new RuntimeException("nem jo a tablemeret");
		}
		this.tableSize = tableSize;
		this.figures = new Figure[tableSize][tableSize];
		initTable();
	}
	
	private void initTable(){
		int center=tableSize/2;
		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				FigureType type=FigureType.NONE;
				if ((i==center-1 || i==center) && (j==center-1 || j==center)){
					type=(i==j)?FigureType.WHITE:FigureType.BLACK;
				}
				figures[i][j]=new Figure(type);
			}
		}
	}
	
	/**
	 * Visszaadja a tábla méretet.
	 * 
	 * @return a tábla mérete
	 */
	public int getTableSize() {
		return tableSize;
	}
	
	/**
	 * Visszaadja a táblán lévő bábuk listáját.
	 * 
	 * @return a bábuk listája egy tömbben
	 */
	public Figure[][] getFigures() {
		return figures;
	}	
}
