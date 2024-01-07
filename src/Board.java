import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;


public class Board extends JPanel implements KeyListener
{
   // public static ArrayList<ArrayList<ArrayList<Integer>>> everyPossibleMovesBoards = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
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

        currentShape = shapes[random.nextInt(shapes.length)];

        looper = new Timer(delay, new ActionListener() {//half second delay

            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint(); // redoes all of paintComponent
            }
        });
        looper.start(); // this will run action listener and will perform whats in actionPerformed but once every 500 milli so cant be faster
    }

    private void update() {
        if (state == STATE_GAME_PLAY){
            currentShape.update();
        }

    }



    public void setCurrentShape(){
        Rboard();
        ai.curentBoard = Rboard;

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

        invertBoard();

        System.out.println("height: "+findaggregateHeight()+"\nHoles: "+findHoles()+"\nsmooth: "+findSmooth()+"\ncomplete: "+findComplete()+"\nSCore: "+score());

        currentShape.reset();




        checkOverGame();
    }

    private void checkOverGame(){
        int[][] coords = currentShape.getCoords();
        for(int row = 0; row < coords.length;row++){
            for(int col =0; col < coords[0].length;col++){
                if (coords[row][col]!= 0){
                    if (board[row + currentShape.getY()] [col + currentShape.getX()] != null){
                        //System.out.println("game over");
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
            g.setColor(Color.red);
            Font stringFont = new Font( "SansSerif", Font.PLAIN, 70 );
            g.setFont(stringFont);
            g.setColor(Color.gray);
            g.drawString("GAME OVER", 10, 200);
            stringFont = new Font( "SansSerif", Font.PLAIN, 69 );
            g.setFont(stringFont);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 11, 200);




        }

        g.drawString(("Score: "+String.valueOf(score)), 320,200);


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
                setCurrentShape();
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



        for (int row = 0; row < board.length; row++){
            ArrayList<Integer> firstLayerData = new ArrayList<>();
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] != null){
                    firstLayerData.add(1);

                }else{
                    firstLayerData.add(0);
                }
            }
            Rboard.set(row, firstLayerData);


        }





        for (int row = 0; row < Rboard.size(); row++){
            System.out.println(Rboard.get(row));


        }
        System.out.println("------------------------------");


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
            System.out.println(Rboard.get(row));


        }
        System.out.println("------------------------------");


    }

    public void invertBoard(){

        temp.clear();


        for (int col = 0; col < Rboard.get(2).size(); col++){
            ArrayList<Integer> temp1d = new ArrayList<>();
            temp1d.clear();
            for(int row = Rboard.size()-1; row > -1;row--){
                temp1d.add(Rboard.get(row).get(col));
            }
            temp.add(temp1d);
        }

        for(int i =0; i < temp.size();i++){
            System.out.println(temp.get(i));
        }
    }

    public int findSmooth(){

        int smooth =0;

        stop:
        for(int line = 0; line < temp.size();line++){
            int height1 =0;
            int height2=0;

            for(int single=0; single < temp.get(2).size();single++){

                if(line+1==temp.size()){
                    break stop;
                }

                if(temp.get(line).get(single)==1){
                    height1 = single;
                }
                if(temp.get(line+1).get(single)==1){
                    height2 = single;
                }
            }
            smooth = smooth+(abs(height1-height2));

        }

        return smooth;

    }



    public int findHoles(){

        int holes = 0;


        for(int line = 0; line < temp.size();line++){
            int track =0;
            for(int single=0; single < temp.get(2).size();single++){


                if(temp.get(line).get(single)==1){
                    track = single;
                }



            }
            for(int find = 0; find < track;find++){
                if(temp.get(line).get(find)==0){
                    holes++;
                }
            }

        }



        return holes;

    }

    public int rateMove(int holes, int smooth, int aggregateHeight, int completedLines){
        int rating = 0;




        return rating;
    }


    public int findComplete(){
        int completedLines = 0;

        for(int single=0; single < temp.get(2).size();single++){
            int count =0;

            for(int line = 0; line < temp.size();line++){


                if (temp.get(line).get(single)==1){
                    count++;
                }


            }
            if(count == 10){
                completedLines++;
            }
        }
        return completedLines;
    }

    public double score(){
        double score = -0.510066*findaggregateHeight()+0.760666*findComplete()-0.35663*findHoles()-0.184483*findSmooth();
        return score;

    }





}
