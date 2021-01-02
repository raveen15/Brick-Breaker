/********************************************************************
 Name: Raveenth Maheswaran
 Date: 25/01/2017
 Title: MyAppClass

 Project Description:

 To develop a game similar to brick breaker. The objective
 for this game is to break all the bricks by making the ball bounce
 from the moving platform to the bricks. There is unlimited tries,
 but try your best to get the least amount of deaths.
 *******************************************************************/

package javagraphicstemplate;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;
import sun.audio.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.applet.*;
import java.awt.*;

/**
 *
 * @author 023627
 */
class MyAppClass extends JComponent {
    
    int x = 190; //coordinates for the starting platform
    int y = 320;
    int xc= 0; //coordinates for the ball
    int yc = 0;
    Graphics me = null; 	
   
    Ship myShip; 
    final short CIRCLERADIUS = 20;
   
    int directionx = 1; //moves horizontally
    int directiony = 1; //moves vertically
    
    int width = 60; //width of the blocks
    int height = 30; //height of the blocks
    
    BufferedImage backgroundImage = null; //declares background image
     
    Image imageOne[] = new Image[5]; //Declares first row blocks, with x and y coordinates
    int blockOneX[] = new int[5];
    int blockOneY[] = new int[5];
    boolean blockOne[] = new boolean[5]; //Boolean array for showing and hiding images
    
    Image imageTwo[] = new Image[5]; //Declares second row blocks, with x and y coordinates
    int blockTwoX[] = new int[5];
    int blockTwoY[] = new int[5];
    boolean blockTwo[] = new boolean[5]; //Boolean array for showing and hiding images
    
    Image imageThree[] = new Image[5]; //Declares third row blocks, with x and y coordinates
    int blockThreeX[] = new int[5];
    int blockThreeY[] = new int[5];
    boolean blockThree[] = new boolean[5]; //Boolean array for showing and hiding images
    
    int score = 0; //Counter for score
    int death = 0; //Counter for death
    
    
    /*****************************************************************
     * Name: Raveenth Maheswaran
     * Title: MyKeyListener
     * Date: 23/01/2017
     * Description: This class contains the operations for key inputs
     ****************************************************************/
    public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
                /****************************************************************
                * Name: Raveenth Maheswaran
                * Title: MyKeyListener
                * Date: 23/01/2017
                * Input: KeyEvent e
                * Output: The direction of the platform moving left and right
                ****************************************************************/
		public void keyPressed(KeyEvent e) {
                   if (e.getKeyCode() == 'D') x+=20;
                   if (e.getKeyCode() == 'A') x-=20; 
                }
			
		@Override
                /****************************************************************
                * Name: Raveenth Maheswaran
                * Title: MyKeyListener
                * Date: 23/01/2017
                * Input: KeyEvent e
                * Output: Shows what key is being pressed
                ****************************************************************/
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
    /******************************************************************
     * Name: Raveenth Maheswaran
     * Title: MyMouseListener
     * Date: 23/01/2017
     * Description: This class contains the operations for mouse inputs
     ******************************************************************/
    public class MyMouseListener implements MouseListener {
        
                /****************************************************************
                * Name: Raveenth Maheswaran
                * Title: mouseClicked
                * Date: 23/01/2017
                * Input: MouseEvent mouseEvent
                * Output: Places platform where mouse is being clicked
                ****************************************************************/
        	public void mouseClicked (MouseEvent mouseEvent) {
                    x = mouseEvent.getX();
                    y = mouseEvent.getY();
                } 
                
                public void mouseEntered (MouseEvent mouseEvent) {} 
                public void mousePressed (MouseEvent mouseEvent) {} 
                public void mouseReleased (MouseEvent mouseEvent) {}  
                public void mouseExited (MouseEvent mouseEvent) {}  

	}
    /******************************************************
     * Name: Raveenth Maheswaran
     * Title: MyAppClass
     * Date: 23/01/2017
     * Input: All the offscreen graphics from curBitmap
     * Output: Displaying the entire game
     *****************************************************/
    MyAppClass(){
        // maze 2 dimensional array	
        KeyListener listener = new MyKeyListener(); 
        MouseListener mouselistener = new MyMouseListener();
        addKeyListener(listener);
        addMouseListener(mouselistener);
            
        setFocusable(true);
        myShip = new Ship(200,200);
        xc = this.x+(this.width/2-10); //
        yc = this.y - 22;
        backgroundImage = getImage("Background.jpg"); //Offscreen graphic for background image

        //Offscrren graphics for first row blocks
        for (int b = 0; b < 5; b++){  
                imageOne[b] = getImage("blockOneMain.jpg");
                blockOneX[b] = 90 * b;
                blockOne[b] = true;
        }
        
        //Offscrren graphics for second row blocks
        for (int c = 0; c < 5; c++){
                
                imageTwo[c] = getImage("blockTwoMain.jpg");
                blockTwoX[c] = 90 * c;
                blockTwo[c] = true;
        }
        
        //Offscrren graphics for third row blocks
        for (int d = 0; d < 5; d++){
                imageThree[d] = getImage("blockThreeMain.jpg");
                blockThreeX[d] = 90 * d;
                blockThree[d] = true;
        }
    
    }
    
