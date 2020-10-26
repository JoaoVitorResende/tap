package principal;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Produto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrincipalController {
	@FXML
	private TextField txtnome;
	@FXML
	private TextField txtvalor;
	@FXML
	private TextField txtTotal;
	@FXML
	private TextField txtquantidade;
	@FXML
	private TableView<Produto> tblprodutos;
	@FXML
	private TableColumn<Produto, String> colnome;
	@FXML
	private TableColumn<Produto, Number> colvalor;
	@FXML
	private TableColumn<Produto, Number> colquantidade;
	@FXML
	private TableColumn<Produto, Number> colsubtotal;

	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	

	private void limpaTela() {
		txtnome.setText("");
		txtvalor.setText("");
		txtquantidade.setText("");
		txtnome.requestFocus();
	}

	@FXML
	public void cadastra() {
		if (validador()) {
			double total = 0;
			Produto p = new Produto();
			p.setNome(txtnome.getText());
			p.setValor(Double.parseDouble(txtvalor.getText()));
			p.setQuantidade(Integer.parseInt(txtquantidade.getText()));
			p.setsubtotal((p.getValor() * p.getquantidade()));
			p.setImagem(selecionaImagem());//salva imagem aqui 
			produtos.add(p);
			limpaTela();
			// faz total
			for (Produto produtos : produtos) {
				total += produtos.getsubtotal();
				txtTotal.setText(total + "");
			}
			tblprodutos.setItems((FXCollections.observableArrayList(produtos)));
		}
	}

	@FXML
	public void initialize() { // INICIALIZACAO PADRAO DE TABELA
		colnome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colvalor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colquantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty());
		colsubtotal.setCellValueFactory(cellData -> cellData.getValue().subtotalProperty());
	}

	private boolean validador() { // VALIDA SE OS CAMPOS ESTAO PREENCHIDOS
		if (txtnome.getText().equals("")) {
			mensagemErroValidacao("NOME");
			return false;
		}
		if (txtvalor.getText().equals("")) {
			mensagemErroValidacao("valor");
			return false;
		}
		if (txtquantidade.equals("")) {
			mensagemErroValidacao("quantidade");
			return false;
		}
		return true;
	}
	private void mensagemErroValidacao(String erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Erro de validacao");
		alert.setContentText("Erro de validacao no campo: " + erro + "\n Preenchimento obrigatorio.");//mensagem de erro a variavel erro irá mostrar o erro em especifico
		alert.initStyle(StageStyle.UNDECORATED);//  tira os botões do windows para fechar e maximizar a janela 
		alert.getDialogPane().setStyle("-fx-border-color: black; -fx-boerder-width: 3;");
		alert.show();
	}
	
	@FXML
	public void AbreJanelaProduto() {
		try {
				Stage janelaprodutotab = new Stage();// variavel para dar load na janela
				FXMLLoader loader = new FXMLLoader(getClass().getResource("JanelaProdutoTabela.fxml"));
				Parent root = loader.load();
				janelaprodutotab .setScene(new Scene(root));
				janelaprodutotab .initOwner(txtnome.getScene().getWindow());// é necessario mapear usando alguma coisa já mapeada obrigatorio
				janelaprodutotab .show();//enviar para outra janela
				JanelaProdutoTabelaController controller = loader.getController();//controler da outra janela 
				controller.trocanome(tblprodutos.getSelectionModel().getSelectedItem().getNome());//da tabela envia nome
				controller.trocarvalor(tblprodutos.getSelectionModel().getSelectedItem().getValor());//da tabela envia valor
				controller.trocarquantidade(tblprodutos.getSelectionModel().getSelectedItem().getquantidade());//da tabela envia quantidade
				controller.trocasubtotal(tblprodutos.getSelectionModel().getSelectedItem().getsubtotal());//da tabela envia subtotal
				controller.abreImagem(tblprodutos.getSelectionModel().getSelectedItem().getImagem());//envia para a outra pagina aqui
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@FXML
	public File selecionaImagem() {//o que abre a imagem
		   FileChooser dialago = new FileChooser();
		 dialago.getExtensionFilters().add(new 
				   FileChooser.ExtensionFilter(
						   "Imagens", "*.jpg", "*.JPG", 
						   "*.png", "*.PNG", "*.gif", "*.GIF", 
						   "*.bmp", "*.BMP")); 	
		 dialago.setInitialDirectory(new File(
				   "C:\\Users\\joaov\\Pictures"));//caminho do metodo para encontrar as imagens
		   return dialago.showOpenDialog(null);
	}
}
