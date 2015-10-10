import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import static java.lang.System.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.imageio.*;

public class Char{
	BufferedImage character = null;
    int direction;
    int x = 250;
    int y = 710;
    boolean dank = false;
    int xSize = 166/2;
    int ySize = 488/2;

	public Char(){
		readImg("imgs/char.png");
	}
	public void readImg(String in)
	{
		try {
    		character = ImageIO.read(new File(in));
		} catch (IOException e) {

		}

 	}

 	public void resetVar(){
 		normState();
		changeDirection(0);
        x = 250;
        y = 710;
        xSize = 166/2;
        ySize = 488/2;
 	}
 	public void dankMemes(){
 		if(dank == false){
 			readImg("imgs/Mountain-Dew.png");
 			dank = true;
 		}else{
 			dank = false;
 		}
 	}
 	public void changeDirection(int x){
        direction = x;
 		if(x == 0){
 			if(dank == false){
 				readImg("imgs/char.png");
 			}else{
 				readImg("imgs/Mountain-Dew.png");
 			}
 		}else{
 			if(dank == false){
 				readImg("imgs/charFlip.png");
 			}else{
 				readImg("imgs/Mountain-Dew.png");
 			}
 		}
 	}
    public void stepOut(){
        if(direction == 0){
            x += 100;
            y -= 50;
        }else{
            x -= 100;
            y -= 50;
        }
    }
    public void jumpState(){
    	y -= 25;
    }
    public void normState(){
    	y = 710;
    }
    public void moveDown(){

        y+=25;
    }
	public void death(){
		ySize = 480/2;
		if(direction == 0){
			readImg("imgs/death.png");
        }else{
			readImg("imgs/deathFip.png");
        }
	}
 	public void drawChar(Graphics g)
	{
		g.drawImage(character,x, y, xSize, ySize,null);
		//System.out.println(yLoc);
	}
}
