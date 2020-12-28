import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bullet extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	float moveX;
	float moveY;
	int damage;
	BufferedImage graphic;
	int facing;
	Bullet(float x, float y, int width, int height, float destX, float destY, int damage) {
		super(x, y, width, height);
		speed = 10;
	    this.damage = damage;
		float diffx = destX - this.x;
		float diffy = destY - this.y;
		float dist = (float) Math.sqrt(diffx * diffx + diffy * diffy);
		moveX = (diffx/dist);
		moveY = (diffy/dist);
		facing = (int) (Math.atan2(diffy, diffx)*180/Math.PI) + 90;
		if (needImage) {
			loadImage("Bullet1.png");
		}
		graphic = rotateImageByDegrees(image, facing);
		// TODO Auto-generated constructor stub
	}
	void draw(Graphics g) {
		if (gotImage) {
        	g.drawImage(graphic, (int) x - graphic.getWidth()/2, (int) y - graphic.getHeight()/2, null);
        } else {
        	g.setColor(Color.yellow);
        	g.fillRect((int)x - width/2, (int)y - height/2, width, height);
        }
	}
	void update() {
		x += moveX * speed;
		y += moveY * speed;
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

