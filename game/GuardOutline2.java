package game;

import java.awt.*;

public class GuardOutline2 extends Figure {

    public static class GuardOutline extends Figure {
        /**
         *
         * @author Vasil
         * @param  "това е визуализирането на outline на име GuardOutline което служи за обграждане на определената пешка във случия Guard2"
         */

        public GuardOutline(int row, int col) {
            this.row = row;
            this.col = col;

        }
        public void render(Graphics g) {

           // g.setColor(Color.GREEN);
            //g.fillOval(425, 445,  50, 50);
           // g.setColor(Color.GREEN);
           // g.fillOval(325, 445, 50, 50);
           // g.setColor(Color.GREEN);
           // g.fillOval(225, 445, 50, 50);
           // g.setColor(Color.GREEN);
            //g.fillOval(125, 445, 50, 50);
        }

    }
}