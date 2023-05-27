import parcs.*;

import java.util.List;

public class SentimentAnalyzerParcs implements AM {

    public Result AnalyzeSentiment(List<String> sentences, List<String> positive, List<String> negative) {

        Result result = new Result();

        int count = sentences.size();

        return result;
    }

    public void run(AMInfo info) {
        Input input = (Input) info.parent.readObject();

        List<String> sentences = input.getSentences();
        List<String> positive = input.getPositive();
        List<String> negative = input.getNegative();

        System.out.println("Input : text = " + sentences.get(0) + ", positive = " + positive + ", negative = " + negative);

        info.parent.write(AnalyzeSentiment(sentences, positive, negative));
    }
}

//
//
//
//import java.util.*;
//
//import parcs.*;
//
//public class SentimentAnalyzerParcs {
//    public void run(AMInfo info) {
//
//        System.out.println("Start");
//
//        Input input = (Input) (info.parent.readObject());
//
//        System.out.println("Input : text = " + input.getText() + ", negative = " + input.getNegative() + ", positive = " + input.getPositive());
//
//        info.parent.write(input);
//    }
//}
////
////public class SentimentAnalyzerParcs implements AM {
////    @Override
////    public void run(AMInfo amInfo) {
////
////        System.out.println("Started");
////
////        Input input = (Input) amInfo.parent.readObject() ;
////
//////        String input = (String) amInfo.parent.readObject();
//////        int input = amInfo.parent.readInt();
////        System.out.println("Got Input");
////
//        List<String> negativeWords = new ArrayList<>(input.getNegativeWords());
//        System.out.println("Negative");
//////        List<String> positiveWords = new ArrayList<>(input.getPositiveWords());
//////        System.out.println("Positive");
////        List<String> sentences     = new ArrayList<>(input.getSentences());
////        System.out.println("Sentences");
////
////        for (String sentence: sentences){
////            System.out.println(sentence);
////        }
////        Result result = new Result();
////        amInfo.parent.write(result);
//////        amInfo.parent.write(analyzeAllSentiments(sentences, positiveWords, negativeWords));
////    }
////
////    public Result analyzeAllSentiments(List<String> sentences,
////                                   List<String> positiveWords,
////                                   List<String> negativeWords)
////    {
////        Result result = new Result();
////
////        for (String sentence : sentences){
////            analyzeSentiment(result, sentence, positiveWords, negativeWords);
////        }
////
////        return result;
////    }
////
////    public static void analyzeSentiment(Result result, String sentence, List<String> positiveWords, List<String> negativeWords) {
////        int positiveCount = 0;
////        int negativeCount = 0;
////
////        // Convert the sentence to lowercase for case-insensitive matching
////        sentence = sentence.toLowerCase();
////
////        // Split the sentence into words
////        String[] words = sentence.split("[ ,\"']");
////
////        // Iterate through each word and check if it is a positive or negative word
////        for (String word : words) {
////            if (positiveWords.contains(word)) {
////                positiveCount++;
////            } else if (negativeWords.contains(word)) {
////                negativeCount++;
////            }
////        }
////
////        result.incTotalNegative(negativeCount);
////        result.incTotalPositive(positiveCount);
////
////    }
////}
