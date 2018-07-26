package com.tvdp.minesweeper.gfx;

import java.awt.Graphics;
import java.util.Random;

import com.tvdp.minesweeper.Handler;
import com.tvdp.minesweeper.input.MouseManager;

public class BoardCreator
{
	public static int TILE_WIDTH;
	public static int TILE_HEIGHT;
	
	public static void setTileWidth(int screenWidth, int screenHeight, int boardWidth, int boardHeight)
	{
		TILE_WIDTH = (int)(screenWidth / boardWidth);
		TILE_HEIGHT = (int)(screenHeight / boardHeight);
		if (TILE_WIDTH < 32 || TILE_HEIGHT < 32)
		{
			TILE_WIDTH = 32;
			TILE_HEIGHT = 32;
		}
	}
	
	private Handler handler;
	private Tile[][] tiles;
	private int width, height, bombs;
	
	public BoardCreator(Handler handler, int width, int height, int bombs)
	{
		this.handler = handler;
		this.width = width;
		this.height = height;
		this.bombs = bombs;
		this.tiles = new Tile[height][width];
	}
	
	public void createBoard()
	{
		
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				tiles[i][j] = new Tile(0, false);
			}
		}
		
		handler.getGameCamera().move(this.width * BoardCreator.TILE_WIDTH / 2 - handler.getWidth() / 2, this.height * BoardCreator.TILE_HEIGHT / 2 - handler.getHeight() / 2);
	}
	
	public void placeBombs()
	{
		for (int i = 0; i < bombs; ++i)
		{
			int randX = new Random().nextInt(width);
			int randY = new Random().nextInt(height);
			Tile tile = tiles[randY][randX];
			while (tile == null || tile.isBomb())
			{
				randX = new Random().nextInt(width);
				randY = new Random().nextInt(height);
				tile = tiles[randY][randX];
			}
			
			tiles[randY][randX].setValue(9);
			if (!(randX  + 1 > width || randY + 1 > height))
			{
				if (randY > 0 && tiles[randY - 1][randX].getValue() != 9)
					tiles[randY - 1][randX].setValue(tiles[randY - 1][randX].getValue() + 1);
				if (randY > 0 && randX > 0 && tiles[randY - 1][randX - 1].getValue() != 9)
					tiles[randY - 1][randX - 1].setValue(tiles[randY - 1][randX - 1].getValue() + 1);
				if (randY > 0 && randX + 1 < tiles.length && tiles[randY - 1][randX + 1].getValue() != 9)
					tiles[randY - 1][randX + 1].setValue(tiles[randY - 1][randX + 1].getValue() + 1);
				if (randY + 1 < tiles.length && tiles[randY + 1][randX].getValue() != 9)
					tiles[randY + 1][randX].setValue(tiles[randY + 1][randX].getValue() + 1);
				if (randY + 1 < tiles.length && randX + 1 < tiles.length && tiles[randY + 1][randX + 1].getValue() != 9)
					tiles[randY + 1][randX + 1].setValue(tiles[randY + 1][randX + 1].getValue() + 1);
				if (randY + 1 < tiles.length && randX > 0 && tiles[randY + 1][randX - 1].getValue() != 9)
					tiles[randY + 1][randX - 1].setValue(tiles[randY + 1][randX - 1].getValue() + 1);
				if (randX > 0 && tiles[randY][randX - 1].getValue() != 9)
					tiles[randY][randX - 1].setValue(tiles[randY][randX - 1].getValue() + 1);
				if (randX + 1 < tiles.length && tiles[randY][randX + 1].getValue() != 9)
					tiles[randY][randX + 1].setValue(tiles[randY][randX + 1].getValue() + 1);
			}
		}
	}
	
	public void draw(Graphics g, int screenWidth, int screenHeight)
	{	
		int xStart = (int)Math.max(0, handler.getGameCamera().getXOffset() / BoardCreator.TILE_WIDTH);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getXOffset() + handler.getWidth()) / BoardCreator.TILE_WIDTH + 1);
		int yStart = (int)Math.max(0, handler.getGameCamera().getYOffset() / BoardCreator.TILE_HEIGHT);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getYOffset() + handler.getHeight()) / BoardCreator.TILE_HEIGHT + 1);
				
		for (int i = yStart; i < yEnd; ++i)
		{
			for (int j = xStart; j < xEnd; ++j)
			{
				getTile(j, i).draw(g, (int)(j * BoardCreator.TILE_WIDTH - handler.getGameCamera().getXOffset()), (int)(i * BoardCreator.TILE_HEIGHT - handler.getGameCamera().getYOffset()));
				getTile(j, i).setPos(j, i);
			}
		}
	}
	
	public void handleMouseLeft(int screenWidth, int screenHeight, MouseManager mouse, int mouseX, int mouseY)
	{	
		for (int i = 0; i < height; ++i)
		{
			for (int j = 0; j < width; j++)
			{	
				if (mouseX >= (int)(getTile(j, i).getPos()[0] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getXOffset()) && mouseX <= (int)(getTile(j, i).getPos()[0] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getXOffset()) + BoardCreator.TILE_WIDTH)
				{
					if (mouseY >= (int)(getTile(j, i).getPos()[1] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getYOffset()) && mouseY <= (int)(getTile(j, i).getPos()[1] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getYOffset()) + BoardCreator.TILE_WIDTH)
					{
						if (!getTile(j, i).isFlagged() && !getTile(j, i).getState())
						{
							getTile(j, i).setState(true);
						}
						if (getTile(j, i).getValue() == 0)
						{
							removeAround(i, j);
						}
						mouse.hasLeftClicked(false);
						return;
					}
				}
			}
		}
	}
	
	public void handleMouseRight(int screenWidth, int screenHeight, MouseManager mouse, int mouseX, int mouseY)
	{	
		for (int i = 0; i < height; ++i)
		{
			for (int j = 0; j < width; j++)
			{	
				if (mouseX >= (int)(getTile(j, i).getPos()[0] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getXOffset()) && mouseX <= (int)(getTile(j, i).getPos()[0] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getXOffset()) + BoardCreator.TILE_WIDTH)
				{
					if (mouseY >= (int)(getTile(j, i).getPos()[1] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getYOffset()) && mouseY <= (int)(getTile(j, i).getPos()[1] * BoardCreator.TILE_WIDTH - handler.getGameCamera().getYOffset()) + BoardCreator.TILE_WIDTH)
					{
						if (!getTile(j, i).getState())
						{
							if (getTile(j, i).isFlagged())
								getTile(j, i).isFlagged(false);
							else
								getTile(j, i).isFlagged(true);
						}
						mouse.hasRightClicked(false);
						return;
					}
				}
			}
		}
	}
	
	private void removeAround(int i, int j)
	{
		if (getTile(j, i) == null)
		{
			return;
		}
		if (!(getTile(j, i - 1) == null))
		{
			Tile tile = getTile(j, i - 1);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i - 1, j);
				}
			}
		}
		
		if (!(getTile(j - 1, i - 1) == null))
		{
			Tile tile = getTile(j - 1, i - 1);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i - 1, j - 1);
				}
			}
		}
		
		if (!(getTile(j - 1, i) == null))
		{
			Tile tile = getTile(j - 1, i);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i, j - 1);
				}
			}
		}
		
		if (!(getTile(j, i + 1) == null))
		{
			Tile tile = getTile(j, i + 1);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i + 1, j);
				}
			}
		}
		
		if (!(getTile(j + 1, i + 1) == null))
		{
			Tile tile = getTile(j + 1, i + 1);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i + 1, j + 1);
				}
			}
		}
		
		if (!(getTile(j + 1, i) == null))
		{
			Tile tile = getTile(j + 1, i);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i, j + 1);
				}
			}
		}
		
		if (!(getTile(j - 1, i + 1) == null))
		{
			Tile tile = getTile(j - 1, i + 1);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i + 1, j - 1);
				}
			}
		}
		
		if (!(getTile(j + 1, i - 1) == null))
		{
			Tile tile = getTile(j + 1, i - 1);
			if(!tile.getState())
			{
				tile.setState(true);
				if (tile.getValue() == 0)
				{
					removeAround(i - 1, j + 1);
				}
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		if (x < 0 || y < 0 || x >= width || y >= height)
		{
			return null;
		}
		
		Tile t = tiles[y][x];
		return t;
	}
	
	public int countBombs()
	{
		int i = 0;
		for (Tile[] tileArray : tiles)
		{
			for (Tile tile : tileArray)
			{
				if (tile.isBomb())
				{
					i++;
				}
			}
		}
		
		return i;
	}
}
