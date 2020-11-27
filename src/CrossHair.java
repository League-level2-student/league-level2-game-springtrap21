import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class CrossHair extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	CrossHair(float x, float y, int width, int height) {
		super(x, y, width, height);
		if (needImage) {
			loadImage ("crosshair.png");
		}
	}
	void update(float mouseX, float mouseY) {
		x = mouseX;
		y = mouseY;
		super.update();
	}
	void draw(Graphics g) {
		 if (gotImage) {
	        	g.drawImage(image, (int) x - width/2, (int) y - height/2, width, height, null);
	        } else {
	        	g.setColor(Color.BLUE);
	        	g.fillRect((int)x - width/2, (int)y - height/2, width, height);
	        }
	}
	void loadImage(String imageFile) {
		// TODO Auto-generated method stub
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
