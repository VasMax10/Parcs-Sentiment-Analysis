


import java.util.*;

import parcs.*;

public class SentimentAnalyzerParcs {
    public void run(AMInfo info) {

        String text = (String) info.parent.readObject();
        String negative = (String) info.parent.readObject();
        String positive = (String) info.parent.readObject();

        System.out.println("Input : text = " + text + ", negative = " + negative + ", positive = " + positive);

        info.parent.write(text);
    }
}
//
//public class SentimentAnalyzerParcs implements AM {
//    @Override
//    public void run(AMInfo amInfo) {
//
//        System.out.println("Started");
//
//        Input input = (Input) amInfo.parent.readObject() ;
//
////        String input = (String) amInfo.parent.readObject();
////        int input = amInfo.parent.readInt();
//        System.out.println("Got Input");
//
////        List<String> negativeWords = new ArrayList<>(input.getNegativeWords());
////        System.out.println("Negative");
////        List<String> positiveWords = new ArrayList<>(input.getPositiveWords());
////        System.out.println("Positive");
//        List<String> sentences     = new ArrayList<>(input.getSentences());
//        System.out.println("Sentences");
//
//        for (String sentence: sentences){
//            System.out.println(sentence);
//        }
//        Result result = new Result();
//        amInfo.parent.write(result);
////        amInfo.parent.write(analyzeAllSentiments(sentences, positiveWords, negativeWords));
//    }
//
//    public Result analyzeAllSentiments(List<String> sentences,
//                                   List<String> positiveWords,
//                                   List<String> negativeWords)
//    {
//        Result result = new Result();
//
//        for (String sentence : sentences){
//            analyzeSentiment(result, sentence, positiveWords, negativeWords);
//        }
//
//        return result;
//    }
//
//    public static void analyzeSentiment(Result result, String sentence, List<String> positiveWords, List<String> negativeWords) {
//        int positiveCount = 0;
//        int negativeCount = 0;
//
//        // Convert the sentence to lowercase for case-insensitive matching
//        sentence = sentence.toLowerCase();
//
//        // Split the sentence into words
//        String[] words = sentence.split("[ ,\"']");
//
//        // Iterate through each word and check if it is a positive or negative word
//        for (String word : words) {
//            if (positiveWords.contains(word)) {
//                positiveCount++;
//            } else if (negativeWords.contains(word)) {
//                negativeCount++;
//            }
//        }
//
//        result.incTotalNegative(negativeCount);
//        result.incTotalPositive(positiveCount);
//
//    }
//}
