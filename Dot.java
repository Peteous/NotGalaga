package Galaga.Characters;

/**
 * @author Brian
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Dot {

    double x, y;
    int intx, inty;
    int wallx, wally;
    double theta;
    public double speed = 6.5;
    Point location;
    Color color = Color.green;
    int size = 6;
    Image PIC;
    boolean active;

    public Dot() {
        this.color = Color.green;
        location = new Point ();
        active = false;
    }

    public Dot(Point p, double direction) {
        location = p.getLocation ();
        this.intx = p.x;
        this.inty = p.y;
        x = p.x;
        y = p.y;
        speed = 6.5;
        wallx = 512;
        wally = 512;
        theta = direction;
        active = true;
    }

    public Dot(int xx, int yy, double direction) {
        location = new Point (xx, yy);
        this.intx = xx;
        this.inty = yy;
        x = xx;
        y = yy;
        speed = 6.5;
        wallx = 512;
        wally = 512;
        theta = direction;
        active = true;
    }

    public Dot(int xx, int yy, double direction, int Size) {
        location = new Point (xx, yy);
        this.intx = xx;
        this.inty = yy;
        x = xx;
        y = yy;
        size = Size;
        speed = 6.5;
        wallx = 512;
        wally = 512;
        theta = direction;
        active = true;
    }

    public void createNEW(Point p, int xwall, int ywall, double direction, boolean active) {
        intx = (int) Math.round (p.x - (size / 2));
        inty = (int) Math.round (p.y - (size / 2));
        x = Math.round (p.x - (size / 2));
        y = Math.round (p.y - (size / 2));
        speed = 6.5;
        location = p.getLocation ();
        wallx = xwall;
        wally = ywall;
        theta = direction;
        this.active = active;
    }

    public int getX() {
        return intx;
    }

    public int getY() {
        return inty;
    }

    public int size() {
        return size;
    }

    /**
     * updates the location of the bullet
     */
    public void update() {
        if (this.isActive ()) {
            if (checkForWallx ()) {
                x += (Math.cos (theta) * this.speed);
            } else {
                this.speed = 0;
                active = false;
            }
            intx = (int) Math.round (x);
//===============================================
            if (checkForWally ()) {
                y += (Math.sin (theta) * this.speed);
            } else {
                this.speed = 0;
                active = false;
            }
            inty = (int) Math.round (y);
        }
        this.setLocationPoint ();
    }

    public void deactivate() {
        active = false;
    }

    /* sets the image of this bullet
     * @param Image
     * @return void */
    public void setPIC(Image i) {
        PIC = i;
    }

    /**
     * returns the image of this bullet
     * @return the Bullet Image
     */
    public Image getPIC() {
        return PIC;
    }

    private void setLocationPoint() {
        location.setLocation (x, y);
    }

    private boolean checkForWallx() {
        for (int A = 0; A < 4; A++) {
            if (((x - size/2) < 512) & ((x + size/2) > 0)) {
                return true;
            }
        }
        active = false;
        return false;
    }

    private boolean checkForWally() {//lololol
        if (((y - size/2) < 512) & ((y + size/2) > 0)) {
            return true;
        }
        active = false;
        return false;
    }

    /**
     * returns the speed at which the bullet should travel
     * @return double
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * returns the surrounding rectangle
     * @return rectangle(intx, inty, size, size)
     */
    public Rectangle getBounds() {
        return new Rectangle(intx - size / 2, inty - size / 2, size, size);
    }

    /**
     * returns if bullet is active
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean alive) {
        active = alive;
    }

    /**
     *draws image or a red oval
     * @param Graphics (such as a buffer)
     * @return the image or red oval
     */
    public void draw(Graphics e) {
//        e.drawImage(PIC, intx, inty, null);
        e.setColor(color);
        e.fillOval(intx - (int) (size/2), inty - (int) (size/2), size, size);
    }
}
