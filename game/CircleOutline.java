package game;

import java.awt.*;

public class CircleOutline extends Figure {
    public Color color;

    public CircleOutline(int row, int col ,Color color) {
        this.color = color;
        this.row = row;
        this.col = col;
    }



    public void render(Graphics g) {

        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(this.color);
        g.fillOval(x+20, y+27, 60, 60);

    }

    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }



}
