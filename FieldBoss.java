package Galaga.Fields;

/**
 * @author Brian
 */
import Galaga.Characters.*;
import Galaga.Star;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class FieldBoss extends Field implements MouseListener {

    private int x = 0;

    public FieldBoss(Graphics page) {
        super(page);
        won = false;
        you = new Ship(this.page);
        aliens = new ArrayList<Alien>();
        lives = 2;
        bullets = new ArrayList<Dot>();
        lazers = new ArrayList<Dot>();
        for (int num = 0; num < 3; num++) {
            aliens.add(new Boss(this.page));
        }
        for (int q = 0; q < 1; q++) {
            aliens.get(4 * q + 0).setLocation((0) * 96 + 60, q * 96 + 64);
            aliens.get(4 * q + 1).setLocation((2) * 96 + 60, q * 96 + 64);
            aliens.get(4 * q + 2).setLocation((4) * 96 + 60, q * 96 + 64);
        }
        this.bossTargetTowards(you);
    }
    
    @Override
    public void reset() {
        aliens = new ArrayList<Alien>();
        for (int num = 0; num < 3; num++) {
            aliens.add(new Boss(this.page));
        }
        aliens.get(0).setLocation(0 * 96 + 60, 64);
        aliens.get(1).setLocation(2 * 96 + 60, 64);
        aliens.get(2).setLocation(4 * 96 + 60, 64);
        this.bossTargetTowards(you);
    }
    @Override
    public void setShip(Ship s) {
        you = s;
        this.bossTargetTowards(you);
    }

    @Override
    public void alienImage(ArrayList<Image> im) {
        bossImage(im.get(3));
        img = im;
    }
    public void bossImage(Image image) {
        for (Alien loop : aliens) {
            loop.setImage(image);
        }
    }

    public final void bossTargetTowards(Ship Player) {
        for (Alien loop : aliens) {
            loop.setPublicEnemy(Player);
        }
    }

    @Override
    public void draw() {
        if (you.health() > 0 & lives >= 0) {
            if (aliens.isEmpty()) {
                super.win();
            }
            you.act();
            for (Dot d : lazers) {
                d.update();
                d.draw(page);
            }
            for (Dot d : bullets) {
                d.update();
                d.draw(page);
            }
            
            if (x > 512) {
                x = 0;
            }
            x++;
            for (Alien loop : aliens) {
                loop.act();
                if (x%28 == 0) {
                    for (Dot d : loop.fire()) {
                        lazers.add(d);
                    }
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
                        index2--;
                        size1--;
                        score += 10;
                    }
                    index2++;
                }
            }

            index1 = 0;
            size1 = aliens.size();
            while (index1 < size1) {
                if (aliens.get(index1).health() <= 0) {
                    aliens.remove(index1);
                    index1--;
                    size1--;
                    score += 50;
                }
                index1++;
            }

            for (Dot d : lazers) {
                if (d.getX() < 0 | d.getX() > 512 | d.getY() < 0 | d.getY() > 490) {
                    d.setActive(false);
                }
            }
            index1 = 0;
            size1 = lazers.size();
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
                    d.setActive(false);
                }
            }
            index1 = 0;
            size1 = bullets.size();
            while (index1 < size1) {
                if (!bullets.get(index1).isActive()) {
                    bullets.remove(index1);
                    size1--;
                    index1--;
                }
                index1++;
            }

            for (int i = 0; i < lives; i++) {
                page.drawImage(you.getImage(), i * 20, 496, 16, 16, null);
            }
            
            page.setColor(Color.white);
            page.drawString("" + score, 450, 510);
//            page.drawImage(you.getImage(), you.getX() - 25, you.getY() - 24, null);
            you.draw();
        } else {
            if (lives >= 0) {
                super.die();
                this.bossTargetTowards(you);
            }
            else {
                super.lose();
            }
        }
    }

    
//    private void win(){
//        won = true;
//        if (meh) {
//            warpField();
//        } else {
//            colorField();
//        }
//        page.setColor(Color.black);
//        page.fillOval(256 - 32, 256 - 32, 64, 64);
//        page.setColor(Color.white);
//        page.drawString("YOU WIN!", 228, 262);
//    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (won) {
            if (e.getX() > 224 & e.getX() < 288 & e.getY() > 224 & e.getY() < 288) {
                meh = !meh;
            }
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
}
