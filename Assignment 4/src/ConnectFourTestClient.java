import java.awt.Point;
import java.util.Scanner;

/**
 * The Class ConnectFourTestClient.
 */
public class ConnectFourTestClient {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.RED);
		Scanner scanner = new Scanner(System.in);
		Point location = new Point();
		// debug statement: Random r = new Random();
		do {
			System.out.println(game.toString());
			System.out.println(game.getTurn() + ": Where do you want to mark? Enter row column");

			location.x = scanner.nextInt();
			location.y = scanner.nextInt();
			scanner.nextLine();

			// debug statement: int row = r.nextInt(10); int column = r.nextInt(10);
			game.takeTurn(location);

		} while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
		System.out.println(game.toString());
		System.out.println(game.getGameState());
		scanner.close();

	}

}
