package Galaga.Fields;

import Galaga.Characters.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Brian
 */
public class Field2 extends Field {
    
    public Field2(Graphics page) {
        super(page);
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 10; i++) {
            aliens.add(new Alien2(this.page));
        }
        for (int i = 0; i < 5; i++) {
            aliens.get(i).setLocation(64 + 96*i, 96);
            aliens.get(i + 5).setLocation(64 + 96*i, 96*2);
        }
        fireRate = .992;
    }
    
    @Override
    public void alienImage(ArrayList<Image> im) {
        super.alienImage(im);
        for (Alien a : aliens) {
            a.setImage(im.get(0));
        }
    }
    @Override
    public void reset() {
        super.reset();
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 10; i++) {
            aliens.add(new Alien2(page));
            aliens.get(i).setImage(img.get(5));
        }
        for (int i = 0; i < 5; i++) {
            aliens.get(i).setLocation(64 + 96*i, 96);
            aliens.get(i +5).setLocation(64 + 96*i, 96*2);
        }
    }
}
