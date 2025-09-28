package Mini.countString;

public class CountString {
    String word;
    String reverse;

    public CountString(String word) {
        this.word = word;
        this.reverse = word;
    }

    public int Count() {
        return this.word.split(" ").length;
    }

    public void Reverse() {
        // Chuyển chuỗi thành mảng ký tự để dễ xử lý
        char[] chars = this.word.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        // Hoán đổi ký tự từ hai đầu
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        // Lưu chuỗi đảo ngược vào reverse
        this.reverse = new String(chars);
        System.out.println(this.reverse);
    }

    public static void main(String[] args) {
        CountString danchoi = new CountString("One Two Three Four");
        System.out.println(danchoi.Count()); // In số từ
        danchoi.Reverse(); // In chuỗi đảo ngược
    }
}