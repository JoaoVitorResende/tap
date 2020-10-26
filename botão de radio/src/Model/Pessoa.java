package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa{
	
	 private StringProperty nome = new SimpleStringProperty(""); 
	 private StringProperty sexo = new SimpleStringProperty("");
	 private IntegerProperty idade = new SimpleIntegerProperty(0);
	 
	 public final StringProperty nomeProperty() {
			return this.nome;
		}
		public final String getNome() {
			return this.nomeProperty().get();
		}
		public final void setNome(final String nome) {
			this.nomeProperty().set(nome);
		}
		public final IntegerProperty idadeProperty() {//inteiro
			return this.idade;
		}
		public final int getquantidade() {// inteiro
			return this.idadeProperty().get();
		}
		public final void setidade(final int idade) {
			this.idadeProperty().set(idade);
		}
		public StringProperty sexoProperty() {
			return this.sexo;
		}
		public String getSexo() {
			return this.sexoProperty().get();
		}
		public void setSexo(final String sexo) {
			this.sexoProperty().set(sexo);
		}
}
