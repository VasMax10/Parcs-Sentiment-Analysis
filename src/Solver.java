import parcs.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Solver {

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("SentimentAnalyzerParcs.jar");

        String text = textFromFile( curtask.findFile("input") );
        String positive = sentimentFromFile( curtask.findFile("positive_words.txt"));
        String negative = sentimentFromFile( curtask.findFile("negative_words.txt"));

        AMInfo info = new AMInfo(curtask, null);

        int nThreads = 4;
        int n = text.length() / nThreads;
        int M = positive.length();

        List<String> texts = new ArrayList<>();
        List<Integer> shifts = new ArrayList<>();

        for (int i = 0; i < nThreads; i++) {
            int l = i * n;
            int r = (i + 1) * n;
            String textPart = text.substring(l, r);
            texts.add(textPart);
            shifts.add(l);
            if (i < nThreads - 1) {
                int ll = r - (M - 1);
                int rr = r + M - 1;
                String text1 = text.substring(ll, rr);
                texts.add(text1);
                shifts.add(ll);
            }
        }

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();
        List<String> words = List.of(positive.split("[ ,\"']"));

        List<String> sentences = List.of(text.toString().split("[.!?]"));
        List<String> non_empty_sentences = new ArrayList<>();
        int count = 0;

        for (String sentence : sentences) {
            if (!sentence.trim().isEmpty()) {
                count++;
                non_empty_sentences.add(sentence);
            }
        }

        System.out.println("Number of sentences: " + count);


        for (int i = 0; i < count; i++) {
            String t = texts.get(i);
            Integer shift = shifts.get(i);

            point p = info.createPoint();
            points.add(p);

            channel c = p.createChannel();
            channels.add(c);

            System.out.println(words.get(i) + " -- " + non_empty_sentences.get(i));

            Input input = new Input(non_empty_sentences.get(i), words.get(i), negative);

            points.get(i).execute("SentimentAnalyzerParcs");
            channels.get(i).write(input);

            System.out.println("Waiting for result .. ");

            Result result = (Result) (channels.get(i).readObject());
            List<Integer> ins = result.getRes();
            if (ins.size() > 0) {
                System.out.println("Pattern ins : {");
                for (int index : ins) {
                    System.out.print(shift + index + " ");
                }
                System.out.println("}");
            }
        }


        curtask.end();
    }
    public static String textFromFile(String filename) throws Exception {

        String text = new Scanner(new File(filename)).useDelimiter("\\Z").next();

        return text;
    }
    public static String sentimentFromFile(String filename) throws Exception {
        String pattern = "";

        Scanner sc = new Scanner(new File(filename));

        pattern = sc.nextLine();

        return pattern;
    }
}



