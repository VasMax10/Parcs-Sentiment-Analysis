import  java.io.*;
import java.util.*;

import parcs.*;

public class Solver implements AM{

    public static void main(String[] args){
        task curtask = new task();

        curtask.addJarFile("SentimentAnalyzerParcs.jar");

        System.out.println("The Solver class method 'main' has added jar file 'SentimentAnalyzerParcs.jar'");

        (new Solver()).run(new AMInfo(curtask, (channel) null));

        System.out.println("The Solver class method 'main' has finished its work");
        curtask.end();
    }

    @Override
    public void run(AMInfo amInfo) {

        int nThreads;

        StringBuilder full_text = new StringBuilder();

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(amInfo.curtask.findFile("input_small.txt")));

            nThreads = Integer.parseInt(in.readLine());
            String line;

            while ((line = in.readLine()) != null) {
                full_text.append(line);
            }
        }
        catch (IOException e)
        {
            System.out.print("Reading input data error\n");
            e.printStackTrace();
            return;
        }

        List<String> positiveWords = readListFromFile(amInfo, "positive_words.txt");
        List<String> negativeWords = readListFromFile(amInfo,"negative_words.txt");

        List<String> sentences = List.of(full_text.toString().split("[.!?]"));
        List<String> non_empty_sentences = new ArrayList<>();
        int count = 0;

        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                count++;
                non_empty_sentences.add(sentence);
            }
        }

        System.out.println("Number of sentences: " + count);

        List<List<String>> dividedLists = divideList(non_empty_sentences, nThreads);

        List<point> points = new ArrayList<point>();
        List<channel> channels = new ArrayList<channel>();

        // Connection to points

        // Time counting
        long tStart = System.nanoTime();

        int totalNegative = 0;
        int totalPositive = 0;

        for(int i = 0; i < nThreads; i++)
        {
            System.out.println(i);

            point p = amInfo.createPoint();
            channel c = p.createChannel();

            points.add(p);
            channels.add(c);

            Input input = new Input(positiveWords, negativeWords, dividedLists.get(i));

            System.out.println(positiveWords.get(0));
            System.out.println(negativeWords.get(0));
            System.out.println(dividedLists.get(i).get(0));

            p.execute("SentimentAnalyzerParcs");
            c.write(123);

            System.out.println("Waiting for the result...");

            Result result = (Result) (c.readObject());

            totalPositive += result.getTotalPositive();
            totalNegative += result.getTotalNegative();

        }

        long tEnd = System.nanoTime();

        if (totalPositive > totalNegative) {
            System.out.println("Positive");
        } else if (totalNegative > totalPositive) {
            System.out.println("Negative");
        } else {
            System.out.println("Neutral");
        }

        System.out.println("Working time on " + nThreads + " processes: " + ((tEnd - tStart) / 1000000) + "ms");
    }

    public static List<List<String>> divideList(List<String> originalList, int numberOfDivisions) {
        List<List<String>> dividedLists = new ArrayList<>();

        int listSize = originalList.size();
        int sublistSize = listSize / numberOfDivisions;
        int mod = listSize % numberOfDivisions;

        System.out.println("mod = " + mod);

        int startIndex = 0;
        int endIndex = sublistSize;

        for (int i = 0; i < numberOfDivisions; i++) {
            if (i == numberOfDivisions - 1) {
                // Last sublist may have different size
                endIndex = listSize;
            }

            if (mod > 0){
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

    public static List<String> readListFromFile(AMInfo amInfo, String filePath){

        List<String> lines = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(new FileReader(amInfo.curtask.findFile(filePath)));
            String line;

            while ((line = in.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Reading sentiment words error\n");
        }
        return lines;
    }
}
