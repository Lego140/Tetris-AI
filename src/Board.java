import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;


public class Board extends JPanel implements KeyListener
{
   // public static ArrayList<ArrayList<ArrayList<Integer>>> everyPossibleMovesBoards = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
    public int input;

    public int [][] present = new int[20][10];
    public static int STATE_GAME_PLAY = 0;
    public static int STATE_GAME_Pause = 1;
    public static int STATE_GAME_OVER = 2;
    public static int score = 0;

    public static ArrayList<ArrayList<Integer>> Rboard = new ArrayList<>();

    public static int aggregateHeight = 0;

    private int state = STATE_GAME_PLAY;
    public static int FPS = 60;
    public static int delay = FPS/1000; //gets delay time for every frame in milliseconds
    public static final int BOARD_WIDTH = 10; // 10 BLOCKS of 30x30
    public static final int BOARD_HEIGHT = 20; // 20 BLOCKS of 30x30
    public static final int BLOCK_SIZE = 30; //every block in the board is 30 x 30 pixels
    public static String shapeName;
    private Random random;


    private Timer looper;
    private Color[][] board = new Color[BOARD_HEIGHT][BOARD_WIDTH];

    private Shape[] shapes = new Shape[7];

    private Color[] colors = {Color.decode("#ed1c24"), Color.decode("#ff7f27"), Color.decode("#fff200"),
            Color.decode("#22b14c"), Color.decode("#00a2e8"), Color.decode("#a349a4"), Color.decode("#3f48cc")};
    private Shape currentShape;
    public Board(){
        random = new Random();



        // create shapes
        shapes[0] = new Shape(new int[][]{
                {1, 1, 1, 1}, // I shape;

        }, this, colors[0]);

        shapes[1] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 1, 0}, // T shape;
        }, this, colors[1]);

        shapes[2] = new Shape(new int[][]{
                {1, 1, 1},
                {1, 0, 0}, // L shape;
        }, this, colors[2]);

        shapes[3] = new Shape(new int[][]{
                {1, 1, 1},
                {0, 0, 1}, // J shape;
        }, this, colors[3]);

        shapes[4] = new Shape(new int[][]{
                {0, 1, 1},
                {1, 1, 0}, // S shape;
        }, this, colors[4]);

        shapes[5] = new Shape(new int[][]{
                {1, 1, 0},
                {0, 1, 1}, // Z shape;
        }, this, colors[5]);

        shapes[6] = new Shape(new int[][]{
                {1, 1},
                {1, 1}, // O shape;
        }, this, colors[6]);

        input = random.nextInt(shapes.length);
        currentShape = shapes[input];








        looper = new Timer(delay, new ActionListener() {//half second delay

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    update();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                repaint(); // redoes all of paintComponent
            }
        });
        looper.start(); // this will run action listener and will perform whats in actionPerformed but once every 500 milli so cant be faster
    }

    private void update() throws IOException {
        if (state == STATE_GAME_PLAY){
            currentShape.update();

            /*
            Rboard();
            int [][]inputBoard = new int[Rboard.size()][Rboard.get(0).size()];
            for (int row =0; row<Rboard.size();row++){
                for (int col = 0; col<Rboard.get(0).size();col++){
                    inputBoard[row][col] = Rboard.get(row).get(col);
                }
            }
            ai.startTest(inputShape, inputBoard);

             */



        }

    }



    public void setCurrentShape() throws IOException {
        Rboard();
        //ai.curentBoard = Rboard;

        int ramdomShape = random.nextInt(shapes.length);


        currentShape = shapes[ramdomShape];
        switch (ramdomShape) {
            case 0:
                ai.curentShape = new int[][]{
                        {1, 1, 1, 1},};
                break; //l shape
            case 1:
                ai.curentShape = new int[][]{
                        {1, 1, 1},
                        {0, 1, 0}};
                break; // T shape
            case 2:
                ai.curentShape = new int[][]{
                        {1, 1, 1},
                        {1, 0, 0}}; // L shape
                break;
            case 3: ai.curentShape = new int[][]{
                    {1, 1, 1},
                    {0, 0, 1}}; //J shape
                break;
            case 4: ai.curentShape = new int[][]{
                    {0, 1, 1},
                    {1, 1, 0}}; //S shape
                break;
            case 5: ai.curentShape = new int[][]{
                    {1, 1, 0},
                    {0, 1, 1}}; //Z shape
                break;
            case 6: ai.curentShape = new int[][]{
                    {1, 1},
                    {1, 1}}; // O shape;
                break;
        }

        //invertBoard();

        //System.out.println("height: "+findaggregateHeight()+"\nHoles: "+findHoles()+"\nsmooth: "+findSmooth()+"\ncomplete: "+findComplete()+"\nSCore: "+score());

        currentShape.reset();

        int [][]inputBoard = new int[Rboard.size()][Rboard.get(0).size()];
        for (int row =0; row<Rboard.size();row++){
            for (int col = 0; col<Rboard.get(0).size();col++){
                inputBoard[row][col] = Rboard.get(row).get(col);
            }
        }

        //System.out.println(ramdomShape);

        for (int row =0; row<Rboard.size();row++){
            for (int col = 0; col<Rboard.get(0).size();col++){
                present[row][col] = 0;
            }
        }

        switch (ramdomShape){
            case 0: int[][] inputShape = new int[][]{{1, 1, 1, 1}};        present = ai.getBestBoard(inputShape,inputBoard,"I"); break;
            case 1: int[][] inputShape1 = new int[][]{{1, 1, 1}, {0, 1, 0}};present = ai.getBestBoard(inputShape1,inputBoard,"T");break;
            case 2: int[][] inputShape2 = new int[][]{{1, 1, 1}, {1, 0, 0}}; present = ai.getBestBoard(inputShape2,inputBoard,"L");break;
            case 3: int[][] inputShape3 = new int[][]{{1, 1, 1}, {0, 0, 1}}; present = ai.getBestBoard(inputShape3,inputBoard,"J");break;
            case 4: int[][] inputShape4 = new int[][]{{0, 1, 1}, {1, 1, 0}};   present = ai.getBestBoard(inputShape4,inputBoard,"S");break;
            case 5: int[][] inputShape5 = new int[][]{{1, 1, 0}, {0, 1, 1}};  present = ai.getBestBoard(inputShape5,inputBoard,"Z");break;
            case 6: int[][] inputShape6 = new int[][]{{1, 1}, {1, 1}};    present = ai.getBestBoard(inputShape6,inputBoard,"O");break;
        }

        /*
                switch (ramdomShape){
            case 0: int[][] inputShape = new int[][]{{1, 1, 1, 1}};        ai.startTest(inputShape, inputBoard); present = ai.getBestBoard(inputShape,inputBoard); break;
            case 1: int[][] inputShape1 = new int[][]{{1, 1, 1}, {0, 1, 0}};        ai.startTest(inputShape1, inputBoard);present = ai.getBestBoard(inputShape1,inputBoard);break;
            case 2: int[][] inputShape2 = new int[][]{{1, 1, 1}, {1, 0, 0}};        ai.startTest(inputShape2, inputBoard);present = ai.getBestBoard(inputShape2,inputBoard);break;
            case 3: int[][] inputShape3 = new int[][]{{1, 1, 1}, {0, 0, 1}};        ai.startTest(inputShape3, inputBoard);present = ai.getBestBoard(inputShape3,inputBoard);break;
            case 4: int[][] inputShape4 = new int[][]{{0, 1, 1}, {1, 1, 0}};        ai.startTest(inputShape4, inputBoard);present = ai.getBestBoard(inputShape4,inputBoard);break;
            case 5: int[][] inputShape5 = new int[][]{{1, 1, 0}, {0, 1, 1}};        ai.startTest(inputShape5, inputBoard);present = ai.getBestBoard(inputShape5,inputBoard);break;
            case 6: int[][] inputShape6 = new int[][]{{1, 1}, {1, 1}};        ai.startTest(inputShape6, inputBoard);present = ai.getBestBoard(inputShape6,inputBoard);break;
        }

         */


       // ai.startTest(inputShape, inputBoard);


        checkOverGame();
    }

    private void checkOverGame() throws IOException {
        int[][] coords = currentShape.getCoords();
        for(int row = 0; row < coords.length;row++){
            for(int col =0; col < coords[0].length;col++){
                if (coords[row][col]!= 0){
                    if (board[row + currentShape.getY()] [col + currentShape.getX()] != null){
                        //System.out.println("game over");
                        HighScore.writeScore(score);
                        state = STATE_GAME_OVER;
                        /*
                        for(int i =0;i<everyPossibleMovesBoards.size();i++){
                            for(int x=0;x<everyPossibleMovesBoards.get(i).size();x++){
                                System.out.println("\n"+everyPossibleMovesBoards.get(i).get(x));
                            }
                            System.out.println("\n-------------------------------------------");
                        }


                         */

                        //System.out.println(ai.getScore());



                    }
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g)
    {

        super.paintComponent(g); // makes g do painting
        g.setColor(Color.BLACK); //make what ever is next black
        g.fillRect(0,0,getWidth(),getHeight()); // fills whole gui with black

        currentShape.render(g);


        //draws pieces on to the board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] != null){
                    g.setColor(board[row][col]);
                    g.fillRect(col* BLOCK_SIZE, row*BLOCK_SIZE, BLOCK_SIZE,BLOCK_SIZE);
                }
            }
        }



        //draws Board
        g.setColor(Color.WHITE);
        for (int row = 0; row< BOARD_HEIGHT+1; row++){ //draws the row lines
            g.drawLine(0,BLOCK_SIZE*row,BLOCK_SIZE * BOARD_WIDTH,row*BLOCK_SIZE);
        }
        for (int col = 0; col< BOARD_WIDTH+1; col++){ //draws the colum lines
            g.drawLine(col*BLOCK_SIZE,0,col*BLOCK_SIZE,BLOCK_SIZE*BOARD_HEIGHT);
        }
        if(state == STATE_GAME_OVER){

            Font stringFont = new Font( "SansSerif", Font.PLAIN, 69 );
            g.setFont(stringFont);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 11, 200);




        }
        Font stringFont1 = new Font( "SansSerif", Font.PLAIN, 25 );
        g.setFont(stringFont1);
        g.setColor(Color.white);
        g.drawString("Controls",320,20);
        g.fillRect(320, 25, 40,40);
        g.fillRect(320, 75, 40,40);
        g.fillRect(320, 125, 80,40);

        Font stringFont2 = new Font( "SansSerif", Font.BOLD, 15 );
        g.setFont(stringFont2);
        g.setColor(Color.black);
        g.drawString("A",335,50);
        g.drawString("D",335,100);
        g.drawString("SPACE",335,150);

        g.setColor(Color.white);
        g.drawString("Move Left",370,50);
        g.drawString("Move Right",370,100);
        g.drawString("Fast Drop",410,150);

        Font stringFont = new Font( "SansSerif", Font.PLAIN, 15 );
        g.setFont(stringFont);
        g.setColor(Color.white);
        g.drawString(("Score: "+String.valueOf(score)), 320,210);
        g.drawString(("Best Move"), 320,237);
        g.drawString(("Holes: "+ai.findHoles(present)), 320,555);
        g.drawString(("Height: "+ai.findaggregateHeight(present)), 320,570);
        g.drawString(("Smoothness: "+ai.findSmooth(present)), 320,585);
        g.drawString(("Completed Lines: "+ai.findComplete(present)), 320,600);





        try {
            g.drawString(("High Score: "+String.valueOf(HighScore.findHighScore())), 320,225);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }



        g.setColor(Color.BLACK); //make what ever is next black
        //g.fillRect(0,0,getWidth(),getHeight()); // fills whole gui with black


        int BLOCK_SIZE2 = 15; //every block in the board is 30 x 30 pixels


        //draws pieces on to the board
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){


                if (present[row][col] != 0){
                    if (present[row][col]==1){
                        g.setColor(Color.BLUE);
                    }
                    if (present[row][col]==2){
                        g.setColor(Color.YELLOW);
                    }

                    g.fillRect((col* BLOCK_SIZE2)+320, (row*BLOCK_SIZE2)+240, BLOCK_SIZE2,BLOCK_SIZE2);
                }

                /*
                if (present[row][col]==0){
                    g.setColor(Color.BLACK);
                    g.fillRect((col* BLOCK_SIZE2)+320, (row*BLOCK_SIZE2)+240, BLOCK_SIZE2,BLOCK_SIZE2);
                }

                 */




            }
        }



        //draws Board
        g.setColor(Color.WHITE);
        for (int row = 0; row< BOARD_HEIGHT+1; row++){ //draws the row lines
            g.drawLine(320,(BLOCK_SIZE2*row)+240,(BLOCK_SIZE2 * BOARD_WIDTH)+320,(row*BLOCK_SIZE2)+240);
        }
        for (int col = 0; col< BOARD_WIDTH+1; col++){ //draws the colum lines
            g.drawLine((col*BLOCK_SIZE2)+320,240,(col*BLOCK_SIZE2)+320,(BLOCK_SIZE2*BOARD_HEIGHT)+240);
        }

    }
    public Color[][] getBoard(){
        return board; // gets the current color of that board on that cordinate
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyChar() == KeyEvent.VK_SPACE){
            currentShape.speedUp();
        } else if(e.getKeyChar() == 100){ // 100 is acii for d
            currentShape.moveRight();
        } else if(e.getKeyChar() == 97){ //97 is ascii for a
            currentShape.moveLeft();
        } else if(e.getKeyChar() == 119){ //119 is ascii for w
            currentShape.rotate();
        }

        //clean board
        if(state == STATE_GAME_OVER){
            if (e.getKeyCode() == KeyEvent.VK_SPACE){
                for (int row = 0; row < board.length; row++){
                    for (int col = 0; col < board[row].length; col++){
                        board[row][col] = null;
                    }
                }
                try {
                    setCurrentShape();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                score = 0;
                state = STATE_GAME_PLAY;
            }

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE){
            currentShape.speedDown();
        }

    }

    public int findaggregateHeight(){


        int count = 0;



        for (int row = 0; row < Rboard.size(); row++){
            for (int col = 0; col < Rboard.get(row).size(); col++){
                if (Rboard.get(row).get(col) == 1){
                    count++;
                }
            }
        }


        return count;


    }


    public void Rboard(){
        Rboard.clear();
        for (int row = 0; row < board.length; row++){
            ArrayList<Integer> firstLayerData = new ArrayList<>();
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] != null){
                    firstLayerData.add(1);

                }else{
                    firstLayerData.add(0);
                }
            }
            Rboard.add(firstLayerData);
        }

        for (int row = 0; row < Rboard.size(); row++){
            //System.out.println(Rboard.get(row));
        }
        //System.out.println("------------------------------");

    }



    public static void startRboard(){



        for (int row = 0; row < 20; row++){
            ArrayList<Integer> firstLayerData = new ArrayList<>();
            for (int col = 0; col < 10; col++){
                firstLayerData.add(0);
            }
            Rboard.add(firstLayerData);


        }





        for (int row = 0; row < Rboard.size(); row++){
            //System.out.println(Rboard.get(row));


        }
        //System.out.println("------------------------------");


    }


}
