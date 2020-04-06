package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOQuestion {

    //поиск ответа по введённому. Вернет экземпляр question
    public static ObservableList<Question> searchQuestion (String questionSelect) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM " + DBConnect.DBName + " WHERE "+ DBConnect.nameCol.QUESTION +" LIKE '" + questionSelect + "';";//было = вместо like
        //Execute SELECT statement
        try {
        //Get ResultSet from dbExecuteQuery method
            ResultSet rsQue = DBConnect.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            ObservableList<Question> questListFind = FXCollections.observableArrayList();
            while (rsQue.next()) {
                Question que = getQuestionFromResultSet(rsQue);
                questListFind.add(que);
            }
            //Return employee object
            return questListFind;
        } catch (SQLException e) {
            System.out.println("While searching an question with " + questionSelect + " question, an error occurred: " + e
                    + ". Method: searchQuestion()");
            //Return exception
            throw e;
        }
    }

    //сформировать экземпляр вопроса из вернувшегося ответа от БД
    private static Question getQuestionFromResultSet(ResultSet rs) throws SQLException {
        Question quest = new Question("aaaa", "bbbb", "ll1","cccc","dddd");
        quest.setId(rs.getString(DBConnect.nameCol.ID));
        quest.setQuestion(rs.getString(DBConnect.nameCol.QUESTION));
        quest.setLastname(rs.getString(DBConnect.nameCol.LASTNAME));//получение по столбцу в БД
        quest.setAnswer(rs.getString(DBConnect.nameCol.ANSWER));
        quest.setComment(rs.getString(DBConnect.nameCol.COMMENT));
        return quest;
    }

    //получить весь список ответов
    public static ObservableList<Question> searchList() throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM " + DBConnect.DBName + ";";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rs = DBConnect.dbExecuteQuery(selectStmt);
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Question> questList = FXCollections.observableArrayList();
            while (rs.next()) {
                Question que = getQuestionFromResultSet(rs);
                questList.add(que);
            }
            //Return employee object
            return questList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e + ". Method: searchList()");
            //Return exception
            throw e;
        }
    }

    //удаление из БД ответа по Id
    public static void deleteQuestWithId (String questId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        " DELETE FROM " + DBConnect.DBName + "\n" +
                        " WHERE " + DBConnect.nameCol.ID + " = " +
                        questId +";\n" +
                        " COMMIT;\n" +
                        "END;";
        //Execute UPDATE operation
        try {
            DBConnect.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e + ". Method: deleteQuestWithId()");
            throw e;
        }
    }

    //вставка данных в БД на основе экзмепляра question. ID - автоинкрементное поле
    public static void insertQuest(Question question) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt = "INSERT INTO " + DBConnect.DBName + "(Question,Listener_last_name,Answer,Comment) VALUES (" + question.record() + ");";
        //Execute DELETE operation
        try {
            DBConnect.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e + ". Method: insertQuest()");
            throw e;
        }
    }


}
