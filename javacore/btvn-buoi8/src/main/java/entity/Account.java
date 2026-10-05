package entity;

public class Account {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private Department department;
    private Position position;

    public Account(int id, String username, String fullName, String email,
                   Department department, Position position) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.position = position;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public Department getDepartment() { return department; }
    public Position getPosition() { return position; }
}
