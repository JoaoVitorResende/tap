package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lib.Constantes;
import model.Conta;
import model.Correntista1;

public class CorrentistaDao {
	
	 public void inserir (Correntista1 c) {
		   String sql ="insert into correntista(nome,nascimento)values(?,?)";
		   try {
			   PreparedStatement ps = Constantes.conn.prepareStatement(sql);
			   ps.setString(1,c.getnome());
			   ps.setString(2,c.getnascimento());
			   ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	 
	 public Correntista1 buscarPorID(int id){
		   String sql = "select * from correntista where id=?";
		   Correntista1 c = new Correntista1();
		  try {
			  Connection conn = Constantes.conn;
			  PreparedStatement ps =  conn.prepareStatement(sql);
			  ps.setInt(1,id);
			  ResultSet rs = ps.executeQuery();
			    if(rs.next()) {
				  c.setId(rs.getInt("id"));
				  c.setNome(rs.getString("nome"));
				  c.setNascimento(rs.getString("nascimento"));
			  }
		} catch (Exception e) {
		  e.printStackTrace();
		}
		  return c;
	  }
	 
	 public ArrayList<Correntista1> listartodoas(){
		  String sql = "select * from correntista where status='A' order by nome ";
		  ArrayList<Correntista1>lista = new ArrayList<Correntista1>();
		  try {
			  Connection conn = Constantes.conn;
			  PreparedStatement ps =  conn.prepareStatement(sql);
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  Correntista1 c = new Correntista1();
				  c.setId(rs.getInt("id"));
				  c.setNome(rs.getString("nome"));
				  c.setNascimento(rs.getString("nascimento"));
				  lista.add(c);
			  }
		} catch (Exception e) {
		  e.printStackTrace();
		}
		  return lista;
	  }
}
