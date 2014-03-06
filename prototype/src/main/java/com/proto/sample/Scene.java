/**
 * 
 */
package com.proto.sample;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * @author Dan
 * 
 */
public class Scene {

	private Image background;
	
	public Scene(String backgroundImage) {
		try {
			background = new Image(backgroundImage);			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(GameContainer gc, Graphics graphics)
			throws SlickException {
		graphics.drawImage(background, 0, 0);
	}
}
