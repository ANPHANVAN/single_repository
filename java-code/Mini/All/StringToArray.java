import java.util.Arrays;

public class StringToArray {
    String input;
    String[] output;
    public StringToArray(String input) {
        this.input = input;
    }

    public String[] Exchange(){
        this.output = this.input.split(" ");
        return this.output;
    }
    
    public static void PrintArray(String[] thisArray){
        System.out.println(Arrays.toString(thisArray));
    }
    public static void main(String[] args) {
        StringToArray classAn = new StringToArray("I love Java");
        String[] output = classAn.Exchange();
        StringToArray.PrintArray(output);
    }
}
