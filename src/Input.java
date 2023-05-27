import java.io.Serializable;
import java.util.List;

public class Input implements Serializable {
    private String text;
    private List<String> positive;
    private List<String> negative;

    public Input(String text, List<String> positive, List<String> negative) {
        this.text = text;
        this.positive = positive;
        this.negative = negative;
    }

    public String getText() {
        return text;
    }

    public List<String> getPositive() {
        return positive;
    }

    public List<String> getNegative() {
        return negative;
    }
}

//import java.io.Serializable;
//import java.util.List;
//
//public class Input implements Serializable {
////    private List<String> positiveWords;
////    private List<String> negativeWords;
////    private List<String> sentences;
//    private String positive;
//    private String negative;
//    private String text;
//
//    public Input(
//            String positive,
//            String negative,
//            String text){
//
////            List<String> positiveWords,
////                 List<String> negativeWords,
////                 List<String> sentences){
////        this.positiveWords = positiveWords;
////        this.negativeWords = negativeWords;
////        this.sentences = sentences;
//        this.negative = negative;
//        this.positive = positive;
//        this.text = text;
//    }
//
//    public String getPositive() {
//        return positive;
//    }
//
//    public String getNegative() {
//        return negative;
//    }
//
//    public String getText() {
//        return text;
//    }
//
////    public List<String> getPositiveWords() {
////        return positiveWords;
////    }
////    public List<String> getNegativeWords() {
////        return negativeWords;
////    }
////    public List<String> getSentences() {
////        return sentences;
////    }
//
//}
