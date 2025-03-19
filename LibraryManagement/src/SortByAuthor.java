import java.util.Comparator;
import java.util.List;

// Concrete strategy for sorting books by year
public class SortByAuthor implements SortingStartegy{
    @Override
    public void sort(List<Book> books) {
        // Sort books by author
        books.sort(Comparator.comparing(Book::getAuthor));
    }
}
