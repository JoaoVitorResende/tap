package application;

import dao.Dao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Agencia;
import model.Conta;

public class AgenciaController {
	@FXML TextField txtnumero;
	@FXML TextField txtcidade;
	@FXML CheckBox  cInativar;
	@FXML  Button btacao;
	@FXML TableView<Agencia> agencias;
	@FXML TableColumn<Agencia,String> cnumero;
	@FXML TableColumn<Agencia,String> ccidade;
	@FXML TableColumn<Conta,Number> csaldo;
	@FXML TextField txtfiltro;
	
	@FXML public void initialize() {
		agencias.setItems(FXCollections.observableArrayList(d.listartodoas()));
		cnumero.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());
		ccidade.setCellValueFactory(cellData -> cellData.getValue().cidadeProperty());
		
	}
		
	@FXML
	public void inserir() {
	Agencia a = tela4Agencia();
	if(cInativar.isSelected()) {
		d.Inativar(agencia);
	  }else {
		 if(agencia!=null) {
			a.setId(agencia.getId());
			d.alterar(a);
		    }else {
			 d.inserir(a);
		}
	}
	cInativar.setSelected(false);
	cInativar.setDisable(true);
	agencia = null;
	btacao.setText("inserir");
	agencias.setItems(FXCollections.observableArrayList());
	}
	@FXML public void filtrar() {
		if(txtfiltro.getText().equals("")) {
			agencias.setItems(FXCollections.observableArrayList(d.listartodoas()));
		}else {
			agencias.setItems(FXCollections.observableArrayList(d.filtrar(txtfiltro.getText())));
		}
		
	}
    private Dao d  = new Dao();
    private  Agencia agencia = null;
	 
	private Agencia tela4Agencia() {
		Agencia a = new Agencia();
		a.setNumero(txtnumero.getText());
		a.setCidade(txtcidade.getText());
		return a;
	}
	private void agencia4tela(Agencia a) {
		txtnumero.setText(a.getNumero());
		txtcidade.setText(a.getCidade());
	}
	@FXML public void selecionaAgencia() {
		agencia = agencias.getSelectionModel().getSelectedItem();
		if(agencia != null) {
			agencia4tela(agencia);
			btacao.setText("Alterar");
			cInativar.setDisable(false);
		}
	}
}
