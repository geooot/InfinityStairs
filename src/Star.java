	import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import static java.lang.System.*;
public class Star extends Light {
	String shapeName;

	public Star (Color c, int x, int y, String name){
   		super(c, x, y);
   		shapeName = name;
	}
	public void drawStep(Graphics g){
		g.setColor(getCurrentC());
		g.fillRect(getX(),getY(),getWidth(),getHeight());
	}


}
