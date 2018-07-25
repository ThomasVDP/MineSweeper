package com.tvdp.minesweeper.gfx;

import java.awt.Graphics;

public class Tile
{
	private int value;
	private boolean revealed = false;
	private boolean isFlagged = false;
	private int x, y = 0;
	
	public Tile(int value, boolean revealed)
	{
		this.value = value;
		this.revealed = revealed;
	}
	
	public void draw(Graphics g, int x, int y)
	{
		if (isFlagged)
		{
			g.drawImage(Assets.flag, x, y, BoardCreator.TILE_WIDTH, BoardCreator.TILE_WIDTH, null);
		} else if (revealed)
		{
			g.drawImage(Assets.blocks[getValue()], x, y, BoardCreator.TILE_WIDTH, BoardCreator.TILE_WIDTH, null);
		} else {
			g.drawImage(Assets.hidden, x, y, BoardCreator.TILE_WIDTH, BoardCreator.TILE_WIDTH, null);
		}
	}
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int[] getPos()
	{
		int[] pos = new int[2];
		pos[0] = x;
		pos[1] = y;
		return pos;
	}
	
	public boolean isFlagged()
	{
		return isFlagged;
	}
	
	public void isFlagged(boolean newValue)
	{
		this.isFlagged = newValue;
	}
	
	public boolean isBomb()
	{
		return this.value == 9;
	}
	
	public void setValue(int newValue)
	{
		this.value = newValue;
	}
	
	public int getValue()
	{
		return this.value;
	}
	
	public void setState(boolean revealed)
	{
		this.revealed = revealed;
	}
	
	public boolean getState()
	{
		return revealed;
	}
}
