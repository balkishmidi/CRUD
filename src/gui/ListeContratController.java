/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import entities.Contrat;
import entities.Message;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ContratCRUD;
import services.MessageCRUD;

/**
 * FXML Controller class
 *
 * @author BKHmidi
 */
public class ListeContratController implements Initializable {

    @FXML
    private TextField tfidad;
    @FXML
    private TextField tfidcon;
    @FXML
    private TextField tfdd;
    @FXML
    private TextField tfdf;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfstatut;
    @FXML
    private Button btn;
    @FXML
    private TextField tf_Contrat_D;
    @FXML
    private TableView<Contrat> TabContrat;
    @FXML
    private TableColumn<Contrat, Integer> id_contrattab;
    @FXML
    private TableColumn<Contrat, Integer> id_contab;
    @FXML
    private TableColumn<Contrat, Integer> id_adtab;
    @FXML
    private TableColumn<Contrat, Date> ddtab;
    @FXML
    private TableColumn<Contrat, Date> dftab;
    @FXML
    private TableColumn<Contrat, Integer> prixtab;
    @FXML
    private TableColumn<Contrat, String> statuttab;

    ObservableList<Contrat> list;
    int index = -1;
    @FXML
    private Button btn_Supprimer_contrat;
    @FXML
    private Button btn_update_contrat;
    @FXML
    private TextField tfidContrat;
    @FXML
    private TextField tfsearch_contrat;
   private ContratCRUD contratCRUD;
    @FXML
    private DatePicker dpdd;
    @FXML
    private DatePicker dpdf;
    @Override

    public void initialize(URL location, ResourceBundle resources) {

        UpdateTabContrat();
        
        // Set the field as non-editable
         contratCRUD = new ContratCRUD();
    TabContrat.setItems(FXCollections.observableArrayList(contratCRUD.contratList()));


    }

    @FXML
    private void savecontrat(ActionEvent event) {

        int residcon = Integer.parseInt(tfidcon.getText());
        int residad = Integer.parseInt(tfidad.getText());
        LocalDate dd = dpdd.getValue();
        LocalDate df = dpdf.getValue();
        int resprix = Integer.parseInt(tfprix.getText());
        String resstatut = tfstatut.getText();
        ContratCRUD pcd = new ContratCRUD();
     if (dd == null || df == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout du contrat");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner les dates de début et de fin du contrat");
        alert.showAndWait();
        return;
    }
     
         // Check if start date is earlier than end date
    if (dd.isAfter(df)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur lors de l'ajout du contrat");
        alert.setHeaderText(null);
        alert.setContentText("La date de fin doit être après la date de début du contrat");
        alert.showAndWait();
        return;
    }
    Date date_debut = Date.valueOf(dd);
    Date date_fin = Date.valueOf(df);
  
        Contrat c = new Contrat(residcon, residad,date_debut, date_fin, resprix, resstatut);

        // Check if the Contrat already exists in the database
    if (pcd.contratExists(c)) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Le contrat existe déjà");
        alert.setContentText("Le contrat que vous essayez d'ajouter existe déjà dans la base de données.");
        alert.showAndWait();
          clean(event);
        UpdateTabContrat();
    } else {
        try {
            pcd.AddContrat(c);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText("Contrat ajouté");
            alert.setContentText("Le contrat a été ajouté avec succès.");
            alert.showAndWait();
           
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur lors de l'ajout du contrat");
            alert.setContentText("Une erreur est survenue lors de l'ajout du contrat dans la base de données.");
            alert.showAndWait();
        }
       
        UpdateTabContrat();
    }
     clean(event);
    }

    @FXML
    private void Supprimercontrat(ActionEvent event) {

        ContratCRUD pcd = new ContratCRUD();

        list = pcd.getDataC();

        if (tf_Contrat_D.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun contrat supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le contrat ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcd.DeleteContrat(Integer.parseInt(tf_Contrat_D.getText()));
            System.out.println("Contrat supprimé !");
            UpdateTabContrat();
        }

        clean(event);

    }

    private Object Myconnection() {
        return null;

    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) {
        index = TabContrat.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        tfidContrat.setText(id_contrattab.getCellData(index).toString());
tfidad.setText(id_adtab.getCellData(index).toString());
tfidcon.setText(id_contab.getCellData(index).toString());
tfprix.setText(prixtab.getCellData(index).toString());
tfstatut.setText(statuttab.getCellData(index));

// Set the value for dpdd
String ddString = ddtab.getCellData(index).toString();
if (ddString != null && !ddString.isEmpty()) {
    LocalDate dd = LocalDate.parse(ddString);
    dpdd.setValue(dd);
}

// Set the value for dpdf
String dfString = dftab.getCellData(index).toString();
if (dfString != null && !dfString.isEmpty()) {
    LocalDate df = LocalDate.parse(dfString);
    dpdf.setValue(df);}

    }

    @FXML
    private void clean(ActionEvent event) {
        tfidContrat.setText(null);
    tf_Contrat_D.setText(null);
    tfidcon.setText(null);
    tfidad.setText(null);
    dpdd.setValue(LocalDate.MIN); // Set to empty value
    dpdf.setValue(LocalDate.MIN); // Set to empty value
    tfprix.setText(null);
    tfstatut.setText(null);
   
     

    }

    public void UpdateTabContrat() {
        ContratCRUD cv = new ContratCRUD();

        id_contrattab.setCellValueFactory(new PropertyValueFactory<>("id_contrat"));
        id_adtab.setCellValueFactory(new PropertyValueFactory<>("id_admin"));
        id_contab.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));
        ddtab.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        dftab.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        prixtab.setCellValueFactory(new PropertyValueFactory<>("prix"));
        statuttab.setCellValueFactory(new PropertyValueFactory<>("statut"));

        list = cv.getDataC();
        TabContrat.setItems(list);

    }
    
    
    
    
    
    
    

    @FXML
    private void updateContrat(ActionEvent event) {

    int residcontrat = Integer.parseInt(tfidContrat.getText());
    int residcon = Integer.parseInt(tfidcon.getText());
    int residad = Integer.parseInt(tfidad.getText());
    LocalDate dd = dpdd.getValue();
    LocalDate df = dpdf.getValue();
    int resprix = Integer.parseInt(tfprix.getText());
    String resstatut = tfstatut.getText();
    ContratCRUD pcd = new ContratCRUD();
    Date date_dd = java.sql.Date.valueOf(dd);
    Date date_df = java.sql.Date.valueOf(df);
    Contrat c = new Contrat(residcontrat, residcon, residad, date_dd, date_df, resprix, resstatut);

    try {
        pcd.UpdateContrat(c);

        JOptionPane.showMessageDialog(null, "Contrat modifié");
        UpdateTabContrat();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erreur lors de la modification du contrat");
    }
    clean(event);
    UpdateTabContrat();}
   
   
 
}
