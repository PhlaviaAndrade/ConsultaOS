/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.DadosIniciais;
import model.utils;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.BloqueioDataValor;
import CurrencyField.CurrencyField;
import dao.ConsultasSQL;
import java.sql.Date;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.BloqueioDatasCaptura;
import model.TabelaOS;
import model.TextFieldFormatter;
import poi.GeraExcel;
import subsidioCartao.MainApp;

/**
 * FXML Controller class
 *
 * @author f3295813
 */
public class TelaPrincipalController implements Initializable {

    Thread t1;
    Thread t2;
    Thread t3;

    utils util = new utils();
    
      
    //=====================================================================================================
    @FXML
    private AnchorPane AnchorPanePrincipal;
    @FXML
    private ScrollPane ScrollPaneEsquerdo;
   
    @FXML
    private JFXTextField txtChave;
    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane AP_ParcelamentoFatura;

    @FXML
    private Tab tab_ParcelamentoFatura;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabInformacoes;
   
   
    @FXML
    private Tab tab_BloqueioRestricao;
    @FXML
    private AnchorPane AP_BloqueioRestricao;
       
    AtribuicaoController atribuicao = new AtribuicaoController();
    @FXML
    private TextField txt_dtinicio;
    @FXML
    private TextField txt_dtfim;
    @FXML
    private JFXButton btn_atualizar;
    @FXML
    private TableColumn<TabelaOS, Integer> col_AnoOS;
    @FXML
    private TableColumn<TabelaOS, Integer> col_NumeroOS;
    @FXML
    private TableColumn<TabelaOS, String> col_NumeroBem;
    @FXML
    private TableColumn<TabelaOS, String> col_Status;
    @FXML
    private TableColumn<TabelaOS, Integer> col_Prefixo;
    @FXML
    private TableColumn<TabelaOS, Integer> col_SB;
    @FXML
    private TableColumn<TabelaOS, String> col_NomeDependencia;
    @FXML
    private TableColumn<TabelaOS, String> col_ProblemaApresentado;
    @FXML
    private TableColumn<TabelaOS, Date> col_DataHoraAbertura;
    @FXML
    private TableColumn<TabelaOS, String> col_UF;
    @FXML
    private TableColumn<TabelaOS, String> col_NumeroContrato;
    @FXML
    private TableColumn<TabelaOS, String> col_Fornecedor;
    @FXML
    private TableColumn<TabelaOS, String> col_NrSOL;
    @FXML
    private TableColumn<TabelaOS, String> col_TipoManutencao;
    @FXML
    private JFXButton btn_excel;
    @FXML
    private TableView<TabelaOS> tb_os;
    @FXML
    private TableColumn<TabelaOS, String> col_os;
    MainApp mainApp;
    String matricula;

    public TelaPrincipalController() throws PropertyVetoException {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        matricula = System.getProperty("user.name").toUpperCase();
        txtChave.setText(matricula);
        Image imagem = new Image("/views/imagens/logo.png");
        imageView.setImage(imagem);

       
        CurrencyField cur = new CurrencyField();

        ScrollPane sp = new ScrollPane();
        sp.setContent(sp);


// Usando esta property você pode ver as mudanças no valor do textfield
        cur.amountProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.doubleValue());
            }
        });
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

