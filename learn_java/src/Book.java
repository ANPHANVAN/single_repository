import java.util.ArrayList;
import java.util.List;
abstract class Book {
    private String title;
    private String author;
    private int id;
    private boolean isBorrowed;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isBorrowed = false;
    }

    // Getter và Setter
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getId() { return id; }
    public boolean isBorrowed() { return isBorrowed; }
    public void setBorrowed(boolean borrowed) { isBorrowed = borrowed; }

    // Phương thức trừu tượng
    public abstract String getDetails();
}