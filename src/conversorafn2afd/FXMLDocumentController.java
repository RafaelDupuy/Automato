package conversorafn2afd;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author 144141065
 */
public class FXMLDocumentController implements Initializable {

    String alfabeto[],estados[],sequencia[];
    String estadoAtual;
    int proximaLinhaAFD;
    ObservableList<Estados> listaTabelaAFD = FXCollections.observableArrayList();
    

    //Variaveis interface de usuario Java FX
    @FXML
    private TextField fxAlfabeto;
    @FXML
    private TextField fxEstados;
    @FXML
    private TextField fxEstadoInicial;
    @FXML
    private TextField fxSequencia;
    @FXML
    private TextField fxEstadosFinais;
    @FXML
    private TableView<Estados> fxTabelaAFN;
    @FXML
    private TableColumn<Estados, String> fxAtualAFN;
    @FXML
    private TableColumn<Estados, String> fxValorAFN;
    @FXML
    private TableColumn<Estados, String> fxAlvoAFN;
    @FXML
    private TableView<Estados> fxTabelaAFD;
    @FXML
    private TableColumn<Estados, String> fxAtualAFD;
    @FXML
    private TableColumn<Estados, String> fxValorAFD;
    @FXML
    private TableColumn<Estados, String> fxAlvoAFD;
    @FXML
    private TableColumn<Estados, String> fxEstadosFinaisAFD;
    @FXML
    private TextArea fxResultado;
    @FXML
    private ImageView rightwrong;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //valores Padroes tabela AFN
        fxAtualAFN.setCellValueFactory(new PropertyValueFactory<>("statusAtual"));
        fxValorAFN.setCellValueFactory(new PropertyValueFactory<>("valor"));
        fxAlvoAFN.setCellValueFactory(new PropertyValueFactory<>("statusAlvo"));
        fxAtualAFN.setCellFactory(TextFieldTableCell.forTableColumn());
        fxValorAFN.setCellFactory(TextFieldTableCell.forTableColumn());
        fxAlvoAFN.setCellFactory(TextFieldTableCell.forTableColumn());
        
        fxTabelaAFN.setItems(listaDeEstados());
        
        //Valores Tabela AFD
        fxAtualAFD.setCellValueFactory(new PropertyValueFactory<>("statusAtual"));
        fxValorAFD.setCellValueFactory(new PropertyValueFactory<>("valor"));
        fxAlvoAFD.setCellValueFactory(new PropertyValueFactory<>("statusAlvo"));
        fxEstadosFinaisAFD.setCellValueFactory(new PropertyValueFactory<>("estadoFinal"));    
        
        //Valores Padroes TextField
        fxAlfabeto.setText("a,b");
        fxEstados.setText("q0,q1,q2,q3");
        fxEstadoInicial.setText("q0");
        fxSequencia.setText("b,b,a,a,a,a");
        fxEstadosFinais.setText("q3");

