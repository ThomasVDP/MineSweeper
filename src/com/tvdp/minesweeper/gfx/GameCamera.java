package com.tvdp.minesweeper.gfx;

import com.tvdp.minesweeper.Handler;

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
	
	public void move(float xAmt, float yAmt)
	{
		xOffset += xAmt;
		yOffset += yAmt;
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
