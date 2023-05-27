import java.util.*;

import parcs.*;

public class SentimentAnalyzerParcs implements AM {
    @Override
    public void run(AMInfo amInfo) {

        Input input = (Input) amInfo.parent.readObject() ;

        List<String> negativeWords = input.getNegativeWords();
        List<String> positiveWords = input.getPositiveWords();
        List<String> sentences     = input.getSentences();

        amInfo.parent.write(analyzeAllSentiments(sentences, positiveWords, negativeWords));
    }

    public Result analyzeAllSentiments(List<String> sentences,
                                   List<String> positiveWords,
                                   List<String> negativeWords)
    {
        Result result = new Result();

        for (String sentence : sentences){
            analyzeSentiment(result, sentence, positiveWords, negativeWords);
        }

        return result;
    }

    public static void analyzeSentiment(Result result, String sentence, List<String> positiveWords, List<String> negativeWords) {
        int positiveCount = 0;
        int negativeCount = 0;

        // Convert the sentence to lowercase for case-insensitive matching
        sentence = sentence.toLowerCase();

        // Split the sentence into words
        String[] words = sentence.split("[ ,\"']");

        // Iterate through each word and check if it is a positive or negative word
        for (String word : words) {
            if (positiveWords.contains(word)) {
                positiveCount++;
            } else if (negativeWords.contains(word)) {
                negativeCount++;
            }
        }

        result.incTotalNegative(negativeCount);
        result.incTotalPositive(positiveCount);

    }
}
