import java.util.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.*;
 import javax.swing.Timer;
 import static java.lang.System.*;


public class gameFrame extends JFrame{

	LightPanel theLight;

	public gameFrame()
	{
		theLight = new LightPanel();

		getContentPane().add(theLight);

		//This code is required to so the listener frame allows the listener to pay attention to the panel!
		theLight.setFocusable(true);
		theLight.requestFocusInWindow();

		new Timer(1000/30, theLight).start();

	}

}
