package Galaga.Fields;

/**
 * @author Brian
 */
import Galaga.Characters.*;
import Galaga.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Deprecated
public class Field implements MouseListener, KeyListener, ActionListener {
    public Ship you;
    protected ArrayList<Alien> aliens;
    ArrayList<Dot> bullets; //your bullets
    ArrayList<Dot> lazers;  //alien bullets
    protected Graphics page;
    protected Random rand = new Random();
    private ArrayList<Star> stars = new ArrayList<Star>();
    int lives;
    boolean won, meh = true;
    ArrayList<Image> img;
    public boolean fire, fl = false;
    protected double fireRate;
    public int score;
    
    public Field(Graphics page) {
        this.page = page;
        won = false;
        score = 0;
        you = new Ship(this.page);
        aliens = new ArrayList<Alien>();
        lives = 2;
        bullets = new ArrayList<Dot>();
        lazers = new ArrayList<Dot>();
        for (int num = 0; num < 8; num++) {
            aliens.add(new Alien(this.page));
        }
        for (int q = 0; q < 2; q++) {
            aliens.get(4*q+0).setLocation((0)*96+112, q*64+32);
            aliens.get(4*q+1).setLocation((1)*96+112, q*64+32);
            aliens.get(4*q+2).setLocation((2)*96+112, q*64+32);
            aliens.get(4*q+3).setLocation((3)*96+112, q*64+32);
        }
        fireRate = .993;
    }
    public void reset() {
        won = false;
        Image i = you.getImage();
        you = new Ship(page);
        you.setImage(i);
        aliens = new ArrayList<Alien>();
        lives = 2;
        bullets = new ArrayList<Dot>();
        lazers = new ArrayList<Dot>();
        
        for (int num = 0; num < 8; num++) {
            aliens.add(new Alien(page));
        }
        for (Alien a : aliens) {
            a.setImage(img.get(0));
        }
        for (int q = 0; q < 2; q++) {
            aliens.get(4*q+0).setLocation((0)*96+112, q*64+32);
            aliens.get(4*q+1).setLocation((1)*96+112, q*64+32);
            aliens.get(4*q+2).setLocation((2)*96+112, q*64+32);
            aliens.get(4*q+3).setLocation((3)*96+112, q*64+32);
        }
    }
    public void shipImage(Image image) {you.setImage(image);}
    public void alienImage(ArrayList<Image> image) {
        for (Alien loop : aliens) {
            loop.setImage(image.get(0));
        }
        img = image;
    }
    public void moreBullets(Dot d) {bullets.add(d);}
    public void setShip(Ship s) {you = s;}
    public Ship getShip() {return you;}
    
//Don't override this, unless you're ready to deal with some serious shenanigans
    public void draw() {
        if (you.health() > 0 | lives >= 0) {
            if (aliens.isEmpty()) {
                win();
            }
            if (you.health() <= 0 & lives >= 0) {
                die();
            }
            
            you.act();
            
            if (fire & you.getWeapon() == Weapon.WEAPON_4) {
                for (Dot d : you.fire()) {
                    this.moreBullets(d);
                }
            }
            for (Dot d : lazers){d.update(); d.draw(page);}
            for (Dot d : bullets) {d.update(); d.draw(page);}
            for (Alien loop : aliens) {
                loop.act();
                if (Math.random() > .993) {
                    for (Dot d : loop.fire())
                        lazers.add(d);
                }
                loop.draw();
            }
            
            if (collisionCheck() | fl) {
                flash();
                fl = !fl;
            }
            
            int index1 = 0, index2 = 0, size1 = bullets.size(), size2 = aliens.size();
            for (Alien al : aliens) {
                index2 = 0;
                while (index2 < size1) {
                    if (al.collide(bullets.get(index2))) {
                        bullets.remove(index2);
                        size1--;
                        score += 10;
                    }
                    index2++;
                }
            }

            index1 = 0;  size1 = aliens.size();
            while (index1 < size1) {
                if (aliens.get(index1).health() <= 0) {
                    aliens.remove(index1);
                    index1--;
                    size1--;
                    score += 25;
                }
                index1++;
            }
            
            for (Dot d : lazers) {
                if (d.getX() < 0 | d.getX() > 512 | d.getY() < 0 | d.getY() > 496) {
                    d.deactivate();
                }
            }
            index1 = 0;  size1 = lazers.size();
            while (index1 < size1) {
                if (!lazers.get(index1).isActive()) {
                    lazers.remove(index1);
                    size1--;
                    index1--;
                }
                index1++;
            }
            
            for (Dot d : bullets) {
                if (d.getX() < 0 | d.getX() > 512 | d.getY() < 0 | d.getY() > 512) {
                    d.deactivate();
                }
            }
            index1 = 0;  size1 = bullets.size();
            while (index1 < size1) {
                if (!bullets.get(index1).isActive()) {
                    bullets.remove(index1);
                    size1--;
                    index1--;
                }
                index1++;
            }
            
            for (int i = 0; i < lives; i++) {
                page.drawImage(you.getImage(), i*20, 496, 16, 16, null);
            }
            page.setColor(Color.white);
            page.drawString("" + you.health(), 420, 509);
            page.drawString("" + score, 450, 509);
            you.draw();
        }
        else {
            lose();
        }
    }
    
