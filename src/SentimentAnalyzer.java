import java.util.List;

public class SentimentAnalyzer {
//    public static boolean analyzeSentencesSentiment(String[] sentences){
//
//        for (String sentence : sentences){
//            analyzeSentiment(sentence);
//        }
//        return true;
//    }

    public static String analyzeSentiment(String sentence, List<String> positiveWords, List<String> negativeWords) {
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

        // Determine the sentiment based on the counts
        System.out.println(positiveCount + " " + negativeCount);
        if (positiveCount > negativeCount) {
            return "Positive";
        } else if (negativeCount > positiveCount) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
}
