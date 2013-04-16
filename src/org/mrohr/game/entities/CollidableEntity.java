package org.mrohr.game.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 11:36 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class CollidableEntity extends Entity {
    boolean solid;
    private Shape collidableShape;


    public CollidableEntity(Shape bb,boolean solid){
        super(bb);
        collidableShape = bb;
        this.solid = solid;
        this.setDebugColor(new Color(0,255,0,200));
    }


    public CollidableEntity(Shape bb,Image img, boolean solid){
        super(bb,img);
        collidableShape = bb;
        this.solid = solid;
        this.setDebugColor(new Color(0,255,0,200));
    }

    public boolean testCollision(CollidableEntity other){
      if(this.collidableShape.intersects(other.collidableShape)){
          onCollision(other);
          other.onCollision(this);
          return true;
      }
        return false;
    }

    public abstract void onCollision(CollidableEntity other);

    public Shape getCollidableShape() {
        return collidableShape;
    }

    public void setCollidableShape(Shape collidableShape) {
        this.collidableShape = collidableShape;
    }
}