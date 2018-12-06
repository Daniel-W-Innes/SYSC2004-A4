import java.awt.Point;

/**
 * The Class ConnectMove.
 */
public class ConnectMove {

	/** The location. */
	private Point location;

	/** The color. */
	private ConnectFourEnum color;

	/**
	 * Instantiates a new connect move.
	 *
	 * @param location   the location
	 * @param color the color
	 */
	ConnectMove(Point location, ConnectFourEnum color) {
		this.location = location;
		this.color = color;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Point getlocation() {
		return location;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public ConnectFourEnum getColor() {
		return color;
	}
}
