package com.tvdp.minesweeper.gfx;

import com.tvdp.minesweeper.Handler;
import com.tvdp.minesweeper.state.GameState;
import com.tvdp.minesweeper.state.State;

public class GameCamera
{
	private float xOffset, yOffset;
	private Handler handler;
	
	public GameCamera(Handler handler, float xOffset, float yOffset)
	{
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void checkBlankSpace()
	{
		if (State.getState() instanceof GameState)
		{
			if(xOffset < 0)
			{
				xOffset = 0;
			} else if (xOffset > ((GameState)State.getState()).getWidth() * BoardCreator.TILE_WIDTH - handler.getWidth())
			{
				xOffset = ((GameState)State.getState()).getWidth() * BoardCreator.TILE_WIDTH - handler.getWidth();
			}
			if (yOffset < 0 - ((GameState)State.getState()).getMenuHeight())
			{
				yOffset = 0 - ((GameState)State.getState()).getMenuHeight();
			} else if (yOffset > ((GameState)State.getState()).getHeight() * BoardCreator.TILE_HEIGHT - handler.getHeight())
			{
				yOffset = ((GameState)State.getState()).getHeight() * BoardCreator.TILE_HEIGHT - handler.getHeight();
			}
		}
	}
	
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public float getXOffset()
	{
		return xOffset;
	}
	
	public void setXOffset(float xOffset)
	{
		this.xOffset = xOffset;
	}
	
	public float getYOffset()
	{
		return yOffset;
	}
	
	public void setYOffset(float yOffset)
	{
		this.yOffset = yOffset;
	}
}
