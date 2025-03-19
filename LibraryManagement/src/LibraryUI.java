import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryUI extends JFrame {
    private Library library;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortOptions;
    private JComboBox<String> viewModeComboBox;

    public LibraryUI(Library library) {
        this.library = library;
        setTitle("Library Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize table model with default columns for Books.
        tableModel = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "Year"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel for buttons and controls
        JPanel buttonPanel = new JPanel(new FlowLayout());

        // Add Book Button
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> addBook());
        buttonPanel.add(addBookButton);

        // Register User Button
        JButton registerUserButton = new JButton("Register User");
        registerUserButton.addActionListener(e -> registerUser());
        buttonPanel.add(registerUserButton);

        // View Mode ComboBox (Books or Users)
        viewModeComboBox = new JComboBox<>(new String[]{"Books", "Users"});
        buttonPanel.add(new JLabel("View Mode:"));
        buttonPanel.add(viewModeComboBox);

        // View Button to update the table
        JButton viewButton = new JButton("View");
        viewButton.addActionListener(e -> loadView());
        buttonPanel.add(viewButton);

        // Sorting Options (only applicable when viewing Books)
        sortOptions = new JComboBox<>(new String[]{"Title", "Author", "Year"});
        buttonPanel.add(new JLabel("Sort By:"));
        buttonPanel.add(sortOptions);

        // Sort Button
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> sortBooks());
        buttonPanel.add(sortButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Initially load view based on the default selection (Books)
        loadView();
    }

    // Method to add a new book
    private void addBook() {
        String title = JOptionPane.showInputDialog(this, "Enter book title:");
        if (title == null) return; // User canceled

        String author = JOptionPane.showInputDialog(this, "Enter book author:");
        if (author == null) return;

        String yearStr = JOptionPane.showInputDialog(this, "Enter publication year:");
        if (yearStr == null) return;

        try {
            int year = Integer.parseInt(yearStr);
            long id = library.getBooks().size() + 1; // Generate ID dynamically
            Book newBook = new Book(id, title, author, year);
            library.addBook(newBook);

            // Save the updated library data
            LibraryDataHandler.saveLibraryData(library);

            // Refresh table if currently in Books view
            if ("Books".equals(viewModeComboBox.getSelectedItem())) {
                loadBooks();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid year format!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to register a new user with an automatically generated ID
    private void registerUser() {
        String name = JOptionPane.showInputDialog(this, "Enter user name:");
        if (name == null) return; // User cancelled

        String password = JOptionPane.showInputDialog(this, "Enter user password:");
        if (password == null) return; // User cancelled

        // Generate the next available user ID
        long nextId = generateNextUserId();

        // Create a new user using the automatically generated ID
        User newUser = LibraryFactory.create(User.class, nextId, name, password);
        library.registerObservers(newUser);

        // Save the updated library data
        LibraryDataHandler.saveLibraryData(library);

        JOptionPane.showMessageDialog(this, "User registered successfully ",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        // Refresh table if the current view is set to Users
        if ("Users".equals(viewModeComboBox.getSelectedItem())) {
            loadUsers();
        }
    }

    // Helper method to generate the next available user ID
    private long generateNextUserId() {
        long maxId = 0;
        for (User user : library.getUsers()) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId + 1;
    }

    // Method to update the table based on the selected view mode (Books or Users)
    private void loadView() {
        String viewMode = (String) viewModeComboBox.getSelectedItem();
        if ("Books".equals(viewMode)) {
            // Set table model columns for Books and load books
            tableModel.setColumnIdentifiers(new Object[]{"ID", "Title", "Author", "Year"});
            loadBooks();
        } else if ("Users".equals(viewMode)) {
            // Set table model columns for Users and load users
            tableModel.setColumnIdentifiers(new Object[]{"ID", "Name"});
            loadUsers();
        }
    }

    // Method to sort books (only applies if current view mode is Books)
    private void sortBooks() {
        if (!"Books".equals(viewModeComboBox.getSelectedItem())) {
            JOptionPane.showMessageDialog(this, "Sorting is only applicable to books!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String selectedOption = (String) sortOptions.getSelectedItem();
        if ("Title".equals(selectedOption)) {
            library.setSortingStrategy(new SortByTitle());
        } else if ("Author".equals(selectedOption)) {
            library.setSortingStrategy(new SortByAuthor());
        } else if ("Year".equals(selectedOption)) {
            library.setSortingStrategy(new SortByYear());
        }
        library.sortBooks();

        // Save the updated library data after sorting
        LibraryDataHandler.saveLibraryData(library);

        loadBooks();
    }

    // Load books into the table
    private void loadBooks() {
        tableModel.setRowCount(0);
        for (Book book : library.getBooks()) {
            tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getYear()});
        }
    }

    // Load users into the table
    private void loadUsers() {
        tableModel.setRowCount(0);
        for (User user : library.getUsers()) { // Ensure Library.getUsers() returns List<User>
            tableModel.addRow(new Object[]{user.getId(), user.getUsername()});
        }
    }

    // Main method to launch the UI
    public static void main(String[] args) {
        Library library = new Library();
        LibraryUI libraryUI = new LibraryUI(library);
        libraryUI.setVisible(true);
    }
}