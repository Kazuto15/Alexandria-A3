/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;
import javax.swing.JOptionPane;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Livro;

/**
 *
 * @author Kazuto
 */
public class LivroDao {
    public void create(Livro l) throws SQLException{
        Connection con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO livros (nome,id_tipo,autor,feedback, usuario_id) VALUES (?,?,?,?,?)");
            stmt.setString(1, l.getNome());
            stmt.setInt(2, l.getIdTipo());
            stmt.setString(3, l.getAutor());
            stmt.setString(4, l.getFeedback());
            stmt.setInt(5, l.getUsuarioId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public List<Livro> read(int usuarioId){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Livro> livros = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("SELECT l.id, l.nome, l.autor, l.feedback, t.tipo FROM livros l JOIN tipoLivros t ON l.id_tipo = t.id WHERE l.usuario_id = ? ORDER BY l.id ASC");
            stmt.setInt(1, usuarioId);
            rs = stmt.executeQuery();
            while (rs.next()){
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("Nome"));
              //livro.setIdTipo(rs.getInt("Id_Tipo"));
                livro.setTipo(rs.getString("Tipo"));
                livro.setAutor(rs.getString("Autor"));
                livro.setFeedback(rs.getString("Feedback"));
                livros.add(livro);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao carregar as informações!:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        } 
        return livros;
    }
    
       public List<Livro> readFav(){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Livro> livros = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("SELECT livros.id,livros.nome, livros.autor,t.tipo FROM livros INNER JOIN livroFav AS lf1 ON lf1.id_livro = livros.id JOIN tipoLivros t ON livros.id_tipo = t.id ORDER BY livros.id ASC");
            rs = stmt.executeQuery();
            while (rs.next()){
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("Nome"));
                livro.setAutor(rs.getString("Autor"));
                livro.setTipo(rs.getString("Tipo"));
                livros.add(livro);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao carregar as informações!:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        } 
        return livros;
    }
    
    
    public void update(Livro l){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE livros SET nome = ?,id_tipo = ?,autor = ?,feedback = ?,WHERE id = ?");
            stmt.setString(1, l.getNome());
            stmt.setInt(2, l.getIdTipo());
            stmt.setString(3, l.getAutor());
            stmt.setString(4, l.getFeedback());
            stmt.setInt(5,l.getId());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public void delete(Livro l){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM livros WHERE id = ?");
            stmt.setInt(1, l.getId());
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
        
    }
    public int contarLivro(int usuarioId){
        int total = 0;
        String sql = "SELECT COUNT(*) AS total FROM livros WHERE usuario_id= ?";
        
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, usuarioId);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;  
    }
}
