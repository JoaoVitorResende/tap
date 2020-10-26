package application;

import java.util.ArrayList;

import Model.Pessoa;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsuarioController {
	@FXML
	TextField txnome ;
	@FXML
	TextField txidade ;
	@FXML
	private RadioButton rdMasculino;
	@FXML
	private RadioButton rdFeminino;
	@FXML
	private TableView<Pessoa> tblpessoas;
	@FXML
	private TableColumn<Pessoa, String> colnome;
	@FXML
	private TableColumn<Pessoa, Number> colIdade;
	@FXML
	private TableColumn<Pessoa, String> colSexo;

	
	private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

	@FXML
	public void adiciona() {
		Pessoa p = new Pessoa();
		p.setNome(txnome.getText());
		p.setidade(Integer.parseInt(txidade.getText()));
		p.setSexo(rdMasculino.isSelected()?"Masculino":"Feminino");
		pessoas.add(p);
		
		tblpessoas.setItems((FXCollections.observableArrayList(pessoas)));
		limpaTela();
	}
	@FXML
	public void initialize() { // INICIALIZACAO PADRAO DE TABELA
		colnome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colIdade.setCellValueFactory(cellData -> cellData.getValue().idadeProperty());
		colSexo.setCellValueFactory(cellData -> cellData.getValue().sexoProperty());
	}
	private void limpaTela() {
		txnome.setText("");
		txidade.setText("");
		rdMasculino.setSelected(false);
		rdFeminino.setSelected(false);
		txnome.requestFocus();
	}
}
