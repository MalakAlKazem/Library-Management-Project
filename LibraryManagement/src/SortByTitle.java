import java.util.Comparator;
import java.util.List;

// Concrete strategy for sorting books by title
public class SortByTitle implements SortingStartegy {
    @Override
    public void sort(List<Book> books) {
        // Sort books by title
        books.sort(Comparator.comparing(Book::getTitle));
    }
}
