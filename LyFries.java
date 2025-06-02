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

public class LyFries extends LyCooking
{
    private int x,y,w,h;

    public LyFries()
    {
        x = 1000;
        y = 1000;
        w = 100;
        h = 100;
    }

    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.red);
        g.fillRect(x, y, w, h);
    }
}
