package gamepackage;

import java.util.Stack;
import java.util.Arrays;

public class Engine {
	/* STEPS:
	 * [x] generate game board 
	 * [x] initialize unknown board w all '' values 
	 * [ ] continue to allow robot to act until game over 
	 * [ ] add robot actions: uncover/mark 
	 */
	
	public static void main(String args[]) {
		
		Game newgame = new Game();
		int[][] board = newgame.gen_board(30, 30, 100);
		newgame.print_board(board);
		
		int[][] cboard = new int[30][30];
		
		for(int[] arr : cboard) {
			 Arrays.fill(arr, -1);
		}
		
		Stack<Coordinate> s = new Stack<Coordinate>();
		ResponseData data;
		data = newgame.recursive_uncover(cboard, board, 15, 15, 0, s);
		cboard = data.get_board();
		s = data.get_unc();
		while(s.isEmpty() == false) {
			s.pop().print();
		}
		System.out.println();
		
		for(int i = 0; i < 30; i++) {
			for(int j = 0; j < 30; j++) {
				System.out.print('[');
				if (cboard[i][j] != -1) {
					System.out.print(cboard[i][j]);
				}
				else {
					System.out.print(' ');
				}
				System.out.print(']');
			}
			System.out.println();
		}
	}
}