//    private void pegaInformacoesIniciais(ActionEvent event) throws Throwable {
//        
//       
//        
//        t1 = new Thread(() -> {
//            
//           
//            
//            listaPlastico.clear();
//
//            
//
//            try {
//
//                cpf = txt_CPF.getText().trim().replace(".", "").replace("-", "");
//                npj = txt_NPJ.getText().trim().replace(".", "").replace("-", "");
//
//              
//
//                    if (cpf != null && cpf.length() != 0 && cpf.length() == 11 && npj != null && npj.length() != 0) {
//
//                        try {
//                            //                    JanelaSisbb sisbb = new JanelaSisbb();
//                            this.sisbb = new JanelaSisbb();
//                            sisbb.setTamanho(600, 500);
//                            listaPlastico = capturas.dadosIniciais(sisbb, cpf);
//                        } catch (PropertyVetoException ex) {
//                            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//                        } catch (Throwable ex) {
//                            Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        
//
//                       
//                        if (!listaPlastico.isEmpty()) {
//
//                            autor = listaPlastico.get(0).getAutor();
//                            txt_Autor.setText(autor);
//
//                            colDadosGerais_Selecao.setCellValueFactory(new PropertyValueFactory<>("selected"));
//                            colDadosGerais_Conta.setCellValueFactory(new PropertyValueFactory<>("conta"));
//                            colDadosGerais_TipoPlastico.setCellValueFactory(new PropertyValueFactory<>("tipoPlastico"));
//                            colDadosGerais_NrCartao.setCellValueFactory(new PropertyValueFactory<>("nrCartao"));
//                            colDadosGerais_NomePlastico.setCellValueFactory(new PropertyValueFactory<>("nomePlastico"));
//                            colDadosGerais_Restricao.setCellValueFactory(new PropertyValueFactory<>("restricao"));
//                            colDadosGerais_Vencido.setCellValueFactory(new PropertyValueFactory<>("vencido"));
//                            colDadosGerais_Selecao.setCellFactory(CheckBoxTableCell.forTableColumn(colDadosGerais_Selecao));
//                           
//
//                            // coluna editável
////                        colDadosGerais_Conta.setCellFactory(TextFieldTableCell.<DadosIniciais>forTableColumn());
////                        colDadosGerais_Conta.setOnEditCommit(
////                                (CellEditEvent<DadosIniciais, String> t) -> {
////                                    ((DadosIniciais) t.getTableView().getItems().get(
////                                            t.getTablePosition().getRow())).setConta(t.getNewValue());
////                                });
////
////                        
//                            colDadosGerais_Selecao.setCellValueFactory((CellDataFeatures<DadosIniciais, Boolean> param) -> {
//                                DadosIniciais dados = param.getValue();
//                                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(dados.isSelected());
//                                // Note: singleCol.setOnEditCommit(): Not work for
//                                // CheckBoxTableCell.
//                                // When "Single?" column change.
//                                booleanProp.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                                    dados.setSelected(newValue);
//                                });
//                                return booleanProp;
//                            });
//                            colDadosGerais_Selecao.setCellFactory((TableColumn<DadosIniciais, Boolean> p) -> {
//                                CheckBoxTableCell<DadosIniciais, Boolean> cell = new CheckBoxTableCell<DadosIniciais, Boolean>();
//                                return cell;
//                            });
//
//                            observableListPlastico = FXCollections.observableList(listaPlastico);
//
//                            tableDadosGerais.setItems(observableListPlastico);
//
//                            util.alertaGeralInformacao("Atenção", "Captura finalizada!", null);
//
//                        } else {
//                            
//                              Platform.runLater(() -> {
//
//                            util.alertaGeral("Atenção", "CPF incorreto ou o cliente não possui lançamentos.", "Gentileza verificar os dados");
//                
//                 });
//
//                        }
//                    }else{
//                        
//                        Platform.runLater(() -> { 
//                      util.alertaValidacao("Digite o NPJ e o CPF com valores númericos e quantidade suficiente de dígitos");
//                       }); 
//                        
//                        
//                    }
//               
//
//            } catch (Exception e) {
//                System.out.println(e);
//            } catch (Throwable ex) {
//                Logger.getLogger(TelaPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        });
//
//        t1.start();
//
//    }

  

   

//    private void inputDataKeyTyped() throws ParseException {
//
//        TextFieldFormatter tff = new TextFieldFormatter();
//        tff.setMask("##.##.####");
//        tff.setCaracteresValidos("0123456789");
//        tff.setTf(txtData);
//        tff.formatter();
//
//    }

