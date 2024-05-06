import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public abstract class User {
    protected String name;
    protected String userId;
    protected String password;
    protected  String role;
    protected BankUser user;

    protected BankManager manager;
    Random random = new Random();

    public User(String name, String userId, String password, String role) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.role=role;
    }

    public boolean checkLogin() {

        String username="";
        String password="";
        String role="";//Based on login page they choose
        // SQL query to check the username, password, and role
        String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND role = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Setting the parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);

            // Execute the query
            ResultSet rs = pstmt.executeQuery();

            // Check if any rows were returned
            if (rs.next()) {
                if (this.role.equals(Constants.get.bankmanager)){
                    manager = new BankManager(name,userId,password,role);
                }else{
                    user = new BankUser(name,userId,password,role);
                }
                return true;  // Login successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Login failed
    }



}
