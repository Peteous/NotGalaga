/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Galaga;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Brian
 */
public class Star {
    Random rand = new Random();
    public double x = rand.nextInt(64) + 224, y = rand.nextInt(64) + 224;
    public double size = 1, size1, d, dd;
    private double x1 = x, y1 = y;
    public double theta;
    Graphics graph;
    Color col;
    public Star(Graphics page, boolean meh) {
        graph = page;
        size = rand.nextInt(7) + Math.random();
        d = Math.sqrt((x - 256)*(x - 256) + (y - 256)*(y - 256));
        if (x != 256) 
            if (x-256 > 0) {
                theta = Math.atan((y-256)/(x-256));
            }
            else {
                theta = Math.PI+Math.atan((y-256)/(x-256));
            }
        else {
            if (y > 256) 
                theta = Math.PI/2;
            else
                theta = -Math.PI/2;
        }
        
        col = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }
    public Star(Graphics page) {
        graph = page;
//        size = rand.nextInt(5) + Math.random();
        d = Math.sqrt((x - 256)*(x - 256) + (y - 256)*(y - 256));
        if (x != 256) 
            if (x-256 > 0) {
                theta = Math.atan((y-256)/(x-256));
            }
            else {
                theta = Math.PI+Math.atan((y-256)/(x-256));
            }
        else {
            if (y > 256) 
                theta = Math.PI/2;
            else
                theta = -Math.PI/2;
        }
        col = Color.white;
    }
    
    public void draw() {
        graph.setColor(col);
        dd = d;
        size1 = size;
        x1 = x; y1 = y;
        
        x = x + (d/24) * Math.cos(theta);
        y = y + (d/40) * Math.sin(theta);
        d = Math.sqrt((x - 256)*(x - 256) + (y - 256)*(y - 256));
        size = size + (d - dd)/15;
        
        int xx = (int) x;
        int yy = (int) y;
        int X = (int) x1;
        int Y = (int) y1;
        graph.fillOval((int) (xx - (size/2)), (int) (yy - (size/2)), (int) size, (int) size);
        graph.fillOval((int) (X - (size1/2)), (int) (Y - (size1/2)), (int) size1, (int) size1);
    }
}