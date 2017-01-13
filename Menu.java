package Galaga.Fields;

import Galaga.Characters.Dot;
import Galaga.Characters.Ship;
import Galaga.Characters.Weapon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Cursor;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.MemoryImageSource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Brian
 */
public class Menu implements MouseListener, KeyListener {

    Graphics page;
    public Field field;
    int level = 1;
    boolean pause, menu, started;
    private int prev;
    public int score;
    public Cursor transparentCursor;
    private Point mouseLoc, Screen;
    ArrayList<Image> ship, alien;

    public Menu(Graphics page) {
        this.page = page;
        field = new Field(page);
        pause = false;
        started = false;
        score = 0;
        createCursor();
        ship = new ArrayList<Image>();
        alien = new ArrayList<Image>();
    }
    
    public void setImages(ArrayList<Image> im) {
        for (int i = 0; i < 6; i++) {
            ship.add(im.get(i));
        }
        for (int i = 6; i < 10; i++) {
            alien.add(im.get(i));
        }
    }
    
    public void draw() {
        if (started) {
            page.setColor(Color.black);
            page.fillRect(0, 0, 512, 512);
            page.setColor(Color.gray);
            page.fillRect(0, 496, 512, 16);
            if (field.won) {
                if (level < 5) {
                    menu = true;
                }
                nextLevel();
            }
            if (!pause & !menu) {
                field.draw();
            }
            if (pause) {
                field.colorField();
                page.drawString("Paused", 2, 14);
            }
            if (menu) {
                upgrade();
            }
        } else {
            omgButton();
            level1();
        }
    }

    public void nextLevel() {
        score += field.score;
        level++;
        switch (level) {
            case 1:
                level1();
                break;
            case 2:
                level2();
                break;
            case 3:
                level3();
                break;
            case 4:
                level4();
                break;
            case 5:
                level5();
                break;
            case 6:
                bossLevel();
                break;
            case 7:
                level--;
                score -= field.score;
                field.draw();
                break;
        }
    }
    public void level1() {
        Ship s = field.getShip();
//        Image i = field.img;
        field = new Field1(page);
        field.setShip(s);
//        if (field.collisionCheck()) {
//            s.setLocation(256, 448);
//            field.reset();
//            field.setShip(s);
//        }
        field.alienImage(alien);
    }
    public void level2() {
        Ship s = field.getShip();
//        Image i = field.img;
        field = new Field2(page);
        field.setShip(s);
//        if (field.collisionCheck()) {
//            s.setLocation(256, 448);
//            field.reset();
//            field.setShip(s);
//        }
        field.alienImage(alien);
    }
    public void level3() {
        Ship s = field.getShip();
//        Image i = field.img;
        field = new Field3(page);
        field.setShip(s);
//        if (field.collisionCheck()) {
//            s.setLocation(256, 448);
//            field.reset();
//            field.setShip(s);
//        }
        field.alienImage(alien);
    }
    public void level4() {
        Ship s = field.getShip();
//        Image i = field.img;
        field = new Field4(page);
        field.setShip(s);
        field.alienImage(alien);
//        if (field.collisionCheck()) {
//            s.setLocation(256, 448);
//            field.reset();
//            field.setShip(s);
//        }
    }
    public void level5() {
        Ship s = field.getShip();
//        Image i = field.img;
        field = new Field5(page);
        field.setShip(s);
        field.alienImage(alien);
//        if (field.collisionCheck()) {
//            s.setLocation(256, 448);
//            field.reset();
//            field.setShip(s);
//        }
    }
    public void bossLevel() {
        Ship s = field.getShip();
//        Image i = field.img;
        field = new FieldBoss(page);
        field.setShip(s);
        if (field.collisionCheck()) {
            s.setLocation(256, 448);
            field.reset();
            field.setShip(s);
        }
        field.alienImage(alien);
    }
    