        //Atualizar tabela quando valores forem modificados
        fxAtualAFN.setOnEditCommit((CellEditEvent<Estados, String> t) -> {
            ((Estados) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setStatusAtual(t.getNewValue());
        });
        fxValorAFN.setOnEditCommit((CellEditEvent<Estados, String> t) -> {
            ((Estados) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setValor(t.getNewValue());
        });
        fxAlvoAFN.setOnEditCommit((CellEditEvent<Estados, String> t) -> {
            ((Estados) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setStatusAlvo(t.getNewValue());
        });
    }
    
    //Botao Construir AFD
    @FXML
    private void construirAFD(ActionEvent event) {
        
        //Limpar tabela e campo de resposta
        listaTabelaAFD.clear();
        fxResultado.setText("");
        fxTabelaAFN.refresh();
        
        proximaLinhaAFD = 0;
        alfabeto = fxAlfabeto.getText().split(",");
        estados = fxEstados.getText().split(",");
        sequencia = fxSequencia.getText().split(",");
        
         fxResultado.appendText("Sequencia de Conversão do AFN..." + "\n\n");
        fxResultado.appendText("Estado criado a partir de: (" + fxEstadoInicial.getText() + ")\n");
        
        constroiEstados(fxEstadoInicial.getText());
        
       fxResultado.appendText("\n AFN convertido para AFD");
       
       String pathToImageSortBy = "img/Slide1.PNG";
            Image wrong = new Image(pathToImageSortBy);
            rightwrong.setImage(wrong);
            rightwrong.setVisible(false);
        
        
    }
    
    //Botao Verificar
    @FXML
    private void verificarPalavra(ActionEvent event) {
        
        int i;
        rightwrong.setVisible(true);
        fxResultado.setText("");    // Limpa os resultados
        sequencia = fxSequencia.getText().split(",");

        fxResultado.appendText("Verificado...\n\n");
        String p = fxEstadoInicial.getText();    // Seleciona o estado inicial
        fxResultado.appendText("Leu '" +sequencia[0]+ "' em (" + p+") e foi ");
        
        for (i = 0; i < sequencia.length; i++) { // Encontra o próximo estado
                        
            p = proximoEstado(p, sequencia[i]);
            fxResultado.appendText(" para (" + p + ")");
            
            int j = i + 1;
            
            if(j < sequencia.length){
                fxResultado.appendText("\nLeu '" +sequencia[j]+ "' em (" + p+") e foi "); }       
            }
        if (fxEstadosFinaisAFD(p)) {
            fxResultado.appendText("\n\n" + "Entrada aceita!");
            fxResultado.setScrollTop(Double.MAX_VALUE);
            String pathToImageSortBy = "img/Imagem2.png";
            Image right = new Image(pathToImageSortBy);
            rightwrong.setImage(right);
        } else {
            fxResultado.appendText("\n\n" + "Entrada rejeitada!");
            fxResultado.appendText("\n(" +p+ ") Não é estado final!");
            fxResultado.setScrollTop(Double.MAX_VALUE);
            String pathToImageSortBy = "img/Imagem1.png";
            Image wrong = new Image(pathToImageSortBy);
            rightwrong.setImage(wrong);

     
            // Fim da execução do autômato
        }
        
    }
    
    private ObservableList<Estados> listaDeEstados() {
        return FXCollections.observableArrayList(
                new Estados("q0", "a", "q0", ""),
                new Estados("q0", "a", "q1", ""),
                new Estados("q0", "b", "q0", ""),
                new Estados("q1", "a", "q2", ""),
                new Estados("q2", "a", "q3", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""),
                new Estados("", "", "", ""));
    }

    private void constroiEstados(String estado) {
        fxTabelaAFD.setItems(listaTabelaAFD);
        fxTabelaAFD.refresh();

        String estados[] = null;
        int i, j, k;
        String novoEstado, estadoFinal;

        for (j = 0; j < alfabeto.length; j++) {
            novoEstado = "";
            estados = estado.split(",");
            int tamanhoAFN = fxTabelaAFN.getItems().size();

            for (k = 0; k < estados.length; k++) {
                for (i = 0; i < tamanhoAFN; i++) {

                    if (fxAtualAFN.getCellData(i).equals(estados[k])
                            && fxValorAFN.getCellData(i).equals(alfabeto[j])) {

                        if (!contemEstado(novoEstado, fxAlvoAFN.getCellData(i))) {
                            if (novoEstado != "") {
                                novoEstado += ",";
                            }
                            novoEstado += fxAlvoAFN.getCellData(i);
                        }
                    }
                }
            }


            // Verifica se novoEstado já foi incluído na tabela AFD
            for (i = 0; i <= (proximaLinhaAFD - 1); i++) {
                //if (tabelaTransicaoAFD.getValueAt(i,0).toString().equals(novoEstado))
                if (fxAtualAFD.getCellData(i).equals(estado)
                        && fxValorAFD.getCellData(i).equals(alfabeto[j])
                        && fxAlvoAFD.getCellData(i).equals(novoEstado)) {
                    break;
                }

            }

            // Se novoEstado ainda não foi incluído no AFD, então inclui e constrói novos estados
            if (i > (proximaLinhaAFD - 1)) {

                Estados auxiliarAFD = new Estados("", "", "", "");
                auxiliarAFD.setStatusAtual(estado);
                auxiliarAFD.setValor(alfabeto[j]);
                auxiliarAFD.setStatusAlvo(novoEstado);
                estadoFinal = estadoFinalAFND(novoEstado);
                auxiliarAFD.setEstadoFinal(estadoFinal);
                listaTabelaAFD.add(auxiliarAFD);
                proximaLinhaAFD++;
                
                fxResultado.appendText("Nova regra do AFD: (" + estado + ")  |  " + alfabeto[j] + "  |  (" + novoEstado + ")  " + estadoFinal + "\n");
                fxResultado.appendText("Estado criado a partir de: (" + novoEstado + ")\n");
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
        String estadosFinais[] = fxEstadosFinais.getText().split(",");
        String estadosAux[] = s.split(",");

        // Verifica se o estado final é um dos estados finais do autômato
        for (i = 0; i < estadosFinais.length; i++) {
            for (j = 0; j < estadosAux.length; j++) {
                if (estadosFinais[i].equals(estadosAux[j])) {
                    return "FINAL";
                }
            }
        }
        return "";
    }

    private String proximoEstado(String p1, String p2) {
        int i;
        String s = null;

        // Encontra uma entrada na tabela de transição de estados
        for (i = 0; i < fxTabelaAFD.getItems().size(); i++) {
            if (fxAtualAFD.getCellData(i).equals(p1)
                    && fxValorAFD.getCellData(i).equals(p2)) {
                s = fxAlvoAFD.getCellData(i);
                break;
            }
        }
        return s;
    }

    private boolean fxEstadosFinaisAFD(String s) {
        int i;
        
        for (i = 0; i < fxTabelaAFD.getItems().size(); i++) {
            if (fxAlvoAFD.getCellData(i).equals(s)
                    && fxEstadosFinaisAFD.getCellData(i).equals("FINAL")) {
                return true;
            }
        }
        return false;
    }
}
