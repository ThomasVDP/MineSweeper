package com.tvdp.minesweeper.gfx;

import java.awt.image.BufferedImage;

public class Assets
{
	public static final int width = 32;
	public static final int height = 32;
	
	public static BufferedImage flag, hidden;
	public static BufferedImage[] blocks = new BufferedImage[10];
	
	public static void init()
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/spritesheet.png"));
		
		blocks[0] = sheet.crop(0, 0, width, height);
		blocks[1] = sheet.crop(width, 0, width, height);
		blocks[2] = sheet.crop(2 * width, 0, width, height);
		blocks[3] = sheet.crop(3 * width, 0, width, height);
		blocks[4] = sheet.crop(4 * width, 0, width, height);
		blocks[5] = sheet.crop(5 * width, 0, width, height);
		blocks[6] = sheet.crop(6 * width, 0, width, height);
		blocks[7] = sheet.crop(7 * width, 0, width, height);
		blocks[8] = sheet.crop(8 * width, 0, width, height);
		blocks[9]= sheet.crop(9 * width, 0, width, height);
		flag = sheet.crop(10 * width, 0, width, height);
		hidden = sheet.crop(11 * width, 0, width, height);
		
	}
}
