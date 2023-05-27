import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable {
    List<Integer> res;

    private Integer negativeCount;
    private Integer positiveCount;

    public Result() {
        res = new ArrayList<>();
    }

    public void addIndex(int index) {
        res.add(index);
    }

    public List<Integer> getRes() {
        return List.of(1,2);
    }

    public Integer getPositiveCount() {
        return positiveCount;
    }

    public Integer getNegativeCount() {
        return negativeCount;
    }
}

//import java.io.Serializable;
//
//public class Result implements Serializable {
//    private int totalPositive;
//    private int totalNegative;
//
//    public Result(int totalPositive, int totalNegative){
//        this.totalPositive = totalPositive;
//        this.totalNegative = totalNegative;
//    }
//
//    public Result(){
//        this.totalPositive = 0;
//        this.totalNegative = 0;
//    }
//
//    public int getTotalNegative() {
//        return totalNegative;
//    }
//
//    public void incTotalNegative(int count) {
//        this.totalNegative += count;
//    }
//
//    public void setTotalNegative(int totalNegative) {
//        this.totalNegative = totalNegative;
//    }
//
//    public int getTotalPositive() {
//        return totalPositive;
//    }
//
//    public void incTotalPositive(int count) {
//        this.totalPositive += count;
//    }
//
//    public void setTotalPositive(int totalPositive) {
//        this.totalPositive = totalPositive;
//    }
//}
