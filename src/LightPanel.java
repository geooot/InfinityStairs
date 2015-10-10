

 import java.io.FileNotFoundException;
 import java.util.*;
 import java.awt.event.*;
 import java.awt.*;
 import javax.swing.*;
 import javax.swing.Timer;
 import static java.lang.System.*;

public class LightPanel extends JPanel implements ActionListener,KeyListener {


Random r = new Random();
Scanner kb = new Scanner(System.in);
Step[] spawnedSteps = new Step[20];
int z;
int difference;
    int frame = 0;
    int wait = 0;
    int justStarted = 0;
    int timerStop = 0;
    boolean isControlsActive = true;
    boolean isGameOver = false;
    //l:0 r:1
    boolean showEndGame = false;
    int leftOrRight=0;
    background bg = new background();
    Char ch = new Char();
    GameBoard gb = new GameBoard();

//Inheritance Lab:
//declare a object of type of the child class
public LightPanel()

	{
		super();

		addKeyListener(this);
        setPreferredSize(new Dimension(600,1000));
		/*for(int x=0;x<20;x++){
			random[x] = r.nextInt((5-0)+1)*100;
		}*/
		startUp();


//Inheritance Lab:
//instantiate tshe object per the constructor.  Just like you did for the original Traffic Light lab


	}
	public void startUp(){
        System.out.println("\n NEW SET");
        spawnedSteps[0] = new Step(r.nextInt((5-0)+1)*100, 0);
		for(int x=1;x<20;x++){

			if(r.nextInt((1-0)+1) == 1){
				spawnedSteps[x] = new Step(spawnedSteps[x-1].getX()-100,x);
			}else{
				spawnedSteps[x] = new Step(spawnedSteps[x-1].getX()+100,x);
			}
            //System.out.println(spawnedSteps[x].getY());




		}
        midCheck(19);
	}
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		g.setColor(Color.CYAN);
		g.fillRect(0,0,600,1000);

        bg.drawBg(g);
		//step.setX(pos*100);


		for(int x=0;x<20;x++){
			spawnedSteps[x].drawStep(g);

		}

		ch.drawChar(g);

        if(isGameOver == false) {
        	ch.normState();
            gb.drawUI(g);
            gb.drawScore(g);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 8));
            g.setColor(Color.cyan);
            //g.drawString("M 4 MAGIC@AL EAST3R EGG",2,10);
        }else{
            if(showEndGame == true) {
                gb.drawEndScreen(g);
            }

        }
        gb.normClick();
        gb.revertBarColor();
        if(justStarted == 0) {
            gb.drawLogo(g);
        }




	}


	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){
        if(isControlsActive == true) {
            if (e.getKeyChar() == ' ') {
                //left
                justStarted = 1;
                timerStop = 1;
                ch.jumpState();
                gb.spaceClick();
                gb.changeBarColor();
                isControlsActive = false;

            }

            //turn

            if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
                gb.lClick();
                gb.changeBarColor();
                ch.jumpState();
                timerStop = 1;
                justStarted = 1;
                if (leftOrRight == 0) {
                    ch.changeDirection(1);
                    leftOrRight = 1;
                } else {
                    ch.changeDirection(0);
                    leftOrRight = 0;
                }
                isControlsActive = false;
            }
        }
        if(isGameOver == true) {
            if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
                reset();
            }
        }
        if(e.getKeyChar() == 'd'){
        	for(int x=0;x<20;x++){
        		spawnedSteps[x].devMode();
        	}
        }
        if(e.getKeyChar() == 'm'){
        	bg.dankMemes("imgs/city.png");
        	ch.dankMemes();
        }

	}
