import game.Board;
import game.CardGameGUI;
import game.GameSelect;

public class Main
{
	public static void main(String[] args)
	{
		Board board = GameSelect.query(args);
		CardGameGUI gui = new CardGameGUI(board);
		gui.displayGame();
	}
}
