import java.util.Hashtable;
import java.util.Iterator;

public class Normalize {

    public  Hashtable<String, Double>  Normal_Vals(String choice, Hashtable<String, Integer> unigrams, Hashtable<String, Integer> bigrams)

    {
        Hashtable<String, Double> Normalized_Bigrams = new Hashtable<>();
        Iterator<String> Normalize = bigrams.keySet().iterator();
        while (Normalize.hasNext()) {
            try {


                String key = Normalize.next();
                String tokenizer[] = key.split(" ");
                String token = tokenizer[0];
                double normal=0;
                if(choice.equals("smoothing")) {
                    normal = ((double) bigrams.get(key) + 1) / (unigrams.get(token) + unigrams.size());
                }
                else
                {
                    normal = (double) bigrams.get(key) / unigrams.get(token) ;
                }
                Normalized_Bigrams.put(key, normal);
            } catch (Exception e) {
                System.out.println("Exception occured for " + Normalize.next());
            }
        }

        return Normalized_Bigrams;


    }
}
