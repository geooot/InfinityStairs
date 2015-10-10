import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import static java.lang.System.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.imageio.*;

public class Step extends JPanel
{
	//Class variables go here
	private int xLoc = 550, wide =100, high =50;
	private int yLoc;
    private int number;
	private Color onColor, displayColor;
	BufferedImage img = null;
	boolean devMode = false;





//Secondary Constructor so you can draw lights of a different color at specified locations
public Step(int x, int num)
{
	readImage("imgs/step.png");
	xLoc = x;
    number = num;

    yLoc = num*50;

}

public void readImage(String in)
{
	try {
    		img = ImageIO.read(new File(in));
		} catch (IOException e) {

		}

 }



//mutators
public void setX(int x)
{
	xLoc = x;
}

public void setY(int y)
{
	yLoc = y;
}


public void moveDown()
{
	yLoc = yLoc + 25;

}
public void devMode(){
	if(devMode == false){
		devMode = true;
	}else{
		devMode = false;
	}
}


//gettor or accessor methods - think about what information the Light Panel needs to know!
public int getWidth()
{
	return wide;
}

public int getHeight()
{
	return high;
}

public int getX()
{
	return xLoc;
}
public int getY()
{
	return yLoc;
}

//a helper method called from the LightPanel class that takes the Graphics g argument
//and draws a light at the current class attributes
public void drawStep(Graphics g)
{
	g.setColor(Color.BLACK );
	if(devMode == true){
    	g.drawString(String.valueOf(number), xLoc, yLoc);
	}
	//g.fillRect(xLoc+5, yLoc+5, wide, high);
	g.drawImage(img,xLoc, yLoc, wide, high,null);

	//System.out.println(yLoc);
}

}

