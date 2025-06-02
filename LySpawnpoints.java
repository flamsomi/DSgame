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
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class LySpawnpoints
{
    private int x1,x2,x3,x4,x5,x6;
    private int y1,y2,y3,y4,y5,y6;
    private int w,h;

    public LySpawnpoints()
    {
        x1 = 300;
        x2 = 500;
        x3 = 700;
        x4 = 0;
        x5 = 0;
        x6 = 0;
        y1 = 0;
        y2 = 0;
        y3 = 0;
        y4 = 500;
        y5 = 1000;
        y6 = 1500;
        w = 100;
        h = 100;
    }
    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.red);
        g.fillRect(x1, y1, w, h);
    }

    // Spawning the customers randomly in 1 of 6 spots
    public void spawn()
    {
        int spawn = (int)(Math.random()*6+1);
        
            
    }
}