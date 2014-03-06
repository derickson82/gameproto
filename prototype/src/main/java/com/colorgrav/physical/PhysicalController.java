package com.colorgrav.physical;

import org.newdawn.slick.Input;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.geom.Vector2f;


public class PhysicalController
  implements InputProviderListener
{
  private final PhysicalObject object;

  private final InputProvider inputProvider;


  public PhysicalController(Physical physical, Input input)
  {
    object = physical.getPhysicalObject();
    inputProvider = new InputProvider(input);

    inputProvider.bindCommand(new KeyControl(Input.KEY_UP), UP);
    inputProvider.bindCommand(new KeyControl(Input.KEY_DOWN), DOWN);
    inputProvider.bindCommand(new KeyControl(Input.KEY_LEFT), LEFT);
    inputProvider.bindCommand(new KeyControl(Input.KEY_RIGHT), RIGHT);
    
    inputProvider.bindCommand(new KeyControl(Input.KEY_Z), ROTATE_COUNTER_CLOCKWISE);
    inputProvider.bindCommand(new KeyControl(Input.KEY_X), ROTATE_CLOCKWISE);

//    inputProvider.bindCommand(new KeyControl(Input.KEY_R), ROTATE);

    inputProvider.addListener(this);
  }

  private RotateCommand ROTATE_COUNTER_CLOCKWISE = new RotateCommand(-0.002f);
  private RotateCommand ROTATE_CLOCKWISE = new RotateCommand(0.002f);
  private static class RotateCommand
    implements Command
  {
    private float angularAcceleration;
    
    RotateCommand(float angularAcceleration) {
      this.angularAcceleration = angularAcceleration;
    }
    
    public void rotate(PhysicalObject object)
    {
//      object.applyAngularForce(angularAcceleration);
    }
    
    public void unrotate(PhysicalObject object) {
//      object.removeAngularForce(angularAcceleration);
    }
  }

  private static class AccelerateCommand
    implements Command
  {

    private Vector2f force;

    private AccelerateCommand(Vector2f force)
    {
      this.force = force;
    }

    void applyForce(PhysicalObject object)
    {
      object.applyForce(force);
    }

    void removeForce(PhysicalObject object)
    {
      object.removeForce(force);
    }

    @Override
    public String toString()
    {
      return "Force command: " + force;
    }
  }

  private final AccelerateCommand UP = new AccelerateCommand(new Vector2f(0.0f, -0.002f));

  private final AccelerateCommand DOWN = new AccelerateCommand(new Vector2f(0.0f, 0.002f));

  private final AccelerateCommand LEFT = new AccelerateCommand(new Vector2f(-0.002f, 0.0f));

  private final AccelerateCommand RIGHT = new AccelerateCommand(new Vector2f(0.002f, 0.0f));

  //  private final AccelerateCommand[] COMMANDS = new AccelerateCommand[]{UP, DOWN, LEFT, RIGHT};


  @Override
  public void controlPressed(Command command)
  {
    if (command instanceof AccelerateCommand) {
      ((AccelerateCommand)command).applyForce(object);
    } else if (command instanceof RotateCommand) {
      ((RotateCommand)command).rotate(object);
    }
  }

  @Override
  public void controlReleased(Command command)
  {
    if (command instanceof AccelerateCommand) {
      ((AccelerateCommand)command).removeForce(object);
    } else if (command instanceof RotateCommand) {
      ((RotateCommand)command).unrotate(object);
    }
  }
}
