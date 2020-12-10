import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Splat extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	float moveX;
	float moveY;
	long startTime;
	int delay;
	Splat(float x, float y, int width, int height) {
		super(x, y, width, height);
		moveX = ObjectManager.rnd.nextFloat() * 2 - 1;
		moveY = ObjectManager.rnd.nextFloat() * 2 - 1;
		speed = ObjectManager.rnd.nextInt(10) + 11;
		startTime = System.currentTimeMillis();
		delay = ObjectManager.rnd.nextInt(2000) + 3000;
	 }
	void draw(Graphics g) {
		if (gotImage) {
        	g.drawImage(image, (int) x - width/2, (int) y - height/2, width, height, null);
        } else {
        	g.setColor(Color.red);
        	g.fillRect((int)x - width/2, (int)y - height/2, width, height);
        }
	}
	void update() {
		x += moveX * speed;
		y += moveY * speed;
		if (speed > 0) {
			speed--;
		}
		super.update();
		if (System.currentTimeMillis() - startTime > delay) {
			this.isActive = false;
		}
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