    public void omgButton() {
        page.setColor(Color.black);
        page.fillRect(0, 0, 512, 512);
        page.setColor(Color.GREEN);
        page.fill3DRect(192, 224, 128, 64, true);
        page.setColor(Color.black);
        page.drawString("Start", 246, 260);
        drawMouse();
    }
//========================================================================

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_W:
                field.you.forward(true);
                break;
            case KeyEvent.VK_S:
                field.you.backward(true);
                break;
            case KeyEvent.VK_A:
                field.you.left(true);
                break;
            case KeyEvent.VK_D:
                field.you.right(true);
                break;
            case KeyEvent.VK_UP:
                field.you.forward(true);
                break;
            case KeyEvent.VK_DOWN:
                field.you.backward(true);
                break;
            case KeyEvent.VK_LEFT:
                field.you.left(true);
                break;
            case KeyEvent.VK_RIGHT:
                field.you.right(true);
                break;
            case KeyEvent.VK_SPACE:
                field.fire = true;
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode ();
        if (key == prev) {
            prev = 0;
        }
        switch (key) {
            case KeyEvent.VK_UP:
                field.you.forward (false);
                break;
            case KeyEvent.VK_DOWN:
                field.you.backward (false);
                break;
            case KeyEvent.VK_LEFT:
                field.you.left (false);
                break;
            case KeyEvent.VK_RIGHT:
                field.you.right (false);
                break;
            case KeyEvent.VK_ESCAPE:
                pause = !pause;
                field.resetStars ();
                break;
            case KeyEvent.VK_SPACE:
                field.fire = false;
                for (Dot d : field.you.fire ()) {
                    field.moreBullets (d);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (field.won) {
                    started = false;
                    level = 0;
                    Image i = field.you.getImage();
                    field.setShip(new Ship(page));
                    field.shipImage(i);
                    field.alienImage(field.img);
                    nextLevel();
                }
                break;
            case KeyEvent.VK_X:
                field.shipImage(ship.get(5));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!started) {
            if (e.getX () >= 192 & e.getX () <= 320) {
                if (e.getY () >= 224 & e.getY () <= 288) {
                    try {Thread.sleep(1000);}
                    catch (InterruptedException ex) {}
                    started = true;
                }
            }
        } else {
            if (menu) {
                if (e.getX() >= 25 & e.getX() <= 465 & e.getY() >= 75 & e.getY() <= 326) {
                    UGMENUOnClick(e.getPoint());
                }
                if (e.getX() >= 102 & e.getX() <= 409) {
                    if (e.getY() >= 420 & e.getY() <= 440) {
                        try {Thread.sleep(400);}
                        catch (InterruptedException ex) {}
                        menu = false;
                    }
                    if (e.getY() >= 450 & e.getY() <= 470) {
                        System.out.println("You got " + (int) ((score)) + " fun-points!");
                        System.exit(1);
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if (!started) {
//            if (e.getX() > 192 & e.getX() < 320) {
//                if (e.getY() > 224 & e.getY() < 288) {
//                    started = true;
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
////                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
//        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void UGMENUOnClick(Point p) {
        Point tempoint = new Point (0, 0);
        for (int b = 1; b <= 5; b++) {//b*
            for (int a = 1; a <= 3; a++) {//a*78+152
                if (p.y >= (((a - 1) * 78) + 152) & p.x >= ((10 * (b - 1) * 8) - 30) & p.y <= (((a - 1) * 78) + 170) & p.x >= ((10 * (b - 1) * 8) + 45)) {
                    tempoint.setLocation(b, a);
                    //showStatus ("(" + p.x + "," + p.y + ")     (" + a + "," + b + ")");
                }
            }
        }
        if (tempoint.x > 0 & tempoint.y > 0) {
            //-------------------------------------------------------------------------------------------------------
            // weapon level
            //-------------------------------------------------------------------------------------------------------
            if (tempoint.y == 1) {
                if (score - ((int) (100 * Math.pow(tempoint.x, 2))) >= 0) {
                    score -= ((int) (100 * Math.pow(tempoint.x, 2)));
                    if (tempoint.x == 5) {
                        field.you.setWeapon(Weapon.WEAPON_GOD);
                        field.you.setImage(ship.get(4));
                    } else {
                        field.you.setWeapon(tempoint.x);
                        field.you.setImage(ship.get(tempoint.x - 1));
                    }
                }
            }
            //-------------------------------------------------------------------------------------------------------
            // number of lives
            //-------------------------------------------------------------------------------------------------------
            if (tempoint.y == 2) {
                if (score - ((int) (100 * Math.pow (tempoint.x, 2))) >= 0) {
                    score -= ((int) (100 * Math.pow (tempoint.x, 2)));
                    field.lives = tempoint.x + 1;
                }
            }
            //-------------------------------------------------------------------------------------------------------
            // number of hit points
            //-------------------------------------------------------------------------------------------------------
            if (tempoint.y == 3) {
                if (score - ((int) (100 * Math.pow(tempoint.x, 2))) >= 0) {
                    score -= ((int) (100 * Math.pow(tempoint.x, 2)));
                    field.you.setHealth(tempoint.x + 2);
                }
            }
        }
    }

    private void upgrade() {
        page.setColor (Color.DARK_GRAY.brighter());
        page.fillRect (0, 0, 512, 512);
        page.setColor (Color.BLUE.brighter ());
        page.fillRect (0, 0, 512, 20);
        page.setColor (Color.WHITE);
        page.setFont (Font.decode ("Lucida Sans-PLAIN-12"));
        page.drawString ("CURRENT POINTS: " + score, 3, 16);
        page.setColor (Color.lightGray);
        page.fillRect (25, 75, 465, 310);
        page.setColor (Color.BLACK);
        page.setFont (Font.decode ("Lucida Sans-BOLD-25"));
        page.drawString ("UPGRADE MENU", 145, 55);
        page.setColor (Color.BLUE);
        page.setFont (Font.decode ("Times New Roman-ITALIC-12"));
        page.drawString ("CURRENT WEAPON LEVEL: " + field.you.getWeapon (), 50, 112);
        page.drawString ("CURRENT NUMBER OF LIVES PER LEVEL: " + (field.lives + 1), 50, 190);
        page.drawString ("CURRENT AMOUNT OF HEALTH PER LIFE: " + field.you.health (), 50, 268);

        page.setColor (Color.BLACK);
        page.setFont (Font.decode ("TimesNewRoman-PLAIN-10"));
        for (int a = 1; a <= 5; a++) {
            page.drawString ("LEVEL " + a, (10 * a * 8) - 22, 130);
            page.drawString ("COST: " + (int) (100 * Math.pow (a, 2)), (10 * a * 8) - 22, 145);
            page.setColor (Color.WHITE.darker ());

            page.fillRoundRect ((10 * a * 8) - 30, 152, 75, 18, 18, 18);
            page.setColor (Color.CYAN.brighter ().brighter ());
            page.fillRoundRect ((10 * a * 8) - 28, 154, 70, 14, 14, 14);
            page.setColor (Color.BLACK);
            page.drawString ("UPGRADE", (10 * a * 8) - 20, 165);
        }
        for (int a = 1; a <= 5; a++) {
            page.drawString ((2 + a) + " LIVES ", (10 * a * 8) - 22, 208);
            page.drawString ("COST: " + (int) (100 * Math.pow (a, 2)), (10 * a * 8) - 22, 223);
            page.setColor (Color.WHITE.darker ());

            page.fillRoundRect ((10 * a * 8) - 30, 230, 75, 18, 18, 18);
            page.setColor (Color.CYAN.brighter ().brighter ());
            page.fillRoundRect ((10 * a * 8) - 28, 232, 70, 14, 14, 14);
            page.setColor (Color.BLACK);
            page.drawString ("UPGRADE", (10 * a * 8) - 20, 243);
        }
        for (int a = 1; a <= 5; a++) {
            page.drawString ((2 + a) + " HIT POINTS", (10 * a * 8) - 22, 286);
            page.drawString ("COST: " + (int) (100 * Math.pow (a, 2)), (10 * a * 8) - 22, 301);
            page.setColor (Color.WHITE.darker ());

            page.fillRoundRect ((10 * a * 8) - 30, 308, 75, 18, 18, 18);
            page.setColor (Color.CYAN.brighter ().brighter ());
            page.fillRoundRect ((10 * a * 8) - 28, 310, 70, 14, 14, 14);
            page.setColor (Color.BLACK);
            page.drawString ("UPGRADE", (10 * a * 8) - 20, 321);
        }

        page.setFont(Font.decode("Times New Roman-ITALIC-12"));
        page.setColor(Color.BLACK);
        page.fillRoundRect(102, 420, 307, 20, 20, 20);
        page.setColor(Color.WHITE);
        page.fillRoundRect(104, 422, 303, 16, 16, 16);
        page.setColor(Color.BLACK);
        page.drawString("CONTINUE", 210, 435);

        page.setColor(Color.WHITE);
        page.fillRoundRect(102, 450, 307, 20, 20, 20);
        page.setColor(Color.RED);
        page.fillRoundRect(104, 452, 303, 16, 16, 16);
        page.setColor(Color.WHITE);
        page.drawString("QUIT", 210, 464);

        page.setFont(Font.decode("Times New Roman-PLAIN-12"));
        drawMouse();
    }

    private void drawMouse() {
        if (new Point(MouseInfo.getPointerInfo().getLocation().x - Screen.x, MouseInfo.getPointerInfo().getLocation().y - Screen.y) != null) {
            mouseLoc = new Point(MouseInfo.getPointerInfo().getLocation().x - Screen.x, MouseInfo.getPointerInfo().getLocation().y - Screen.y);
        }
        if (mouseLoc.x < 512 && mouseLoc.x > 0 && mouseLoc.y /*- Screen.y*/ > 0 && mouseLoc.y /*- Screen.y*/ < 512) {
            page.setColor(Color.orange);
            page.drawLine(mouseLoc.x - 7, mouseLoc.y, mouseLoc.x + 7, mouseLoc.y);
            page.drawLine(mouseLoc.x, mouseLoc.y - 7, mouseLoc.x, mouseLoc.y + 7);
            page.drawOval(mouseLoc.x - 4, mouseLoc.y - 4, 8, 8);
        }
    }

    public void setLocationOnScreen(Point p) {
        Screen = p;
    }

    private void createCursor() {
        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit ().createImage (
                new MemoryImageSource (16, 16, pixels, 0, 16));
        transparentCursor =
                Toolkit.getDefaultToolkit ().createCustomCursor (image, new Point (0, 0), "invisibleCursor");
    }
}
