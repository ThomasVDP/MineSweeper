package com.tvdp.minesweeper;

import com.tvdp.minesweeper.gfx.GameCamera;
import com.tvdp.minesweeper.input.KeyManager;
import com.tvdp.minesweeper.input.MouseManager;

public class Handler
{
	private Game game;
	
	public Handler(Game game)
	{
		this.game = game;
	}
	
	public GameCamera getGameCamera()
	{
		return game.getGameCamera();
	}
	
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	
	public int getWidth()
	{
		return game.getWidth();
	}
	
	public int getHeight()
	{
		return game.getHeight();
	}

	public Game getGame()
	{
		return game;
	}

	public void setGame(Game game)
	{
		this.game = game;
	}
	
	
}
