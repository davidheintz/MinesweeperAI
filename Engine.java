package gamepackage;

public class Engine {
	/* STEPS:
	 * [x] generate game board 
	 * [x] initialize unknown board w all '' values 
	 * [ ] continue to allow robot to act until game over 
	 * [ ] add robot actions: uncover/mark 
	 */
	
	public static void main(String args[]) {
		
		Game newgame = new Game();
		int[][] board = newgame.gen_board(30, 30, 50);
		newgame.print_board(board);
	}
}
