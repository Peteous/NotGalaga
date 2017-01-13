package Galaga.Characters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

/**
 * @author Brian
 */
public class Ship extends Object {

    protected int xpos, ypos;
    protected int health = 3;
    protected double xspeed = 5, yspeed = 5, xposi = 256, yposi = 400;
    protected double size = 48;
    double[] x = {xpos + 10, xpos + 4, xpos + 18, xpos + 7, xpos - 1, xpos - 2};
    double[] y = {ypos - 6, ypos + 2, ypos + 10, ypos + 7, ypos + 12, ypos - 2};
    private boolean left = false, right = false, forward = false, backward = false;
    public int lives;
    protected Point loc;
    private Image ship;
    public Weapon welp;
    protected Graphics page;

    public Ship(Graphics g) {
        page = g;
        ypos = 512 - 16 - 96;
        xpos = 256;
        lives = 3;
        loc = new Point(xpos, ypos);
        ship = null;
        welp = new Weapon(Weapon.WEAPON_9);
    }

    public void setImage(Image image) {ship = image;}
    public Image getImage() {return ship;}

    public int getX() {return xpos;}
    public int getY() {return ypos;}
    @Deprecated
    public void setX(double xpos) {
        this.xposi = xpos;
        return;
    }
    @Deprecated
    public void setY(double ypos) {
        this.yposi = ypos;
    }
    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
        loc.setLocation(x, y);
    }
    public void setLocation(Point p) {
        setX(p.getX());
        setY(p.getY());
        loc = p;
    }
    public double getDistance(Point p) {
        return Math.sqrt((p.x - xposi) * (p.x - xposi) + (p.y - yposi) * (p.y - yposi));
    }
    public double getDistance(int x, int y) {
        return Math.sqrt((x - xposi) * (x - xposi) + (y - yposi) * (y - yposi));
    }
    public boolean collide(Ship other) {
        if (getDistance(other.getX(), other.getY()) < other.size() / 2 + this.size() / 2) {
            other.health -= 10;
            health -= 10;
            return true;
        }
        return false;
    }
    public boolean collide(Dot other) {
        if (getDistance(other.getX(), other.getY()) < other.size() / 2 + this.size() / 2) {
            health--;
            return true;
        }
        return false;
    }
    public int health() {
        return health;
    }
    
    public double size() {
        return size;
    }
    
    public void setWeapon(int w) {
        welp = new Weapon (w);
    }
    public int getWeapon() {
        return welp.serialnum();
    }

    public void act() {
        move ();
    }

    @Deprecated
    public void move() {
        if (left) {
            xposi -= xspeed;
        }
        if (right) {
            xposi += xspeed;
        }
        if (forward) {
            yposi -= yspeed;
        }
        if (backward) {
            yposi += yspeed;
        }

//        xposi = xposi + xspeed;
//        yposi+=yspeed;
        if (yposi > 512 - 40) {
            yposi = 512 - 40;
        }
        if (xposi > 512 - 24) {
            xposi = 512 - 24;
        }
        if (yposi < 24) {
            yposi = 24;
        }
        if (xposi < 24) {
            xposi = 24;
        }
        xpos = (int) (xposi);
        ypos = (int) (yposi);
        loc.setLocation(xpos, ypos);
    }
    public void setXspd(double speed) {xspeed = speed;}
    public void setYspd(double speed) {yspeed = speed;}
    
    public ArrayList<Dot> fire() {return welp.fire(xposi, yposi);}
    
    public void left(boolean l) {left = l;}
    public void right(boolean r) {right = r;}
    public void forward(boolean f) {forward = f;}
    public void backward(boolean b) {backward = b;}
    public void setHealth(int h) {health = h;}
    public void draw() {
        page.drawImage(ship, (int) (xpos - size/2), (int) (ypos - size/2), (int) size, (int) size, null);
    }
    public void explode() {
        for (double x1 : x) {
            x1 = 1.04 * x1;
        }
        int[] xx = new int[x.length];
        for (int i = 0; i < x.length; i++) {
            xx[i] = (int) x[i];
        }
        int[] yy = new int[y.length];
        page.fillPolygon(null);
    }
}
