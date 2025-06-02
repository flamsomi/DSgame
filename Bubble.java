import java.awt.Graphics;
import java.awt.Color;
public class Bubble
{
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int diam;
    private Color col;
    public Bubble(int xCoor, int yCoor, int d, Color c)
    {
        x = xCoor;
        y = yCoor;
        diam = d;
        col = c;
        vx = 1;
        vy = 1;
    }
    public String toString()
    {
        String output = ("X coordinate is: " + x + "\nY coordinate is: " + y + "\nThe diameter is: " + diam);
        return output;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getDiam()
    {
        return diam;
    }
    private double distance(int x1, int y1, int x2, int y2)
    {
        double dx = Math.pow(x2-x1,2);
        double dy = Math.pow(y2-y1,2);
        double combine = dx + dy;
        double total = Math.sqrt(combine);
        return total;
    }
    public int getCenterX()
    {
        return x + diam/2;
    }

    public int getCenterY()
    {
        return y + diam/2;
    }
    public void act(int w, int h)
    {
        //get the next x and y coordinates
        int nextX,nextY;
        nextX = x + vx;
        nextY = y + vy;
        //if-statements to handle the Bubble bouncing off of the 4 walls
        if(nextX+diam > w)
        {
            vx *= -1;
        }
        else if(nextY+diam > h)
        {
            vy *= -1;
        }
        else if(nextX < 0)
        {
            vx *= -1;
        }
        else if(nextY < 0)
        {
            vy *= -1;
        }
        //updating x and y
        x += vx;
        y += vy;
    }
    public void drawSelf(Graphics g)
    {
        g.setColor(col);
        g.fillOval(x,y,diam,diam);
    }
    public void handleCollision(Bubble anotherBubble)
    {
        //Getting the center of this Bubble and anotherBubble
        int bx = this.getCenterX();
        int by = this.getCenterY();
        int abx = anotherBubble.getCenterX();
        int aby = anotherBubble.getCenterY();
        //getting the radius of this Bubble and anotherBubble
        int abr = anotherBubble.getDiam()/2;
        int br = this.getDiam()/2;
        //checking if this Bubble Collided with anotherBubble
        double d = distance(bx,by,abx,aby);
        if(d <= abr + br)
        {
            //calculating the velocities of this Bubble after colliding with anotherBubble
            vx = bx - abx;
            vy = by - aby;
            //Slowing down the velocities. otherwise they go crazy
            int maxSpeed = 5;
            if(vx <= -maxSpeed)
            vx = -maxSpeed;
            else if(vx >= maxSpeed)
            vx = maxSpeed;
            if(vy <= -maxSpeed)
            vy = -maxSpeed;
            else if(vy >= maxSpeed)
            vy = maxSpeed;
        }
    }
}