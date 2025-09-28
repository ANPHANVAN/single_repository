public class Sum {
    int aNumber; 
    int bNumber; 
    int sumNumber; 

    public Sum(int aNumber, int bNumber){
        this.aNumber = aNumber;
        this.bNumber = bNumber;
        this.sumNumber = aNumber + bNumber;
    }

    public void getSum() {
        System.out.println(this.sumNumber);
    }
    public static void main(String[] args) {
        Sum ansum = new Sum(4,5);
        ansum.getSum();
    }
}