    /******************************************************
     * Name: Raveenth Maheswaran
     * Title: getImage
     * Date: 23/01/2017
     * Input: String filename
     * Output: Image/graphics
     *****************************************************/
    private BufferedImage getImage(String filename) {
        // This time, you can use an InputStream to load
        try {
            // Grab the InputStream for the image.                    
            InputStream in = getClass().getResourceAsStream(filename);

            // Then read it in.
            return ImageIO.read(in);
        } catch (IOException e) {
            
        System.out.println("The image was not loaded.");
        //System.exit(1);
    }
    return null;
}
    /***************************************************************
     * Name: Raveenth Maheswaran
     * Title: paint
     * Date: 23/01/2017
     * Input: Graphics g
     * Output: All the images and strings in the offscreen per tick
     ***************************************************************/
        public void paint(Graphics g) {
                me = g;
                myShip.move(x,y);
                g = myShip.setColor(g);
                g.drawImage(backgroundImage, 0, 0, this);

                // ship is a rectangle
		g.fillRect(myShip.returnx(),myShip.returny(),width,height);
                Color circlecolour = new Color(255,0,255);
		g.setColor(circlecolour);						
                
                //direction of the ball
                yc += directiony;
                xc += directionx;
                
                // collision object is a circle
                if (yc < 400){
                    g.fillOval(xc,yc,CIRCLERADIUS,CIRCLERADIUS);   
                }
                
                //restarts ball when it goes offscreen
                if(yc > 400){
                    death++;
                    xc = this.x+(this.width/2-10);
                    yc = this.y - 22;    
                }
                
                // create a delay
                try {
                    Thread.sleep(1);
                } 
                catch(InterruptedException ex) {}
                
                // bounce off of the right and left wall
                if (yc == 0) directiony = -directiony;
                if (xc < 0) directionx = -directionx;
                if (xc > this.getWidth()-20) directionx = -directionx;
                 
                //The ball moves to the left when it hits the left side of platform
                if(yc > this.y - 20 && yc < this.y + 10){
                    if(xc > this.x && xc < this.x + (width / 2)){
                        directionx = -directionx;
                        directiony = -directiony;
                    }
                    //The ball moves to the right when it hits the right side of the platform
                    else if (xc > this.x + (width / 2) && xc < this.x + width){
                        
                        directiony = -directiony;
                    }
                }   
                
                //Loop for the arrays
                for(int k = 0; k < 5; k++){
                    
                    //Displays the first row of blocks
                    if (blockOne[k] == true){
                        g.drawImage(imageOne[k], blockOneX[k] + 22, blockOneY[k], this);
                    }
                    
                    //Displays the second row of blocks
                    if (blockTwo[k] == true){                    
                        g.drawImage(imageTwo[k], blockTwoX[k] + 22, blockTwoY[k] + 80, this);
                    }
                    
                    //Display the third row of blocks
                    if (blockThree[k] == true){                   
                        g.drawImage(imageThree[k], blockThreeX[k] + 22, blockThreeY[k] + 160, this);
                    }  
                    
                    //Collision code for the first row of blocks    
                    if (xc > blockOneX[k] && xc < blockOneX[k] + 80 && yc > blockOneY[k] && yc < blockOneY[k] + 44){    
                        directiony = -directiony;
                        blockOne[k] = false;
                        score += 10;
                    }
                    
                    //Collision code for the second row of blocks  
                    if (xc > blockTwoX[k] && xc < blockTwoX[k] + 80 && yc > blockTwoY[k] + 60 && yc < blockTwoY[k] + 122){    
                        directiony = -directiony;
                        blockTwo[k] = false;
                        score += 10;
                    }
                    
                    //Collision code for the third row of blocks  
                    if (xc > blockThreeX[k] && xc < blockThreeX[k] + 80 && yc > blockThreeY[k] + 140 && yc < blockThreeY[k] + 196){    
                        directiony = -directiony;
                        blockThree[k] = false;
                        score += 10;
                    } 
                    
                    //Move blocks once the block is hit
                    if(blockOne[k] == false){
                        blockOneX[k] = 1000;
                        blockOneY[k] = 3000;
                    }
                    
                    if(blockTwo[k] == false){
                        blockTwoX[k] = 1000;
                        blockTwoY[k] = 3000;
                    }
                    
                    if(blockThree[k] == false){
                        blockThreeX[k] = 1000;
                        blockThreeY[k] = 3000;
                    }
                    
                   //Displays message if all blocks are destroyed
                   if(score == 150) g.drawString("Congratulations, you win", 170, 170); 
                   
                   
                   g.drawString("Score: " + Integer.toString(score), 10, 360); //Displays score
                   g.drawString("Death: " + Integer.toString(death), 400, 360); //Displays death counter
                    
                }     
                repaint();
            }
        }