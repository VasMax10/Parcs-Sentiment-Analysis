import parcs.*;

public class SentimentAnalyzerParcs implements AM {
    static int NO_OF_CHARS = 256;

    //A utility function to get maximum of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static void badCharHeuristic( String pattern, int size,int badchar[])
    {
        int i;

        // Initialize all occurrences as -1
        for (i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;

        // Fill the actual value of last occurrence
        // of a character
        for (i = 0; i < size; i++)
            badchar[(int) pattern.charAt(i)] = i;
    }

    // A pattern searching function that uses Bad Character Heuristic of Boyer Moore Algorithm
    public Result search(String text, String pattern) {
        Result result = new Result();
        int m = pattern.length();
        int n = text.length();

        int badchar[] = new int[NO_OF_CHARS];

      /* Fill the bad character array by calling
         the preprocessing function badCharHeuristic()
         for given pattern */
        badCharHeuristic(pattern, m, badchar);

        int s = 0;  // s is shift of the pattern with respect to text
        while (s <= (n - m)) {
            int j = m - 1;

            //reducing index of pattern while matching at this shift s */
            while (j >= 0 && pattern.charAt(j) == text.charAt(s + j) )
                j--;

            // the pattern is present -> j = -1
            if (j < 0) {
                result.addIndex(s);
                // shift to last occurrence of the character
                s += (s + m < n) ? m - badchar[text.charAt(s + m)] : 1;

            } else
                s += max(1, j - badchar[text.charAt(s + j)]);
        }

        return result;
    }

    public void run(AMInfo info) {
        Input input = (Input) info.parent.readObject();
        String text = input.getText();
        String positive = input.getPositive();

//        String negative = input.getNegative();

        System.out.println("Input : text = " + text + ", positive = " + positive);

        info.parent.write(search(text, positive));
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
//////        List<String> negativeWords = new ArrayList<>(input.getNegativeWords());
//////        System.out.println("Negative");
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
