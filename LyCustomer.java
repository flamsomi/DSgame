import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Font;

public class LyCustomer
{
    
    private int WIDTH;
    private int HEIGHT;
    private int xC;
    private int yC;
    private int c1,c2,c3,c4,c5,c6;
    
    //Default Constructor
    public LyCustomer()
    {
        //initializing instance variables
        WIDTH = 75;
        HEIGHT = 75;
        xC = 50;
        yC = 900;
        c1 = 0;
        c2 = 0;
        c3 = 0;
        c4 = 0;
        c5 = 0;
        c6 = 0;
    }
    
    // Parameterized constructor
    public LyCustomer(int w, int h, int x, int y)
    {
        WIDTH = w;
        HEIGHT = h;
        xC = x;
        yC = y;
    }
    
    // Getters
    public int getWIDTH() 
    {
        return WIDTH;
    }
    public int getHEIGHT()
    {
        return HEIGHT;
    }
    public int getxC()
    {
        return xC;
    }
    public int getyC()
    {
        return yC;
    }

    // Setters
    public void setxC(int x)
    {
        xC = x;
    }
    public void setyC(int y)
    {
        yC = y;
    }
    
    // Drawing the Customer
    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.red);
        g.fillOval(xC, yC, WIDTH/2, HEIGHT);
    }
}