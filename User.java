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
        String role="";
        password = BCrypt.hashpw(this.password, BCrypt.gensalt());

        String sql = "SELECT * FROM Users WHERE UserID = ? AND Password = ? AND Role = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {


            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);


            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                if (this.role.equals(Constants.get.bankmanager)){
                    manager = new BankManager(name,userId,password,role);
                }else{
                    user = new BankUser(name,userId,password,role);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}
