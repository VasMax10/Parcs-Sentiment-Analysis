import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static List<String> positiveWords = readListFromFile("out/positive_words.txt");
    static List<String> negativeWords = readListFromFile("out/negative_words.txt");

    public static void main(String[] args) {

        String filePath = "out/input2.txt";
        StringBuilder full_text = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                full_text.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Time counting
        long tStart = System.nanoTime();

        String[] sentences = full_text.toString().split("[.!?]");
        List<String> non_empty_sentences = new ArrayList<>();
        int count = 0;

        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                count++;
                non_empty_sentences.add(sentence);
            }
        }

        // Divide the list into smaller sublists
        List<List<String>> dividedLists = divideList(non_empty_sentences, 3);

        int i = 0;
        for (List<String> list : dividedLists){
            System.out.println("\tList № : " + i);
            for (String item: list){
                System.out.println("\t\tItem : " + item);
            }
        }

        System.out.println("Number of sentences: " + count);
        long tEnd = System.nanoTime();

        System.out.println("Initialization time: " + ((tEnd - tStart) / 1000000) + "ms");

        tStart = System.nanoTime();

        String sentiment = SentimentAnalyzer.analyzeSentiment(non_empty_sentences.toString(), positiveWords, negativeWords);

        tEnd = System.nanoTime();

        System.out.println("Sentiment: " + sentiment);

        System.out.println("Working time: " + ((tEnd - tStart) / 1000000) + "ms");
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

    public static List<String> readListFromFile(String filePath){

        List<String> lines = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}


