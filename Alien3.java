package Galaga.Characters;

import java.awt.Graphics;

/**
 * @author Brian
 */
public class Alien3 extends Alien {
    public Alien3(Graphics page) {
        super(page);
        health = 7;
    }
    
    @Override
    public void act() {
        if (i > Math.PI * 36) {
            i = 0;
        }
        i += .08;
        sin = Math.cos(i + Math.PI/2);
        cos = -Math.cos(i/2 + Math.PI);
        setYspd(sin);
        setXspd(cos);
        
        move();
    }
}
