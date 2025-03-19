# Library Management System

This is a Java-based Library Management System that allows users to manage books, users, and genres. It includes a **User Interface (UI)** for interacting with the system, as well as features like sorting books, adding special edition books, notifying users about new book additions, and creating objects using the Factory Pattern.

## Features

- **Book Management**: Add, update, and manage books with details like title, author, and year.
- **User Management**: Manage users with details like username and password.
- **Genre Management**: Assign genres to books.
- **Sorting Strategies**: Sort books by title, author, or year using the Strategy Pattern.
- **Special Edition Books**: Decorate books with special features using the Decorator Pattern.
- **Observer Pattern**: Notify users when new books are added to the library.
- **Want to Read List**: Users can maintain a list of books they want to read.
- **Data Persistence**: Save and load data using `library_data.txt`.
- **Factory Pattern**: Create different types of books (e.g., regular books, special edition books) using the Factory Pattern.
- **User Interface (UI)**: A graphical interface for interacting with the library system.

## Project Structure

The project is organized as follows:

```
LibraryManagement/
├── src/
│   ├── SpecialEditionBook.java        // Decorator for special edition books
│   ├── SortByAuthor.java              // Sorting strategy by author
│   ├── SortByTitle.java               // Sorting strategy by title
│   ├── SortByYear.java                // Sorting strategy by year
│   ├── SortingStartegy.java           // Interface for sorting strategies
│   ├── User.java                      // User class implementing Observer
│   ├── WantToRead.java                // Represents a user's "Want to Read" list
│   ├── BookFactory.java               // Factory for creating books
│   ├── Book.java                      // Base class for books
│   ├── LibraryUI.java                 // User Interface for the library system
│   └── library_data.txt               // Data storage for books, users, and genres
├── LibraryManagement.iml              // IntelliJ module configuration
├── .gitignore                         // Git ignore file
└── README.md                          // This file
```

## How to Run

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/library-management-system.git
   cd library-management-system
   ```

2. **Open in IDE**:
   - Open the project in IntelliJ IDEA or any other Java IDE.
   - Ensure the JDK is properly configured.

3. **Run the Project**:
   - Compile and run the project from the IDE.
   - The `LibraryUI` class contains the main entry point for the UI. Run this class to launch the graphical interface.

## Data Storage

The `library_data.txt` file is used to store and load data for the library management system. It contains information about books, users, genres, and book-genre relationships. The file is structured as follows:

### File Structure:
```
[Books]
<book_id>,<title>,<author>,<year>

[Users]
<user_id>,<username>,<password>

[Genres]
<genre_id>,<genre_name>

[BookGenres]
<book_id>,<genre_id>
```

### Example Data:
```
[Books]
1,The Hobbit,J.R.R. Tolkien,1937
2,Dune,Frank Herbert,1965
3,1984,George Orwell,1949

[Users]
1,Alice,pass123
2,Bob,pass456

[Genres]
1,Fantasy
2,Sci-Fi
3,Dystopian

[BookGenres]
1,1
2,2
3,3
```

### How Data is Managed:
- **Books**: Each book has a unique ID, title, author, and publication year.
- **Users**: Each user has a unique ID, username, and password.
- **Genres**: Each genre has a unique ID and name.
- **BookGenres**: Represents the relationship between books and genres.

## Design Patterns Used

- **Strategy Pattern**: Used for sorting books by different attributes (title, author, year).
- **Decorator Pattern**: Used to add special features to books (e.g., special edition books).
- **Observer Pattern**: Used to notify users when new books are added to the library.
- **Factory Pattern**: Used to create different types of books (e.g., regular books, special edition books).

### Factory Pattern Implementation

The **Factory Pattern** is implemented in the `BookFactory` class. It provides a way to create different types of books without exposing the instantiation logic to the client.

#### Example Usage:
```java
Book regularBook = BookFactory.createBook("Regular", "The Hobbit", "J.R.R. Tolkien", 1937);
Book specialEditionBook = BookFactory.createBook("SpecialEdition", "Dune", "Frank Herbert", 1965, "Limited Cover");
```

#### `BookFactory.java`:
```java
public class BookFactory {
    public static Book createBook(String type, String title, String author, int year, String... specialFeature) {
        if (type.equalsIgnoreCase("Regular")) {
            return new Book(title, author, year);
        } else if (type.equalsIgnoreCase("SpecialEdition")) {
            return new SpecialEditionBook(new Book(title, author, year), specialFeature[0]);
        }
        throw new IllegalArgumentException("Invalid book type");
    }
}
```

## User Interface (UI)

The `LibraryUI` class provides a graphical interface for interacting with the library management system. It allows users to:
- Add, update, and delete books.
- Manage users and genres.
- Sort books by title, author, or year.
- View special edition books.
- Add books to the "Want to Read" list.

### Running the UI:
- The `LibraryUI` class contains the main entry point for the UI. Run this class to launch the graphical interface.
- The UI is built using Java Swing or JavaFX (depending on your implementation).

### Example UI Features:
1. **Book Management**:
   - Add a new book (regular or special edition).
   - Update or delete existing books.
2. **User Management**:
   - Add or remove users.
3. **Sorting**:
   - Sort books by title, author, or year.
4. **Notifications**:
   - Users are notified when new books are added.

## Contributing

Feel free to contribute to this project by opening issues or submitting pull requests. Any improvements or new features are welcome!
