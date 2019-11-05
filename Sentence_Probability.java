import java.util.Hashtable;

public class Sentence_Probability {

    public void Sent_Prob(String input, String choice, Hashtable<String, Integer> unigrams, Hashtable<String, Integer> bigrams, Hashtable<String, Double> Normalized_Bigrams, int numwords )
    {

        input=input.toLowerCase();
        String[] newString = input.replaceAll("[^a-zA-Z]", " ").split(" ");
        double logprob = 0;

        for (int i = 0; i < newString.length - 1; i++) {
            String key = newString[i] + " " + newString[i + 1];
            if (Normalized_Bigrams.containsKey(key)) {

                logprob += Math.log(Normalized_Bigrams.get(key));
            }
            else
            {
                if(choice=="smoothing")
                {
                    if(unigrams.containsKey(newString[0]))
                    {
                        logprob+=( 1/(unigrams.get(newString[0])+unigrams.size() ) );
                    }
                    else
                    {
                        logprob+=Math.log((double) 1/unigrams.size());
                    }
                }
                else {
                    logprob += Math.log((double) 1 / numwords);
                }
            }
        }

        System.out.println("\n\nprobability of occurence of -> "+"'"+input+"' \n"+ "In our corpus is "+ Math.exp(logprob)+"\n\n");

    }
}
