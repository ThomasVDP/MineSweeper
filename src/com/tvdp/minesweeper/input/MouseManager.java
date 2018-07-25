package com.tvdp.minesweeper.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseManager implements MouseListener
{
	private boolean leftClicked, rightClicked;
	private int mouseX, mouseY;
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			leftClicked = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightClicked = true;
		}
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public boolean hasLeftClicked()
	{
		return leftClicked;
	}
	
	public void hasLeftClicked(boolean clicked)
	{
		this.leftClicked = clicked;
	}
	
	public boolean hasRightClicked()
	{
		return rightClicked;
	}
	
	public void hasRightClicked(boolean clicked)
	{
		this.rightClicked = clicked;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	
	public int getMouseY()
	{
		return mouseY;
	}

}
