import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ai {


    public ArrayList<Double> ratingsForAllBoards = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> curentBoard = new ArrayList<>();

    public static int [][]testBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 0}};


    public static int[][] curentShape;

    public int[][] allCoords;

    public static ArrayList<Integer> X = new ArrayList<>();
    public static ArrayList<Integer> Y = new ArrayList<>();



    public static void test(){

    }

    public static void findBestMove(){

    }
    public void generateCoords(){
        for(int x =0 ;x<curentBoard.size();x++){
            for(int y=0; y< curentBoard.get(x).size();y++){
                //allCoords[x][y] = y,x;
            }
        }
    }
    public static void startTest(int [][] curentShape, int [][] testBoard){

        /*
        int rotates =0;

        switch (shapeName){
            case "T": rotates =3;break;
            case "L": rotates = 3;break;
            case "Z": rotates=1; break;
            case "S": rotates=1; break;
            case "I": rotates=1; break;
            default: rotates=0;break;
        }

         */

        printArray(getBestBoard(curentShape,testBoard));

    }


    public static boolean canPlaceShape(int[][] shape,int [][]currentBoard , int x, int y){

        if ((x + shape[0].length > currentBoard[0].length) || (y + shape.length > currentBoard.length)||x<0||y<0) {
            return false;
        }
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    int boardRow = y + i;
                    int boardCol = x + j;
                    if (shape[i][j] == 1) {
                        if(currentBoard[boardRow][boardCol]!=0){
                            return false;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if((y+1+i)<currentBoard.length){
                    if (currentBoard[y + 1+i][x+j] == 1) {
                        return true;
                    }
                }
            }
        }

        if((y+shape.length==20 )&& y+shape.length<=currentBoard.length){
            return true;
        }

        return false;
    }

    public static void printArray(int [][] array){
        for(int x=0;x<array.length;x++){
            ArrayList<Integer> firstLayer = new ArrayList<>();
            for(int y =0; y<array[x].length;y++){
                System.out.print(array[x][y]+" ");
            }
            System.out.println("         ");
        }
    }


    public static int [][] placeShape(int[][] shape, int [][] array, int x, int y) {



            // Copy the shape into the board at the specified position.
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    //temp.get(y + i).set(x + j, 2); //replaces the value at that x and y as 1 for placing the shape
                    //array.get(y+i).set(x+j, 2);
                    array[(y+i)][(x+j)] =2;

                }
            }
            //printArray(testBoard);
           // printArray(array);
            //System.out.println("---------------------------------");

        }
        return array;

    }

    public static void removeShape(int[][] shape, int [][] array, int x, int y){
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] == 1) {
                    //temp.get(y + i).set(x + j, 2); //replaces the value at that x and y as 1 for placing the shape
                    //array.get(y+i).set(x+j, 2);
                    array[(y+i)][(x+j)] =0;

                }
            }
            //printArray(testBoard);
            // printArray(array);
            //System.out.println("---------------------------------");

        }
    }




    /*
    public static void addTestBoards(){
        everyPossibleMovesBoards.add(Board.Rboard);
    }

     */



   // public void rateAllBoardsAndSaveScore(){

        /*


        for (int i =0; i< everyPossibleMovesBoards.size();i++ ){


            for(int y =0;y< everyPossibleMovesBoards.get(i).size();y++){
                System.out.println("\n"+everyPossibleMovesBoards.get(i).get(y));
            }


            double score = -0.510066*findaggregateHeight(everyPossibleMovesBoards.get(i))+0.760666*findComplete(everyPossibleMovesBoards.get(i))-0.35663*findHoles(everyPossibleMovesBoards.get(i))-0.184483*findSmooth(everyPossibleMovesBoards.get(i));
            ratingsForAllBoards.add(score);
            System.out.println(score);

        }

         */
    //}
    public void copyArray(ArrayList<ArrayList<Integer>> freshArray,ArrayList<ArrayList<Integer>> copyArray){
        freshArray.clear();
        for (int i = 0; i < copyArray.size(); i++) {
            freshArray.add(copyArray.get(i));
            //System.out.println(copyArray.get(i));
        }
    }

    public static int[][] rotate(int[][] shape){
        int[][] rotatedShape = transposeMatrix(shape);

        return reverseRows(rotatedShape);
    }
    private static int[][] transposeMatrix(int[][] matrix){
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
    private static int[][] reverseRows(int[][] matrix){
        int middle = matrix.length /2;
        for( int row = 0; row <middle;row++){
            int[] temp = matrix[row];
            matrix [row] = matrix[matrix.length -row -1];
            matrix[matrix.length - row - 1] = temp;
        }
        return matrix;
    }

    public static int[][] getBestBoard(int[][] shape, int[][] aftercurent) {


        double bestScore = -5764767487593567898765678765678976547985789346634756778457543.3;
        ArrayList<Integer> X = new ArrayList<>();
        ArrayList<Integer> Y = new ArrayList<>();

        for (int y = 0; y < aftercurent.length; y++) {
            for (int x = 0; x < aftercurent[0].length; x++) {


                if (canPlaceShape(shape, aftercurent, x, y) == true) {
                    //double score = (findSmooth(placeShape(shape, aftercurentBoard, x, y))*-0.184483)+(findHoles(placeShape(shape,aftercurentBoard,x,y))*-0.35663)+(findaggregateHeight(placeShape(shape,aftercurentBoard,x,y))*-0.510066)+(findComplete(placeShape(shape,aftercurentBoard,x,y))*0.760666);
                    //System.out.println("(" + x + ", " + y + "valid");
                    X.add(x);
                    Y.add(y);

                } else {
                    //System.out.println("(" + x + ", " + y + ") not valid");
                }
            }
        }


        //System.out.println(X+"\n"+Y);
        //ArrayList<ArrayList<Integer>> best = new ArrayList<>();

        int Bestx =0;
        int Besty =0;
        //final ArrayList<ArrayList<Integer>> keep = aftercurentBoard;
        //ArrayList<ArrayList<Integer>> placedBoard2 = new ArrayList<>();
        //copyArray(placedBoard1, placeShape(shape,aftercurentBoard,X.get(0),Y.get(0)));

        //copyArray(keep,aftercurentBoard);
        //printArray(testBoard);
        //int [][]save = testBoard;
        for(int x=0;x<X.size();x++){


            if (bestScore<rateBoard(placeShape(shape,aftercurent,X.get(x),Y.get(x)))){
                //removeShape(shape,aftercurent,X.get(x),Y.get(x));
                //best.clear();
                Bestx = X.get(x);
                Besty = Y.get(x);
                bestScore = rateBoard(placeShape(shape,aftercurent,Bestx,Besty));
                //removeShape(shape,aftercurent,X.get(x),Y.get(x));
            }



            //printArray(placeShape(shape,aftercurent,X.get(x),Y.get(x)));
            //System.out.println(rateBoard(placeShape(shape,aftercurent,X.get(x),Y.get(x))));
            removeShape(shape,aftercurent,X.get(x),Y.get(x));

            /*
            for (int i=0;i<aftercurent.size();i++){
                System.out.println(testBoard);
            }

             */
            //System.out.println("----------------------");


        }
        /*
        for (int i=0;i<aftercurentBoard.size();i++){
            System.out.println(aftercurentBoard.get(i));
        }

         */
        //System.out.println(bestScore+"\nY: "+Besty+"\nX: "+Bestx);
        //System.out.println(placeShape(shape,curentBoard,Bestx,Besty));
        //System.out.println("-------------------------------------");
        //System.out.println(bestScore);
        return placeShape(shape,aftercurent,Bestx,Besty);
    }






    public static double rateBoard(int [][]testBoard){

        double score = (findSmooth(testBoard)*-0.184483)+(findaggregateHeight(testBoard)*-0.510066)+(findHoles(testBoard)*-0.35663)+(findComplete(testBoard)*0.760666);
        //double score = (findSmooth(board)*-2.184483)+(findaggregateHeight(board)*-0.510066)+(findHoles(board)*-3.35663)+(findComplete(board)*10.760666);
        return score;
    }

    public static ArrayList<ArrayList<Integer>> invertBoard(ArrayList<ArrayList<Integer>> temp){

        ArrayList<ArrayList<Integer>> invert = new ArrayList<>();
        for (int col = 0; col < temp.get(2).size(); col++){
            ArrayList<Integer> temp1d = new ArrayList<>();
            temp1d.clear();
            for(int row = temp.size()-1; row > -1;row--){
                temp1d.add(temp.get(row).get(col));
            }
            invert.add(temp1d);
        }
        /*
        for(int i =0; i < invert.size();i++){
            System.out.println(invert.get(i));
        }

         */

        return invert;
    }

    public static  int findSmooth(int [][] input1){

        ArrayList<ArrayList<Integer>> evaluate = new ArrayList<>();
        for (int row=0; row<input1.length;row++){
            ArrayList<Integer> firstLayer = new ArrayList<>();
            for (int col=0; col< input1[0].length;col++){
                firstLayer.add(input1[row][col]);
            }
            evaluate.add(firstLayer);
            //System.out.println(firstLayer);
        }

        evaluate = invertBoard(evaluate);
        int smooth =0;

        stop:
        for(int line = 0; line < evaluate.size();line++){
            int height1 =0;
            int height2=0;

            for(int single=0; single < evaluate.get(line).size();single++){

                if(line+1>=evaluate.size()){
                    break stop;
                }
                if(evaluate.get(line).get(single)!=0){
                    height1++;
                }
                if(evaluate.get(line+1).get(single)!=0){
                    height2++;
                }
            }
            smooth = smooth+(abs(height1-height2));
        }
        return smooth;
    }


    public static  int findaggregateHeight(int[][] Board){

        int count = 0;

        for (int row = 0; row < Board.length; row++){
            for (int col = 0; col < Board[0].length; col++){
                if ((Board[row][col] == 1)||(Board[row][col] == 2)){
                    count++;
                }
            }
        }

        return count;
    }

    public static  int findHoles( int[][]input1){

        ArrayList<ArrayList<Integer>> Board = new ArrayList<>();
        for (int row=0; row<input1.length;row++){
            ArrayList<Integer> firstLayer = new ArrayList<>();
            for (int col=0; col< input1[0].length;col++){
                firstLayer.add(input1[row][col]);
            }
            Board.add(firstLayer);
            //System.out.println(firstLayer);
        }


        int holes = 0;

        for(int line = 0; line < Board.get(0).size();line++){
            int track =0;
            for(int row=Board.size()-1; row >= 0;row--){
                if((Board.get(row).get(line)==1)||(Board.get(row).get(line)==2)){
                    track = row;

                }


            }

            //System.out.println("col: "+line+"\nrow: "+track);

            if(track!=0) {
                for (int find = Board.size()-1; find >= track; find--) {
                    if (Board.get(find).get(line) == 0) {
                        holes++;
                    }
                }
            }

        }
        return holes;
    }


    public static  int findComplete(int[][]Board){
        int completedLines = 0;


        for(int line = 0; line < Board.length;line++){
            int count =0;
            for(int single=0; single < Board[0].length;single++){
                if (Board[line][single]!=0){
                    count++;
                }
            }
            if(count == 10){
                completedLines++;
            }
        }
        return completedLines;
    }






}
