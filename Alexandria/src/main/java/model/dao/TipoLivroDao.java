package model.dao;
import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.TipoLivro;

/**
 *
 * @author Kazuto
 */
public class TipoLivroDao {
    
    public void create(TipoLivro t){
        Connection con= Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO tipoLivros (tipo) VALUES (?)");
            stmt.setString(1, t.getTipo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Tipo de livro cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Cadastrar:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
    
     public List<TipoLivro> read(){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<TipoLivro> tipoLivros = new ArrayList<>();
                
        try {
            stmt = con.prepareStatement("SELECT * FROM tipoLivros");
            rs = stmt.executeQuery();
            while (rs.next()){
                TipoLivro tipoLivro = new TipoLivro();
                tipoLivro.setId(rs.getInt("id"));
                tipoLivro.setTipo(rs.getString("tipo"));
                tipoLivros.add(tipoLivro);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao carregar as informações!:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt, rs);
        } 
        return tipoLivros;
    }
    
      public void update(TipoLivro t){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE tipoLivros SET tipo = ? WHERE id = ?");
            stmt.setString(1, t.getTipo());
            stmt.setInt(2,t.getId());
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    }
      public void delete(TipoLivro t){
        Connection con = Conexao.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM tipoLivros WHERE id = ?");
            stmt.setInt(1, t.getId());
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir:"+ex);
        }finally{
            Conexao.closeConnection(con, stmt);
        }
    
    }
}
