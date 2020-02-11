import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

class Ball{
    //Initialize ball position and initial velocty
    int x = 500, y = 0, xa = 1, ya = 1;
    // Create ball size
    private static final int DIAMETER = 50;
    private Game game;

    public Ball(Game game) {
        this.game = game;
    }


    void moveBall(){
        // hits left wall
        if (x + xa < 0)
            game.gameOver();
        // hits right wall
        if (x + xa > game.getWidth() - DIAMETER)
            game.gameOver();
        // hits top wall
        if (y + ya < 0)
            ya = -game.speed;
        // Goes under paddle
        if (y + ya > game.getHeight() - DIAMETER)
            ya = -game.speed;

        if (y + ya <0)
            ya = game.speed;


        if (collision()) {
            xa *= -game.speed;
            x += 5;
            game.speed++;
            }
        if (collision2()) {
            xa *= -game.speed;
            x -= 5;
            game.speed++;
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
    private boolean collision2(){
        return game.paddle2.getBounds().intersects(getBounds());
    }



    public Rectangle getBounds(){
        return new Rectangle(x,y, DIAMETER, DIAMETER);
    }
}                                                                                                                                                                                                                   