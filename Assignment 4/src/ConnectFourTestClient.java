import java.util.Scanner;

public class ConnectFourTestClient {

	public static void main(String[] args) {
		ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.RED);
		Scanner scanner = new Scanner(System.in);
		//debug statement: Random r = new Random();

		do {
			System.out.println(game.toString());
			System.out.println(game.getTurn() + ": Where do you want to mark? Enter row column");

			
			  int row = scanner.nextInt(); int column = scanner.nextInt();
			  scanner.nextLine();
			 
			//debug statement: int row = r.nextInt(10); int column = r.nextInt(10);
			game.takeTurn(row, column);

		} while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
		System.out.println(game.toString());
		System.out.println(game.getGameState());
		scanner.close();

	}

}
