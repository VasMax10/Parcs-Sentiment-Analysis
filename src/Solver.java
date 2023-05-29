import parcs.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Solver {

    public static void main(String[] args) throws Exception {

        task curtask = new task();
        curtask.addJarFile("SentimentAnalyzerParcs.jar");

        int nThreads = 2;

//        String text = textFromFile(curtask.findFile("Moby-Dick.txt"));
//        List<String> sentences = List.of(text.split("[.!?]"));

        List<String> sentences = bigTextFromFile(curtask.findFile("input_small_2.txt"));

        String positive = sentimentFromFile(curtask.findFile("positive_words2.txt"));
        String negative = sentimentFromFile(curtask.findFile("negative_words2.txt"));

        AMInfo info = new AMInfo(curtask, null);

        long tStart = System.nanoTime();

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        List<String> non_empty_sentences = new ArrayList<>();
        int count = 0;

        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                count++;
                non_empty_sentences.add(sentence);
            }
        }

        List<String> positive_words = List.of(positive.split(" "));
        List<String> negative_words = List.of(negative.split(" "));

        System.out.println("Number of sentences: " + count);

        List<List<String>> dividedLists = divideList(non_empty_sentences, nThreads);

        for (int i = 0; i < nThreads; i++) {

            point p = info.createPoint();
            points.add(p);

            channel c = p.createChannel();
            channels.add(c);

            System.out.println("Num of sentences in thread : " + dividedLists.get(i).size());

            Input input = new Input(dividedLists.get(i), positive_words, negative_words);

            points.get(i).execute("SentimentAnalyzerParcs");
            channels.get(i).write(input);

            System.out.println("Waiting for result .. ");
        }

        Integer posRes = 0;
        Integer negRes = 0;

        for (int i = 0; i < nThreads; i++) {
            Result result = (Result) (channels.get(i).readObject());

            Integer posCount = result.getPositiveCount();
            Integer negCount = result.getNegativeCount();

            posRes += posCount;
            negRes += negCount;

            System.out.println("Thread: positive - " + posCount + ", negative - " + negCount);
        }

        long tEnd = System.nanoTime();

        System.out.println("Total : positive - " + posRes + ", negative - " + negRes);

        if (posRes > negRes) {
            System.out.println("It seems that this text is Positive");
        } else if (negRes > posRes) {
            System.out.println("It seems that this text is Negative");
        } else {
            System.out.println("It seems that this text is Neutral");
        }

        System.out.println();

        System.out.println("Working time on " + nThreads + " processes: " + ((tEnd - tStart) / 1000000) + "ms");

        curtask.end();
    }

    public static String textFromFile(String filename) throws Exception {

        return new Scanner(new File(filename)).useDelimiter("\\Z").next();
    }

    public static List<String> bigTextFromFile(String filename) throws Exception {

        String pattern;
        List<String> res = new ArrayList<>();

        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()){
            pattern = sc.nextLine();
            res.addAll(List.of(pattern.split("[.!?]")));

        }
        return res;
    }


    public static String sentimentFromFile(String filename) throws Exception {
        String pattern;

        Scanner sc = new Scanner(new File(filename));

        pattern = sc.nextLine();

        return pattern;
    }

    public static List<List<String>> divideList(List<String> originalList, int numberOfDivisions) {
        List<List<String>> dividedLists = new ArrayList<>();

        int listSize = originalList.size();
        int sublistSize = listSize / numberOfDivisions;
        int mod = listSize % numberOfDivisions;


        int startIndex = 0;
        int endIndex = sublistSize;

        for (int i = 0; i < numberOfDivisions; i++) {
            if (i == numberOfDivisions - 1) {
                // Last sublist may have different size
                endIndex = listSize;
            }

            if (mod > 0) {
                endIndex++;
                mod--;
            }

            List<String> sublist = originalList.subList(startIndex, endIndex);
            dividedLists.add(sublist);

            startIndex = endIndex;
            endIndex += sublistSize;
        }

        return dividedLists;
    }

}