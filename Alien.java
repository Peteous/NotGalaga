package Galaga.Characters;

import java.awt.Graphics;

/**
 * @author Brian @author Peteous
 */
public class Alien extends Ship {
    protected double i=0,sin=0,cos=0;
    protected Ship player;
    
    public Alien(Graphics page) {
        super(page);
        setX(256); setY(1);
        xposi = 256; yposi = 0;
        welp = new Weapon(Weapon.WEAPON_A);
        health = 5;
    }
    @Override public void act() {
        if (i > Math.PI * 36) {
            i = 0;
        }
        i += .08;
        sin = Math.sin(i);
        cos = -Math.cos(i);
        setYspd(sin);
        setXspd(cos);
        
        move();
    }
    
    @Override @Deprecated
    public void move() {
        xposi = xposi + xspeed;
        yposi+=yspeed;
        if(yposi > 512-40)
            yposi = 512-40;
        if(xposi >512-24)
            xposi = 512-24;
        if(yposi < 24)
            yposi = 24;
        if(xposi < 24)
            xposi = 24;
        xpos = (int) (xposi);
        ypos = (int) (yposi);
        loc.setLocation(xpos, ypos);
    }

    public void setPublicEnemy(Ship Player) {
        player = Player;
    }
}
