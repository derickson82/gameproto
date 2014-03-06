package com.colorgrav.physical;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class PhysicalObject
{
  // there is an absolute position
  private final Vector2f position = new Vector2f(100.0f, 100.0f);
  private Vector2f velocity = new Vector2f(0.0f, 0.0f);
  private final Vector2f force = new Vector2f(0.0f, 0.0f);  

  // eventually, multiple shapes could define this physical object
  private Shape physicalShape;
  
  private float facingAngle = 0.0f;
  
  public PhysicalObject() {
    this.physicalShape = new Rectangle(position.x, position.y, 50.0f, 50.0f);
  }
  
  public PhysicalObject(Shape boundingShape) {
    this.physicalShape = boundingShape;
    this.position.x = physicalShape.getCenterX();
    this.position.y = physicalShape.getCenterY();
  }
  
  public void render(Graphics g) {
    g.draw(rotate(physicalShape));
  }
  
  public void update(int delta) {

//    float seconds_passed = delta * 1000.0f;
	  
    // first apply forces to adjust the velocity
    velocity.set(velocity.x + force.x * delta, velocity.y + force.y * delta);
    
    // then apply the velocity to adjust the position
    position.set(position.x + velocity.x * delta, position.y + velocity.y * delta);
    
    // then set the position of the shape
    physicalShape.setCenterX(position.x);
    physicalShape.setCenterY(position.y);
  }
  
  public void setInitialVelocity(Vector2f velocity) {
    this.velocity = velocity;
  }
  
  public void setVelocity(Vector2f velocity) {
	  this.velocity = velocity;
  }
  
  public Shape rotate(Shape shape) {
//    float angle = (float)(Math.PI) * (float)(delta / 1000.0);

    Transform t = Transform.createRotateTransform(facingAngle, physicalShape.getCenterX(), physicalShape.getCenterY());
    return physicalShape.transform(t);
  }
  
  public void applyForce(Vector2f force) {
    this.force.set(this.force.add(force));
  }
  
  public void removeForce(Vector2f force) {
    this.force.set(this.force.add(force.negate()));
  }
  
  public void setAngle(float angle) {
	  this.facingAngle = angle;
  }
  
  public float getAngle() {
	  return facingAngle;
  }
  
}
