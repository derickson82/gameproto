package com.colorgrav.asteroid;

import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import com.colorgrav.physical.Physical;
import com.colorgrav.physical.PhysicalObject;

public class Asteroid implements Physical {

  private PhysicalObject object;
  
  public Asteroid(Vector2f center, Vector2f velocity) {
//    Polygon asteroidShape = new Polygon();
//    asteroidShape.addPoint(0.0f, 0.0f);
//    asteroidShape.addPoint(5.0f, -10.0f);
//    asteroidShape.addPoint(7.0f, -5.0f);
//    asteroidShape.addPoint(9.0f, 2.0f);
//    asteroidShape.addPoint(15.0f, 10.0f);
//    asteroidShape.addPoint(16.0f, 15.0f);
//    asteroidShape.addPoint(14.0f, 18.0f);
//    asteroidShape.addPoint(10.0f, 16.0f);
//    asteroidShape.addPoint(5.0f, 7.0f);
//    asteroidShape.addPoint(3.0f, 3.0f);
    
    Circle asteroidShape = new Circle(0.0f, 0.0f, 25.0f);
    
    asteroidShape.setCenterX(center.x);
    asteroidShape.setCenterY(center.y);
    
    object = new PhysicalObject(asteroidShape);
    object.setInitialVelocity(velocity);
  }
  
  @Override
  public PhysicalObject getPhysicalObject() {
    return object;
  }

}
