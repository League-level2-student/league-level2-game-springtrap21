import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;

public class Zombies extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GameObject target;
	int facing;
	Zombies(int x, int y, int width, int height, GameObject target, int hp) {
		super(x, y, width, height);
		speed = 1;
		this.target = target;
		this.hp = hp;
		if (needImage) {
			loadImage("zombie1.png");
		}
	}
	
	public void update() {
		float diffx = target.x - this.x;
		float diffy = target.y - this.y;
		float dist = (float) Math.sqrt(diffx * diffx + diffy * diffy);
		x += (diffx/dist*speed);
		y += (diffy/dist*speed);
		facing = (int) (Math.atan2(diffy, diffx)*180/Math.PI) + 90;
		super.update();
	}
	void draw(Graphics g) {
		if (gotImage) {
			int imgX = (int)x - width/2;
			int imgY = (int)y - height/2;
			g.drawImage(rotateImageByDegrees(image, facing), imgX, imgY, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect((int)x-width/2, (int)y-height/2, width, height);
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
	void gotHit(int damage, float moveX, float moveY) {
		hp = hp-damage;
		if (hp <= 0) {
			this.isActive = false;
		}
		else {
			x += moveX * 10;
			y += moveY * 10;
		}
	}
}
