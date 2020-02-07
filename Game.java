import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Suppresses the warnings for "Serialization"
@SuppressWarnings("serial")
//The "Game" class gets extended by the JPanel
public class Game extends JPanel {
//The variablle "Ball" is made into a "this"
	Ball ball = new Ball(this);
//The variable "Paddle" is made into a "this"
	Paddle paddle = new Paddle(this);
//The variable "Paddle2" is made into a "this"
	Paddle2 paddle2 = new Paddle2(this);
//The variable "Speed"
	int speed = 1;

//	
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
				paddle2.keyReleased(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
				paddle2.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	private void move() {
		ball.moveBall();
		paddle.move();
		paddle2.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Chnages Background Color
		setBackground(Color.BLACK);
		ball.paint(g2d);
		paddle.paint(g2d);	
		paddle2.paint(g2d);

			// Score
			g2d.setColor(Color.GRAY);
			g2d.setFont(new Font("Verdana", Font.BOLD, 30));
			g2d.drawString(String.valueOf(getScore()), 10, 30);
			g2d.drawString(String.valueOf(getScore()), 905, 45);
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(this, "your score is:" + getScore(),"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	// returns the score
	private int getScore() {
		return speed - 1;
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Mini Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
}