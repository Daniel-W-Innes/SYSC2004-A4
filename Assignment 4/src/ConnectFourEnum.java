/**
 * The Enum ConnectFourEnum.
 *
 * @author Schramm
 */
public enum ConnectFourEnum {

	/** The game state in progress. */
	IN_PROGRESS("Game in Progress"),
	/** The peace color red. */
	RED("Red"),
	/** The peace color black. */
	BLACK("Black"),
	/** The game state draw. */
	DRAW("It's a draw!"),
	/** The peace color empty. */
	EMPTY(" ");

	/** The value. */
	@SuppressWarnings("unused")
	private String value;

	/**
	 * Instantiates a new connect four enum.
	 *
	 * @param value the value
	 */
	ConnectFourEnum(String value) {
		this.value = value;
	}
}