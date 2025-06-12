/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Admin;
import model.bean.Usuario;

/**
 *
 * @author Kazuto
 */
public class UsuarioDao {
    public void create(Usuario u) throws SQLException{
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
        stmt = con.prepareStatement("INSERT INTO usuario (nome,senha,dataNasc) VALUES (?,?,?)");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setDate(3, java.sql.Date.valueOf(u.getDataNasc()));
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             Conexao.closeConnection(con, stmt);
        }
    }
    public void update(Usuario u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome =?,senha=?,dataNasc=? WHERE id=?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setDate(3,java.sql.Date.valueOf(u.getDataNasc()));
            stmt.setInt(4, u.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao atualizar");
        }
        
    }
    public void delete(Usuario u){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id=?");
            stmt.setInt(1, u.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deletado com sucesso");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Falha ao deletar");
        }
        
    }
    public void login(String email, String senha){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            stmt =con.prepareStatement("SELECT * FROM usuario WHERE email = ? and senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Usuario u = new Usuario();
                
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
            }
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    public List<Usuario> read(){
        Connection con= Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs =  null;
        
        List<Usuario> Usuarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                Date data = rs.getDate("dataNasc");
                if(data != null){
                    u.setDataNasc(data.toLocalDate());                
                }
                Usuarios.add(u);
            }
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return Usuarios;
    }
    public Usuario readById(int id){
        String sql = "SELECT * FROM usuario WHERE id=?";
        Usuario u = null;
        try (Connection con = Conexao.getConnection();
                PreparedStatement stmt = con.prepareStatement(sql);){
                        
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setDataNasc(rs.getDate("dataNasc").toLocalDate());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
}

