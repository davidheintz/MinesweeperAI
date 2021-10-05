package gamepackage;

import java.util.Stack;

public class ResponseData {
	int[][] board;
	Stack<Coordinate> unc_coords;
	public ResponseData(int[][] b, Stack<Coordinate> u) {
		board = b;
		unc_coords = u;
	}
	
	public int[][] get_board() {
		return board;
	}
	
	public Stack<Coordinate> get_unc() {
		return unc_coords;
	}
}
