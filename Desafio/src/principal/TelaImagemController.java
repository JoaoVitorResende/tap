package principal;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Produto;

public class TelaImagemController {
	
	
	 @FXML
	 private ImageView img;//mapeia imagem
	 @FXML
	 public Button sair;//sair
	 @FXML
	 public Button Aplicar;
	 @FXML
	 public void fechajanela() {//metodo para sair da modal
		 Stage stage = (Stage) sair.getScene().getWindow();
		 stage.close();
	 }
	@FXML
	public void abreImagem() {
		File f = selecionaImagem();
		if (f != null) {
			Image i = new Image(f.toURI().toString());// corrige caminho da imagem
			img.setImage(i);
			img.setFitWidth(i.getWidth());
			img.setFitHeight(i.getHeight());
		}
	}
	public File selecionaImagem() {//o que abre a imagem
		   FileChooser dialago = new FileChooser();
		   PrincipalController principal = new PrincipalController();
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
