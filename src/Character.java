import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Character extends GameObject{
	Character(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	public boolean movingUp, movingDown, movingRight, movingLeft;
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	void draw(Graphics g) {
	    if (gotImage) {
	        } else {
	        	g.setColor(Color.BLUE);
	        }
	} 
	public void right() {
		
	
    }
	public void left() {
       
    }
	public void up() {

    }
	public void down() {
		
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
 

}

