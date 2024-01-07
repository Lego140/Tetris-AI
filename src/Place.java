import java.util.ArrayList;

public class Place {

    public ArrayList<ArrayList<Integer>> Board = new ArrayList<>();

    public Place(ArrayList<ArrayList<Integer>> Board){
        this.Board=Board;
    }

    public ArrayList<ArrayList<Integer>> placeShape(ArrayList<ArrayList<Integer>> shape, int x, int y){
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for (int i=0;i<this.Board.size();i++){
            temp.add(this.Board.get(i));
        }
        for (int i = 0; i < shape.size(); i++) {
            for (int j = 0; j < shape.get(0).size(); j++) {
                if (shape.get(i).get(j) == 1) {
                    temp.get(y + i).set(x + j, 2); //replaces the value at that x and y as 1 for placing the shape

                }
            }
        }
        return temp;
    }


}
