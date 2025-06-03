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

public class LyProj8 extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{
    //instance variables
    private int WIDTH;
    private int HEIGHT;
    
    private LyPlayer player;
    private LyItem item;
    private boolean leftP;
    private boolean rightP;
    private int screen;
    private boolean gameOver;

    private LyMeat meat;
    private LyBuns buns;
    private LyCooking cooker;
    private LyCustomer customer;
    private LyItem burger;

    private String text, textPoints,inventoryText;
    
    //Default Constructor
    public LyProj8()
    {
        //initializing instance variables
        WIDTH = 2000; 
        HEIGHT = 1000; 
        player = new LyPlayer();
        item = new LyItem();
        screen = 0;
        gameOver = false;
        meat = new LyMeat();
        buns = new LyBuns();
        cooker = new LyCooking();
        text = "";
        customer = new LyCustomer();
        textPoints = "";
        inventoryText = "";
        burger = new LyItem();
        leftP = false;
        rightP = false;

        //Setting up the GUI
        JFrame gui = new JFrame(); //This makes the gui box
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Makes sure program can close
        gui.setTitle("Serve it up"); //This is the title of the game, you can change it
        gui.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Setting the size for gui
        gui.setResizable(false); //Makes it so the gui can be resized
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
        player.keyPressed(e, meat, buns);
        
        if(key == 10 && screen < 3)
        {
            screen++;
        }
    
        //moving the rectangle
        if(key == 39 || key == 68)
        {
            rightP = true;
        }
        else if(key == 37 || key == 65)
        {
            leftP = true;
        }
        
    }
    //All your UI drawing goes in here
    public void paintComponent(Graphics g)
    {
        if(player.getPoints() == 5)
        {
            gameOver = true;
            ImageIcon w = new ImageIcon(LyProj8.class.getResource("Win.PNG"));
            Image win = w.getImage();
            g.drawImage(win,0,0,WIDTH,HEIGHT,null);
        }


        else if(!gameOver)
        {
            if(screen == 0)
            {
                ImageIcon ss = new ImageIcon(LyProj8.class.getResource("OC.png"));
                Image start = ss.getImage(); 
                g.drawImage(start, 0, 0, WIDTH, HEIGHT, null);
            }
            else if(screen == 1)
            {
                ImageIcon k = new ImageIcon(LyProj8.class.getResource("Keys.png"));
                Image keys = k.getImage(); 
                g.drawImage(keys, 0, 0, WIDTH, HEIGHT, null);
            }
            else if(screen == 2)
            {
                ImageIcon i = new ImageIcon(LyProj8.class.getResource("Ingredients.png"));
                Image ingredients = i.getImage(); 
                g.drawImage(ingredients, 0, 0, WIDTH, HEIGHT, null);
            }
            else if(screen == 3)
            {
                //Drawing a background
                g.setColor(Color.WHITE);
                g.fillRect(0,0,WIDTH,HEIGHT);
                
                

                // Drawing customer spawn plates 
                g.setColor(Color.BLACK);
                // g.fillRect(0,300,100,100);
                g.fillRect(0,500,100,100);
                // g.fillRect(0,700,100,100);
                // g.fillRect(500,0,100,100);
                // g.fillRect(1000,0,100,100);
                // g.fillRect(1500,0,100,100);
                
                //Drawing the player
                Graphics2D g2d;
                g2d = (Graphics2D)g;
                ImageIcon lee = new ImageIcon(LyProj8.class.getResource("rhg.png"));
                g2d.drawImage(lee.getImage(), player.getxC(), player.getyC(), player.getWIDTH(), player.getHEIGHT(), null);

                // Customer
                ImageIcon c = new ImageIcon(LyProj8.class.getResource("Customer.png"));
                Image cust = c.getImage(); 
                g.drawImage(cust, 400, 500, 100, 100, null);

                // Drawing a table
                g.setColor(Color.BLACK);
                g.fillRect(500,300,100,500);
                g.fillRect(1500,300,100,600);
                g.fillRect(500,300,1000,100);
                g.fillRect(500,800,1000,100);

                //text
                g.setColor(Color.black);
                g.drawString(text, 50, 50);
                g.drawString(textPoints,100,100);
                g.drawString(inventoryText,500,100);

                item.drawSelf(g);
                meat.drawSelf(g,1100,300);
                buns.drawSelf(g);
                cooker.drawSelf(g);
                player.drawInventory(g);

                ImageIcon p = new ImageIcon(LyProj8.class.getResource("meat.png"));
                Image pat = p.getImage(); 
                g.drawImage(pat, 1100, 300, 100, 100, null);

                ImageIcon b = new ImageIcon(LyProj8.class.getResource("b2.png"));
                Image burger = b.getImage(); 
                g.drawImage(burger, 450, 475, 25, 25, null);

                ImageIcon br = new ImageIcon(LyProj8.class.getResource("buns.png"));
                Image bread = br.getImage(); 
                g.drawImage(bread, 1050, 730, 200, 200, null);
            }
        }

    }
    public void loop()
    {
        
        text = player.getText();
        textPoints = player.getTextPoints();
        inventoryText = player.getInventory();
        if(leftP) player.setxC(-5);
        else if(rightP) player.setxC(5);

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
        return output;
    }
    //These methods are required by the compiler.
    //You might write code in these methods depending on your goal.
    public void keyTyped(KeyEvent e)
    {
        
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == 39)
        {
            rightP = false;
        }
        else if(key == 37)
        {
            leftP = false;
        }
    }
    public void mousePressed(MouseEvent e)
    {
        
    }
            
    public void mouseReleased(MouseEvent e)
    {
        
    }
    public void mouseClicked(MouseEvent e)
    {
        
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
        LyProj8 g = new LyProj8();
        g.start(60);
    }
}