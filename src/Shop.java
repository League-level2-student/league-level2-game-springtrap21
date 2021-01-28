import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Shop extends JPanel{
	int shopWidth;
	int shopHeight;
	JButton b = new JButton("Rifle for $3000");
	JButton b1 = new JButton("SMG for $2500");
	JButton b2 = new JButton("Sniper for $5000");
	JButton b3 = new JButton("Ray Gun for $10000");
	JButton b4 = new JButton("Press to Exit when Done");
	
	Shop() {
		this.add(b);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		
	}
	void update() {
	}
	void draw(Graphics g) {		
	}
}
