import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Zombies extends GameObject {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	GameObject target;
	Zombies(int x, int y, int width, int height, GameObject target, int hp) {
		super(x, y, width, height);
		speed = 1;
		this.target = target;
		this.hp = hp;
		if (needImage) {
		   // loadImage ("");
		}
		// TODO Auto-generated constructor stub
	}
	public void update() {
		float diffx = target.x - this.x;
		float diffy = target.y - this.y;
		float dist = (float) Math.sqrt(diffx * diffx + diffy * diffy);
		x += (diffx/dist*speed);
		y += (diffy/dist*speed);
		super.update();
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, (int)x - width/2, (int)y - height/2, width, height, null);
		} else {
			g.setColor(Color.RED);
			g.fillRect((int)x-width/2, (int)y-height/2, width, height);
		}
	}
	void loadImage(String imageFile) {
	    
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
