import java.util.ArrayList;
import java.util.List;
// Lớp Library quản lý sách
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added: " + book.getDetails());
    }

    public void borrowBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && !book.isBorrowed()) {
                book.setBorrowed(true);
                System.out.println("Borrowed: " + book.getDetails());
                return;
            }
        }
        System.out.println("Book not found or already borrowed.");
    }

    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id && book.isBorrowed()) {
                book.setBorrowed(false);
                System.out.println("Returned: " + book.getDetails());
                return;
            }
        }
        System.out.println("Book not found or not borrowed.");
    }

    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book.getDetails() + ", Borrowed: " + book.isBorrowed());
        }
    }
}