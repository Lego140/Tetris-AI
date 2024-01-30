import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class testMain {
    public static ArrayList<ArrayList<Integer>> testShape = new ArrayList<>();


    /*
        public static final int [][]testBoard = {
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
     */

    public static int [][]pestBoard = {
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
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 1, 1, 1, 0, 0},
            {1, 1, 1, 0, 0, 1, 1, 0, 0, 0}};

    public static void main(String[] args) {

        /*
        ArrayList<ArrayList<Integer>> testShape = new ArrayList<>();
        for (int row=0; row<pestBoard.length;row++){
            ArrayList<Integer> firstLayer = new ArrayList<>();
            for (int col=0; col< pestBoard[0].length;col++){
                firstLayer.add(pestBoard[row][col]);
            }
            testShape.add(firstLayer);
            System.out.println(firstLayer);
        }

        System.out.println(findHoles(testShape));

         */

        int [][]curentShape = {
                {0,1,0},
                {1, 1, 1}};
        /*


        for(int row=0;row<curentShape.length;row++){
            ArrayList<Integer> firstLayer = new ArrayList<>();
            for(int col=0; col<curentShape[row].length;col++){
                firstLayer.add(curentShape[row][col]);
            }
            testShape.add(firstLayer);


        }


        for(int x=0;x<testBoard.length;x++){
            ArrayList<Integer> firstLayer = new ArrayList<>();
            for(int y =0; y<testBoard[x].length;y++){
                firstLayer.add(testBoard[x][y]);
            }
            ai.curentBoard.add(firstLayer);


        }
        */




        /*
        List<List<Integer>> okBoard = new ArrayList<>();
        for(int x=0;x<testBoard.length;x++){
            List<Integer> firstLayer = new ArrayList<>();
            for(int y =0; y<testBoard[x].length;y++){
                firstLayer.add(testBoard[x][y]);
            }
            okBoard.add(firstLayer);


        }

         */

        //ai.findPossibleMoves(testShape);
        /*
        for(int i =0; i< ai.X.size();i++){
            System.out.println(ai.X.get(i)+ai.Y.get(i));
            System.out.println(ai.canPlaceShape(testShape, okBoard,ai.X.get(i), ai.Y.get(i)));
        }


        //List<List<Integer>> unmodifiable2DArrayList = Collections.unmodifiableList();

        //List<List<Integer>> kool = Collections.unmodifiableList(okBoard);

        //constant keep = new constant(okBoard);
        //List<List<Integer>> best = ai.getBestBoard(testShape,kool);
        for (int r=0;r<best.size();r++){
            System.out.println(best.get(r));

        }




        for(int y=0;y<ai.curentBoard.size();y++){
            for(int x =0; x<ai.curentBoard.get(y).size();x++){

                ai.getBestBoard(testShape,okBoard);
                for (int i =0; i<ai.placeShape(testShape,okBoard,x,y).size();i++){
                    System.out.println(ai.placeShape(testShape,okBoard,x,y).get(i));
                }



                if(ai.canPlaceShape(testShape,okBoard,x,y)==true){
                    System.out.println("("+x+", "+y+") valid");
                }else {
                    System.out.println("("+x+", "+y+") not valid");
                }





                //ai.placeShape(testShape,okBoard,x,y);



                //ai.placeShape(testShape,x,y);


            }

        }
        */



        // ai.test();
        //System.out.println(pestBoard);
        //ai.startTest(curentShape, ai.testBoard, "T");


    }


    public static  int findHoles( ArrayList<ArrayList<Integer>> Board){
        int holes = 0;

        for(int line = 0; line < Board.get(0).size();line++){
            int track =0;
            for(int row=Board.size()-1; row >= 0;row--){
                if((Board.get(row).get(line)==1)||(Board.get(row).get(line)==2)){
                    track = row;

                }


            }

            System.out.println("col: "+line+"\nrow: "+track);

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
}
