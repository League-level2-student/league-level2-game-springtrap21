import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Character c;
	ArrayList<Bullet> bl;
	ArrayList<Zombies> zm;
	ArrayList<Splat> sp;
	public static Random rnd = new Random();
	int score = 0;
	CrossHair ch;

	ObjectManager(Character c, CrossHair ch) {
		this.c = c;
		bl = new ArrayList<Bullet>();
		zm = new ArrayList<Zombies>();
		sp = new ArrayList<Splat>();
		this.ch = ch;
	}
	int getScore() {
		return score;
	}
	void addBullet(float destX, float destY) {
		bl.add(new Bullet(c.x, c.y, 10, 10, destX, destY, 34));
	}

	void addZombie() {
		int side = rnd.nextInt(4);
		int x, y;
		if (side == 0) {
			x = rnd.nextInt(Zombs.WIDTH);
			y = 0;
		} else if (side == 1) {
			x = rnd.nextInt(Zombs.WIDTH);
			y = Zombs.HEIGHT;
		} else if (side == 2) {
			x = 0;
			y = rnd.nextInt(Zombs.HEIGHT);
		} else {
			x = Zombs.WIDTH;
			y = rnd.nextInt(Zombs.HEIGHT);		
		}
		zm.add(new Zombies(x, y, 50, 50, c, 100));
	}

	void update() {
		for (int i = 0; i < bl.size(); i++) {
			Bullet b1 = bl.get(i);
			b1.update();
			if (b1.outOfBounds()) {
				b1.isActive = false;
			}
		}
		for (int i = 0; i < zm.size(); i++) {
			Zombies z1 = zm.get(i);
			z1.update();
			if (z1.outOfBounds()) {
				z1.isActive = false;
			}
		}
		for (int i = 0; i < sp.size(); i++) {
			Splat s1 = sp.get(i);
			s1.update();
			if (s1.outOfBounds()) {
				s1.isActive = false;
			}
		}
		c.update();
		checkCollision();
		purgeObjects();
		}
	void draw(Graphics g) {
		for (int i = 0; i < sp.size(); i++) {
			Splat pro = sp.get(i);
			pro.draw(g); 
		}
		c.draw(g);
		for (int i = 0; i <zm.size(); i++) {
			Zombies z1 = zm.get(i);
			z1.draw(g);
		}
		for (int i = 0; i < bl.size(); i++) {
			Bullet pro = bl.get(i);
			pro.draw(g); 
		}
		
		ch.draw(g);
	}
	void purgeObjects() {
		for (int i = zm.size()-1; i >= 0; i--) {
			Zombies z1 = zm.get(i);
			if (z1.isActive == false) {
				zm.remove(z1);
			}
		}
		for (int i = bl.size()-1; i >= 0; i--) {
			Bullet pro = bl.get(i);
			if (pro.isActive == false) {
				bl.remove(pro);
			}
		}
		for (int i = sp.size()-1; i >= 0; i--) {
			Splat pro = sp.get(i);
			if (pro.isActive == false) {
				sp.remove(pro);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addZombie();
		System.out.println("Zombie");
	}
	void checkCollision() {
		for (int i = 0; i < zm.size(); i++) {
			Zombies z = zm.get(i);
			for (int j = 0; j < bl.size(); j++) {
				Bullet b = bl.get(j);
				if (z.collisionBox.intersects(b.collisionBox)) {
					z.gotHit(b.damage, b.moveX, b.moveY);
					createBlood(b.x, b.y);
					b.isActive = false;
					score++;
				}
			}
			if (z.collisionBox.intersects(c.collisionBox)) {
				c.isActive = false;
				z.isActive = false;
				
			}
		}
	}
	void createBlood(float x, float y) {
		for (int i = 0; i < 5; i++) {
			Splat s = new Splat(x, y, 5, 5);
			sp.add(s);
		}
	}
}
