package gamepackage;

public class Coordinate {
	int x;
	int y;
	
	public Coordinate(int row, int col) {
		x = row;
		y = col;
	}
	
	public int get_x() {
		return x;
	}
	
	public int get_y() {
		return y;
	}
	
	public void print() {
		System.out.print("(" + x + "," + y + ")");
	}
}
