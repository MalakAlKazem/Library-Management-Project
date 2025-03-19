import java.util.Comparator;
import java.util.List;

// Concrete strategy for sorting books by year
public class SortByYear implements SortingStartegy{
    @Override
    public void sort(List<Book> books) {
        // Sort books by year
        books.sort(Comparator.comparingInt(Book::getYear));
    }
}
