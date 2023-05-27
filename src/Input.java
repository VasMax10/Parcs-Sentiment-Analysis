import java.io.Serializable;

public class Input implements Serializable {
    private String text;
    private String pattern;

    public Input(String text, String pattern) {
        this.text = text;
        this.pattern = pattern;
    }

    public String getText() {
        return text;
    }

    public String getPattern() {
        return pattern;
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
