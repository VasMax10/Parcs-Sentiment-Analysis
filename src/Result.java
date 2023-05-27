import java.io.Serializable;

public class Result implements Serializable {

    private Integer negativeCount = 0;
    private Integer positiveCount = 0;

    public Result() {
        negativeCount = 0;
        positiveCount = 0;
    }

    public void incNegativeCount(Integer count){
        this.negativeCount += count;
    }

    public void incPositiveCount(Integer count){
        this.positiveCount += count;
    }

    public Integer getPositiveCount() {
        return positiveCount;
    }

    public Integer getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(Integer negativeCount){
        this.negativeCount = negativeCount;
    }

    public void setPositiveCount(Integer positiveCount){
        this.positiveCount = positiveCount;
    }
}