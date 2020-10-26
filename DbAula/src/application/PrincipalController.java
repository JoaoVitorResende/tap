package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import lib.ConeccaoDb;
import lib.Constantes;

public class PrincipalController {
	@FXML 
	public void initialize() {
		Constantes.conn = ConeccaoDb.conectaBd();
	}
	
	@FXML
	private TabPane Pane;//variavel do pane 
	
	@FXML
	public void abreTela1() {
		abreTab("Agencia","Tela1.fxml");//abre tela
	}
	@FXML
	public void abreTela2() {
		abreTab("Correntista","Tela2.fxml");//abre tela
	}
	@FXML
	public void abreTela3() {
		abreTab("Conta","Tela3.fxml");//abre tela
	}
	@FXML
	public void abreTela4() {
		abreTab("Operações","Tela4.fxml");//abre tela
	}

	private Tab tabAberta(String titulo) { // verifica se a tablea está aberta
		for (Tab tb : Pane.getTabs()) {
			if (tb.getText().equals(titulo))
				return tb;
		}
		return null;
	}

	private void abreTab(String titulo, String path) { //abre nova janela 
		try {
			Tab tab = tabAberta(titulo);
			if (tab == null) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				Pane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));//aqui cria tela nao aberta
			}
			selecionaTab(tab);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selecionaTab(Tab tab) {//foco na janela aberta
		Pane.getSelectionModel().select(tab);
	}
}
