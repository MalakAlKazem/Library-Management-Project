import java.io.*;
import java.util.*;

public class LibraryDataHandler {

    private static final String FILE_NAME = "library_data.txt";

    // Save library data to a file
    public static void saveLibraryData(Library library) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            // Save books
            writer.println("[Books]");
            for (Book book : library.getBooks()) {
                writer.println(book.getId() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getYear());
            }

            // Save users
            writer.println("[Users]");
            for (User user : library.getUsers()) {
                writer.println(user.getId() + "," + user.getUsername() + "," + user.getPassword());
            }

            // Save genres (avoid duplicates)
            writer.println("[Genres]");
            Set<Long> genreIds = new HashSet<>();
            for (Genre genre : library.getGenres()) {
                if (!genreIds.contains(genre.getId())) {
                    writer.println(genre.getId() + "," + genre.getName());
                    genreIds.add(genre.getId());
                }
            }

            // Save book-genre relationships
                    writer.println("[BookGenres]");
                    for (Book book : library.getBooks()) {
                        List<Genre> genres = library.getGenresForBook(book);
                        for (Genre genre : genres) {
                            writer.println(book.getId() + "," + genre.getId());
                        }
                    }

            System.out.println("Library data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

    public static void loadLibraryData(Library library) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            String section = "";
            Map<Long, Genre> genreMap = new HashMap<>();
            Map<Long, Book> bookMap = new HashMap<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("[")) {
                    section = line;  // Identifying section headers like [Books], [Genres]
                    continue;
                }

                String[] parts = line.split(",");
                switch (section) {
                    case "[Books]":
                        long bookId = Long.parseLong(parts[0]);
                        if (!bookMap.containsKey(bookId)) {
                            Book book = new Book(bookId, parts[1], parts[2], Integer.parseInt(parts[3]));
                            library.addBook(book);
                            bookMap.put(bookId, book);
                        }
                        break;
                    case "[Users]":
                        User user = new User(Long.parseLong(parts[0]), parts[1], parts[2]);
                        // Avoid adding duplicate users
                        if (!library.isUserRegistered(user)) {
                            library.registerObservers(user);
                        }
                        break;
                    case "[Genres]":
                        long genreId = Long.parseLong(parts[0]);
                        if (!genreMap.containsKey(genreId)) {
                            Genre genre = new Genre(genreId, parts[1]);
                            library.addGenre(genre);
                            genreMap.put(genreId, genre);
                        }
                        break;
                    case "[BookGenres]":
                        long BookId = Long.parseLong(parts[0]);
                        long GenreId = Long.parseLong(parts[1]);
                        Book b = bookMap.get(BookId);
                        Genre g = genreMap.get(GenreId);
                        if (b != null && g != null) {
                            library.addBookGenre(b, g);
                        } else {
                            System.err.println("Warning: Book or Genre not found for Book ID=" + BookId + ", Genre ID=" + GenreId);
                        }
                        break;



                }
            }
            System.out.println("Library data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading library data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + e.getMessage());
        }
    }
}
