package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.cert.CertificateParsingException;
import java.util.ArrayList;


public class gameboard extends JFrame implements MouseListener {
    /**
     *
     * @author Vasil
     * @param "това е игралната дъска със координатите на съответните пешки и визуализирането на дъската "
     */
    public static final int TILE_SIDE_COUNT = 5;
    public static final int TILE_SIZE =100;

    private Object[][] cirleCollection1;
    private Object[][] leaderCollection;
    private Object selectedPiece;
    private Object selectedLeader;
    private Color orange = new Color(255, 165, 0);
    public static Color grey = new Color(128, 128, 128);
    /**
     *
     * @author Vasil
     * @param "това е импламентирането на вида фигури във игралното поле както беше показано във видеото на Господин Петров"
     */
    public gameboard() {
        this.cirleCollection1 = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        //Green
        this.cirleCollection1[0][1]=(new Guard(0, 0,Color.YELLOW,Color.GREEN));
        this.cirleCollection1[0][2]=(new Guard(0, 1,Color.YELLOW,Color.GREEN));
        this.cirleCollection1[0][3]=(new Guard(0, 2,Color.YELLOW,Color.GREEN));
        this.cirleCollection1[0][4]=(new Guard(0, 3,Color.YELLOW,Color.GREEN));
        //center
        this.cirleCollection1[2][2]=(new Guard(2, 2,Color.gray,Color.GRAY));
        //Yellow
        this.cirleCollection1[4][1]=(new Guard(4, 1,Color.GREEN,Color.YELLOW));
        this.cirleCollection1[4][2]=(new Guard(4, 2,Color.GREEN,Color.YELLOW));
        this.cirleCollection1[4][3]=(new Guard(4, 3,Color.GREEN,Color.YELLOW));
        this.cirleCollection1[4][4]=(new Guard(4, 4,Color.GREEN,Color.YELLOW));


        this.leaderCollection = new Object[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        this.leaderCollection[0][4]=(new Leader(0,4,Color.green));
        this.leaderCollection[4][0]=(new Leader(4,0,Color.yellow));
        this.setSize(525, 525);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        /**
         *
         * @author Vasil
         * @param "визуализиране на игралните пешки върху бойното поле чрез два for цикъла и повикване на точната им позиция чрез row,col"
         */

        super.paint(g);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                this.renderGameTile(g,row,col);

            }
        }

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                this.renderGamePiece(g,row,col);

            }
        }


    }
    /**
     *
     * @author Vasil
     * @param "преработеното от статично в динамично инициализиране на плочките на бойното поле "
     */
    private Color getTileColor(int row, int col) {
            if((row == 0 && (col == 4 || col==0)) || (row == 4 && (col==1 || col==3))) return orange;
            if((row==1 || row==3  )&& col!=2)return grey;
            if(col == 2 || row == 2) return Color.WHITE;

            {

        }

     return Color.BLACK;
    }

    //private Object getBoardOutline(int row, int col) {
       // return this.cirleOutLine[row][col];

    //}
    /**
     *
     * @author Vasil
     * @param "извикване на рендерирането на полето"
     */
    private void renderGameTile(Graphics g, int row, int col) {
        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
    }
    /**
     *
     * @author Vasil
     * @param "обекта кръг със съответния му ред и колона"
     */
    private Object getBoardCirle1(int row, int col) {
        return this.cirleCollection1[row][col];

    }

    /**
     *
     * @author Vasil
     * @param "проверка дали избрания кръг не е занулен"
     */
    private boolean hasBoardCirle(int row, int col) {
        return this.getBoardCirle1(row, col) != null;
    }

    /**
     *
     * @author Vasil
     * @param "обекта лидер на съответните му ред и колона"
     */
    private Object getBoardLeader(int row, int col) {
        return this.leaderCollection[row][col];

    }
    /**
     *
     * @author Vasil
     * @param "проверка дали лидера не е занулен "
     */
    private boolean hasBoardLeader(int row, int col) {
        return this.getBoardLeader(row, col) != null;
    }

    /**
     *
     * @author Vasil
     * @param "координатите спрямо големината на бойното поле"
     */
    private int getBoardDimensionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }

    /**
     *
     * @author Vasil
     * @param "рендерирането на вида фигурка Гард/Лидер"
     */
   private void renderGamePiece(Graphics g, int row, int col) {


        if(this.hasBoardCirle(row, col)) {

            Guard p0= (Guard) this.getBoardCirle1(row, col);
            p0.render(g);

        }
        if(this.hasBoardLeader(row,col)){
            Leader p1=(Leader) this.getBoardLeader(row,col);
            p1.render(g);
        }

    }


    /**
     *
     * @author Vasil
     * @param "Override на MouseEvent като използваме опцията Mouse clicked и взимаме съответните координати"
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimensionBasedOnCoordinates(e.getX());
        int y = this.getBoardDimensionBasedOnCoordinates(e.getY());
        int x = this.getBoardDimensionBasedOnCoordinates(e.getX());
/**
 *
 * @author Vasil
 * @param "проверка дали има възможност за ход и дали самия ход на лидера не вътре във предназначеното му поле "
 */
        if (this.selectedLeader != null) {
            Leader p1 = (Leader) this.selectedLeader;
            p1.move(row, col);
            this.selectedLeader = null;
            if (row < 5 && col < 5) {
                this.repaint();
            } else {
                System.out.println("Избери друга позиция");

            }
        } else if (this.selectedPiece != null) {
            Guard p = (Guard) this.selectedPiece;
            p.move(row, col);
            this.selectedPiece = null;
            if (row < 5 && col < 5) {
                this.repaint();
            } else {
                System.out.println("Изберете друга позиция");
                mouseClicked(e);
            }
            /**
             *
             * @author Vasil
             * @param "проверка за движение с два фор цикъла "
             */


                   if(cirleCollection1[row][col] !=leaderCollection[row][col]){
                       this.repaint();
                       if(cirleCollection1[row][col] !=cirleCollection1[row][col]) {
                           this.repaint();
                       }
                       if(selectedLeader != null) {
                           ((Leader) this.selectedLeader).isMoveValid(col,row);
                           this.repaint();
                       }
                       if(selectedPiece != null) {
                           ((Guard) this.selectedPiece).isMoveValid(col,row);
                           this.repaint();
                       }

                       if(leaderCollection[row][col] != leaderCollection[row][col]){
                           this.repaint();
                       }

           }
        }
/**
 *
 * @author Vasil
 * @param "проверка дали линията и колоната имат лидер или гард"
 */
        if(this.hasBoardCirle(row, col)) {
            this.selectedPiece = this.getBoardCirle1(row, col);
        }
        if(this.hasBoardLeader(row,col)){
            this.selectedLeader = this.getBoardLeader(row, col);
        }


    }



    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}