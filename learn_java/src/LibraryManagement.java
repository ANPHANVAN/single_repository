import java.util.ArrayList;
import java.util.List;
// Lớp chính để chạy chương trình
public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();

        // Thêm sách
        library.addBook(new TextBook("Java Programming", "John Doe", 1, "Computer Science"));
        library.addBook(new Novel("The Hobbit", "J.R.R. Tolkien", 2, "Fantasy"));

        // Hiển thị danh sách sách
        System.out.println("\nAll Books:");
        library.displayBooks();

        // Mượn sách
        library.borrowBook(1);

        // Hiển thị lại danh sách sách
        System.out.println("\nAll Books after borrowing:");
        library.displayBooks();

        // Trả sách
        library.returnBook(1);

        // Hiển thị lại danh sách sách
        System.out.println("\nAll Books after returning:");
        library.displayBooks();
    }
}