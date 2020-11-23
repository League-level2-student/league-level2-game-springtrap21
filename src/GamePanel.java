import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subFont;
	Timer frameDraw;
	ObjectManager om;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Timer alienSpawn;
	
	GamePanel() {
		  frameDraw = new Timer(1000/60,this);
		    frameDraw.start();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subFont = new Font("Arial", Font.PLAIN, 30);
		if (needImage) {
		    loadImage ("space.png");
		}
	}

	void updateMenuState() {
	}

	void updateGameState() {
		
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Zombs.WIDTH, Zombs.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 10, 100);
		g.setFont(subFont);
		g.setColor(Color.YELLOW);
		g.drawString("press ENTER to start", 100, 400);
		g.setFont(subFont);
		g.setColor(Color.YELLOW);
		g.drawString("press SPACE for instructions", 50, 550);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0,Zombs.WIDTH, Zombs.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0,Zombs.WIDTH, Zombs.HEIGHT);
		}
		
		
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Zombs.WIDTH, Zombs.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		g.drawString("GAME OVER", 100, 100);
		g.setFont(subFont);
		g.setColor(Color.BLACK);
		g.setFont(subFont);
		g.setColor(Color.BLACK);
		g.drawString("press enter to reastart", 75, 550);
		
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}	
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    }
		    else if (currentState == MENU) {
				currentState = GAME;
				startGame();
			}
		    else {
		        currentState++;
		    }
		}
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode()==KeyEvent.VK_W) {
		    System.out.println("W");
		    
		}
		if (arg0.getKeyCode()==KeyEvent.VK_S) {
		    System.out.println("S");
		   
		}
		if (arg0.getKeyCode()==KeyEvent.VK_A) {
		    System.out.println("A");
		  
		}
		if (arg0.getKeyCode()==KeyEvent.VK_D) {
		    System.out.println("D");
		    
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SPACE && currentState == GAME) {
			
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SPACE && currentState == MENU) {
			JOptionPane.showMessageDialog(null, "You move your rocket ship around with the arrow keys. Use space to shoot at the Aliens, and try to survive!");
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode()==KeyEvent.VK_UP) {
		    System.out.println("UP");
		    
		}
		if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		    System.out.println("DOWN");
		    
		}
		if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		    System.out.println("LEFT");
		    
		}
		if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		    System.out.println("RIGHT");
		    
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	void startGame() {
		alienSpawn = new Timer(1000 , om);
	    alienSpawn.start();

	}
}