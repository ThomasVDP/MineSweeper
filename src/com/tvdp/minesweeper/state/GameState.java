package com.tvdp.minesweeper.state;

import java.awt.Graphics;

import com.tvdp.minesweeper.Handler;
import com.tvdp.minesweeper.gfx.BoardCreator;

public class GameState extends State
{
	private int width, height, bombs;
	private BoardCreator bc;
	
	public GameState(Handler handler, int width, int height, int bombs)
	{
		super(handler);
		this.width = width;
		this.height = height;
		this.bombs = bombs;
		
		BoardCreator.setTileWidth(handler.getWidth(), handler.getHeight(), this.width, this.height);
		bc = new BoardCreator(handler, this.width, this.height, this.bombs);
		bc.createBoard();
		bc.placeBombs();
	}

	@Override
	public void tick()
	{
		if (handler.getMouseManager().hasLeftClicked())
		{
			bc.handleMouseLeft(handler.getWidth(), handler.getHeight(), handler.getMouseManager(), handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
		}
		if (handler.getMouseManager().hasRightClicked())
		{
			bc.handleMouseRight(handler.getWidth(), handler.getHeight(), handler.getMouseManager(), handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
		}
		
		if (handler.getKeyManager().up)
			handler.getGameCamera().setYOffset(handler.getGameCamera().getYOffset() - 6);
		if (handler.getKeyManager().down)
			handler.getGameCamera().setYOffset(handler.getGameCamera().getYOffset() + 6);
		if (handler.getKeyManager().left)
			handler.getGameCamera().setXOffset(handler.getGameCamera().getXOffset() - 6);
		if (handler.getKeyManager().right)
			handler.getGameCamera().setXOffset(handler.getGameCamera().getXOffset() + 6	);
	}

	@Override
	public void render(Graphics g)
	{
		bc.draw(g, handler.getWidth(), handler.getHeight());
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
}
