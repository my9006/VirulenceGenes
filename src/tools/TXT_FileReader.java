package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TXT_FileReader {

    private ArrayList<String> textLines;
    private Stream<String> line;
    private LinkedList<String> genes;

    /*
     * Using the path reads the text file line by line and collect the info into
     * ArrayList<String>
     */
    public void readText(String path, String fileName) {


        try {
            line = Files.lines(Paths.get(path + "/"+ fileName));
            textLines = (ArrayList<String>) line.collect(Collectors.toList());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        genes = new LinkedList<>();
        for(int i = 0; i<textLines.size(); i++){
            String a = textLines.get(i).substring(textLines.get(i).indexOf("(")+1, textLines.get(i).indexOf(")"));
            genes.add(a);
        }
    }

    public LinkedList<String> getTextLines() {
        return genes;
    }

}
