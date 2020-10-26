package application;

import dao.CorrentistaDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Agencia;
import model.Correntista1;

public class CorrentistaController1 {

	
	@FXML TextField txtnome;
	@FXML TextField txtNascimento;
	@FXML TableView<Correntista1> correntistas;
	@FXML TableColumn<Correntista1,String> cnome;
	@FXML TableColumn<Correntista1,String> cnascimento;
	
	private CorrentistaDao cd = new CorrentistaDao();
    private Correntista1 correntista = null;
     
	@FXML public void initialize() {
		correntistas.setItems(FXCollections.observableArrayList());
		cnome.setCellValueFactory((cellData -> cellData.getValue().NomeProperty()));
		cnascimento.setCellValueFactory((cellData -> cellData.getValue().nascimentoProperty()));
	}
	
	@FXML public void inserir () {
		Correntista1 c = tela4Correntista();
		 cd.inserir(c);
	}
	
	private Correntista1 tela4Correntista() {
		Correntista1 c = new Correntista1();
		c.setNome(txtnome.getText());
		c.setNascimento(txtNascimento.getText());
		return c;
	}
	
	private void agencia4tela(Correntista1 c) {
		txtnome.setText(c.getnome());
		txtNascimento.setText(c.getnascimento());
	}
	
	
}
