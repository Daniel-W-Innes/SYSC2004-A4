import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Class ConnectFourApplication.
 */
public class ConnectFourApplication extends Application implements Observer {
	
	/** The max grid size. */
	public final Point MAX = new Point(8, 8);
	
	/** The number of adjacent pieces to win. */
	public final int NUM_TO_WIN = 4;
	
	/** The button size. */
	public final int BUTTON_SIZE = 20;
	
	/** The game engine. */
	private ConnectFourGame gameEngine;
	
	/** The buttons. */
	private ConnectButton[][] buttons;
	
	/** The top text field. */
	private TextField textField;
	
	/** The end alert. */
	private Alert alert;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = new VBox();
		gameEngine = new ConnectFourGame(MAX, NUM_TO_WIN, ConnectFourEnum.BLACK);
		gameEngine.addObserver(this);
		buttons = new ConnectButton[MAX.x][MAX.y];
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Monopoly Game");
		alert.setHeaderText("Game Over");
		Pane branch;
		branch = new HBox();
		textField = new TextField(ConnectFourEnum.BLACK.toString());
		textField.setMinHeight(BUTTON_SIZE);
		textField.setMaxWidth(Double.MAX_VALUE);
		branch.getChildren().add(textField);
		root.getChildren().add(branch);
		for (int y = 0; y < MAX.y; ++y) {
			branch = new HBox();
			for (int x = 0; x < MAX.x; ++x) {
				buttons[x][y] = new ConnectButton(ConnectFourEnum.EMPTY.toString(), new Point(x, y));
				buttons[x][y].setMinHeight(BUTTON_SIZE);
				buttons[x][y].setMaxWidth(Double.MAX_VALUE);
				buttons[x][y].setOnAction(new ButtonHandler(new Point(x, y), gameEngine));
				branch.getChildren().add(buttons[x][y]);
			}
			root.getChildren().add(branch);
		}
		branch = new HBox();
		Button TurnButton = new Button("Roll");
		TurnButton.setOnAction(e -> gameEngine.takeTurn());
		TurnButton.setMinHeight(BUTTON_SIZE);
		branch.getChildren().add(TurnButton);
		root.getChildren().add(TurnButton);
		Scene scene = new Scene(root);
		primaryStage.setTitle("Connect Four");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		ConnectMove connectMove = (ConnectMove) arg1;
		textField.setText(connectMove.getColor().toString());
		Point location = connectMove.getlocation();
		if (connectMove.getColor() == ConnectFourEnum.RED) {
			System.out.println("--");
			System.out.println(location.toString());
			System.out.println(buttons[location.x][location.y].toString());
			buttons[location.x][location.y].setText(ConnectFourEnum.BLACK.toString());
		}else if (connectMove.getColor() == ConnectFourEnum.BLACK) {
			System.out.println("--");
			System.out.println(location.toString());
			System.out.println(buttons[location.x][location.y].toString());
			buttons[location.x][location.y].setText(ConnectFourEnum.RED.toString());
		}else {
			System.out.println("--");
			System.out.println(location.toString());
			System.out.println(buttons[location.x][location.y].toString());
			buttons[location.x][location.y].setText(ConnectFourEnum.EMPTY.toString());
		}
		if(gameEngine.getGameState() != ConnectFourEnum.IN_PROGRESS) {
			alert.setContentText(gameEngine.getGameState().toString() + " wins!");
			alert.showAndWait();
			System.exit(0);
		}
	}
	/**
	 * The Class ButtonHandler.
	 */
	private class ButtonHandler implements EventHandler<ActionEvent> {
		
		/** The button location. */
		private Point location;
		
		/** The game engine. */
		private ConnectFourGame gameEngine;

		/**
		 * Instantiates a new button handler.
		 *
		 * @param location is the button location
		 * @param gameEngine the game engine
		 */
		ButtonHandler(Point location, ConnectFourGame gameEngine) {
			this.location = location;
			this.gameEngine = gameEngine;
		}

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event) {
			gameEngine.previewTurn(location);
		}
	}
}
