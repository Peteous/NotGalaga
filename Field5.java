package Galaga.Fields;

import Galaga.Characters.*;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

/**
 * @author Brian
 */
public class Field5 extends Field {
    
    public Field5(Graphics page) {
        super(page);
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 4; i++) {aliens.add(new Alien3(page));}
        for (int i = 0; i < 5; i++) {aliens.add(new Alien2(page));}
        for (int i = 0; i < 4; i++) {aliens.add(new Alien4(page));}
        
        for (int i = 0; i < 4; i++) {aliens.get(i).setLocation(i*64 + 160, 64);}
        for (int i = 0; i < 5; i++) {aliens.get(i+4).setLocation(i*64 + 128, 128);}
        for (int i = 0; i < 4; i++) {aliens.get(i+9).setLocation(i*64 + 160, 224);}
    }
    
    @Override
    public void alienImage(ArrayList<Image> im) {
        super.alienImage(im);
        for (int a = 0; a < 4; a++) {aliens.get(a).setImage(im.get(1));}
        for (int a = 4; a < 9; a++) {aliens.get(a).setImage(im.get(0));}
        for (int a = 9; a < 13; a++) {aliens.get(a).setImage(im.get(2));}
    }
    @Override
    public void reset() {
        aliens = new ArrayList<Alien>();
        for (int i = 0; i < 4; i++) {aliens.add(new Alien3(page));}
        for (int i = 0; i < 5; i++) {aliens.add(new Alien2(page));}
        for (int i = 0; i < 4; i++) {aliens.add(new Alien4(page));}
        
        for (int i = 0; i < 4; i++) {aliens.get(i).setLocation(i*64 + 160, 64);}
        for (int i = 0; i < 5; i++) {aliens.get(i+4).setLocation(i*64 + 128, 128);}
        for (int i = 0; i < 4; i++) {aliens.get(i+9).setLocation(i*64 + 160, 224);}
        alienImage(img);
    }
}
