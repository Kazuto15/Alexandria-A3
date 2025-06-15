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
            stmt = con.prepareStatement("INSERT INTO livros (nome,autor,id_tipo , usuario_id) VALUES (?,?,?,?)");
            stmt.setString(1, l.getNome());
            stmt.setString(2, l.getAutor());
            stmt.setInt(3, l.getIdTipo());
            stmt.setInt(4, l.getUsuarioId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
    public List<Livro> read(int idUsuario){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Livro> livros = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("SELECT l.id, l.nome, l.autor, t.tipo FROM livros l JOIN tipoLivros t ON l.id_tipo = t.id WHERE l.usuario_id = ? ORDER BY l.nome ASC");
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();
            while (rs.next()){
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setNome(rs.getString("nome"));
              //livro.setIdTipo(rs.getInt("Id_Tipo"));
                livro.setTipo(rs.getString("tipo"));
                livro.setAutor(rs.getString("autor"));
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
            stmt = con.prepareStatement("UPDATE livros SET nome = ?,id_tipo = ?,autor = ? WHERE id = ?");
            stmt.setString(1, l.getNome());
            stmt.setInt(2, l.getIdTipo());
            stmt.setString(3, l.getAutor());
            stmt.setInt(4,l.getId());
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