//import  java.io.*;
//import java.util.*;
//
//import parcs.*;
//
//public class Solver {
//    public static void main(String[] args) throws Exception {
//        task curtask = new task();
//        curtask.addJarFile("SentimentAnalyzerParcs.jar");
//
//        String text = textFromFile(curtask.findFile("input_small.txt"));
//        String positive = textFromFile(curtask.findFile("positive_words.txt"));
//        String negative = textFromFile(curtask.findFile("negative_words.txt"));
//
//        AMInfo info = new AMInfo(curtask, null);
//
//        int nThreads = 4;
//
//        List<point> points = new ArrayList<>();
//        List<channel> channels = new ArrayList<>();
//
//        for (int i = 0; i < nThreads; i++) {
////            String t = texts.get(i);
////            Integer shift = shifts.get(i);
//
//            point p = info.createPoint();
//            channel c = p.createChannel();
//
//            points.add(p);
//            channels.add(c);
//
//            Input input = new Input(positive, negative, text);
//
//            p.execute("SentimentAnalyzerParcs");
//            c.write(input);
//
//            System.out.println(input.getText());
//            System.out.println(input.getNegative());
//            System.out.println(input.getPositive());
//            System.out.println("Waiting for result .. ");
//
////            Result result = (Result) (c.readObject());
////            String result = (String) (c.readObject());
//            Input result = (Input) (c.readObject());
//            System.out.println(result.getText());
//            System.out.println(result.getNegative());
//            System.out.println(result.getPositive());
////            System.out.println(result);
////            List<Integer> ins = result.getRes();
////            if (ins.size() > 0) {
////                System.out.println("Pattern ins : {");
////                for (int index : ins) {
////                    System.out.print(shift + index + " ");
////                }
////                System.out.println("}");
////            }
//        }
//
//
//        curtask.end();
//    }
//    public static String textFromFile(String filename) throws Exception {
//
//        String text = new Scanner(new File(filename)).useDelimiter("\\Z").next();
//
//        return text;
//    }
//}
////
////public class Solver{
////    public static void main(String[] args) throws Exception {
////        task curtask = new task();
////        curtask.addJarFile("SentimentAnalyzerParcs.jar");
////
////        int nThreads;
////
////        StringBuilder full_text = new StringBuilder();
////
////        try
////        {
////            BufferedReader in = new BufferedReader(new FileReader(curtask.findFile("input_small.txt")));
////
////            nThreads = Integer.parseInt(in.readLine());
////            String line;
////
////            while ((line = in.readLine()) != null) {
////                full_text.append(line);
////            }
////        }
////        catch (IOException e)
////        {
////            System.out.print("Reading input data error\n");
////            e.printStackTrace();
////            return;
////        }
////
////        List<String> positiveWords = readListFromFile(curtask.findFile("positive_words.txt"));
////        List<String> negativeWords = readListFromFile(curtask.findFile("negative_words.txt"));
////
////        AMInfo info = new AMInfo(curtask, null);
////
//        List<String> sentences = List.of(full_text.toString().split("[.!?]"));
//        List<String> non_empty_sentences = new ArrayList<>();
//        int count = 0;
//
//        for (String sentence : sentences) {
//            if (!sentence.trim().isEmpty()) {
//                count++;
//                non_empty_sentences.add(sentence);
//            }
//        }
//
//        System.out.println("Number of sentences: " + count);
////
////        List<List<String>> dividedLists = divideList(non_empty_sentences, nThreads);
////
////
////        List<point> points = new ArrayList<>();
////        List<channel> channels = new ArrayList<>();
////
////        for (int i = 0; i < nThreads; i++){
////
////            point p = info.createPoint();
////            channel c = p.createChannel();
////
////            points.add(p);
////            channels.add(c);
////
////            Input input = new Input(
//////                    positiveWords, negativeWords,
////                    dividedLists.get(i));
////
////            p.execute("SentimentAnalyzerParcs");
////            c.write(input);
////
////            System.out.println("Waiting for result .. ");
////
//////            Result result = (Result) (c.readObject());
////
//////            System.out.println(result.getTotalNegative());
//////            System.out.println(result.getTotalPositive());
////        }
////        curtask.end();
////    }
////
//////public class Solver implements AM{
//////
//////    public static void main(String[] args){
//////        task curtask = new task();
//////
//////        curtask.addJarFile("SentimentAnalyzerParcs.jar");
//////
//////        System.out.println("The Solver class method 'main' has added jar file 'SentimentAnalyzerParcs.jar'");
//////
//////        (new Solver()).run(new AMInfo(curtask, null));
//////
//////        System.out.println("The Solver class method 'main' has finished its work");
//////        curtask.end();
//////    }
//////
//////    @Override
//////    public void run(AMInfo amInfo) {
//////
//////        int nThreads;
//////
//////        StringBuilder full_text = new StringBuilder();
//////
//////        try
//////        {
//////            BufferedReader in = new BufferedReader(new FileReader(amInfo.curtask.findFile("input_small.txt")));
//////
//////            nThreads = Integer.parseInt(in.readLine());
//////            String line;
//////
//////            while ((line = in.readLine()) != null) {
//////                full_text.append(line);
//////            }
//////        }
//////        catch (IOException e)
//////        {
//////            System.out.print("Reading input data error\n");
//////            e.printStackTrace();
//////            return;
//////        }
//////
//////        List<String> positiveWords = readListFromFile(amInfo, "positive_words.txt");
//////        List<String> negativeWords = readListFromFile(amInfo,"negative_words.txt");
//////
//////        List<String> sentences = List.of(full_text.toString().split("[.!?]"));
//////        List<String> non_empty_sentences = new ArrayList<>();
//////        int count = 0;
//////
//////        for (String sentence : sentences) {
//////            if (!sentence.trim().isEmpty()) {
//////                count++;
//////                non_empty_sentences.add(sentence);
//////            }
//////        }
//////
//////        System.out.println("Number of sentences: " + count);
//////
//////        List<List<String>> dividedLists = divideList(non_empty_sentences, nThreads);
//////
//////        List<point> points = new ArrayList<point>();
//////        List<channel> channels = new ArrayList<channel>();
//////
//////        // Connection to points
//////
//////        // Time counting
//////        long tStart = System.nanoTime();
//////
//////        int totalNegative = 0;
//////        int totalPositive = 0;
//////
//////        for(int i = 0; i < nThreads; i++)
//////        {
//////            System.out.println(i);
//////
//////            point p = amInfo.createPoint();
//////            channel c = p.createChannel();
//////
//////            points.add(p);
//////            channels.add(c);
//////
//////            Input input = new Input(positiveWords, negativeWords, dividedLists.get(i));
//////
//////            System.out.println(positiveWords.get(0));
//////            System.out.println(negativeWords.get(0));
//////            System.out.println(dividedLists.get(i).get(0));
//////
//////            p.execute("SentimentAnalyzerParcs");
//////            c.write(123);
//////
//////            System.out.println("Waiting for the result...");
//////
//////            Result result = (Result) (c.readObject());
//////
//////            totalPositive += result.getTotalPositive();
//////            totalNegative += result.getTotalNegative();
//////
//////        }
//////
//////        long tEnd = System.nanoTime();
//////
//////        if (totalPositive > totalNegative) {
//////            System.out.println("Positive");
//////        } else if (totalNegative > totalPositive) {
//////            System.out.println("Negative");
//////        } else {
//////            System.out.println("Neutral");
//////        }
//////
//////        System.out.println("Working time on " + nThreads + " processes: " + ((tEnd - tStart) / 1000000) + "ms");
//////    }
//////
////    public static List<List<String>> divideList(List<String> originalList, int numberOfDivisions) {
////        List<List<String>> dividedLists = new ArrayList<>();
////
////        int listSize = originalList.size();
////        int sublistSize = listSize / numberOfDivisions;
////        int mod = listSize % numberOfDivisions;
////
////        System.out.println("mod = " + mod);
////
////        int startIndex = 0;
////        int endIndex = sublistSize;
////
////        for (int i = 0; i < numberOfDivisions; i++) {
////            if (i == numberOfDivisions - 1) {
////                // Last sublist may have different size
////                endIndex = listSize;
////            }
////
////            if (mod > 0){
////                endIndex++;
////                mod--;
////            }
////
////            List<String> sublist = originalList.subList(startIndex, endIndex);
////            dividedLists.add(sublist);
////
////            startIndex = endIndex;
////            endIndex += sublistSize;
////        }
////
////        return dividedLists;
////    }
////
////    public static List<String> readListFromFile(String file){
////
////        List<String> lines = new ArrayList<>();
////
////        try {
////            BufferedReader in = new BufferedReader(new FileReader(file));
////            String line;
////
////            while ((line = in.readLine()) != null) {
////                lines.add(line);
////            }
////
////        } catch (IOException e) {
////            e.printStackTrace();
////            System.out.print("Reading sentiment words error\n");
////        }
////        return lines;
////    }
////}
