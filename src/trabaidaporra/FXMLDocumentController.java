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
import javafx.util.Callback;

/**
 *
 * @author 144141065
 */
public class FXMLDocumentController implements Initializable {

    String alfabeto[];
    String estados[];
    String sequencia[];
    int proximaLinhaAFD;
    String estadoAtual;

   
    @FXML
    private TableView<Estados> tableAFN;
    @FXML
    private TableView<Estados> tableAFD;
    @FXML
    private TableColumn<Estados, String> atual;
    @FXML
    private TableColumn<Estados, String> valor;
    @FXML
    private TableColumn<Estados, String> alvo;
    @FXML
    private TableColumn<Estados, String> atualAFD;
    @FXML
    private TableColumn<Estados, String> valorAFD;
    @FXML
    private TableColumn<Estados, String> alvoAFD;
    @FXML
    private TableColumn<Estados, String> estadoFinalAFD;
    @FXML
    private TextField txtAlfabeto;
    @FXML
    private TextField txtEstados;
    @FXML
    private TextField estadoInicial;
    @FXML
    private TextField txtSequencia;
    @FXML
    private TextField txtEstadosFinais;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        int i;
//        i = alfabeto.length;
//                i = tableAFN.getItems().size();
        proximaLinhaAFD = 0;
        alfabeto = txtAlfabeto.getText().split(",");
        estados = txtEstados.getText().split(",");
        sequencia = txtSequencia.getText().split(",");

        constroiEstados(estadoInicial.getText());
        System.out.println(estadoAtual);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
        atual.setCellValueFactory(new PropertyValueFactory<>("statusAtual"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        alvo.setCellValueFactory(new PropertyValueFactory<>("statusAlvo"));
        
        atualAFD.setCellValueFactory(new PropertyValueFactory<>("statusAtual"));
        valorAFD.setCellValueFactory(new PropertyValueFactory<>("valor"));
        alvoAFD.setCellValueFactory(new PropertyValueFactory<>("statusAlvo"));
        
        

        tableAFN.setItems(listaDeEstados());
        

        atual.setCellFactory(TextFieldTableCell.forTableColumn());
        valor.setCellFactory(TextFieldTableCell.forTableColumn());
        alvo.setCellFactory(TextFieldTableCell.forTableColumn());
        
        txtAlfabeto.setText("0,1");
        txtEstados.setText("q0,q1,q2");
        estadoInicial.setText("q0");
        txtEstadosFinais.setText("q1");

    }

    private ObservableList<Estados> listaDeEstados() {
        return FXCollections.observableArrayList(
                new Estados("q0", "0", "q1"),
                new Estados("q0", "1", "q1"),
                new Estados("q1", "1", "q1"),
                new Estados("q1", "0", "q1"),
                new Estados("q2", "1", "q1"));
    }

    private ObservableList<Estados> listaDeEstadosAFD() {
        
        return FXCollections.observableArrayList(new Estados("", "", ""));
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
            if (novoEstado == "") {
                continue;
            }

            // Verifica se novoEstado já foi incluído na tabela AFD
            for (i = 0; i <= (proximaLinhaAFD - 1); i++) {
                //if (tabelaTransicaoAFD.getValueAt(i,0).toString().equals(novoEstado))
                if (atualAFD.getCellData(i).toString().equals(estado)
                        && valorAFD.getCellData(i).equals(alfabeto[j])
                        && alvoAFD.getCellData(i).equals(novoEstado)) {
                    break;
                }
            }// Se novoEstado ainda não foi incluído no AFD, então inclui e constrói novos estados
            if (i > (proximaLinhaAFD - 1)) {

                Estados teste = new Estados("", "", "");
                teste.setStatusAtual(estado);
                teste.setValor(alfabeto[j]);
                teste.setStatusAlvo(novoEstado);
                ObservableList<Estados> list = FXCollections.observableArrayList(teste);
                tableAFD.setItems(list);
                tableAFD.refresh();
                estadoFinal = estadoFinalAFND(novoEstado);
                proximaLinhaAFD++;
                constroiEstados(novoEstado);
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

    private String estadoFinalAFND(String s) {
        int i, j;
        String estadosFinais[] = txtEstadosFinais.getText().split(",");
        String estadosAux[] = s.split(",");

        // Verifica se o estado final é um dos estados finais do autômato
        for (i = 0; i < estadosFinais.length; i++) {
            for (j = 0; j < estadosAux.length; j++) {
                if (estadosFinais[i].equals(estadosAux[j])) {
                    return "*";
                }
            }
        }

        return "";
    }
}
