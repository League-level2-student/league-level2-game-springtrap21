import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Character extends GameObject{
	Character(int x, int y, int width, int height, int hp) {
		super(x, y, width, height);
		this.hp = hp;
		speed = 5;
	}
	public boolean movingUp, movingDown, movingRight, movingLeft;
	public BufferedImage image;
	public boolean needImage = true;
	public boolean gotImage = false;	
	
	void draw(Graphics g) {
	    if (gotImage) {
	    	g.drawImage(image, (int)x, (int)y, width, height, null);
	        } else {
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
	public Bullet getBullet(float destX, float destY) {
		return new Bullet((int)x+width/2, (int)y, 10, 10, destX, destY );
	}
 

}

