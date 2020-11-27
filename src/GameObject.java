
import java.awt.Rectangle;

public class GameObject {
	 Rectangle collisionBox;
	 float x;
	 float y;
	 int width;
	 int height;
	 float speed = 0;
	 boolean isActive = true;
	 int hp;
	 GameObject(float x, float y, int width, int height) {
		 this.x = x;
		 this.y = y;
		 this.width = width;
		 this.height = height;
		collisionBox = new Rectangle((int)x, (int)y, width, height);
	 }
	 void update() {
		 collisionBox.setBounds((int)x-width/2, (int)y-height/2, width, height);                 
	 }
	 
	 boolean outOfBounds() {
		 if (x < 0 || x > Zombs.WIDTH || y < 0 || y > Zombs.HEIGHT)
			 return true;
		 else
			 return false;
	 }
	 
}
