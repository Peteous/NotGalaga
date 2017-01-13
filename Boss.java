package Galaga.Characters;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Boss extends Alien {

    private double i = 0, sin = 0, cos = 0;
    private double rotation = Math.PI / 2;
    protected double theta;
    private Image creeperBoss, weapon1, weapon2;
    private AffineTransform id = new AffineTransform();

    public Boss(Graphics page) {
        super(page);
        setX(256);
        setY(1);
        xposi = 256 - 24;
        yposi = 0;
        welp = new Weapon(Weapon.WEAPON_CREEPER);
        health = 100;
        size = 96;
    }

    public void setPublicEnemy(Ship Player) {
        player = Player;
    }

    public void setTheta(double d) {
        theta = d;
    }

    public double getTheta() {
        return theta;
    }

    public void setRotation(double d) {
        rotation = d;
    }

    public double getRotation() {
        return rotation;
    }

//    private void rotate() {
//        if (rotation != theta * 2) {
//            creeperBoss.createGraphics().rotate(-rotation + theta, getCenter().x, getCenter().y);
//        }
//        rotation = theta;
//    }
//
    public void setImage(Image image) {
        creeperBoss = image;
        super.setImage(image);
    }
    public Image getImage() {
        return creeperBoss;
    }

    private void turn() {
        AffineTransform other = new AffineTransform ();
        other.setTransform (id);

        if (getX() - player.getX() == 0) {
            theta = Math.PI / 2;
        } else {
            theta = Math.acos ((player.getX () - getX ()) / loc.distance (player.loc));
        }
        other.rotate(theta);
    }

    @Override
    public void act() {
        if (i > Math.PI * 360) {
            i = 0;
        }
        i += .05;
        sin = Math.sin (i + Math.PI/2)/3 + .15;
        cos = Math.cos (i/2 + Math.PI)/3;
        setYspd (sin);
        setXspd (cos);

        if (player.loc.x - this.getX() == 0) {
            theta = Math.PI / 2;
        } else {
            theta = Math.atan2(-player.getY() + this.getY(), -player.getX() + this.getX());
            // (Math.tan ((you.getX () - this.getX ()) / (you.getY () - loc.y)))+ Math.PI/2;
            theta += Math.PI;
        }

        move ();
    }

    @Override
    @Deprecated
    public void move() {
        xposi = xposi + xspeed;
        yposi += yspeed;
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

    public AffineTransform getAT() {
        return id;
    }
//    public void setWeaponImages(Image Weapon1, Image Weapon2){
//
//    }
    
    @Override
    public ArrayList<Dot> fire() {
        return welp.fire (xposi, yposi, theta);
    }
}