    public boolean collisionCheck() { //true if player is hit
        for (Alien al : aliens) {
            if (you.collide(al)) {
                al.collide(you);
                return true;
            }
        }
        boolean tf = false;
        int index = 0, size = lazers.size();
        while (index < size) {
            if (you.collide(lazers.get(index))) {
                lazers.remove(index);
                size--;
                index--;
                tf = true;
            }
            index++;
        }
        return tf;
    }
    
    protected void flash() {
        page.setColor(Color.red);
        page.fillRect(0, 0, 512, 512);
    }
    protected void die() {
        lives--;
        lazers.clear();
        bullets.clear();
        Image i = you.getImage();
        int w = you.getWeapon();
        you = new Ship(page);
        you.setImage(i);
        you.setWeapon(w);
        try {
            Thread.sleep(800);
        } catch (InterruptedException ex) {}
    }
    protected void explode() {
        
    }
    
    protected void win() {
        won = true;
        lazers.clear();
        if (meh) 
            warpField();
        else 
            colorField();
        page.setColor(Color.black);
        page.fillOval(256-32, 256-32, 64, 64);
        page.setColor(Color.white);
        page.drawString("YOU WIN!", 228, 262);
    }
    protected void lose() {
        page.setColor(Color.darkGray);
        page.fillRect(0, 0, 512, 512);
        page.setColor(Color.WHITE);
        page.drawString("YOU LOSE", 234, 262);
    }
    
    public void resetStars() {
        stars = new ArrayList<Star>();
    }
    public void warpField() {
        page.setColor(Color.black);
        page.fillRect(0, 0, 512, 512);
        
        if (rand.nextBoolean()) {
            stars.add(new Star(page));
        }
        for (Star s : stars) {
            s.draw();
        }
        while(stars.size() > 200) {
            stars.remove(0);
        }
    }
    public void colorField() {
        page.setColor(Color.black);
        page.fillRect(0, 0, 512, 512);
        
        if (rand.nextBoolean()) {
            stars.add(new Star(page,true));
        }
        for (Star s : stars)
        {
            s.draw();
        }
        while(stars.size() > 200) 
        {
            stars.remove(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getX() > 224 & e.getX() < 288 & e.getY() > 224 & e.getY() < 288) {
            if (won) {
                meh = !meh;
            }
            if (you.health() <= 0) {
                this.reset();
            }
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (you.getWeapon() == Weapon.WEAPON_4 | you.getWeapon() == Weapon.WEAPON_10) {
                fire = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("lol, this doesn't do anything yet");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fire = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}