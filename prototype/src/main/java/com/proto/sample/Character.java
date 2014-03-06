/***********************************************************************************************************************
 * Copyright (C) 2010 Partnet, Inc. Confidential and Proprietary
 */
package com.proto.sample;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Vector2f;

import com.proto.sample.SampleGame.MyCommand;

/**
 * @author dan
 * 
 */
public class Character implements InputProviderListener {
  private final Vector2f position;
  private Image characterImage;

  private InputProvider inputProvider;

  private int speed = 3;

  public Character(String imageFile, float x, float y) {
    position = new Vector2f(100, 100);

    try {
      characterImage = new Image(imageFile, new Color(148, 148, 145));
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }
  
  public void setActive(boolean active) {
    System.out.println("setActive: " + active);
    inputProvider.setActive(active);
  }
  
  public boolean isActive() {
    return inputProvider.isActive();
  }

  public void acceptInput(Input input) {
    inputProvider = new InputProvider(input);

    inputProvider.bindCommand(new KeyControl(Input.KEY_DOWN), DOWN);
    inputProvider.bindCommand(new KeyControl(Input.KEY_UP), UP);
    inputProvider.bindCommand(new KeyControl(Input.KEY_LEFT), LEFT);
    inputProvider.bindCommand(new KeyControl(Input.KEY_RIGHT), RIGHT);

    inputProvider.bindCommand(new KeyControl(Input.KEY_S), DOWN);
    inputProvider.bindCommand(new KeyControl(Input.KEY_W), UP);
    inputProvider.bindCommand(new KeyControl(Input.KEY_A), LEFT);
    inputProvider.bindCommand(new KeyControl(Input.KEY_D), RIGHT);
    inputProvider.bindCommand(new KeyControl(Input.KEY_1), MyCommand.KEY_1);
    inputProvider.bindCommand(new KeyControl(Input.KEY_2), MyCommand.KEY_2);
    inputProvider.bindCommand(new KeyControl(Input.KEY_3), MyCommand.KEY_3);
    inputProvider.bindCommand(new KeyControl(Input.KEY_4), MyCommand.KEY_4);
    inputProvider.bindCommand(new KeyControl(Input.KEY_5), MyCommand.KEY_5);
    
    inputProvider.addListener(this);
  }

  public void render(GameContainer gc, Graphics graphics) throws SlickException {
    graphics.drawString("Current position: (" + position.x + ", " + position.y
        + ") ", 0, 20);
    graphics.drawString("Speed: " + this.speed + " (Set with keys 1 - 5)", 0,
        40);
    graphics.drawImage(characterImage, position.x, position.y);
  }

  public void update(GameContainer gc, int delta) {
    if (inputProvider.isActive()) {
      DOWN.move(delta);
      UP.move(delta);
      LEFT.move(delta);
      RIGHT.move(delta);
    }
  }

  @Override
  public void controlPressed(Command command) {
    if (command instanceof MyCommand) {
      MyCommand myCommand = MyCommand.class.cast(command);
      switch (myCommand) {
      case KEY_1:
        speed = 1;
        break;
      case KEY_2:
        speed = 2;
        break;
      case KEY_3:
        speed = 3;
        break;
      case KEY_4:
        speed = 4;
        break;
      case KEY_5:
        speed = 5;
        break;
      }
    }
  }

  @Override
  public void controlReleased(Command arg0) {
  }

  private abstract class MovementCommand implements Command {
    void move(int delta) {
      if (inputProvider.isCommandControlDown(this)) {
        doMove(delta);
      }
    }
    
    abstract void doMove(int delta);
  }

  private MovementCommand UP = new MovementCommand() {
    @Override
    public void doMove(int delta) {
      Character.this.position.y -= Character.this.speed * delta * 0.1f;
    }
  };
  
  private MovementCommand LEFT = new MovementCommand() {
    @Override
    public void doMove(int delta) {
      Character.this.position.x -= Character.this.speed * delta * 0.1f;
    }
  };

  private MovementCommand RIGHT = new MovementCommand() {
    @Override
    public void doMove(int delta) {
      Character.this.position.x += Character.this.speed * delta * 0.1f;
    }
  };

  private MovementCommand DOWN = new MovementCommand() {
    @Override
    public void doMove(int delta) {
      Character.this.position.y += Character.this.speed * delta * 0.1f;
    }
  };
}
