import java.util.ArrayList;

public class constant {
    private ArrayList<ArrayList<Integer>> keep = new ArrayList<>();
    public constant(ArrayList<ArrayList<Integer>> Keep){
        this.keep = Keep;
    }

    public ArrayList<ArrayList<Integer>> getKeep(){
        return this.keep;
    }

}
