import java.util.ArrayList;
import java.util.List;
// Lớp Novel kế thừa Book
class Novel extends Book {
    private String genre;

    public Novel(String title, String author, int id, String genre) {
        super(title, author, id);
        this.genre = genre;
    }

    @Override
    public String getDetails() {
        return "Novel: " + getTitle() + " by " + getAuthor() + ", Genre: " + genre;
    }
}