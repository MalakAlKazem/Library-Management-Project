import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Library class acts as the Subject in the Observer Pattern
// Manages books and notifies observers when a new book is added
public class Library {
    // List of observers (users)
    private List<Observer> observers = new ArrayList<>();
    // List of books in the library
    private List<Book> books = new ArrayList<>();
    private SortingStartegy sortingStartegy;
    private List<BookGenre> bookGenres = new ArrayList<>(); // Track genre associations
    private List<Genre> genres = new ArrayList<>();


    public Library(List<Book> books) {
        this.books = books;
    }

    public Library(){}

    //Add new book to library and notify all observers
    public void addBook(Book book) {
        // Check if the book ID already exists
        if (books.stream().anyMatch(b -> b.getId() == book.getId())) {
            System.out.println("Duplicate book ID found: " + book.getId() + " - Book not added.");
            return;
        }
        books.add(book);
        notifyObservers(book);
    }

    //Register an observer (user) to receive notifications
    public void registerObservers(Observer observer) {
        if (observer instanceof User) {
            User user = (User) observer;
            if (isUserRegistered(user)) {
                System.out.println("Duplicate user ID found: " + user.getId() + " - User not registered.");
                return;
            }
        }
        observers.add(observer);
    }
    // Notify all registered observers about the new book
    private void notifyObservers(Book book){
        // Call the update method on each observer using lambda
        observers.forEach(observer -> observer.update(book));
    }

    //set sorting strategy dynamically
    public void setSortingStrategy(SortingStartegy sortingStartegy){
        this.sortingStartegy = sortingStartegy;
    }

    //sort book using the selected startegy
    public void sortBooks(){
        if(sortingStartegy != null){
            sortingStartegy.sort(books);
        }
    }



    //display sorted books
    public void displayBooks(){
        for(Book book : books){
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " (" + book.getYear() + ")");
        }
    }
    //generic method to display any list objects
    public <T> void displayList(List<T> list, String header){
        System.out.println("\n=== " + header + " ===");
        list.forEach(item -> System.out.println(item)); //lambda for iteration
        System.out.println("==========================");
    }

    // Link a book to a genre
    public void addBookGenre(Book book, Genre genre) {
        // Prevent duplicate book-genre relationships
        for (BookGenre bg : bookGenres) {
            if (bg.getBook().equals(book) && bg.getGenre().equals(genre)) {
                System.out.println("Book-Genre relationship already exists: " + book.getTitle() + " - " + genre.getName());
                return;
            }
        }
        BookGenre bookGenre = new BookGenre(null, book, genre); // ID can be auto-generated
        bookGenres.add(bookGenre);
    }


    public List<Genre> getGenresForBook(Book book) {
        List<Genre> genres = new ArrayList<>();
        for (BookGenre bg : bookGenres) {
            if (bg.getBook().equals(book)) {
                genres.add(bg.getGenre());
            }
        }
        return genres;
    }



    public void addGenre(Genre genre) {
        if (!genres.contains(genre)) {
            genres.add(genre);
        }
    }

    public boolean isUserRegistered(User user) {
        return getUsers().stream().anyMatch(u -> u.getId() == user.getId());
    }


    public List<Genre> getGenres() {
        return genres;
    }

    // Get all books in a genre
    public List<Book> getBooksByGenre(Genre genre) {
        return bookGenres.stream()
                .filter(bg -> bg.getGenre().equals(genre))
                .map(BookGenre::getBook)
                .collect(Collectors.toList());
    }
    public List<Book> getBooks() {
        return books;
    }
    // Return a list of users (observers that are instances of User)
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (Observer observer : observers) {
            if (observer instanceof User) {
                users.add((User) observer);
            }
        }
        return users;
    }



}
