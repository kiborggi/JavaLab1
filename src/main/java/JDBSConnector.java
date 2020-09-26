import java.sql.*;
import java.util.*;

public class JDBSConnector {
    Connection connection;
    Statement statement;

    public void createConnect() {
        {
            try {

                connection = DriverManager.getConnection("jdbc:mysql://localhost/vlad?serverTimezone=Europe/Moscow&useSSL=false","vlad","vlad");
                statement = connection.createStatement();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public JDBSConnector () throws SQLException {
    }

    public void addUser(User user){
        try {
            String name = user.getName();
            String pass = user.getPass();
            String role = user.getRole();
            statement.executeUpdate("INSERT INTO `users` (`id`, `name`, `pass`, `role`) VALUES (NULL, '" + name + "','" + pass + "','" + role + "');" );
            System.out.println("Пользователь " + name + " добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteByID (int id){
        try {

            statement.executeUpdate("DELETE FROM `users` WHERE `users`.`id` = " + id +";" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeRoleById (int id, String role){
        try {
            statement.executeUpdate(" UPDATE `users` SET `role` = '"+ role +"' WHERE `users`.`id` = "+id + ";" );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> show(){
        ArrayList<User> list = new ArrayList<User>();



        try {
            ResultSet rs =   statement.executeQuery("SELECT * FROM `users`;" );
            while (rs.next()){
                list.add(new User(rs.getInt(1) , rs.getString(2), rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
