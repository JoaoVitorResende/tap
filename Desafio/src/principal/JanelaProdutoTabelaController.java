package principal;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produto;

public class JanelaProdutoTabelaController {
     @FXML
     Label lbnome;
     @FXML
     Label lbQuantidade;
     @FXML
     Label lbValor;
     @FXML
     Label lbsubtotal;
	 @FXML
	 public Button sair;//sair
	 @FXML
	 public ImageView img;
	 @FXML
	 public void fechajanela() {//metodo para sair da modal
		 Stage stage = (Stage) sair.getScene().getWindow();
		 stage.close();
	 }
    public void trocanome (String n) {
    	lbnome.setText("nome do produto "+n);
    }
    public void trocarvalor(double v) {
    	lbValor.setText("Valor "+v);
    }
    public void trocarquantidade(int q) {
    	lbQuantidade.setText("quantidade "+q);
    }
    public void trocasubtotal(double s) {
    	lbsubtotal.setText("subtotal "+s);
    }
    @FXML
    public void abreImagem(File imagem) {
		File f = imagem ;
		if (f != null) {
			Image i = new Image(f.toURI().toString());// corrige caminho da imagem
			img.setImage(i);
			img.setFitWidth(200);
			img.setFitHeight(200);
		}
	}
}
