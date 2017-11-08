/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabaidaporra;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author 144141065
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TableView<Estados> tableAFN;
    @FXML
    private TableColumn<Estados, String> atual;
    @FXML
    private TableColumn<Estados, String> valor;
    @FXML
    private TableColumn<Estados, String> alvo;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        atual.setCellValueFactory(
                new PropertyValueFactory<>("statusAtual"));
        valor.setCellValueFactory(
                new PropertyValueFactory<>("valor"));
        alvo.setCellValueFactory(
                new PropertyValueFactory<>("statusAlvo"));
        
        tableAFN.setItems(listaDeEstados());
        
        atual.setCellFactory(TextFieldTableCell.forTableColumn());
        valor.setCellFactory(TextFieldTableCell.forTableColumn());
        alvo.setCellFactory(TextFieldTableCell.forTableColumn());
        
       
    }    

    private ObservableList<Estados> listaDeEstados() {
        return FXCollections.observableArrayList(
                new Estados(null, "0", "q0"),
                new Estados("q0", "0", "q1"),
                new Estados("q0", "0", "q2"));
    }
    
}

