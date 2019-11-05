import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tokenize extends Main{


    public  ArrayList<String> Tokens_into_a_list() throws IOException
    {
        ArrayList<String> stopwordList = new ArrayList<>();


        stopwordList.add("b");
        stopwordList.add("c");
        stopwordList.add("d");
        stopwordList.add("e");
        stopwordList.add("f");
        stopwordList.add("g");
        stopwordList.add("h");
        stopwordList.add("j");
        stopwordList.add("k");
        stopwordList.add("l");
        stopwordList.add("m");
        stopwordList.add("n");
        stopwordList.add("o");
        stopwordList.add("p");
        stopwordList.add("q");
        stopwordList.add("r");
        stopwordList.add("s");
        stopwordList.add("t");
        stopwordList.add("u");
        stopwordList.add("v");
        stopwordList.add("w");
        stopwordList.add("x");
        stopwordList.add("y");
        stopwordList.add("z");
        stopwordList.add("ll");
        stopwordList.add("isn");
        stopwordList.add(" ");
        stopwordList.add("");
        stopwordList.add("th");
        //initializing a Array List to store words
        ArrayList<String> words = new ArrayList<String>();

        //reference to input stream reader
        BufferedReader reader= null;

        //Error handling reading input through try,catch

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {


                // Regex for removing all numbers/digits
                line = line.replaceAll("[^a-zA-Z ]", " ").toLowerCase();

                // Storing all the tokens of a single line of input temporarily(line by line iterations)

                String[] midwords = line.split("\\s+");
                for (int i = 0; i < midwords.length; i++) {
                    if (stopwordList.contains(midwords[i]))
                    {
                        continue;
                    }
                    words.add(midwords[i]);

                }

            }
        }


        catch(Exception e)
        {
            // Printing Excess information about error
            e.printStackTrace();
            System.out.println("\nfile not found in the input folder\n");
            System.out.println("\nInput File should be named as input.txt and moved to input folder before running");
        }

        finally

        {
            // Always close the reader after use
            reader.close();
        }
        return words;

    }
}
