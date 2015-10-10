
import java.awt.*;
import java.io.*;

import java.awt.image.BufferedImage;
import javax.imageio.*;

public class GameBoard {

	BufferedImage controls = null;
	BufferedImage endGame = null;
    BufferedImage scoreTxt = null;
    BufferedImage highTxt = null;
    BufferedImage newScore = null;
    BufferedImage logo = null;
    BufferedImage start = null;
    BufferedImage dark = null;

    int up = 0;
    int score = 0;
    int timer = 300;
    int highScore;
    boolean newHighScore = false;
	int x = 0;
    int yMove = 110;
	Font font;
	Font fontEnd;
    Color currentColor = Color.red;
    int step = 5;


    public GameBoard(){
		readImg("imgs/controls.png");
		readEndGame("imgs/Score.png");
		fontLoader();
		fontLoaderScore();
        readHs("imgs/scoreText.png","imgs/high.png", "imgs/new.png", "imgs/logo.png","imgs/start.png","imgs/darkBg.png");




	}
	public void fontLoader(){
        try {
                //create the font to use. Specify the size!
                font = Font.createFont(Font.TRUETYPE_FONT, new File("imgs/pixelFont.ttf")).deriveFont(50f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("imgs/pixelFont.ttf")));
                } catch (IOException e) {
                e.printStackTrace();
                }
                catch(FontFormatException e)
                {
                    e.printStackTrace();
                }


	}
	public void resetVar(){
		score = 0;
		timer = 300;
		x = 0;
        newHighScore = false;
        currentColor = Color.red;
        step = 7;
	}
	public void setStep(int x){
		step = x;
	}
	public void fontLoaderScore(){
        try {
                //create the font to use. Specify the size!
                fontEnd = Font.createFont(Font.TRUETYPE_FONT, new File("imgs/pixelFont.ttf")).deriveFont(100f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                //register the font
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("imgs/pixelFont.ttf")));
                } catch (IOException e) {
                e.printStackTrace();
                }
                catch(FontFormatException e)
                {
                    e.printStackTrace();
                }


	}
    public void saveScore(int x){
        try {
            PrintWriter writer = new PrintWriter("highScore.txt", "UTF-8");
            writer.println(x);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
    public int getScore(){
    	return score;
    }
    public void isHighScore() throws FileNotFoundException {
        FileReader hs = new FileReader("highScore.txt");
        BufferedReader hScore = new BufferedReader(hs);

        try {
            highScore = Integer.parseInt(hScore.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(score > highScore){
            saveScore(score);
            newHighScore = true;
        }


    }
	public void readImg(String in)
	{
		try {
    		controls = ImageIO.read(new File(in));
		} catch (IOException e) {

		}

 	}
    public void changeBarColor(){
            currentColor = Color.white;
    }
    public void revertBarColor(){
        currentColor = Color.RED;
    }
    public void readHs(String in, String tu, String el, String unos, String unas, String nosotros)
    {
        try {
            scoreTxt = ImageIO.read(new File(in));
        } catch (IOException e) {

        }
        try {
            highTxt = ImageIO.read(new File(tu));
        } catch (IOException e) {

        }
        try {
            newScore = ImageIO.read(new File(el));
        } catch (IOException e) {

        }
        try {
            logo = ImageIO.read(new File(unos));
        } catch (IOException e) {

        }
        try {
            start = ImageIO.read(new File(unas));
        } catch (IOException e) {

        }
        try {
            dark = ImageIO.read(new File(nosotros));
        } catch (IOException e) {

        }
    }


 	public void readEndGame(String in)
	{
		try {
    		endGame = ImageIO.read(new File(in));
		} catch (IOException e) {

		}

 	}
    public void addScore(){
        score += 1;
    }
 	public void spaceClick(){
 		readImg("controlsSpc.png");
 	}
    public void normClick(){
        readImg("controls.png");
    }
 	public void lClick(){
 		readImg("controlsL.png");
 	}
 	public void drawUI(Graphics g)
	{
		g.drawImage(controls,50, 875, 1920/4, 480/4,null);

		//System.out.println(yLoc);
	}
    public void subtractTmr(){
        timer -= 1;
    }
    public int getTimer(){
        return timer;
    }
    public void addTmr(){
        timer += step;
    }
    public void animateLogo(){
        if(up == 0) {
            yMove -= 1;
        }else{
            yMove += 1;
        }
        if(yMove == 100){
            up = 1;
        }else if(yMove == 110){
            up = 0;
        }

    }
    public void drawScore(Graphics g){
    	g.setFont(font);
        g.fillRect(140,65,320,45);
        g.setColor(currentColor);
        g.fillRect(150,75,timer,25);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(score), 280, 55);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(score), 275, 50);
    }
    public void drawEndScreen(Graphics g){
    	g.setFont(fontEnd);
    	g.drawImage(dark,0,0,600,1000,null);
		g.drawImage(endGame, 40, 100, 500, 500, null);

        g.drawImage(scoreTxt, 100, 515, 28*5, 7*5, null);
        g.drawImage(highTxt, 100, 475, 28*5, 7*5, null);

        g.setColor(Color.RED);
		g.drawString(String.valueOf(score), 125, 400);
        if(newHighScore == true) {
            g.drawImage(newScore, 100, 440, 18*4, 7*4, null);
            g.drawString(String.valueOf(score), 250, 550);
        }else{
            g.drawString(String.valueOf(highScore), 260, 550);
        }
        g.setFont(font);

        g.setColor(Color.BLACK);
        g.drawString("Press r ", 165, 655);
        g.setColor(Color.GREEN);
        g.drawString("Press r ", 160, 650);

        g.setColor(Color.BLACK);
        g.drawString("to play again", 45, 705);
        g.setColor(Color.RED);
        g.drawString("to play again", 40, 700);

    }
    public void drawLogo(Graphics g){
        g.drawImage(logo,50,yMove,952/2,452/2,null);
        g.setColor(Color.black);
        g.fillRect(205,330,200,100);
        g.drawImage(start,200,325,200,100,null);
    }
}
