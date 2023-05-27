import java.io.Serializable;
import java.util.List;

public class Input implements Serializable {
    private List<String> positiveWords;
    private List<String> negativeWords;
    private List<String> sentences;

    public Input(List<String> positiveWords,
                 List<String> negativeWords,
                 List<String> sentences){
        this.positiveWords = positiveWords;
        this.negativeWords = negativeWords;
        this.sentences = sentences;
    }

    public List<String> getPositiveWords() {
        return positiveWords;
    }
    public List<String> getNegativeWords() {
        return negativeWords;
    }
    public List<String> getSentences() {
        return sentences;
    }
}
