import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


class LoginListener implements ActionListener{

    private JTextField userName;
    private JPasswordField password;

    public LoginListener(JTextField userName, JPasswordField password){
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String user = userName.getText();
        String passwd = new String(password.getPassword());
        System.out.println( "Login Clicked" );

        if (checkLogin(user, passwd)) {
            System.out.println("Login successful");
            // Assuming UserDefPanel is a JPanel you switch to upon successful login
            Session.getInstance().login(user);
            Project_UI.switchPanel(new UserDefPanel());   
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            System.out.println("Login failed");
        }
    }

    private boolean checkLogin(String username, String password) {
        // Database connection and query setup
        String sql = "SELECT * FROM users WHERE userId = ? AND password = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            // Check if the query returned any rows
            if(rs.next()) return true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}