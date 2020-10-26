package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class PrincipalController {
	public String linha;
	@FXML
	private ImageView img;
    @FXML
    TextField txtaltura;
    @FXML
    TextField txtlargura;
    
    
    @FXML
    AnchorPane a;
    
    String caminho;
    @FXML
    public void initialize() {
    	File f = new File("C:\\Users\\apoio.inftb.UNISUL\\Downloads\\atividades.properties");
    	if(f.exists()) {
    		leitura();
    	}
    }
	//busca a imagem
	private File selecionaImagem() {
		   FileChooser dialago = new FileChooser();
		 dialago.getExtensionFilters().add(new 
				   FileChooser.ExtensionFilter(
						   "Imagens", "*.jpg", "*.JPG", 
						   "*.png", "*.PNG", "*.gif", "*.GIF", 
						   "*.bmp", "*.BMP")); 	
		 dialago.setInitialDirectory(new File(
				   "C:\\Users\\apoio.inftb.UNISUL\\Desktop"));
		   return dialago.showOpenDialog(null);
	}
	@FXML
	public void abreImagem() {
		File f = selecionaImagem();
		if (f != null) {
			caminho = f.toURI().toString();
			Image i = new Image(f.toURI().toString());// corrige caminho da imagem
			img.setImage(i);
			img.setFitWidth(i.getWidth());
			img.setFitHeight(i.getHeight());
		}
	}
	@FXML
	public void escrever() {
		System.out.println("teste");
	Properties p = new Properties();
	p.setProperty("caminho", caminho);
        p.setProperty("altura", txtaltura.getText());
	p.setProperty("largura", txtlargura.getText());
	try {
		FileWriter fw = new FileWriter("C:\\Users\\apoio.inftb.UNISUL\\Downloads\\atividades.properties");
		p.store(fw,"agora vai ");
		fw.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	//
	@FXML
	public void leitura() {
		System.out.println("lendo");
	Properties properties = new Properties();
	try {
		FileReader fr = new FileReader("C:\\Users\\apoio.inftb.UNISUL\\Downloads\\atividades.properties");
		properties.load(fr);
		Image i = new Image(properties.getProperty("caminho"));// corrige caminho da imagem
		img.setImage(i);
		img.setFitWidth(i.getWidth());
		img.setFitHeight(i.getHeight());
		int largura = Integer.parseInt(properties.getProperty("largura"));
		int altura = Integer.parseInt(properties.getProperty("altura"));
		a.setPrefSize(largura,altura);
		
	}catch (Exception e) {
	  e.printStackTrace();
		}
	}
		
}
