

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel implements Runnable {

	private static final long serialVersionUID = 2677239111702505350L;
	
	private JFrame frame;
	
	private int angle = 0;
	
	private int red = 255;
	
	private int blue = 255;
	
	private int green = 255;
	
	private Random random;
	
	private String message = "蜗爱橙子";
	
	private int display = 1;
	
	private int luoxuan;
	
	public Test() {frame = new JFrame();
		frame.setVisible(true);
		setVisible(true);
		frame.add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("魔力宝贝");// TODO Auto-generated constructor stub
		frame.setSize(800, 600);
		setSize(800, 600);
		
		random = new Random();
		
		new Thread(this, "test").start();
	}
	
	public static void main(String[] args) {
		new Test();
	}

	@Override
	public void run() {
		while (true) { // 无条件循环
			try {
				long time = System.currentTimeMillis(); // 获得更新前时间（毫秒）
				
				if (angle < 360) {
					angle += 10;
					red--;
					blue--;
					green--;
				} else {
					angle = 0;
					red = 255;
					blue = 255;
					green = 255;
				}
				if (luoxuan < 360) {
					luoxuan++;
				} else {
					luoxuan = 0;
				}
				repaint();
				
				time = System.currentTimeMillis() - time; // 计算更新耗时（毫秒）
				
				if (time < 25) { // 更新耗时不足更新间隔时
					synchronized (this) {
						wait(25 - time); // 当前循环等待，时间为补足更新间隔
					}
				} else { // 更新耗时达到或超过更新间隔时
					Thread.yield();
				}
			} catch (Exception e) {
				e.printStackTrace();; // 输出异常
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);

//		g.setClip(0, 0, getWidth(), getHeight());
//		g.draw3DRect(20, 20, 100, 100, false);
//		g.drawOval(100, 100, 100, 200);
//		g.setColor(new Color(red, green, 255, 255));
//		g.fillArc(50, 50, 200, 200, 0, angle);
//		g.fillArc(50, 50, 200, 200, 0, -angle);
		for (int i = 0;i < 10;i++) {
			int angle = ((this.angle - i * 10) + 360) % 360;
			Image image = new BufferedImage(30, 30, BufferedImage.TYPE_4BYTE_ABGR_PRE);
			image.getGraphics().setColor(new Color(255, 255, 255, 100));
			image.getGraphics().fillArc(-15, -15, 60, 60, 90, -angle);
			g.drawImage(image, 100 + i * 40, 100, null);
			g.setColor(Color.RED);
			g.drawString(((360 - angle) / 10) + "", 108 + i * 40, 120);
			g.drawString("99", 108 + i * 40, 100 - angle / 10);
		}
		g.setColor(new Color(255, 255, 255, 100));
		g.drawArc(275, 275, 50, 50, angle, 45);
		g.drawArc(275, 275, 50, 50, angle + 180, 45);
		
		for (int i = 0;i < 10;i++) {
			int angle = this.angle - i * 10;
			g.drawArc(300 - angle / 2, 300 - angle / 2, angle, angle, 0, 360);
		}
		
		g.drawRect(100, 500, 360, 20);
		g.fillRect(100, 500, angle, 20);
		g.setColor(Color.BLUE);
		g.drawString(angle * 100 / 360 + "%", 280, 515);
		
		g.drawArc(500 - luoxuan / 2, 400 - luoxuan / 10, luoxuan, 20, luoxuan, 180);
		
		for (int i = 0;i < 10;i++) {
			g.setColor(new Color(255 - i * 20, 0 + 20 * i, 255 - i * 20, 255));
			g.fillArc(600 - (360 - angle) / 2, 300 - angle / 2, 360 - angle, angle, angle / 4 + i * 3, 180 - angle / 2 - i * 6);
		}
		
//		g.fillArc(100, 100, 100, 100, 0, angle);
//		g.fillArc(100, 100, 100, 100, 90, -angle);
//		
//		for (int i = 0;i < 1000;i++) {
//			int x = random.nextInt(800);
//			int y = random.nextInt(600);
//			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255), 255));
//			g.drawRect(x, y, 1, 1);
//		}
//		
//		if (display < message.length()) {
//			display++;
//		} else {
//			display = 1;
//		}
//		g.setColor(Color.RED);
//		g.setFont(new Font("Courier", Font.PLAIN, 30));
//		g.drawString(message.substring(display - 1, display), 400, 300);
	}

}
