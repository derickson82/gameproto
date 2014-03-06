/**
 * 
 */
package com.proto.sample;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;

/**
 * @author dan
 * 
 */
public class SampleGame implements Game, InputProviderListener {

	private boolean quit = false;

	private Scene scene;
	private Character character;
	private InputProvider inputProvider;

	public SampleGame() {
	  // seem to need a default constructor for an applet to work
	}
	
	/**
	 * @param title
	 */
	public SampleGame(String title) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 */
	@Override
	public void init(GameContainer gc) throws SlickException {
		scene = new Scene("images/background.png");
		character = new Character("images/character.png", 100.0f, 100.0f);
    character.acceptInput(gc.getInput());

    inputProvider = new InputProvider(gc.getInput());
    inputProvider.bindCommand(new KeyControl(Input.KEY_Q), MyCommand.QUIT);
    inputProvider.bindCommand(new KeyControl(Input.KEY_Z), TOGGLE_ACTIVE);
		inputProvider.addListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 * int)
	 */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (quit) {
			container.exit();
		}
		
		character.update(container, delta);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 * org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, Graphics graphics)
			throws SlickException {

		scene.render(gc, graphics);
		character.render(gc, graphics);
		if (!quit) {
			graphics.drawString("Hit 'Q' to quit", 0, 0);
		} else {
			graphics.drawString("Quitting...", 0, 0);
		}
	}

	@Override
	public void controlReleased(Command command) {
	}

	@Override
	public void controlPressed(Command command) {
		if (command instanceof MyCommand) {
			MyCommand myCommand = MyCommand.class.cast(command);
			switch (myCommand) {
			case QUIT:
				quit = true;
				break;
			}

		}
		
		if (command instanceof ToggleActive) {
		  System.out.println("Toggling active");
		  ((ToggleActive)command).toggleActive();
		}
	}

	public static enum MyCommand implements Command {
		QUIT, KEY_1, KEY_2, KEY_3, KEY_4, KEY_5
	}
	
  private class ToggleActive implements Command {
    public void toggleActive() {
      character.setActive(!character.isActive());
    }
  }
  
  private ToggleActive TOGGLE_ACTIVE = new ToggleActive();


	@Override
	public boolean closeRequested() {
		return true;
	}

	@Override
	public String getTitle() {
		return "Prototype";
	}

	public static void main(String[] args) {

		try {
			final AppGameContainer container = new AppGameContainer(
					new SampleGame("Sample Game"), 640, 480, false);

			container.setShowFPS(false);
			container.setForceExit(false);
			container.start();
			System.out.println("Exiting");

		} catch (SlickException e) {
			e.printStackTrace(System.err);
		}
	}

}