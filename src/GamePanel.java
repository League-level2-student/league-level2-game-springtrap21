import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
	
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font subFont;
	Timer frameDraw;
	ObjectManager om;
	Character c;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Timer alienSpawn;
	CrossHair ch;
	
	GamePanel() {
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		titleFont = new Font("Arial", Font.PLAIN, 48);
		subFont = new Font("Arial", Font.PLAIN, 30);
		c = new Character(Zombs.WIDTH/2, Zombs.HEIGHT/2, 50, 50, 100);
		ch = new CrossHair(400, 400, 250, 250);
		om = new ObjectManager(c, ch);
		if (needImage) {
		    //loadImage ("");
		}
			}

	void updateMenuState() {
	}

	void updateGameState() {
		om.update();
		if (c.isActive == false) {
			currentState = END;
		}
	}

	void updateEndState() {
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Zombs.WIDTH, Zombs.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		g.drawString("Zombs", Zombs.WIDTH/2-50, 100);
		g.setFont(subFont);
		g.setColor(Color.WHITE);
		g.drawString("press ENTER to start", Zombs.WIDTH/2-130, 400);
		g.setFont(subFont);
		g.setColor(Color.WHITE);
		g.drawString("press SPACE for instructions", Zombs.WIDTH/2-170, 550);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0,Zombs.WIDTH, Zombs.HEIGHT, null);
		} else {
			g.setColor(new Color (165,42,42));
			g.fillRect(0, 0,Zombs.WIDTH, Zombs.HEIGHT);
		}
		om.draw(g);
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
		    c.movingUp = true;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_S) {
		    System.out.println("S");
		   c.movingDown = true;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_A) {
		    System.out.println("A");
		  c.movingLeft = true;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_D) {
		    System.out.println("D");
		    c.movingRight = true;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SPACE && currentState == GAME) {
			
		}
		if (arg0.getKeyCode()==KeyEvent.VK_SPACE && currentState == MENU) {
			JOptionPane.showMessageDialog(null, "You move your rocket ship around with the arrow keys. Use space to shoot at the Aliens, and try to survive!");
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode()==KeyEvent.VK_W) {
		    System.out.println("UP");
		    c.movingUp = false;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_S) {
		    System.out.println("DOWN");
		    c.movingDown = false;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_A) {
		    System.out.println("LEFT");
		    c.movingLeft = false;
		}
		if (arg0.getKeyCode()==KeyEvent.VK_D) {
		    System.out.println("RIGHT");
		    c.movingRight = false;
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (currentState == GAME) {
			om.addBullet(ch.x, ch.y);
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int mouseX = arg0.getX() - 7;
		int mouseY = arg0.getY() - 30;
		ch.update(mouseX, mouseY);
	}
}