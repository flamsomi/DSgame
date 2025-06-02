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

public class LyBuns extends LyItem
{
    private int x,y,w,h;
    private boolean isPickedUp;
    private int amount;

    public LyBuns()
    {
        x = 1100;
        y = 800;
        w = 100;
        h = 100;
        isPickedUp = false;
        amount = 100000;
    }

    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.yellow);
        g.fillOval(x, y, w, h);
    }

    public boolean isPickedUp(LyPlayer p)
    {
        if(amount > 1 && !isPickedUp)
        {
            amount--;
            isPickedUp = true;
            System.out.println("You picked up a bun");
            System.out.println("There are " + amount + " buns left");
        }
        else
        {
            isPickedUp = false;
        }
        return isPickedUp;
    }

    public String toString()
    {
        return "Bun";
    }
}
