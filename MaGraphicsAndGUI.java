//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
//import java.awt.Font;
import java.util.ArrayList;

public class MaGraphicsAndGUI extends JComponent implements KeyListener, MouseListener, MouseMotionListener
{    
    private int width, height; //Instance Variables for the GUI's border measurements
    private long currentTime, waveTime;
    private MaPlayer player;
    private ArrayList<MaEnemyProjectileSlash> random;
    
    public MaGraphicsAndGUI(){
        //Setting up the GUI attributes and its default characteristics:
        width = 1360;
        height = 720;
        JFrame gui = new JFrame(); 
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        gui.setTitle("Player Control Demo Testing"); 
        gui.setPreferredSize(new Dimension(width + 5, height + 30));
        gui.setResizable(false);
        gui.getContentPane().add(this);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        gui.addKeyListener(this);
        gui.addMouseListener(this);
        gui.addMouseMotionListener(this);
        
        player = new MaPlayer();
        currentTime = 0;
        waveTime = System.currentTimeMillis();
        random = new ArrayList<>();
    }
    
    public void paintComponent(Graphics g){
        player.drawSelf(g);
        for(MaEnemyProjectileSlash elements: random)
            elements.drawSelf(g);
    }
    
    public void loop(){
        //This was part of the CS Fair submittion during May. This section was to simulate the enemy's projectiles and utilizes the dodge and parry mechanics.
        if(System.currentTimeMillis() - waveTime >= 3000){
            MaEnemyProjectileSlash addition = new MaEnemyProjectileSlash(40,125,10,470,20);
            random.add(addition);
            waveTime = System.currentTimeMillis();
        }
        if(random.size() > 0)
            for(MaEnemyProjectileSlash element : random) {
                if(element.getX() == 0 || element.getX() == width)
                    random.remove(element);
                element.act();
                player.checkCollision(element);
            }
        //This is the player's looping code.
        player.act(width,currentTime);
        repaint();
    }
    
    //Keyboard interactions from the player:
    public void keyPressed(KeyEvent e){
        int keyboard = e.getKeyCode();
        if(keyboard == 87 && !player.isParrying() && !player.isDodging() && !player.isOnJumpCD()) //W Key, Jump
            player.jump();
        if(keyboard == 65 && !player.isParrying() && !player.isMovingLeft() && !player.isDodging()){ //A Key, Move left
            player.movingLeft(true);
            player.movingRight(false);
        }
        if(keyboard == 68 && !player.isParrying() && !player.isMovingRight() && !player.isDodging()){ //D Key, Move right
            player.movingRight(true);
            player.movingLeft(false);
        }
        if(keyboard == 70 && !player.isParrying() && !player.isOnParryCD() && !player.isDodging() && !player.isJumping() && !player.isHurt()){ //F Key, Parry
            player.parrying();
            currentTime = System.currentTimeMillis();
        }
        if(keyboard == 67 && !player.isDodging() && !player.isOnDodgeCD() && !player.isParrying() && !player.isJumping() && (player.isMovingRight() || player.isMovingLeft())){ //C Key, Dodge
            player.dodging();
            currentTime = System.currentTimeMillis();
        }           
    }
    public void keyTyped(KeyEvent e){
    }
    public void keyReleased(KeyEvent e){
        int keyboard = e.getKeyCode();
        //My solution in creating fluid motion for the player was to activate booleans when the key is being pressed and turning it off when the key is released.
        if(keyboard ==  68 && player.isMovingRight())
            player.movingRight(false);
        if(keyboard ==  65 && player.isMovingLeft())
            player.movingLeft(false);
    }
    //These were never implemnted and I wasn't planning to do so.
    public void mousePressed(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
    }
    public void mouseDragged(MouseEvent e){
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

    public static void main(String[] args){
        MaGraphicsAndGUI g = new MaGraphicsAndGUI();
        g.start(60);
    }
}