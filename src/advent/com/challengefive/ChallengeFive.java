package advent.com.challengefive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//TO:DO this can be written reading a list of strings and then write a method that can be used to map a value as part of a stream and collect to an immutable list of vents.
public class ChallengeFive {

    public static List<Vent> readVentDataFromFile(String fileValues, String seperatorPattern) {
        BufferedReader bufReader;
        List<Vent> listOfVents = new ArrayList<>();
        {
            try {
                bufReader = new BufferedReader(new FileReader(fileValues));
                String line = bufReader.readLine();
                while (line != null) {
                    String[] firstSplit = line.split(seperatorPattern);
                    String x1y1 = firstSplit[0].substring(0,3);
                    String x2y2 = firstSplit[1].substring(2,5);
                    Vent vent = new Vent(x1y1.split(","),x2y2.split(","));
                    listOfVents.add(vent);
                    line = bufReader.readLine();
                }
                bufReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return listOfVents;
    }
}
