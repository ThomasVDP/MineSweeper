package com.tvdp.minesweeper;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.tvdp.minesweeper.display.Display;
import com.tvdp.minesweeper.gfx.Assets;
import com.tvdp.minesweeper.gfx.GameCamera;
import com.tvdp.minesweeper.input.KeyManager;
import com.tvdp.minesweeper.input.MouseManager;
import com.tvdp.minesweeper.state.GameState;
import com.tvdp.minesweeper.state.State;

public class Game implements Runnable
{
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private MouseManager mouseManager;
	private KeyManager keyManager;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private GameCamera gameCamera;
	
	private Handler handler;
	
	//States
	private State gameState;
	
	public Game(String title, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
	}
	
	private void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addMouseListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();

		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler, 100, 100, 1000);
		State.setState(gameState);
	}
	
	private void tick()
	{
		keyManager.tick();
		if (State.getState() != null)
		{
			State.getState().tick();
		}
	}
	
	private void render()
	{
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null)
		{
			display.getCanvas().createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();

		g.clearRect(0, 0, width, height);
		
		if (State.getState() != null)
		{
			State.getState().render(g);
		}
		
		bs.show();
		g.dispose();
	}

	@Override
	public void run()
	{
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= 1000000000)
			{
				System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public MouseManager getMouseManager()
	{
		return mouseManager;
	}
	
	public KeyManager getKeyManager()
	{
		return keyManager;
	}
	
	public GameCamera getGameCamera()
	{
		return gameCamera;
	}
	
	public State getGameState()
	{
		return gameState;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop()
	{ 
		if (!running)
			return;
		
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
