package game;

/**
 * This is a class that simulates games.
 * See accompanying documents for a description of how games are played.
 */
public class Simulation
{
	/**
	 * The number of games to play.
	 */
	private static final int GAMES_TO_PLAY = 1000;

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;

	/**
	 * @param args is not used.
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		int wins = 0;
		Board board = GameSelect.query(args);
		for (int k = 0; k < GAMES_TO_PLAY; k++) {
			
			board.newGame();
			if (I_AM_DEBUGGING)
				System.out.println(board);
			
			while (board.playIfPossible())
				if (I_AM_DEBUGGING)
					System.out.println(board);
			
			if (board.gameIsWon())
				wins++;
		}

		double percentWon = (int) (1000.0 * wins / GAMES_TO_PLAY + 0.5) / 10.0;
		System.out.println("Games won:    " + wins);
		System.out.println("Games played: " + GAMES_TO_PLAY);
		System.out.println("Percent won:  " + percentWon + "%");
	}
}
