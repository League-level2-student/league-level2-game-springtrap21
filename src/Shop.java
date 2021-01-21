import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Shop {
	int shopWidth;
	int shopHeight;
	JFrame shopFrame = new JFrame();
	JPanel shopPanel = new JPanel();
	JButton b = new JButton();
	JButton b1 = new JButton();
	JButton b2 = new JButton();
	JButton b3 = new JButton();
	void update() {
		shopFrame.add(shopPanel);
		shopPanel.add(b);
		shopPanel.add(b1);
		shopPanel.add(b2);
		shopPanel.add(b3);

	}
	void draw(Graphics g) {
		g.fillRect(200, 150, Zombs.WIDTH/2 - shopWidth, Zombs.HEIGHT/2 - shopHeight);
		
	}
}
