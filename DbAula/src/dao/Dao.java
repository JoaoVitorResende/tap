package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lib.Constantes;
import model.Agencia;

public class Dao {
  public void inserir(Agencia a) {
	  String sql = "insert into agencia(numero,cidade)values(?,?)";
	  try {
		PreparedStatement ps = Constantes.conn.prepareStatement(sql);
		ps.setString(1,a.getNumero());
		ps.setString(2,a.getCidade());
		ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  public ArrayList<Agencia> listartodoas(){
	  String sql = "select * from agencia where status='A' order by cidade ";
	  ArrayList<Agencia>lista = new ArrayList<Agencia>();
	  try {
		  Connection conn = Constantes.conn;
		  PreparedStatement ps =  conn.prepareStatement(sql);
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()) {
			  Agencia a = new Agencia();
			  a.setId(rs.getInt("id"));
			  a.setCidade(rs.getString("cidade"));
			  a.setNumero(rs.getString("numero"));
			  lista.add(a);
		  }
	} catch (Exception e) {
	  e.printStackTrace();
	}
	  return lista;
  }
  
  public Agencia  buscarPorID(int id){
	   String sql = "select * from agencia where id=?";
	   Agencia a = new Agencia();
	  try {
		  Connection conn = Constantes.conn;
		  PreparedStatement ps =  conn.prepareStatement(sql);
		  ps.setInt(1,id);
		  ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
			  a.setId(rs.getInt("id"));
			  a.setCidade(rs.getString("cidade"));
			  a.setNumero(rs.getString("numero"));
		  }
	} catch (Exception e) {
	  e.printStackTrace();
	}
	  return a;
  }
  
  public ArrayList<Agencia> filtrar(String filtro){
	  String sql = "select * from agencia where status='A' and cidade like ?  order by cidade";
	  ArrayList<Agencia>lista = new ArrayList<Agencia>();
	  try {
		  Connection conn = Constantes.conn;
		  PreparedStatement ps =  conn.prepareStatement(sql);
		  ps.setString(1,filtro+"%");
		  ResultSet rs = ps.executeQuery();
		  while(rs.next()) {
			  Agencia a = new Agencia();
			  a.setId(rs.getInt("id"));
			  a.setCidade(rs.getString("cidade"));
			  a.setNumero(rs.getString("numero"));
			  lista.add(a);
		  }
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
	  return lista;
  }
  
  public void alterar(Agencia a) {
	  String sql = "update agencia set numero = ?,cidade=? where id=?";
	  try {
		  Connection conn = Constantes.conn;
		PreparedStatement ps = Constantes.conn.prepareStatement(sql);
		ps.setString(1,a.getNumero());
		ps.setString(2,a.getCidade());
		ps.setInt(3,a.getId());
		ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public void Inativar(Agencia a) {
	  String sql = "update agencia set status ='I' where id=?";
	  try {
		  Connection conn = Constantes.conn;
		  PreparedStatement ps = Constantes.conn.prepareStatement(sql);
		  ps.setInt(1,a.getId());
		  ps.executeUpdate();
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
  }
}
