package com.tvdp.minesweeper;

public class Launcher
{
	public static void main(String[] args)
	{
		Game game = new Game("MineSweeper!", 1500, 1100);
		game.start();
	}
}
