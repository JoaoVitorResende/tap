package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import lib.Constantes;
import model.Agencia;
import model.Conta;
import model.Correntista1;

public class ContaDao {
	 public void inserir(Conta c) {
		  String sql = "insert into conta (agencia,correntista)values(?,?)";
		  try {
			PreparedStatement ps = Constantes.conn.prepareStatement(sql);
			ps.setInt(1,c.getAgencia().getId());
			ps.setInt(2,c.getCorrentista().getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	 
	 public ArrayList<Conta> listartodoas(){
		  Dao agdao = new Dao();
		  CorrentistaDao cd = new CorrentistaDao();
		  String sql = "select * from conta where status='A' order by saldo desc ";
		  ArrayList<Conta>lista = new ArrayList<Conta>();
		  try {
			  Connection conn = Constantes.conn;
			  PreparedStatement ps =  conn.prepareStatement(sql);
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  Conta c = new Conta();
				  c.setId(rs.getInt("id"));
				  c.setSaldo(rs.getDouble("saldo"));
				  c.setAgencia(agdao.buscarPorID(rs.getInt("agencia")));
				  c.setCorrentista(cd.buscarPorID(rs.getInt("correntista")));
				  lista.add(c);
			  }
		} catch (Exception e) {
		  e.printStackTrace();
		}
		  return lista;
	  }
	
	 public void Inativar(Conta c) {
		  String sql = "update conta set status ='I' where id=?";
		  try {
			  Connection conn = Constantes.conn;
			  PreparedStatement ps = Constantes.conn.prepareStatement(sql);
			  ps.setInt(1,c.getId());
			  ps.executeUpdate();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	 
	 public void alterar(Conta c) {
		  String sql = "update conta set agencia = ?, correntista =? where id=?";
		  try {
			  Connection conn = Constantes.conn;
			PreparedStatement ps = Constantes.conn.prepareStatement(sql);
			ps.setInt(1,c.getAgencia().getId());
			ps.setInt(2,c.getCorrentista().getId());
			ps.setInt(3,c.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	 
	 public void AtualizaSaldo(Conta c,double saldo) {
		  String sql = "update conta set saldo = ? where id = ?";
		  try {
			  Connection conn = Constantes.conn;
			  PreparedStatement ps = Constantes.conn.prepareStatement(sql);
			  ps.setDouble(1,saldo);
			  ps.setInt(2,c.getId());
			  ps.executeUpdate();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	 
	 public ArrayList<Conta> filtroPorCorrentista(String nomecorr){
		  Dao agdao = new Dao();
		  CorrentistaDao cd = new CorrentistaDao();
		  String sql = "select conta.id, conta.agencia, conta.correntista, "
		  		+ "conta.saldo from conta, correntista"
		  		+ " where conta.correntista = correntista.id "
		  		+ "and conta.status = 'A' "
		  		+ "and correntista.nome like ?";
		  ArrayList<Conta>lista = new ArrayList<Conta>();
		  try {
			  Connection conn = Constantes.conn;
			  PreparedStatement ps =  conn.prepareStatement(sql);
			  ps.setString(1,nomecorr+"%");
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  Conta c = new Conta();
				  c.setId(rs.getInt("id"));
				  c.setSaldo(rs.getDouble("saldo"));
				  c.setAgencia(agdao.buscarPorID(rs.getInt("agencia")));
				  c.setCorrentista(cd.buscarPorID(rs.getInt("correntista")));
				  lista.add(c);
			  }
		} catch (Exception e) {
		  e.printStackTrace();
		}
		  return lista;
	  }
}
