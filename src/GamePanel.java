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
	
	public static BufferedImage image;
	public static BufferedImage heartImage;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	final int SHOP = 3;
	int currentState = MENU;			
	Font titleFont;
	Font subFont;
	Timer frameDraw;
	ObjectManager om;
	Character c;
	CrossHair ch;
	Shop shop;
	boolean firing;
	long lastFire;
	boolean pause = false;

	GamePanel() {
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
		loadImages();
		reset();
	}
	
	void reset() {
		
		titleFont = new Font("Calibri", Font.PLAIN, 48);
		subFont = new Font("Comic Sans", Font.PLAIN, 30);
		ch = new CrossHair(0, 0, 75, 75);
		c = new Character(Zombs.WIDTH/2, Zombs.HEIGHT/2, 40, 40, 100, ch);
		om = new ObjectManager(c, ch);
		shop = new Shop(this);
		firing = false;
		lastFire = System.currentTimeMillis();
		
	}

	void updateMenuState() {
	}

	void updateGameState() {
		if (!pause) {
			om.update();
			if (c.isActive == false) {
				currentState = END;
			}
			if (firing) {
				if (System.currentTimeMillis() - lastFire > c.fireDelay) {
					lastFire = System.currentTimeMillis();
					if (currentState == GAME) {
						om.addBullet(ch.x, ch.y);
					}
					if (c.isAuto == false) {
						firing = false;
					}
				}
			}
			if (om.shopTime) {
				currentState = SHOP;
				this.add(shop);
				this.revalidate();
				om.shopTime = false;
			}
		}
	}

	void updateEndState() {
	}
	
	void updateShopState() {
		shop.update();
		if (shop.doneShopping) {
			this.remove(shop);
			currentState = GAME;
			shop.doneShopping = false;
		}
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Zombs.WIDTH, Zombs.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.WHITE);
		int textWidth = g.getFontMetrics().stringWidth("Zombs: Survive!");
		g.drawString("Zombs: Survive", Zombs.WIDTH/2-(textWidth/2), 100);
		g.setFont(subFont);
		g.setColor(Color.WHITE);
		textWidth = g.getFontMetrics().stringWidth("Press ENTER to start");
		g.drawString("Press ENTER to start", Zombs.WIDTH/2-(textWidth/2), 400);
		g.setFont(subFont);
		g.setColor(Color.WHITE);
		textWidth = g.getFontMetrics().stringWidth("press SPACE for instructions");
		g.drawString("Press SPACE for instructions", Zombs.WIDTH/2-(textWidth/2), 550);
	}

	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0,Zombs.WIDTH, Zombs.HEIGHT, null);
		} else {
			g.setColor(new Color (165,42,42));
			g.fillRect(0, 0,Zombs.WIDTH, Zombs.HEIGHT);
		}
		om.draw(g);
		
		drawHud(g);
	}
	
	void drawHud(Graphics g) {
		if (c.hp > 66) {
			g.drawImage(heartImage, Zombs.WIDTH - 100, 25, null);
		}
		if (c.hp > 33) {
			g.drawImage(heartImage, Zombs.WIDTH - 160, 25, null);
		}
		if (c.hp > 0) {
			g.drawImage(heartImage, Zombs.WIDTH - 220, 25, null);
		}
		
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("Purged: " + om.score, 30, 60);
		int textWidth = g.getFontMetrics().stringWidth("Wave: " + om.wave);
		g.drawString("Wave: " + om.wave, Zombs.WIDTH/2 - textWidth/2, 60);
		textWidth = g.getFontMetrics().stringWidth("PAUSED");
		if (pause) {
			g.drawString("PAUSED", Zombs.WIDTH/2 - textWidth/2, Zombs.HEIGHT/2);
		}
		textWidth = g.getFontMetrics().stringWidth("Points: " + om.points);
		g.drawString("Points: " + om.points, Zombs.WIDTH/2 - textWidth/2, 700);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, Zombs.WIDTH, Zombs.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.BLACK);
		int textWidth = g.getFontMetrics().stringWidth("NICE TRY GAME OVER");
		g.drawString("NICE TRY GAME OVER", Zombs.WIDTH/2-(textWidth/2), 100);
		g.setFont(subFont);
		g.setColor(Color.BLACK);
		g.setFont(subFont);
		textWidth = g.getFontMetrics().stringWidth("You survived until wave " + om.wave + " !");
		g.drawString("You survived until wave " + om.wave + " !", Zombs.WIDTH/2-(textWidth/2), 400);
		textWidth = g.getFontMetrics().stringWidth("You purged " + om.score + " Zombies!");
		g.drawString("You purged " + om.score + " Zombies!", Zombs.WIDTH/2-(textWidth/2), 330);
		g.setColor(Color.BLACK);
		textWidth = g.getFontMetrics().stringWidth("Press ENTER to restart");
		g.drawString("Press ENTER to restart", Zombs.WIDTH/2-(textWidth/2), 550);
		
	}
	
	void drawShopState(Graphics g) {
		shop.draw(g);
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		} else if (currentState == SHOP) {
			drawGameState(g);
			drawShopState(g);
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
		}else if(currentState == SHOP) {
			updateShopState();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		        reset();
		    }
		    else if (currentState == MENU) {
				currentState = GAME;
				//Zombs.playSound("ho ho ho.wav");
			}
		    else if (currentState == GAME) {
		    	pause = !pause;
		    }
		    else if (currentState == SHOP) {
		    	
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
			JOptionPane.showMessageDialog(null, "You move your character around with the keys: W A S D. Use Left Click to shoot at the Zombies. Need some time? press enter when you are in the game to pause. When the shop pops up, you can click on the butons to by different weapons. And one last thing, try to survive!");
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
	
	void loadImages() {
		heartImage = loadImage("healthIcon.png");
		image = loadImage("grass.png");
		gotImage = true;
	}
	
	BufferedImage loadImage(String imageFile) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		} catch (Exception e) {

		}
		return img;
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
		firing =true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		firing = false;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int mouseX = arg0.getX() - 7;
		int mouseY = arg0.getY() - 30;
		ch.update(mouseX, mouseY);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int mouseX = arg0.getX() - 7;
		int mouseY = arg0.getY() - 30;
		ch.update(mouseX, mouseY);
	}
}