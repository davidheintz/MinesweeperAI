package gamepackage;

import java.util.Random;
import java.util.Stack;

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
	
	
	public ResponseData recursive_uncover(int[][] cboard, int[][] uboard, int x, int y, int pos, Stack<Coordinate> s) {
		
		// 1 2 3
		// 4 0 5
		// 6 7 8 zero is in middle because only accessed on first uncovering
		ResponseData data;
		if (cboard[x][y] == -1) {
			cboard[x][y] = uboard[x][y];
			System.out.print(uboard[x][y]);
			System.out.println(cboard[x][y]);
			if(uboard[x][y] == 0) {
				switch(pos) {
				
					case 0: // middle space case: check all surrounding spaces that aren't over edge of map
						
						if (x > 0 && x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else if (x > 0) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else { //if x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data  = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 1: // upper left space: check 5 left/upper spaces that aren't off edge of map
						
						if (x > 0 && x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else if (x > 0) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else { //if (x < cboard.length -1) {
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
						}
						break;
						
					case 2: // upper middle space: check all upper spaces not off edge of map
						
						if(x > 0) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 3: // upper right space: check all right/upper spaces not off edge of map
						
						if (x > 0 && x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else if (x > 0) {
							data = recursive_uncover(cboard, uboard, x-1, y, 2, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else { //if (x < cboard.length -1) {
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 4: // left middle space: check all left spaces not off edge of map
						
						if(y > 0) {
							data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (x > 0) {
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
							if (x < cboard.length-1) {
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 5: // right middle space: check all right spaces not off edge of map 
						
						if(y < cboard[0].length -1) {
							data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (x > 0) {
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
							if (x < cboard.length-1) {
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 6: // lower left space: check all left/lower spaces not off edge of map 
						
						if (x > 0 && x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else if (x > 0) {
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y-1, 1, s);		
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else { //if (x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x, y-1, 4, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 7: // lower middle space: check all lower spaces not off edge of map 
						
						if(x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
						
					case 8: // lower right space: check all right/lower spaces not off edge of map 
						
						if (x > 0 && x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else if (x > 0) {
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x-1, y+1, 3, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						else { // (x < cboard.length -1) {
							data = recursive_uncover(cboard, uboard, x+1, y, 7, s);
							cboard = data.get_board();
							s = data.get_unc();
							if (y > 0) {
								data = recursive_uncover(cboard, uboard, x+1, y-1, 6, s);
								cboard = data.get_board();
								s = data.get_unc();
								
							}
							if (y < cboard[0].length -1) {
								data = recursive_uncover(cboard, uboard, x, y+1, 5, s);
								cboard = data.get_board();
								s = data.get_unc();
								data = recursive_uncover(cboard, uboard, x+1, y+1, 8, s);
								cboard = data.get_board();
								s = data.get_unc();
							}
						}
						break;
					
					default:
						break;
						
				}
			}
			else { // if uncovered space not 0, then save it in stack for robot to check
				s.push(new Coordinate(x,y));
			}
		}
		return new ResponseData(cboard,s);
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
	
	public void print_bombs(int[][] cboard, int[][] uboard) {
		for(int i = 0; i < cboard.length; i++) {
			for(int j = 0; j < cboard[0].length; j++) {
				if(cboard[i][j] < 0) {
					System.out.print("[-]");
				}
				else if (cboard[i][j] != -1) {
					System.out.print('[' + cboard[i][j] + ']');
				}
				else {
					System.out.print("[ ]");
				}
			}
			System.out.println();
		}
	}
}
