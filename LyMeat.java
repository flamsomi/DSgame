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

public class LyMeat extends LyCooking
{
    private int HEIGHT, WIDTH;
    private boolean isPickedUp;
    public LyMeat()
    {
        super();
        HEIGHT = 100;
        WIDTH = 100;
        isPickedUp = false;
    }

    public void drawSelf(Graphics g, int coordX, int coordY)
    {
        g.setColor(Color.cyan);
        g.fillOval(coordX, coordY, WIDTH, HEIGHT);
    }
    
    public boolean isPickedUp(LyPlayer p)
    {
        
        // if (p.getxC() >= 1100 && p.getxC() <= 1200 && p.getyC() >= 300 && p.getyC() <= 400) 
        // {
        //     System.out.println("You picked up the meat");
        //     return true;
        // }
        // else {
        //     System.out.println("Did not pick up meat");
        //     return false;
        // }
        if (getAmount() > 1 && !isPickedUp)
        {
            used();
            isPickedUp = true;
            System.out.println("You picked up the meat");
            System.out.println("there is " + getAmount() + " meat left");
        }
        else 
        {
            isPickedUp = false;
        }
        return isPickedUp;
    }

    public String toString(){
        return "Meat";
    }
}