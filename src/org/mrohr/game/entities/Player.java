package org.mrohr.game.entities;

import org.mrohr.game.Map;
import org.mrohr.game.MyGameContainer;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mjrohr
 * Date: 3/21/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class Player extends LivingEntity implements KeyListener {
    private final float SPEED = 0.05f;
    private Map currentMap;
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean sprinting;
    private List<Item> inventory;

    private int hunger;
    private int battery;

    int hungerTimer;
    public final int hungerTimerPeriod = 2000;

    int batteryTimer;
    public final int batteryTimerPeriod = 30000;

    public Player(int x, int y)throws SlickException{
        super(new Rectangle(x,y,32,32),new Image("res/images/player.png"),true,100);
        this.setDebugColor(Color.pink);
        inventory = new ArrayList<Item>();
        hunger = 100;
        battery = 100;
        damage(10);
        hungerTimer = hungerTimerPeriod;
        batteryTimer = batteryTimerPeriod;
    }

    public void eat(Food item){
        hunger += item.getHungerAmount();
    }
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }
    public void setCurrentMap(Map map){
        currentMap = map;
    }

    @Override
    public void onDamaged(int amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onHealed(int amount) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onDeath() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.exit(0);
    }

    @Override
    public void onCollision(CollidableEntity other) {
        super.onCollision(other);
        if(other instanceof LivingTree){
            this.damage(10);
        }
    }

    @Override
    public void init(MyGameContainer gameContainer) throws SlickException {
        //To change body of implemented methods use File | Settings | File Templates.
        //super.init(gameContainer);
        gameContainer.getInput().addKeyListener(this);


    }
    public void update(MyGameContainer gameContainer, int i) throws SlickException {
        setHeading((float)Math.toDegrees(currentMap.calculatePlayerHeading()));

        float currentX = 0;
        float currentY = 0;
        float speed = SPEED;
        if(sprinting){
            speed = SPEED * 1.5f;
        }
        if(upPressed){
            currentY = -speed;
        }
        if(downPressed){
            currentY = speed;
        }
        if(leftPressed){
            currentX = -speed;
        }
        if(rightPressed){
            currentX = speed;
        }

        hungerTimer -= sprinting? i * 3: i;
        if(hungerTimer < 0){
            hunger --;
            hungerTimer = hungerTimerPeriod;
            if(hunger < 0){
                this.onDeath();
            }
        }

        setSpeed(currentX,currentY);
        super.update(gameContainer,i);

    }


    public void keyPressed(int key,char c){
        if(key ==Input.KEY_LSHIFT){
            sprinting = true;
        }

        if(key ==Input.KEY_W){
            upPressed = true;
        }
        if(key ==Input.KEY_S){
            downPressed = true;
        }
        if(key ==Input.KEY_A){
            leftPressed = true;
        }
        if(key ==Input.KEY_D){
            rightPressed = true;
        }
    }

    public void keyReleased(int key,char c){
        if(key ==Input.KEY_LSHIFT){
            sprinting = false;
        }
        if(key ==Input.KEY_W){
            upPressed = false;
        }
        if(key ==Input.KEY_S){
            downPressed = false;
        }
        if(key ==Input.KEY_A){
            leftPressed = false;
        }
        if(key ==Input.KEY_D){
            rightPressed = false;
        }
    }
    public void setInput(Input input) {
    }
    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    /**
     * unused
     */
    @Override
    public void inputEnded() {
    }

    /**
     * unused
     */
    @Override
    public void inputStarted() {
    }


    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }
}
