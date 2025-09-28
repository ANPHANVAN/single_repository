public class LuuY {
    public static void main(String[] args){
        String a = "hello";
        String c = "hello";
        String b = new String("hello");
        System.out.println(a == b); // ❌ Sai, so sánh địa chỉ
        System.out.println(a.equals(b)); // ✅ Đúng, so sánh nội dung
        System.out.println(a == c );

    }
}