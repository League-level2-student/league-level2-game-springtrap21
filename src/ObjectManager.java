import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.management.timer.Timer;

public class ObjectManager{
	static int score = 0;
	Character c;
	ArrayList<Bullet> bl;
	ArrayList<Zombies> zm;
	ArrayList<Splat> sp;
	public static Random rnd = new Random();
	int hits = 0;
	int misses = 0;
	int points = 0;
	int zomSpawned = 0;
	int zomKilled = 0;
	boolean zomSpawn = true;
	boolean allZomDead = false;
	boolean shopTime = false;
	int wave = 1;
	int zomPerWave = 5;
	long wavePause;
	long zombLastSpawn;
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
		Zombs.playSound("gun shot.wav", -12);
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
		zm.add(new Zombies(x, y, 50, 50, c, 100, 34));
		//Zombs.playSound("zombie gargle.wav");
	}

	void update() {
		for (int i = 0; i < bl.size(); i++) {
			Bullet b1 = bl.get(i);
			b1.update();
			if (b1.outOfBounds()) {
				b1.isActive = false;
				misses++;
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
		checkSpawn();
	}
	
	void draw(Graphics g) {
		for (int i = 0; i < sp.size(); i++) {
			Splat pro = sp.get(i);
			pro.draw(g); 
		}
		for (int i = 0; i < bl.size(); i++) {
			Bullet pro = bl.get(i);
			pro.draw(g); 
		}
		for (int i = 0; i <zm.size(); i++) {
			Zombies z1 = zm.get(i);
			z1.draw(g);
		}
		c.draw(g);
		ch.draw(g);
	}
	void purgeObjects() {
		for (int i = zm.size()-1; i >= 0; i--) {
			Zombies z1 = zm.get(i);
			if (z1.isActive == false) {
				zm.remove(z1);
				score++;
				zomKilled++;
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
	
	void checkSpawn() {
		if (zomSpawn && System.currentTimeMillis() - zombLastSpawn > 1000) {
			addZombie();
			zomSpawned++;
			zombLastSpawn = System.currentTimeMillis();
		}
		else if (zomSpawned == 0 && System.currentTimeMillis() - wavePause > 5000){
			zomSpawn = true;
		}
		if (zomSpawned == zomPerWave) {
			zomSpawn = false;
		}
		if (zomKilled == zomPerWave) {
			newWaveStart();
		}
	}
	
	void newWaveStart() {
		wavePause = System.currentTimeMillis();
		zomKilled = 0;
		zomSpawned = 0;
		points += hits * 10 - misses * 5;
		hits = 0;
		misses = 0;
		wave++;
		zomPerWave += 2;
		if (wave == 2 || wave == 5 || wave == 10 || wave == 15 || wave == 20 || wave == 25 || wave == 30 || wave == 40|| wave == 50) {
			shopTime = true;
		}
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
					Zombs.playSound("bullet hit.wav", -6);
					hits++;
				}
			}
			if (z.collisionBox.intersects(c.collisionBox)) {
				c.gotHit(z.damage, z.moveX, z.moveY);
				z.gotHit(0, -z.moveX, -z.moveY);
				Zombs.playSound("oof.wav");
				
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
