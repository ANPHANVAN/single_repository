import java.lang.reflect.Array;
import java.util.Arrays;

public class SumArray {
    int[] myArray;
    int sumArray = 0;
    public SumArray(int[] myArray){
        this.myArray = myArray;
    }
    public int TakeSum(){
        for (int i = 0 ; i < this.myArray.length; i++) {
            this.sumArray = this.sumArray + this.myArray[i];
        }
        return this.sumArray;
    }
    public static void main(String[] args) {
        SumArray anSum = new SumArray(new int[] {1, 5, 10, 25});
        System.out.println(Arrays.toString(anSum.myArray));
        System.out.println("Sum: "+anSum.TakeSum());
    }
}