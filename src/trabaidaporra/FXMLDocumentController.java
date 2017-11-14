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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 *
 * @author 144141065
 */
public class FXMLDocumentController implements Initializable {

    String alfabeto[];
    String estados[];
    String sequencia[];

    String estadoAtual;

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
    private TextField txtAlfabeto;
    @FXML
    private TextField txtEstados;
    @FXML
    private TextField estadoInicial;
    @FXML
    private TextField txtSequencia;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        int i;
//        i = alfabeto.length;
//                i = tableAFN.getItems().size();
        
        alfabeto = txtAlfabeto.getText().split(",");
        estados = txtEstados.getText().split(",");
        sequencia = txtSequencia.getText().split(",");
        

        constroiEstados(estadoInicial.getText());
        System.out.println(estadoAtual);

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
//        txtAlfabeto.setText("0,1");
//        txtEstados.setText("q0,q1,q2");
//        estadoInicial.setText("q0");

    }

    private ObservableList<Estados> listaDeEstados() {
        return FXCollections.observableArrayList(
                new Estados("q0", "0", ""),
                new Estados("q0", "0", ""),
                new Estados("q0", "0", ""),
                new Estados("q0", "0", ""),
                new Estados("q0", "0", ""));
    }

    private void constroiEstados(String estado) {
        String estados[];
        int i, j, k;
        String novoEstado, estadoFinal;

        for (j = 0; j < alfabeto.length; j++) {
            novoEstado = "";
            estados = estado.split(",");

            for (k = 0; k < estados.length; k++) {
                for (i = 0; i < tableAFN.getItems().size(); i++) {

                    if (atual.getCellData(i).equals(estados[k])
                            && valor.getCellData(i).equals(alfabeto[j])) {

                        if (!contemEstado(novoEstado, alvo.getCellData(i))) {
                            if (novoEstado != "") {
                                novoEstado += ",";
                            }
                            novoEstado += alvo.getCellData(i);
                        }

                    }

                }

            }

        }
    }

    private boolean contemEstado(String estado1, String estado2) {
        int i;
        String estados[] = estado1.split(",");

        for (i = 0; i < estados.length; i++) {
            if (estados[i].equals(estado2)) {
                break;
            }
        }
        if (i < estados.length) {
            return true;
        } else {
            return false;
        }
    }
}
