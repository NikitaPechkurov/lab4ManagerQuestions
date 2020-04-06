package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DAOQuestion;
import model.DBConnect;
import model.Question;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Controller implements Initializable{

    public Button findButton;//кнопка поиска
    public TableView table;//вся таблица
    public TextField findTextField;//поле с текстом поиска
    public ObservableList<Question> questData = FXCollections.observableArrayList();//основной список отображения
    public Button fillButton;
    public TableColumn questId;
    public TableColumn questQuest;
    public TableColumn questLastname;//столбцы с данными
    public TableColumn questAnswer;
    public TableColumn questComm;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        questId.setCellValueFactory(new PropertyValueFactory<Question, String>("id"));
        questQuest.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        questLastname.setCellValueFactory(new PropertyValueFactory<Question, String>("lastname"));//получение по названию свойства
        questAnswer.setCellValueFactory(new PropertyValueFactory<Question, String>("answer"));
        questComm.setCellValueFactory(new PropertyValueFactory<Question, String>("comment"));
    }

    public void findQuestion(ActionEvent actionEvent) {
        String findString, findAdd="%";
        findString = findTextField.getText()+findAdd;
        questData.removeAll();
        try{
            questData = DAOQuestion.searchQuestion(findString);
            table.setItems(questData);
        }catch (SQLException sqlEx){
            System.out.println("Error occurred while getting questions information from DB.\n" + sqlEx + ". Method: findQuestion()");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //тут сделать поиск
        //(найти как по первым словам сравнивать в БД и делать выборку)
    }

    public void fillTable(ActionEvent actionEvent) {
        //добавляются не все данные в таблицу, а только половина
        //загуглить "Отображаются не все данные в таблицу table observablelist java"
        try {
            //Get all Questions information
            //DAOQuestion.insertQuest(new Question ("1", "1", "1","1","1"));
            questData = DAOQuestion.searchList();
            // empData.add(new Story("1", "1", "111111"));
            // empData.add(new Story("2", "2", "222222"));
            //Populate Questions on TableView
            table.setItems(questData);
        } catch (SQLException e) {
            System.out.println("Error occurred while getting questions information from DB.\n" + e + ". Method: fillTable()");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
