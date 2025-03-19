import java.util.List;

// Interface for the Strategy Pattern
// Defines the sort method that all sorting strategies must implement
public interface SortingStartegy {
    void sort(List<Book> books); // Sort a list of books

}
