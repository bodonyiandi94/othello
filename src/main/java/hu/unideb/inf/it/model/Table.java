package hu.unideb.inf.it.model;

/**
 * A jatekmezot reprezentalo osztaly.
 * 
 * @author Andi
 *
 */
public class Table {
	private Figure[][] figures;
	private int tableSize;

	/**
	 * Letrehoz egy uj, klasszikus meretu jatektablat.
	 */
	public Table() {
		this(8);
	}

	/**
	 * Letrehoz egy uj tablat a megadott merettel.
	 * 
	 * @param tableSize a tabla merete, amelynek parosnak kell lennie
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
	 * Visszaadja a tabla meretet.
	 * 
	 * @return a tabla merete
	 */
	public int getTableSize() {
		return tableSize;
	}
	
	/**
	 * Visszaadja a tablan levo babuk listajat.
	 * 
	 * @return a babuk listaja egy tombben
	 */
	public Figure[][] getFigures() {
		return figures;
	}	
}
