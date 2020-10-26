package application;

import dao.ContaDao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Conta;

public class OperacaoController {

	@FXML TextField txtvalor;
	@FXML TextField txtorigem;
	@FXML TextField txtdestino;
	@FXML TextField txtfiltro;
	@FXML TableView<Conta> contas ;
	@FXML TableColumn<Conta, String> colcorrentista;
	@FXML TableColumn<Conta, String> colagencia;
	@FXML TableColumn<Conta, Number> colsaldo;
	@FXML RadioButton rbtransferencia;
	@FXML RadioButton rbsacar;
	@FXML RadioButton rbdepositar;
	
	private ContaDao contadao = new ContaDao () ;
	private Conta corigem = null;
	private Conta cdestino = null;
	
	@FXML public void initialize() {
		contas.setItems(FXCollections.observableArrayList(contadao.listartodoas()));
		configuraTabela();
	}
	@FXML public void realizaoperacao() {
		try {
			double valor = Double.parseDouble(txtvalor.getText());
			if(valor<=0 || txtvalor.getText().equals("")) {
				System.out.println("teste");
			}else {
				double saldooirgem = corigem.getSaldo();
				double vlope = Double.parseDouble(txtvalor.getText());
				if(rbdepositar.isSelected()) {
					rbsacar.setSelected(false);
					rbtransferencia.setSelected(false);
					contadao.AtualizaSaldo(corigem, saldooirgem+vlope);
					mensagem("deposito feito com sucesso");
				}
				if(rbsacar.isSelected()) {
					rbdepositar.setSelected(false);
					rbtransferencia.setSelected(false);
					if(saldooirgem >=vlope) {
						contadao.AtualizaSaldo(corigem,saldooirgem-vlope);
						mensagem("saque feito com sucesso");
					}else {
						mensagem("saldo insuficiente");
						limpa();
					}
				}
				if(rbtransferencia.isSelected()) {
					if(saldooirgem>= vlope) {
						double saldodestino = cdestino.getSaldo();
						contadao.AtualizaSaldo(corigem,saldooirgem-vlope);
						contadao.AtualizaSaldo(cdestino,saldodestino+vlope);
						corigem.setSaldo(saldooirgem-vlope);
						cdestino.setSaldo(saldodestino+vlope);
						txtdestino.setText(cdestino.toString());
					}else {
						mensagem("saldo insuficiente");
						limpa();
					}
				}
				contas.setItems(FXCollections.observableArrayList(contadao.listartodoas()));
				limpa();
			}
		} catch (Exception e) {
		 mensagem("o valor não pode ser menor que zero ou igual ao mesmo ");
		}
		
	}
	
	private void mensagem (String msg ) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("operação");
		a.setHeaderText("resultado : ");
		a.setContentText(msg);
		a.show();
	}
	private void limpa() {
		txtdestino.setText(" ");
		txtorigem.setText(" ");
		txtfiltro.setText(" ");
		txtvalor.setText(" ");
		corigem = null;
		cdestino = null;
	}
	
	private void configuraTabela() {
		colcorrentista.setCellValueFactory(cellData -> cellData.getValue().getCorrentista().NomeProperty());
		colagencia.setCellValueFactory(cellData -> cellData.getValue().getAgencia().cidadeProperty());
		colsaldo.setCellValueFactory(cellData -> cellData.getValue().saldoProperty());
	}
	
	@FXML public void filtracontas () {
		contas.setItems(FXCollections.observableArrayList(contadao.filtroPorCorrentista(txtfiltro.getText())));
	}
	
	@FXML public void selecionaconta() {
		if(corigem == null ) {
			corigem = contas.getSelectionModel().getSelectedItem();
			txtorigem.setText(corigem.toString());
		}else {
			
			if(rbtransferencia.isSelected()) {
				cdestino = contas.getSelectionModel().getSelectedItem();
					if(cdestino.equals(corigem)) {
						mensagem("mesma conta não pode ser efetuado uma transferencia");
						}
				txtdestino.setText(cdestino.toString());
			}
		}
	}
	
}
