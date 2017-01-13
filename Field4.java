package Galaga.Fields;

import Galaga.Characters.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Brian
 */
public class Field4 extends Field {
    
    public Field4(Graphics page) {
        super(page);
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 12; i++) {
            aliens.add(new Alien4(this.page));
        }
        
        for (int i = 0; i < 4; i++) {
            aliens.get(i).setLocation(192, 64*i +64);
            aliens.get(i + 4).setLocation(256, 64*i +64);
            aliens.get(i + 8).setLocation(320, 64*i +64);
        }
        fireRate = .99;
    }
    @Override
    public void alienImage(ArrayList<Image> im) {
        super.alienImage(im);
        for (Alien a : aliens) {
            a.setImage(im.get(2));
        }
    }
    @Override
    public void reset() {
        super.reset();
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 12; i++) {
            aliens.add(new Alien4(this.page));
        }
        
        for (int i = 0; i < 4; i++) {
            aliens.get(i).setLocation(192, 64*i +64);
            aliens.get(i + 4).setLocation(256, 64*i +64);
            aliens.get(i + 8).setLocation(320, 64*i +64);
        }
        alienImage(img);
    }
}
