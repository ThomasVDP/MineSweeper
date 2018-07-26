package com.tvdp.minesweeper.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	private boolean[] keys;
	public boolean up, down, left, right;
	
	public KeyManager()
	{
		keys = new boolean[256];
	}
	
	public void tick()
	{
		if (keys[KeyEvent.VK_Z] || keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_KP_UP])
		{
			up = true;
		} else {
			up = false;
		}
		if (keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_KP_DOWN])
		{
			down = true;
		} else {
			down = false;
		}
		if (keys[KeyEvent.VK_Q] || keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_KP_LEFT])
		{
			left = true;
		} else {
			left = false;
		}
		if (keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_KP_RIGHT])
		{
			right = true;
		} else {
			right = false;
		}
	}

	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e)
	{
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e)
	{
		keys[e.getKeyCode()] = false;
		
	}
	
}
