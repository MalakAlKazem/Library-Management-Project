// User class implements the Observer interface
// Users will be notified when a new book is added to the library
public class User implements Observer{

    private long id;

    private String username;

    private String password;

    public User() {

    }

    public User(long id, String username, String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // Update method called by the Library when a new book is added
    @Override
    public void update(Book book) {
        System.out.println("User "+ username + " notified about new book: "+ book.getTitle());
    }
}
