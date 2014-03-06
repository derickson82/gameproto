package com.colorgrav;

import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import com.colorgrav.asteroid.Asteroid;
import com.colorgrav.physical.MovementController;
import com.colorgrav.physical.PhysicalController;
import com.colorgrav.physical.PhysicalSpace;
import com.colorgrav.ship.Ship;

public class ColorGravGame
  implements Game
{
  private final PhysicalSpace physicalSpace;
  private MovementController controller;
  
  /**
   * 
   */
  public ColorGravGame()
  {
    physicalSpace = new PhysicalSpace();
  }

  /* (non-Javadoc)
   * @see org.newdawn.slick.Game#getTitle()
   */
  @Override
  public String getTitle()
  {
    return "ColorGrav";
  }

  /* (non-Javadoc)
   * @see org.newdawn.slick.Game#init(org.newdawn.slick.GameContainer)
   */
  @Override
  public void init(GameContainer gc)
    throws SlickException
  {
    Ship ship = new Ship();
    physicalSpace.add(ship);
    
//    for (int i = 0; i < 5; i++) {
//      physicalSpace.add(randomAsteroid());
//    }
    
    physicalSpace.setRenderEnabled(true);
    
    controller = new MovementController(ship, gc.getInput());
    
  }

  private static Random random = new Random();
  public Asteroid randomAsteroid() {
    float centerX = random.nextFloat() * 640.0f;
    float centerY = random.nextFloat() * 480.0f;
    Vector2f center = new Vector2f(centerX, centerY);
    
    float velocityX = (random.nextFloat() * 1.0f) - 0.5f;
    float velocityY = (random.nextFloat() * 1.0f) - 0.5f;
    Vector2f velocity = new Vector2f(velocityX, velocityY);
    
    return new Asteroid(center, velocity);
  }

  /* (non-Javadoc)
   * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer, org.newdawn.slick.Graphics)
   */
  @Override
  public void render(final GameContainer gc, final Graphics graphics)
    throws SlickException
  {
    physicalSpace.render(graphics);
  }

  /* (non-Javadoc)
   * @see org.newdawn.slick.Game#update(org.newdawn.slick.GameContainer, int)
   */
  @Override
  public void update(GameContainer gc, int delta)
    throws SlickException
  {
    physicalSpace.update(delta);
  }

  /* (non-Javadoc)
   * @see org.newdawn.slick.Game#closeRequested()
   */
  @Override
  public boolean closeRequested()
  {
    return true;
  }
  
  public static void main(String[] args) {
    try {
      final AppGameContainer container = new AppGameContainer(
          new ColorGravGame(), 640, 480, false);

      container.setShowFPS(true);
      container.setForceExit(false);
      container.start();
      System.out.println("Exiting");

    } catch (SlickException e) {
      e.printStackTrace(System.err);
    }
  }

}
