import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ===================================================================
        // Scenario 1: Observer Pattern (Notifications)
        // ===================================================================

        System.out.println("\n=== Observer Pattern Test ===");

        // Create a library and users using the generic factory
        Library library = new Library();
        // Load existing data
        LibraryDataHandler.loadLibraryData(library);

        User user1 = LibraryFactory.create(User.class, 1L, "Alice", "pass123");
        User user2 = LibraryFactory.create(User.class, 2L, "Bob", "pass456");

        // Register users as observers
        library.registerObservers(user1);
        library.registerObservers(user2);

        // Add new books using the generic factory (triggers notifications)
        Book book1 = LibraryFactory.create(Book.class, 1L, "The Hobbit", "J.R.R. Tolkien", 1937);
        Book book2 = LibraryFactory.create(Book.class, 2L, "Dune", "Frank Herbert", 1965);
        Book book3 = LibraryFactory.create(Book.class, 3L, "1984", "George Orwell", 1949);
        Book book4 = LibraryFactory.create(Book.class, 4L, "Brave New World", "Aldous Huxley", 1932);

        library.addBook(book1); // Alice and Bob get notified
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        // ===================================================================
        // Scenario 2: Decorator Pattern (Special Editions)
        // ===================================================================
        System.out.println("\n=== Decorator Pattern Test ===");

        // Create a special edition book
        Book specialEdition = new SpecialEditionBook(book1, "Illustrated Edition");
        System.out.println("Special Edition Book: " + specialEdition.getTitle());

        // ===================================================================
        // Scenario 3: Strategy Pattern (Sorting)
        // ===================================================================
        System.out.println("\n=== Strategy Pattern Test ===");

        // Create a new list of books for sorting (includes special edition)
        List<Book> booksForSorting = new ArrayList<>();
        booksForSorting.add(book1);
        booksForSorting.add(book2);
        booksForSorting.add(book3);
        booksForSorting.add(book4);
        booksForSorting.add(specialEdition);

        // Create a library instance for sorting
        Library sortingLibrary = new Library(booksForSorting);

        // Test sorting by title
        sortingLibrary.setSortingStrategy(new SortByTitle());
        sortingLibrary.sortBooks();
        System.out.println("\nSorted by Title:");
        sortingLibrary.displayBooks();

        // Test sorting by author
        sortingLibrary.setSortingStrategy(new SortByAuthor());
        sortingLibrary.sortBooks();
        System.out.println("\nSorted by Author:");
        sortingLibrary.displayBooks();

        // Test sorting by year
        sortingLibrary.setSortingStrategy(new SortByYear());
        sortingLibrary.sortBooks();
        System.out.println("\nSorted by Year:");
        sortingLibrary.displayBooks();

        // ===================================================================
        // Scenario 4: Genre Feature
        // ===================================================================
        System.out.println("\n=== Genre Assignment Test ===");

        // Create genres using the factory
        Genre fantasy = LibraryFactory.create(Genre.class, 1L, "Fantasy");
        Genre sciFi = LibraryFactory.create(Genre.class, 2L, "Sci-Fi");
        Genre dystopian = LibraryFactory.create(Genre.class, 3L, "Dystopian");

        //add genres to library
        library.addGenre(fantasy);
        library.addGenre(sciFi);
        library.addGenre(dystopian);

        // Assign genres to books (using the main library)
        library.addBookGenre(book1, fantasy);    // The Hobbit → Fantasy
        library.addBookGenre(book2, sciFi);      // Dune → Sci-Fi
        library.addBookGenre(book3, dystopian);  // 1984 → Dystopian
        library.addBookGenre(book3, sciFi);      // 1984 → Sci-Fi (multiple genres)
        library.addBookGenre(book4, dystopian);  // Brave New World → Dystopian

        // Display books in the "Sci-Fi" genre
        System.out.println("\nSci-Fi Books:");
        List<Book> sciFiBooks = library.getBooksByGenre(sciFi);
        sciFiBooks.forEach(book -> System.out.println("- " + book.getTitle()));

        // Display genres of "1984"
        System.out.println("\nGenres of '1984':");
        List<Genre> genresOf1984 = library.getGenresForBook(book3);
        genresOf1984.forEach(genre -> System.out.println("- " + genre.getName()));

        // Launch the UI and pass the library instance
        LibraryUI libraryUI = new LibraryUI(library);
        libraryUI.setVisible(true);

        // Save library data before exiting
        LibraryDataHandler.saveLibraryData(library);
    }
}
///Malak AlKazem
//I still have no id