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
	int facing; 
	CrossHair ch;
	Character(float x, float y, int width, int height, int hp, CrossHair ch) {
		super(x, y, width, height);
		if (needImage) {
			loadImage ("character.png");
		}
		this.hp = hp;
		this.ch = ch;
		speed = 5;
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
}

