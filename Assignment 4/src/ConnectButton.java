import javafx.scene.control.Button;

public class ConnectButton extends Button{
	private int row;
	private int column;
	
	ConnectButton(String label, int row, int column){
		this.row = row;
		this.row = row;
		setText(label);
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public String toString() {
		return "("+column+","+row+")";
	}
}