//    private void inputDataKeyTypedDataInicial() throws ParseException {
//
//        TextFieldFormatter tff = new TextFieldFormatter();
//        tff.setMask("##/####");
//        tff.setCaracteresValidos("0123456789");
//        tff.setTf(txtDataInicio);
//        tff.formatter();
//
//    }
//
//    private void inputDataKeyTypedDataFinal() throws ParseException {
//
//        TextFieldFormatter tff = new TextFieldFormatter();
//        tff.setMask("##/####");
//        tff.setCaracteresValidos("0123456789");
//        tff.setTf(txtDataFim);
//        tff.formatter();
//
//    }
//
//       private void inputDataKeyTypedDataFinalRestricao(KeyEvent event) {
//        
//        TextFieldFormatter tff = new TextFieldFormatter();
//        tff.setMask("##/####");
//        tff.setCaracteresValidos("0123456789");
//        tff.setTf(txt_DataFimRestricao);
//        tff.formatter();
//        
//    }
//
//    private void inputDataKeyTypedDataInicialRestricao(KeyEvent event) {
//        
//        
//        TextFieldFormatter tff = new TextFieldFormatter();
//        tff.setMask("##/####");
//        tff.setCaracteresValidos("0123456789");
//        tff.setTf(txt_DataInicioRestricao);
//        tff.formatter();
//    }

    

    @FXML
    private void selecionaAtribuicao(Event event) {
        
        String path = "/view/AtribuicaoEstados.fxml";
        atribuicao =  (AtribuicaoController) mainApp.showCenterAnchorPaneWithReturn(path, atribuicao, AP_ParcelamentoFatura);


    }

        @FXML
    private void atualizarTabela(ActionEvent event) throws Throwable {
        ConsultasSQL sql = new ConsultasSQL();
        String UF = "";

        if (txt_dtinicio.getText().length() != 0 && txt_dtfim.getText().length() != 0) {

            List<TabelaOS> populaTabela = sql.atualizarTabela(txt_dtinicio.getText(), txt_dtfim.getText(), UF);

            if (!populaTabela.isEmpty()) {

                ObservableList<TabelaOS> observableListOS = FXCollections.observableList(populaTabela);

                col_os.setCellValueFactory(new PropertyValueFactory<>("OS"));
                col_AnoOS.setCellValueFactory(new PropertyValueFactory<>("AnoOS"));
                col_NumeroOS.setCellValueFactory(new PropertyValueFactory<>("NumeroOS"));
                col_NumeroBem.setCellValueFactory(new PropertyValueFactory<>("NumeroBem"));
                col_Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
                col_Prefixo.setCellValueFactory(new PropertyValueFactory<>("Prefixo"));
                col_SB.setCellValueFactory(new PropertyValueFactory<>("SB"));
                col_NomeDependencia.setCellValueFactory(new PropertyValueFactory<>("NomeDependencia"));
                col_ProblemaApresentado.setCellValueFactory(new PropertyValueFactory<>("ProblemaApresentado"));
                col_DataHoraAbertura.setCellValueFactory(new PropertyValueFactory<>("DataHoraAbertura"));
                col_UF.setCellValueFactory(new PropertyValueFactory<>("UF"));
                col_NumeroContrato.setCellValueFactory(new PropertyValueFactory<>("NumeroContrato"));
                col_Fornecedor.setCellValueFactory(new PropertyValueFactory<>("Fornecedor"));
                col_NrSOL.setCellValueFactory(new PropertyValueFactory<>("NrSOL"));
                col_TipoManutencao.setCellValueFactory(new PropertyValueFactory<>("TipoManutencao"));

                tb_os.setItems(observableListOS);

            } else {
            Platform.runLater(() -> {

                util.alertaValidacao("Não foram localizados no registro");

            });

            }
        } else {
            Platform.runLater(() -> {

                util.alertaValidacao("Preencher as datas no formato AAAAMMDD");

            });

        }
    }

    @FXML
    private void GeraExcel(ActionEvent event) {
        
        GeraExcel gerar = new GeraExcel();
        List<TabelaOS> listaExcel = tb_os.getItems();
        gerar.geraExcelPendentes(listaExcel);
    }
    
     @FXML
    private void inputDataKeyTypedDataInicial(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("####-##-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txt_dtinicio);
        tff.formatter();
    }

    @FXML
    private void inputDataKeyTypedDataFinal(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("####-##-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(txt_dtfim);
        tff.formatter();
    }


}
