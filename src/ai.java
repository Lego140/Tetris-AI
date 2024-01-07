import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class ai {


    public static ArrayList<Double> ratingsForAllBoards = new ArrayList<>();

    public static ArrayList<ArrayList<Integer>> curentBoard = new ArrayList<>();


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


    public static boolean canPlaceShape(ArrayList<ArrayList<Integer>> shape,List<List<Integer>> currentBoard , int x, int y){

        if ((x + shape.get(0).size() > currentBoard.get(0).size()) || (y + shape.size() > currentBoard.size())||x<0||y<0) {
            return false;
        }



        for (int i = 0; i < shape.size(); i++) {
            for (int j = 0; j < shape.get(0).size(); j++) {
                if (shape.get(i).get(j) == 1) {
                    int boardRow = y + i;
                    int boardCol = x + j;


                    if (shape.get(i).get(j) == 1) {
                        // Check if the board cell is already filled (1).
                        //if (boardRow >= 0 && boardRow < curentBoard.size() && boardCol >= 0 && boardCol < curentBoard.get(1).size()) {
                        /*
                        if (curentBoard.get(boardRow).get(boardCol) == 1) {

                            return false; // Collision with existing piece.
                        }

                         */




                        if(currentBoard.get(boardRow).get(boardCol)!=0){
                            return false;
                        }





                        // Check if there is an adjacent filled cell directly beneath this cell.  && shape.get(i).get(j) != 1
                        // (boardRow + 1 >= 0 && boardRow + 1 < curentBoard.size()&& shape.get(i).get(j) != 1  && curentBoard.get(boardRow + 1).get(boardCol) == 0)
                        /*
                        if(boardRow + 1 >= 0 && boardRow + 1 < curentBoard.size()&& shape.get(i).get(j) != 1){
                            return false;
                        }

                         */
                    }

                }
            }
        }

        for (int i = 0; i < shape.size(); i++) {
            for (int j = 0; j < shape.get(0).size(); j++) {
                if((y+1+i)<currentBoard.size()){
                    if (currentBoard.get(y + 1+i).get(x+j) == 1) {
                        return true;
                    }
                }


            }
        }

        if((y+shape.size()==20 )&& y+shape.size()<=currentBoard.size()){
            return true;
        }





        /*

        for (int j = 0; j < shape.get(shape.size()-1).size(); j++) {

            if ((shape.get(shape.size()-1).get(j) == 1 )&& (curentBoard.get(y+1).get(x + j) == 0)) {
                return false; //checks if there is a collision or not
            }

        }





        for (int i = 0; i < shape.size(); i++) {
            for (int j = 0; j < shape.get(0).size(); j++) {
                if ((shape.get(i).get(j) == 1) && (curentBoard.get(y + i).get(x + j) == 1)) {
                    return false;
                }
            }
        }

         */


        return false;
    }



    public static List<List<Integer>> placeShape(ArrayList<ArrayList<Integer>> shape, List<List<Integer>> array, int x, int y) {





            // Copy the shape into the board at the specified position.
        for (int i = 0; i < shape.size(); i++) {
            for (int j = 0; j < shape.get(0).size(); j++) {
                if (shape.get(i).get(j) == 1) {
                    //temp.get(y + i).set(x + j, 2); //replaces the value at that x and y as 1 for placing the shape
                    array.get(y+i).set(x+j, 2);

                }
            }

            /*

            for(int row=0;row<temp.size();row++){

                System.out.println(temp.get(row));
            }
            System.out.println("---------------------------------");

             */

        }
        return array;





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
    public static void copyArray(ArrayList<ArrayList<Integer>> freshArray,ArrayList<ArrayList<Integer>> copyArray){
        freshArray.clear();
        for (int i = 0; i < copyArray.size(); i++) {
            freshArray.add(copyArray.get(i));
            //System.out.println(copyArray.get(i));
        }
    }

    public static List<List<Integer>> getBestBoard(ArrayList<ArrayList<Integer>> shape, List<List<Integer>> aftercurentBoard) {


        double bestScore = -5764767487593567898765678765678976547985789346634756778457543.3;
        ArrayList<Integer> X = new ArrayList<>();
        ArrayList<Integer> Y = new ArrayList<>();
        for (int y = 0; y < aftercurentBoard.size(); y++) {
            for (int x = 0; x < aftercurentBoard.get(y).size(); x++) {


                if (ai.canPlaceShape(shape, aftercurentBoard, x, y) == true) {
                    //double score = (findSmooth(placeShape(shape, aftercurentBoard, x, y))*-0.184483)+(findHoles(placeShape(shape,aftercurentBoard,x,y))*-0.35663)+(findaggregateHeight(placeShape(shape,aftercurentBoard,x,y))*-0.510066)+(findComplete(placeShape(shape,aftercurentBoard,x,y))*0.760666);
                    System.out.println("(" + x + ", " + y + "valid");
                    X.add(x);
                    Y.add(y);

                } else {
                    System.out.println("(" + x + ", " + y + ") not valid");
                }
            }
        }

        System.out.println(X+"\n"+Y);
        ArrayList<ArrayList<Integer>> best = new ArrayList<>();

        int Bestx =0;
        int Besty =0;
        //final ArrayList<ArrayList<Integer>> keep = aftercurentBoard;
        ArrayList<ArrayList<Integer>> placedBoard2 = new ArrayList<>();
        //copyArray(placedBoard1, placeShape(shape,aftercurentBoard,X.get(0),Y.get(0)));

        //copyArray(keep,aftercurentBoard);
        for(int x=0;x<X.size();x++){


            if (bestScore<rateBoard(placeShape(shape,aftercurentBoard,X.get(x),Y.get(x)))){

                best.clear();
                Bestx = X.get(x);
                Besty = Y.get(x);
                bestScore = rateBoard(placeShape(shape,aftercurentBoard,X.get(x),Y.get(x)));

            }
            for (int i=0;i<aftercurentBoard.size();i++){
                System.out.println(aftercurentBoard.get(i));
            }
            System.out.println("----------------------");


        }
        /*
        for (int i=0;i<aftercurentBoard.size();i++){
            System.out.println(aftercurentBoard.get(i));
        }

         */
        System.out.println(bestScore+"\nY: "+Besty+"\nX: "+Bestx);
        //System.out.println(placeShape(shape,curentBoard,Bestx,Besty));

        return placeShape(shape,aftercurentBoard,Bestx,Besty);
    }

    public static double rateBoard(final List<List<Integer>> board){
        double score = (findSmooth(board)*-0.184483)+(findaggregateHeight(board)*-0.510066)+(findHoles(board)*-0.35663)+(findComplete(board)*0.760666);
        return score;
    }

    public static ArrayList<ArrayList<Integer>> invertBoard(List<List<Integer>> temp){

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

    public static int findSmooth(List<List<Integer>> input){
        ArrayList<ArrayList<Integer>> evaluate;
        evaluate = invertBoard(input);
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


    public static int findaggregateHeight(List<List<Integer>> Board){
        int count = 0;

        for (int row = 0; row < Board.size(); row++){
            for (int col = 0; col < Board.get(row).size(); col++){
                if ((Board.get(row).get(col) == 1)||(Board.get(row).get(col) == 2)){
                    count++;
                }
            }
        }

        return count;
    }

    public static int findHoles( List<List<Integer>> Board){

        int holes = 0;


        for(int line = 0; line < Board.size();line++){
            int track =0;
            for(int single=0; single < Board.get(2).size();single++){


                if((Board.get(line).get(single)==1)||(Board.get(line).get(single)==2)){
                    track = single;
                }

            }
            for(int find = 0; find < track;find++){
                if(Board.get(line).get(find)==0){
                    holes++;
                }
            }

        }
        return holes;
    }


    public static int findComplete(List<List<Integer>> Board){
        int completedLines = 0;


        for(int line = 0; line < Board.size();line++){
            int count =0;
            for(int single=0; single < Board.get(line).size();single++){
                if (Board.get(line).get(single)!=0){
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
