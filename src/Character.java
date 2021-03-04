import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Character extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public boolean movingUp, movingDown, movingRight, movingLeft;
	public BufferedImage graphic;
	long lastSet;
	final int PISTOL = 0;
	static final int RIFLE = 1;
	static final int SMG = 2;
	static final int SNIPER = 3;
	static final int RAYGUN = 4;
	boolean isAuto = true;
	
	int currentWeapon;
	int damage;
	int facing; 
	int fireDelay;
	CrossHair ch;
	Character(float x, float y, int width, int height, int hp, CrossHair ch) {
		super(x, y, width, height);
		if (needImage) {
			loadImage ("character.png");
		}
		this.hp = hp;
		this.ch = ch;
		speed = 5;
		changeWeapon(PISTOL);
	}
	
	void draw(Graphics g) {
		if (gotImage) {
			int imgX = (int)x - width/2;
			int imgY = (int)y - height/2;
			if (System.currentTimeMillis() - lastSet > 100) {
				graphic = rotateImageByDegrees(image, facing);
				lastSet = System.currentTimeMillis();
			}
			g.drawImage(graphic, (int)x - graphic.getWidth()/2, (int)y - graphic.getHeight()/2, null);
			//g.setColor(Color.BLUE);
			//g.drawRect(imgX, imgY, width, height);
		}

		else {
			g.setColor(Color.BLUE);
			g.fillRect((int)x-width/2, (int)y-height/2, width, height);
		}
	} 
	public void right() {
		if (x < Zombs.WIDTH - width) {
			x+=speed;
		}
    }
	public void left() {
		if (x > 0) {
	        	x-=speed;
			}
    }
	public void up() {
		if (y > 0) {
			y-=speed;
		}
    }
	public void down() {
		if (y < Zombs.HEIGHT - height) {
			y+=speed;
		}
	}
		
	public void update() {
		float diffx = ch.x - this.x;
		float diffy = ch.y - this.y;
		facing = (int) (Math.atan2(diffy, diffx)*180/Math.PI) + 90;
		if (movingUp) {
			up();
		}
		if (movingDown) {
			down();
		}
		if (movingRight) {
			right();
		}
		if (movingLeft) {
			left();
		}
		super.update();
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
	
	void switchImage(String imageFile) {
		 try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
	        } catch (Exception e) {
	            
	        }
	}
	
	void gotHit(int damage, float moveX, float moveY) {
		hp = hp-damage;
		if (hp <= 0) {
			this.isActive = false;
			Zombs.playSound("santa die.wav");
		}
		else {
			x += moveX * 30;
			y += moveY * 30;
		}

	}
	void changeWeapon(int weapon) {
		currentWeapon = weapon;
		if (currentWeapon == PISTOL) {
			damage = 25;
			fireDelay = 350;
			isAuto = false;
			switchImage("pistol.png");
		}
		if (currentWeapon == RIFLE) {
			damage = 34;
			fireDelay = 210;
			isAuto = true;
			switchImage("AR.png");
		}
		else if (currentWeapon == SMG) {
			damage = 15;
			fireDelay = 120;
			isAuto = true;
			switchImage("character.png");
		}
		else if (currentWeapon == SNIPER) {
			damage = 100;
			fireDelay = 400;
			isAuto = false;
			switchImage("sniper.png");
		}
		else if (currentWeapon == RAYGUN) {
			damage = 75;
			fireDelay = 210;
			isAuto = false;
			switchImage("character.png");
		}
		else {
			damage = 25;
			fireDelay = 250;		
		}
	}
	void buyWeapon(int weapon, int cost) {
		changeWeapon(weapon);
		ObjectManager.points = ObjectManager.points - cost;
	}
}

