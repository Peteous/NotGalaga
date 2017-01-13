package Galaga.Fields;

import Galaga.Characters.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Brian
 */
public class Field3 extends Field {
    public Field3(Graphics page) {
        super(page);
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 10; i++) {
            aliens.add(new Alien3(this.page));
        }
        
        for (int i = 0; i < 5; i++) {
            aliens.get(i).setLocation(224, 64*i +64);
            aliens.get(i + 5).setLocation(288, 64*i +64);
        }
        fireRate = .991;
    }
    @Override
    public void alienImage(ArrayList<Image> im) {
        super.alienImage(im);
        for (Alien a : aliens) {
            a.setImage(im.get(1));
        }
    }
    @Override
    public void reset() {
        super.reset();
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 10; i++) {
            aliens.add(new Alien3(this.page));
        }
        
        for (int i = 0; i < 5; i++) {
            aliens.get(i).setLocation(224, 64*i +64);
            aliens.get(i + 5).setLocation(288, 64*i +64);
        }
    }
}
