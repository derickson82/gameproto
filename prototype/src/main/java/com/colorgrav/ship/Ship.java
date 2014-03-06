/**
 * 
 */
package com.colorgrav.ship;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.colorgrav.physical.Physical;
import com.colorgrav.physical.PhysicalObject;

/**
 * @author dan
 *
 */
public class Ship implements Physical {
  
  private PhysicalObject object;
  
  public Ship() {
    Polygon shipShape = new Polygon();
    shipShape.addPoint(0.0f, 0.0f);
    shipShape.addPoint(20.0f, 10.0f);
    shipShape.addPoint(40.0f, 0.0f);
    shipShape.addPoint(20.0f, 60.0f);
    
    shipShape.setCenterX(100.0f);
    shipShape.setCenterY(100.0f);
    object = new PhysicalObject(shipShape);
  }
  
  /* (non-Javadoc)
   * @see com.colorgrav.physical.Physical#getPhysicalObject()
   */
  @Override
  public PhysicalObject getPhysicalObject() {
    return object;
  }

}
