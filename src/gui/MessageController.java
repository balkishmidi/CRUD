/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Client;
import entities.Conducteur;
import entities.Contrat;
import entities.Messages;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import services.ClientCRUD;
import services.ConducteurCRUD;
import services.ContratCRUD;
import services.MessageCRUD;

/**
 * FXML Controller class
 *
 * @author BKHmidi
 */

public class MessageController implements Initializable {
 int index = -1;
    @FXML
    private TextField tfexped;
    @FXML
    private TextField tfdest;
    @FXML
    private TextField tfcontenu;
    @FXML
    private Button btn_msg;
    @FXML
    private TextArea tabmessagex;
    @FXML
    private Button btn_delete_msg;
    @FXML
    private TableColumn<Messages, Integer> id_messagetab;
    @FXML
    private TableColumn<Messages, String> Expediteurtab;
    @FXML
    private TableColumn<Messages,String> Destinairetab;
    @FXML
    private TableColumn<Messages, Date> Heuretab;
    @FXML
    private TableColumn<Messages, String> Contenutab;
    @FXML
    private TableView<Messages> Tabmsg;
    ObservableList<Messages> list;
   
    private void clean(ActionEvent event) {
    tfexped.setText(null);
    tfdest.setText(null);
    tfcontenu.setText(null);
}

private void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

@FXML
//private void sendMessage(ActionEvent event) {
    // int id_client = Integer.parseInt(tfdest.getText());
    //int id_conducteur = Integer.parseInt(tfexped.getText());
   // String message = tfcontenu.getText();

    // Create new message and add to database
    //Message m = new Message();
  //  m.setId_client(id_client);
   // m.setId_conducteur(id_conducteur);
    //m.setContenu(message);

   // Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    // Set the date_message field of the Message object
  //  m.setDate_message(timestamp);
//if (tfexped.getText().isEmpty() || tfdest.getText().isEmpty() || tfcontenu.getText().isEmpty()) {
   // showAlert(Alert.AlertType.ERROR, "Champ(s) vide(s)", "Veuillez remplir tous les champs.");
   // return;
//////}//

   // MessageCRUD pcd = new MessageCRUD();
   // pcd.AddMessage(m);
   // UpdateTabMessage();

    // Retrieve the Client and Conducteur objects associated with the message's foreign keys
   // ClientCRUD ccrud = new ClientCRUD();
   // Client c = ccrud.getClientById(id_client);

    //ConducteurCRUD ccrudd = new ConducteurCRUD();
    //Conducteur cd = ccrudd.getConducteurById(id_conducteur);

    // Append message to text area
    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    //String messageText = String.format("%s -> %s : %s (%s)", c.getPrenom_client(), cd.getPrenom_conducteur(), message, m.getDate_message() != null ?
          //  formatter.format(m.getDate_message().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()) :
           // formatter.format(LocalDateTime.now()));
    //tabmessagex.appendText(messageText + "\n");

    // Clear input fields
   // clean(event);
//}






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTabMessage();
    }
    private int id_message;

    private void onMessageSelected(javafx.scene.input.MouseEvent event) {
   String selectedMessage = tabmessagex.getSelectedText();
    // extract the ID of the selected message from the string
    String[] parts = selectedMessage.split(" ");
    String id = parts[0];
    id_message = Integer.parseInt(id);
clear(event); 
    }

    

    private void clear(MouseEvent event) {
    tabmessagex.setText(null);
  
        
    }

   


 


    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = Tabmsg.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tabmessagex.setText(Contenutab.getCellData(index).toString());
        tfexped.setText(Expediteurtab.getCellData(index).toString());
        tfdest.setText(Destinairetab.getCellData(index).toString());
        tfcontenu.setText(Contenutab.getCellData(index).toString());
       

    }
    public void UpdateTabMessage() {
    MessageCRUD cv = new MessageCRUD();

    id_messagetab.setCellValueFactory(new PropertyValueFactory<>("id_message"));
    Expediteurtab.setCellValueFactory(new PropertyValueFactory<>("expediteur"));
    Destinairetab.setCellValueFactory(new PropertyValueFactory<>("destinataire"));
    Heuretab.setCellValueFactory(new PropertyValueFactory<>("date_message"));
    Contenutab.setCellValueFactory(new PropertyValueFactory<>("contenu"));


    list = cv.getDataMsg();
    Tabmsg.setItems(list);
}

    @FXML
 

  void deletemessage(ActionEvent event) {
         int selectedIndex = Tabmsg.getSelectionModel().getSelectedIndex();
    if (selectedIndex < 0) {
        // No message selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a message to delete.");
        alert.showAndWait();
        return;
    }
    // Get the selected message from the TableView
    Messages message = Tabmsg.getSelectionModel().getSelectedItem();

    // Display a confirmation dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Message");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this message?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        // User clicked OK, delete the message from the database
      
        MessageCRUD messageCRUD = new MessageCRUD();
messageCRUD.DeleteMessage(message.getId_message());
  

        // Delete the message from the TableView
        Tabmsg.getItems().remove(selectedIndex);
         UpdateTabMessage() ;
    }
  }
    
    
    
        
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
   
    


  

        

    

