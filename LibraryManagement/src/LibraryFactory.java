// Factory class for creating Book, Genre, and User objects
// Centralizes object creation logic
public class LibraryFactory {
    //generic method to create objects
    public static <T> T create(Class<T> type, Object... args){
        if(type == Book.class){
            return type.cast(new Book((Long) args[0], (String) args[1], (String) args[2], (Integer) args[3]));
        }
        else if (type == Genre.class){
            return type.cast(new Genre((Long) args[0], (String) args[1]));
        }
        else if (type == User.class){
            return type.cast(new User((Long) args[0], (String) args[1], (String) args[2]));
        }
        throw new IllegalArgumentException("Unsupported type: "+ type);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////
    //I deleted individual methods when I used generic method inorder to keep the code clean and maintainable.

    //create new book
//    public static Book createBook(long id, String title, String author, int year){
//        return new Book(id, title, author, year);
//    }
//    //create new genre
//    public static Genre createGenre(Long id, String name){
//        return new Genre(id, name);
//    }
//    //create new user
//    public static User createUser(long id, String username, String password){
//        return new User(id, username, password);
//    }
}
