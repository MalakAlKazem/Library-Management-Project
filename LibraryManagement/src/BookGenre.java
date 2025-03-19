public class BookGenre {

	private Long id;

	private Book book;

	private Genre genre;

	public BookGenre() {
	}

	public BookGenre(Long id, Book book, Genre genre) {
		super();
		this.id = id;
		this.book = book;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
