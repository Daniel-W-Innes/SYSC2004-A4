import java.awt.Point;
import java.util.Observable;

/**
 * The Class ConnectFourGame.
 */
public class ConnectFourGame extends Observable {

	/** The max grid size. */
	private Point max;

	/** The number of adjacent pieces to win. */
	private int numToWin;

	/** The grid. */
	private ConnectFourEnum[][] grid;

	/** The game state. */
	private ConnectFourEnum gameState;

	/** The turn. */
	private ConnectFourEnum turn;

	/** The preview location. */
	private Point preview;

	/**
	 * Instantiates a new connect four game.
	 *
	 * @param initialTurn the initial turn
	 */
	ConnectFourGame(ConnectFourEnum initialTurn) {
		this(new Point(8, 8), 4, initialTurn);
	}

	/**
	 * Instantiates a new connect four game.
	 *
	 * @param max         The max grid size.
	 * @param numToWin    The number of adjacent pieces to win.
	 * @param initialTurn the initial turn
	 */
	ConnectFourGame(Point max, int numToWin, ConnectFourEnum initialTurn) {
		turn = initialTurn;
		this.max = max;
		this.numToWin = numToWin;
		reset(initialTurn);
	}

	/**
	 * Reset.
	 *
	 * @param initialTurn the initial turn
	 */
	public void reset(ConnectFourEnum initialTurn) {
		grid = new ConnectFourEnum[max.x][max.y];
		for (int y = 0; y < max.y; ++y) {
			for (int x = 0; x < max.x; ++x) {
				grid[x][y] = ConnectFourEnum.EMPTY;
			}
		}
		turn = initialTurn;
		gameState = ConnectFourEnum.IN_PROGRESS;
		preview = new Point(0, 0);
		setChanged();
		notifyObservers(new ConnectMove(preview, ConnectFourEnum.EMPTY));
	}

	/**
	 * Preview turn.
	 *
	 * @param location the location
	 */
	public void previewTurn(Point location) {
		if (preview != null) {
			setChanged();
			notifyObservers(new ConnectMove(preview, ConnectFourEnum.EMPTY));
		}
		System.out.println("-");
		System.out.println(location.toString());
		System.out.println("+");
		if (location.y < max.y && location.x < max.x) {
			System.out.println("\\");
			if (grid[location.x][location.y] == ConnectFourEnum.EMPTY) {
				System.out.println("|");
				preview = fall(location);
			}
		}
		System.out.println("?");
		setChanged();
		System.out.println("=");
		notifyObservers(new ConnectMove(preview, turn));
	}

	/**
	 * Take turn.
	 */
	public void takeTurn() {
		if (preview != null) {
			grid[preview.x][preview.y] = turn;
			gameState = findWinner();
			if (turn == ConnectFourEnum.RED)
				turn = ConnectFourEnum.BLACK;
			else
				turn = ConnectFourEnum.RED;
		}
		preview = null;
	}

	/**
	 * Take turn.
	 *
	 * @param location the location
	 */
	public void takeTurn(Point location) {
		if (location.y < max.y && location.x < max.x) {
			if (grid[location.x][location.y] == ConnectFourEnum.EMPTY) {
				location = fall(location);
				grid[location.x][location.y] = turn;
				gameState = findWinner();
				if (turn == ConnectFourEnum.RED)
					turn = ConnectFourEnum.BLACK;
				else
					turn = ConnectFourEnum.RED;
			}
		}
	}

	/**
	 * Fall.
	 *
	 * @param location the location
	 * @return the point
	 */
	private Point fall(Point location) {
		Boolean c = true;
		while (c) {
			if (location.y + 1 < max.y) {
				if (grid[location.x][location.y + 1] == ConnectFourEnum.EMPTY) {
					location.y++;
				} else {
					c = false;
				}
			} else {
				c = false;
			}
		}
		return location;
	}

	/**
	 * Find winner.
	 *
	 * @return the connect four enum
	 */
	private ConnectFourEnum findWinner() {

		// Look through the grid and determine if any of the marks of the player who
		// just went Are part of a victory.
		for (int y = 0; y < max.y; ++y) {
			for (int x = 0; x < max.x; ++x) {
				if (grid[x][y] == turn) {
					if (isVictory(new Point(x, y))) {
						return grid[x][y];
					}
				}
			}
		}
		return ConnectFourEnum.IN_PROGRESS;

	}

	/**
	 * Checks if is victory.
	 *
	 * @param location the location
	 * @return the boolean
	 */
	private Boolean isVictory(Point location) {
		int i;
		int numHorizontal = 1;
		int numVertical = 1;
		int numDiagonal = 1;
		ConnectFourEnum p = grid[location.x][location.y];
		// Counting the number of marks in a row in the negative X direction.
		for (i = location.x - 1; i > location.x - numToWin; --i) {
			if (i >= 0) {
				if (grid[i][location.y].equals(p)) {
					numHorizontal++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the negative Y direction.
		for (i = location.y - 1; i > location.y - numToWin; --i) {
			if (i >= 0) {
				if (grid[location.x][i].equals(p)) {
					numVertical++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the negative diagonal direction.
		for (i = -1; i > -numToWin; --i) {
			if (i + location.x >= 0) {
				if (i + location.y >= 0) {
					if (grid[i + location.x][i + location.y].equals(p)) {
						numDiagonal++;
					} else {
						break;
					}
				}
			}
		}
		// Counting the number of marks in a row in the positive X direction.
		for (i = location.x + 1; i < location.x + numToWin; ++i) {
			if (i < max.x) {
				if (grid[i][location.y].equals(p)) {
					numHorizontal++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the positive Y direction.
		for (i = location.y + 1; i < location.y + numToWin; ++i) {
			if (i < max.y) {
				if (grid[location.x][i].equals(p)) {
					numVertical++;
				} else {
					break;
				}
			}
		}
		// Counting the number of marks in a row in the positive diagonal direction.
		for (i = 1; i < numToWin; ++i) {
			if (i + location.x < max.x) {
				if (i + location.y < max.y) {
					if (grid[i + location.x][i + location.y].equals(p)) {
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

	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public ConnectFourEnum getTurn() {
		return turn;
	}

	/**
	 * Gets the game state.
	 *
	 * @return the game state
	 */
	public ConnectFourEnum getGameState() {
		return gameState;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String output = "";
		for (int y = 0; y < max.y; ++y) {
			for (int x = 0; x < max.x; ++x) {
				output += grid[x][y].toString() + " | ";
			}
			output += "\n";
		}
		return output;
	}
}
