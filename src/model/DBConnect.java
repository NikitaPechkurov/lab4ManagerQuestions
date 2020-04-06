package model;
import com.sun.rowset.CachedRowSetImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DB_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1890;databaseName=answers;user=sa;password=12345";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "12345";
    public static final String DBName = "Questions";

    public static final class nameCol {
        public static final String ID = "id";
        public static final String QUESTION = "Question";
        public static final String LASTNAME = "Listener_last_name";
        public static final String ANSWER = "Answer";
        public static final String COMMENT = "Comment";
    }


    //создание соединения
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("\r\nОшибка: " + e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database CONNECTED!");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage() + ". Method: getDBConnection()");
        }
        return dbConnection;
    }


    //createDbUserTable() - База Данных уже была создана


    //простой запрос к БД.  Вернет cachedResultSet
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Declare statement, resultSet and CachedResultSet as null
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnection = getDBConnection();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create statement
            statement = dbConnection.createStatement();
            //Execute select (query) operation
            resultSet = statement.executeQuery(queryStmt);
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException:Closed Connection: next" error
            //We are using CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e + ". Method: dbExecuteQuery()");
            throw e;
        } finally { //закрыли соединения и данные
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (statement != null) {
                //Close Statement
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        //Return CachedRowSet
        return crs;
    }

    //обновление БД. Ничего не вернет
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Declare statement as null
        Connection dbConnection = null;
        Statement statement = null;
        try {
            //Connect to DB (Establish Oracle Connection)
            dbConnection = getDBConnection();
            //Create Statement
            statement = dbConnection.createStatement();
            //Run executeUpdate operation with given sql statement
            statement.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e + ". Method: dbExecuteUpdate()");
            throw e;
        } finally {
            if (statement != null) {
                //Close statement
                statement.close();
            }
            //Close connection
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }


    //данный метод заполняет массив ответов с помощью переданного sql-запроса
    public ObservableList<Question> create(String query) throws SQLException {

        ObservableList<Question> questions = FXCollections.observableArrayList();
        Connection dbConnection = null;
        Statement statement = null;
        // String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(query);
            // И, если что то было получено, то цикл while сработает
            while (rs.next()) {
                Question question = new Question(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4),rs.getString(5));
                questions.add(question);
            }
        } catch (SQLException e) {
            System.out.println("Ошиюка: " + e.getMessage() + ". Method: create()");
        }
        return questions;
    }


    //данный метод обновляет данные в БД. Использовать после каждого добавления нового вопроса в массив
    public void update(ObservableList<Question> questions) {

        Connection dbConnection = null;
        Statement statement = null;
        // String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER";
        if (questions.isEmpty()) return;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    DBName + questions.get(questions.size()-1).getId()+ questions.get(questions.size()-
                    1).getQuestion() + questions.get(questions.size()-
                    1).getLastname() + questions.get(questions.size()-1).getAnswer() +
            questions.get(questions.size()-1).getComment());
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


}
