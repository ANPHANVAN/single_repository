import java.util.ArrayList;
import java.util.List;
// Lớp TextBook kế thừa Book
class TextBook extends Book {
    private String subject;

    public TextBook(String title, String author, int id, String subject) {
        super(title, author, id);
        this.subject = subject;
    }

    @Override
    public String getDetails() {
        return "TextBook: " + getTitle() + " by " + getAuthor() + ", Subject: " + subject;
    }
}