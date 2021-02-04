import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Shop extends JPanel{
	int shopWidth;
	int shopHeight;
	boolean doneShopping = false;
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
		this.add(b4);
		b.setFocusable(false);
		b1.setFocusable(false);
		b2.setFocusable(false);
		b3.setFocusable(false);
		b4.setFocusable(false);
		b.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    bButtonPressed();
				  }
		} );
		b1.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    b1ButtonPressed();
				  }
		} );
		b2.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    b2ButtonPressed();
				  }
		} );
		b3.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    b3ButtonPressed();
				  }
		} );
		b4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    b4ButtonPressed();
				  }
		} );
		
	}
	void update() {
		if (ObjectManager.score >= 3000) {
			b.setEnabled(true);
		}
		else {
			b.setEnabled(false);
		}
		if (ObjectManager.score >= 2500) {
			b1.setEnabled(true);
		}
		else {
			b1.setEnabled(false);
		}
		if (ObjectManager.score >= 5000) {
			b2.setEnabled(true);
		}
		else {
			b2.setEnabled(false);
		}
		if (ObjectManager.score >= 10000) {
			b3.setEnabled(true);
		}
		else {
			b3.setEnabled(false);		
		}
	}
	void draw(Graphics g) {		
	}
	void bButtonPressed() {
		
	}
	void b1ButtonPressed() {
		
	}
	void b2ButtonPressed() {
	
	}
	void b3ButtonPressed() {
	
	}
	void b4ButtonPressed() {
		doneShopping = true;
	}
}
