	import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

	public class Zombs {
		JFrame frame;
		GamePanel gp1;
		public static final int HEIGHT = 800;
		public static final int WIDTH = 800;

		Zombs() {
			frame = new JFrame();
			gp1 = new GamePanel();
			
		}

		void setup() {
			frame.add(gp1);
			frame.setSize(WIDTH, HEIGHT);
			frame.addKeyListener(gp1);
			frame.addMouseMotionListener(gp1);
			frame.addMouseListener(gp1);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setCursor(frame.getToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB), new Point(), null));
			frame.setVisible(true);
		}

		public static void main(String[] args) {
			new Zombs().setup();

		}
		
		public static synchronized void playSound(final String url) {
			playSound(url, 0);
		}
		
		public static synchronized void playSound(final String url, final float gainAmount) {
			  new Thread(new Runnable() {
			  // The wrapper thread is unnecessary, unless it blocks on the
			  // Clip finishing; see comments.
			    public void run() {
			      try {
			        Clip clip = AudioSystem.getClip();
			        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
			          this.getClass().getResource("/" + url));
			        clip.open(inputStream);
			        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			        volume.setValue(gainAmount);
			        clip.start(); 
			      } catch (Exception e) {
			        System.err.println(e.getMessage());
			      }
			    }
			  }).start();
			}
	}
