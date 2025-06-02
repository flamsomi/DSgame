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

/**
 * Items should be abled to picked up by the player
 * When it does get picked up, the x and y coords of the item will follow
 * 
 * If the item gets dropped then you want it gone forever (unless youre adding levels and want to recycle it)
 */

public class LyItem
{
    private boolean isOut;
    private int x, y;
    public boolean isPickedUp;
    private int width, height;

    public LyItem()
    {
        isOut = false;
        x = 1100;
        y = 300;
        width = 100;
        height = 100;
    }



    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void dropItem()
    {
        x = -1000;
        y = -1000;
    }

    public void pickedUp()
    {
        
    }

    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.red);
        g.fillRect(x, y, width, height);
        g.fillRect(1100, 800,width,height);
        g.fillRect(1500,500,width,height);
    }

    public void drawBurger(Graphics g)    
    {
        g.setColor(Color.red);
        g.fillOval(100, 920, 15, 15);
    }
    public void loop()
    {
        
    }

    
}