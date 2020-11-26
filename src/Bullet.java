import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bullet extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	float destX;
	float destY;
	Bullet(int x, int y, int width, int height, float destX, float destY) {
		super(x, y, width, height);
		speed = 10;
		this.destX = destX;
		this.destY = destY;
		// TODO Auto-generated constructor stub
	}
	void draw(Graphics g) {
		if (gotImage) {
        	g.drawImage(image, (int) x, (int) y, width, height, null);
        } else {
        	g.setColor(Color.yellow);
        	g.fillRect((int)x, (int)y, width, height);
        }
	}
	void update() {
		float diffx = destX - this.x;
		float diffy = destY - this.y;
		float dist = (float) Math.sqrt(diffx * diffx + diffy * diffy);
		x += (diffx/dist*speed);
		y += (diffy/dist*speed);
		super.update();
		System.out.println("X " + diffx);
		System.out.println("Y " + diffy);
		if (diffx >= 0 && diffy >= 0) {
			isActive = false;
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

