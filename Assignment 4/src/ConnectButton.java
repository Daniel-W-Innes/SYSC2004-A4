import java.awt.Point;

import javafx.scene.control.Button;

/**
 * The Class ConnectButton.
 */
public class ConnectButton extends Button {

	/** The button location. */
	private Point location;

	/**
	 * Instantiates a new connect button.
	 *
	 * @param label    is the button label
	 * @param location is the button location
	 */
	ConnectButton(String label, Point location) {
		this.location = location;
		setText(label);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.scene.control.Labeled#toString()
	 */
	public String toString() {
		return location.toString();
	}
}
