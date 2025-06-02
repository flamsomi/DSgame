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

public class LearningGraphics extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    private int rX,rY,rW,rH;
    private int cX,cY,diam,cVx,cVy;
    private Bubble bub,bub2;
    private ArrayList<Bubble> bubbles;
    //Default Constructor
    public LearningGraphics()
    {
        //initializing instance variables
        WIDTH = 1000;
        HEIGHT = 500;
        rX = 300;
        rY = 300;
        rW = 50;
        rH = 100;
        cX = 500;
        cY = 300;
        diam = 70;
        cVy = 5;
        cVx = 5;
        bub = new Bubble(100,100,50,Color.RED);
        bub2 = new Bubble(300,300,50,Color.RED);
        bubbles = new ArrayList<Bubble>();
        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Serve it up"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui cant be resized
        gui.getContentPane().add(this); //Adding this class to the gui
        /*If after you finish everything, you can declare your buttons or other things
        *at this spot. AFTER gui.getContentPane().add(this) and BEFORE gui.pack();
        */
        gui.pack(); //Packs everything together
        gui.setLocationRelativeTo(null); //Makes so the gui opens in the center of screen
        gui.setVisible(true); //Makes the gui visible
        gui.addKeyListener(this);//stating that this object will listen to the keyboard
        gui.addMouseListener(this); //stating that this object will listen to the Mouse
        gui.addMouseMotionListener(this); //stating that this object will acknowledge when the Mouse moves
    }
    //This method will acknowledge user input
    public void keyPressed(KeyEvent e)
    {
        //getting the key pressed
        int key = e.getKeyCode();
        System.out.println(key);
        //moving the rectangle
        if(key == 38)
        {
            rY-=10;
        }
        else if(key == 40)
        {
            rY+=10;
        }
        else if(key == 39)
        {
            rX+=10;
        }
        else if(key == 37)
        {
            rX-=10;
        }
    }
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        //Drawing a Blue Rectangle to be the background
        g.setColor(Color.BLUE);
        g.fillRect(0,0,WIDTH,HEIGHT);
        //Drawing Hello World!! at the center of the GUI
        Font f = new Font("Arial", Font.BOLD, 50);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("Hello World!!", WIDTH/2, HEIGHT/2);
        //Drawing the user-controlled rectangle
        g.setColor(Color.RED);
        g.fillRect(rX,rY,rW,rH);
        g.draw3DRect(rX, rY, rW, rH, true);
        //Drawing the autonomous circle
        g.setColor(Color.YELLOW);
        g.fillOval(cX,cY,diam,diam);
        
        // Drawing bubble
        bub.drawSelf(g);
        bub2.drawSelf(g);
        
        // ArrayList of Bubbles
        for(int i = 0; i < bubbles.size(); i++)
        {
            bubbles.get(i).drawSelf(g);
        }
    }
    public void loop()
    {
        //making the autonomous circle move
        cX += cVx;
        cY += cVy;
        //handling when the circle collides with the edges
        int nextX, nextY;
        nextX = cX + cVx;
        nextY = cY + cVy;
        //handling the collision of the circle with the rectangle
        if(nextY + diam > HEIGHT)
        {
            cVy*=(-1);  
        }
        else if(nextX + diam > WIDTH)
        {
            cVx*=(-1);
        }
        else if(nextY < 0)
        {
            cVy*=(-1);
        }
        else if(nextX < 0)
        {
            cVx*=(-1);
        }
        if(detectCollision() == true)
        {
            cVx*=-1;
            cVy*=-1;
        }
        // Bub collision
        bub.handleCollision(bub2);
        bub2.handleCollision(bub);
        // Moving bubble
        bub.act(WIDTH,HEIGHT);
        bub2.act(WIDTH,HEIGHT);
        
        // Bubble Array List
        for(int i = 0; i < bubbles.size();i++)
        {
            for(int j = i+1; j < bubbles.size();j++)
            {
                bubbles.get(i).handleCollision(bubbles.get(j));
                bubbles.get(j).handleCollision(bubbles.get(i));
            }
        }
        
        for(int i = 0; i < bubbles.size(); i++)
        {
            bubbles.get(i).act(WIDTH, HEIGHT);
        }
        
        //Do not write below this
        repaint();
    }
    public double distance(int x1, int y1, int x2, int y2)
    {
        double x = Math.pow(x2 - x1,2);
        double y = Math.pow(y2-y1, 2);
        double combine = x + y;
        double total = Math.sqrt(combine);
        return total;
    }
    public boolean detectCollision()
    {
        boolean output = false;
        int radius = diam/2;
        int centerX, centerY;
        int nextX = cX + cVx;
        int nextY = cX + cVy;
        centerX = (2*nextX + diam)/2;
        centerY = (2*nextY + diam)/2;
        for(int i = rX; i < rX + rW; i++)
        {
            for(int j = rY; j < rY + rH; j++)
            {
                if(distance(i,j,centerX,centerY) < radius)
                    output = true;
            }
        }
        return output;
    }
    //These methods are required by the compiler.
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {
        
    }
    public void keyReleased(KeyEvent e)
    {
        
    }
    public void mousePressed(MouseEvent e)
    {
        
    }
    public void mouseReleased(MouseEvent e)
    {
        
    }
    public void mouseClicked(MouseEvent e)
    {
        bubbles.add(new Bubble(e.getX(), e.getY(), 75, Color.MAGENTA));
    }
    public void mouseEntered(MouseEvent e)
    {
        
    }
    public void mouseExited(MouseEvent e)
    {
        
    }
    public void mouseMoved(MouseEvent e)
    {
        
    }
    public void mouseDragged(MouseEvent e)
    {
        
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
            e.printStackTrace();
                }
            }
        }
    };
    gameThread.start();
    }
    public static void main(String[] args)
    {
        LearningGraphics g = new LearningGraphics();
        g.start(60);
    }
}