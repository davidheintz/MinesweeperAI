package gamepackage;

import java.util.Random;

public class Game {
	/* METHODS
	 * generate board
	 * uncover piece
	 * recursive uncover
	 * game over */
	
	public int[][] gen_board(int x, int y, int b) { 
		int[][] board = new int[x][y];
		Random rand = new Random();
		int[] bombs = rand.ints(0, x*y).distinct().limit(b).toArray();
		for(int i = 0; i < bombs.length; i++) {
			int bx = bombs[i]/y;
			int by = bombs[i]%y;
			board[bx][by] = -9;
			
			
			// incrementing value of 8 spaces surrounding a bomb 
			// 1 2 3
			// 4 b 5
			// 6 7 8
			if(bx < board.length-1) { // if next row
				board[bx+1][by] += 1; // space 7
				if(by < board[0].length-1) { // if next column
					board[bx][by+1] += 1; // space 5
					board[bx+1][by+1] += 1; // space 8
				}
				if(by > 0) { // if previous column 
					board[bx][by-1] += 1; // space 4
					board[bx+1][by-1] += 1; // space 6
				}
			}
			
			else { // if not next row: 
				if(by < board[0].length-1) { // if next column 
					board[bx][by+1] += 1; // space 5
				}
				if(by > 0) { // if previous column 
					board[bx][by-1] += 1; // space 4
				}	
			}	
			
			if(bx > 0) { // if previous row
				board[bx-1][by] += 1; // space 2
				if(by < board[0].length-1) { // if next column 
					board[bx-1][by+1] += 1; // space 3
				}
				if(by > 0) { // if previous column 
					board[bx-1][by-1] += 1; // space 1 
				}
			}
		}
		return board;
	}
	
	public char[][] uncover_piece(int[][] uboard, char[][] cboard, int x, int y) {
		if(uboard[x][y] < 0) {
			// GAME OVER
		}
		else {
			cboard[x][y] = (char) uboard[x][y];
			if(uboard[x][y] == 0) {
				// RECURSIVE UNCOVER
			}
		}
		return cboard; // return T or F for game over
	}
	
	public void recursive_uncover() {
		
	}
	
	public int game_over() {
		int score = 0;
		return score;
	}
	
	public void print_board(int[][] board) {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] < 0) {
					System.out.print("[-]");
				}
				else {
					System.out.print("[" + board[i][j] + "]");
				}
			}
			System.out.println();
		}
	}
}
