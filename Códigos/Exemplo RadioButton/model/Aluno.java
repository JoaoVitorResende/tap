package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aluno {

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty sexo = new SimpleStringProperty("");
	private IntegerProperty idade = new SimpleIntegerProperty(0);

	public StringProperty nomeProperty() {
		return this.nome;
	}

	public String getNome() {
		return this.nomeProperty().get();
	}

	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
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

	public IntegerProperty idadeProperty() {
		return this.idade;
	}

	public int getIdade() {
		return this.idadeProperty().get();
	}

	public void setIdade(final int idade) {
		this.idadeProperty().set(idade);
	}

}
