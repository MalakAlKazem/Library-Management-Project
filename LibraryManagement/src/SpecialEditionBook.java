// Concrete decorator class for special edition books
// Adds a special feature to the book's title
public class SpecialEditionBook extends BookDecorator{
    private String specialFeature; // Special feature of the book (e.g., "Limited Cover")

    // Constructor to initialize the special feature
    public SpecialEditionBook(Book decoratedBook, String specialFeature){
        super(decoratedBook);
        this.specialFeature = specialFeature;
    }

    // Override getTitle to include the special feature
    @Override
    public String getTitle() {
        return decoratedBook.getTitle() + " (Special Edition: " + specialFeature + ")";
    }
}
