package org.mrohr.game.entities.items;

import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/26/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Berry extends Food {
    public Berry(int x, int y) throws SlickException {
        super(new Rectangle(x,y,32,32), new Image("res/images/berry.png"), 15);
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
