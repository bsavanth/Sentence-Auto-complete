import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException

    {
		
		System.out.println("\nI am using Shakespeare corpus\n");

        // Taking input for calculating  probability
        Scanner sc1 = new Scanner(System.in);
        System.out.println("\n\nEnter the sentence to calculate it's probability in our corpus:");
        String input1=sc1.nextLine().toLowerCase();

        // Taking input for predicting next most probable word in the corpus
        System.out.println("\n\nEnter the word-pair to predict next eight words:");
        String input2=sc1.nextLine().toLowerCase();


        String choice=null;
        System.out.print("\nHow do you want to do it?\nenter 1 for laplace smoothing and 2 for no-smoothing: ");
        int type = sc1.nextInt();
        if(type==1){
            choice = "smoothing"; }
        else if (type==2){
            choice = "no-smoothing";}
        else {
            System.out.println("select between 1 for laplace smoothing and 2 for regular normalizing ");
            System.exit(0);
        }
        sc1.close();

        Instant start = Instant.now(); // Starting run-time calculator

        // Array list to store tokens from corpus
        ArrayList<String> words = new ArrayList<>();

        //Calling this class to preprocess and retrieve tokens from the corpus
        Tokenize t = new Tokenize();
        words = t.Tokens_into_a_list();


        // Declaring hastables for storing unigrams and bigrams
        Hashtable<String, Integer> unigrams = new Hashtable<String, Integer>();
        Hashtable<String, Integer> bigrams = new Hashtable<String, Integer>();

        //Loop through the Array list for unigrams and bigrams
        for (int i = 0; i < words.size(); i++) {

            String first = words.get(i);
            Integer j;
            Integer k;
            j = unigrams.get(first);
            unigrams.put(first, (j == null) ? 1 : j + 1);

            // N unigrams and (N-1) bigrams, condition to avoid Index out of Bound error


            if (i < (words.size() - 1)) {
                String second = words.get(i + 1);
                String bigram = first + " " + second;
                k = bigrams.get(bigram);
                bigrams.put(bigram, (k == null) ? 1 : k + 1);


            }
        }


        // Calling this class to calculate the normalized values of all the bigrams

        Hashtable<String, Double> Normalized_Bigrams = new Hashtable<>();
        Normalize nz = new Normalize();
        Normalized_Bigrams = nz.Normal_Vals(choice, unigrams, bigrams);



        // Calling this class to find the probability of occurence of thi sentence in our corpus
        Sentence_Probability sprob = new Sentence_Probability();
        sprob.Sent_Prob(input1, choice, unigrams, bigrams, Normalized_Bigrams, words.size());



        // Calling this class for predicting next 8 words given a known bigram.
        Sentence_Prediction sp = new Sentence_Prediction();
        sp.Predict_Sentence(input2,bigrams, Normalized_Bigrams);



        Instant finish = Instant.now();

        double timeElapsed = Duration.between(start, finish).toMillis();  //in millis

        System.out.println("\n\nTotal runtime of both the tasks is : "+(timeElapsed/1000)+" seconds\n\n");


    }
}


