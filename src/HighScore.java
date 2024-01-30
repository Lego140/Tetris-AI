import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HighScore {


    public static void writeScore(int score) throws IOException {


        String filePath  = System.getProperty("user.dir").replace("\\", "\\\\");
        filePath = filePath+"\\\\src\\\\Scores.csv";

        // create a new file object
        File csvFile = new File(filePath);


        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvFile, true));
        //            String result = pfoodsArray.get(r).toString().replaceAll("[\\[\\]]", "");
        //            csvWriter.write(pfoodsArray.get(i).get(o)+",");
        //String result = pfoodsArray.get(0).toString().replaceAll("[\\[\\]]", "");
        // add data to the CSV file on the next line

        String s=Integer.toString(score);
        csvWriter.write(s);
        csvWriter.newLine();



        csvWriter.close();

    }

    public static int findHighScore() throws FileNotFoundException {
        String filePath = System.getProperty("user.dir").replace("\\", "\\\\");
        filePath = filePath + "\\\\src\\\\Scores.csv";

        //notification.generateExpList();
        FileReader file = new FileReader(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Integer> scores = new ArrayList<>();

        while (sc.hasNextLine()) {

            scores.add(Integer.valueOf(sc.nextLine()));
        }
        int high = 0;
        for (int i=0;i<scores.size();i++){
            if (high<scores.get(i)){
                high = scores.get(i);
            }
        }
        return high;

    }


}
