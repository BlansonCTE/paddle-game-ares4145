import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class Ball{
    //Initialize ball position and initial velocty
    int x = 0, y = 0, xa = 1, ya = 1;
    // Create ball size
    private static final int DIAMETER = 30;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }


    void moveBall(){
        // hits left wall
        if (x + xa < 0)
            xa = game.speed;
        // hits right wall
        if (x + xa > game.getWidth() - DIAMETER)
            xa = -game.speed;
        // hits top wall
        if (y + ya < 0)
            ya = game.speed;
        if (y + ya > game.getHeight()-DIAMETER)
            game.gameOver();
        if (collision()) {
            ya = -game.speed;
            y = game.paddle.getTopY() - DIAMETER;
            game.speed++;
            // Goes under paddle
        
            }

        // Move ball
        x = x + xa;
        y = y + ya;
    }

    public void paint(Graphics2D g){
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    private boolean collision(){
        return game.paddle.getBounds().intersects(getBounds());
    }


    public Rectangle getBounds(){
        return new Rectangle(x,y, DIAMETER, DIAMETER);
    }
}