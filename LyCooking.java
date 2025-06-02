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

public class LyCooking extends LyItem
{
     private int timer, amount, toBeCooked;
     private boolean isCooked;
     private int x,y,w,h;

     private long start;

     public LyCooking()
     {
          timer = 3000;
          amount = 100000;
          toBeCooked = 0;
          isCooked = false;
          x = 1500;
          y = 500;
          w = 100;
          h = 100;
          start = 0;
     }

     public int getAmount()
     {
          return amount;
     }
     
     public void used()
     {
          amount--;
     }

     public void addBack()
     {
          amount++;
     }
     public void drawSelf(Graphics g)    
     {
         
          g.setColor(Color.pink);
          g.fillOval(x, y, w, h);
     } 

     public void cook(){
          start = System.currentTimeMillis(); 
     }
     public boolean isCooked()
     {

          long now = System.currentTimeMillis();
          int seconds = (int)(now - start)/1000;

          // timer-=1000;
          if(timer > 3)
          {
               isCooked = true;
          }
          return isCooked;
     }
     public void loop()
     {
          // cook();
          // isCooked();

          
     }
}