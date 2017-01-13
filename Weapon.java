package Galaga.Characters;

import Galaga.Characters.Dot;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Brian
 */
public class Weapon {

    public static final int WEAPON_1 = 1;
    public static final int WEAPON_2 = 2;
    public static final int WEAPON_3 = 3;
    public static final int WEAPON_4 = 4;
    public static final int WEAPON_9 = 9;
    public static final int WEAPON_10 = 10;
    public static final int WEAPON_A = 42;
    public static final int WEAPON_B = 43;
    public static final int WEAPON_GOD = 73;
    public static final int WEAPON_CREEPER = 3055;// 3055 loks like BOSS
    private int numstir;
    
    public Weapon(int param) {
        numstir = param;
    }
    
    public int serialnum() {
        return numstir;
    }
    
    public ArrayList<Dot> fire(double x, double y, double theta) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        switch (numstir) {
            case (WEAPON_CREEPER):
                dot = wBIG(x, y, theta);
                break;
        }
        return dot;
    }
    
    public ArrayList<Dot> fire(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        switch (numstir) {
            case (WEAPON_1):
                dot = w1(x, y);
                break;
            case (WEAPON_2):
                dot = w2(x, y);
                break;
            case (WEAPON_3):
                dot = w3(x, y);
                break;
            case (WEAPON_4):
                dot = w4(x, y);
                break;
            case (WEAPON_9):
                dot = w9(x, y);
                break;
            case (WEAPON_10):
                dot = w10(x, y);
                break;
            case (WEAPON_GOD):
                dot = wG(x, y);
                break;
            case (WEAPON_A):
                dot = wA(x, y);
                break;
        }
        return dot;
    }
    
    private ArrayList<Dot> w1(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2));
        return dot;
    }
    
    private ArrayList<Dot> w2(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot((int) x + 5, (int) y, -Math.PI / 2));
        dot.add(new Dot((int) x - 5, (int) y, -Math.PI / 2));
        return dot;
    }
    private ArrayList<Dot> w3(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 + .1));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 - .1));
        return dot;
    }
    private ArrayList<Dot> w4(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 + .05));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 + .1));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 + .15));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 - .05));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 - .1));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2 - .15));
        dot.add(new Dot((int) x, (int) y, -Math.PI / 2));
        return dot;
    }
    private ArrayList<Dot> w9(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        for (double i = 0; i < 4*Math.PI; i += .0314159/2) {
            dot.add(new Dot((int) x, (int) y-10,i));
        }
        return dot;
    }
    private ArrayList<Dot> w10(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        for (int i = 0; i < 512; i++) {
            dot.add(new Dot(i, 504, -Math.PI / 2));
//            dot.add(new Dot(i, 0, Math.PI / 2));
//            dot.add(new Dot(0, i, 0));
//            dot.add(new Dot(512, i, -Math.PI));
        }
        return dot;
    }
    
    private ArrayList<Dot> wG(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot((int) x, (int) y, -Math.PI * Math.random()));
        dot.add(new Dot((int) x, (int) y, -Math.PI * Math.random()));
        return dot;
    }
    private ArrayList<Dot> wA(double x, double y) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot ((int) x, (int) y, Math.PI / 2));
        for (Dot d : dot) {
            d.color = Color.red;
        }
        return dot;
    }
    
    private ArrayList<Dot> wBIG(double x, double y, double theta) {
        ArrayList<Dot> dot = new ArrayList<Dot>();
        dot.add(new Dot((int) x, (int) y, theta, 24));
        for (Dot d : dot) {
            d.color = Color.red;
            d.size = 20;
        }
        return dot;
    }

    public String toString() {
        return String.valueOf(numstir);
    }
}
