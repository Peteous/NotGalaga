package Galaga.Characters;

import java.awt.Graphics;

/**
 * @author Brian
 */
public class Alien4 extends Alien {
    
    public Alien4(Graphics page) {
        super(page);
        health = 8;
    }
    @Override
    public void act() {
        if (i > Math.PI * 36) {
            i = 0;
        }
        i += .08;
        sin = Math.cos(i/3);
        cos = Math.cos(i/2);
        setYspd(sin);
        setXspd(cos);
        
        move();
    }
}