public void cycleLights()
		{
			if(gb.getScore() == 100){
				gb.setStep(4);
			}
			if(gb.getScore() == 200){
				gb.setStep(3);
			}
            gb.animateLogo();
            if(justStarted != 0 && timerStop != 0) {
                gb.subtractTmr();
            }

            if(isGameOver == true){
                wait +=1;
                if(wait >=4){
                	ch.death();
                }

                if(wait >= 15) {
                    ch.moveDown();

                }
                if(wait >= 25) {
                    showEndGame = true;
                }
            }else{
                if(gb.getTimer() <= 0){
                    gameOver();
                }
	            if(isControlsActive == false){
	            	if(frame != 1){
	            		step();

                        bg.move();
	            	}else{
	            		stepGoUp();
                        bg.move();
	            	}
	            	if(frame == 1){
	            		isControlsActive = true;
	            		frame = 0;
	            	}else{
	            		frame += 1;
	            	}

	            }
            }



		}
    public void midCheck(int value){
        if(spawnedSteps[value].getX() != 250){
            difference = spawnedSteps[value].getX() - 250;
        }
        for(int x = 0; x<20; x++) {
            spawnedSteps[x].setX(spawnedSteps[x].getX() - difference);
        }
    }
    public void stepGoUp(){

        for(int x=0;x<20;x++){
            spawnedSteps[x].moveDown();

            if(spawnedSteps[x].getY() == 950){
                midCheck(x);
            }
            if (spawnedSteps[x].getY() >= 1000) {

                spawnedSteps[x].setY(0);
                if (z == 1) {
                    if (r.nextInt((1 - 0) + 1) == 0) {
                        spawnedSteps[x].setX(spawnedSteps[x + 1].getX() + 100);
                    } else {
                        spawnedSteps[x].setX(spawnedSteps[x + 1].getX() - 100);
                    }

                }

                if (z == 2) {
                    if (r.nextInt((1 - 0) + 1) == 0) {
                        spawnedSteps[x].setX(spawnedSteps[x - 1].getX() + 100);
                    } else {
                        spawnedSteps[x].setX(spawnedSteps[x - 1].getX() - 100);
                    }

                }

                if (spawnedSteps[19].getY() == 0) {
                    if (r.nextInt((1 - 0) + 1) == 0) {
                        spawnedSteps[19].setX(spawnedSteps[0].getX() + 100);
                    } else {
                        spawnedSteps[19].setX(spawnedSteps[0].getX() - 100);
                    }
                    z = 1;

                }
                if (spawnedSteps[0].getY() == 0) {
                    if (r.nextInt((1 - 0) + 1) == 0) {
                        spawnedSteps[0].setX(spawnedSteps[1].getX() + 100);
                    } else {
                        spawnedSteps[0].setX(spawnedSteps[1].getX() - 100);
                    }
                    z = 2;

                }


            }
            //System.out.println(spawnedSteps[x].getY());
        }

    }
    public void step() {
        if (leftOrRight == 0) {
            if (spawnedSteps[0].getY() == 950) {
                if (spawnedSteps[19].getX() == 350) {
                    stepGoUp();
                    gb.addScore();
                    gb.addTmr();
                } else {
                    gameOver();
                }
            }
            if (spawnedSteps[19].getY() == 950) {
                if (spawnedSteps[18].getX() == 350) {
                    stepGoUp();
                    gb.addScore();
                    gb.addTmr();
                } else {
                    gameOver();
                }
            }
                for (int x = 1; x < 19; x++) {
                    if (spawnedSteps[x].getY() == 950) {
                        if (spawnedSteps[x - 1].getX() == 350 || spawnedSteps[x + 1].getX() == 350) {
                            stepGoUp();
        					gb.addScore();
                            gb.addTmr();
                        } else {
                            gameOver();
                        }
                    }
                }
            } else {
            if(spawnedSteps[0].getY() == 950){
                if(spawnedSteps[19].getX() == 150){
                    stepGoUp();
                    gb.addScore();
                }else{
                    gameOver();
                }
            }
            if(spawnedSteps[19].getY() == 950){
                if(spawnedSteps[18].getX() == 150){
                    stepGoUp();
                    gb.addScore();
                }else{
                    gameOver();
                }
            }
                for (int x = 1; x < 19; x++) {
                    if (spawnedSteps[x].getY() == 950) {
                        if (spawnedSteps[x - 1].getX() == 150 || spawnedSteps[x + 1].getX() == 150) {
                            stepGoUp();
                            gb.addScore();
                            gb.addTmr();
                        } else {
                            gameOver();
                        }
                    }
                }
            }

    }
    public void gameOver(){
        try {
            gb.isHighScore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ch.stepOut();

        isGameOver = true;
        isControlsActive = false;
    }
    public void reset(){

    	z = 0;
		difference = 0;
	    frame = 0;
	    wait = 0;
	    timerStop = 0;
	    isControlsActive = true;
	    isGameOver = false;
	    //l:0 r:1
	    showEndGame = false;
	    leftOrRight=0;

    	gb.resetVar();
    	ch.resetVar();
        bg.resetVar();
        for(int x = 0; x < 20; x++){
            spawnedSteps[x].setX(0);
            spawnedSteps[x].setY(0);
        }
        startUp();
    }

//this listens to the timer and calls the a method(s) to change the lights
	public void actionPerformed(ActionEvent e)
		{

		    cycleLights();

			repaint();
		}


}