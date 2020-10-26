package application;

import dao.ContaDao;
import dao.CorrentistaDao;
import dao.Dao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Agencia;
import model.Conta;
import model.Correntista1;

public class ContaController {
 
	@FXML private ComboBox<Agencia> cbagencia;
	@FXML private ComboBox<Correntista1> ccorrentista;
	@FXML private CheckBox ckInativar;
	@FXML private TextField txtfiltro;
	@FXML private TextField txtsaldo;
	@FXML private TableView<Conta> contas;
	@FXML private TableColumn<Conta,String> colcorrentista;
	@FXML private TableColumn<Conta,String> colagencia;
	@FXML private TableColumn<Conta,Number> colsaldo;
	@FXML private Button btnAcao;
	private ContaDao cd = new ContaDao();
	private Conta conta = null;

	@FXML
	public void initialize() {
		configuraTabela();
		preencheAgencia();
	    preencheCorrentista();
	    contas.setItems(FXCollections.observableArrayList(cd.listartodoas()));
	}

	private void configuraTabela() {
		colcorrentista.setCellValueFactory(cellData -> cellData.getValue().getCorrentista().NomeProperty());
		colagencia.setCellValueFactory(cellData -> cellData.getValue().getAgencia().cidadeProperty());
		colsaldo.setCellValueFactory(cellData -> cellData.getValue().saldoProperty());
	}

	private void preencheAgencia() {
		Dao agDao = new Dao();
		cbagencia.setItems(FXCollections.observableArrayList(agDao.listartodoas()));
	}

	private void preencheCorrentista() {
		CorrentistaDao corDao = new CorrentistaDao();
		ccorrentista.setItems(FXCollections.observableArrayList(corDao.listartodoas()));
	}
	
	private Conta tela4Conta() {
		Conta c = new Conta();
		c.setAgencia(cbagencia.getSelectionModel().getSelectedItem());
		c.setCorrentista(ccorrentista.getSelectionModel().getSelectedItem());
		return c;
	}
	
	@FXML private void Conta4tela(Conta c) {
		localizaAgencia(c.getAgencia());
		localizaCorrentista(c.getCorrentista());
		txtsaldo.setText("R$:"+c.getSaldo()+"");
	}
	
	private void localizaAgencia(Agencia ac) {
		 int indice  = 0 ;
		 int cont = 0 ;
		for(Agencia a : cbagencia.getItems()) {
			if(a.getId() == ac.getId()) {
				indice = cont ;
				cont ++;
			}
		}
		cbagencia.getSelectionModel().select(indice);
	}
	
	private void localizaCorrentista(Correntista1 cc) {
		int indice  = 0 ;
		int cont = 0 ;
		for(Correntista1 c : ccorrentista.getItems()) {
			if(c.getId() == cc.getId()) {
				indice = cont ;
				cont ++;
			}
		}
		ccorrentista.getSelectionModel().select(indice);
	}
	
@FXML public void inserir() {
		Conta c = tela4Conta();
		if(ckInativar.isSelected()) {
			cd.Inativar(conta);
		}else {
			if(conta!=null) {
				c.setId(conta.getId());
				cd.alterar(c);
					}else {
						cd.inserir(c);
					}
				}
		    			ckInativar.setSelected(false);
		    			ckInativar.setDisable(true);
		    			conta = null;
		    			btnAcao.setText("inserir");
		    			contas.setItems(FXCollections.observableArrayList(cd.listartodoas()));
	                }
         

   @FXML public void selecionaConta() {
	conta = contas.getSelectionModel().getSelectedItem();
	if(conta != null) {
		Conta4tela(conta);
		btnAcao.setText("Alterar");
		ckInativar.setDisable(false);
	}
}
	
}
