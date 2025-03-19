// Abstract decorator class for the Decorator Pattern
// Wraps a Book object and adds additional functionality
public abstract class BookDecorator extends Book{
    protected Book decoratedBook; // The book being decorated

    // Constructor to wrap a Book object
    public BookDecorator(Book decoratedBook){
        this.decoratedBook = decoratedBook;
    }

    // Override methods to delegate to the decorated book
    @Override
    public String getTitle() {
        return decoratedBook.getTitle();
    }

    @Override
    public String getAuthor() {
            return decoratedBook.getAuthor();
        }

    @Override
    public int getYear() {
        return decoratedBook.getYear();
    }
}

