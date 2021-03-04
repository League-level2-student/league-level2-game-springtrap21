import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Shop extends JPanel{
	GamePanel gp;
	int shopWidth;
	int shopHeight;
	boolean doneShopping = false;
	final int RIFLE_COST = 5000;
	final int SMG_COST = 2500;
	final int SNIPER_COST = 10000;
	final int RAY_COST = 15000;
	JButton b = new JButton("AR for $" + RIFLE_COST);
	JButton b1 = new JButton("SMG for $" + SMG_COST);
	JButton b2 = new JButton("Sniper for $" + SNIPER_COST);
	//JButton b3 = new JButton("Ray Gun for $" + RAY_COST);
	JButton b4 = new JButton("Press to Exit when Done");
	
	Shop(GamePanel gp) {
		this.gp = gp;
		this.add(b);
		this.add(b1);
		this.add(b2);
		//this.add(b3);
		this.add(b4);
		b.setFocusable(false);
		b1.setFocusable(false);
		b2.setFocusable(false);
		//b3.setFocusable(false);
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
		//addActionListener(new ActionListener() { 
			//  public void actionPerformed(ActionEvent e) { 
				//    b3ButtonPressed();
				  //}
		//} );
		b4.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				    b4ButtonPressed();
				  }
		} );
		
	}
	void update() {
		if (ObjectManager.points >= RIFLE_COST) {
			b.setEnabled(true);
		}
		else {
			b.setEnabled(false);
		}
		if (ObjectManager.points >= SMG_COST) {
			b1.setEnabled(true);
		}
		else {
			b1.setEnabled(false);
		}
		if (ObjectManager.points >= SNIPER_COST) {
			b2.setEnabled(true);
		}
		else {
			b2.setEnabled(false);
		}
		//if (ObjectManager.points >= RAY_COST) {
			//b3.setEnabled(true);
		//}
		//else {
			//b3.setEnabled(false);		
		//}
	}
	void draw(Graphics g) {		
	}
	void bButtonPressed() {
		JOptionPane.showMessageDialog(null, "You equipped the Assult rifle");
		gp.c.buyWeapon(Character.RIFLE, RIFLE_COST);
	}
	void b1ButtonPressed() {
		JOptionPane.showMessageDialog(null, "You equipped the smg");
		gp.c.buyWeapon(Character.SMG, SMG_COST);
	}
	void b2ButtonPressed() {
		JOptionPane.showMessageDialog(null, "You equipped the sniper");
		gp.c.buyWeapon(Character.SNIPER, SNIPER_COST);
	}
	void b3ButtonPressed() {
		JOptionPane.showMessageDialog(null, "You equipped the ray gun");
		gp.c.buyWeapon(Character.RAYGUN, RAY_COST);
	}
	void b4ButtonPressed() {
		doneShopping = true;
	}
}
