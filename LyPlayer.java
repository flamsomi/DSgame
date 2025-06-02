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


/**
 * 
 */
public class LyPlayer
{
    
    private int WIDTH;
    private int HEIGHT;
    private int xC;
    private int yC;
    private int vX;
    private int vY;
    private ArrayList<LyItem> inventory;
    private int currentPos;
    private LyItem currentItem;
    private boolean hasMeat, hasBuns;
    private String text, textPoints;
    private int iX, iY;
    private int points;
    private boolean gotPoints;
    private String inventoryText;

    //Default Constructor
    public LyPlayer()
    {
        //initializing instance variables
        WIDTH = 75;
        HEIGHT = 75;
        xC = 1412;
        yC = 725;
        inventory = new ArrayList<LyItem>();
        currentPos = 0;
        currentItem = null;
        hasMeat = false;
        hasBuns = false;
        text = ""+currentPos;
        iX = 950;
        iY = 25;
        points = 0;
        textPoints = "Points: "+points;
        gotPoints = false;
        inventoryText = "Your inventory contains: ";
    }
    
    // Parameterized constructor
    public LyPlayer(int w, int h, int x, int y)
    {
        WIDTH = w;
        HEIGHT = h;
        xC = x;
        yC = y;
        inventory = new ArrayList<LyItem>();
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

    public int getPoints()
    {
        return points;
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
    
    // Drawing the player
    public void drawSelf(Graphics g)    
    {
        g.setColor(Color.red);
        g.fill3DRect(xC, yC, WIDTH, HEIGHT, true);
    }

    public void drawMeat(Graphics g)    
    {
        g.setColor(Color.cyan);
        g.fillOval(900, 100, WIDTH*2, HEIGHT*2);
    }

    public void drawInventory(Graphics g)
    {
        //space for inventory
        g.setColor(Color.black);
        g.fillRect(900,100,WIDTH*2,HEIGHT*2);
        g.setColor(Color.blue);
        g.fillRect(1050,100,WIDTH*2,HEIGHT*2);
        g.setColor(Color.GRAY);
        g.fillRect(1200,100,WIDTH*2,HEIGHT*2);
        
        g.setColor(Color.yellow);
        
        //selector icon
        g.fillOval(iX,iY,WIDTH/2,HEIGHT);

        //first ingredient
        // g.fillOval(900, 100, WIDTH*2, HEIGHT*2);

        //second ingredient


        //third ingredient

        // for (Object item : inventory)
        // {

        // }
        
        
        
    }
    
    // Movement
    public void left()
    {
        if (xC > 610) xC -= 20;
    }
    public void right()
    {
        if (xC < 1425) xC += 20;
    }
    public void down()
    {
        if (yC > 400) yC -= 20;
    }
    public void up()
    {
        if (yC < 725)yC += 20;
    }

    public void keyPressed(KeyEvent e, LyMeat meat, LyBuns buns)
    {
        //getting the key pressed
        int key = e.getKeyCode();
        System.out.println(key);
        //moving the rectangle
        if(key == 38 || key == 87)
        {
            this.down();
        }
        else if(key == 40 || key == 83)
        {
            this.up();
        }
        else if(key == 39 || key == 68)
        {
            this.right();
        }
        else if(key == 37 || key == 65)
        {
            this.left();
        }
        else if(key == 32)
        {
            if(inventory.size() >= 3 && xC >= 1100 && xC <= 1200 && yC >= 300 && yC <= 400)
            {
                System.out.println("Your inventory is full");
                text = "Your inventory is full";
            }
            else if(inventory.size() >= 3 && xC >= 1100 && xC <= 1200 && yC >= 700 && yC <= 800)
            {
                System.out.println("Your inventory is full");
                text = "Your inventory is full";
            }
            else
            {
                if (xC >= 1100 && xC <= 1200 && yC >= 300 && yC <= 400) // meat
                {
                    if (meat.isPickedUp(this))
                    {
                        // System.out.println("ghi");
                        hasMeat = true;
                        pickUp(meat);
                        text = "picked up meat";
                        inventoryText += "meat ";
                    }
                }   
                if(xC >= 1100 && xC <= 1200 && yC >= 700 && yC <= 800)
                {
                    if(buns.isPickedUp(this))
                    {
                        pickUp(buns);
                        text = "picked up buns";
                        inventoryText += "buns ";
                    }
                }
                if(xC >= 500 && xC <= 700 && yC >= 400 && yC <= 600)
                {
                    if(inventory.get(0).equals(meat) && inventory.get(1).equals(buns) && inventory.get(2).equals(buns))
                    {
                        gotPoints = true;
                    }
                    else if(inventory.get(0).equals(buns) && inventory.get(1).equals(meat) && inventory.get(2).equals(buns))
                    {
                        gotPoints = true;
                    }
                    else if(inventory.get(0).equals(buns) && inventory.get(1).equals(buns) && inventory.get(2).equals(meat))
                    {
                        gotPoints = true;
                    }   
                    else
                    {
                        gotPoints = false;
                    }
                }
                if(gotPoints == true)
                {
                    points++;
                    for(int i = 0; i < inventory.size(); i++)
                    {
                        inventory.remove(0);
                    }
                    textPoints = "Points: "+points;
                    gotPoints = false;
                }
            }
            
        }
        else if(key == 70)
        {
            this.dropItem();
            // if (currentItem.equals(meat) && hasMeat)
            //     hasMeat = false;
            // else if (currentItem.equals(buns) && hasBuns)
            //     hasBuns = false;
        }
        else if(key == 69) // e
        {
            if(currentPos == 2)
            {
                currentPos = 0;
                iX = 950;
                
                
            }
            else if (currentPos < 2)//0, 1
            {
                currentPos++;
                if (currentPos <= 2 && currentPos > -1) iX += 150;
            }    
            text = ""+currentPos;
        }
        else if(key == 81) // q
        {
            if(currentPos == 0)
            {
                currentPos = 2;
                iX = 1250;
            }
            else
            {
                currentPos--;
                if (currentPos >= 0 && currentPos < 3) iX -= 150;
            }
            text = ""+currentPos;
        }
        
    }

    public boolean getMeat()
    {
        return hasMeat;
    }
    public void pickUp(LyItem item)
    {
        
        // for(int i = 0; i < inventory.size(); i++)
        // {
        //     if(inventory[i] == null)
        //     {
        //         inventory[i] = item;
        //     }
        // }

        // System.out.println(inventory[0]);
        // System.out.println(inventory[1]);
        // System.out.println(inventory[2]);

        if (inventory.size() < 4)
        {
            inventory.add(item);
            System.out.println(inventory);
        }
    }
    public void dropItem()
    {
        inventory.remove(currentPos);
        if(currentPos == 0)
        {
            inventoryText = inventoryText.substring(0, 24+(currentPos+1)) + inventoryText.substring(24+(currentPos+5));
        }
        else if(currentPos == 1)
        {
            inventoryText = inventoryText.substring(0, 24+(currentPos+5)) + inventoryText.substring(24+(currentPos+10));
        }
        else
        {
            inventoryText = inventoryText.substring(0, 24+(currentPos+9));
        }
        
    }


    public String getText()
    {
        return text;
    }

    public String getTextPoints()
    {
        return textPoints;
    }

    public String getInventory()
    {
        return inventoryText;
    }

    // public void loop()
    // {
    //     currentItem = inventory[currentPos];
    //     for(int i = 0; i < inventory.length; i++)
    //     {
    //         if(inventory[i] != null)
    //         {
    //             inventory[i].setX(xC);
    //             inventory[i].setY(yC);
    //         }
    //     }
    // }
}