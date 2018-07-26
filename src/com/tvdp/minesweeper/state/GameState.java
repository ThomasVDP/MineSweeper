package com.tvdp.minesweeper.state;

import java.awt.Color;
import java.awt.Graphics;

import com.tvdp.minesweeper.Handler;
import com.tvdp.minesweeper.gfx.BoardCreator;

public class GameState extends State
{
	private int width, height, bombs;
	private int menuWidth, menuHeight;
	private BoardCreator bc;
	
	public GameState(Handler handler, int width, int height, int bombs)
	{
		super(handler);
		this.width = width;
		this.height = height;
		this.bombs = bombs;
		this.menuWidth = handler.getWidth();
		this.menuHeight = (int)(handler.getHeight() * 0.15F);
		
		BoardCreator.setTileWidth(handler.getWidth(), handler.getHeight(), this.width, this.height);
		bc = new BoardCreator(handler, this.width, this.height, this.bombs);
		bc.createBoard();
		bc.placeBombs();
	}

	@Override
	public void tick()
	{
		handleInput();
	}

	@Override
	public void render(Graphics g)
	{
		bc.draw(g, handler.getWidth(), handler.getHeight());
		g.setColor(new Color(108, 200, 200));
		g.fillRect(0, 0, menuWidth, menuHeight);
		g.setColor(Color.black);
		g.drawRect(0, 0, menuWidth, menuHeight);
	}
	
	public void handleInput()
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
			handler.getGameCamera().move(0, -6);
		if (handler.getKeyManager().down)
			handler.getGameCamera().move(0, 6);
		if (handler.getKeyManager().left)
			handler.getGameCamera().move(-6, 0);
		if (handler.getKeyManager().right)
			handler.getGameCamera().move(6, 0);
	}
	
	public int getMenuWidth()
	{
		return menuWidth;
	}

	public void setMenuWidth(int menuWidth)
	{
		this.menuWidth = menuWidth;
	}

	public int getMenuHeight()
	{
		return menuHeight;
	}

	public void setMenuHeight(int menuHeight)
	{
		this.menuHeight = menuHeight;
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
