package model;

import java.io.File;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

public class Produto {
	
	 private StringProperty nome = new SimpleStringProperty(""); //nome do produto
	 private DoubleProperty  valor = new SimpleDoubleProperty(0);// valor do produto
	 private IntegerProperty quantidade = new SimpleIntegerProperty(0);//quantidade do produto
	 private DoubleProperty subtotal = new SimpleDoubleProperty(0);//sub total dos produtos
	 private File imagem = new File(" ") ;
	 
		public File getImagem() {
		return imagem;
	    }
	    public void setImagem(File imagem) {
		   this.imagem = imagem;
	     }
		public final StringProperty nomeProperty() {
			return this.nome;
		}
		public final String getNome() {
			return this.nomeProperty().get();
		}
		public final void setNome(final String nome) {
			this.nomeProperty().set(nome);
		}
		public final DoubleProperty valorProperty() {
			return this.valor;
		}
		public final double getValor() {
			return this.valorProperty().get();
		}
		public final void setValor(final double valor) {
			this.valorProperty().set(valor);
		}
		public final IntegerProperty quantidadeProperty() {//inteiro
			return this.quantidade;
		}
		public final int getquantidade() {// inteiro
			return this.quantidadeProperty().get();
		}
		public final void setQuantidade(final int quantidade) {
			this.quantidadeProperty().set(quantidade);
		}
		public final DoubleProperty subtotalProperty() {
			return this.subtotal;
		}
		public final double getsubtotal() {
			return this.subtotalProperty().get();
		}
		public final void setsubtotal(final double d) {
			this.subtotalProperty().set(d);
		}
}
