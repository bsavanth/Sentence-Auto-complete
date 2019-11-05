import java.util.*;

public class Sentence_Prediction {

    public void Predict_Sentence(String input,Hashtable<String, Integer> bigrams,Hashtable<String, Double> Normalized_Bigrams)
    {

        input = input.toLowerCase();
        if(!bigrams.containsKey(input))
        {
            System.out.println("\n\nWord pair not found, Try sentence prediction with the word pair in our corpus\n\n");
            System.exit(0);
        }
        String splits[] = input.replaceAll("[^a-zA-Z]", " ").split(" ");
        String[] sent = new String[10];
        sent[0]=splits[0];
        sent[1]=splits[1];

        int prednum=1;


        while(prednum<9)
        {
            sent[prednum+1]=findword(sent[prednum], Normalized_Bigrams);
            prednum++;
        }

        System.out.println("\nPredicted sentence for '"+input+"' using Shannon algorithm is:\n\n");
        System.out.print("##-- ");

        for(int i=0;i<sent.length;i++)
        {
            System.out.print(sent[i]+" ");
        }
        System.out.print("--##");

        System.out.println("\n\n\n\n");



    }


    public static String findword(String word, Hashtable<String, Double> Normalized)
    {
        Set<String> nm = Normalized.keySet();
        double highest=0;int p=0;
        String finword = null;

        ArrayList<String> pool = new ArrayList<>();

        for(String s:nm)
        {
            if(s.startsWith(word+" ") && (highest<Normalized.get(s)))
            {


                highest=Normalized.get(s);
                finword=s;
                pool.add(finword);
            }

        }

        int listsize = pool.size();
        int result=0;
        Random r = new Random();
        int high = listsize-1;
        int low = 0;
        if(high!=low)
            result = r.nextInt(high - low) + low;
        else
            result = 0;
        finword = pool.get(result);
        String[] buff = finword.split(" ");
        return buff[1];



    }
}
