package sample;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DBConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    //public static final String DB_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1890;databaseName=answers;user=sa;password=12345";
    //public static final String DB_Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MANAGER OF QUESTIONS");
        primaryStage.setScene(new Scene(root, 752, 505));
        primaryStage.show();

        //отладка подлючения ↓↓↓↓↓↓↓↓

        /*String instanceName = "Никита-ПК\\Никита";
        String databaseName = "answers";
        String userName = "sa";
        String pass = "12345";
        //Ошибка: Ошибка входа пользователя "root". ClientConnectionId:c6c5bd54-fa89-41c3-bb82-e8b1e159d449
        //вход в sql сделал по идентификации user password, брандмауэр отключен
        //порт TCP/IP - 1890
        try {
            Class.forName(DB_Driver);
        }
        catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage() + "\r\n");
        }
        try {
            Connection con = DriverManager.getConnection(DB_URL,userName,pass);
            System.out.println("Соединение с СУБД выполнено.");
            Statement statement = con.createStatement();

            ResultSet rs = statement.executeQuery("select * from Questions;");
            //ResultSet получает результирующую таблицу
            int x = rs.getMetaData().getColumnCount();
            //Resultset.getMetaData() получаем информацию
            //результирующей таблице
            while (rs.next())
            {
                for (int i = 1; i <= x; i++)
                {
                    System.out.print(rs.getString(i) + "\r\n");
                }
                System.out.println();
            }
            System.out.println();
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (con != null) con.close();
            //con.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
        }
        catch (SQLException e){
            System.out.println("Ошибка: " + e.getMessage());
        }*/

    }


    public static void main(String[] args) {
        launch(args);
    }
}
