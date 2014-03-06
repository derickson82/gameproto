package com.proto.sample;

import org.newdawn.slick.Graphics;

public class Board {

	private int SIZE = 30;
	
	private char BLANK = ' ';
	private char ME = '@';
	private char[][] board = new char[SIZE][SIZE];

	public Board() {
		board[11][11] = '&';
	}
	public Board(int x, int y) {
		board[x][y] = ME;
	}
	
	void draw(final Graphics graphics) {
		this.each(new CellVisitor() {
			@Override
			public void visit(int x, int y, char val) {
				graphics.drawString(String.valueOf(val), x * 10, y * 15);
			}
		});
	}
	
	public void each(CellVisitor cellVisitor) {
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				cellVisitor.visit(row, col, board[row][col]);
			}
		}
	}
	
	public static interface CellVisitor {
		void visit(int x, int y, char val);
	}
	
	public void move(int curX, int curY, int nextX, int nextY) {
		board[nextX][nextY] = board[curX][curY];
		board[curX][curY] = BLANK;
	}
}
