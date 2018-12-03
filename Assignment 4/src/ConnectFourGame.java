
public class ConnectFourGame {
	private int nColumns;
	private int nRows;
	private int numToWin;
	private ConnectFourEnum[][] grid;
	private ConnectFourEnum gameState;
	private ConnectFourEnum turn;
	
	ConnectFourGame(ConnectFourEnum initialTurn){
		this(8,8,4,initialTurn);
	}
	
	ConnectFourGame(int nRows,int nColumns,int numToWin,ConnectFourEnum initialTurn){
		turn = initialTurn;
		this.nRows = nRows;
		this.nColumns = nColumns;
		this.numToWin = numToWin;
		reset(initialTurn);
	}
	
	public void reset(ConnectFourEnum initialTurn){
		grid = new ConnectFourEnum[nRows][nColumns];
		for (int y = 0; y<nColumns; ++y) {
			for (int x = 0; x<nRows; ++x) {
				grid[x][y] = ConnectFourEnum.EMPTY;
			}
		}
		turn = initialTurn;
		gameState = ConnectFourEnum.IN_PROGRESS;
	}
	
	public void takeTurn(int row,int column){
		if (row < nRows && column < nColumns) {
			if (grid[row][column] == ConnectFourEnum.EMPTY) {
				grid[row][column] = turn;
				gameState = findWinner();
				if (turn == ConnectFourEnum.RED)
					turn = ConnectFourEnum.BLACK;
				else
					turn = ConnectFourEnum.RED;
			}
		}
	}
	
	private ConnectFourEnum findWinner() {

		int i;
		int j;
		// Look through the grid and determine if any of the marks of the player who just went Are part of a victory.
		for (i = 0; i < nRows; ++i) {
			for (j = 0; j < nColumns; ++j) {
				if (grid[i][j] == turn) {
					if (isVictory(i, j)) {
						return grid[i][j];
					}
				}
			}
		}
		return ConnectFourEnum.IN_PROGRESS;

	}
	
	private Boolean isVictory(int x, int y){
		int i;
		int numHorizontal = 1;
		int numVertical = 1;
		int numDiagonal = 1;
		ConnectFourEnum p = grid[x][y];
		// Counting the number of marks in a row in the negative X direction.
		for (i = x - 1; i > x - numToWin; --i) {
			if (i >= 0) {
				if (grid[i][y].equals(p)) {
					numHorizontal++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the negative Y direction.
		for (i = y - 1; i > y - numToWin; --i) {
			if (i >= 0) {
				if (grid[x][i].equals(p)) {
					numVertical++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the negative diagonal direction.
		for (i = -1; i > -numToWin; --i) {
			if (i + x >= 0) {
				if (i + y >= 0) {
					if (grid[i + x][i + y].equals(p)) {
						numDiagonal++;
					} else {
						break;
					}
				}
			}
		}
		// Counting the number of marks in a row in the positive X direction.
		for (i = x + 1; i < x + numToWin; ++i) {
			if (i < nRows) {
				if (grid[i][y].equals(p)) {
					numHorizontal++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the positive Y direction.
		for (i = y + 1; i < y + numToWin; ++i) {
			if (i < nColumns) {
				if (grid[x][i].equals(p)) {
					numVertical++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the positive diagonal direction.
		for (i = 1; i < numToWin; ++i) {
			if (i + x < nRows) {
				if (i + y < nColumns) {
					if (grid[i + x][i + y].equals(p)) {
						numDiagonal++;
					} else {
						break;
					}
				}
			}
		}
		if (numHorizontal >= numToWin || numVertical >= numToWin || numDiagonal >= numToWin)
			return true;	
		return false;
	}
	
	public ConnectFourEnum getTurn() {
		return turn;
	}
	
	public ConnectFourEnum getGameState() {
		return gameState;
	}
	
	public String toString() {
		String output = "";
		for (int y = 0; y<nColumns; ++y) {
			for (int x = 0; x<nRows; ++x) {
				output += grid[x][y].toString() + " | ";
			}
			output += "\n";
		}
		return output;
	}
}
