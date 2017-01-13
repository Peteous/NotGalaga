package Galaga;

/**
 * @author me
 */
import Galaga.Fields.Menu;
import java.applet.Applet;
import javax.swing.JApplet; 
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Lazer_Rocket_Fun extends JApplet implements ActionListener {

    private Thread thre;
    private Image ship1, ship2, ship3, ship4, ship5, shipx, alien1, alien2, alien3, boss;
    private Image offScreen;
    private Graphics JimmyBuffet;
    private Menu menu;
    private Timer tm;
    
    @Override
    public void init() {
        thre = new Thread ();
        
        setSize(512, 512);
        offScreen = createImage(512, 512);
        JimmyBuffet = offScreen.getGraphics ();
        menu = new Menu(JimmyBuffet);
        
        ArrayList<Image> i = new ArrayList<Image>();
        ship1 =     getImage(getDocumentBase(), "Ship.png");    i.add(ship1);
        ship2 =     getImage(getDocumentBase(), "Ship2.png");   i.add(ship2);
        ship3 =     getImage(getDocumentBase(), "Ship3.png");   i.add(ship3);
        ship4 =     getImage(getDocumentBase(), "Ship4.png");   i.add(ship4);
        ship5 =     getImage(getDocumentBase(), "Ship5.png");   i.add(ship5);
        shipx =     getImage(getDocumentBase(), "ShipX.gif");   i.add(shipx);
        alien1 =    getImage(getDocumentBase(), "Alien.png");   i.add(alien1);
        alien2 =    getImage(getDocumentBase(), "Alien2.png");  i.add(alien2);
        alien3 =    getImage(getDocumentBase(), "Alien3.png");  i.add(alien3);
        boss =      getImage(getDocumentBase(), "Boss.png");    i.add(boss);
        menu.field.shipImage(ship1);
        menu.setImages(i);
        
        this.setCursor(menu.transparentCursor);
        
        this.addKeyListener(menu);
        this.addMouseListener(menu);
        setBackground(Color.black);
        
        tm = new Timer(15, this);
        tm.start();
    }
    
    @Override
    public void paint(Graphics page) {
        JimmyBuffet.clearRect(0, 0, 512, 512);
        menu.setLocationOnScreen(this.getLocationOnScreen());
        menu.draw();
        page.drawImage(offScreen, 0, 0, null);
//        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
