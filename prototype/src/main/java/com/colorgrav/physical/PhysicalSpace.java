package com.colorgrav.physical;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class PhysicalSpace
{
  private List<PhysicalObject> objects = new ArrayList<PhysicalObject>();
  
  public static Line westLine = new Line(new Vector2f(0.0f, 0.0f), new Vector2f(0.0f, 480.0f));
  public static Line eastLine = new Line(new Vector2f(640.0f, 0.0f), new Vector2f(640.0f, 480.0f));
  public static Line northLine = new Line(new Vector2f(0.0f, 0.0f), new Vector2f(640.0f, 0.0f));
  public static Line southLine = new Line(new Vector2f(0.0f, 480.0f), new Vector2f(640.0f, 480.0f));
  
  private boolean shouldRender = false;
  
  public void add(Physical physical) {
    objects.add(physical.getPhysicalObject());
  }
  
  public void remove(Physical physical) {
    objects.remove(physical.getPhysicalObject());
  }
  
  public void render(Graphics g) {
    if (shouldRender) {
      for (PhysicalObject object : objects) {
        object.render(g);
      }
    }
  }
  
  public void update(int delta) {
    for (PhysicalObject object: objects) {
      object.update(delta);
    }
  }
  
  public void setRenderEnabled(boolean enabled) {
    this.shouldRender = enabled;
  }
  
  public boolean isRenderEnabled() {
    return shouldRender;
  }
}
