import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class Session {
    private static Session singletonInst = null;
    private boolean login = false;
    private String userName;
    private String userId;
    private String role;
    private String checkAccId;
    private String savingsAccId;
    private String tradeAccId;

    // Private constructor for singleton pattern
    private Session() {
    }

    // Public method to get the instance of the class
    public static Session getInstance() {
        if (singletonInst == null) {
            singletonInst = new Session();
        }
        return singletonInst;
    }

    // Login the user
    public void login(String userId) {
        this.userId = userId;
        getValues();
        this.login = true;
    }

    // Logout the user
    public void logout() {
        this.userName = null;
        this.userId = null;
        this.role = null;
        this.login = false;
    }

    // Check if the user is logged in
    public boolean isLoggedIn() {
        return login;
    }

    // Getters for user details
    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getAccType(String accId){
        String prefix = userId + "-";
        return accId.substring(prefix.length());
    }

    // Fetch user details from the database
    private boolean getValues() {
        String sql = "SELECT name, role FROM users WHERE userId = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                this.userName = rs.getString("name");
                this.role = rs.getString("role");
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}