import parcs.*;

import java.util.List;

public class SentimentAnalyzerParcs implements AM {

    public Result AnalyzeSentiment(List<String> sentences, List<String> positiveWords, List<String> negativeWords) {

        Result result = new Result();

        int positiveCount = 0;
        int negativeCount = 0;

        for (String sentence : sentences){

            sentence = sentence.toLowerCase();

            String[] words = sentence.split("[ ,\"']");

            for (String word : words) {
                if (positiveWords.contains(word)) {
                    positiveCount++;
                } else if (negativeWords.contains(word)) {
                    negativeCount++;
                }
            }
        }

        result.setNegativeCount(negativeCount);
        result.setPositiveCount(positiveCount);

        return result;
    }

    public void run(AMInfo info) {

        Input input = (Input) info.parent.readObject();

        List<String> sentences = input.getSentences();
        List<String> positive = input.getPositive();
        List<String> negative = input.getNegative();

        System.out.println("Input : sentences count = " + sentences.size());

        Result result = AnalyzeSentiment(sentences, positive, negative);
        info.parent.write(result);

        System.out.println("The thread has ended job with result: ");
        System.out.println("Positive count = " + result.getPositiveCount() + ", negative count = " + result.getNegativeCount());
    }
}