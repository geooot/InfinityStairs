import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class background{
    int y = 500;
    boolean dank = false;
    BufferedImage bg = null;
    BufferedImage b = null;
    Random r = new Random();


    public background(){
        readImg("imgs/city.png");
        readBg("imgs/bg.png");
    }
    public void dankMemes(String pic){
    	if(dank == false){
    		if(r.nextInt((4-0)+1) == 0){
    			readImg("imgs/images.jpg");
    		}else if(r.nextInt((4-0)+1) == 1){
    			readImg("imgs/images2.png");
    		}else if(r.nextInt((4-0)+1) == 2){
    			readImg("imgs/images3.jpg");
    		}else if(r.nextInt((4-0)+1) == 3){
    			readImg("imgs/download.jpg");
    		}else{
    			readImg("images4.png");
    		}

    		dank = true;
    	}else{
    		readImg(pic);
    		dank = false;
    	}
    }
    public void readImg(String in)
    {
        try {
            bg = ImageIO.read(new File(in));
        } catch (IOException e) {

        }

    }

    public void move(){
        y += 2;
    }
    public void resetVar(){
        y=500;
    }
    public void readBg(String in)
    {
        try {
            b = ImageIO.read(new File(in));
        } catch (IOException e) {

        }

    }
    public void drawBg(Graphics g)
    {
        g.drawImage(b,0, 0, 600, 1000,null);
        g.drawImage(bg,0, y, 600, 600,null);
        //System.out.println(yLoc);
    }
}
