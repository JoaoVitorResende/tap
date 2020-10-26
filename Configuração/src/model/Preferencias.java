package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Preferencias {
	 private StringProperty imagem = new SimpleStringProperty("");
	 private IntegerProperty altura = new SimpleIntegerProperty(0);
	 private IntegerProperty largura = new SimpleIntegerProperty(0);
	 
	 
	 public final StringProperty imagemProperty() {
			return this.imagem;
		}
		public final String getImagem() {
			return this.imagemProperty().get();
		}
		public final void setImagem(final String imagem) {
			this.imagemProperty().set(imagem);
		}
		//
		public final IntegerProperty alturaProperty() {//inteiro
			return this.largura;
		}
		public final int getlargura() {// inteiro
			return this.alturaProperty().get();
		}
		public final void setLargura(final int altura) {
			this.alturaProperty().set(altura);
		}
		//
		public final IntegerProperty larguraProperty() {//inteiro
			return this.largura;
		}
		public final int getaltura() {// inteiro
			return this.larguraProperty().get();
		}
		public final void setEstoque(final int largura) {
			this.larguraProperty().set(largura);
		}
}
