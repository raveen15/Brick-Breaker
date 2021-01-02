/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraphicstemplate;
import java.awt.Color;
import java.awt.Graphics;
/**
 *
 * @author 023627
 */
public class Ship {
    int x=0;
    int y=0;
    Graphics g;
    
    Ship(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
   public void move(int x, int y) {
       this.x = x;
       this.y = y;
   }
   
    public Graphics setColor(Graphics g) {
        Color shipColor = new Color(0,0,255);
	g.setColor(shipColor);
    
        return g;
    }
   public  int returnx(){
    return this.x;
   }
   
   public int returny(){
       return this.y;
   }
   
}
