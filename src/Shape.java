import java.awt.*;
import java.io.IOException;

public class Shape {
    private int x = 4, y = 0;  // x is row y is colum
    private int normal = 600;
    private int fast = 50;
    private int delayTimeForMovement = normal;
    private long beginTime;
    private int deltaX = 0;
    private Board board;
    private Color color;
    private int[][] coords; //cords
    public Shape(int[][] coords, Board board, Color color){
        this.coords = coords;
        this.board = board;
        this.color = color;
    }
    private boolean collision = false;

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void reset(){
        this.x = 4;
        this.y = 0;
        collision = false;
    }
    public void update() throws IOException {


        if(collision) {
            //fill the color for board in the shapes shape
            for(int row = 0; row < coords.length;row++){
                for (int col =0; col< coords[0].length;col++){
                    if(coords[row][col]!= 0){
                        board.getBoard()[y+row][x+col] = color;
                    }
                }
            }
            checkLine();
                //set curretn shape
            board.setCurrentShape();


            return;
        }

        boolean moveX = true;
        if (!(x + deltaX + coords[0].length > 10) && !(x+deltaX < 0)){
            for (int row = 0; row< coords.length;row++){
                for (int col = 0; col < coords[row].length; col++){
                    if (coords[row][col] != 0) {
                        if (board.getBoard()[y + row][x + deltaX + col] != null) {
                            moveX = false;
                        }
                    }
                }
            }
            if(moveX){
                x += deltaX;
            }
        }

        deltaX = 0;

        if (System.currentTimeMillis()-beginTime > delayTimeForMovement){
            // verticle movement
            if (!(y +1+ coords.length > Board.BOARD_HEIGHT)){ // stops the shape so it doesnt go below the board
                for (int row = 0; row< coords.length;row++){
                    for (int col = 0; col < coords[row].length; col++){
                        if (coords[row][col] != 0){
                            if (board.getBoard()[y +1+ row][x + deltaX +col] != null){
                                collision = true;

                            }
                        }
                    }
                }
                if (!collision){
                    y++;
                }

            }else {
                collision = true;
            }

            //add gravity by having y go down to the bottom
            beginTime = System.currentTimeMillis();
        }

    }
    private void checkLine (){
        int streak = 0;
        int bottonLine = board.getBoard().length - 1;// hvae to minus 1 because its on the last row
        for(int topLine = board.getBoard().length - 1; topLine > 0; topLine --){
            int count = 0;
            for(int col = 0; col < board.getBoard()[0].length; col++){
                if (board.getBoard()[topLine][col] != null){
                    count++;

                }
                board.getBoard()[bottonLine][col] = board.getBoard()[topLine][col];

            }

            if (count < board.getBoard()[0].length) {
                bottonLine--; // move bottom line to top 1 unit

            }
            if (count == 10){
                streak = streak + 1;
            }

        }


        if(streak == 1){
            Board.score = Board.score + 100;
        } else if (streak == 2) {
            Board.score = Board.score + 300;
        }   else if (streak == 3) {
            Board.score = Board.score + 500;
        } else if (streak == 4) {
            Board.score = Board.score + 800;
        }

        streak = 0;

    }

    public void rotate(){
        int[][] rotatedShape = transposeMatrix(coords);

        reverseRows(rotatedShape);
        // check for right side and bottom if out of bounds
        if((x +rotatedShape[0].length > Board.BOARD_WIDTH) || (y + rotatedShape.length > 20)){
            return;
        }

        //check for collision with other shapes before able to rotate
        for(int row = 0; row < rotatedShape.length; row++){
            for(int col = 0; col < rotatedShape[0].length;col++){
                if (rotatedShape[row][col] != 0 ){
                    if((board.getBoard()[y + row][x +col]) != null){
                        return;
                    }
                }
            }
        }
        coords =  rotatedShape;
    }
    private int[][] transposeMatrix(int[][] matrix){
        int [][] temp = new int[matrix[0].length][matrix.length]; // makes the array flip so instead of row col its col row so from 2x3 it turns to 3x2 grid
        for(int row = 0; row < matrix.length;row++){
            for(int col = 0; col < matrix[0].length; col++){
                temp[col][row] = matrix[row][col]; // makes the array flip so instead of row col its col row so from 2x3 it turns to 3x2 grid
                // {1,1,1} L shape turns to ---->   {1,1}  since this isnt a 90 degree turn we have to reverse the rows to make it that so we need the
                // {1,0,0}                          {1,0}   reversRows() function
                //                                  {1,0}
            }
        }
        return temp;
    }
    private void reverseRows(int[][] matrix){
        int middle = matrix.length /2;
        for( int row = 0; row <middle;row++){
            int[] temp = matrix[row];
            matrix [row] = matrix[matrix.length -row -1];
            matrix[matrix.length - row - 1] = temp;
        }
    }

    public void render(Graphics g){
        //draw shape T
        for (int row=0;row<coords.length;row++){
            for (int col=0;col<coords[0].length;col++){
                if(coords[row][col]!= 0){
                    g.setColor(color);
                    g.fillRect(col* Board.BLOCK_SIZE + x* Board.BLOCK_SIZE,row* Board.BLOCK_SIZE+ y* Board.BLOCK_SIZE,Board.BLOCK_SIZE,Board.BLOCK_SIZE);
                }
            }


        }
    }
    public int[][] getCoords(){
        return coords;
    }
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }
    public void speedUp(){
        delayTimeForMovement = fast;
    }


    public void speedDown(){
        delayTimeForMovement = normal;
    }
    public void moveRight(){
        deltaX = 1;
    }
    public void moveLeft(){
        deltaX = -1;
    }


}
