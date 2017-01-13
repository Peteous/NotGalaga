package Galaga.Characters;

import java.awt.Graphics;

/**
 * @author Brian
 */
public class Alien2 extends Alien {
    public Alien2(Graphics page) {
        super(page);
        health = 6;
    }
    
    @Override
    public void act() {
        if (i > Math.PI * 36) {
            i = 0;
        }
        i += .08;
        sin = Math.cos(i);
        cos = -Math.cos(i/2 + Math.PI);
        setYspd(sin);
        setXspd(-cos);
        
        move();
    }
}
